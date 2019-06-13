package com.example.evolverss.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.evolverss.R;
import com.example.evolverss.model.Channel;
import com.example.evolverss.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.section_title)
    TextView title;

    private SectionHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static SectionHeaderViewHolder create(Context context, ViewGroup viewGroup) {
        return new SectionHeaderViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_section_header, viewGroup, false));
    }

    public void onBind(NewsAdapterItem newsAdapterItem) {
        Channel channel = (Channel) newsAdapterItem.getObject();
        title.setText(channel.getTitle());
    }

}
