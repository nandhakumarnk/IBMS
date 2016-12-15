package com.rd.strivos.cobby;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


// Restarted @ 08.24 AM 16-10-2016

public class Splash extends Activity {
    private static final String TAG = LoginForm.class.getName();
    private static int SPLASH_TIME_OUT = 3000;
    int userPinCount;
    private SQLiteHelper dbHelper;
    FirstTimePreference prefFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        prefFirstTime = new FirstTimePreference(getApplicationContext());

        dbHelper = new SQLiteHelper(getApplicationContext());
        checkRegister();

        try {
            new Handler().postDelayed(new Runnable() {
                /*
                 * Showing splash screen with a timer. This will be useful when
                 * you want to show case your app logo / company
                 */
                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    try {
                        synchronized (this) {
                            // Wait given period of time or exit on touch
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    // TODO Auto-generated method stub
                                    try {
                                        //checkRegister();
                                        Thread.sleep(0);
                                    } catch (InterruptedException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                            wait(1000);
                        }
                    } catch (InterruptedException ex) {
                    }

                    finish();
                    if (userPinCount >= 1) {
                        Intent i = new Intent(Splash.this,
                                LoginForm.class);
                        startActivity(i);
                    } else {

                        if (prefFirstTime.runTheFirstTime("FirstTimePermit")) {
                            if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                                Intent i = new Intent(Splash.this,
                                        EnablePermission.class);
                                startActivity(i);
                            } else {
                                Intent i = new Intent(Splash.this,
                                        Register.class);
                                startActivity(i);
                            }
                        } else {
                            Intent i = new Intent(Splash.this,
                                    Register.class);
                            startActivity(i);
                        }
                    }
                }
            }, SPLASH_TIME_OUT);
        } catch (OutOfMemoryError e) {
            Toast.makeText(
                    getApplicationContext(),
                    "Your phone memory is too low, free your cache memory and start the application",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void checkRegister() {
        try {
            Cursor c = dbHelper.isRegister();
            userPinCount = c.getCount();

        } catch (Exception ex) {

        }
    }
}
