package com.example.evolverss.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.evolverss.R;
import com.example.evolverss.api.ESPNService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ESPNService espnService;

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.espn_base_url))
                .build();
        espnService = retrofit.create(ESPNService.class);
    }
}
