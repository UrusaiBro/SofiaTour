package com.mbtutu.sofiatour;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class ImageListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<?> data;
    View view;
    Resources res;


    private static LayoutInflater inflater = null;

    public ImageListViewAdapter(Context context, ArrayList<?> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        res = context.getApplicationContext().getResources();


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
                holder.rowTitle = vi.findViewById(R.id.smallRowTextView);
                //holder.rowDesc = vi.findViewById(R.id.rowDesc);
                holder.listImage = vi.findViewById(R.id.listImageSmall);


                vi.setTag(holder);
            }
            else {
                holder = (ViewHolder) vi.getTag();
            }


            holder.rowTitle.setText(((Sight)data.get(position)).getName());
            //new DownloadImageTask(holder.listImage).execute(((Sight)data.get(position)).getPictureUrl());
            holder.listImage.setImageResource(res.getIdentifier(((Sight) data.get(position)).getPictureUrl(), "drawable", context.getPackageName()));




            vi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SingleSightActivity.class);
                    intent.putExtra("title", ((Sight) data.get(position)).getName());
                    intent.putExtra("desc", ((Sight) data.get(position)).getDescription());
                    intent.putExtra("picurl", ((Sight) data.get(position)).getPictureUrl());
                    context.startActivity(intent);
                }
            });
        }




        else if(data.get(position) instanceof Tour) {

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


            holder.rowTitle.setText(((Tour)data.get(position)).getName());
            holder.rowDesc.setText(((Tour)data.get(position)).getDescritpion());
            //new DownloadImageTask(holder.listImage).execute(((TourBundle)data.get(position)).getPictureUrl());
            holder.listImage.setImageResource(res.getIdentifier(((Tour) data.get(position)).getPictureUrl(), "drawable", context.getPackageName()));


            vi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SingleTourActivity.class);
                    intent.putExtra("title", ((Tour) data.get(position)).getName());
                    intent.putExtra("desc", ((Tour) data.get(position)).getDescritpion());
                    intent.putExtra("picurl", ((Tour) data.get(position)).getPictureUrl());
                    intent.putExtra("sights", ((Tour) data.get(position)).getSights());
                    context.startActivity(intent);
                }
            });
        }





        return vi;

    }













}
