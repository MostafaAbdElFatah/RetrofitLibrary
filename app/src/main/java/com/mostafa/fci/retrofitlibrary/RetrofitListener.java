package com.mostafa.fci.retrofitlibrary;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by FCI on 2018-06-25.
 */

public interface RetrofitListener {
    public  void onJSONCompleted(List<Flower> list);
    public  void onXMLCompleted(List<XMLFlower> list);
    public  void onPostCompleted(Flower flower);
    public  void onImageLoaded(Bitmap bitmap);
}
