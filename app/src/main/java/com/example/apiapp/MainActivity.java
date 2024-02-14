package com.example.apiapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    // variable declaration
    private RecyclerView recyclerView;
    private CountryAdapter adapter;

    // onCreate Method.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recycler view called
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryAdapter();
        recyclerView.setAdapter(adapter);
        
        // loadCountry() method called.
        loadCountries(); 
    }
    // loadCountry() method declared.
    private void loadCountries() {
        CountryApi countryApi = RetrofitClient.getClient().create(CountryApi.class);
        // API Call
        Call<List<Country>> call = countryApi.getCountries("name,capital,flag");
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                // successfull response
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setCountries(response.body());
                }
                // failed response.
                else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }
            // If API is failed => handle the below lines.
            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("API Error", "Error fetching data", t);
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void loadCountries() {
//        CountryApi countryApi = RetrofitClient.getClient().create(CountryApi.class);
//        Call<List<Country>> call = countryApi.getCountries();
//
//        call.enqueue(new Callback<List<Country>>() {
//            @Override
//            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    adapter.setCountries(response.body());
//                } else {
//                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Country>> call, Throwable t) {
//                Log.e("API Error", "Error fetching data", t);
//                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
