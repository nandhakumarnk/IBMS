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

public class NewOEMFragment extends AppCompatActivity {

    Button _photoVcardFront, photoVcardback, photoMetPerson, _Save, _Clear,
            _AddMorePerson, _AddBranchInfo, _AddOEMInfo, _AddBuyingFromUs,
            _AddGivingReferences;

    public static int count = 0;
    AlertDialog.Builder builder_verify;
    public static String foto, foto2, foto3, employeeID;

    String getDistrictID, CoEmployee, TypeOfCall, CallDateTime, getVisitRefID,
            getOutcomeOEMID, getActionCoburgID, getOEMTypeID, getOEMConvincedID;

    private EditText et_companyname, et_fullAddress,
            et_Pincode, et_FDAPersonMeet,
            etFDAPersonMDesignation, et_FDAPhone, et_FDATelephoneSTD, et_FDATelephone, et_FDAEmail,
            et_Website, et_remarks, et_NOT_CONVINCED, et_SupplierName, et_Why;

    Spinner spnDistrict, spnOEMType, spnAnyOtherMeet,
            spnTechnicalPresentation, spnCustomerConvinced, spnCONVINCED,
            spnPresentSupplier, spnReplacementVendor, spnOutcome,
            spnActionCoburg;

    LinearLayout ll_dtlsNo, ll_dtlsYes, ll_BuyingFromUs, ll_GivingReferences,
            ll_SupplierName, ll_replacementVendor;

    Vector<String> vecDistrict, vecVisitRef, vecOutcomeOEM, vecBycoburgOEM,
            vecOEMType, vecOEMConvinced;
    List<String> lsDistrict = new ArrayList<String>();
    List<String> lsVisitRef = new ArrayList<String>();
    List<String> lsOutcomeOEM = new ArrayList<String>();
    List<String> lsBycoburgOEM = new ArrayList<String>();
    List<String> lsOEMType = new ArrayList<String>();
    List<String> lsOEMConvinced = new ArrayList<String>();
    ArrayAdapter<String> adptDistrict, adptVisitRef, adptOutcomeOEM,
            adptBycoburgOEM, adptOEMTypeOEM, adptOEMConvinced;

    SQLiteHelper db;
    Boolean check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_oem);

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
                                                NewOEMFragment.this,
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
                                                NewOEMFragment.this,
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
                                                NewOEMFragment.this,
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

        _AddMorePerson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "OEM";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddMorePerson.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewOEMFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddBranchInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "OEM";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddBranchInfoOEM.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewOEMFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddOEMInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "OEM";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddOEMIndustryInfo.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewOEMFragment.this,
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

        spnOEMType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Outcome = arg0.getItemAtPosition(arg2).toString();
                OEMTypeID(Outcome);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnCustomerConvinced
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        String AnyOtherMeet = arg0.getItemAtPosition(arg2)
                                .toString();

                        if (AnyOtherMeet.equalsIgnoreCase("Yes")) {
                            ll_dtlsYes.setVisibility(View.VISIBLE);
                            ll_dtlsNo.setVisibility(View.GONE);
                            ll_GivingReferences.setVisibility(View.GONE);
                            ll_BuyingFromUs.setVisibility(View.GONE);

                        } else {
                            ll_dtlsYes.setVisibility(View.GONE);
                            ll_dtlsNo.setVisibility(View.VISIBLE);
                            ll_GivingReferences.setVisibility(View.GONE);
                            ll_BuyingFromUs.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });

        spnCONVINCED.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String AnyOtherMeet = arg0.getItemAtPosition(arg2).toString();
                OEMConvincedID(AnyOtherMeet);

                if (AnyOtherMeet.equalsIgnoreCase("Giving References")) {
                    ll_GivingReferences.setVisibility(View.VISIBLE);
                    ll_BuyingFromUs.setVisibility(View.GONE);

                } else {
                    ll_BuyingFromUs.setVisibility(View.VISIBLE);
                    ll_GivingReferences.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnPresentSupplier
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        String AnyOtherMeet = arg0.getItemAtPosition(arg2)
                                .toString();

                        if (AnyOtherMeet.equalsIgnoreCase("Yes")) {
                            ll_SupplierName.setVisibility(View.VISIBLE);

                        } else {
                            ll_SupplierName.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });

        spnReplacementVendor
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        String AnyOtherMeet = arg0.getItemAtPosition(arg2)
                                .toString();

                        if (AnyOtherMeet.equalsIgnoreCase("Yes")) {
                            ll_replacementVendor.setVisibility(View.VISIBLE);

                        } else {
                            ll_replacementVendor.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                });

        _AddBuyingFromUs.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "OEM";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddBuyingFromUs.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewOEMFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddGivingReferences.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "OEM";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddGivingReferences.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewOEMFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
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
                    InsertOEM();
                    clearAll();
                    finish();
                    Toast toast = Toast.makeText(NewOEMFragment.this, "Saved",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(NewOEMFragment.this,
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
        loadOEMTypeSpin();
        loadOutcomeOEMSpin();
        loadByCoburgOEMSpin();
        loadOEMConvincedSpin();
    }


    private void InsertOEM() {
        String companyname, fullAddress, District, Pincode, FDAPersonMeet, FDAPersonMDesignation,
                FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website, OEMType, FotoVCardFront, FotoVCardBack, FotoPerson,
                AnyOtherMeet, TechnicalPresentation, CustomerConvinced, NOT_CONVINCED,
                CONVINCED, PresentSupplier, SupplierName, ReplacementVendor, Why, Outcome, ActionCoburg, remarks, UpStatus,
                CreatedBy, CreatedOn;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();

        District = spnDistrict.getSelectedItem().toString();

        Pincode = et_Pincode.getText().toString();
        FDAPersonMeet = et_FDAPersonMeet.getText().toString();
        FDAPersonMDesignation = etFDAPersonMDesignation.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();
        OEMType = spnOEMType.getSelectedItem().toString();
        FotoVCardFront = foto;
        FotoVCardBack = foto2;
        FotoPerson = foto3;
        AnyOtherMeet = spnAnyOtherMeet.getSelectedItem().toString();
        TechnicalPresentation = spnTechnicalPresentation.getSelectedItem()
                .toString();
        CustomerConvinced = spnCustomerConvinced.getSelectedItem().toString();
        NOT_CONVINCED = et_NOT_CONVINCED.getText().toString();
        CONVINCED = spnCONVINCED.getSelectedItem().toString();
        PresentSupplier = spnPresentSupplier.getSelectedItem().toString();
        SupplierName = et_SupplierName.getText().toString();
        ReplacementVendor = spnReplacementVendor.getSelectedItem().toString();
        Why = et_Why.getText().toString();
        Outcome = spnOutcome.getSelectedItem().toString();
        ActionCoburg = spnActionCoburg.getSelectedItem().toString();
        remarks = et_remarks.getText().toString();

        UpStatus = "N";
        CreatedBy = employeeID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        CreatedOn = currentDateandTime;

        db.insertOEM(companyname, fullAddress, getDistrictID,
                Pincode, FDAPersonMeet, FDAPersonMDesignation, FDATelephoneSTD, FDATelephone, FDAPhone,
                FDAEmail, Website, getOEMTypeID, FotoVCardFront,
                FotoVCardBack, FotoPerson,
                AnyOtherMeet, TechnicalPresentation,
                CustomerConvinced, NOT_CONVINCED, getOEMConvincedID, PresentSupplier,
                SupplierName, ReplacementVendor, Why, getOutcomeOEMID, getActionCoburgID,
                remarks, UpStatus, CoEmployee, TypeOfCall, CallDateTime, "New",
                CreatedBy, CreatedOn);

        db.insertCompany("OEM", companyname, Pincode, FDATelephoneSTD, FDATelephone,
                FDAPhone, Website, FDAEmail, District, "OEM", "1",fullAddress);

    }

    private void checkStatus() {
        String companyname, fullAddress, CityVillage, State, Pincode, FDAPersonMeet, FDAPersonMDesignation, FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website, FDAName, FDADesignation, NOT_CONVINCED, SupplierName, Why, remarks, CustomerConvinced, PresentSupplier, ReplacementVendor;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();


        Pincode = et_Pincode.getText().toString();
        FDAPersonMeet = et_FDAPersonMeet.getText().toString();
        FDAPersonMDesignation = etFDAPersonMDesignation.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();

        NOT_CONVINCED = et_NOT_CONVINCED.getText().toString();
        SupplierName = et_SupplierName.getText().toString();
        Why = et_Why.getText().toString();
        remarks = et_remarks.getText().toString();

        CustomerConvinced = spnCustomerConvinced.getSelectedItem().toString();
        PresentSupplier = spnPresentSupplier.getSelectedItem().toString();
        ReplacementVendor = spnReplacementVendor.getSelectedItem().toString();

        if (companyname.length() != 0 & fullAddress.length() != 0
                & Pincode.length() != 0 & FDAPersonMeet.length() != 0
                & FDAPersonMDesignation.length() != 0 & FDAPhone.length() != 0 & FDATelephoneSTD.length() != 0
                & FDATelephone.length() != 0 & FDAEmail.length() != 0
                & Website.length() != 0 & remarks.length() != 0) {

            if (CustomerConvinced.equalsIgnoreCase("No")) {
                if (NOT_CONVINCED.length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if (PresentSupplier.equalsIgnoreCase("Yes")) {
                if (SupplierName.length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if (ReplacementVendor.equalsIgnoreCase("Yes")) {
                if (Why.length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if (isValidEmail(FDAEmail) != false) {

                check = true;
            } else {
                Toast toast = Toast.makeText(NewOEMFragment.this,
                        "Please enter valid email address", Toast.LENGTH_SHORT);
                toast.show();
                check = false;
            }
            if (isValidMobile(FDAPhone) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewOEMFragment.this,
                        "Please enter valid phone number", Toast.LENGTH_SHORT);
                toast.show();
            }

            if (isValidPincode(Pincode) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewOEMFragment.this,
                        "Please enter valid pincode", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            check = false;
        }
    }

    private void ActionCoburgID(String type) {

        try {
            Cursor c = db.getByCoburgOEMID(type);
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

    private void OutcomeID(String type) {

        try {
            Cursor c = db.getOutcomeOEMID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getOutcomeOEMID = c.getString(0);

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
        et_FDATelephoneSTD.setText("");
        et_FDATelephone.setText("");
        et_FDAEmail.setText("");
        et_Website.setText("");
        et_remarks.setText("");
        et_NOT_CONVINCED.setText("");
        et_SupplierName.setText("");
        et_Why.setText("");
    }

    private void DistrictID(String Type) {
        // String type = spnDistrict.getSelectedItem().toString();

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

    private void OEMTypeID(String Type) {
        // String type = spnDistrict.getSelectedItem().toString();

        try {
            Cursor c = db.getOEMTypeID(Type);
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

    private void OEMConvincedID(String Type) {

        try {
            Cursor c = db.getOEMConvincedID(Type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getOEMConvincedID = c.getString(0);

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

    private void loadByCoburgOEMSpin() {

        try {
            Cursor c = db.selectOEMByCob();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecBycoburgOEM.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecBycoburgOEM.size(); i++) {
                lsBycoburgOEM.add(vecBycoburgOEM.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsBycoburgOEM);
            lsBycoburgOEM.clear();
            lsBycoburgOEM.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptBycoburgOEM = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsBycoburgOEM);
        adptBycoburgOEM
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnActionCoburg.setAdapter(adptBycoburgOEM);
        spnActionCoburg.setWillNotDraw(false);

    }

    private void loadOutcomeOEMSpin() {

        try {
            Cursor c = db.selectOutcomeOEM();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecOutcomeOEM.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecOutcomeOEM.size(); i++) {
                lsOutcomeOEM.add(vecOutcomeOEM.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsOutcomeOEM);
            lsOutcomeOEM.clear();
            lsOutcomeOEM.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptOutcomeOEM = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOutcomeOEM);
        adptOutcomeOEM
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcome.setAdapter(adptOutcomeOEM);
        spnOutcome.setWillNotDraw(false);

    }

    private void loadOEMTypeSpin() {

        try {
            Cursor c = db.selectOEMType();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecOEMType.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecOEMType.size(); i++) {
                lsOEMType.add(vecOEMType.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsOEMType);
            lsOEMType.clear();
            lsOEMType.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptOEMTypeOEM = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOEMType);
        adptOEMTypeOEM
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOEMType.setAdapter(adptOEMTypeOEM);
        spnOEMType.setWillNotDraw(false);

    }

    private void loadOEMConvincedSpin() {

        try {
            Cursor c = db.selectOEMConvinced();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecOEMConvinced.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecOEMConvinced.size(); i++) {
                lsOEMConvinced.add(vecOEMConvinced.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsOEMConvinced);
            lsOEMConvinced.clear();
            lsOEMConvinced.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptOEMConvinced = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOEMConvinced);
        adptOEMConvinced
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCONVINCED.setAdapter(adptOEMConvinced);
        spnCONVINCED.setWillNotDraw(false);

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

    private void LoadDistrictSpin() {
        // to test
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

        // District
        adptDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsDistrict);
        adptDistrict
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDistrict.setAdapter(adptDistrict);
        spnDistrict.setWillNotDraw(false);
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

        et_companyname = (EditText) findViewById(R.id.et_companyName_oem);
        et_fullAddress = (EditText) findViewById(R.id.et_companyfulladd_oem);
        spnDistrict = (Spinner) findViewById(R.id.spn_district_oem);
        et_Pincode = (EditText) findViewById(R.id.et_companypin_oem);
        et_FDAPersonMeet = (EditText) findViewById(R.id.et_personmetname_oem);
        etFDAPersonMDesignation = (EditText) findViewById(R.id.et_personmetdesg_oem);
        et_FDAPhone = (EditText) findViewById(R.id.et_personmetphone_oem);
        et_FDATelephoneSTD = (EditText) findViewById(R.id.et_stdcode_oem);
        et_FDATelephone = (EditText) findViewById(R.id.et_personmettelephone_oem);
        et_FDAEmail = (EditText) findViewById(R.id.et_personmetemail_oem);
        et_Website = (EditText) findViewById(R.id.et_personmetwebsite_oem);
        spnOEMType = (Spinner) findViewById(R.id.spn_ometype_oem);
        _photoVcardFront = (Button) findViewById(R.id.btn_photovcardfront_oem);
        photoVcardback = (Button) findViewById(R.id.btn_photovcardback_oem);
        photoMetPerson = (Button) findViewById(R.id.btn_photometperson_oem);
        spnAnyOtherMeet = (Spinner) findViewById(R.id.spn_anyotherperson_oem);
        _AddMorePerson = (Button) findViewById(R.id.btn_moreperson_oem);
        _AddBranchInfo = (Button) findViewById(R.id.btn_branchinfo_oem);
        _AddOEMInfo = (Button) findViewById(R.id.btn_oeminfo_oem);
        spnTechnicalPresentation = (Spinner) findViewById(R.id.spn_technical_presentation_oem);
        spnCustomerConvinced = (Spinner) findViewById(R.id.spn_customer_convinced_oem);
        et_NOT_CONVINCED = (EditText) findViewById(R.id.et_NOTCONVINCED_oem);
        spnCONVINCED = (Spinner) findViewById(R.id.spn_CONVINCED_oem);
        _AddBuyingFromUs = (Button) findViewById(R.id.btn_BuyingFromUs_oem);
        _AddGivingReferences = (Button) findViewById(R.id.btn_GivingReferences_oem);
        spnPresentSupplier = (Spinner) findViewById(R.id.spn_present_supplier_oem);
        et_SupplierName = (EditText) findViewById(R.id.et_SupplierName_oem);
        spnReplacementVendor = (Spinner) findViewById(R.id.spn_replacementVendor_oem);
        et_Why = (EditText) findViewById(R.id.et_Why_oem);
        spnOutcome = (Spinner) findViewById(R.id.spn_OutcomeOEM_oem);
        spnActionCoburg = (Spinner) findViewById(R.id.spn_OEMbycob_oem);
        et_remarks = (EditText) findViewById(R.id.et_remarks_oem);
        ll_dtlsNo = (LinearLayout) findViewById(R.id.ll_dtlsNo_oem);
        ll_dtlsYes = (LinearLayout) findViewById(R.id.ll_dtlsYes_oem);
        ll_BuyingFromUs = (LinearLayout) findViewById(R.id.ll_BuyingFromUs_oem);
        ll_GivingReferences = (LinearLayout) findViewById(R.id.ll_GivingReferences_oem);
        ll_SupplierName = (LinearLayout) findViewById(R.id.ll_SupplierName_oem);
        ll_replacementVendor = (LinearLayout) findViewById(R.id.ll_replacementVendor_oem);

        _Save = (Button) findViewById(R.id.btn_save_oem);
        _Clear = (Button) findViewById(R.id.btn_clear_oem);

        db = new SQLiteHelper(getApplicationContext());
        builder_verify = new AlertDialog.Builder(this);

        vecDistrict = new Vector<String>();
        vecVisitRef = new Vector<String>();
        vecOutcomeOEM = new Vector<String>();
        vecBycoburgOEM = new Vector<String>();
        vecOEMType = new Vector<String>();
        vecOEMConvinced = new Vector<String>();
    }

}
