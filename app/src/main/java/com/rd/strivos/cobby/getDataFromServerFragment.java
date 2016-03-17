package com.rd.strivos.cobby;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class getDataFromServerFragment extends AppCompatActivity {

    Button _getData;
    ImageView img_on_off;
    TextView txt_on_off;
    public static int NETWORK_STATE = 0;
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getdata);

        _getData = (Button) findViewById(R.id.btn_getDataFrmServer);
        img_on_off = ((ImageView) findViewById(R.id.image_on_off));
        txt_on_off = (TextView) findViewById(R.id.text_on_off);

        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));

        _getData.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getApplicationContext(),Fetch.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    public BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            NetworkInfo currentNetworkInfo = (NetworkInfo) intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

            if (currentNetworkInfo.isConnected()) {
                img_on_off.setImageResource(R.drawable.d_on);
                txt_on_off.setText("Online");
                txt_on_off.setTextColor(Color.rgb(0, 153, 0));
                NETWORK_STATE = CONNECTED;
                _getData.setEnabled(true);

            } else {

                NETWORK_STATE = NOT_CONNECTED;
                img_on_off.setImageResource(R.drawable.d_off);
                txt_on_off.setText("Offline");
                txt_on_off.setTextColor(Color.RED);
                _getData.setEnabled(false);
            }
        }
    };
}
