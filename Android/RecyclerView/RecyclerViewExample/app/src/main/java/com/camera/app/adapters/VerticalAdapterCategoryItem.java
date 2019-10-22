package com.camera.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camera.app.activity.DetailsActivity;
import com.example.shehrooz.app.R;
import com.camera.app.model.Item;

import java.util.List;


public class VerticalAdapterCategoryItem extends RecyclerView.Adapter<VerticalAdapterCategoryItem.VerticalViewHolder> {

    private List<Item> cars;
    private Context context;


    public static class VerticalViewHolder extends RecyclerView.ViewHolder{

        LinearLayout verticalLayout;
        TextView regularTitle;
        TextView regularPrice;
        ImageView regularImage;
        TextView regularPlus;

        public VerticalViewHolder(View itemView) {
            super(itemView);

            verticalLayout = itemView.findViewById(R.id.horizontal_parent_layout2);
            regularTitle = itemView.findViewById(R.id.categorytext);
            regularImage = itemView.findViewById(R.id.cart_food_pic2);
            regularPrice = itemView.findViewById(R.id.categorytext1);
            regularPlus = itemView.findViewById(R.id.categorytext2);
          //  regularPrice = itemView.findViewById(R.id.regular_food_price);
         //   regularPlus = itemView.findViewById(R.id.regular_food_plus);

        }
    }

    public VerticalAdapterCategoryItem(List<Item> cars, int vertical_recyclerview, Context context){
        this.context = context;
        this.cars = cars;
    }

    @NonNull
    @Override
    public VerticalAdapterCategoryItem.VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_category_item, parent, false);
        return new VerticalAdapterCategoryItem.VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VerticalAdapterCategoryItem.VerticalViewHolder holder, final int position) {
        holder.regularTitle.setText(cars.get(position).name);
        holder.regularPrice.setText(cars.get(position).category);
        holder.regularPlus.setText(cars.get(position).details);
       // byte[] decodedString = Base64.decode(cars.get(position).photo, Base64.DEFAULT);
     //   Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);
      //  holder.regularImage.setImageBitmap(decodedByte);

        byte[] imageAsBytes = Base64.decode(cars.get(position).photo.getBytes(), Base64.DEFAULT);
        holder.regularImage.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
  holder.verticalLayout.setOnClickListener(new View.OnClickListener() {
              @Override

         public void onClick(View v) {
             Intent intent = new Intent(context, DetailsActivity.class);
              intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("name", cars.get(position).name);
            intent.putExtra("details",cars.get(position).details);
                  intent.putExtra("category",cars.get(position).category);
                  intent.putExtra("photo",cars.get(position).photo);
               context.startActivity(intent);
           }
      });

    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}
