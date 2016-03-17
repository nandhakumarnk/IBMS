package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;

public class NewProspectFragment extends AppCompatActivity {

    Button _photoVcardFront, photoVcardback, photoMetPerson, _Save,
            _AddMorePerson, _AddProduct, _Clear, _Expense;
    public static int count = 0;
    AlertDialog.Builder builder, builder_verify;
    public static String foto, foto2, foto3, employeeID;
    private ImageButton img_projectcompl, img_projectdelion;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    String CoEmployee, TypeOfCall, CallDateTime, getDistrictID,
            getVisitRefID, getIndustryID, getProcessID="", getProjectStatusID,
            getOutcomeProspectID, getOrderReceivedID, getActionCoburgID,
            ProjectCompleteDate, ProjectStartDate;

    int dueDate, dueMonth, dueYear;

    private EditText et_projectcompl, et_projectdelion, et_companyname,
            et_fullAddress, et_Pincode, et_FDAName,
            et_FDADesignation, et_FDAPersonMeet, etFDAPersonMDesignation,
            et_FDAPhone, et_FDATelephoneSTD, et_FDATelephone, et_FDAEmail, et_Website,
            et_EndProductMfrd, et_ProductClassification,
            et_WriteUp, et_ODHowMuch, et_Scopeofsupply,
            et_Technicalfeatures, et_Quality, et_ServiceSupport,
            et_Deliveryschedule, et_ModeofSale, et_Price, et_PaymentTerms,
            et_Warrantyterms, et_Otherpartifany, et_remarks;

    Spinner spnAnyOtherMeet, spnIndustry, spnProcess, spnVisitReference,
            spnProjectstatus, spnDtlsTech, spnCustomerTechProduct,
            spnCLP, spnOfferedDiscount, spnOutcome,
            spnOrderReceived, spnActionCoburg, spnDistrict;

    LinearLayout OfferDiscLayoyt, OrderReceivedLayout, AnyCompetition;
    SQLiteHelper db;
    Boolean check = false;
    Vector<String> vecDistrict, vecIndustry, vecVisitRef, vecProjectStatus,
            vecOutcomeProspect, vecProspectByCoburg;
    List<String> lsDistrict = new ArrayList<String>();
    List<String> lsVisitRef = new ArrayList<String>();
    List<String> lsIndustry = new ArrayList<String>();
    List<String> lsProjectStatus = new ArrayList<String>();
    List<String> lsOutcomeProspect = new ArrayList<String>();
    List<String> lsProspectByCoburg = new ArrayList<String>();

    ArrayAdapter<String> adptDistrict, adptIndustry, adptVisitRef,
            adptProjectStatus, adptOutcomeProspect, adptProspectByCoburg;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_prospect);

        et_companyname = (EditText) findViewById(R.id.et_companyName_pros);
        et_fullAddress = (EditText) findViewById(R.id.et_companyfulladd_pros);
        spnDistrict = (Spinner) findViewById(R.id.spn_district_pros);
        et_Pincode = (EditText) findViewById(R.id.et_companypin_pros);
        et_FDATelephoneSTD = (EditText) findViewById(R.id.et_personmettelephone_pros);
        et_FDATelephone = (EditText) findViewById(R.id.et_personmettelephone_pros);
        et_FDAPhone = (EditText) findViewById(R.id.et_personmetphone_pros);
        et_FDAEmail = (EditText) findViewById(R.id.et_personmetemail_pros);
        et_Website = (EditText) findViewById(R.id.et_personmetwebsite_pros);
        et_FDAName = (EditText) findViewById(R.id.et_decisionpersonname_pros);
        et_FDADesignation = (EditText) findViewById(R.id.et_decisionpersondesg_pros);
        et_FDAPersonMeet = (EditText) findViewById(R.id.et_personmetname_pros);
        etFDAPersonMDesignation = (EditText) findViewById(R.id.et_personmetdesg_pros);
        _photoVcardFront = (Button) findViewById(R.id.btn_photovcardfront_pros);
        photoVcardback = (Button) findViewById(R.id.btn_photovcardback_pros);
        photoMetPerson = (Button) findViewById(R.id.btn_photometperson_pros);
        spnAnyOtherMeet = (Spinner) findViewById(R.id.spn_anyotherperson_pros);
        _AddMorePerson = (Button) findViewById(R.id.btn_moreperson_pros);
        spnVisitReference = (Spinner) findViewById(R.id.spn_ref_pros);
        spnIndustry = (Spinner) findViewById(R.id.spn_selectindustry_pros);
        spnProcess = (Spinner) findViewById(R.id.spn_selectprocess_pros);
        et_EndProductMfrd = (EditText) findViewById(R.id.et_endproduct_pros);
        et_ProductClassification = (EditText) findViewById(R.id.et_prodclassi_pros);
        et_WriteUp = (EditText) findViewById(R.id.et_writeup_pros);
        _AddProduct = (Button) findViewById(R.id.btn_addprod_pros);
        spnProjectstatus = (Spinner) findViewById(R.id.spn_projectstatus_pros);
        et_projectcompl = (EditText) findViewById(R.id.et_projectcompl_pros);
        img_projectcompl = (ImageButton) findViewById(R.id.img_projectcompl_pros);
        et_projectdelion = (EditText) findViewById(R.id.et_projectdelion_pros);
        img_projectdelion = (ImageButton) findViewById(R.id.img_projectdelion_pros);
        spnDtlsTech = (Spinner) findViewById(R.id.spn_selectdtls1_pros);
        spnCustomerTechProduct = (Spinner) findViewById(R.id.spn_selectdtls2_pros);
        spnCLP = (Spinner) findViewById(R.id.spn_dtls3_pros);
        spnOfferedDiscount = (Spinner) findViewById(R.id.spn_dtls4_pros);
        et_ODHowMuch = (EditText) findViewById(R.id.et_dtls5_pros);
        et_Scopeofsupply = (EditText) findViewById(R.id.et_scope_pros);
        et_Technicalfeatures = (EditText) findViewById(R.id.et_technical_pros);
        et_Quality = (EditText) findViewById(R.id.et_Quality_pros);
        et_ServiceSupport = (EditText) findViewById(R.id.et_Service_pros);
        et_Deliveryschedule = (EditText) findViewById(R.id.et_Delivery_pros);
        et_ModeofSale = (EditText) findViewById(R.id.et_mode_pros);
        et_Price = (EditText) findViewById(R.id.et_Price_pros);
        et_PaymentTerms = (EditText) findViewById(R.id.et_Payment_pros);
        et_Warrantyterms = (EditText) findViewById(R.id.et_Warranty_pros);
        et_Otherpartifany = (EditText) findViewById(R.id.et_Other_pros);
        spnOutcome = (Spinner) findViewById(R.id.spn_outcome_pros);
        spnOrderReceived = (Spinner) findViewById(R.id.spn_order_pros);
        spnActionCoburg = (Spinner) findViewById(R.id.spn_bycoburg_pros);
        et_remarks = (EditText) findViewById(R.id.et_remarks_pros);
        _Expense = (Button) findViewById(R.id.btn_expense_pros);

        _Save = (Button) findViewById(R.id.btn_save_pros);
        _Clear = (Button) findViewById(R.id.btn_clear_pros);

        OfferDiscLayoyt = (LinearLayout) findViewById(R.id.ll_dtls5_pros);
        OrderReceivedLayout = (LinearLayout) findViewById(R.id.ll_order_pros);

        db = new SQLiteHelper(getApplicationContext());
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        builder_verify = new AlertDialog.Builder(this);
        vecDistrict = new Vector<String>();
        vecIndustry = new Vector<String>();
        vecVisitRef = new Vector<String>();
        vecProjectStatus = new Vector<String>();
        vecOutcomeProspect = new Vector<String>();
        vecProspectByCoburg = new Vector<String>();

        Bundle b = getIntent().getExtras();
        CoEmployee = b.getString("CoEmployee");
        TypeOfCall = b.getString("TypeOfCall");
        CallDateTime = b.getString("CallDateTime");


        getfromdatabase();
        loadSpinner();
        loadIndustry();
        loadSpinIndustry();
        loadVisitRef();
        loadProjectStatus();
        loadOutcomeProspect();
        loadProspectByCoburg();
        EmployeeID();
        //DistrictID();
        //VisitRefID();
        //IndustryID();
        //ProcessID();
        //ProjectStatusID();
        //OutcomeID();
        //ActionCoburgID();

        _photoVcardFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String set_string = "Side of Business Card Front\n" +
                        "Do you want to proceed?";
                builder_verify
                        .setMessage(set_string)
                        .setCancelable(true)
                        .setPositiveButton("Yes, Proceed",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        count = 1;
                                        // _photoVcardFront.setEnabled(true);
                                        Intent in = new Intent(
                                                NewProspectFragment.this,
                                                Cam_offline.class);
                                        in.putExtra("Code", "Prospect");
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
                String set_string = "Side of VCard Back\n" +
                        "Do you want to proceed?";
                builder_verify
                        .setMessage(set_string)
                        .setCancelable(true)
                        .setPositiveButton("Yes, Proceed",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        count = 1;
                                        // _photoVcardFront.setEnabled(true);
                                        Intent in = new Intent(
                                                NewProspectFragment.this,
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
                String set_string = "The Person who you meet\n" +
                        "Do you want to proceed?";
                builder_verify
                        .setMessage(set_string)
                        .setCancelable(true)
                        .setPositiveButton("Yes, Proceed",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        count = 1;
                                        // _photoVcardFront.setEnabled(true);
                                        Intent in = new Intent(
                                                NewProspectFragment.this,
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

        img_projectcompl.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(0);
            }
        });

        _AddMorePerson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "Prospect";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addPerson = new Intent(getApplicationContext(),
                            PersonAddListPage.class);
                    addPerson.putExtra("CompanyName", CompanyName);
                    addPerson.putExtra("ProjectType", Project);
                    startActivity(addPerson);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewProspectFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        _AddProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = et_companyname.getText().toString();
                String Project = "Prospect";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addProduct = new Intent(getApplicationContext(),
                            ProductListPage.class);
                    addProduct.putExtra("CompanyName", CompanyName);
                    addProduct.putExtra("ProjectType", Project);
                    startActivity(addProduct);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewProspectFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        img_projectdelion.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDialog(1);
            }
        });

        _Save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                getCurrentLocation();
                checkStatus();
                if (check == true) {
                    InsertProspect();
                    clearAll();
                    finish();
                    Toast toast = Toast.makeText(NewProspectFragment.this,
                            "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(NewProspectFragment.this,
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


        spnOfferedDiscount
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        String OfferedDiscount = arg0.getItemAtPosition(arg2)
                                .toString();

                        if (OfferedDiscount.equalsIgnoreCase("Yes")) {
                            OfferDiscLayoyt.setVisibility(View.VISIBLE);
                        } else {
                            OfferDiscLayoyt.setVisibility(View.GONE);
                        }
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

        spnOutcome.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Outcome = arg0.getItemAtPosition(arg2).toString();

                if (Outcome.equalsIgnoreCase("ORDER RECEIVED. ")) {
                    selectOutcomeID(Outcome);
                    OrderReceivedLayout.setVisibility(View.VISIBLE);
                } else {
                    OrderReceivedLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        _Expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CompanyName = et_companyname.getText().toString();
                String Project = "Prospect";
                if (CompanyName.length() != 0) {
                    et_companyname.setEnabled(false);
                    Intent addExpense = new Intent(getApplicationContext(),
                            ExpenseListPage.class);
                    addExpense.putExtra("CompanyName", CompanyName);
                    addExpense.putExtra("ProjectType", Project);
                    startActivity(addExpense);
                } else {
                    et_companyname.setEnabled(true);
                    Toast toast = Toast.makeText(NewProspectFragment.this,
                            "Please fill company name", Toast.LENGTH_SHORT);
                    toast.show();
                }
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

            @SuppressWarnings("rawtypes")
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

        // Process
        adptProcess = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsProcess);
        adptProcess
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProcess.setAdapter(adptProcess);
        spnProcess.setWillNotDraw(false);
    }

    private void loadVisitRef() {

        try {
            Cursor c = db.selectVisitRef();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecVisitRef.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecVisitRef.size(); i++) {
                lsVisitRef.add(vecVisitRef.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsVisitRef);
            lsVisitRef.clear();
            lsVisitRef.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptVisitRef = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsVisitRef);
        adptVisitRef
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVisitReference.setAdapter(adptVisitRef);
        spnVisitReference.setWillNotDraw(false);

    }

    private void loadProjectStatus() {

        try {
            Cursor c = db.selectProjectStatus();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecProjectStatus.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecProjectStatus.size(); i++) {
                lsProjectStatus.add(vecProjectStatus.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsProjectStatus);
            lsProjectStatus.clear();
            lsProjectStatus.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptProjectStatus = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsProjectStatus);
        adptProjectStatus
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProjectstatus.setAdapter(adptProjectStatus);
        spnProjectstatus.setWillNotDraw(false);

    }

    private void loadOutcomeProspect() {

        try {
            Cursor c = db.selectOutcomeProspect();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecOutcomeProspect.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecOutcomeProspect.size(); i++) {
                lsOutcomeProspect.add(vecOutcomeProspect.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsOutcomeProspect);
            lsOutcomeProspect.clear();
            lsOutcomeProspect.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptOutcomeProspect = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOutcomeProspect);
        adptOutcomeProspect
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcome.setAdapter(adptOutcomeProspect);
        spnOutcome.setWillNotDraw(false);

    }

    private void loadProspectByCoburg() {

        try {
            Cursor c = db.selectProspectByCoburg();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecProspectByCoburg.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecProspectByCoburg.size(); i++) {
                lsProspectByCoburg.add(vecProspectByCoburg.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsProspectByCoburg);
            lsProspectByCoburg.clear();
            lsProspectByCoburg.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptProspectByCoburg = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsProspectByCoburg);
        adptProspectByCoburg
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnActionCoburg.setAdapter(adptProspectByCoburg);
        spnActionCoburg.setWillNotDraw(false);

    }

    private void selectIndustryID(String IndustryID) {
        String GetIndustryID = null;
        try {
            Cursor c = db.IndustryID(IndustryID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            GetIndustryID = c.getString(0);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        LoadProcess(GetIndustryID);
    }

    private void selectOutcomeID(String OutcomeID) {
        String GetOutcomeID = null;
        try {
            Cursor c = db.OutcomeID(OutcomeID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            GetOutcomeID = c.getString(0);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        LoadOrderReceived(GetOutcomeID);
    }

    private void LoadOrderReceived(String ID) {

        Vector<String> vecOrderReceived = new Vector<String>();
        List<String> lsOrderReceived = new ArrayList<String>();
        ArrayAdapter<String> adptOrderReceived;
        try {
            Cursor c = db.selectOrderReceived(ID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecOrderReceived.add(c.getString(1));
                            }
                        }
                    }
                }
            } else {
                lsOrderReceived.add("");
            }

            for (int i = 0; i < vecOrderReceived.size(); i++) {
                lsOrderReceived.add(vecOrderReceived.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsOrderReceived);
            lsOrderReceived.clear();
            lsOrderReceived.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptOrderReceived = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsOrderReceived);
        adptOrderReceived
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOrderReceived.setAdapter(adptOrderReceived);
        spnOrderReceived.setWillNotDraw(false);
    }

    public void getfromdatabase() {
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
    }

    private void loadIndustry() {

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
    }

    private void loadSpinner() {
        // District
        adptDistrict = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsDistrict);
        adptDistrict
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDistrict.setAdapter(adptDistrict);
        spnDistrict.setWillNotDraw(false);
    }

    private void loadSpinIndustry() {
        // Industry
        adptIndustry = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsIndustry);
        adptIndustry
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIndustry.setAdapter(adptIndustry);
        spnIndustry.setWillNotDraw(false);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        if (id == 0) {
            return new DatePickerDialog(this, datePickerListener, year, month,
                    day);
        }
        if (id == 1) {
            return new DatePickerDialog(this, datePickerListener1, year, month,
                    day);
        }
        // if (id == 2) {
        // return new TimePickerDialog(this, timeReminderListener,
        // remind_hour, remind_minute, false);
        // }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            // et_datePicker.setText(selectedDay + " / " + (selectedMonth + 1)
            // + " / " + selectedYear);

            dueDate = selectedDay;
            dueMonth = selectedMonth;
            dueYear = selectedYear;

            et_projectcompl.setText(new StringBuilder()
                    .append(pad(selectedDay)).append("/")
                    .append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));

            ProjectCompleteDate = pad(selectedYear) + "-"
                    + pad(selectedMonth + 1) + "-" + pad(selectedDay);

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            // et_datePicker.setText(selectedDay + " / " + (selectedMonth + 1)
            // + " / " + selectedYear);

            dueDate = selectedDay;
            dueMonth = selectedMonth;
            dueYear = selectedYear;

            et_projectdelion.setText(new StringBuilder()
                    .append(pad(selectedDay)).append("/")
                    .append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));

            ProjectStartDate = pad(selectedYear) + "-" + pad(selectedMonth + 1)
                    + "-" + pad(selectedDay);

        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void checkStatus() {

        String CompanyName, FullAddress, City, District, State, Pincode, FDAPersonName, FDAPersonDesignation, PersonMeet, Designation, PhoneNo, TeleNo, Email, WebSite, AnyPersonMet, EstablishedYear, AnnualIncome, NoOfEmployees, AssociatedCompany, EndProductMfrd, ProductClassify, WriteUp, ProjectCompleteBy, ProjectDeliveyBefore, AnyCompetition, Competitors, Offered, OfferedHowMuch, ScopeofSupply, TechFeatures, Quality, ServiceSupport, DeliverySchedule, ModeSale, Price, Payment, Warranty, Other, OutCome, OrderReceived, ActionBy;

        CompanyName = et_companyname.getText().toString();
        FullAddress = et_fullAddress.getText().toString();
        Pincode = et_Pincode.getText().toString();
        FDAPersonName = et_FDAName.getText().toString();
        FDAPersonDesignation = et_FDADesignation.getText().toString();
        PersonMeet = et_FDAPersonMeet.getText().toString();
        Designation = etFDAPersonMDesignation.getText().toString();
        PhoneNo = et_FDAPhone.getText().toString();
        TeleNo = et_FDATelephone.getText().toString();
        Email = et_FDAEmail.getText().toString();
        WebSite = et_Website.getText().toString();
        EndProductMfrd = et_EndProductMfrd.getText().toString();
        ProductClassify = et_ProductClassification.getText().toString();
        WriteUp = et_WriteUp.getText().toString();
        ProjectCompleteBy = et_projectcompl.getText().toString();
        ProjectDeliveyBefore = et_projectdelion.getText().toString();
        Offered = spnCLP.getSelectedItem().toString();
        OfferedHowMuch = et_ODHowMuch.getText().toString();
        ScopeofSupply = et_Scopeofsupply.getText().toString();
        TechFeatures = et_Technicalfeatures.getText().toString();
        Quality = et_Quality.getText().toString();
        ServiceSupport = et_ServiceSupport.getText().toString();
        DeliverySchedule = et_Deliveryschedule.getText().toString();
        ModeSale = et_ModeofSale.getText().toString();
        Price = et_Price.getText().toString();
        Payment = et_PaymentTerms.getText().toString();
        Warranty = et_Warrantyterms.getText().toString();
        Other = et_Otherpartifany.getText().toString();

        if (CompanyName.length() != 0 & FullAddress.length() != 0
                & Pincode.length() != 0 & FDAPersonName.length() != 0
                & FDAPersonDesignation.length() != 0 & PersonMeet.length() != 0
                & Designation.length() != 0 & PhoneNo.length() != 0
                & TeleNo.length() != 0 & Email.length() != 0
                & WebSite.length() != 0
                & EndProductMfrd.length() != 0 & ProductClassify.length() != 0
                & WriteUp.length() != 0 & ProjectCompleteBy.length() != 0
                & ProjectDeliveyBefore.length() != 0
                & ScopeofSupply.length() != 0 & TechFeatures.length() != 0
                & Quality.length() != 0 & ServiceSupport.length() != 0
                & DeliverySchedule.length() != 0 & ModeSale.length() != 0
                & Price.length() != 0 & Payment.length() != 0
                & Warranty.length() != 0 & Other.length() != 0) {
            if (Offered.equalsIgnoreCase("Yes")) {
                if (OfferedHowMuch.length() != 0) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if (isValidEmail(Email) != false) {

                check = true;
            } else {
                Toast toast = Toast.makeText(NewProspectFragment.this,
                        "Please enter valid email address", Toast.LENGTH_SHORT);
                toast.show();
                check = false;
            }
            if (isValidMobile(PhoneNo) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewProspectFragment.this,
                        "Please enter valid phone number", Toast.LENGTH_SHORT);
                toast.show();
            }

            if (isValidPincode(Pincode) != false) {
                check = true;

            } else {
                check = false;
                Toast toast = Toast.makeText(NewProspectFragment.this,
                        "Please enter valid pincode", Toast.LENGTH_SHORT);
                toast.show();
            }

        } else {

            check = false;
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

    private void clearAll() {
        et_projectcompl.setText("");
        et_projectdelion.setText("");
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
        et_EndProductMfrd.setText("");
        et_ProductClassification.setText("");
        et_WriteUp.setText("");
        et_ODHowMuch.setText("");
        et_Scopeofsupply.setText("");
        et_Technicalfeatures.setText("");
        et_Quality.setText("");
        et_ServiceSupport.setText("");
        et_Deliveryschedule.setText("");
        et_ModeofSale.setText("");
        et_Price.setText("");
        et_PaymentTerms.setText("");
        et_Warrantyterms.setText("");
        et_Otherpartifany.setText("");
    }

    public void getCurrentLocation()
    {
        latitude=0.0;
        longitude=0.0;
        GPSTracker gps = new GPSTracker(NewProspectFragment.this);
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        }else{
            gps.showSettingsAlert();
        }

    }

    private void InsertProspect() {
        String CompanyName, FullAddress, City, District, State, Pincode, FDAPersonName, FDAPersonDesignation, PersonMeet, Designation,
                PhoneNo, TeleNoSTD, TeleNo, Email, WebSite, FotoVCardFront, FotoVCardBack, FotoPerson, AnyPersonMet,
                EstablishedYear, AnnualIncome, NoOfEmployees, AssociatedCompany, Industry, Process, EndProductMfrd,
                ProductClassify, WriteUp, VisitReference, ProjectStatus, ProjectCompleteBy, ProjectDeliveyBefore, DtlsTech,
                CustomerTech, AnyCompetition, Competitors = "", CLP, Offered, OfferedHowMuch = "", ScopeofSupply, TechFeatures,
                Quality, ServiceSupport, DeliverySchedule, ModeSale, Price, Payment, Warranty, Other, OutCome, OrderReceived,
                ActionBy, Remarks, UpStatus, CreatedBy, CreatedOn;

        CompanyName = et_companyname.getText().toString();
        FullAddress = et_fullAddress.getText().toString();
        District = spnDistrict.getSelectedItem().toString();
        Pincode = et_Pincode.getText().toString();
        FDAPersonName = et_FDAName.getText().toString();
        FDAPersonDesignation = et_FDADesignation.getText().toString();
        PersonMeet = et_FDAPersonMeet.getText().toString();
        Designation = etFDAPersonMDesignation.getText().toString();
        PhoneNo = et_FDAPhone.getText().toString();
        TeleNoSTD = et_FDATelephoneSTD.getText().toString();
        TeleNo = et_FDATelephone.getText().toString();
        Email = et_FDAEmail.getText().toString();
        WebSite = et_Website.getText().toString();
        FotoVCardFront = foto;
        FotoVCardBack = foto2;
        FotoPerson = foto3;
        AnyPersonMet = spnAnyOtherMeet.getSelectedItem().toString();
        String AnyPersonMetYesNo = "0";
        if(AnyPersonMet.equalsIgnoreCase("Yes")){
            AnyPersonMetYesNo = "1";
        }else {
            AnyPersonMetYesNo = "0";
        }
        Industry = spnIndustry.getSelectedItem().toString();
        Process = spnProcess.getSelectedItem().toString();
        EndProductMfrd = et_EndProductMfrd.getText().toString();
        ProductClassify = et_ProductClassification.getText().toString();
        WriteUp = et_WriteUp.getText().toString();
        VisitReference = spnVisitReference.getSelectedItem().toString();
        ProjectStatus = spnProjectstatus.getSelectedItem().toString();
        ProjectCompleteBy = et_projectcompl.getText().toString();
        ProjectDeliveyBefore = et_projectdelion.getText().toString();
        DtlsTech = spnDtlsTech.getSelectedItem().toString();
        String DtlsTechYesNo = "0";
        if(DtlsTech.equalsIgnoreCase("Yes")){
            DtlsTechYesNo = "1";
        }else {
            DtlsTechYesNo = "0";
        }
        CustomerTech = spnCustomerTechProduct.getSelectedItem().toString();
        String CustomerTechYesNo = "0";
        if(CustomerTech.equalsIgnoreCase("Yes")){
            CustomerTechYesNo = "1";
        }else {
            CustomerTechYesNo = "0";
        }
        CLP = spnCLP.getSelectedItem().toString();
        String CLPYesNo = "0";
        if(CLP.equalsIgnoreCase("Yes")){
            CLPYesNo = "1";
        }else {
            CLPYesNo = "0";
        }
        Offered = spnCLP.getSelectedItem().toString();
        String OfferedYesNo = "0";
        if(Offered.equalsIgnoreCase("Yes")){
            OfferedYesNo = "1";
        }else {
            OfferedYesNo = "0";
        }
        if (Offered.equalsIgnoreCase("Yes")) {
            if (OfferedHowMuch.length() != 0) {
                OfferedHowMuch = et_ODHowMuch.getText().toString();
            } else {
                OfferedHowMuch = "";
            }
        }

        ScopeofSupply = et_Scopeofsupply.getText().toString();
        TechFeatures = et_Technicalfeatures.getText().toString();
        Quality = et_Quality.getText().toString();
        ServiceSupport = et_ServiceSupport.getText().toString();
        DeliverySchedule = et_Deliveryschedule.getText().toString();
        ModeSale = et_ModeofSale.getText().toString();
        Price = et_Price.getText().toString();
        Payment = et_PaymentTerms.getText().toString();
        Warranty = et_Warrantyterms.getText().toString();
        Other = et_Otherpartifany.getText().toString();
        OutCome = spnOutcome.getSelectedItem().toString();
        if (OutCome.equalsIgnoreCase("Order Received")) {
            OutcomeOrderReceivedID();
            OrderReceived = getOrderReceivedID;
        } else {
            OrderReceived = "";
        }

        ActionBy = spnActionCoburg.getSelectedItem().toString();
        Remarks = et_remarks.getText().toString();
        UpStatus = "N";
        CreatedBy = employeeID;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        CreatedOn = currentDateandTime;
        String lat = String.valueOf(latitude);
        String lon = String.valueOf(longitude);

        DistrictID();
        VisitRefID();
        IndustryID();
        ProcessID();
        ProjectStatusID();
        OutcomeID();
        ActionCoburgID();

        db.insertProspect(CompanyName, FullAddress, getDistrictID,
                Pincode, FDAPersonName, FDAPersonDesignation, PersonMeet,
                Designation, TeleNoSTD, TeleNo, PhoneNo, Email, WebSite, FotoVCardFront,
                FotoVCardBack, FotoPerson, AnyPersonMetYesNo, getVisitRefID,
                getIndustryID,
                getProcessID, EndProductMfrd, ProductClassify, WriteUp,
                getProjectStatusID, ProjectCompleteBy,
                ProjectDeliveyBefore, DtlsTechYesNo, CustomerTechYesNo,
                CLPYesNo, OfferedYesNo, OfferedHowMuch, ScopeofSupply,
                TechFeatures, Quality, ServiceSupport, DeliverySchedule,
                ModeSale, Price, Payment, Warranty, Other,
                getOutcomeProspectID, OrderReceived, getActionCoburgID, Remarks,
                UpStatus, CoEmployee, TypeOfCall, CallDateTime, "New", CreatedBy,
                CreatedOn,lat,lon);

        db.insertCompany("Prospect", CompanyName, Pincode, TeleNoSTD, TeleNo, PhoneNo, WebSite, Email, District, "Prospect", "1",FullAddress);
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

    private void DistrictID() {
        String type = spnDistrict.getSelectedItem().toString();

        try {
            Cursor c = db.getDistrictID(type);
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

    private void VisitRefID() {
        String type = spnVisitReference.getSelectedItem().toString();

        try {
            Cursor c = db.getVisitRefID(type);
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

    private void IndustryID() {
        String type = spnIndustry.getSelectedItem().toString();

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

    private void ProcessID() {
        String type = spnProcess.getSelectedItem().toString();

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

    private void ProjectStatusID() {
        String type = spnProjectstatus.getSelectedItem().toString();

        try {
            Cursor c = db.getProjectStatusID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getProjectStatusID = c.getString(0);

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

    private void OutcomeID() {
        String type = spnOutcome.getSelectedItem().toString();

        try {
            Cursor c = db.getOutcomeProspectID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getOutcomeProspectID = c.getString(0);

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

    private void OutcomeOrderReceivedID() {
        String type = spnOrderReceived.getSelectedItem().toString();

        try {
            Cursor c = db.getOrderReceivedID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getOrderReceivedID = c.getString(0);

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

    private void ActionCoburgID() {
        String type = spnActionCoburg.getSelectedItem().toString();

        try {
            Cursor c = db.getProspectByCoburgID(type);
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
}
