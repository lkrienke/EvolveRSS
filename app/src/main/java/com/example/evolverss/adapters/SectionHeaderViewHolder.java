package com.example.evolverss.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.evolverss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.section_title)
    String title;

    private SectionHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static SectionHeaderViewHolder create(Context context, ViewGroup viewGroup) {
        return new SectionHeaderViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_section_header, viewGroup, false));
    }

    public void onBind(NewsAdapterItem item) {

    }

}
