package com.example.evolverss.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.evolverss.R;
import com.example.evolverss.api.ESPNService;

import retrofit2.Converter;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ESPNService espnService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.espn_base_url))
                .build();
        espnService = retrofit.create(ESPNService.class);
    }
}
