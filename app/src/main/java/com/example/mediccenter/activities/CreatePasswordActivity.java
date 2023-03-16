package com.example.mediccenter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.health.TimerStat;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.mediccenter.R;
import com.example.mediccenter.databinding.ActivityCreatePasswordBinding;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CreatePasswordActivity extends AppCompatActivity {

    ActivityCreatePasswordBinding binding;

    ArrayList<Integer> pinCode;

    public int pinCodeLength = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pinCode = new ArrayList<>();


        binding.textViewSkip.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.btnOneNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnOneNumber, 1);
        });

        binding.btnTwoNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnTwoNumber, 2);
        });

        binding.btnThreeNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnThreeNumber, 3);
        });

        binding.btnFourNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnFourNumber, 4);
        });

        binding.btnFiveNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnFiveNumber, 5);
        });

        binding.btnSixNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnSixNumber, 6);
        });

        binding.btnSevenNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnSevenNumber, 7);
        });

        binding.btnEightNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnEightNumber, 8);
        });

        binding.btnNineNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnNineNumber, 9);
        });

        binding.btnZeroNumber.setOnClickListener(v -> {
            onNumberButtonClick(binding.btnZeroNumber, 0);
        });

        binding.deleteNumberCretePassword.setOnClickListener(v -> {
            clearCirclePinCode();
            pinCodeDrawing();
            System.out.println(pinCode);
        });
    }

    private void onNumberButtonClick(Button buttonName, int buttonValue) {
        buttonName.setBackgroundResource(R.drawable.shape_enable_number_btn);
        buttonName.setTextColor(Color.rgb(255,255,255));
        if (pinCode.size() < 4) {
            pinCode.add(buttonValue);
            pinCodeLength++;
            System.out.println(pinCode);
        }
        if (pinCode.size() == 4) {
            startActivity(new Intent(this, CreatePatientCardActivity.class));
            finish();
        }
        pinCodeDrawing();
    }

    private void clearCirclePinCode() {
        if (pinCode.size() > 0 && pinCode.size() <= 4) {
            pinCodeLength--;
            pinCode.remove(pinCodeLength);
        }
    }

    private void pinCodeDrawing() {
        switch (pinCode.size()){
            case 0:
                binding.oneCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                binding.twoCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                binding.threeCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                binding.fourCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                break;
            case 1:
                binding.oneCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.twoCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                binding.threeCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                binding.fourCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                break;
            case 2:
                binding.oneCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.twoCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.threeCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                binding.fourCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                break;
            case 3:
                binding.oneCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.twoCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.threeCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.fourCirclePinCode.setBackgroundResource(R.drawable.shape_disable_circle_pin_code);
                break;
            case 4:
                binding.oneCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.twoCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.threeCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                binding.fourCirclePinCode.setBackgroundResource(R.drawable.shape_enable_circle_pin_code);
                break;
        }
    }
}