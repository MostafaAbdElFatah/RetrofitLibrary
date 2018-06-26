package com.mostafa.fci.retrofitlibrary;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends ArrayAdapter<Flower> {


    Context context;
    List<Flower> flowerList;

    public FlowerAdapter(Context context , int resource , List<Flower> flowerList) {
        super(context, resource,flowerList);
        this.flowerList = flowerList;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.child_row,parent,false);
        Flower flower = flowerList.get(position);

        final ImageView imageView = view.findViewById(R.id.flowerImage);
        URLManager.loadingImage(URLs.mainURL + URLs.photosFlowersURL + flower.getPhoto()
                , new RetrofitListener() {

                    @Override
                    public void onJSONCompleted(List<Flower> list) {

                    }

                    @Override
                    public void onXMLCompleted(List<XMLFlower> list) {

                    }

                    @Override
                    public void onImageLoaded(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onPostCompleted(Flower flower) {

                    }
                });

        TextView textView = view.findViewById(R.id.flowerName);
        textView.setText(flower.getName());
        return view;
    }

}
