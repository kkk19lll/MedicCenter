package com.example.mediccenter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mediccenter.R;
import com.example.mediccenter.models.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolderNews> {

    Context context;
    List<News> newsList = new ArrayList<>();

    public NewsAdapter(Context context) {
        this.context = context;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderNews(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_new_analyse_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderNews holder, int position) {
        holder.bind(newsList.get(position));
        if (position % 2 == 0) {
            holder.itemViewNews.setBackgroundResource(R.drawable.shape_banner_1);
            holder.itemViewRL.setBackgroundResource(R.drawable.shape_banner_1);
        } else {
            holder.itemViewNews.setBackgroundResource(R.drawable.shape_banner_2);
            holder.itemViewRL.setBackgroundResource(R.drawable.shape_banner_2);
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolderNews extends RecyclerView.ViewHolder {

        TextView titleTextViewNews, subTitleTextViewNews, costTextView;
        CardView itemViewNews;
        ImageView imageView;

        RelativeLayout itemViewRL;

        public ViewHolderNews(@NonNull View itemView) {
            super(itemView);
            titleTextViewNews = itemView.findViewById(R.id.titleTextViewNews);
            subTitleTextViewNews = itemView.findViewById(R.id.subTitleTextView);
            costTextView = itemView.findViewById(R.id.costTextView);
            itemViewNews = itemView.findViewById(R.id.itemView);
            imageView = itemView.findViewById(R.id.imageViewNews);
            itemViewRL = itemView.findViewById(R.id.itemViewRL);
        }
        public void bind(News news) {
            titleTextViewNews.setText(news.getName());
            subTitleTextViewNews.setText(news.getDescription());
            costTextView.setText(news.getPrice());
            Glide.with(context).load(news.getImage()).into(imageView);
        }
    }
}
