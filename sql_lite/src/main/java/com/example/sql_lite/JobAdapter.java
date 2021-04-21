package com.example.sql_lite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class JobAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Job> jobList;

    public JobAdapter(Context context, int layout, List<Job> jobList) {
        this.context = (MainActivity)context;
        this.layout = layout;
        this.jobList = jobList;
    }

    @Override
    public int getCount() {
        return jobList.size();
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
        TextView txtName;
        ImageView imageViewEdit, imageViewDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout, null);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textViewJobName);
            viewHolder.imageViewEdit = (ImageView) convertView.findViewById(R.id.imageViewJobEdit);
            viewHolder.imageViewDelete = (ImageView) convertView.findViewById(R.id.imageViewJobDelete);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Job job = jobList.get(position);
        viewHolder.txtName.setText(job.getName());

        // Event khi user click edit
        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.EditJobDialog(job);
//                Toast.makeText(context, "Click edit " + jobs.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // Event khi user click delete
        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DeleteJobDialog(job);
//                Toast.makeText(context, "Click delete " + job.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
