package com.enea.allergendatabase;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enea.allergendatabase.Database.Allergen;
import com.enea.allergendatabase.Database.AllergenDatabase;
import com.enea.allergendatabase.Database.AllergenRepository;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AllergenAdapter extends RecyclerView.Adapter<AllergenAdapter.MyViewHolder> {

    Context context;
    Application application;

    private Allergen allergen;
    private AllergenDatabase vehicleDatabase;
    private AllergenRepository vehRepository;
    private List<Allergen> allergens = new ArrayList<>();
    private OnAllergenClickListener onAllergenClickListener;


    public AllergenAdapter(List<Allergen>allergens, OnAllergenClickListener onAllergenClickListener){
        this.allergens = allergens;
        this.onAllergenClickListener = onAllergenClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new MyViewHolder(itemView, onAllergenClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        if (allergens != null){
            Allergen allergenData = allergens.get(position);
            holder.setName(allergenData.getAllergen_name(), position);
            holder.setImage(allergenData.getImage_url(), position);


        }
    }
    @Override
    public int getItemCount() {
        return allergens.size();
    }
    public void setAllergens(List<Allergen>allergenData){
        allergens = allergenData;
        notifyDataSetChanged();
    }

    public Allergen getAllergenAtPosition(int position){
        return allergens.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name;
        int position;
        private int namePosition;
        private int imagePosition;
        OnAllergenClickListener onAllergenClickListener;
        public MyViewHolder(@NonNull View itemView, OnAllergenClickListener onAllergenClickListener) {
            super(itemView);

            name = itemView.findViewById(R.id.name_tv);
            image = itemView.findViewById(R.id.image_iv);
            this.onAllergenClickListener = onAllergenClickListener;
            itemView.setOnClickListener(this);

        }
        public void setName(String name, int position){
            this.name.setText(name);
            namePosition = position;

        }
        public void setImage(String image, int position){
//            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(String.valueOf(image)).into(this.image);
            imagePosition = position;
        }


        @Override
        public void onClick(View v) {

            onAllergenClickListener.onAllergenClick(getAdapterPosition());
        }
    }

   public interface OnAllergenClickListener{
        void onAllergenClick(int position);
   }








}

