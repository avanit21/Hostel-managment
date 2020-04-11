package com.e.hob.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.hob.DataModel.datamodel_collagelist;
import com.e.hob.R;

import java.util.ArrayList;

public class collagelist_adapters extends BaseAdapter {


    private Context context;
    private ArrayList<datamodel_collagelist> dataModelArrayList;

    public collagelist_adapters(Context context, ArrayList<datamodel_collagelist> dataModelArrayList ) {

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
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_collagelist_list_details, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvaddress = (TextView) convertView.findViewById(R.id.address);
            holder.tvcity = (TextView) convertView.findViewById(R.id.city);
            holder.tvstate = (TextView) convertView.findViewById(R.id.state);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }


            holder.tvname.setText("Name: " + dataModelArrayList.get(position).getName());
            holder.tvaddress.setText("Address: " + dataModelArrayList.get(position).getAddress());
            holder.tvcity.setText("City: " + dataModelArrayList.get(position).getCity());
            holder.tvstate.setText("State: " + dataModelArrayList.get(position).getState());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvaddress, tvcity , tvstate;
        //protected ImageView iv;
    }

}
