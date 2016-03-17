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

public class AddPresentProject extends Activity {

	private EditText edtPROJECTNAME, edtINDUSTRY, edtAPPLICATION,
			edtPRODUCTREQ;
	private Button btnSave, btnClear;
	private ListView lstView;
	private ListAdapter listAdaptor;
	SQLiteHelper db;
	String name, project;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addpresentproject);
		edtPROJECTNAME = (EditText) findViewById(R.id.edtPROJECTNAME_present);
		edtINDUSTRY = (EditText) findViewById(R.id.edtINDUSTRY_present);
		edtAPPLICATION = (EditText) findViewById(R.id.edtAPPLICATION_present);
		edtPRODUCTREQ = (EditText) findViewById(R.id.edtPRODUCTREQ_present);
		btnSave = (Button) findViewById(R.id.add_present);
		btnClear = (Button) findViewById(R.id.remove_present);
		lstView = (ListView) findViewById(R.id.lst_present);
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
				String PROJECTNAME = edtPROJECTNAME.getText().toString();
				String INDUSTRY = edtINDUSTRY.getText().toString();
				String APPLICATION = edtAPPLICATION.getText().toString();
				String PRODUCTREQ = edtPRODUCTREQ.getText().toString();
				String ProjectType = project;
				String Status = "N";

				if (PROJECTNAME.length() != 0 & INDUSTRY.length() != 0
						& APPLICATION.length() != 0 & PRODUCTREQ.length() != 0) {

					db.insertPresentProject(ProjectID, PROJECTNAME, INDUSTRY,
							APPLICATION, PRODUCTREQ, ProjectType, Status);
					clearAll();
					Toast toast = Toast.makeText(AddPresentProject.this,
							"Saved", Toast.LENGTH_SHORT);
					toast.show();

				} else {
					Toast toast = Toast.makeText(AddPresentProject.this,
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

	private void clearAll() {
		edtPROJECTNAME.setText("");
		edtINDUSTRY.setText("");
		edtAPPLICATION.setText("");
		edtPRODUCTREQ.setText("");
	}

	private void refreshList() {

		ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
				R.layout.text_view);
		String PROJECTNAME, INDUSTRY, APPLICATION, PRODUCTREQ;
		try {
			Cursor c = db.selectPresentProject();
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {
							while (c.moveToNext()) {
								PROJECTNAME = c.getString(1);
								INDUSTRY = c.getString(2);
								APPLICATION = c.getString(3);
								PRODUCTREQ = c.getString(4);
								listAdaptor.add(PROJECTNAME + " - " + INDUSTRY
										+ "-" + APPLICATION + "-" + PRODUCTREQ);
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
