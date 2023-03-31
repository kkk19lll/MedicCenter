package com.example.mediccenter.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mediccenter.R;
import com.example.mediccenter.adapters.CatalogAdapter;
import com.example.mediccenter.databinding.ActivityMainBinding;
import com.example.mediccenter.fragments.FragmentAnalyse;
import com.example.mediccenter.fragments.ProfileFragment;
import com.example.mediccenter.fragments.ResultsFragment;
import com.example.mediccenter.fragments.SupportFragment;
import com.example.mediccenter.models.Catalog;

import java.util.ArrayDeque;
import java.util.Deque;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Deque<Integer> integerDeque = new ArrayDeque<>(3);
    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        integerDeque.push(R.id.menuAnalyse);

        loadFragment(new FragmentAnalyse());

        binding.bottomNavigationView.setSelectedItemId(R.id.menuAnalyse);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (integerDeque.contains(id)) {
                if (id == R.id.menuAnalyse) {
                    if (integerDeque.size() != 1) {
                        if (flag) {
                            integerDeque.addFirst(R.id.menuAnalyse);
                            flag = false;
                        }
                    }
                }
                integerDeque.remove(id);
            }
            integerDeque.push(id);
            loadFragment(getFragment(item.getItemId()));
            return true;
        });
    }

    @SuppressLint("NonConstantResourceId")
    private Fragment getFragment(int itemId) {
        switch (itemId) {
            case R.id.menuAnalyse:
                binding.bottomNavigationView.getMenu().getItem(0).setChecked(true);
                return new FragmentAnalyse();
            case R.id.menuResults:
                binding.bottomNavigationView.getMenu().getItem(1).setChecked(true);
                return new ResultsFragment();
            case R.id.menuSupport:
                binding.bottomNavigationView.getMenu().getItem(2).setChecked(true);
                return new SupportFragment();
            case R.id.menuProfile:
                binding.bottomNavigationView.getMenu().getItem(3).setChecked(true);
                return new ProfileFragment();
        }
        binding.bottomNavigationView.getMenu().getItem(0).setChecked(true);
        return new FragmentAnalyse();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutMainActivity, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        integerDeque.pop();

        if (!integerDeque.isEmpty()) {
            loadFragment(getFragment(integerDeque.peek()));
        }
        else {
            finish();
        }
    }
}