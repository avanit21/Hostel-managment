package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class signupmore extends AppCompatActivity {

    EditText age , city , address , dob;
    Button serbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_more);

        age = findViewById(R.id.age);
        city = findViewById(R.id.city);
        address = findViewById(R.id.address);
        dob = findViewById(R.id.dob);
        serbutton = findViewById(R.id.profiledetailbutton);

        Intent intent = this.getIntent();
        final String userdata = intent.getExtras().getString("user");

        serbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Age = age.getText().toString().trim();
                String City = city.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Dob = dob.getText().toString().trim();

                inseetdata(userdata , Age , City , Address , Dob);
            }
        });

    }

    private void inseetdata(String userdata, String age, String city, String address, String dob) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.86/System/signupmore.php?username="+userdata+" &age="+age+" &address="+address+" &dob="+dob+" &city="+city,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {
                            if (response.equalsIgnoreCase("done"))
                            {
                                Toast.makeText(getApplicationContext(), "Register Successfully ... " , Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), login.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Failed .. ",Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }}
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