package com.example.apiapp;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// RetrofitFClient class.
public class RetrofitClient {
    // Api URL
    private static final String BASE_URL = "https://restcountries.com/v2/";
    private static final String TAG = "RetrofitClient";
    private static Retrofit retrofit = null;

    // getClient() method.
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // Gson Converter
                    .build();
        }
        return retrofit;
    }
}
