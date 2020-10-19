package com.enea.allergendatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.enea.allergendatabase.Database.Allergen;
import com.enea.allergendatabase.Database.AllergenDatabase;
import com.enea.allergendatabase.Database.AllergenRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button allergens_btn,cross_react_btn;

    public String allerg_ID, allerg_name, allerg_info, allerg_occur, allerg_url;
    private Allergen allergen;
    private AllergenDatabase vehicleDatabase;
    private AllergenRepository vehRepository;
    private List<Allergen> allergens = new ArrayList<>();
    int position;




    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = null;
        prefs = getSharedPreferences("com.enea.allergendatabase", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            getDataFirestore();
            prefs.edit().putBoolean("firstrun", false).commit();
        }else {

        }



    }
    public void getDataFirestore(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("allergens").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
                        Allergen allergen = document.toObject(Allergen.class);
                        String name = allergen.getAllergen_name();
                        String info = allergen.getAllergen_information();
                        String occur = allergen.getAllergen_occurrence();
                        String url = allergen.getImage_url();


                        Log.e("asdasdf", name);

                        saveAllergen(name, info, occur, url);
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        allergens_btn = findViewById(R.id.allergens_btn);
        cross_react_btn = findViewById(R.id.cross_react_btn);

        allergens_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllergenList.class));
            }
        });

        cross_react_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CrossReactions.class));
            }
        });
    }
    public void saveAllergen(String name, String info, String occur, String url){
        Log.e("bbbb", name);
        Allergen allergen = new Allergen(name, info, occur, String.valueOf(url));

        AllergenRepository allergenRepository = new AllergenRepository((Application) getApplicationContext());
        allergenRepository.insert(allergen);
    }

}