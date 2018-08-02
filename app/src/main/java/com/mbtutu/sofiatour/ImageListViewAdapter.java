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


public class ImageListViewAdapter extends BaseAdapter {

    Context context;
    String[] titles ,descriptions;
    View view;


    private static LayoutInflater inflater = null;

    public ImageListViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;

        Resources res = context.getApplicationContext().getResources();
        titles = res.getStringArray(res.getIdentifier("titles", "array", context.getPackageName()));
        descriptions = res.getStringArray(res.getIdentifier("descriptions", "array", context.getPackageName()));

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return titles[position];
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

        Resources res = context.getApplicationContext().getResources();
        holder.rowTitle.setText(titles[position]);
        holder.rowDesc.setText(descriptions[position]);
        holder.listImage.setImageResource(res.getIdentifier("pic"+position, "drawable", context.getPackageName()));


        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingleTourActivity.class);
                context.startActivity(intent);
            }
        });

        /*
        holder.listImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // otvarq sledvashtiq fragment/activity s info za marshruta
            }
        });
        */

        return vi;

    }
}
