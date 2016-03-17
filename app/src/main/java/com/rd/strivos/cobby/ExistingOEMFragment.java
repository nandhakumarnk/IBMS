package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;

public class ExistingOEMFragment extends AppCompatActivity {

	Button _photoVcardFront, photoVcardback, photoMetPerson, _MorePerson,
			_ProductInfo, _GrievanceInfo, _PaymentInfo,
			_StatutoryDocumentsInfo, _MaterialsReturnInfo, _Save, _Clear;

	public static int count = 0;
	AlertDialog.Builder builder_verify;
	public static String foto, foto2, foto3, employeeID;

	String CoEmployee, TypeOfCall, CallDateTime, getVisitTypeID, getOutcomeID,
			getActionCoburgID, getACCOUNTNAME, getPINCODE, getSTD,getPHONE, getMOBILE,
			getWEBSITE, getEMAIL, getDISTRICT, getDistrictID,GetAddress;

	private EditText et_companyname, et_fullAddress, et_districts, et_Pincode,
			et_FDAPhone, et_FDATelephoneSTD, et_FDATelephone, et_FDAEmail, et_Website, et_remarks;

	Spinner spnAnyOtherMeet, spnVisitType, spnOutcome, spnActionCoburg;

	LinearLayout lay_ProductInfo, lay_GrievanceInfo, lay_PaymentInfo,
			lay_StatutoryDocumentsInfo, lay_MaterialsReturnInfo;

	Vector<String> vecVisitType, vecOutcome, vecBycoburg;

	List<String> lsVisitType = new ArrayList<String>();
	List<String> lsOutcome = new ArrayList<String>();
	List<String> lsBycoburg = new ArrayList<String>();
	ArrayAdapter<String> adptVisitType, adptOutcome, adptBycoburg;

	SQLiteHelper db;
	Boolean check = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_existing_oem);

		findViewById();
		callMethod();

		Bundle b = getIntent().getExtras();
		CoEmployee = b.getString("CoEmployee");
		TypeOfCall = b.getString("TypeOfCall");
		CallDateTime = b.getString("CallDateTime");
		getACCOUNTNAME = b.getString("ACCOUNTNAME");
		getPINCODE = b.getString("PINCODE");
		getSTD = b.getString("STD");
		getPHONE = b.getString("PHONE");
		getMOBILE = b.getString("MOBILE");
		getWEBSITE = b.getString("WEBSITE");
		getEMAIL = b.getString("EMAIL");
		getDISTRICT = b.getString("DISTRICT");
		GetAddress = b.getString("Address");

		et_companyname.setText(getACCOUNTNAME);
		et_Pincode.setText(getPINCODE);
		et_FDATelephoneSTD.setText(getSTD);
		et_FDATelephone.setText(getPHONE);
		et_FDAPhone.setText(getMOBILE);
		et_Website.setText(getWEBSITE);
		et_FDAEmail.setText(getEMAIL);
		et_districts.setText(getDISTRICT);
		et_fullAddress.setText(GetAddress);

		_photoVcardFront.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String set_string = "\nSide of VCard Front";
				builder_verify
						.setMessage("Verify" + set_string)
						.setCancelable(true)
						.setPositiveButton("Yes, Proceed",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id) {
										count = 1;

										Intent in = new Intent(
												ExistingOEMFragment.this,
												Cam_offline.class);
										startActivity(in);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int which) {
										dialog.cancel();
									}
								});
				AlertDialog verify_alert = builder_verify.create();
				builder_verify.show();
			}
		});

		photoVcardback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String set_string = "\nSide of VCard Back";
				builder_verify
						.setMessage("Verify" + set_string)
						.setCancelable(true)
						.setPositiveButton("Yes, Proceed",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id) {
										count = 1;

										Intent in = new Intent(
												ExistingOEMFragment.this,
												Cam_offline_VCardBack.class);
										startActivity(in);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int which) {
										dialog.cancel();
									}
								});
				AlertDialog verify_alert = builder_verify.create();
				builder_verify.show();
			}
		});

		photoMetPerson.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String set_string = "\nThe Person who you meet";
				builder_verify
						.setMessage("Verify" + set_string)
						.setCancelable(true)
						.setPositiveButton("Yes, Proceed",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int id) {
										count = 1;

										Intent in = new Intent(
												ExistingOEMFragment.this,
												Cam_offline_PersonMeet.class);
										startActivity(in);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
														int which) {
										dialog.cancel();
									}
								});
				AlertDialog verify_alert = builder_verify.create();
				builder_verify.show();
			}
		});

		_MorePerson.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String CompanyName = et_companyname.getText().toString();
				String Project = "Customer";
				if (CompanyName.length() != 0) {
					et_companyname.setEnabled(false);
					Intent addPerson = new Intent(getApplicationContext(),
							AddMorePerson.class);
					addPerson.putExtra("Key_name", CompanyName);
					addPerson.putExtra("Key_project", Project);
					startActivity(addPerson);
				} else {
					et_companyname.setEnabled(true);
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill company name", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		_ProductInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String CompanyName = et_companyname.getText().toString();
				String Project = "XOEM";
				if (CompanyName.length() != 0) {
					et_companyname.setEnabled(false);
					Intent addPerson = new Intent(getApplicationContext(),
							AddProductInfoCustomer.class);
					addPerson.putExtra("Key_name", CompanyName);
					addPerson.putExtra("Key_project", Project);
					startActivity(addPerson);
				} else {
					et_companyname.setEnabled(true);
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill company name", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		_GrievanceInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String CompanyName = et_companyname.getText().toString();
				String Project = "XOEM";
				if (CompanyName.length() != 0) {
					et_companyname.setEnabled(false);
					Intent addPerson = new Intent(getApplicationContext(),
							AddGrievanceInfoCustomer.class);
					addPerson.putExtra("Key_name", CompanyName);
					addPerson.putExtra("Key_project", Project);
					startActivity(addPerson);
				} else {
					et_companyname.setEnabled(true);
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill company name", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		_PaymentInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String CompanyName = et_companyname.getText().toString();
				String Project = "XOEM";
				if (CompanyName.length() != 0) {
					et_companyname.setEnabled(false);
					Intent addPerson = new Intent(getApplicationContext(),
							AddPaymentInfoCustomer.class);
					addPerson.putExtra("Key_name", CompanyName);
					addPerson.putExtra("Key_project", Project);
					startActivity(addPerson);
				} else {
					et_companyname.setEnabled(true);
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill company name", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		_StatutoryDocumentsInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String CompanyName = et_companyname.getText().toString();
				String Project = "XOEM";
				if (CompanyName.length() != 0) {
					et_companyname.setEnabled(false);
					Intent addPerson = new Intent(getApplicationContext(),
							AddStatutoryDocumentsInfoCustomer.class);
					addPerson.putExtra("Key_name", CompanyName);
					addPerson.putExtra("Key_project", Project);
					startActivity(addPerson);
				} else {
					et_companyname.setEnabled(true);
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill company name", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		_MaterialsReturnInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String CompanyName = et_companyname.getText().toString();
				String Project = "XOEM";
				if (CompanyName.length() != 0) {
					et_companyname.setEnabled(false);
					Intent addPerson = new Intent(getApplicationContext(),
							AddMaterialReturnInfoCustomer.class);
					addPerson.putExtra("Key_name", CompanyName);
					addPerson.putExtra("Key_project", Project);
					startActivity(addPerson);
				} else {
					et_companyname.setEnabled(true);
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill company name", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		spnAnyOtherMeet.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int arg2, long arg3) {
				String AnyOtherMeet = arg0.getItemAtPosition(arg2).toString();

				if (AnyOtherMeet.equalsIgnoreCase("Yes")) {
					_MorePerson.setVisibility(View.VISIBLE);
				} else {
					_MorePerson.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		spnVisitType.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int arg2, long arg3) {
				String VisitType = arg0.getItemAtPosition(arg2).toString();
				chooseVisitType(VisitType);
				getVisitTypeID(VisitType);
				VisitTypeID(VisitType);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		spnOutcome.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int arg2, long arg3) {
				String outcome = arg0.getItemAtPosition(arg2).toString();
				OutcomeID(outcome);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		spnActionCoburg.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int arg2, long arg3) {
				String bycoburg = arg0.getItemAtPosition(arg2).toString();
				ActionCoburgID(bycoburg);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		_Save.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkStatus();
				if (check == true) {
					InsertCustomer();
					clearAll();
					finish();
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Saved", Toast.LENGTH_SHORT);
					toast.show();
				} else {
					Toast toast = Toast.makeText(ExistingOEMFragment.this,
							"Please fill every fields", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});

		_Clear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				clearAll();
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// app icon in action bar clicked; goto parent activity.
				this.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void callMethod() {
		LoadVisitiTypeSpin();
		LoadCustomerByCobSpin();
	}

	private void InsertCustomer() {
		String companyname, fullAddress, districts, Pincode, FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website, AnyOtherMeet, VisitType, Outcome, ActionCoburg, remarks, UpStatus, CreatedBy, CreatedOn, FotoVCardFront, FotoVCardBack, FotoPerson;

		companyname = et_companyname.getText().toString();
		fullAddress = et_fullAddress.getText().toString();
		districts = et_districts.getText().toString();
		Pincode = et_Pincode.getText().toString();
		FDAPhone = et_FDAPhone.getText().toString();
		FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
		FDATelephone = et_FDATelephone.getText().toString();
		FDAEmail = et_FDAEmail.getText().toString();
		Website = et_Website.getText().toString();
		FotoVCardFront = foto;
		FotoVCardBack = foto2;
		FotoPerson = foto3;
		AnyOtherMeet = spnAnyOtherMeet.getSelectedItem().toString();
		VisitType = spnVisitType.getSelectedItem().toString();
		Outcome = spnOutcome.getSelectedItem().toString();
		ActionCoburg = spnActionCoburg.getSelectedItem().toString();
		remarks = et_remarks.getText().toString();
		UpStatus = "N";
		CreatedBy = employeeID;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDateandTime = sdf.format(new Date());
		CreatedOn = currentDateandTime;

		DistrictID(districts);

		db.insertXOEM(companyname, fullAddress, getDistrictID, Pincode,
				FDAPhone, FDATelephoneSTD,FDATelephone, FDAEmail, Website, FotoVCardFront,
				FotoVCardBack, FotoPerson, AnyOtherMeet, getVisitTypeID,
				getOutcomeID, getActionCoburgID, remarks, UpStatus, CoEmployee,
				TypeOfCall, CallDateTime, "Old", CreatedBy, CreatedOn);

	}

	private void ActionCoburgID(String type) {

		try {
			Cursor c = db.getCustomerByCob(type);
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {

							getActionCoburgID = c.getString(0);
						}
					}
				}
			}
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error Cob ID", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
	}

	private void OutcomeID(String type) {

		try {
			Cursor c = db.getOutcomeCustomerID(type);
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {

							getOutcomeID = c.getString(0);

						}
					}
				}
			}
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error Outcome", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
	}

	private void VisitTypeID(String Type) {

		try {
			Cursor c = db.getVisitTypeIDXOEM(Type);
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {

							getVisitTypeID = c.getString(0);

						}
					}
				}
			}
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error VisitType", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
	}

	private void DistrictID(String Type) {

		try {
			Cursor c = db.getDistrictID(Type);
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {

							getDistrictID = c.getString(0);

						}
					}
				}
			}
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error District", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
	}

	private void clearAll() {
		et_remarks.setText("");
	}

	private void checkStatus() {
		String FDAPhone, FDATelephoneSTD,FDATelephone, FDAEmail, Website, remarks;

		FDAPhone = et_FDAPhone.getText().toString();
		FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
		FDATelephone = et_FDATelephone.getText().toString();
		FDAEmail = et_FDAEmail.getText().toString();
		Website = et_Website.getText().toString();
		remarks = et_remarks.getText().toString();

		if (FDAPhone.length() != 0 & FDATelephone.length() != 0
				& FDAEmail.length() != 0 & Website.length() != 0
				& remarks.length() != 0) {
			if (isValidEmail(FDAEmail) != false) {

				check = true;
			} else {
				Toast toast = Toast.makeText(ExistingOEMFragment.this,
						"Please enter valid email address", Toast.LENGTH_SHORT);
				toast.show();
				check = false;
			}
			if (isValidMobile(FDAPhone) != false) {
				check = true;

			} else {
				check = false;
				Toast toast = Toast.makeText(ExistingOEMFragment.this,
						"Please enter valid phone number", Toast.LENGTH_SHORT);
				toast.show();
			}
		} else {
			check = false;
		}
	}

	private void getVisitTypeID(String Type) {
		String GetVisitTypeID = null;
		try {
			Cursor c = db.VisitTypeIDXOEM(Type);
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {
							GetVisitTypeID = c.getString(0);
						}
					}
				}
			}
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error VisitType 1", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

		OutcomeBasedType(GetVisitTypeID);
	}

	private void OutcomeBasedType(String ID) {

		Vector<String> vecOutcome = new Vector<String>();
		List<String> lsOutcome = new ArrayList<String>();
		ArrayAdapter<String> adptOutcome;
		try {
			Cursor c = db.selectOutcomeCustomerBasedVType(ID);
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {
							while (c.moveToNext()) {
								vecOutcome.add(c.getString(1));
							}
						}
					}
				}
			}

			for (int i = 0; i < vecOutcome.size(); i++) {
				lsOutcome.add(vecOutcome.get(i));
			}

			@SuppressWarnings("rawtypes")
			HashSet hs = new HashSet();
			TreeSet ts = new TreeSet(hs);
			ts.addAll(lsOutcome);
			lsOutcome.clear();
			lsOutcome.addAll(ts);
			db.close();
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error Outcome 1", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

		adptOutcome = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lsOutcome);
		adptOutcome
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnOutcome.setAdapter(adptOutcome);
		spnOutcome.setWillNotDraw(false);
	}

	private void LoadVisitiTypeSpin() {

		try {
			Cursor c = db.selectVisitTypeXOEM();
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {
							while (c.moveToNext()) {
								vecVisitType.add(c.getString(2));
							}
						}
					}
				}
			}

			for (int i = 0; i < vecVisitType.size(); i++) {
				lsVisitType.add(vecVisitType.get(i));
			}

			HashSet hs = new HashSet();
			TreeSet ts = new TreeSet(hs);
			ts.addAll(lsVisitType);
			lsVisitType.clear();
			lsVisitType.addAll(ts);
			db.close();
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error VT Spin", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

		adptVisitType = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lsVisitType);
		adptVisitType
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnVisitType.setAdapter(adptVisitType);
		spnVisitType.setWillNotDraw(false);
	}

	private void LoadCustomerByCobSpin() {

		try {
			Cursor c = db.selectCustomerByCob();
			if (c.getCount() > 0) {
				for (int i = 0; i < c.getCount(); i++) {
					for (int j = 0; j < c.getColumnCount(); j++) {
						if (c.getCount() > 0) {
							while (c.moveToNext()) {
								vecBycoburg.add(c.getString(2));
							}
						}
					}
				}
			}

			for (int i = 0; i < vecBycoburg.size(); i++) {
				lsBycoburg.add(vecBycoburg.get(i));
			}

			HashSet hs = new HashSet();
			TreeSet ts = new TreeSet(hs);
			ts.addAll(lsBycoburg);
			lsBycoburg.clear();
			lsBycoburg.addAll(ts);
			db.close();
		}

		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error Cob Spin", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

		adptBycoburg = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lsBycoburg);
		adptBycoburg
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnActionCoburg.setAdapter(adptBycoburg);
		spnActionCoburg.setWillNotDraw(false);
	}

	private void chooseVisitType(String Type) {
		if (Type.equalsIgnoreCase("NEW REQUIREMENT")) {
			lay_ProductInfo.setVisibility(View.VISIBLE);
			lay_GrievanceInfo.setVisibility(View.GONE);
			lay_PaymentInfo.setVisibility(View.GONE);
			lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
			lay_MaterialsReturnInfo.setVisibility(View.GONE);
		} else if (Type.equalsIgnoreCase("GRIEVANCES")) {
			lay_ProductInfo.setVisibility(View.GONE);
			lay_GrievanceInfo.setVisibility(View.VISIBLE);
			lay_PaymentInfo.setVisibility(View.GONE);
			lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
			lay_MaterialsReturnInfo.setVisibility(View.GONE);
		} else if (Type.equalsIgnoreCase("PAYMENT FOLLOW UP")) {
			lay_ProductInfo.setVisibility(View.GONE);
			lay_GrievanceInfo.setVisibility(View.GONE);
			lay_PaymentInfo.setVisibility(View.VISIBLE);
			lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
			lay_MaterialsReturnInfo.setVisibility(View.GONE);
		} else if (Type.equalsIgnoreCase("STATUTORY DOC FOLLOW UP")) {
			lay_ProductInfo.setVisibility(View.GONE);
			lay_GrievanceInfo.setVisibility(View.GONE);
			lay_PaymentInfo.setVisibility(View.GONE);
			lay_StatutoryDocumentsInfo.setVisibility(View.VISIBLE);
			lay_MaterialsReturnInfo.setVisibility(View.GONE);
		} else if (Type.equalsIgnoreCase("MATERIAL RETURN FOLLOW UP")) {
			lay_ProductInfo.setVisibility(View.GONE);
			lay_GrievanceInfo.setVisibility(View.GONE);
			lay_PaymentInfo.setVisibility(View.GONE);
			lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
			lay_MaterialsReturnInfo.setVisibility(View.VISIBLE);
		} else {
			lay_ProductInfo.setVisibility(View.GONE);
			lay_GrievanceInfo.setVisibility(View.GONE);
			lay_PaymentInfo.setVisibility(View.GONE);
			lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
			lay_MaterialsReturnInfo.setVisibility(View.GONE);
		}
	}

	private final static boolean isValidEmail(CharSequence target) {
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
			} else {
				check = true;
			}
		} else {
			check = false;
		}
		return check;
	}

	private boolean isValidPincode(String pincode) {
		boolean check = false;
		if (!Pattern.matches("[a-zA-Z]+", pincode)) {
			if (pincode.length() != 6) {
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

	private void findViewById() {
		_photoVcardFront = (Button) findViewById(R.id.btn_photovcardfront_xoem);
		photoVcardback = (Button) findViewById(R.id.btn_photovcardback_xoem);
		photoMetPerson = (Button) findViewById(R.id.btn_photometperson_xoem);
		_MorePerson = (Button) findViewById(R.id.btn_moreperson_xoem);
		_ProductInfo = (Button) findViewById(R.id.btn_ProductInfo_xoem);
		_GrievanceInfo = (Button) findViewById(R.id.btn_GrievanceInfo_xoem);
		_PaymentInfo = (Button) findViewById(R.id.btn_PaymentInfo_xoem);
		_StatutoryDocumentsInfo = (Button) findViewById(R.id.btn_StatutoryDocumentsInfo_xoem);
		_MaterialsReturnInfo = (Button) findViewById(R.id.btn_MaterialsReturnInfo_xoem);
		_Save = (Button) findViewById(R.id.btn_save_xoem);
		_Clear = (Button) findViewById(R.id.btn_clear_xoem);
		et_companyname = (EditText) findViewById(R.id.et_companyName_xoem);
		et_fullAddress = (EditText) findViewById(R.id.et_companyfulladd_xoem);
		et_districts = (EditText) findViewById(R.id.et_district_xoem);
		et_Pincode = (EditText) findViewById(R.id.et_companypin_xoem);
		et_FDAPhone = (EditText) findViewById(R.id.et_personmetphone_xoem);
		et_FDATelephoneSTD = (EditText) findViewById(R.id.et_stdcode_xoem);
		et_FDATelephone = (EditText) findViewById(R.id.et_personmettelephone_xoem);
		et_FDAEmail = (EditText) findViewById(R.id.et_personmetemail_xoem);
		et_Website = (EditText) findViewById(R.id.et_personmetwebsite_xoem);
		et_remarks = (EditText) findViewById(R.id.et_remarks_xoem);
		spnAnyOtherMeet = (Spinner) findViewById(R.id.spn_anyotherperson_xoem);
		spnVisitType = (Spinner) findViewById(R.id.spn_visittype_xoem);
		spnOutcome = (Spinner) findViewById(R.id.spn_Outcome_xoem);
		spnActionCoburg = (Spinner) findViewById(R.id.spn_byCoburg_xoem);
		lay_ProductInfo = (LinearLayout) findViewById(R.id.ll_ProductInfo_xoem);
		lay_GrievanceInfo = (LinearLayout) findViewById(R.id.ll_GrievanceInfo_xoem);
		lay_PaymentInfo = (LinearLayout) findViewById(R.id.ll_PaymentInfo_xoem);
		lay_StatutoryDocumentsInfo = (LinearLayout) findViewById(R.id.ll_StatutoryDocumentsInfo_xoem);
		lay_MaterialsReturnInfo = (LinearLayout) findViewById(R.id.ll_MaterialsReturnInfo_xoem);
		db = new SQLiteHelper(getApplicationContext());
		builder_verify = new AlertDialog.Builder(this);
		vecVisitType = new Vector<String>();
		vecBycoburg = new Vector<String>();
	}
}
