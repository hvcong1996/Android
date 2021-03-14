package com.example.oop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitApdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> fruits;

    @Override
    public int getCount() {
        return fruits.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Return 1 record in Data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Xác định lấy context nào
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Gắn data vào View(data của 1 record)
        convertView = inflater.inflate(layout, null);

        // Mapping data to UI
        TextView txtFruitName = (TextView) convertView.findViewById(R.id.textView_FruitName);
        TextView txtFruitDescription = (TextView) convertView.findViewById(R.id.textView_FruitDescription);
        ImageView imgFruitImage = (ImageView) convertView.findViewById(R.id.imageView_FruitImage);

        // Set data
        Fruit fruit = fruits.get(position);

        txtFruitName.setText(fruit.getName());
        txtFruitDescription.setText(fruit.getDescription());
        imgFruitImage.setImageResource(fruit.getImage());

        return convertView;
    }

    public FruitApdapter(Context context, int layout, List<Fruit> fruits) {
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }
}
