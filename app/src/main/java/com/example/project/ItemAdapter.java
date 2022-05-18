package com.example.project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterViewHolder> {
   private List<Lake> lakeList;
   private MainActivity mainActivity;

    public void setLakeList(List<Lake> list){
        this.lakeList = list;
    }

    public ItemAdapter(List<Lake> lakeList, MainActivity activity) {
        this.lakeList = lakeList;
        this.mainActivity = activity;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent,false);
        return new ItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemAdapterViewHolder holder, int position) {
        holder.ID.setText("ID: " + lakeList.get(position).getId());
        holder.name.setText("Namn: " + lakeList.get(position).getName());
        holder.type.setText("Typ: " + lakeList.get(position).getType());
        holder.company.setText("Company: " + lakeList.get(position).getCompany());
        holder.location.setText("Location: " + lakeList.get(position).getLocation());
        holder.category.setText("Category: " + lakeList.get(position).getCategory());
        holder.size.setText("Size: " + lakeList.get(position).getSize());
        holder.cost.setText("Cost: " + lakeList.get(position).getCost());
        holder.wiki.setText("Wiki: " + lakeList.get(position).getAuxdata().getWiki());
        holder.img.setText("img: " + lakeList.get(position).getAuxdata().getImg());
        Picasso.get().load(lakeList.get(position).getAuxdata().getImg().toString()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return lakeList.size();
    }

    public class ItemAdapterViewHolder extends RecyclerView.ViewHolder {


        TextView ID;
        TextView name;
        TextView type;
        TextView company;
        TextView location;
        TextView category;
        TextView size;
        TextView cost;

        TextView wiki;
        TextView img;
        ImageView imageView;
        public ItemAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            ID = itemView.findViewById(R.id.idTextView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            company = itemView.findViewById(R.id.company);
            location = itemView.findViewById(R.id.location);
            category = itemView.findViewById(R.id.category);
            size = itemView.findViewById(R.id.size);
            cost = itemView.findViewById(R.id.cost);
            wiki = itemView.findViewById(R.id.wiki);
            img = itemView.findViewById(R.id.imgText);
            imageView = itemView.findViewById(R.id.urlImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mainActivity, SecondActivity.class);
                    intent.putExtra("namn", "" + name.getText());
                    intent.putExtra("imgURL", "" + img.getText());
                    mainActivity.startActivity(intent);
                }
            });

        }
    }
}
