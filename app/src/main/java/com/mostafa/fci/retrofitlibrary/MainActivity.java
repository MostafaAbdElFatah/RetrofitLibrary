package com.mostafa.fci.retrofitlibrary;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Flower> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.flowersList);

        //URLManager.getXmlData(null);
        URLManager.getAuthJSONDataByRetrofit("feeduser","feedpassword",new RetrofitListener(){

            @Override
            public void onJSONCompleted(List<Flower> list) {
                arrayList = list;
                if (arrayList != null) {
                    FlowerAdapter flowerAdapter = new FlowerAdapter(MainActivity.this
                            , R.layout.child_row, arrayList);
                    listView.setAdapter(flowerAdapter);
                }
            }

            @Override
            public void onXMLCompleted(List<XMLFlower> list) {

            }

            @Override
            public void onPostCompleted(Flower flower) {

            }

            @Override
            public void onImageLoaded(Bitmap bitmap) {

            }
        });


    }

}
