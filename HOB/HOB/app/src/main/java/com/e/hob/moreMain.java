package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.hob.Adapters.comparelist_adapters;
import com.e.hob.DataModel.datamodel_comparelist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class moreMain extends AppCompatActivity {

    TextView hostelname, status, city, address, food, acroomvacancy, nonacroomvacancy, acroomrent, nonacroomrent;
    ImageView hostelimage;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moremain);

        setTitle("More Details");

        hostelname = findViewById(R.id.hostelname);
        status = findViewById(R.id.status);
        city = findViewById(R.id.city);
        address = findViewById(R.id.address);
        food = findViewById(R.id.food);
        acroomvacancy = findViewById(R.id.acroomvacancy);
        nonacroomvacancy = findViewById(R.id.nonacroomvacancy);
        acroomrent = findViewById(R.id.acroomrent);
        nonacroomrent = findViewById(R.id.nonacroomrent);
        book = findViewById(R.id.book);

        hostelimage = findViewById(R.id.hostelimage_click);

        Intent intent = this.getIntent();
        final String hosteldata = intent.getExtras().getString("hostelname");

        hostelimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),comingsoon.class);
                startActivity(i);
            }
        });


        moreJSON(hosteldata);

    }

    private void moreJSON(final String hosteldata) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://192.168.43.86/System/moreMain.php?hostelname=" + hosteldata,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            hostelname.setText(jsonObject.getString("hostelname"));
                            address.setText(jsonObject.getString("address"));
                            food.setText(jsonObject.getString("food"));
                            city.setText(jsonObject.getString("city"));
                            status.setText(jsonObject.getString("status"));
                            nonacroomvacancy.setText(jsonObject.getString("nonacroom"));
                            nonacroomrent.setText(jsonObject.getString("nonacrent"));
                            acroomvacancy.setText(jsonObject.getString("acroom"));
                            acroomrent.setText(jsonObject.getString("acrent"));


                            book.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    Toast.makeText(getApplicationContext(), hosteldata, Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(),bookingroom.class);
                                    i.putExtra("hostelname",hosteldata);
                                    String Acroomvacancy = acroomvacancy.getText().toString().trim();
                                    i.putExtra("acroomvacancy",Acroomvacancy);
                                    String Nonacroomvacancy = nonacroomvacancy.getText().toString().trim();
                                    i.putExtra("nonacroomvacancy",Nonacroomvacancy);
                                    String Acroomrent = acroomrent.getText().toString().trim();
                                    i.putExtra("acroomrent",Acroomrent);
                                    String Nonacroomrent = nonacroomrent.getText().toString().trim();
                                    i.putExtra("nonacroomrent",Nonacroomrent);

                                    startActivity(i);


                                }
                            });
                        } catch (JSONException e) {

                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}