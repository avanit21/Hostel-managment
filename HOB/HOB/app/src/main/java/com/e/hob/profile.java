package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profile extends AppCompatActivity {
    TextView tv_name , tv_address , tv_email , tv_phoneNo, tv_city  , tv_dob , tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        Intent intent = this.getIntent();
        String user = intent.getExtras().getString("User");


        tv_name =  findViewById(R.id.username);
        tv_address=  findViewById(R.id.address);
        tv_email =  findViewById(R.id.email);
        tv_phoneNo=  findViewById(R.id.phoneNo);
        tv_age= findViewById(R.id.age);
        tv_city=  findViewById(R.id.city);
        tv_dob= findViewById(R.id.dob);



        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://192.168.43.86/System/profile.php?user="+user,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray jsonArray=new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            tv_name.setText(jsonObject.getString("username"));
                            tv_address.setText("ADDRESS : "+ jsonObject.getString("address"));
                            tv_email.setText("E-MAIL : "+jsonObject.getString("email"));
                            tv_phoneNo.setText("PHONE NO : "+jsonObject.getString("phoneno"));
                            tv_city.setText("CITY : "+jsonObject.getString("city"));
                            tv_age.setText("AGE : "+jsonObject.getString("age"));
                            tv_dob.setText("DOB : "+jsonObject.getString("dob"));

                        }catch (JSONException e)
                        {

                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();


                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}
