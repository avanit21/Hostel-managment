package com.e.hob.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.e.hob.DataModel.datamodel_hostellist;
import com.e.hob.R;

import java.util.ArrayList;

public class hostellist_adapters extends BaseAdapter {

    private Context context;
    private ArrayList<datamodel_hostellist> dataModelArrayList;

    public hostellist_adapters(Context context, ArrayList<datamodel_hostellist> dataModelArrayList ) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        hostellist_adapters.ViewHolder holder;

        if (convertView == null) {
            holder = new hostellist_adapters.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_hostelist_list_details, null, true);

            holder.tvname =  convertView.findViewById(R.id.hostelname);
            //holder.tvaddress =  convertView.findViewById(R.id.address);
            //holder.tvcity =  convertView.findViewById(R.id.city);
            holder.tvrating =  convertView.findViewById(R.id.rating);
            holder.tvfoodrating =  convertView.findViewById(R.id.foodrating);
            holder.tvnearbycollage = convertView.findViewById(R.id.nearbycollage);

            holder.compare = convertView.findViewById(R.id.compare);
            holder.more = convertView.findViewById(R.id.more);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (hostellist_adapters.ViewHolder)convertView.getTag();
        }


        holder.tvname.setText( dataModelArrayList.get(position).getName());
        holder.tvnearbycollage.setText("Address: " + dataModelArrayList.get(position).getNearbycollage());
        //holder.tvcity.setText("City: " + dataModelArrayList.get(position).getCity());
        holder.tvrating.setText("Rating : " + dataModelArrayList.get(position).getRating());
        holder.tvfoodrating.setText("Food Rating : " + dataModelArrayList.get(position).getFoodrating());

        holder.compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 comparehostels(position);
            }

        });

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                morehostel(position);
            }



        });


        return convertView;
    }

    private void comparehostels(int position) {

        Toast.makeText(context,"Comparing hostels .. ",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(context,com.e.hob.comparelist.class);

        String hostelname =  dataModelArrayList.get(position).getName();
        int rating =  dataModelArrayList.get(position).getRating();
        int foodrating =  dataModelArrayList.get(position).getFoodrating();
        String nearbycollage = dataModelArrayList.get(position).getNearbycollage();

        i.putExtra("hostelname",hostelname);
        i.putExtra("rating",rating);
        i.putExtra("foodrating",foodrating);
        i.putExtra("nearbycollage",nearbycollage);

        Toast.makeText(context,hostelname,Toast.LENGTH_SHORT).show();

        context.startActivity(i);

    }

    private void morehostel(int position) {

        Toast.makeText(context,"Fetching hostel data .. ",Toast.LENGTH_SHORT).show();

        Intent i = new Intent(context,com.e.hob.moreMain.class);

        String hostelname =  dataModelArrayList.get(position).getName();
        i.putExtra("hostelname",hostelname);

        Toast.makeText(context,hostelname,Toast.LENGTH_SHORT).show();

        context.startActivity(i);

    }


    private class ViewHolder {
        protected TextView tvname, tvnearbycollage, tvcity , tvrating , tvfoodrating;
        protected ImageButton compare , more;
    }

}
