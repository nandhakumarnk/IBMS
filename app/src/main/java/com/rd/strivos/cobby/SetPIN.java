package com.rd.strivos.cobby;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rd.strivos.cobby.SQLiteHelper;

public class SetPIN extends AppCompatActivity {
	
	EditText _pin, _cpin;
	Button _Submit;
	SQLiteHelper dbHelper;
	String result;
	String IMEINo;
	String Model, empID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pin);
		
		_pin = (EditText)findViewById(R.id.et_pin);
		_cpin = (EditText)findViewById(R.id.et_cpin);
		_Submit = (Button)findViewById(R.id.btn_submit);
		dbHelper = new SQLiteHelper(getApplicationContext());
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		IMEINo = telephonyManager.getDeviceId();
		Model = android.os.Build.MODEL;

		checkEmplyoeeID();
		
		_Submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String pin = _pin.getText().toString().trim();
				String cpin = _cpin.getText().toString().trim();
				int a=Integer.parseInt(pin),b=Integer.parseInt(cpin);
				
				
				if(a==b)
				{
					dbHelper.pin_insert(pin);
					
					String qry = "update EMPLOYEEMJOININGDETAILS set MOBILEMODEL='"+Model+"', IMEINO='"+IMEINo+"' , PINNO='"+pin+"' WHERE EMPLOYEEID='"+empID+"'";
					try {
						result = WebServices.select(qry);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					if (result != null && result.length() > 0) {
						
						System.out.println("Result :" +result);
					}
							
							
					Toast toast = Toast.makeText(SetPIN.this,"Login PIN has been set successfully.",Toast.LENGTH_SHORT);					
					toast.show();
					Intent i = new Intent(SetPIN.this, PinEntryView.class);
					startActivity(i);					
					finish();
				}
				else
				{
					Toast toast = Toast.makeText(SetPIN.this,"PIN details don't match. Try again?",Toast.LENGTH_SHORT);					
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

	private void checkEmplyoeeID()
	{
		String _id="";
		try {
			_id = dbHelper.getEmplyoeeID();
			empID = _id;

		} catch (Exception ex) {

		}
	}

}
