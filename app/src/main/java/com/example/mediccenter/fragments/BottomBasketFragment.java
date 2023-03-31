package com.example.mediccenter.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mediccenter.R;
import com.example.mediccenter.activities.BasketActivity;

public class BottomBasketFragment extends Fragment {

    RelativeLayout btnBasket;

    TextView setTextViewPriceBasket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_basket, container, false);
        setTextViewPriceBasket = view.findViewById(R.id.setTextViewPriceBasket);
        btnBasket.setOnClickListener(v ->
                startActivity(new Intent(getContext(), BasketActivity.class)));
        return view;
    }
}