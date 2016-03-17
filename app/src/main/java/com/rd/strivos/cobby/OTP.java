package com.rd.strivos.cobby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OTP extends AppCompatActivity {

    EditText _OTP;
    Button _Submit;
    String checkOTP = "0";
    SQLiteHelper dbHelper;
    String msg = "This is an automatic message to activate COBBY mobile.", HR = "9791477999", MobileUser, vOTP,Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        _OTP = (EditText) findViewById(R.id.et_otp);
        _Submit = (Button) findViewById(R.id.btn_submitOTP);
        dbHelper = new SQLiteHelper(getApplicationContext());
        checkOTP();
        Bundle b = getIntent().getExtras();
        MobileUser = b.getString("MobileNumber");
        vOTP = b.getString("OTP");
        Status = b.getString("Status");

        _Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int cOTP = 0, OTP = 0;
                cOTP = Integer.parseInt(checkOTP);
                OTP = Integer.parseInt(_OTP.getText().toString().trim());
                if (cOTP == OTP) {
                    sendSMS(MobileUser, msg);
                    sendSMS(HR, msg);
                    Intent i = new Intent(OTP.this, MobileNumberVerification.class);
                    //Intent i = new Intent(OTP.this, SetPINtoLogin.class);
                    i.putExtra("MobileNumber", MobileUser);
                    //i.putExtra("Status",Status);
                    startActivity(i);
                    finish();
                } else {
                    Toast toast = Toast.makeText(OTP.this, "Invalid OTP", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        //App not allowed to go back to Parent activity until correct pin entered.
        return;
        //super.onBackPressed();
    }

    private void sendSMS(String phoneNumber, String message) {
        Log.v("phoneNumber", phoneNumber);
        Log.v("MEssage", message);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }

    private void checkOTP() {
        String _id = "";
        try {
            _id = dbHelper.getOTP();
            checkOTP = _id;

        } catch (Exception ex) {

        }
    }

}
