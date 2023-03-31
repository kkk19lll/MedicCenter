package com.example.mediccenter.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mediccenter.R;
import com.example.mediccenter.api.API;
import com.example.mediccenter.databinding.ActivityCreatePatientCardBinding;
import com.example.mediccenter.models.CreateCardPaitentModel;
import com.example.mediccenter.view_model.CreateCardPatientViewModel;

import java.util.Calendar;

public class CreatePatientCardActivity extends AppCompatActivity {

    ActivityCreatePatientCardBinding binding;

    CreateCardPatientViewModel viewModel;


    String current = "";
    String ddmmyyyy = "DDMMYYYY";
    Calendar cal = Calendar.getInstance();

    boolean TextField1, TextField2, TextField3, TextField4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePatientCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.skipTextView.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        installDateEditText();
        installEnableEditTexts();
        installViewModel();
        binding.buttonCreate.setOnClickListener(v -> {
            createCardPatient();
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("lastName", binding.lastNameEditText.getText().toString());
            intent.putExtra("firstName", binding.firstNameEditText.getText().toString());
            intent.putExtra("middleName", binding.middleNameEditText.getText().toString());
            intent.putExtra("dateBirth", binding.dateBirthEditText.getText().toString());
            intent.putExtra("gender", binding.genderEditText.getSelectedItem().toString());
            startActivity(intent);
            finish();
        });
    }

    private void installViewModel() {
        viewModel = new ViewModelProvider(this).get(CreateCardPatientViewModel.class);
    }

    private void createCardPatient() {
        viewModel.getCreateUserData().observe(this, user -> {
            if (user == null) {
                Toast.makeText(this, "Неудалось создать карту пациента...", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Карта пациента успешно создана!", Toast.LENGTH_SHORT).show();
            }
        });

        CreateCardPaitentModel createCardPaitentModel = new CreateCardPaitentModel("", binding.firstNameEditText.getText().toString(), binding.lastNameEditText.getText().toString(), binding.middleNameEditText.getText().toString(),
                binding.dateBirthEditText.getText().toString(), binding.genderEditText.getSelectedItem().toString(), "");
        viewModel.createUser(createCardPaitentModel);

    }

    private void installEnableEditTexts() {
        binding.lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    TextField1 = true;
                }
                else {
                    TextField1 = false;
                }
                if (TextField1 & TextField2 & TextField3 & TextField4){
                    binding.buttonCreate.setEnabled(true);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                }
                else {
                    binding.buttonCreate.setEnabled(false);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    TextField1 = true;
                }
                else {
                    TextField1 = false;
                }
                if (TextField1 & TextField2 & TextField3 & TextField4){
                    binding.buttonCreate.setEnabled(true);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                }
                else {
                    binding.buttonCreate.setEnabled(false);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
                }
            }
        });

        binding.firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    TextField2 = true;
                }
                else {
                    TextField2 = false;
                }
                if (TextField1 & TextField2 & TextField3 & TextField4){
                    binding.buttonCreate.setEnabled(true);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                }
                else {
                    binding.buttonCreate.setEnabled(false);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    TextField2 = true;
                }
                else {
                    TextField2 = false;
                }
            }
        });

        binding.middleNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    TextField3 = true;
                }
                else {
                    TextField3 = false;
                }
                if (TextField1 & TextField2 & TextField3 & TextField4){
                    binding.buttonCreate.setEnabled(true);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                }
                else {
                    binding.buttonCreate.setEnabled(false);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    TextField3 = true;
                }
                else {
                    TextField3 = false;
                }
            }
        });

        binding.dateBirthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0){
                    TextField4 = true;
                }
                else {
                    TextField4 = false;
                }
                if (TextField1 & TextField2 & TextField3 & TextField4){
                    binding.buttonCreate.setEnabled(true);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                }
                else {
                    binding.buttonCreate.setEnabled(false);
                    binding.buttonCreate.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    TextField4 = true;
                }
                else {
                    TextField4 = false;
                }
            }
        });
    }

    private void installDateEditText() {
        binding.dateBirthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (!charSequence.toString().equals(current)) {
                    String clean = charSequence.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : Math.min(mon, 12);
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900: Math.min(year, 2100);
                        cal.set(Calendar.YEAR, year);

                        day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = Math.max(sel, 0);
                    current = clean;
                    binding.dateBirthEditText.setText(current);
                    binding.dateBirthEditText.setSelection(Math.min(sel, current.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}