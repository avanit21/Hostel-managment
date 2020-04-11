package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class signup extends AppCompatActivity {

    EditText username , email , phoneno , password;
    Button register;
    TextView alreadyExists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        password = findViewById(R.id.password);
        alreadyExists = findViewById(R.id.already_user);
        register = findViewById(R.id.register);
        alreadyExists = findViewById(R.id.already_user);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Phoneno = phoneno.getText().toString().trim();
                String Password = password.getText().toString().trim();
                insertData(user , Email , Phoneno , Password);
            }
        });

        alreadyExists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);

            }
        });



    }

    private void insertData(String User , String Email , String Phoneno , String password) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.86/System/signup.php?username="+User+" &password="+password+" &email="+Email+" &phoneno="+Phoneno,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {
                            if (!response.isEmpty())
                            {
                                String result = response;
                                Toast.makeText(getApplicationContext(), result , Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), signupmore.class);
                                intent.putExtra("user",result);
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