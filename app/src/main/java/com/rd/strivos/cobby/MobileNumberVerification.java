package com.rd.strivos.cobby;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by COBURG DESIGN on 15-12-2015.
 */
public class MobileNumberVerification extends AppCompatActivity {
    SQLiteHelper dbHelper;
    private Intent intent;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_number_verify);
        dbHelper = new SQLiteHelper(getApplicationContext());

        loadBar();
        intent = new Intent(this, BroadcastService.class);
    }

    @Override
    public void onBackPressed() {

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        startService(intent);
        registerReceiver(broadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        stopService(intent);
    }

    private void updateUI(Intent intent) {
        String counter = intent.getStringExtra("counter");
        String time = intent.getStringExtra("time");
        String status = "", pin = "", flag = "";
        String Status = "", Pin = "", Flag = "";
        try {
            try {
                status = dbHelper.getStatus();
                Status = status;

                pin = dbHelper.getPin();
                Pin = pin;

                flag = dbHelper.getFlag();
                Flag = flag;

            } catch (Exception ex) {
            }
            if (Flag.equalsIgnoreCase("Next")) {
                if (Status.equalsIgnoreCase("OK")) {
                    this.finish();
                    Intent ok = new Intent(getApplicationContext(), SetPINtoLogin.class);
                    startActivity(ok);
                    progressDialog.dismiss();
                } else {
                    this.finish();
                    dbHelper.pin_insert(Pin);
                    Intent ok = new Intent(getApplicationContext(), PinEntryView.class);
                    startActivity(ok);
                    progressDialog.dismiss();
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }
    }

    private  void loadBar(){
        ProgressDialog progressDialog = ProgressDialog.show(this, "",
                "We are verifying your mobile number, Please wait...");

        //progressDialog.dismiss();
    }
}
