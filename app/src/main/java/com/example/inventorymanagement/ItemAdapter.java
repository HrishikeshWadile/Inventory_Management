package com.example.inventorymanagement;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        Item item = itemList.get(position);
//        holder.itemName.setText(item.getName());
//        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
//        holder.itemPrice.setText(String.valueOf(item.getPrice()));
//    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText("Qty: " + item.getQuantity());
        holder.itemPrice.setText("₹" + item.getPrice());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), EditItemActivity.class); // Use holder.itemView.getContext()
            intent.putExtra("itemId", item.getId());
            intent.putExtra("itemName", item.getName());
            intent.putExtra("itemQuantity", item.getQuantity());
            intent.putExtra("itemPrice", item.getPrice());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }
    }


//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Item item = itemList.get(position);
//        holder.itemName.setText(item.getName());
//        holder.itemQuantity.setText("Qty: " + item.getQuantity());
//        holder.itemPrice.setText("₹" + item.getPrice());
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, EditItemActivity.class);
//            intent.putExtra("itemId", item.getId());
//            intent.putExtra("itemName", item.getName());
//            intent.putExtra("itemQuantity", item.getQuantity());
//            intent.putExtra("itemPrice", item.getPrice());
//            context.startActivity(intent);
//        });
//    }
}