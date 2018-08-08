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
    ArrayList<?> data;
    View view;


    private static LayoutInflater inflater = null;

    public ImageListViewAdapter(Context context, ArrayList<?> data) {
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

        // assign data into view

        if(data.get(position) instanceof Sight) {


            if (vi == null){
                holder = new ViewHolder();

                // setvam id-ta
                vi = inflater.inflate(R.layout.row_small, null);
                //holder.rowTitle = vi.findViewById(R.id.rowTitle);
                //holder.rowDesc = vi.findViewById(R.id.rowDesc);
                holder.listImage = vi.findViewById(R.id.listImageSmall);

                vi.setTag(holder);
            }
            else {
                holder = (ViewHolder) vi.getTag();
            }


            //holder.rowTitle.setText(((Sight)data.get(position)).getName());
            //holder.rowDesc.setText(((Sight)data.get(position)).getDescription());
            //new DownloadImageTask(holder.listImage).execute(((Sight)data.get(position)).getPictureUrl());

            Resources res = context.getApplicationContext().getResources();
            //holder.rowTitle.setText(res.getStringArray(res.getIdentifier("aasd", "array", context.getPackageName()))[position]);
            String name = ((Sight) data.get(position)).getPictureUrl();
            int id = res.getIdentifier(name, "drawable", context.getPackageName());
            holder.listImage.setImageResource(id);

            holder.listImage.setImageResource(R.drawable.pic0 + position);
        }

        else if(data.get(position) instanceof TourBundle) {

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


            holder.rowTitle.setText(((TourBundle)data.get(position)).getName());
            holder.rowDesc.setText(((TourBundle)data.get(position)).getDescritpion());
            //new DownloadImageTask(holder.listImage).execute(((TourBundle)data.get(position)).getPictureUrl());


            Resources res = context.getApplicationContext().getResources();
            String name = ((TourBundle) data.get(position)).getPictureUrl();
            int id = res.getIdentifier(name, "drawable", context.getPackageName());
            holder.listImage.setImageResource(id);



        }


        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleSightActivity.class);
                context.startActivity(intent);
            }
        });


        return vi;

    }
}
