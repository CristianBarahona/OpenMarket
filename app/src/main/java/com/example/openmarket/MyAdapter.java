package com.example.openmarket;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<StoreItem> storeItemsList;

    public MyAdapter(Context context, ArrayList<StoreItem> storeItemsList ){
        this.context = context;
        this.storeItemsList= storeItemsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        StoreItem storeItem = storeItemsList.get(position);

        holder.price.setText(storeItem.getPrice());
        holder.fullName.setText(storeItem.getFullName());
        holder.address.setText(storeItem.getAddress());
        holder.item.setText(storeItem.getItem());

    }

    @Override
    public int getItemCount() {
        return storeItemsList.size();
    }

    public MyAdapter(ArrayList<StoreItem> storeItemsList) {
        this.storeItemsList = storeItemsList;
    }

    public void setData(ArrayList<StoreItem> storeItemsList) {
        this.storeItemsList= storeItemsList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item;
        public TextView price;
        public TextView address;
        public TextView fullName;

        public MyViewHolder(View view) {
            super(view);

            price =  view.findViewById(R.id.textViewPriceCard);
            fullName =  view.findViewById(R.id.textViewFullNameCard);
            address =  view.findViewById(R.id.textViewAddressCard);
            item =  view.findViewById(R.id.textViewItemCard);

            view.findViewById(R.id.buttonShowOnMap).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String mapAddress = address.getText().toString();
                    String map = "http://maps.google.co.in/maps?q=" + mapAddress;
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
                    context.startActivity(i);

                }
            });
        }


    }

}
