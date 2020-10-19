package com.enea.allergendatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class AllergenDetails extends AppCompatActivity {

    TextView name_textView, occurrence_tv, info_tv;
    ImageView img_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergen_details);

        img_iv = findViewById(R.id.img_iv);
        name_textView = findViewById(R.id.name_textView);
        occurrence_tv = findViewById(R.id.occurrence_tv);
        info_tv = findViewById(R.id.info_tv);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        String occur = intent.getStringExtra("occur");
        String info = intent.getStringExtra("info");

        Picasso.get().load(imageUrl).into(img_iv);
        name_textView.setText(name);
        occurrence_tv.setText(occur);
        info_tv.setText(info);
    }
}