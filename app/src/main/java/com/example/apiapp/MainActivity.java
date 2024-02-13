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

// MainActivity Class.
public class MainActivity extends AppCompatActivity {

    // variable declaration
    private RecyclerView recyclerView;
    private CountryAdapter adapter;

    // onCreate Method.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // recycler view
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CountryAdapter();
        recyclerView.setAdapter(adapter);

        loadCountries(); // load country data from API.
    }
    private void loadCountries() { // fetching country data.
        CountryApi countryApi = RetrofitClient.getClient().create(CountryApi.class);
        // API Call
        Call<List<Country>> call = countryApi.getCountries("name,capital,flag");
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                // success
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setCountries(response.body());
                }
                // fail
                else {
                    Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }
            // handling API failure.
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
