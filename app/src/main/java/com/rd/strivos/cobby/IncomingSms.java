package com.rd.strivos.cobby;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;


public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {
        SharedPreferences settings = context.getSharedPreferences("myPrefs",
                Context.MODE_PRIVATE);
        SQLiteHelper dbHelper = new SQLiteHelper(context.getApplicationContext());
        String mobile = settings.getString("mobileno", "");

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);
                    String _id = "";//, status = "", pin = "";
                    String mobileno = "";//, Status = "", Pin = "";
                    try {
                        _id = dbHelper.getMobileNo();
                        mobileno = _id;

//                        status = dbHelper.getStatus();
//                        Status = status;
//
//                        pin = dbHelper.getPin();
//                        Pin = pin;

                    } catch (Exception ex) {

                    }
                    if (senderNum.equalsIgnoreCase(mobileno)) {
                        dbHelper.flag_insert("Next");
                    }
//                    if (Status.equalsIgnoreCase("OK")) {
//                        if (senderNum.equalsIgnoreCase(mobileno)) {
//                            Intent ok = new Intent(context, SetPINtoLogin.class);
//                            ok.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            ok.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            context.startActivity(ok);
//                            dbHelper.pin_insert("Next");
//                        }
//                    } else {
//                        if (senderNum.equalsIgnoreCase(mobileno)) {
//                            dbHelper.pin_insert(Pin);
//                            Intent ok = new Intent(context, PinEntryView.class);
//                            ok.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                            ok.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            context.startActivity(ok);
//                        }
//                    }
                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }
}