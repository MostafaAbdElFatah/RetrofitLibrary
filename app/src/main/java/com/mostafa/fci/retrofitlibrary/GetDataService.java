package com.mostafa.fci.retrofitlibrary;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GetDataService {

    /// GET Method Request without Parameters
    @GET("flowers.json")
    Call<List<Flower>> getData();


    /// GET Method Request without Parameters
    @GET("flowers.xml")
    Call<Products> getXMLData();

    /// GET Method Request with Parameters
    @GET("flowers.json")
    Call<List<Flower>> getDataWithParams(@Query("param1") String param1 ,@Query("param2") String param2 );

    /// POST Method Request with Parameters
    @POST("ENDPointURL")
    Call<Flower> getDataWithPOSTParams(@Body Flower flower1 ,@Body Flower flower2 );

    // loading image
    @GET
    Call<ResponseBody> loadImage(@Url String url);


}
