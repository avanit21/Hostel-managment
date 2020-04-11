package com.e.hob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class login extends AppCompatActivity {

    EditText l_username , l_password;

    TextView textview_user;

    String username , password , result;
    Button loginbutton , signupbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        l_username = findViewById(R.id.login_username);
        l_password = findViewById(R.id.login_password);
        loginbutton = findViewById(R.id.login_button);
        signupbutton = findViewById(R.id.sign_up_button);

        textview_user = findViewById(R.id.textView_user);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = l_username.getText().toString().trim();
                password = l_password.getText().toString().trim();
                retrieveJSON(username,password);
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext() , signup.class);
                startActivity(i);
            }
        });


    }

    private void retrieveJSON(String username, String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.43.86/System/login.php?username="+username+" &password="+password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {
                            if (!response.isEmpty())
                            {
                                result = response;
                                Toast.makeText(getApplicationContext(), "Welcome .. "+result,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("user",result);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Enter valid data.. for login",Toast.LENGTH_SHORT).show();
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
