package com.example.getandpostusingretrofit.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceGenerator {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://shitab14.github.io";

    public static <S> S createService(final String header, Class<S> serviceClass){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClass);
    }
}
