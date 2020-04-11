package com.e.hob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentActivity extends Activity implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();

    TextView  hostelname , payment;
    Button button;
    String pay , hostel, paymentid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("Payment");

        hostelname = findViewById(R.id.hostelname);
        payment = findViewById(R.id.payment);
        button = findViewById(R.id.btn_pay);

        Intent intent = this.getIntent();
        pay = intent.getExtras().getString("pay");
        hostel = intent.getExtras().getString("hostelname");

        hostelname.setText(hostel);
        payment.setText(pay);

        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", hostel);
            options.put("description", "Welcome to our Home .. ");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            //options.put("amount", (new Integer(pay)*100));
            options.put("amount", (new Integer(pay)*100));

            JSONObject preFill = new JSONObject();
            preFill.put("email", "2310patelchintan@gmail.com");
            preFill.put("contact", "9106571351");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();


            /*paymentid = razorpayPaymentID;
            Intent i = new Intent(getApplicationContext(),PaymentSuccess.class);
            i.putExtra("payment_id",razorpayPaymentID);
            i.putExtra("hostelname",hostel);
            i.putExtra("pay",pay);
            i.putExtra("phoneno","9106572351");
            i.putExtra("email","2310patelchintan@gmail.com" );
            startActivity(i);*/


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {

            //Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
}
