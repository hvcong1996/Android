package com.example.sql_lite_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Item> itemList;

    public ItemAdapter(Context context, int layout, List<Item> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtName, txtDescription;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textViewItemName);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.textViewItemDescription);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageViewImage);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = itemList.get(position);

        viewHolder.txtName.setText(item.getName());
        viewHolder.txtDescription.setText(item.getDescription());

        // Convert byte[] to bitmap
        byte[] byteImage = item.getImage();
        // byteImage: byte[] data
        // offset:0 : giá trị bắt đầu trong data để thực hiện xử lý convert to bitmap
        // byteImage.length: giá trị cuối cùng trong data để thực hiện xử lý convert to bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
        viewHolder.imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
