package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class ExistingConsultantFragment extends AppCompatActivity {

    public static int count = 0;
    AlertDialog.Builder builder_verify;
    public static String foto, foto2, foto3, employeeID;

    String getDistrictID, CoEmployee, TypeOfCall, CallDateTime,
            getActionCoburgID, getACCOUNTNAME, getPINCODE, getPHONE, getSTD, getMOBILE, getWEBSITE,
            getEMAIL, getDISTRICT, getVisitTypeID, GetAddress;

    private EditText et_companyname, et_fullAddress, et_districts,
            et_Pincode, et_FDATelephoneSTD, et_FDATelephone, et_FDAPhone, et_FDAEmail,
            et_Website, et_KeyPointsDiscussed;

    Button _photoVcardFront, photoVcardback, photoMetPerson, _Save, _Clear,
            _AddMorePerson, _AddPresentProject, _AddGrievanceInfo, _AddNewProjects;

    Spinner spnAnyOtherMeet, spnVisitType, spnActionCoburg;

    LinearLayout lay_NewProductInfo, lay_GrievanceInfo, lay_PresentProjectInfo;

    Vector<String> vecVisitType, vecBycoburgDealer;

    List<String> lsVisitType = new ArrayList<String>();
    List<String> lsBycoburgDealer = new ArrayList<String>();
    ArrayAdapter<String> adptVisitType, adptBycoburgDealer;

    SQLiteHelper db;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_existing_consultant);

        findViewById();
        callMethod();

        _AddPresentProject.setEnabled(false);

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
                String set_string = "Side of Business Card Front\nDo you want to proceed?";
                builder_verify
                        .setMessage(set_string)
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        count = 1;

                                        Intent in = new Intent(
                                                ExistingConsultantFragment.this,
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
                String set_string = "Side of VCard Back\nDo you want to proceed?";
                builder_verify
                        .setMessage(set_string)
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        count = 1;

                                        Intent in = new Intent(
                                                ExistingConsultantFragment.this,
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
                String set_string = "The Person who you meet\nDo you want to proceed?";
                builder_verify
                        .setMessage(set_string)
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        count = 1;

                                        Intent in = new Intent(
                                                ExistingConsultantFragment.this,
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

        _AddMorePerson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "Dealer";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddMorePerson.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(ExistingConsultantFragment.this,
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
                    _AddMorePerson.setVisibility(View.VISIBLE);
                } else {
                    _AddMorePerson.setVisibility(View.GONE);
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
                VisitTypeID(VisitType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        _AddPresentProject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String CompanyName = et_companyname.getText().toString();
                String Project = "XConsultancy";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddProductsRequired.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(ExistingConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddGrievanceInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String CompanyName = et_companyname.getText().toString();
                String Project = "XConsultancy";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddGrievanceInfoCustomer.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(ExistingConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddNewProjects.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String CompanyName = et_companyname.getText().toString();
                String Project = "XConsultancy";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            NewProjectXConsultancy.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(ExistingConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        spnActionCoburg.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Outcome = arg0.getItemAtPosition(arg2).toString();
                ActionCoburgID(Outcome);
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
                    InsertConsultancy();
                    clearAll();
                    finish();
                    Toast toast = Toast.makeText(ExistingConsultantFragment.this,
                            "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(ExistingConsultantFragment.this,
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
        EmployeeID();
        LoadVisitTypeSpin();
        loadByCoburgConsultancySpin();
    }

    private void VisitTypeID(String Type) {

        try {
            Cursor c = db.getVisitTypeID(Type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getVisitTypeID = c.getString(0);

                        }
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error VisitType", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void LoadVisitTypeSpin() {

        try {
            Cursor c = db.selectVisitTypeXConsult();
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
        } catch (Exception e) {
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

    private void chooseVisitType(String Type) {
        if (Type.equalsIgnoreCase("GRIEVANCES")) {
            lay_GrievanceInfo.setVisibility(View.VISIBLE);
            lay_NewProductInfo.setVisibility(View.GONE);
            lay_PresentProjectInfo.setVisibility(View.GONE);
        } else if (Type.equalsIgnoreCase("NEW PROJECTS")) {
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_NewProductInfo.setVisibility(View.VISIBLE);
            lay_PresentProjectInfo.setVisibility(View.GONE);
        } else if (Type.equalsIgnoreCase("PRESENT PROJECTS")) {
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_NewProductInfo.setVisibility(View.GONE);
            lay_PresentProjectInfo.setVisibility(View.VISIBLE);
        }
    }


    private void InsertConsultancy() {
        String companyname, fullAddress, District, Pincode, FDATelephoneSTD, FDATelephone, FDAPhone, FDAEmail, Website, FotoVCardFront,
                FotoVCardBack, FotoPerson, AnyOtherMeet, VisitType, KeyPoints, ActionCoburg, UpStatus, CreatedBy, CreatedOn;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();
        District = et_districts.getText().toString();
        Pincode = et_Pincode.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();
        FotoVCardFront = foto;
        FotoVCardBack = foto2;
        FotoPerson = foto3;
        AnyOtherMeet = spnAnyOtherMeet.getSelectedItem().toString();
        VisitType = spnVisitType.getSelectedItem().toString();
        KeyPoints = et_KeyPointsDiscussed.getText().toString();

        UpStatus = "N";
        CreatedBy = employeeID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        CreatedOn = currentDateandTime;

        DistrictID(District);

        db.insertOldConsultancy(companyname, fullAddress, getDistrictID, Pincode, FDATelephoneSTD, FDATelephone, FDAPhone, FDAEmail,
                Website, FotoVCardFront, FotoVCardBack, FotoPerson, AnyOtherMeet, getVisitTypeID, KeyPoints,
                getActionCoburgID, UpStatus, CoEmployee, TypeOfCall, CallDateTime, "Old", CreatedBy, CreatedOn);

    }

    private void checkStatus() {

        String FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website,
                ProductsTheyWish, ReasonsForNot, TheyNotReadyToRecommend, KeyPoints, ReadyToRecommend;


        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();
        KeyPoints = et_KeyPointsDiscussed.getText().toString();

        if (FDAPhone.length() != 0 & FDATelephoneSTD.length() != 0
                & FDATelephone.length() != 0 & FDAEmail.length() != 0
                & Website.length() != 0 & KeyPoints.length() != 0) {

            if (isValidEmail(FDAEmail) != false) {

                check = true;
            } else {
                Toast toast = Toast.makeText(ExistingConsultantFragment.this,
                        "Please enter valid email address", Toast.LENGTH_SHORT);
                toast.show();
                check = false;
            }
            if (isValidMobile(FDAPhone) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(ExistingConsultantFragment.this,
                        "Please enter valid phone number", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else {

            check = false;
        }
    }

    private void EmployeeID() {
        try {
            Cursor c = db.SelectUser();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                employeeID = c.getString(0);
                                Log.i("sdv", employeeID);

                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {

        }
    }

    private void loadByCoburgConsultancySpin() {

        try {
            Cursor c = db.selectCustomerByCob();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecBycoburgDealer.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecBycoburgDealer.size(); i++) {
                lsBycoburgDealer.add(vecBycoburgDealer.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsBycoburgDealer);
            lsBycoburgDealer.clear();
            lsBycoburgDealer.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        adptBycoburgDealer = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsBycoburgDealer);
        adptBycoburgDealer
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnActionCoburg.setAdapter(adptBycoburgDealer);
        spnActionCoburg.setWillNotDraw(false);

    }

    private void ActionCoburgID(String type) {

        try {
            Cursor c = db.getByCoburgConsultancyID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getActionCoburgID = c.getString(0);

                        }
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
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

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void clearAll() {
        et_KeyPointsDiscussed.setText("");
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
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

    private void findViewById() {

        et_companyname = (EditText) findViewById(R.id.et_companyName_xconsult);
        et_fullAddress = (EditText) findViewById(R.id.et_companyfulladd_xconsult);
        et_districts = (EditText) findViewById(R.id.et_district_xconsult);
        et_Pincode = (EditText) findViewById(R.id.et_companypin_xconsult);
        et_FDATelephoneSTD = (EditText) findViewById(R.id.et_stdcode_xconsult);
        et_FDATelephone = (EditText) findViewById(R.id.et_personmettelephone_xconsult);
        et_FDAPhone = (EditText) findViewById(R.id.et_personmetphone_xconsult);
        et_FDAEmail = (EditText) findViewById(R.id.et_personmetemail_xconsult);
        et_Website = (EditText) findViewById(R.id.et_personmetwebsite_xconsult);
        _photoVcardFront = (Button) findViewById(R.id.btn_photovcardfront_xconsult);
        photoVcardback = (Button) findViewById(R.id.btn_photovcardback_xconsult);
        photoMetPerson = (Button) findViewById(R.id.btn_photometperson_xconsult);
        spnAnyOtherMeet = (Spinner) findViewById(R.id.spn_anyotherperson_xconsult);
        _AddMorePerson = (Button) findViewById(R.id.btn_moreperson_xconsult);
        spnVisitType = (Spinner) findViewById(R.id.spn_visittype_xconsult);
        lay_NewProductInfo = (LinearLayout) findViewById(R.id.ll_NewProductInfo_xconsult);
        _AddNewProjects = (Button) findViewById(R.id.btn_NewProductInfo_xconsult);
        lay_GrievanceInfo = (LinearLayout) findViewById(R.id.ll_GrievanceInfo_xconsult);
        _AddGrievanceInfo = (Button) findViewById(R.id.btn_GrievanceInfo_xconsult);
        lay_PresentProjectInfo = (LinearLayout) findViewById(R.id.ll_PresentProjectInfo_xconsult);
        _AddPresentProject = (Button) findViewById(R.id.btn_PresentProjectInfo_xconsult);
        et_KeyPointsDiscussed = (EditText) findViewById(R.id.et_KeyPointsDiscussed_xconsult);
        spnActionCoburg = (Spinner) findViewById(R.id.spn_byCoburg_xconsult);
        _Save = (Button) findViewById(R.id.btn_save_xconsult);
        _Clear = (Button) findViewById(R.id.btn_clear_xconsult);

        db = new SQLiteHelper(getApplicationContext());
        builder_verify = new AlertDialog.Builder(this);

        vecVisitType = new Vector<String>();
        vecBycoburgDealer = new Vector<String>();
    }
}
