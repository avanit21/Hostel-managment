package com.e.hob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.e.hob.DataModel.datamodel_comparelist;
import com.e.hob.R;
import java.util.ArrayList;

public class comparelist_adapters extends BaseAdapter {

    private Context context;
    private ArrayList<datamodel_comparelist> dataModelArrayList;
    int mainrating, mainfoodrating;

    public comparelist_adapters(Context context, ArrayList<datamodel_comparelist> dataModelArrayList  , int rating , int foodrating) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
        mainrating = rating;
        mainfoodrating = foodrating;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        comparelist_adapters.ViewHolder holder;

        if (convertView == null) {
            holder = new comparelist_adapters.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_collagelist_list_details, null, true);

            holder.tvhostelname = convertView.findViewById(R.id.name);
            holder.tvrating = convertView.findViewById(R.id.address);
            holder.tvnearbycollage = convertView.findViewById(R.id.city);
            holder.tvfoodrating = convertView.findViewById(R.id.state);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (comparelist_adapters.ViewHolder)convertView.getTag();
        }

        holder.tvhostelname.setText( dataModelArrayList.get(position).getName() );
        holder.tvnearbycollage.setText( "Nearby Collage : "+dataModelArrayList.get(position).getNearbycollage() );
        holder.tvrating.setText( "Rating : "+dataModelArrayList.get(position).getRating() );
        holder.tvfoodrating.setText( "Food Rating : "+dataModelArrayList.get(position).getFoodrating() );


        return convertView;
    }

    private class ViewHolder {
        protected TextView tvhostelname , tvrating , tvfoodrating , tvnearbycollage  ;
    }
}
