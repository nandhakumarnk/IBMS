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

public class NewDealerFragment extends AppCompatActivity {

    Button _photoVcardFront, photoVcardback, photoMetPerson, _Save,
            _AddMorePerson, _AddBranchInfo, _Clear, _AddIndustryServed,
            _AddExistingBusiness;
    public static int count = 0;
    AlertDialog.Builder builder, builder_verify;
    public static String foto, foto2, foto3, employeeID;

    String getDistrictID, CoEmployee, TypeOfCall, CallDateTime, getVisitRefID, getOutcomeDEalerID, getActionCoburgID;

    private EditText et_companyname, et_fullAddress,
            et_Pincode, et_FDAName, et_FDADesignation, et_FDAPersonMeet,
            etFDAPersonMDesignation, et_FDAPhone, et_FDATelephoneSTD, et_FDATelephone, et_FDAEmail,
            et_Website, et_DealerReffered, et_InterestProduct, et_AreaRegion, et_WriteUp,
            et_remarks;

    Spinner spnDistrict, spnAnyOtherMeet, spnDealerContact,
            spnSeekingInterest, spnInterestedIn, spnAfterSale, spnDealerShip,
            spnOutcome, spnActionCoburg;

    Vector<String> vecDistrict, vecVisitRef, vecOutcomeDealer,
            vecBycoburgDealer;
    List<String> lsDistrict = new ArrayList<String>();
    List<String> lsVisitRef = new ArrayList<String>();
    List<String> lsOutcomeDealer = new ArrayList<String>();
    List<String> lsBycoburgDealer = new ArrayList<String>();
    ArrayAdapter<String> adptDistrict, adptVisitRef, adptOutcomeDealer,
            adptBycoburgDealer;

    SQLiteHelper db;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_dealer);

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
                                                NewDealerFragment.this,
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
                                                NewDealerFragment.this,
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
                                                NewDealerFragment.this,
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
                    Toast toast = Toast.makeText(NewDealerFragment.this,
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
                String Project = "Dealer";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddBranchInfo.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewDealerFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddIndustryServed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "Dealer";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddIndustryServed.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewDealerFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddExistingBusiness.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "Dealer";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            AddExistingBusinessProducts.class);
                    addPerson.putExtra("Key_name", CompanyName);
                    addPerson.putExtra("Key_project", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewDealerFragment.this,
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
                    InsertDealer();
                    clearAll();
                    finish();
                    Toast toast = Toast.makeText(NewDealerFragment.this,
                            "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(NewDealerFragment.this,
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

    private void InsertDealer() {
        String companyname, fullAddress, Pincode, FDAName, FDADesignation, FDAPersonMeet,
                FDAPersonMDesignation, FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website, DealerReffered, InterestProduct,
                AreaRegion, WriteUp, remarks, District, AnyOtherMeet, DealerContact,
                SeekingInterest, InterestedIn, AfterSale, DealerShip,
                Outcome, ActionCoburg, UpStatus, CreatedBy, CreatedOn, FotoVCardFront, FotoVCardBack, FotoPerson;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();

        Pincode = et_Pincode.getText().toString();
        FDAName = et_FDAName.getText().toString();
        FDADesignation = et_FDADesignation.getText().toString();
        FDAPersonMeet = et_FDAPersonMeet.getText().toString();
        FDAPersonMDesignation = etFDAPersonMDesignation.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();
        FotoVCardFront = foto;
        FotoVCardBack = foto2;
        FotoPerson = foto3;
        DealerReffered = et_DealerReffered.getText().toString();
        InterestProduct = et_InterestProduct.getText().toString();
        AreaRegion = et_AreaRegion.getText().toString();
        WriteUp = et_WriteUp.getText().toString();
        remarks = et_remarks.getText().toString();
        District = spnDistrict.getSelectedItem().toString();
        AnyOtherMeet = spnAnyOtherMeet.getSelectedItem().toString();
        DealerContact = spnDealerContact.getSelectedItem().toString();
        SeekingInterest = spnSeekingInterest.getSelectedItem().toString();
        InterestedIn = spnInterestedIn.getSelectedItem().toString();
        AfterSale = spnAfterSale.getSelectedItem().toString();
        DealerShip = spnDealerShip.getSelectedItem().toString();
        Outcome = spnOutcome.getSelectedItem().toString();
        ActionCoburg = spnActionCoburg.getSelectedItem().toString();

        UpStatus = "N";
        CreatedBy = employeeID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        CreatedOn = currentDateandTime;

        db.insertDealer(companyname, fullAddress, getDistrictID, Pincode, FDAPersonMeet, FDAPersonMDesignation, FDATelephoneSTD,
                FDATelephone, FDAPhone, FDAEmail, Website, FotoVCardFront, FotoVCardBack, FotoPerson, FDAName, FDADesignation,
                AnyOtherMeet, DealerContact, DealerReffered, SeekingInterest, InterestProduct, InterestedIn,
                AfterSale, DealerShip, AreaRegion, WriteUp, getOutcomeDEalerID, getActionCoburgID, remarks, UpStatus,
                CoEmployee, TypeOfCall, CallDateTime, "New", CreatedBy, CreatedOn);


        db.insertCompany("Dealer", companyname, Pincode, FDATelephoneSTD, FDATelephone, FDAPhone, Website, FDAEmail, District, "Dealer", "1",fullAddress);

    }

    private void callMethod() {
        EmployeeID();
        LoadDistrictSpin();
        loadOutcomeDealerSpin();
        loadByCoburgDealerSpin();
    }

    private void ActionCoburgID(String type) {

        try {
            Cursor c = db.getDealerByCoburgID(type);
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
            Cursor c = db.getOutcomeDealerID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getOutcomeDEalerID = c.getString(0);

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

    private void checkStatus() {

        String companyname, fullAddress, Pincode, FDAName, FDADesignation, FDAPersonMeet, FDAPersonMDesignation,
                FDAPhone, FDATelephoneSTD, FDATelephone, FDAEmail, Website,
                DealerReffered, InterestProduct, AreaRegion, WriteUp, remarks;

        companyname = et_companyname.getText().toString();
        fullAddress = et_fullAddress.getText().toString();
        Pincode = et_Pincode.getText().toString();
        FDAName = et_FDAName.getText().toString();
        FDADesignation = et_FDADesignation.getText().toString();
        FDAPersonMeet = et_FDAPersonMeet.getText().toString();
        FDAPersonMDesignation = etFDAPersonMDesignation.getText().toString();
        FDAPhone = et_FDAPhone.getText().toString();
        FDATelephone = et_FDATelephone.getText().toString();
        FDATelephoneSTD = et_FDATelephoneSTD.getText().toString();
        FDAEmail = et_FDAEmail.getText().toString();
        Website = et_Website.getText().toString();
        DealerReffered = et_DealerReffered.getText().toString();
        InterestProduct = et_InterestProduct.getText().toString();
        AreaRegion = et_AreaRegion.getText().toString();
        WriteUp = et_WriteUp.getText().toString();
        remarks = et_remarks.getText().toString();

        if (companyname.length() != 0 & fullAddress.length() != 0
                & Pincode.length() != 0 & FDAName.length() != 0
                & FDADesignation.length() != 0 & FDAPersonMeet.length() != 0
                & FDAPersonMDesignation.length() != 0 & FDAPhone.length() != 0
                & FDATelephone.length() != 0 & FDAEmail.length() != 0 & FDATelephoneSTD.length() != 0
                & Website.length() != 0 & DealerReffered.length() != 0 & InterestProduct.length() != 0
                & AreaRegion.length() != 0 & WriteUp.length() != 0
                & remarks.length() != 0) {

            if (isValidEmail(FDAEmail) != false) {

                check = true;
            } else {
                Toast toast = Toast.makeText(NewDealerFragment.this,
                        "Please enter valid email address", Toast.LENGTH_SHORT);
                toast.show();
                check = false;
            }
            if (isValidMobile(FDAPhone) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewDealerFragment.this,
                        "Please enter valid phone number", Toast.LENGTH_SHORT);
                toast.show();
            }

            if (isValidPincode(Pincode) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewDealerFragment.this,
                        "Please enter valid pincode", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else {

            check = false;
        }

    }

    private void clearAll() {

        et_companyname.setText("");
        et_fullAddress.setText("");
        et_Pincode.setText("");
        et_FDAName.setText("");
        et_FDADesignation.setText("");
        et_FDAPersonMeet.setText("");
        etFDAPersonMDesignation.setText("");
        et_FDAPhone.setText("");
        et_FDATelephone.setText("");
        et_FDAEmail.setText("");
        et_Website.setText("");
        et_DealerReffered.setText("");
        et_InterestProduct.setText("");
        et_AreaRegion.setText("");
        et_WriteUp.setText("");
        et_remarks.setText("");
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

    private void loadOutcomeDealerSpin() {

        try {
            Cursor c = db.selectOutcomeDealer();
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

            @SuppressWarnings("rawtypes")
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

        // Process
        adptOutcomeDealer = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOutcomeDealer);
        adptOutcomeDealer
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcome.setAdapter(adptOutcomeDealer);
        spnOutcome.setWillNotDraw(false);

    }

    private void loadByCoburgDealerSpin() {

        try {
            Cursor c = db.selectByCoburgDealer();
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

            @SuppressWarnings("rawtypes")
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

        // Process
        adptBycoburgDealer = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsBycoburgDealer);
        adptBycoburgDealer
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnActionCoburg.setAdapter(adptBycoburgDealer);
        spnActionCoburg.setWillNotDraw(false);

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

        et_companyname = (EditText) findViewById(R.id.et_companyName_dealer);
        et_fullAddress = (EditText) findViewById(R.id.et_companyfulladd_dealer);
        spnDistrict = (Spinner) findViewById(R.id.spn_district_dealer);
        et_Pincode = (EditText) findViewById(R.id.et_companypin_dealer);
        et_FDAPersonMeet = (EditText) findViewById(R.id.et_personmetname_dealer);
        etFDAPersonMDesignation = (EditText) findViewById(R.id.et_personmetdesg_dealer);
        et_FDAPhone = (EditText) findViewById(R.id.et_personmetphone_dealer);
        et_FDATelephoneSTD = (EditText) findViewById(R.id.et_stdcode_dealer);
        et_FDATelephone = (EditText) findViewById(R.id.et_personmettelephone_dealer);
        et_FDAEmail = (EditText) findViewById(R.id.et_personmetemail_dealer);
        et_Website = (EditText) findViewById(R.id.et_personmetwebsite_dealer);
        _photoVcardFront = (Button) findViewById(R.id.btn_photovcardfront_dealer);
        photoVcardback = (Button) findViewById(R.id.btn_photovcardback_dealer);
        photoMetPerson = (Button) findViewById(R.id.btn_photometperson_dealer);
        et_FDAName = (EditText) findViewById(R.id.et_decisionpersonname_dealer);
        et_FDADesignation = (EditText) findViewById(R.id.et_decisionpersondesg_dealer);
        spnAnyOtherMeet = (Spinner) findViewById(R.id.spn_anyotherperson_dealer);
        _AddMorePerson = (Button) findViewById(R.id.btn_moreperson_dealer);
        _AddBranchInfo = (Button) findViewById(R.id.btn_branchinfo_dealer);
        _AddIndustryServed = (Button) findViewById(R.id.btn_IndustryServed_dealer);
        _AddExistingBusiness = (Button) findViewById(R.id.btn_ExistingBusinessProducts_dealer);
        spnDealerContact = (Spinner) findViewById(R.id.spn_dealercontact_dealer);
        et_DealerReffered = (EditText) findViewById(R.id.et_referred_dealer);
        spnSeekingInterest = (Spinner) findViewById(R.id.spn_seekingDealership_dealer);
        et_InterestProduct = (EditText) findViewById(R.id.et_COBURGproducts_dealer);
        spnInterestedIn = (Spinner) findViewById(R.id.spn_interestedin_dealer);
        spnAfterSale = (Spinner) findViewById(R.id.spn_AFTERSALESERVICE_dealer);
        spnDealerShip = (Spinner) findViewById(R.id.spn_conditionsinvolved_dealer);
        et_AreaRegion = (EditText) findViewById(R.id.et_AreaRegion_dealer);
        et_WriteUp = (EditText) findViewById(R.id.et_Writeup_dealer);
        spnOutcome = (Spinner) findViewById(R.id.spn_Outcome_dealer);
        spnActionCoburg = (Spinner) findViewById(R.id.spn_byCoburg_dealer);
        et_remarks = (EditText) findViewById(R.id.et_remarks_dealer);
        _Save = (Button) findViewById(R.id.btn_save_dealer);
        _Clear = (Button) findViewById(R.id.btn_clear_dealer);

        db = new SQLiteHelper(getApplicationContext());
        builder_verify = new AlertDialog.Builder(this);

        vecDistrict = new Vector<String>();
        vecVisitRef = new Vector<String>();
        vecOutcomeDealer = new Vector<String>();
        vecBycoburgDealer = new Vector<String>();
    }
}
