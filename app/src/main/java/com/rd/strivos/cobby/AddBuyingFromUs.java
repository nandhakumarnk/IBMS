package com.rd.strivos.cobby;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddBuyingFromUs extends Activity {

	private EditText edtPRODUCT, edtQTY, edtFINALPRICE, edtDELIVERY, edtFREIGHT, edtPAYMENT;
	private Button btnSave, btnClear;
	private ListView lstView;
	private ListAdapter listAdaptor;
	SQLiteHelper db;
	String name,project;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbuyingfromus);
		edtPRODUCT = (EditText) findViewById(R.id.edtPRODUCT_byus);
		edtQTY = (EditText) findViewById(R.id.edtQTY_byus);
		edtFINALPRICE = (EditText) findViewById(R.id.edtFINALPRICE_byus);
		edtDELIVERY = (EditText) findViewById(R.id.edtDELIVERY_byus);
		edtFREIGHT = (EditText) findViewById(R.id.edtFREIGHT_byus);
		edtPAYMENT = (EditText) findViewById(R.id.edtPAYMENT_byus);
		btnSave = (Button) findViewById(R.id.add_byus);
		btnClear = (Button) findViewById(R.id.remove_byus);
		lstView = (ListView) findViewById(R.id.lst_byus);
		listAdaptor = lstView.getAdapter();
		db = new SQLiteHelper(getApplicationContext());
		Bundle b = getIntent().getExtras();
		name = b.getString("Key_name");
		project = b.getString("Key_project");

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String ProjectID = name;
				String PRODUCT = edtPRODUCT.getText().toString();
				String QTY = edtQTY.getText().toString();
				String FINALPRICE = edtFINALPRICE.getText().toString();
				String DELIVERY = edtDELIVERY.getText().toString();
				String FREIGHT = edtFREIGHT.getText().toString();
				String PAYMENT = edtPAYMENT.getText().toString();				
				String ProjectType = project;
				String Status = "N";

				if (PRODUCT.length() != 0 & QTY.length() != 0
						& FINALPRICE.length() != 0 & DELIVERY.length() != 0 & FREIGHT.length() != 0 & PAYMENT.length() != 0) {

					
							db.insertBuyingFromUs(ProjectID, PRODUCT, QTY, FINALPRICE, DELIVERY,
									FREIGHT,PAYMENT,ProjectType, Status);
							clearAll();
							Toast toast = Toast.makeText(AddBuyingFromUs.this,
									"Saved",
									Toast.LENGTH_SHORT);
							toast.show();
						

				} else {
					Toast toast = Toast.makeText(AddBuyingFromUs.this,
							"Please fill all fileds.", Toast.LENGTH_SHORT);
					toast.show();
				}

				refreshList();
			}
		});

		btnClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearAll();
			}
		});

	}
	
	private void clearAll()
	{		
		edtPRODUCT.setText("");
		edtQTY.setText("");
		edtFINALPRICE.setText("");
		edtDELIVERY.setText("");
		edtFREIGHT.setText("");
		edtPAYMENT.setText("");
		
	}

	private void refreshList() {

		ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
				R.layout.text_view);
		String PRODUCT, QTY, FINALPRICE, DELIVERY;
		try {
			Cursor c = db.selectBuyingFromUs();
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {
							while (c.moveToNext()) {
								PRODUCT = c.getString(1);
								QTY = c.getString(2);
								FINALPRICE = c.getString(3);
								DELIVERY = c.getString(4);
								listAdaptor.add(PRODUCT + " - " + QTY
										+ "-" + FINALPRICE + "-" + DELIVERY);
							}
						}
					}
				}
			}

			lstView.setAdapter(listAdaptor);
			db.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

	}

	public final static boolean isValidEmail(CharSequence target) {
		if (target == null) {
			return false;
		} else {
			return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
					.matches();
		}
	}

	private boolean isValidMobile(String phone2) {
		boolean check = false;
		if (!Pattern.matches("[a-zA-Z]+", phone2)) {
			if (phone2.length() < 10 || phone2.length() > 13) {
				check = false;
				// txtPhone.setError("Not Valid Number");
			} else {
				check = true;
			}
		} else {
			check = false;
		}
		return check;
	}

}
