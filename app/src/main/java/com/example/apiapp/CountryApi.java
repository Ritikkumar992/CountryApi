package com.example.apiapp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface CountryApi {
    // Get request
    @GET("all")
    Call<List<Country>> getCountries(
            @Query("fields") String fields
    );
}
