package com.camera.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camera.app.model.ItemCategory;
import com.example.shehrooz.app.R;
import com.camera.app.activity.ListActivity;

import java.util.List;



public class VerticalAdapterCategory extends RecyclerView.Adapter<VerticalAdapterCategory.VerticalViewHolder> {

    private List<ItemCategory> carsCategory;
    private Context context;


    public static class VerticalViewHolder extends RecyclerView.ViewHolder{

        LinearLayout verticalLayout;
        TextView regularTitle;
        TextView regularPrice;
        ImageView regularImage;
        ImageButton regularPlus;

        public VerticalViewHolder(View itemView) {
            super(itemView);

            verticalLayout = itemView.findViewById(R.id.horizontal_parent_layout);
            regularTitle = itemView.findViewById(R.id.categorytext);
            regularImage = itemView.findViewById(R.id.carcategorypic);
          //  regularPrice = itemView.findViewById(R.id.regular_food_price);
         //   regularPlus = itemView.findViewById(R.id.regular_food_plus);

        }
    }

    public VerticalAdapterCategory(List<ItemCategory> carsList, int vertical_recyclerview, Context context){
        this.context = context;
        this.carsCategory = carsList;
    }

    @NonNull
    @Override
    public VerticalAdapterCategory.VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_category, parent, false);
        return new VerticalAdapterCategory.VerticalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VerticalAdapterCategory.VerticalViewHolder holder, final int position) {
        holder.regularTitle.setText(carsCategory.get(position).category);
        if(carsCategory.get(position).imageid>0)
         holder.regularImage.setImageResource(carsCategory.get(position).imageid);

          holder.verticalLayout.setOnClickListener(new View.OnClickListener() {
              @Override

         public void onClick(View v) {
             Intent intent = new Intent(context, ListActivity.class);
           //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("category", carsCategory.get(position).category);
                 //      intent.putExtra("foodImage",regularFoods.get(position).getImage());
               context.startActivity(intent);
           }
      });
}



    @Override
    public int getItemCount() {
        return carsCategory.size();
    }
}
