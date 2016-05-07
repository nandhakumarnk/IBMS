package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {

    public static int NETWORK_STATE = 0, LOCATION_STATE = 0;
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 2;
    Button btn_login;
    SQLiteHelper db;
    String getSimSerialNumber,SIMSerial;

    //new
    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    // flag for GPS status
    boolean canGetLocation = false;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    // MY_PREFS_NAME - a static String variable like:
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn_login = (Button) findViewById(R.id.btn_login);
        db = new SQLiteHelper(getApplicationContext());
        //turnGPSOn();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        getSimSerialNumber = telephonyManager.getSimSerialNumber();

        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this,
                LoginForm.class));

        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        getUSerDtls();
    }

    private void getUSerDtls() {

        try {
            Cursor c = db.GetLoginUserDtls();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        //employeeName = c.getString(1);
                        //Log.i("sdv", employeeName);
                        editor.putString("UserNAME", c.getString(0));
                        editor.putString("UserMOBILE", c.getString(1));
                        editor.putString("UserEMPLOYEEID", c.getString(2));
                        editor.putString("UserDESIGNATION", c.getString(3));
                        editor.putString("UserEMPLOYEE_ID", c.getString(4));
                        editor.putString("UserDEPARTMENT", c.getString(5));
                        editor.putString("UserDEPARTMENT_ID", c.getString(6));
                        editor.putString("UserDESIGNATION_ID", c.getString(7));
                        editor.putString("UserEMAIL", c.getString(8));
                        editor.putString("UserPINNO", c.getString(9));
                        editor.putString("UserCOMPANYM_ID", c.getString(10));
                        editor.putString("UserDOB", c.getString(11));
                        editor.putString("UserSIM", c.getString(12));
                        SIMSerial = c.getString(12);
                        editor.putString("UserOTP", c.getString(13));
                        editor.putString("UserSTATUS", c.getString(14));
                        editor.commit();
                    } while (c.moveToNext());
                }
            }
            //et_logging.setText(employeeName);
        } catch (Exception ex) {

        }
    }

//    public void turnGPSOn() {
//        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
//        intent.putExtra("enabled", true);
//        LoginForm.this.sendBroadcast(intent);
//
//        String provider = Settings.Secure.getString(LoginForm.this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//        if (!provider.contains("gps")) { //if gps is disabled
//            final Intent poke = new Intent();
//            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//            poke.setData(Uri.parse("3"));
//            LoginForm.this.sendBroadcast(poke);
//
//
//        }
//    }

    public BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            NetworkInfo currentNetworkInfo = (NetworkInfo) intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

            final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            LocationManager mlocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean enabled = mlocManager.isProviderEnabled(locationManager.GPS_PROVIDER);

//            if (currentNetworkInfo.isConnected()) {
//                NETWORK_STATE = CONNECTED;
            //

            btn_login.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
//                        Intent i = new Intent(LoginForm.this, PinEntryView.class);
//                        i.putExtra("register_text", "Register");
//                        startActivity(i);
//                        finish();
                    getCurrentLocation();
                }
            });

//                if (enabled) {
//                    LOCATION_STATE = CONNECTED;
//                    btn_login.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            // TODO Auto-generated method stub
//                            Intent i = new Intent(LoginForm.this, PinEntryView.class);
//                            i.putExtra("register_text", "Register");
//                            startActivity(i);
//                            finish();
//                        }
//                    });
//
//                } else {
//                    LOCATION_STATE = NOT_CONNECTED;
//                    btn_login.setOnClickListener(new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            // TODO Auto-generated method stub
//
//                            showGPSDisabledAlertToUser();
//                        }
//                    });
//
//
//                }

//            } else {
//                NETWORK_STATE = NOT_CONNECTED;
//                btn_login.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        // TODO Auto-generated method stub
//                        Toast.makeText(getApplicationContext(), "No Internet Connection. Your device is currently not connected to the internet, please try again!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
        }
    };

    public void getCurrentLocation() {
        latitude = 0.0;
        longitude = 0.0;
        GPSTracker gps = new GPSTracker(LoginForm.this);
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            //
            //launch new activity when location is detected.
            //
            //nextActivity();
            //                            Intent i = new Intent(LoginForm.this, PinEntryView.class);
            if (getSimSerialNumber.contentEquals(SIMSerial)) {
                Intent i = new Intent(LoginForm.this, PinEntryView.class);
                i.putExtra("register_text", "Register");
                startActivity(i);
                finish();
            }

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
