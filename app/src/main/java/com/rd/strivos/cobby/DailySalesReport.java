package com.rd.strivos.cobby;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

public class DailySalesReport extends Fragment {

    View rootView;
    Button AddNew, addProsp, addOEM, addConsultant, addDealer, Find,
            addOldCustomer, addOldProsp, addOldOEM, addOldConsultant,
            addOldDealer;

    ImageButton img_datepicker;

    private TextView et_datePicker,
            et_companytype, et_logging;

    Spinner spnEmployee, spnContactMode, spnCompany;
    SQLiteHelper db;
    Vector<String> vecCompany, vecEmployee, vecTypeOfCall;
    List<String> lsCompany = new ArrayList<String>();
    List<String> lsEmployee = new ArrayList<String>();
    List<String> lsTypeOfCall = new ArrayList<String>();
    ArrayAdapter<String> adptCompany, adptEmployee, adptTypeOfCall;
    LinearLayout ll;

    String employeeName, callDateTime, getcoEmployeeID,
            CompanyName, typeOfCallID, companyType, getACCOUNTNAME, getSTD, getPINCODE,
            getPHONE, getMOBILE, getWEBSITE, getEMAIL, getDISTRICT = "", GetAddress;
    LinearLayout companytype_layout;

    View one;

    public DailySalesReport() {
        // TODO Auto-generated constructor stub
    }

    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        rootView = inflater.inflate(R.layout.daily_sales_report, container,
                false);
        one = (View) rootView.findViewById(R.id.one);
        AddNew = (Button) rootView.findViewById(R.id.btn_addnewdtls);
        addProsp = (Button) rootView.findViewById(R.id.btn_addnewprosp);
        addOEM = (Button) rootView.findViewById(R.id.btn_addnewOEM);
        addConsultant = (Button) rootView
                .findViewById(R.id.btn_addnewConsultant);
        addOldProsp = (Button) rootView.findViewById(R.id.btn_addoldprosp);
        addOldOEM = (Button) rootView.findViewById(R.id.btn_addoldOEM);
        addOldConsultant = (Button) rootView
                .findViewById(R.id.btn_addoldConsultant);
        addOldDealer = (Button) rootView.findViewById(R.id.btn_addoldDealer);
        addOldCustomer = (Button) rootView
                .findViewById(R.id.btn_addOldCustomer);
        addDealer = (Button) rootView.findViewById(R.id.btn_addnewDealer);
        Find = (Button) rootView.findViewById(R.id.btn_find);
        img_datepicker = (ImageButton) rootView
                .findViewById(R.id.img_datepicker);
        et_datePicker = (TextView) rootView.findViewById(R.id.et_datepicker);
        et_companytype = (TextView) rootView.findViewById(R.id.et_companytype);
        et_logging = (TextView) rootView.findViewById(R.id.et_logging);
        spnEmployee = (Spinner) rootView
                .findViewById(R.id.spn_selectAccompanied);
        spnContactMode = (Spinner) rootView.findViewById(R.id.spn_ContactMode);
        spnCompany = (Spinner) rootView.findViewById(R.id.spn_selectcompany);
        ll = (LinearLayout) rootView.findViewById(R.id.ll_chosebtn);
        companytype_layout = (LinearLayout) rootView.findViewById(R.id.ll_companytype);
        vecCompany = new Vector<String>();
        vecEmployee = new Vector<String>();
        vecTypeOfCall = new Vector<String>();
        db = new SQLiteHelper(getActivity());

        EmployeeID();
        getfromdatabase();
        getEmployee();
        loadSpinner();
        loadSpinnerEmployee();
        CallDateandTime();
        loadTypeOfCall();

        AddNew.setOnClickListener(new OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {

                if (CompanyName.equalsIgnoreCase("None")) {
                    firsttype();
                }
//                Intent myIntent = new Intent(getActivity(),
//                        EnquiryBank.class);
//                myIntent.putExtra("employeeName", employeeName);
//                getActivity().startActivity(myIntent);

            }
        });

        addProsp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        NewProspectFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                getActivity().startActivity(myIntent);
            }
        });

        addOEM.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        NewOEMFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                getActivity().startActivity(myIntent);
            }
        });

        addConsultant.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        NewConsultantFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                getActivity().startActivity(myIntent);
            }
        });

        addDealer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        NewDealerFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                getActivity().startActivity(myIntent);
            }
        });

        addOldProsp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent myIntent = new Intent(getActivity(),
                        ExistingProspectFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                myIntent.putExtra("ACCOUNTNAME", getACCOUNTNAME);
                myIntent.putExtra("PINCODE", getPINCODE);
                myIntent.putExtra("STD", getSTD);
                myIntent.putExtra("PHONE", getPHONE);
                myIntent.putExtra("MOBILE", getMOBILE);
                myIntent.putExtra("WEBSITE", getWEBSITE);
                myIntent.putExtra("EMAIL", getEMAIL);
                myIntent.putExtra("DISTRICT", getDISTRICT);
                myIntent.putExtra("Address", GetAddress);
                getActivity().startActivity(myIntent);
            }
        });

        addOldOEM.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        ExistingOEMFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                myIntent.putExtra("ACCOUNTNAME", getACCOUNTNAME);
                myIntent.putExtra("PINCODE", getPINCODE);
                myIntent.putExtra("PHONE", getSTD);
                myIntent.putExtra("PHONE", getPHONE);
                myIntent.putExtra("MOBILE", getMOBILE);
                myIntent.putExtra("WEBSITE", getWEBSITE);
                myIntent.putExtra("EMAIL", getEMAIL);
                myIntent.putExtra("DISTRICT", getDISTRICT);
                myIntent.putExtra("Address", GetAddress);
                getActivity().startActivity(myIntent);
            }
        });

        addOldConsultant.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        ExistingConsultantFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                myIntent.putExtra("ACCOUNTNAME", getACCOUNTNAME);
                myIntent.putExtra("PINCODE", getPINCODE);
                myIntent.putExtra("STD", getSTD);
                myIntent.putExtra("PHONE", getPHONE);
                myIntent.putExtra("MOBILE", getMOBILE);
                myIntent.putExtra("WEBSITE", getWEBSITE);
                myIntent.putExtra("EMAIL", getEMAIL);
                myIntent.putExtra("DISTRICT", getDISTRICT);
                myIntent.putExtra("Address", GetAddress);
                getActivity().startActivity(myIntent);
            }
        });

        addOldDealer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        NewDealerFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                myIntent.putExtra("ACCOUNTNAME", getACCOUNTNAME);
                myIntent.putExtra("PINCODE", getPINCODE);
                myIntent.putExtra("STD", getSTD);
                myIntent.putExtra("PHONE", getPHONE);
                myIntent.putExtra("MOBILE", getMOBILE);
                myIntent.putExtra("WEBSITE", getWEBSITE);
                myIntent.putExtra("EMAIL", getEMAIL);
                myIntent.putExtra("DISTRICT", getDISTRICT);
                myIntent.putExtra("Address", GetAddress);
                getActivity().startActivity(myIntent);
            }
        });

        addOldCustomer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent myIntent = new Intent(getActivity(),
                        OldCustomerFragment.class);
                myIntent.putExtra("CoEmployee", getcoEmployeeID);
                myIntent.putExtra("TypeOfCall", typeOfCallID);
                myIntent.putExtra("CallDateTime", callDateTime);
                myIntent.putExtra("ACCOUNTNAME", getACCOUNTNAME);
                myIntent.putExtra("PINCODE", getPINCODE);
                myIntent.putExtra("STD", getSTD);
                myIntent.putExtra("PHONE", getPHONE);
                myIntent.putExtra("MOBILE", getMOBILE);
                myIntent.putExtra("WEBSITE", getWEBSITE);
                myIntent.putExtra("EMAIL", getEMAIL);
                myIntent.putExtra("DISTRICT", getDISTRICT);
                myIntent.putExtra("Address", GetAddress);
                getActivity().startActivity(myIntent);
            }
        });

        Find.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (companyType.equalsIgnoreCase("Prospect")) {
                    ll.setVisibility(View.VISIBLE);
                    addOldProsp.setVisibility(View.VISIBLE);
                    addOldDealer.setVisibility(View.GONE);
                    addOldOEM.setVisibility(View.GONE);
                    addOldConsultant.setVisibility(View.GONE);
                    addOldCustomer.setVisibility(View.GONE);
                    addProsp.setVisibility(View.GONE);
                    addDealer.setVisibility(View.GONE);
                    addOEM.setVisibility(View.GONE);
                    addConsultant.setVisibility(View.GONE);
                }
                if (companyType.equalsIgnoreCase("Dealer")) {
                    ll.setVisibility(View.VISIBLE);
                    addOldProsp.setVisibility(View.GONE);
                    addOldDealer.setVisibility(View.VISIBLE);
                    addOldOEM.setVisibility(View.GONE);
                    addOldConsultant.setVisibility(View.GONE);
                    addOldCustomer.setVisibility(View.GONE);
                    addProsp.setVisibility(View.GONE);
                    addDealer.setVisibility(View.GONE);
                    addOEM.setVisibility(View.GONE);
                    addConsultant.setVisibility(View.GONE);

                }
                if (companyType.equalsIgnoreCase("OEM")) {
                    ll.setVisibility(View.VISIBLE);
                    addOldProsp.setVisibility(View.GONE);
                    addOldDealer.setVisibility(View.GONE);
                    addOldOEM.setVisibility(View.VISIBLE);
                    addOldConsultant.setVisibility(View.GONE);
                    addOldCustomer.setVisibility(View.GONE);
                    addProsp.setVisibility(View.GONE);
                    addDealer.setVisibility(View.GONE);
                    addOEM.setVisibility(View.GONE);
                    addConsultant.setVisibility(View.GONE);
                }
                if (companyType.equalsIgnoreCase("Consultancy")) {
                    ll.setVisibility(View.VISIBLE);
                    addOldProsp.setVisibility(View.GONE);
                    addOldDealer.setVisibility(View.GONE);
                    addOldOEM.setVisibility(View.GONE);
                    addOldConsultant.setVisibility(View.VISIBLE);
                    addOldCustomer.setVisibility(View.GONE);
                    addProsp.setVisibility(View.GONE);
                    addDealer.setVisibility(View.GONE);
                    addOEM.setVisibility(View.GONE);
                    addConsultant.setVisibility(View.GONE);
                }
                if (companyType.equalsIgnoreCase("Customer")) {
                    ll.setVisibility(View.VISIBLE);
                    addOldProsp.setVisibility(View.GONE);
                    addOldDealer.setVisibility(View.GONE);
                    addOldOEM.setVisibility(View.GONE);
                    addOldConsultant.setVisibility(View.GONE);
                    addOldCustomer.setVisibility(View.VISIBLE);
                    addProsp.setVisibility(View.GONE);
                    addDealer.setVisibility(View.GONE);
                    addOEM.setVisibility(View.GONE);
                    addConsultant.setVisibility(View.GONE);
                }

            }
        });

        img_datepicker.setOnClickListener(new OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                CallDateandTime();
            }
        });

        spnCompany.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String companyname = arg0.getItemAtPosition(arg2).toString();
                CompanyName = companyname;
                getExistingProspectDtls(companyname);
                if (companyname.equalsIgnoreCase("Select")) {
                    companytype_layout.setVisibility(View.GONE);
                    et_companytype.setText("New Visit");
                    LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.ll_chosebtn);
                    ll.setVisibility(View.GONE);
                    AddNew.setVisibility(View.GONE);
                    one.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                    Find.setVisibility(View.GONE);

                } else if (companyname.equalsIgnoreCase("None")) {
                    companytype_layout.setVisibility(View.VISIBLE);
                    et_companytype.setText("New Visit");
                    AddNew.setVisibility(View.GONE);
                    one.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                    Find.setVisibility(View.GONE);
                    if (CompanyName.equalsIgnoreCase("None")) {
                        firsttype();
                        //AddNew.setVisibility(View.VISIBLE);
                    }
                } else {
                    companytype_layout.setVisibility(View.VISIBLE);
                    Find.setVisibility(View.GONE);
                    AddNew.setVisibility(View.GONE);
                    one.setVisibility(View.GONE);
                    ll.setVisibility(View.GONE);
                    selectCompanyType(companyname);

                    if (companyType.equalsIgnoreCase("Prospect")) {
                        ll.setVisibility(View.VISIBLE);
                        addOldProsp.setVisibility(View.VISIBLE);
                        addOldDealer.setVisibility(View.GONE);
                        addOldOEM.setVisibility(View.GONE);
                        addOldConsultant.setVisibility(View.GONE);
                        addOldCustomer.setVisibility(View.GONE);
                        addProsp.setVisibility(View.GONE);
                        addDealer.setVisibility(View.GONE);
                        addOEM.setVisibility(View.GONE);
                        addConsultant.setVisibility(View.GONE);
                    }
                    if (companyType.equalsIgnoreCase("Dealer")) {
                        ll.setVisibility(View.VISIBLE);
                        addOldProsp.setVisibility(View.GONE);
                        addOldDealer.setVisibility(View.VISIBLE);
                        addOldOEM.setVisibility(View.GONE);
                        addOldConsultant.setVisibility(View.GONE);
                        addOldCustomer.setVisibility(View.GONE);
                        addProsp.setVisibility(View.GONE);
                        addDealer.setVisibility(View.GONE);
                        addOEM.setVisibility(View.GONE);
                        addConsultant.setVisibility(View.GONE);

                    }
                    if (companyType.equalsIgnoreCase("OEM")) {
                        ll.setVisibility(View.VISIBLE);
                        addOldProsp.setVisibility(View.GONE);
                        addOldDealer.setVisibility(View.GONE);
                        addOldOEM.setVisibility(View.VISIBLE);
                        addOldConsultant.setVisibility(View.GONE);
                        addOldCustomer.setVisibility(View.GONE);
                        addProsp.setVisibility(View.GONE);
                        addDealer.setVisibility(View.GONE);
                        addOEM.setVisibility(View.GONE);
                        addConsultant.setVisibility(View.GONE);
                    }
                    if (companyType.equalsIgnoreCase("Consultancy")) {
                        ll.setVisibility(View.VISIBLE);
                        addOldProsp.setVisibility(View.GONE);
                        addOldDealer.setVisibility(View.GONE);
                        addOldOEM.setVisibility(View.GONE);
                        addOldConsultant.setVisibility(View.VISIBLE);
                        addOldCustomer.setVisibility(View.GONE);
                        addProsp.setVisibility(View.GONE);
                        addDealer.setVisibility(View.GONE);
                        addOEM.setVisibility(View.GONE);
                        addConsultant.setVisibility(View.GONE);
                    }
                    if (companyType.equalsIgnoreCase("Customer")) {
                        ll.setVisibility(View.VISIBLE);
                        addOldProsp.setVisibility(View.GONE);
                        addOldDealer.setVisibility(View.GONE);
                        addOldOEM.setVisibility(View.GONE);
                        addOldConsultant.setVisibility(View.GONE);
                        addOldCustomer.setVisibility(View.VISIBLE);
                        addProsp.setVisibility(View.GONE);
                        addDealer.setVisibility(View.GONE);
                        addOEM.setVisibility(View.GONE);
                        addConsultant.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnEmployee.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String CoEmployee = arg0.getItemAtPosition(arg2).toString();
                getCoEmployeeID(CoEmployee);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnContactMode.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String TypeCall = arg0.getItemAtPosition(arg2).toString();
                TypeOfCallID(TypeCall);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        return rootView;
    }

    private void getExistingProspectDtls(String companyname) {
        try {
            Cursor c = db.selectCompanyDtls(companyname);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            getACCOUNTNAME = c.getString(1);
                            getPINCODE = c.getString(2);
                            getSTD = c.getString(3);
                            getPHONE = c.getString(4);
                            getMOBILE = c.getString(5);
                            getWEBSITE = c.getString(6);
                            getEMAIL = c.getString(7);
                            getDISTRICT = c.getString(8);
                            GetAddress = c.getString(11);
                        }
                    }
                }
            }

            for (int i = 0; i < vecCompany.size(); i++) {
                lsCompany.add(vecCompany.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsCompany);
            lsCompany.clear();
            lsCompany.add("Select");
            lsCompany.add("None");
            lsCompany.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private String getCoEmployeeID(String Name) {
        String type = null;
        try {
            Cursor c = db.getEmployeeID(Name);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getcoEmployeeID = c.getString(0);

                        }
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return type;
    }

    private void CallDateandTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTimeForDB = sdfForDB.format(new Date());
        et_datePicker.setText(currentDateandTime);
        callDateTime = currentDateandTimeForDB;
    }

    private void loadSpinner() {

        adptCompany = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lsCompany);
        adptCompany
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCompany.setAdapter(adptCompany);
        spnCompany.setWillNotDraw(false);
    }

    private void loadSpinnerEmployee() {

        adptEmployee = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lsEmployee);
        adptEmployee
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEmployee.setAdapter(adptEmployee);
        spnEmployee.setWillNotDraw(false);
    }

    private void selectCompanyType(String Type) {
        try {
            Cursor c = db.selectCompanyType(Type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            companyType = c.getString(0);
                        }
                    }
                }
            }
            et_companytype.setText(companyType);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void loadTypeOfCall() {
        try {
            Cursor c = db.selectTypeOfCall();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecTypeOfCall.add(c.getString(2));
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < vecTypeOfCall.size(); i++) {
                lsTypeOfCall.add(vecTypeOfCall.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsTypeOfCall);
            lsTypeOfCall.clear();
            lsTypeOfCall.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        adptTypeOfCall = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lsTypeOfCall);
        adptTypeOfCall
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnContactMode.setAdapter(adptTypeOfCall);
        spnContactMode.setWillNotDraw(false);

    }

    private void TypeOfCallID(String TypeCall) {

        try {
            Cursor c = db.getTypeOfCallID(TypeCall);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            typeOfCallID = c.getString(0);
                            Log.i("sdv", typeOfCallID);
                        }
                    }
                }
            }

        } catch (Exception ex) {

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
                                employeeName = c.getString(1);
                                Log.i("sdv", employeeName);

                            }
                        }
                    }
                }
            }

            et_logging.setText(employeeName);
        } catch (Exception ex) {

        }
    }

    public void getfromdatabase() {
        try {
            Cursor c = db.selectCompany();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecCompany.add(c.getString(1));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecCompany.size(); i++) {
                lsCompany.add(vecCompany.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsCompany);
            lsCompany.clear();
            lsCompany.add("Select");
            lsCompany.add("None");
            lsCompany.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void getEmployee() {
        try {
            Cursor c = db.selectEmployee();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecEmployee.add(c.getString(0));

                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecEmployee.size(); i++) {
                lsEmployee.add(vecEmployee.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsEmployee);
            lsEmployee.clear();
            lsEmployee.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void firsttype() {

        ll.setVisibility(View.VISIBLE);
        addProsp.setVisibility(View.VISIBLE);
        addDealer.setVisibility(View.VISIBLE);
        addOEM.setVisibility(View.VISIBLE);
        addConsultant.setVisibility(View.VISIBLE);
        addOldProsp.setVisibility(View.GONE);
        addOldDealer.setVisibility(View.GONE);
        addOldOEM.setVisibility(View.GONE);
        addOldConsultant.setVisibility(View.GONE);
        addOldCustomer.setVisibility(View.GONE);
        retype();
    }

    public void retype() {

        AddNew.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ll.setVisibility(View.GONE);
                secontype();

            }

            public void secontype() {
                AddNew.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        firsttype();
                        retype();
                    }
                });
            }
        });
    }
}
