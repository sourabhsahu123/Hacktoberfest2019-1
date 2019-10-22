package com.camera.app.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shehrooz.app.R;
import com.camera.app.model.Item;

public class DetailsActivity extends AppCompatActivity {

    Item car = new Item();

    TextView title, category, detail;
    ImageView photo;
    ImageView categoryPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setTitle("DetailsActivity");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsActivity.super.onBackPressed();

            }
        });


        title = findViewById(R.id.food_title);
        category = findViewById(R.id.food_price);

        detail = findViewById(R.id.food_calories);
        photo=findViewById(R.id.food_image);
        categoryPhoto=findViewById(R.id.categoryImg);
        byte[] imageAsBytes = Base64.decode(getIntent().getStringExtra("photo").getBytes(), Base64.DEFAULT);
        photo.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        title.setText(getIntent().getStringExtra("name"));
        category.setText(getIntent().getStringExtra("category"));
        detail.setText(getIntent().getStringExtra("details"));
        String cat = getIntent().getStringExtra("category");

        if(cat.equals("SUV"))
            categoryPhoto.setImageResource(R.drawable.suv);
        else if(cat.equals("Hatchback"))
            categoryPhoto.setImageResource(R.drawable.hatchback);
        else if(cat.equals("Crossover"))
            categoryPhoto.setImageResource(R.drawable.crossover);
        else if(cat.equals("MPV"))
            categoryPhoto.setImageResource(R.drawable.mpv);
        else if(cat.equals("Coupe"))
            categoryPhoto.setImageResource(R.drawable.coupe);
        else if(cat.equals("Sedan"))
            categoryPhoto.setImageResource(R.drawable.sedan);
        else if(cat.equals("Convertible"))
            categoryPhoto.setImageResource(R.drawable.convertible);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_addcart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
       View view = notifCount.findViewById(R.id.hotlist_bell);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DetailsActivity.this, AddItemActivity.class);
                startActivity(myIntent);


            }});

        return true;
    }


}
