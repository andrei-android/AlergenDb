package com.enea.allergendatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.enea.allergendatabase.Database.Allergen;
import com.enea.allergendatabase.Database.AllergenRepository;

import java.util.ArrayList;
import java.util.List;

public class AllergenList extends AppCompatActivity implements AllergenAdapter.OnAllergenClickListener {

    RecyclerView recyclerView;
    AllergenAdapter adapter;

    private List<Allergen> allergens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergen_list);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        adapter = new AllergenAdapter(allergens,  this);
        recyclerView.setAdapter(adapter);

        AllergenRepository allergenRepository = new AllergenRepository(getApplication());
        allergenRepository.getAllAllergens().observe(this, new Observer<List<Allergen>>() {
            @Override
            public void onChanged(List<Allergen> allergens) {
                adapter.setAllergens(allergens);
            }
        });
    }

    @Override
    public void onAllergenClick(int position){
        Allergen allergenData = adapter.getAllergenAtPosition(position);

        Intent i = new Intent(AllergenList.this, AllergenDetails.class);
        i.putExtra("image", allergenData.getImage_url());
        i.putExtra("name",allergenData.getAllergen_name());
        i.putExtra("info",allergenData.getAllergen_information());
        i.putExtra("occur",allergenData.getAllergen_occurrence());
        startActivity(i);


    }
}