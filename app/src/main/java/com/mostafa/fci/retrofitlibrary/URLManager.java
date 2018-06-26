package com.mostafa.fci.retrofitlibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ListView;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by FCI on 2018-06-25.
 */

public class URLManager {

    /**
     * get data without parameters
     * */
    public static void getJSONDataByRetrofit(final RetrofitListener rl){

        GetDataService getDataService;
        final Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URLs.mainURL + URLs.feedURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getDataService = retrofit.create(GetDataService.class);
        Call<List<Flower>> call = getDataService.getData();
        call.enqueue(new Callback<List<Flower>>() {

            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {

                if (rl != null) {
                    rl.onJSONCompleted(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }

    /**
     * get xml file without parameters
     * */
    public static void getXmlData(final RetrofitListener rl){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URLs.mainURL + URLs.feedURL)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy())
                ))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        GetDataService dataService =  retrofit.create(GetDataService.class);
        Call<Products> call = dataService.getXMLData();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                for (int i =0 ; i < 10 ; i++)
                    System.out.println("********************************************************");
                System.out.println(response.body().flowers.toString());
                List<XMLFlower> xmlFlowers = response.body().flowers;
                XMLFlower xmlFlower = xmlFlowers.get(0);
                if (rl != null) {
                    rl.onXMLCompleted(xmlFlowers);
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                for (int i =0 ; i < 10 ; i++)
                    System.out.println("********************************************************");
                System.out.println(t.fillInStackTrace());

            }
        });

    }

    /**
     * authenication
     * */
    public static void getAuthJSONDataByRetrofit(String user,String pass,final RetrofitListener rl) {
        OkHttpClient  okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthIntercepter(user, pass))
                .build();
        final Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URLs.mainURL + URLs.secureURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataService getDataService = retrofit.create(GetDataService.class);
        Call<List<Flower>> call = getDataService.getData();
        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (rl != null) {
                    rl.onJSONCompleted(response.body());
                }
            }

            @Override




            public void onFailure(Call<List<Flower>> call, Throwable t) {
                for (int i =0 ; i < 10 ; i++)
                    System.out.println("********************************************************");
                System.out.println(t.fillInStackTrace());
            }
        });


    }

        /**
         * GET Method with parameters
         * */

    public static void getJSONDataByRetrofit(String param1,String param2 , final RetrofitListener retrofitListener) {

        GetDataService getDataService;
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.mainURL + URLs.feedURL)
                //.addConverterFactory(GsonConverterFactory.create()).build(); // Gson ConverterFactory
                .addConverterFactory(MoshiConverterFactory.create()).build();  // Moshi ConverterFactory
        getDataService = retrofit.create(GetDataService.class);
        Call<List<Flower>> call = getDataService.getDataWithParams(param1,param2);

        // synchronous call
        /*
        try {
            if (retrofitListener != null) {
                retrofitListener.onCompleted(call.execute().body());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Asynchronous call
        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (retrofitListener != null) {
                    retrofitListener.onJSONCompleted(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }

    /**
     * POST Method with parameters
     * */

    public static void getDataWithPOSTParams(String param1,String param2 , final RetrofitListener rl) {

        Flower flower = new Flower(1,"asd");
        GetDataService getDataService;
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.mainURL + URLs.feedURL )
                .addConverterFactory(GsonConverterFactory.create()).build();
        getDataService = retrofit.create(GetDataService.class);
        Call<Flower> call = getDataService.getDataWithPOSTParams(flower , flower);
        call.enqueue(new Callback<Flower>() {
            @Override
            public void onResponse(Call<Flower> call, Response<Flower> response) {
                if (rl != null) {
                    rl.onPostCompleted(response.body());

                }
            }

            @Override
            public void onFailure(Call<Flower> call, Throwable t) {

            }
        });
    }


    /**
     * retrieve  image
     * */
    public static void loadingImage(String imageURL, final RetrofitListener listener){

        GetDataService getDataService;
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.mainURL + URLs.feedURL )
                .addConverterFactory(GsonConverterFactory.create()).build();
        getDataService = retrofit.create(GetDataService.class);
        Call<ResponseBody> call = getDataService.loadImage(imageURL);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        // display the image data in a ImageView or save it
                        Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                        if (listener != null) {
                            listener.onImageLoaded(bm);
                        }


                    } else {
                        // TODO
                    }
                } else {
                    // TODO
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // TODO
            }
        });

    }


}
