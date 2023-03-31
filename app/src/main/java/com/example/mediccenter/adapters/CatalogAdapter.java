package com.example.mediccenter.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediccenter.R;
import com.example.mediccenter.models.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {
    List<Catalog> catalogList = new ArrayList<>();
    OnItemCLickListener cLickListener;
    OnEventClickListener onEventClickListener;

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public CatalogAdapter(OnItemCLickListener cLickListener, OnEventClickListener onEventClickListener) {
        this.cLickListener = cLickListener;
        this.onEventClickListener = onEventClickListener;
    }

    @NonNull
    @Override
    public CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CatalogViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_catalog_analyse, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogViewHolder holder, int position) {
        holder.bind(catalogList.get(position));
        holder.itemView.setOnClickListener(v -> cLickListener.onItemClick(catalogList.get(position)));
        holder.btnAddCatalog.setOnClickListener(v -> onEventClickListener.omEventClick(catalogList.get(position)));
        holder.btnAddCatalog.setOnClickListener(v -> {
                if (holder.btnAddCatalog.getText().toString().equals("Добавить")) {
                    onEventClickListener.omEventClick(catalogList.get(position));
                    holder.btnAddCatalog.setBackgroundResource(R.drawable.shape_enable_btn_basket);
                    holder.btnAddCatalog.setTextColor(Color.rgb(26,111,238));
                    holder.btnAddCatalog.setText("Убрать");
                }
                else if (holder.btnAddCatalog.getText().toString().equals("Убрать")) {
                    onEventClickListener.omEventClick(catalogList.get(position));
                    holder.btnAddCatalog.setBackgroundResource(R.drawable.shape_btn_next_auth_enable);
                    holder.btnAddCatalog.setTextColor(Color.rgb(255,255,255));
                    holder.btnAddCatalog.setText("Добавить");
                }
        });
    }

    @Override
    public int getItemCount() {
        return catalogList.size();
    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextViewCatalog, dayTextViewCatalog, costTextViewCatalog;
        Button btnAddCatalog;
        CardView itemViewCatalog;

        public CatalogViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextViewCatalog = itemView.findViewById(R.id.titleTextViewCatalogAnalyse);
            dayTextViewCatalog = itemView.findViewById(R.id.countDayTextView);
            costTextViewCatalog = itemView.findViewById(R.id.costTextView);
            btnAddCatalog = itemView.findViewById(R.id.btnAddAnalyse);
            itemViewCatalog = itemView.findViewById(R.id.itemViewCatalog);
        }

        public void bind(Catalog catalog) {
            titleTextViewCatalog.setText(catalog.getName());
            dayTextViewCatalog.setText(catalog.getTime_result());
            costTextViewCatalog.setText(catalog.getPrice());
        }
    }

    public interface OnItemCLickListener {
        void onItemClick(Catalog catalog);
    }

    public interface OnEventClickListener {
        void omEventClick(Catalog catalog);
    }
}
