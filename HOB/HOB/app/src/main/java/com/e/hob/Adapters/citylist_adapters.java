package com.e.hob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.e.hob.DataModel.datamodel_citylist;
import com.e.hob.R;

import java.util.ArrayList;

public class citylist_adapters extends BaseAdapter {


    private Context context;
    private ArrayList<datamodel_citylist> dataModelArrayList;

    public citylist_adapters(Context context, ArrayList<datamodel_citylist> dataModelArrayList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        citylist_adapters.ViewHolder holder;

        if (convertView == null) {
            holder = new citylist_adapters.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.city, null, true);

            holder.tvcity = convertView.findViewById(R.id.cityname);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (citylist_adapters.ViewHolder)convertView.getTag();
        }

        holder.tvcity.setText( "City: "+dataModelArrayList.get(position).getCity() );

        return convertView;
    }

    private class ViewHolder {
        protected TextView  tvcity ;
    }
}
