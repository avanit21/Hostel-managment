package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.hob.Adapters.comparelist_adapters;
import com.e.hob.Adapters.hostellist_adapters;
import com.e.hob.DataModel.datamodel_comparelist;
import com.e.hob.DataModel.datamodel_hostellist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class comparelist extends AppCompatActivity {

    TextView tv_hostelname , tv_rating , tv_foodrating , tv_nearbycollage;

    private ListView listView;
    ArrayList<datamodel_comparelist> dataModelArrayList;
    private comparelist_adapters comparelist_adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparelist);

        setTitle("Compare Result");

        listView = findViewById(R.id.comparedhostels);

        tv_hostelname = findViewById(R.id.hostelname);
        tv_rating = findViewById(R.id.rating);
        tv_foodrating = findViewById(R.id.foodrating);
        tv_nearbycollage = findViewById(R.id.nearbycollage);

        Intent intent = this.getIntent();

        String hosteldata = intent.getExtras().getString("hostelname");
        String nearbycollage = intent.getExtras().getString("nearbycollage");
        int rating = intent.getExtras().getInt("rating");
        int foodrating = intent.getExtras().getInt("foodrating");

        tv_hostelname.setText(hosteldata);
        tv_nearbycollage.setText("Nearby Collage : " + nearbycollage);
        tv_rating.setText("Rating : " + Integer.toString(rating));
        tv_foodrating.setText("Foodrating : " + Integer.toString(foodrating));


        retrieveJSON(hosteldata,nearbycollage , rating , foodrating);
    }

    private void retrieveJSON(String hostelname , String nearbycollage, final int rating, final int foodrating) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.86/System/compare.php?nearbycollage="+nearbycollage+" &hostelname="+hostelname,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray = new JSONArray(response);

                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject obj = dataArray.getJSONObject(i);
                                datamodel_comparelist playerModel = new datamodel_comparelist();
                                playerModel.setName(obj.getString("hostelname"));
                                playerModel.setNearbycollage(obj.getString("nearbycollage"));
                                playerModel.setRating(obj.getInt("rating"));
                                playerModel.setFoodrating(obj.getInt("foodrating"));
                                dataModelArrayList.add(playerModel);

                                setUpList();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    private void setUpList() {
                        comparelist_adapters = new comparelist_adapters(getApplicationContext(), dataModelArrayList , rating, foodrating);
                        listView.setAdapter(comparelist_adapters);
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
