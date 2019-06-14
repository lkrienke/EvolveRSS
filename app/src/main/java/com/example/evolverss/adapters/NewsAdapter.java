package com.example.evolverss.adapters;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NewsAdapterItem> adapterItems = new ArrayList<>();
    private final NewsAdapterListener listener;

    public interface NewsAdapterListener {
        void onNewsCellClicked(String link);
    }

    public NewsAdapter(NewsAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NewsAdapterItem.NEWS_ITEM:
                return NewsItemViewHolder.create(parent.getContext(), parent);
            case NewsAdapterItem.SECTION_HEADER:
                return SectionHeaderViewHolder.create(parent.getContext(), parent);
            default:
                throw new IllegalStateException("Incorrect view type: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsAdapterItem currentItem = adapterItems.get(position);

        switch (holder.getItemViewType()) {
            case NewsAdapterItem.NEWS_ITEM:
                ((NewsItemViewHolder) holder).onBind(currentItem, listener);
                break;
            case NewsAdapterItem.SECTION_HEADER:
                ((SectionHeaderViewHolder) holder).onBind(currentItem);
                break;
            default:
                throw new IllegalStateException("Incorrect view type: " + holder.getItemViewType());
        }
    }

    @Override
    public int getItemCount() {
        return adapterItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return adapterItems.get(position).getViewType();
    }

    public ArrayList<NewsAdapterItem> getItems() {
        return adapterItems;
    }

    public void setItems(ArrayList<NewsAdapterItem> adapterItems) {
        this.adapterItems = adapterItems;
    }
}
