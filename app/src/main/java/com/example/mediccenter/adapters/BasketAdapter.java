package com.example.mediccenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediccenter.R;
import com.example.mediccenter.models.ItemBasket;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder>{

    Context context;
    List<ItemBasket> itemBaskets;

    public BasketAdapter(Context context, List<ItemBasket> itemBaskets) {
        this.context = context;
        this.itemBaskets = itemBaskets;
    }

    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BasketViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basket_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        holder.bind(itemBaskets.get(position));
    }

    @Override
    public int getItemCount() {
        return itemBaskets.size();
    }

    public class BasketViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNameBasket;
        TextView textViewPriceBasket;
        TextView textViewCountPatient;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameBasket = itemView.findViewById(R.id.setTextViewName);
            textViewPriceBasket = itemView.findViewById(R.id.setTextViewPrice);
            textViewCountPatient = itemView.findViewById(R.id.setTextViewCountPatient);
        }

        public void bind(ItemBasket itemBasket) {
            textViewNameBasket.setText(itemBasket.getTextViewNameBasket());
            textViewPriceBasket.setText(itemBasket.getTextViewPriceBasket());
            textViewCountPatient.setText(itemBasket.getTextViewCountPatient());
        }
    }
}
