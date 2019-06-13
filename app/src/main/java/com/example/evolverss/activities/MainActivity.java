package com.example.evolverss.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.evolverss.R;
import com.example.evolverss.adapters.NewsAdapter;
import com.example.evolverss.adapters.NewsAdapterItem;
import com.example.evolverss.api.ESPNService;
import com.example.evolverss.model.Channel;
import com.example.evolverss.model.Feed;
import com.example.evolverss.model.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    ESPNService espnService;
    ArrayList<NewsAdapterItem> newsAdapterItems = new ArrayList<>();

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private NewsAdapter newsAdapter;
    private Context application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.application = getApplicationContext();

//        Item testItem = new Item("Item title", "Description wow!", "www.image.jpg", "http/////linko");
//        ArrayList<Item> testChannelItems = new ArrayList<Item>();
//        testChannelItems.add(testItem);
//        Channel testChannel = new Channel("Derp title", testChannelItems);

//        newsAdapterItems = new ArrayList<NewsAdapterItem>();
//        newsAdapterItems.add(new NewsAdapterItem(testItem, NewsAdapterItem.NEWS_ITEM));
//        newsAdapterItems.add(new NewsAdapterItem(testChannel, NewsAdapterItem.SECTION_HEADER));

        populateAdapterItems();

        newsAdapter = new NewsAdapter();
        newsAdapter.setItems(newsAdapterItems);

        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(newsAdapter);
    }

    private void initAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.espn_base_url))
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        espnService = retrofit.create(ESPNService.class);
    }

    private void populateAdapterItems() {
        if (espnService == null) {
            initAPI();
        }

        List<String> categories = Arrays.asList("rpm", "mlb", "nhl", "nba", "nfl");

        for (String c : categories){
            callRSS(c);
        }
    }

    private void callRSS(String category) {
        Call<Feed> call = espnService.getNews(category);
        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                Feed feed = response.body();

                if (feed == null) {
                    Toast.makeText(application, "Invalid RSS url " + response.message(), Toast.LENGTH_LONG).show();
                } else {
                    Channel channel = feed.getChannel();
                    ArrayList<Item> items = channel.getItems();

                    newsAdapterItems.add(new NewsAdapterItem(channel, NewsAdapterItem.SECTION_HEADER));
                    for(Item i : items){
                        newsAdapterItems.add(new NewsAdapterItem(i, NewsAdapterItem.NEWS_ITEM));
                    }
                }
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Toast.makeText(application, "API failure occurred", Toast.LENGTH_LONG).show();
            }
        });
    }
}
