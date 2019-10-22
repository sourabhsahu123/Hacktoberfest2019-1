package com.camera.app.activity;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shehrooz.app.R;
import com.camera.app.adapters.VerticalAdapterCategory;
import com.camera.app.model.ItemCategory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewHorizontal;
    RecyclerView recyclerViewVertical;
    public static TextView tv;
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Item App");
        toolbar.setTitleTextColor(0xFFFFFFFF);




        recyclerViewVertical = findViewById(R.id.vertical_recyclerview);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);


        List<ItemCategory>cList = new ArrayList<ItemCategory>();
       String[]m= getResources().getStringArray(R.array.car_category);
        for(int i=0; i<m.length;i++ )
        {
            ItemCategory c = new ItemCategory();
            c.category=m[i];
            if(m[i].equals("Sony"))
                c.imageid=R.drawable.sony;
            else if(m[i].equals("Nikon"))
            c.imageid=R.drawable.nikon;
            else if(m[i].equals("Canon"))
                c.imageid=R.drawable.canon;

            cList.add(c);
        }
        recyclerViewVertical.setNestedScrollingEnabled(false);
        recyclerViewVertical.setAdapter(new VerticalAdapterCategory(cList, R.layout.recyclerview_category, getApplicationContext()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        cartUpdate();
      MenuItem item = menu.findItem(R.id.action_addcart);
       MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
      RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
       // tv = notifCount.findViewById(R.id.hotlist_hot);
      View view = notifCount.findViewById(R.id.hotlist_bell);
//
//        cartUpdate();
//
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(myIntent);


            }});

        return true;
    }


    @Override
    protected void onResume() {
        invalidateOptionsMenu();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

