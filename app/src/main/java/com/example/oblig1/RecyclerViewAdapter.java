package com.example.oblig1;

import android.annotation.SuppressLint;
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
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Item> items;
    private ItemDeleteListener listener;

    public RecyclerViewAdapter(Context context, List<Item> items, ItemDeleteListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }


    // Oppdaterer listen
    public void updateItems(List<Item> newItems) {
        this.items = newItems;
    }

    // Sorterer listen
    public void sortItems(boolean ascending) {
        if (ascending) {
            Collections.sort(items, Item.compareByNameAZ());
        } else {
            Collections.sort(items, Item.compareByNameZA());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(items.get(position).getName());

        Uri imageUri = Uri.parse(items.get(position).getImage());
        if (imageUri != null) {
            holder.imageView.setImageURI(imageUri);
        }

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fjerner elementet fra databasen og vil også bli fjernet fra listen pga obersver i Galleryklassen
                listener.onDeleteItem(items.get(position));
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
