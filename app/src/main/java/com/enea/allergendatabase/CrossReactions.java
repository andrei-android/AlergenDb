package com.enea.allergendatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CrossReactions extends AppCompatActivity {

    ImageView im_iv;
    String URL = "https://www.foodsmatter.com/images/pic_allergy_food/crossreaction-infogram-11-13.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_reactions);

        im_iv = findViewById(R.id.im_iv);

        Picasso.get().load(URL).into(im_iv);
    }
}