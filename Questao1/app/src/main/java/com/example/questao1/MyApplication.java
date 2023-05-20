package com.example.questao1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.questao1.Apis.ApiItems;
import com.example.questao1.domain.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends AppCompatActivity {
    private TextView tvAutenticacao;

    private static String BASE_URL = "https://crudcrud.com/api/7065ab369c2b475e928981b695dee25f/";

    public ApiItems getApiItems() {
        File cacheDir = new File(getCacheDir(), "api-cache");
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(cacheDir, cacheSize);


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(httpClient)
                .build();

        return retrofit.create(ApiItems.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAutenticacao = findViewById(R.id.txt_result);
    }

    public void createItem(View view) throws IOException {
        ApiItems apiItems = getApiItems();

        UUID randomId = UUID.randomUUID();
        String id = randomId.toString();

        EditText editTextName = findViewById(R.id.et_name);
        String name = editTextName.getText().toString();

        EditText editTextPrice = findViewById(R.id.et_price);
        String price = editTextPrice.getText().toString();

        Item item = new Item(id, name, price);

        Call<Item> chamadaPOST = apiItems.post(item);
        chamadaPOST.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                System.out.println(call.request());
                if (response.isSuccessful()) {
                    tvAutenticacao.setText("Created Item!");
                    String fileName = "data.txt";
                    saveFileInternally(fileName, response.body().toString());
                } else {
                    tvAutenticacao.setText("Error");
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error on API: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void editItem(View view) throws IOException {
        ApiItems apiItems = getApiItems();


        EditText editTextId = findViewById(R.id.et_id_put);
        String itemId = editTextId.getText().toString();

        EditText editTextnewName = findViewById(R.id.et_new_name_put);
        String newName = editTextnewName.getText().toString();

        EditText editTextPrice = findViewById(R.id.et_new_price_put);
        String newPrice = editTextPrice.getText().toString();

        Item item = new Item(itemId, newName, newPrice);

        Call<Item> chamadaPUT = apiItems.put(itemId, item);
        chamadaPUT.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                System.out.println(call.request());
                if (response.isSuccessful()) {
                    tvAutenticacao.setText("Updated Item!");
                } else {
                    tvAutenticacao.setText("Error");
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error on API: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void deleteItem(View view) throws IOException {
        ApiItems apiItems = getApiItems();

        EditText editTextName = findViewById(R.id.et_search_id);
        String id = editTextName.getText().toString();

        Call<Void> chamadaDELETE = apiItems.delete(id);
        chamadaDELETE.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(call.request());
                tvAutenticacao.setText("Deleted Item!");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error on API: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void getAll(View view) throws IOException {
        ApiItems apiItems = getApiItems();

        Call<List<Item>> chamadaGetALL = apiItems.getAll();
        chamadaGetALL.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                System.out.println(call.request());
                if (response.isSuccessful()) {
                    tvAutenticacao.setText(response.body().toString());
                } else {
                    tvAutenticacao.setText("Error");
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error on API: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    public void getOne(View view) throws IOException {
        ApiItems apiItems = getApiItems();

        EditText editTextName = findViewById(R.id.et_search_id);
        String id = editTextName.getText().toString();

        Call<Item> chamadaGetONE = apiItems.getOne(id);
        chamadaGetONE.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                System.out.println(call.request());
                if (response.isSuccessful()) {
                    tvAutenticacao.setText(response.body().toString());
                } else {
                    tvAutenticacao.setText("Error");
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error on API: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void saveFileInternally(String filename, String content) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}