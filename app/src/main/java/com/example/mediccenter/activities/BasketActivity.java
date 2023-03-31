package com.example.mediccenter.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.mediccenter.R;
import com.example.mediccenter.adapters.BasketAdapter;
import com.example.mediccenter.databinding.ActivityBasketBinding;
import com.example.mediccenter.models.ItemBasket;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    ActivityBasketBinding binding;

    BasketAdapter basketAdapter;

    List<ItemBasket> itemBaskets = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBasketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackBasket.setOnClickListener(v -> onBackPressed());

        binding.btnNextToOrder.setEnabled(false);
        binding.btnNextToOrder.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);

        binding.recyclerViewBasket.setLayoutManager(new LinearLayoutManager(this));

        basketAdapter = new BasketAdapter(this, itemBaskets);

        binding.recyclerViewBasket.setAdapter(basketAdapter);

        itemBaskets.add(new ItemBasket("Клинический анализ крови с лейкоцинтарной формулировкой", "690 Р", "1 пациент"));
        itemBaskets.add(new ItemBasket("ПЦР-тест на определение РНК коронавируса стандартный", "1800 Р", "1 пациент"));

        binding.btnDeleteBasket.setOnClickListener(v ->
                itemBaskets.remove(itemBaskets));
        binding.setTextViewSum.setText("2490 Р");

        if (itemBaskets == null) {
            binding.btnNextToOrder.setEnabled(false);
            binding.btnNextToOrder.setBackgroundResource(R.drawable.shape_btn_next_auth_disable);
        }
        else {
            binding.btnNextToOrder.setEnabled(true);
            binding.btnNextToOrder.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
        }

        binding.btnNextToOrder.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), OrderActivity.class)));
    }
}