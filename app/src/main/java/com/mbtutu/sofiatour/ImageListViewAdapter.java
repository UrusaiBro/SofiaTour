package com.mbtutu.sofiatour;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ImageListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sight> data;
    View view;


    private static LayoutInflater inflater = null;

    public ImageListViewAdapter(Context context, ArrayList<Sight> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    static class ViewHolder {
        TextView rowTitle, rowDesc;
        ImageView listImage;
    }




    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        final ViewHolder holder;
        View vi = convertView;
        if (vi == null){
            holder = new ViewHolder();

            // setvam id-ta
            vi = inflater.inflate(R.layout.row, null);
            holder.rowTitle = vi.findViewById(R.id.rowTitle);
            holder.rowDesc = vi.findViewById(R.id.rowDesc);
            holder.listImage = vi.findViewById(R.id.listImage);

            vi.setTag(holder);
        }
        else {
            holder = (ViewHolder) vi.getTag();
        }

        // assign data into view

        holder.rowTitle.setText(data.get(position).getName());
        holder.rowDesc.setText(data.get(position).getDescription());
        new DownloadImageTask(holder.listImage).execute(data.get(position).getPictureUrl());


        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleTourActivity.class);
                context.startActivity(intent);
            }
        });


        return vi;

    }
}
