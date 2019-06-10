package com.example.evolverss.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.evolverss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cell_title)
    String title;
    @BindView(R.id.cell_description)
    String description;
    @BindView(R.id.cell_image)
    ImageView image;

    private NewsItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static NewsItemViewHolder create(Context context, ViewGroup viewGroup) {
        return new NewsItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_news_cell, viewGroup, false));
    }

    public void onBind(NewsAdapterItem item) {

    }
}
