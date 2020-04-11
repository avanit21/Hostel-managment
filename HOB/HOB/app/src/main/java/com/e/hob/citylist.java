package com.e.hob;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.hob.Adapters.citylist_adapters;
import com.e.hob.DataModel.datamodel_citylist;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class citylist extends AppCompatActivity {

    private String URLstring ="http://192.168.43.86/System/city.php";
    private ListView listView;
    ArrayList<datamodel_citylist> dataModelArrayList;
    private citylist_adapters citylist_adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        setTitle("City");
        listView = findViewById(R.id.citylistview);
        /*
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        //For get data form database
        retrieveJSON();

    }

    private void retrieveJSON() {

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            dataModelArrayList = new ArrayList<>();

                            JSONArray dataArray = new JSONArray(response);


                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject obj = dataArray.getJSONObject(i);
                                datamodel_citylist playerModel = new datamodel_citylist();
                                playerModel.setCity(obj.getString("city"));
                                dataModelArrayList.add(playerModel);
                                setListView();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    private void setListView() {
                            citylist_adapters = new citylist_adapters(getApplicationContext(), dataModelArrayList);
                            listView.setAdapter(citylist_adapters);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String value =  dataModelArrayList.get(i).getCity();
                                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), collagelist.class);
                                intent.putExtra("cityname",value);
                                startActivity(intent);

                                }
                        });
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}