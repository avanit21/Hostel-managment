package com.e.hob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.hob.Adapters.collagelist_adapters;
import com.e.hob.Adapters.hostellist_adapters;
import com.e.hob.DataModel.datamodel_collagelist;
import com.e.hob.DataModel.datamodel_hostellist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class hostellist extends AppCompatActivity {

    private ListView listView;
    ArrayList<datamodel_hostellist> dataModelArrayList;
    private hostellist_adapters hostellist_adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostellist);
        setTitle("Hostel results");

        listView = findViewById(R.id.hostellist);

        Intent intent = this.getIntent();
        String collagedata = intent.getExtras().getString("collagename");
        Toast.makeText(getApplicationContext(),collagedata,Toast.LENGTH_SHORT).show();

        retrieveJSON(collagedata);

    }



    private void retrieveJSON(String collagedata) {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.86/System/hostel_main_details.php?nearbycollage="+collagedata,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                                dataModelArrayList = new ArrayList<>();

                                JSONArray dataArray = new JSONArray(response);


                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject obj = dataArray.getJSONObject(i);
                                    datamodel_hostellist playerModel = new datamodel_hostellist();
                                    playerModel.setName(obj.getString("hostelname"));
                                    //playerModel.setAddress(obj.getString("address"));
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
                        hostellist_adapters = new hostellist_adapters(hostellist.this, dataModelArrayList);
                        listView.setAdapter(hostellist_adapters);
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
