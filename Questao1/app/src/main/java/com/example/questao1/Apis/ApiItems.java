package com.example.questao1.Apis;

import android.content.Context;

import com.example.questao1.domain.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiItems {

    @POST("items")
    Call<Item> post(@Body Item item);

    @PUT("items/{id}")
    Call<Item> put(@Path("id") String itemId, @Body Item item);

    @DELETE("items/{id}")
    Call<Void> delete(@Path("id") String id);

    @GET("items")
    Call<List<Item>> getAll();

    @GET("items/{id}")
    Call<Item> getOne(@Path("id") String id);

}

