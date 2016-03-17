package com.rd.strivos.cobby;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created by COBURG DESIGN on 30-12-2015.
 */
public class DailyServiceVisit extends Fragment {
    View rootView;

    Button AddNew;
    ImageButton img_in_datepicker, img_in_timepicker, img_out_datepicker;
    private TextView et_in_datePicker, et_in_time_picker, et_out_datePicker, et_logging;
    Spinner spnEmployee, spnCustomer;
    SQLiteHelper db;
    EmployeeDBHelper employeeDBHelper;
    Vector<String> vecCustomer, vecEmployee;
    List<String> lsCustomer = new ArrayList<String>();
    List<String> lsEmployee = new ArrayList<String>();
    ArrayAdapter<String> adptCustomer, adptEmployee;

    String employeeName, inDateTime, outDateTime, CustomerName, getCompanyID,
            getACCOUNTNAME, getSTD, getPINCODE, getcoEmployeeID,
            getPHONE, getMOBILE, getWEBSITE, getEMAIL, getDISTRICT = "", GetAddress;
    int dueDate, dueMonth, dueYear, mDay, mMonth, mYear, mHours, mMinutes, mSeconds;

    public DailyServiceVisit() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.daily_service_visit, container, false);

        AddNew = (Button) rootView.findViewById(R.id.btn_addnewdtls);
        img_in_datepicker = (ImageButton) rootView.findViewById(R.id.img_in_datepicker);
        img_in_timepicker = (ImageButton) rootView.findViewById(R.id.img_timepicker);
        img_out_datepicker = (ImageButton) rootView.findViewById(R.id.img_out_datepicker);
        et_in_time_picker = (TextView) rootView.findViewById(R.id.et_timepicker);
        et_in_datePicker = (TextView) rootView.findViewById(R.id.et_in_datepicker);
        et_out_datePicker = (TextView) rootView.findViewById(R.id.et_out_datepicker);
        et_logging = (TextView) rootView.findViewById(R.id.et_logging);
        spnEmployee = (Spinner) rootView.findViewById(R.id.spn_selectAccompanied);
        spnCustomer = (Spinner) rootView.findViewById(R.id.spn_selectcustomer);
        vecCustomer = new Vector<String>();
        vecEmployee = new Vector<String>();
        db = new SQLiteHelper(getActivity());
        employeeDBHelper = new EmployeeDBHelper(getActivity());

        EmployeeID();
        getfromdatabase();
        getEmployee();
        loadSpinner();
        loadSpinnerEmployee();
        //inDateandTime();
        outDateandTime();

        img_in_datepicker.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //inDateandTime();
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                et_in_datePicker.setText(new StringBuilder().append(pad(dayOfMonth))
                                        .append("/").append(pad(monthOfYear + 1)).append("/")
                                        .append(pad(year)));
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        img_in_timepicker.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //inDateandTime();
                final Calendar c = Calendar.getInstance();
                mHours = c.get(Calendar.HOUR_OF_DAY);
                mMinutes = c.get(Calendar.MINUTE);

                TimePickerDialog tmd = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        StringBuilder _24HourTime = (new StringBuilder().append(
                                pad(hourOfDay)).append(":").append(pad(minute)).append(":").append(pad(00)));

                        String newString = _24HourTime.toString();

                        String _12HourTime = Convert24to12(newString);

                        et_in_time_picker.setText(_12HourTime);
                    }
                }, mHours, mMinutes, false);

                tmd.show();
            }
        });

        spnEmployee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        spnCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String customeryname = parent.getItemAtPosition(position).toString();
                CustomerName = customeryname;
                getExistingCustomer(customeryname);

                if (customeryname.equalsIgnoreCase("Select")) {
                    AddNew.setVisibility(View.GONE);
                } else if (customeryname.equalsIgnoreCase("None")) {
                    AddNew.setVisibility(View.GONE);
                } else {
                    AddNew.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inDate = et_in_datePicker.getText().toString();
                String inTime = et_in_time_picker.getText().toString();
                String inDateandTimeDB="";

                if ((inDate.length() == 0) || (inTime.length() == 0)) {
                    Toast.makeText(getActivity(), "Select Date and Time", Toast.LENGTH_LONG).show();
                } else {

                    String inDateandTime = inDate +" "+inTime;
                    try {
                        inDateandTimeDB = formatDate(inDateandTime,"dd/MM/yyyy hh:mm:ss a","yyyy-MM-dd HH:mm:ss");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Intent myIntent = new Intent(getActivity(),
                            ServiceVisitAdd.class);
                    myIntent.putExtra("CompanyID", getCompanyID);
                    myIntent.putExtra("CoEmployee", getcoEmployeeID);
                    myIntent.putExtra("inDateTime", inDateandTimeDB);
                    myIntent.putExtra("outDateTime", outDateTime);
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
            }
        });
        return rootView;
    }


    public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {

        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);

        return parsedDate;
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

    private void getfromdatabase() {
        try {
            Cursor c = db.selectCustomerOnly();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecCustomer.add(c.getString(1));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecCustomer.size(); i++) {
                lsCustomer.add(vecCustomer.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsCustomer);
            lsCustomer.clear();
            //lsCustomer.add("Select");
            //lsCustomer.add("None");
            lsCustomer.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void getEmployee() {
        try {
            Cursor c = employeeDBHelper.selectEmployeeService();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecEmployee.add(c.getString(1));

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
            lsEmployee.add("None");
            lsEmployee.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void loadSpinner() {

        adptCustomer = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lsCustomer);
        adptCustomer
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCustomer.setAdapter(adptCustomer);
        spnCustomer.setWillNotDraw(false);
    }

    private void loadSpinnerEmployee() {

        adptEmployee = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lsEmployee);
        adptEmployee
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEmployee.setAdapter(adptEmployee);
        spnEmployee.setWillNotDraw(false);
    }

    private void inDateandTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTimeForDB = sdfForDB.format(new Date());
        et_in_datePicker.setText(currentDateandTime);
        inDateTime = currentDateandTimeForDB;
    }

    private void outDateandTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTimeForDB = sdfForDB.format(new Date());
        et_out_datePicker.setText(currentDateandTime);
        outDateTime = currentDateandTimeForDB;
    }

    private void getExistingCustomer(String customeryname) {
        try {
            Cursor c = db.selectCompanyDtls(customeryname);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            getCompanyID = c.getString(0);
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

            for (int i = 0; i < vecCustomer.size(); i++) {
                lsCustomer.add(vecCustomer.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsCustomer);
            lsCustomer.clear();
            //lsCustomer.add("Select");
            //lsCustomer.add("None");
            lsCustomer.addAll(ts);
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
            else{
                getcoEmployeeID="None";
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return type;
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public static String Convert24to12(String time) {
        String convertedTime = "";
        try {
            SimpleDateFormat displayFormat = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = parseFormat.parse(time);
            convertedTime = displayFormat.format(date);
            System.out.println("convertedTime : " + convertedTime);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
        // Output will be 10:23 PM
    }
}
