package com.example.questao1.Apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static String BASE_URL = "https://crudcrud.com/api/7065ab369c2b475e928981b695dee25f/";

    public static ApiItems getApiItems() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit.create(ApiItems.class);
    }
}

