package com.example.mediccenter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.mediccenter.databinding.ActivityEnterCodeBinding;

import java.util.Objects;

public class EnterCodeActivity extends AppCompatActivity {

    ActivityEnterCodeBinding binding;

    int seconds;

    boolean flag = true;

    public static boolean flag_2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnterCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackAuth.setOnClickListener(v -> {
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        });

        binding.firstPinView.setOnClickListener(v -> {
            if (flag != flag_2) {
                installTimeCode();
                flag = false;
            }
        });
    }

    private void installTimeCode() {
        if (flag) {
            if (!binding.firstPinView.getText().toString().equals("1234")) {
                seconds = Integer.parseInt(Integer.valueOf(String.valueOf(binding.firstPinView.getText())).toString());
                CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTick(long l) {
                        binding.textViewTimerCode.setText("Отправить код можно\nбудет только через " + (int) (l/1000) + " секунд");
                    }

                    @Override
                    public void onFinish() {
                        flag = true;
                        binding.textViewTimerCode.setText("Введите код еще раз");
                    }
                }.start();
            }
            else {
                startActivity(new Intent(this, CreatePasswordActivity.class));
                finish();
            }
        }
    }

}