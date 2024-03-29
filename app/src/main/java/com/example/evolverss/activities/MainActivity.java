package com.example.evolverss.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.evolverss.R;
import com.example.evolverss.adapters.NewsAdapter;
import com.example.evolverss.adapters.NewsAdapterItem;
import com.example.evolverss.api.ESPNService;
import com.example.evolverss.model.Channel;
import com.example.evolverss.model.Feed;
import com.example.evolverss.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    ESPNService espnService;
    ArrayList<NewsAdapterItem> newsAdapterItems = new ArrayList<>();

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.wv_link)
    WebView wvLink;

    private NewsAdapter newsAdapter;
    private Context application;

    private final NewsAdapter.NewsAdapterListener listener = new NewsAdapter.NewsAdapterListener() {
        @Override
        public void onNewsCellClicked(String link) {
            if(link == null) {
                Toast.makeText(application, "No link provided", Toast.LENGTH_LONG).show();
            } else {
                wvLink.loadUrl(link);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.application = getApplicationContext();


        newsAdapter = new NewsAdapter(listener);
        newsAdapter.setItems(newsAdapterItems);

        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(newsAdapter);

        populateAdapterItems();
    }

    private void initAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.espn_base_url))
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        espnService = retrofit.create(ESPNService.class);
    }

    private void populateAdapterItems() {
        if (espnService == null) {
            initAPI();
        }

        List<String> categories = Arrays.asList("rpm", "mlb", "nhl", "nba", "nfl");

        for (String c : categories) {
            callRSS(c);
        }
    }

    private void callRSS(String category) {
        espnService.getNews(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Feed>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Feed feed) {
                        if (feed == null) {
                            Toast.makeText(application, "Invalid RSS url", Toast.LENGTH_LONG).show();
                        } else {
                            Channel channel = feed.getChannel();
                            ArrayList<Item> items = channel.getItems();

                            newsAdapterItems.add(new NewsAdapterItem(channel, NewsAdapterItem.SECTION_HEADER));
                            for (Item i : items) {
                                newsAdapterItems.add(new NewsAdapterItem(i, NewsAdapterItem.NEWS_ITEM));
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(application, "API failure occurred", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        rvNews.getAdapter().notifyDataSetChanged();
                    }
                });
    }
}
