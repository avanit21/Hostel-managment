package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentSuccess extends AppCompatActivity {

    TextView hostelname , payment_id , pay , phoneno, email , payment_result;
    Button continue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        hostelname = findViewById(R.id.hostelname);
        payment_id = findViewById(R.id.payment_id);
        pay = findViewById(R.id.pay);
        phoneno = findViewById(R.id.phoneno);
        email = findViewById(R.id.email);
        payment_result = findViewById(R.id.paymentresult);


        Intent intent = this.getIntent();
        String Pay = intent.getExtras().getString("pay");
        String hostel = intent.getExtras().getString("hostelname");
        String Payment_id = intent.getExtras().getString("payment_id");
        String Phoneno = intent.getExtras().getString("phoneno");
        String Email = intent.getExtras().getString("email");

        hostelname.setText(hostel);
        pay.setText(Pay);
        payment_id.setText(Payment_id);
        phoneno.setText(Phoneno);
        email.setText(Email);

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });

    }
}
