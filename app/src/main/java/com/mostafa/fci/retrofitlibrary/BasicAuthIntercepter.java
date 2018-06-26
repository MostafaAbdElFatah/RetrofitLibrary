package com.mostafa.fci.retrofitlibrary;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthIntercepter implements Interceptor {


    private String credentials;

    public BasicAuthIntercepter(String user , String pass){
        this.credentials = Credentials.basic(user,pass);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request authRequest = request.newBuilder()
                .header("Authorization",credentials).build();
        return chain.proceed(authRequest);
    }
}
