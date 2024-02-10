package com.example.oblig1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
Context context;
ArrayList<Item> items;
    public RecyclerViewAdapter(Context context, ArrayList<Item> items ){
    this.context = context;
    this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
    holder.name.setText(items.get(position).getName());
    //holder.imageView.setImageResource(items.get(position).getImage());
        Uri imageUri = items.get(position).getImageUri();
        if(imageUri != null){
            holder.imageView.setImageURI(null);
            holder.imageView.setImageURI(imageUri);
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,items.size());
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;

        ImageButton deleteButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.itemName);
            deleteButton = itemView.findViewById(R.id.button_image_delete);
        }
    }


}
