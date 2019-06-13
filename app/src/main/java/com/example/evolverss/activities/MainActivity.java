package com.example.evolverss.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.evolverss.R;
import com.example.evolverss.adapters.NewsAdapter;
import com.example.evolverss.adapters.NewsAdapterItem;
import com.example.evolverss.api.ESPNService;
import com.example.evolverss.model.Channel;
import com.example.evolverss.model.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ESPNService espnService;

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Item testItem = new Item("Item title", "Description wow!", "www.image.jpg", "http/////linko");
        ArrayList<Item> testChannelItems = new ArrayList<Item>();
        testChannelItems.add(testItem);
        Channel testChannel = new Channel("Derp title", testChannelItems);

        ArrayList<NewsAdapterItem> newsAdapterItems = new ArrayList<NewsAdapterItem>();
        newsAdapterItems.add(new NewsAdapterItem(testItem, NewsAdapterItem.NEWS_ITEM));
        newsAdapterItems.add(new NewsAdapterItem(testChannel, NewsAdapterItem.SECTION_HEADER));

        newsAdapter = new NewsAdapter();
        newsAdapter.setItems(newsAdapterItems);

        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(newsAdapter);
        rvNews.getAdapter().notifyDataSetChanged();
    }

    private void initAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.espn_base_url))
                .build();
        espnService = retrofit.create(ESPNService.class);
    }
}
