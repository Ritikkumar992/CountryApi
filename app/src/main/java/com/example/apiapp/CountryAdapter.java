package com.example.apiapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

// CountryAdapter class.
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private List<Country> countries = new ArrayList<>();

    // list of countries.
    public void setCountries(List<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }
    // country data binded.
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.countryName.setText(country.getName());
        holder.capital.setText(country.getCapital());
        Picasso.get().load(country.getFlag()).into(holder.flag);
    }

    @Override // no of items in data set.
    public int getItemCount() {
        return countries.size();
    }
    // ViewHolder Class.
    static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryName; // country Name
        TextView capital; // Capital Name
        ImageView flag; // flag Image.

        // ViewHolder constructor.
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
            capital = itemView.findViewById(R.id.capital);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
