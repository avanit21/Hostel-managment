package com.e.hob;

// http://192.168.43.86/System/sample.php


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.hob.Adapters.collagelist_adapters;
import com.e.hob.DataModel.datamodel_collagelist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class collagelist extends AppCompatActivity {

    //private String URLstring ="http://192.168.43.86/System/collages.php";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    private EditText collagesearch;
    ArrayList<datamodel_collagelist> dataModelArrayList;
    private collagelist_adapters collagelist_adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collagelist);

        setTitle("Collages");
        listView = findViewById(R.id.collagelist);

        Intent intent = this.getIntent();
        String citydata = intent.getExtras().getString("cityname");
        Toast.makeText(getApplicationContext(),citydata,Toast.LENGTH_SHORT).show();

        retrieveJSON(citydata);


    }

    private void retrieveJSON(final String citydata) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.43.86/System/collages.php?city="+citydata,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            dataModelArrayList = new ArrayList<>();

                            JSONArray dataArray = new JSONArray(response);

                            if (!response.isEmpty()) {



                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject obj = dataArray.getJSONObject(i);
                                    datamodel_collagelist playerModel = new datamodel_collagelist();
                                    playerModel.setName(obj.getString("name"));
                                    playerModel.setAddress(obj.getString("address"));
                                    playerModel.setCity(obj.getString("city"));
                                    playerModel.setState(obj.getString("state"));

                                    dataModelArrayList.add(playerModel);

                                    setListView();

                                }
                            }else {
                                Toast.makeText(getApplicationContext(),"No Data Found .. ",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    private void setListView() {
                        collagelist_adapters = new collagelist_adapters(collagelist.this, dataModelArrayList);
                        listView.setAdapter(collagelist_adapters);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String value =  dataModelArrayList.get(i).getName();
                                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), hostellist.class);
                                intent.putExtra("collagename",value);
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
    }}