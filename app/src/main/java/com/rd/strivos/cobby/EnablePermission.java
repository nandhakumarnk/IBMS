package com.rd.strivos.cobby;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Data Crawl 6 on 24-Jun-16.
 */
public class EnablePermission extends AppCompatActivity {
    private static final String TAG = EnablePermission.class.getName();
    Button _Submit;
    private static final int REQUEST_PERMISSION_All = 111;
    private static String[] PERMISSIONS_ALL = {android.Manifest.permission.READ_CONTACTS,
            android.Manifest.permission.WRITE_CONTACTS, android.Manifest.permission.READ_CALENDAR,
            android.Manifest.permission.WRITE_CALENDAR, android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA,
            Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_permission);

        _Submit = (Button) findViewById(R.id.btn_submit);

        requestAllPermissions();

        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EnablePermission.this,
                        Register.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void requestAllPermissions() {

        boolean requestPermission = PermissionUtil.requestAllPermissions(this);

        if (requestPermission == true) {

            Log.i(TAG,
                    "Displaying contacts permission rationale to provide additional context.");

            // Display a SnackBar with an explanation and a button to trigger the request.
/*            Snackbar.make(mLayout, R.string.permission_contacts_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {*/
            ActivityCompat
                    .requestPermissions(EnablePermission.this, PERMISSIONS_ALL,
                            REQUEST_PERMISSION_All);
                    /*    }
                    })
                    .show();*/

        } else {

            ActivityCompat.requestPermissions(this, PERMISSIONS_ALL, REQUEST_PERMISSION_All);
        }
    }
}
