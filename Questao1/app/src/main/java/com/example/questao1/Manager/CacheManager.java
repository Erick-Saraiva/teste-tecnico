package com.example.questao1.Manager;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheManager {
    private static final String CACHE_FILE_NAME = "cache";
    private static final String DATA_KEY = "data";

    public static void saveDataToCache(Context context, String data) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DATA_KEY, data);
        editor.apply();
    }

    public static String loadDataFromCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DATA_KEY, null);
    }
}
