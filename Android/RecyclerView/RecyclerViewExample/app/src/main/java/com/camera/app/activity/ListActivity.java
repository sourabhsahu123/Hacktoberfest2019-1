package com.camera.app.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camera.app.adapters.VerticalAdapterCategoryItem;
import com.camera.app.model.Item;
import com.example.shehrooz.app.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerViewHorizontal;
    RecyclerView recyclerViewVertical;
    public static TextView tv;
     public Toolbar toolbar;
    DataBaseHandler databaseHandler;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_item);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Item App");
        toolbar.setTitleTextColor(0xFFFFFFFF);


        databaseHandler = new DataBaseHandler(this);

        recyclerViewVertical = findViewById(R.id.car_vertical_recyclerview2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewVertical.setLayoutManager(linearLayoutManager2);
      String cat=getIntent().getStringExtra("category");
        ArrayList<Item>carArrayList=databaseCars(cat);

                recyclerViewVertical.setNestedScrollingEnabled(false);
                recyclerViewVertical.setAdapter(new VerticalAdapterCategoryItem(carArrayList, R.layout.recyclerview_category_item, getApplicationContext()));

    }

    public ArrayList<Item> databaseCars(String category) {
        db = databaseHandler.getWritableDatabase();
        ArrayList<Item> cList = new ArrayList<>();
        try {

            String query = "SELECT name,category,details,ImgFavourite FROM "+DataBaseHandler.TABLE_NAME+" WHERE "+"category"+" = ?";
            Cursor c = db.rawQuery(query, new String[]{ category });
            while (c.moveToNext()) {
                Item c1 = new Item();
                c1.name=c.getString(c.getColumnIndex("name"));
                c1.category=c.getString(c.getColumnIndex("category"));
                c1.details=c.getString(c.getColumnIndex("details"));
                c1.photo=c.getString(c.getColumnIndex("ImgFavourite"));
                cList.add(c1);
            }
        }
        catch (Exception e){
            Log.d("DB",e.toString());
        }
        finally {
            db.close();
        }
        return cList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_addcart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
        View view = notifCount.findViewById(R.id.hotlist_bell);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ListActivity.this, AddItemActivity.class);
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

