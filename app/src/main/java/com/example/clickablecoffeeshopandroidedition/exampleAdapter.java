package com.example.clickablecoffeeshopandroidedition;

//exampleAdapter creates an Adapter for Custom listview items.
//This is why the list items in each shop has a picture as well as 2 lines of text.
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class exampleAdapter extends RecyclerView.Adapter<exampleAdapter.ExampleViewHolder> {
    private ArrayList<exampleAdapter> myExampleList;
        private int itemImageResource;
        private String itemName;
        private String itemDescription;

        public exampleAdapter(int imageResource, String Name, String Description) {
            itemImageResource = imageResource;
            itemName = Name;
            itemDescription = Description;
        }

        public void changeDescription(String text) {
            itemDescription = text;
        }

        public int getImageResource() {
            return itemImageResource;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemDescription() {
            return itemDescription;
        }


    private OnItemClickListener myListener;

    public interface OnItemClickListener{
            void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        myListener = listener;
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView myImageView;
        public TextView myTextView1;
        public TextView myTextView2;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.imageView);
            myTextView1 = itemView.findViewById(R.id.textView);
            myTextView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public exampleAdapter(ArrayList<exampleAdapter> exampleList) {
        myExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exampleupgradeitem, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, myListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        exampleAdapter currentItem = myExampleList.get(position);
        holder.myImageView.setImageResource(currentItem.getImageResource());
        holder.myTextView1.setText(currentItem.getItemName());
        holder.myTextView2.setText(currentItem.getItemDescription());
    }

    @Override
    public int getItemCount() {
        return myExampleList.size();
    }
}
