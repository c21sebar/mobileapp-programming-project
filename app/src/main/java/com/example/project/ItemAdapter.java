package com.example.project;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder> {
    @NonNull
    @Override
    public ItemAdapter.ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemAdapterViewHolder extends RecyclerView.ViewHolder {
        public ItemAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
