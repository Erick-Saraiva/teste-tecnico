package com.example.questao1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.questao1.Apis.Api;
import com.example.questao1.Apis.ApiItems;
import com.example.questao1.domain.Item;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAutenticacao = findViewById(R.id.txt_result);
    }

    public void createItem(View view) throws IOException {
        ApiItems apiItems = Api.getApiItems();

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
        ApiItems apiItems = Api.getApiItems();


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
        ApiItems apiItems = Api.getApiItems();

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
        ApiItems apiItems = Api.getApiItems();


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
        ApiItems apiItems = Api.getApiItems();

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

}