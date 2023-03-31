package com.example.mediccenter.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediccenter.R;
import com.example.mediccenter.activities.BasketActivity;
import com.example.mediccenter.adapters.CatalogAdapter;
import com.example.mediccenter.adapters.NewsAdapter;
import com.example.mediccenter.models.Catalog;
import com.example.mediccenter.view_model.CatalogViewModel;
import com.example.mediccenter.view_model.NewsViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class FragmentAnalyse extends Fragment implements CatalogAdapter.OnItemCLickListener, CatalogAdapter.OnEventClickListener {

    RecyclerView recyclerViewCatalog, recyclerViewNews;

    CatalogAdapter catalogAdapter;

    NewsAdapter newsAdapter;

    CatalogViewModel catalogViewModel;

    NewsViewModel newsViewModel;

    TextView titleTextViewBottomDialogAnalyse, setTextDescriptionView, setTextPreparationView, setTextViewDay, setTextBioMaterial, setTextViewPriceBasket;

    Button btnAddToBasket;

    BottomAppBar bottomAppBar;

    ImageButton imageBtnClose;

    EditText searchViewEdiText;

    RelativeLayout btnEventBasket;

    List<Catalog> catalogList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analyse, container, false);
        recyclerViewCatalog = view.findViewById(R.id.recyclerViewCatalog);
        recyclerViewNews = view.findViewById(R.id.recyclerViewStocksAndNews);
        searchViewEdiText = view.findViewById(R.id.searchViewEdiText);
        bottomAppBar = view.findViewById(R.id.bottomAppBar);
        btnEventBasket = view.findViewById(R.id.btnEventBasket);
        setTextViewPriceBasket = view.findViewById(R.id.setTextViewPriceBasket);


        bottomAppBar.setVisibility(View.GONE);

        recyclerViewCatalog.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        newsAdapter = new NewsAdapter(getContext());
        catalogAdapter = new CatalogAdapter(this, this);

        recyclerViewNews.setAdapter(newsAdapter);
        recyclerViewCatalog.setAdapter(catalogAdapter);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        catalogViewModel = new ViewModelProvider(this).get(CatalogViewModel.class);

        getNews();

        getCatalog();

        searchCatalog();

        btnEventBasket.setOnClickListener(v ->
                startActivity(new Intent(getContext(), BasketActivity.class)));

        return view;
    }

    private void searchCatalog() {

        searchViewEdiText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    catalogViewModel.getCatalog();
                }
                else {
                    catalogViewModel.searchUser(searchViewEdiText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getNews() {
        newsViewModel.getNewsData().observe((LifecycleOwner) getContext(), news -> {
            if (news == null) {
                Toast.makeText(getContext(), "Акций и новостей не найдено", Toast.LENGTH_SHORT).show();
            }
            else {
                newsAdapter.setNewsList(news);
                newsAdapter.notifyDataSetChanged();
            }
        });
        newsViewModel.getNews();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getCatalog() {
        catalogViewModel.getCatalogData().observe((LifecycleOwner) getContext(), catalogs -> {
            if (catalogs == null) {
                Toast.makeText(getContext(), "Каталог анализов не найдено", Toast.LENGTH_SHORT).show();
            }
            else {
                catalogAdapter.setCatalogList(catalogs);
                catalogAdapter.notifyDataSetChanged();
            }
        });
        catalogViewModel.getCatalog();
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onItemClick(Catalog catalog) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.MyBottomDialog);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_analyse);
        imageBtnClose = bottomSheetDialog.findViewById(R.id.imageBtnClose);
        titleTextViewBottomDialogAnalyse = bottomSheetDialog.findViewById(R.id.titleTextViewBottomDialogAnalyse);
        setTextDescriptionView = bottomSheetDialog.findViewById(R.id.setTextDescriptionView);
        setTextPreparationView = bottomSheetDialog.findViewById(R.id.setTextPreparationView);
        setTextViewDay = bottomSheetDialog.findViewById(R.id.setTextViewDay);
        setTextBioMaterial = bottomSheetDialog.findViewById(R.id.setTextBioMaterial);
        btnAddToBasket = bottomSheetDialog.findViewById(R.id.btnAddToBasket);
        catalogViewModel.getCatalogData().observe((LifecycleOwner) getContext(), catalogs -> {
            if (catalogs == null) {
                Toast.makeText(getContext(), "Каталог анализов не найдено", Toast.LENGTH_SHORT).show();
            }
            else {
                catalogAdapter.setCatalogList(catalogs);
                catalogAdapter.notifyDataSetChanged();
            }
        });
        catalogViewModel.getCatalog();
        imageBtnClose.setOnClickListener(v -> bottomSheetDialog.cancel());
        titleTextViewBottomDialogAnalyse.setText(catalog.getName());
        setTextDescriptionView.setText(catalog.getDescription());
        setTextPreparationView.setText(catalog.getPreparation());
        setTextViewDay.setText(catalog.getTime_result());
        setTextBioMaterial.setText(catalog.getBio());
        btnAddToBasket.setText(catalog.getPrice() + " Р");
        btnAddToBasket.setOnClickListener(v -> {
            bottomAppBar.setVisibility(View.VISIBLE);
            for (int i = 0; i <= catalogList.size(); i++) {
                setTextViewPriceBasket.setText(catalog.getPrice() + " Р");
            }
        });
        bottomSheetDialog.show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void omEventClick(Catalog catalog) {
        bottomAppBar.setVisibility(View.VISIBLE);
        for (int i = 0; i <= catalogList.size(); i++) {
            setTextViewPriceBasket.setText(catalog.getPrice() + " Р");
        }
    }
}