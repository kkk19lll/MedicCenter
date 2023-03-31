package com.example.mediccenter.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.accounts.AbstractAccountAuthenticator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.mediccenter.R;
import com.example.mediccenter.api.API;
import com.example.mediccenter.databinding.ActivityAuthBinding;
import com.example.mediccenter.models.CreateCardPaitentModel;
import com.example.mediccenter.models.UserAuth;
import com.example.mediccenter.view_model.AuthViewModel;
import com.example.mediccenter.view_model.CreateCardPatientViewModel;
import com.yandex.authsdk.YandexAuthException;
import com.yandex.authsdk.YandexAuthLoginOptions;
import com.yandex.authsdk.YandexAuthOptions;
import com.yandex.authsdk.YandexAuthSdk;
import com.yandex.authsdk.YandexAuthToken;

import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;

    private API api;

    YandexAuthSdk sdk;

    private static final int REQUEST_LOGIN_SDK = 100;

    AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        installViewModel();
        installEnableDisableBtnNext();
        binding.btnNext.setOnClickListener(v -> {
            postEmail();
            startActivity(new Intent(this, EnterCodeActivity.class));
            finish();
        });
        binding.btnAuthYandex.setOnClickListener(v -> {
            installYandex();
        });
    }

    private void installViewModel() {
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    private void installYandex() {
        sdk = new YandexAuthSdk(this, new YandexAuthOptions.Builder(this)
                .enableLogging()
                .build());
        authYandex();
    }

    private void authYandex() {
        final YandexAuthLoginOptions.Builder loginOptionsBuilder =  new YandexAuthLoginOptions.Builder();
        final Intent intent = sdk.createLoginIntent(loginOptionsBuilder.build());
        startActivityForResult(intent, REQUEST_LOGIN_SDK);
    }

    private void postEmail() {
        viewModel.getAuthUser().observe(this, user -> {
            if (user == null) {
                Toast.makeText(this, "Неудалось войти...", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Вы успешно вошли!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        UserAuth userAuth = new UserAuth(binding.editTextEmail.getText().toString());
        viewModel.authUser(userAuth);
    }

    private void installEnableDisableBtnNext() {
        binding.btnNext.setEnabled(false);
        binding.editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0){
                    binding.btnNext.setEnabled(true);
                    binding.btnNext.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                }
                else {
                    binding.btnNext.setEnabled(false);
                    binding.btnNext.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_OK && data.getData() != null) {
            try {
                final YandexAuthToken yandexAuthToken = sdk.extractToken(resultCode, data);
                if (yandexAuthToken != null) {
                    startActivity(new Intent(this, EnterCodeActivity.class));
                    finish();
                }
            } catch (YandexAuthException e) {
                System.out.println(e.getMessage());
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}