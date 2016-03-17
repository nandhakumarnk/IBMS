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

public class NewConsultantFragment extends AppCompatActivity {

    Button _photoVcardFront, photoVcardback, photoMetPerson, _Save, _Clear,
            _AddMorePerson, _AddKeyProject, _AddProductsRequired,
            _AddPresentProject;
    public static int count = 0;
    AlertDialog.Builder builder_verify;
    public static String foto, foto2, foto3, employeeID;

    String getDistrictID, CoEmployee, TypeOfCall, CallDateTime, getVisitRefID,
            getOutcomeConsultancyID, getActionCoburgID, getIndustryID,
            getProcessID;

    private EditText et_companyname, et_fullAddress,
            et_Pincode, et_FDAPersonMeet,
            etFDAPersonMDesignation, et_FDAPhone, et_FDATelephoneSTD, et_FDATelephone, et_FDAEmail,
            et_Website, et_DealerReffered, et_ProductsTheyWish, et_ReasonsForNot,
            et_TheyNotReadyToRecommend, et_KeyPoints;

    Spinner spnDistrict, spnAnyOtherMeet, spnIndustry, spnProcess,
            spnDealerContact, spnOutcome, spnActionCoburg,
            spnTechnicalPresentation, spnCONSULTANCYconvinced,
            spnReadyToRecommend;

    LinearLayout llYes1, llYes2, llNo1;

    Vector<String> vecDistrict, vecVisitRef, vecOutcomeDealer,
            vecBycoburgDealer, vecIndustry;
    List<String> lsDistrict = new ArrayList<String>();
    List<String> lsVisitRef = new ArrayList<String>();
    List<String> lsOutcomeDealer = new ArrayList<String>();
    List<String> lsBycoburgDealer = new ArrayList<String>();
    List<String> lsIndustry = new ArrayList<String>();
    ArrayAdapter<String> adptDistrict, adptVisitRef, adptOutcomeDealer,
            adptBycoburgDealer, adptIndustry;

    SQLiteHelper db;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_consultant);

        findViewById();
        callMethod();

        Bundle b = getIntent().getExtras();
        CoEmployee = b.getString("CoEmployee");
        TypeOfCall = b.getString("TypeOfCall");
        CallDateTime = b.getString("CallDateTime");

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
                                                NewConsultantFragment.this,
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
                                                NewConsultantFragment.this,
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
                                                NewConsultantFragment.this,
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
                    Toast toast = Toast.makeText(NewConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        spnDistrict.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Outcome = arg0.getItemAtPosition(arg2).toString();
                DistrictID(Outcome);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnIndustry.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String IndustryName = arg0.getItemAtPosition(arg2).toString();
                selectIndustryID(IndustryName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
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

        _AddKeyProject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String CompanyName = et_companyname.getText().toString();
                String Project = "Consultancy";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddKeyProject.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddProductsRequired.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String CompanyName = et_companyname.getText().toString();
                String Project = "Consultancy";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddProductsRequired.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        spnReadyToRecommend
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        String ReadyToRecommend = arg0.getItemAtPosition(arg2)
                                .toString();

                        if (ReadyToRecommend.equalsIgnoreCase("Yes")) {

                            llYes1.setVisibility(View.VISIBLE);
                            llYes2.setVisibility(View.VISIBLE);
                            llNo1.setVisibility(View.GONE);
                        } else {
                            llYes1.setVisibility(View.GONE);
                            llYes2.setVisibility(View.GONE);
                            llNo1.setVisibility(View.VISIBLE);
                        }
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
                String Project = "Consultancy";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddProductsRequired.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewConsultantFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        spnIndustry.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Outcome = arg0.getItemAtPosition(arg2).toString();
                selectIndustryID(Outcome);
                IndustryID(Outcome);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnProcess.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Outcome = arg0.getItemAtPosition(arg2).toString();
                ProcessID(Outcome);
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
                String Outcome = arg0.getItemAtPosition(arg2).toString();
                OutcomeID(Outcome);
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
                    Toast toast = Toast.makeText(NewConsultantFragment.this,
                            "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(NewConsultantFragment.this,
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
        LoadDistrictSpin();
        loadIndustrySpin();
        loadOutcomeConsultancySpin();
        loadByCoburgConsultancySpin();
    }

    private void InsertConsultancy() {
        String companyname, fullAddress, District, Pincode, FDAPersonMeet, FDAPersonMDesignation, FDAPhone,
                FDATelephoneSTD, FDATelephone, FDAEmail, Website, FotoVCardFront, FotoVCardBack, FotoPerson,
                AnyOtherMeet,
                Industry, Process, VisitReference, DealerContact, DealerReffered, TechnicalPresentation, CONSULTANCYconvinced,
                ReadyToRecommend, ProductsTheyWish, ReasonsForNot, TheyNotReadyToRecommend, Outcome, ActionCoburg, KeyPoints,
                UpStatus, CreatedBy, CreatedOn;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();

        District = spnDistrict.getSelectedItem().toString();

        Pincode = et_Pincode.getText().toString();
        FDAPersonMeet = et_FDAPersonMeet.getText().toString();
        FDAPersonMDesignation = etFDAPersonMDesignation.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();
        FotoVCardFront = foto;
        FotoVCardBack = foto2;
        FotoPerson = foto3;
        AnyOtherMeet = spnAnyOtherMeet.getSelectedItem().toString();
        Industry = spnIndustry.getSelectedItem().toString();
        Process = spnProcess.getSelectedItem().toString();
        DealerContact = spnDealerContact.getSelectedItem().toString();
        DealerReffered = et_DealerReffered.getText().toString();
        TechnicalPresentation = spnTechnicalPresentation.getSelectedItem()
                .toString();
        CONSULTANCYconvinced = spnCONSULTANCYconvinced.getSelectedItem()
                .toString();
        ReadyToRecommend = spnReadyToRecommend.getSelectedItem().toString();
        if (ReadyToRecommend.equalsIgnoreCase("Yes")) {
            ProductsTheyWish = et_ProductsTheyWish.getText().toString();
            ReasonsForNot = et_ReasonsForNot.getText().toString();
            TheyNotReadyToRecommend = "";
        } else {
            ProductsTheyWish = "";
            ReasonsForNot = "";
            TheyNotReadyToRecommend = et_TheyNotReadyToRecommend.getText()
                    .toString();
        }

        Outcome = spnOutcome.getSelectedItem().toString();
        ActionCoburg = spnActionCoburg.getSelectedItem().toString();
        KeyPoints = et_KeyPoints.getText().toString();

        UpStatus = "N";
        CreatedBy = employeeID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        CreatedOn = currentDateandTime;

        db.insertConsultancy(companyname, fullAddress,
                getDistrictID, Pincode, FDAPersonMeet,
                FDAPersonMDesignation, FDATelephoneSTD, FDATelephone, FDAPhone, FDAEmail,
                Website, FotoVCardFront, FotoVCardBack, FotoPerson,
                AnyOtherMeet, getIndustryID, getProcessID,
                DealerContact, DealerReffered, TechnicalPresentation,
                CONSULTANCYconvinced, ReadyToRecommend, ProductsTheyWish,
                ReasonsForNot, TheyNotReadyToRecommend,
                getOutcomeConsultancyID, getActionCoburgID, KeyPoints,
                UpStatus, CoEmployee, TypeOfCall, CallDateTime, "New",
                CreatedBy, CreatedOn);

        db.insertCompany("Consultancy", companyname, Pincode, FDATelephoneSTD, FDATelephone,
                FDAPhone, Website, FDAEmail, District, "Consultancy", "1",fullAddress);

    }

    private void checkStatus() {

        String companyname, fullAddress, Pincode, FDAPersonMeet, FDAPersonMDesignation,
                FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website,
                DealerReffered, ProductsTheyWish, ReasonsForNot, TheyNotReadyToRecommend, KeyPoints, ReadyToRecommend;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();


        Pincode = et_Pincode.getText().toString();


        FDAPersonMeet = et_FDAPersonMeet.getText().toString();
        FDAPersonMDesignation = etFDAPersonMDesignation.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();

        DealerReffered = et_DealerReffered.getText().toString();
        ProductsTheyWish = et_ProductsTheyWish.getText().toString();
        ReasonsForNot = et_ReasonsForNot.getText().toString();
        TheyNotReadyToRecommend = et_TheyNotReadyToRecommend.getText()
                .toString();
        KeyPoints = et_KeyPoints.getText().toString();
        ReadyToRecommend = spnReadyToRecommend.getSelectedItem().toString();

        if (companyname.length() != 0 & fullAddress.length() != 0

                & Pincode.length() != 0 & FDATelephoneSTD.length() != 0
                & FDAPersonMeet.length() != 0 & FDAPersonMDesignation.length() != 0 & FDAPhone.length() != 0
                & FDATelephone.length() != 0 & FDAEmail.length() != 0
                & Website.length() != 0 & DealerReffered.length() != 0 & KeyPoints.length() != 0) {

            if (ReadyToRecommend.equalsIgnoreCase("Yes")) {
                if (ProductsTheyWish.length() != 0
                        & ReasonsForNot.length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if (ReadyToRecommend.equalsIgnoreCase("No")) {
                if (TheyNotReadyToRecommend.length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if (isValidEmail(FDAEmail) != false) {

                check = true;
            } else {
                Toast toast = Toast.makeText(NewConsultantFragment.this,
                        "Please enter valid email address", Toast.LENGTH_SHORT);
                toast.show();
                check = false;
            }
            if (isValidMobile(FDAPhone) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewConsultantFragment.this,
                        "Please enter valid phone number", Toast.LENGTH_SHORT);
                toast.show();
            }

            if (isValidPincode(Pincode) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewConsultantFragment.this,
                        "Please enter valid pincode", Toast.LENGTH_SHORT);
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

    private void loadOutcomeConsultancySpin() {

        try {
            Cursor c = db.selectOutcomeConsultancy();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecOutcomeDealer.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecOutcomeDealer.size(); i++) {
                lsOutcomeDealer.add(vecOutcomeDealer.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsOutcomeDealer);
            lsOutcomeDealer.clear();
            lsOutcomeDealer.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        adptOutcomeDealer = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOutcomeDealer);
        adptOutcomeDealer
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcome.setAdapter(adptOutcomeDealer);
        spnOutcome.setWillNotDraw(false);

    }

    private void VisitRefID(String Type) {

        try {
            Cursor c = db.getVisitRefID(Type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            getVisitRefID = c.getString(0);
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

    private void ProcessID(String type) {

        try {
            Cursor c = db.getProcessID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            getProcessID = c.getString(0);
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

    private void OutcomeID(String type) {

        try {
            Cursor c = db.getOutcomeConsultancyID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getOutcomeConsultancyID = c.getString(0);

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

    private void loadByCoburgConsultancySpin() {

        try {
            Cursor c = db.selectByCoburgConsultancy();
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

    private void loadIndustrySpin() {

        try {
            Cursor c = db.selectIndustry();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecIndustry.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecIndustry.size(); i++) {
                lsIndustry.add(vecIndustry.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsIndustry);
            lsIndustry.clear();
            lsIndustry.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        adptIndustry = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsIndustry);
        adptIndustry
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIndustry.setAdapter(adptIndustry);
        spnIndustry.setWillNotDraw(false);
    }

    private void selectIndustryID(String IndustryID) {
        String CuGetIndustryID = null;
        try {
            Cursor c = db.IndustryID(IndustryID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            CuGetIndustryID = c.getString(0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        LoadProcess(CuGetIndustryID);
    }

    private void IndustryID(String type) {
        try {
            Cursor c = db.getIndustryID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getIndustryID = c.getString(0);

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

    private void LoadProcess(String ID) {

        Vector<String> vecProcess = new Vector<String>();
        List<String> lsProcess = new ArrayList<String>();
        ArrayAdapter<String> adptProcess;
        try {
            Cursor c = db.selectProcess(ID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecProcess.add(c.getString(1));
                            }
                        }
                    }
                }
            } else {
                lsProcess.add("No sub process");
            }

            for (int i = 0; i < vecProcess.size(); i++) {
                lsProcess.add(vecProcess.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsProcess);
            lsProcess.clear();
            lsProcess.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        adptProcess = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsProcess);
        adptProcess
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProcess.setAdapter(adptProcess);
        spnProcess.setWillNotDraw(false);
    }

    private void LoadDistrictSpin() {

        try {
            Cursor c = db.selectDistrict();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecDistrict.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecDistrict.size(); i++) {
                lsDistrict.add(vecDistrict.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsDistrict);
            lsDistrict.clear();
            lsDistrict.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        adptDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsDistrict);
        adptDistrict
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDistrict.setAdapter(adptDistrict);
        spnDistrict.setWillNotDraw(false);
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
        et_companyname.setText("");
        et_fullAddress.setText("");


        et_Pincode.setText("");

        et_FDAPersonMeet.setText("");
        etFDAPersonMDesignation.setText("");
        et_FDAPhone.setText("");
        et_FDATelephone.setText("");
        et_FDATelephoneSTD.setText("");
        et_FDAEmail.setText("");
        et_Website.setText("");

        et_DealerReffered.setText("");
        et_ProductsTheyWish.setText("");
        et_ReasonsForNot.setText("");
        et_TheyNotReadyToRecommend.setText("");
        et_KeyPoints.setText("");
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

        et_companyname = (EditText) findViewById(R.id.et_companyName_consult);
        et_fullAddress = (EditText) findViewById(R.id.et_companyfulladd_consult);
        spnDistrict = (Spinner) findViewById(R.id.spn_district_consult);
        et_Pincode = (EditText) findViewById(R.id.et_companypin_consult);
        et_FDAPersonMeet = (EditText) findViewById(R.id.et_personmetname_consult);
        etFDAPersonMDesignation = (EditText) findViewById(R.id.et_personmetdesg_consult);
        et_FDAPhone = (EditText) findViewById(R.id.et_personmetphone_consult);
        et_FDATelephoneSTD = (EditText) findViewById(R.id.et_stdcode_consult);
        et_FDATelephone = (EditText) findViewById(R.id.et_personmettelephone_consult);
        et_FDAEmail = (EditText) findViewById(R.id.et_personmetemail_consult);
        et_Website = (EditText) findViewById(R.id.et_personmetwebsite_consult);
        _photoVcardFront = (Button) findViewById(R.id.btn_photovcardfront_consult);
        photoVcardback = (Button) findViewById(R.id.btn_photovcardback_consult);
        photoMetPerson = (Button) findViewById(R.id.btn_photometperson_consult);
        spnAnyOtherMeet = (Spinner) findViewById(R.id.spn_anyotherperson_consult);
        _AddMorePerson = (Button) findViewById(R.id.btn_moreperson_consult);
        spnIndustry = (Spinner) findViewById(R.id.spn_selectindustry_consult);
        spnProcess = (Spinner) findViewById(R.id.spn_selectprocess_consult);
        _AddKeyProject = (Button) findViewById(R.id.btn_keyproject_consult);
        spnDealerContact = (Spinner) findViewById(R.id.spn_dealercontact_consult);
        et_DealerReffered = (EditText) findViewById(R.id.et_referred_consult);
        _AddProductsRequired = (Button) findViewById(R.id.btn_ProductsRequired_consult);
        spnTechnicalPresentation = (Spinner) findViewById(R.id.spn_technicalpresentation_consult);
        spnCONSULTANCYconvinced = (Spinner) findViewById(R.id.spn_CONSULTANCYconvinced_consult);
        spnReadyToRecommend = (Spinner) findViewById(R.id.spn_ready_to_recommend_consult);
        et_ProductsTheyWish = (EditText) findViewById(R.id.et_Products_they_wish_consult);
        et_ReasonsForNot = (EditText) findViewById(R.id.et_Reasons_for_not_consult);
        et_TheyNotReadyToRecommend = (EditText) findViewById(R.id.et_they_not_ready_to_recommend_consult);
        _AddPresentProject = (Button) findViewById(R.id.btn_PresentProject_consult);
        spnOutcome = (Spinner) findViewById(R.id.spn_out_consult);
        spnActionCoburg = (Spinner) findViewById(R.id.spn_bycob_consult);
        et_KeyPoints = (EditText) findViewById(R.id.et_key_consult);
        _Save = (Button) findViewById(R.id.btn_save_consult);
        _Clear = (Button) findViewById(R.id.btn_clear_consult);

        llYes1 = (LinearLayout) findViewById(R.id.ll_yes1_consult);
        llYes2 = (LinearLayout) findViewById(R.id.ll_yes2_consult);
        llNo1 = (LinearLayout) findViewById(R.id.ll_no1_consult);

        db = new SQLiteHelper(getApplicationContext());
        builder_verify = new AlertDialog.Builder(this);

        vecDistrict = new Vector<String>();
        vecVisitRef = new Vector<String>();
        vecOutcomeDealer = new Vector<String>();
        vecBycoburgDealer = new Vector<String>();
        vecIndustry = new Vector<String>();

    }
}
