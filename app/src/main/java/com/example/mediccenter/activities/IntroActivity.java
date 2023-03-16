package com.example.mediccenter.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.mediccenter.R;
import com.example.mediccenter.adapters.IntroViewPagerAdapter;
import com.example.mediccenter.databinding.ActivityIntroBinding;
import com.example.mediccenter.models.ScreenItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    ActivityIntroBinding binding;

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    int position = 0;
    TextView tvSkip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(),AuthActivity.class );
            startActivity(mainActivity);
            finish();
        }
        setContentView(binding.getRoot());
        tabIndicator = findViewById(R.id.tab_indicator);
        tvSkip = findViewById(R.id.tv_skip);
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Анализы","Экспресс сбор и получение проб",R.drawable.intro_1));
        mList.add(new ScreenItem("Уведомления","Вы быстро узнаете о результатах",R.drawable.intro_2));
        mList.add(new ScreenItem("Мониторинг","Наши врачи всегда наблюдают\n за вашими показателями здоровья",R.drawable.intro_3));
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1) {
                    loadLastScreen();
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        tvSkip.setOnClickListener(v -> screenPager.setCurrentItem(mList.size()));
    }
    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend",false);
        return  isIntroActivityOpnendBefore;
    }
    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend",true);
        editor.commit();
    }
    private void loadLastScreen() {
        tvSkip.setVisibility(View.VISIBLE);
        tvSkip.setText("Завершить");
        tvSkip.setOnClickListener(v -> {
            Intent mainActivity = new Intent(getApplicationContext(),AuthActivity.class );
            startActivity(mainActivity);
            savePrefsData();
            finish();
        });
        tabIndicator.setVisibility(View.VISIBLE);
    }
}