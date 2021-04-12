package com.example.oop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    // Object holder
    private class ViewHolder{
        ImageView image;
        TextView name, description;
    }

    // Return 1 record in Data
    // position: Vị trí của View
    // convertView: Item View trong List View
    // parent: ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Mỗi khi trượt list lên thì sẽ sinh thêm View ở trong list hiện tại
        // Mỗi khi trượt list xuống thì sẽ load lại View cũ

        // Sử dụng ViewHolder để get lại value của View cũ và show lên chứ không generate mới tốn tài nguyên
        ViewHolder viewHolder;

        if(convertView == null){
            // Xác định context nào để Nạp Layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // Nạp layout
            convertView = inflater.inflate(layout, null);

            viewHolder = new ViewHolder();

            // Mapping data to UI
            viewHolder.name = (TextView) convertView.findViewById(R.id.textView_FruitName);
            viewHolder.description = (TextView) convertView.findViewById(R.id.textView_FruitDescription);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageView_FruitImage);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Get data item
        Fruit fruit = fruits.get(position);

        // Set data item
        viewHolder.name.setText(fruit.getName());
        viewHolder.description.setText(fruit.getDescription());
        viewHolder.image.setImageResource(fruit.getImage());

        // Setting animation khi load 1 View Item
        Animation animationScale = AnimationUtils.loadAnimation(context, R.anim.anim_load_listview_item);
        convertView.startAnimation(animationScale);

        return convertView;
    }

    public FruitApdapter(Context context, int layout, List<Fruit> fruits) {
        this.context = context;
        this.layout = layout;
        this.fruits = fruits;
    }
}
