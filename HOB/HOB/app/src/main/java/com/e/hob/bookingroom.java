package com.e.hob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class bookingroom extends AppCompatActivity {

    EditText checkindate , checkoutdate , room , acornonac;
    Button bookroommain;

    int pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingroom);
        setTitle("Book Room");

        checkindate = findViewById(R.id.checkindate);
        checkoutdate = findViewById(R.id.checkoutdate);
        room = findViewById(R.id.room);
        acornonac = findViewById(R.id.acornonac);
        bookroommain = findViewById(R.id.bookroommain);

        Intent intent = this.getIntent();
        final String hosteldata = intent.getExtras().getString("hostelname");
        final String nonacroomvacancy = intent.getExtras().getString("nonacroomvacancy");
        final String acroomvacancy = intent.getExtras().getString("acroomvacancy");
        final String acroomrent = intent.getExtras().getString("acroomrent");
        final String nonacroomrent = intent.getExtras().getString("nonacroomrent");

        bookroommain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Room = new Integer(room.getText().toString());
                String AcOrNon = acornonac.getText().toString().trim();

                if(AcOrNon.equalsIgnoreCase("AC"))
                {
                     pay = (new Integer(acroomrent))*Room;
                }
                if (AcOrNon.equalsIgnoreCase("NONAC"))
                {
                     pay = (new Integer(nonacroomrent))*Room;
                }

                Intent i = new Intent(getApplicationContext() , PaymentActivity.class);
                i.putExtra("pay",Integer.toString(pay));
                i.putExtra("hostelname",hosteldata);
                Toast.makeText(getApplicationContext(), hosteldata, Toast.LENGTH_LONG).show();
                startActivity(i);

            }
        });

    }

}
