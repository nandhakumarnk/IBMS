package com.rd.strivos.cobby;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created by COBURG DESIGN on 01-02-2016.
 */
public class EnquiryBank extends Fragment {


    public EnquiryBank() {

    }

    private TextView txtEnquiryNo, txtEnquiryDate, txtEnquiryReceivedBy;
    private Spinner spnEnquirySource, spnMediaMediator, spnDistrict, spnEnquiryExecutor;
    private EditText _ReferenceNo, _RefDate, _EnquiryProviderName, _Customer, _Address, _Pincode, _ContactPerson, _ContactPersonNo,
            _STDCode, _TelephonNo, _Website, _Email, _ProductRequirement, _ReqOnOrBefore, _Remarks;
    private TextInputLayout inputLayoutReferenceNo, inputLayoutRefDate, inputLayoutEnquiryProviderName, inputLayoutCustomer,
            inputLayoutAddress, inputLayoutPincode, inputLayoutContactPerson, inputLayoutContactPersonNo, inputLayoutSTDCode,
            inputLayoutTelephonNo, inputLayoutWebsite,
            inputLayoutEmail, inputLayoutProductRequirement, inputLayoutReqOnOrBefore, inputLayoutRemarks;
    Button btnSave, btnClear;
    String EnquireDate, GetEnquirySourceID, GetMediaOrMediatorID, GetDistrictID, GetEnquiryExecutorID, EnquiryReceivedBy, GetLoginUserID;
    private int day, month, year;
    int dueDate, dueMonth, dueYear;
    private Calendar cal;
    SQLiteHelper db;
    EnquiryBankDBHelper enquiryBankDBHelper;
    Vector<String> vecDistrict, vecEnquirySource, vecEmployee;
    List<String> lsDistrict = new ArrayList<String>();
    List<String> lsEnquirySource = new ArrayList<String>();
    List<String> lsEmployee = new ArrayList<String>();
    ArrayAdapter<String> adptDistrict, adptEnquirySource, adptEmployee;
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.enquiry_bank, container, false);
        findViewByID();

        _RefDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().showDialog(0);
            }
        });

        spnEnquirySource.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String EnquirySource = arg0.getItemAtPosition(arg2).toString();
                EnquirySourceIDValue(EnquirySource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        _ReqOnOrBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().showDialog(1);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEnquiryBank();
            }
        });

        return rootView;
    }

    private void findViewByID() {
        txtEnquiryNo = (TextView) rootView.findViewById(R.id.txt_enquiry_no_eq);
        txtEnquiryDate = (TextView) rootView.findViewById(R.id.txt_date_eq);
        inputLayoutReferenceNo = (TextInputLayout) rootView.findViewById(R.id.input_layout_reference_no_eq);
        _ReferenceNo = (EditText) rootView.findViewById(R.id.et_reference_no_eq);
        inputLayoutRefDate = (TextInputLayout) rootView.findViewById(R.id.input_layout_ref_date_eq);
        _RefDate = (EditText) rootView.findViewById(R.id.et_ref_date_eq);
        txtEnquiryReceivedBy = (TextView) rootView.findViewById(R.id.txt_enq_received_by_eq);
        spnEnquirySource = (Spinner) rootView.findViewById(R.id.spn_enquiry_source_eq);
        spnMediaMediator = (Spinner) rootView.findViewById(R.id.spn_media_mediator_eq);
        inputLayoutEnquiryProviderName = (TextInputLayout) rootView.findViewById(R.id.input_layout_enquiry_provider_name_eq);
        _EnquiryProviderName = (EditText) rootView.findViewById(R.id.et_enquiry_provider_name_eq);
        inputLayoutCustomer = (TextInputLayout) rootView.findViewById(R.id.input_layout_customer_eq);
        _Customer = (EditText) rootView.findViewById(R.id.et_customer_eq);
        inputLayoutAddress = (TextInputLayout) rootView.findViewById(R.id.input_layout_address_eq);
        _Address = (EditText) rootView.findViewById(R.id.et_address_eq);
        inputLayoutPincode = (TextInputLayout) rootView.findViewById(R.id.input_layout_pincode_eq);
        _Pincode = (EditText) rootView.findViewById(R.id.et_pincode_eq);
        spnDistrict = (Spinner) rootView.findViewById(R.id.spn_district_eq);
        inputLayoutContactPerson = (TextInputLayout) rootView.findViewById(R.id.input_layout_contact_person_eq);
        _ContactPerson = (EditText) rootView.findViewById(R.id.et_contact_person_eq);
        inputLayoutContactPersonNo = (TextInputLayout) rootView.findViewById(R.id.input_layout_contact_person_no_eq);
        _ContactPersonNo = (EditText) rootView.findViewById(R.id.et_contact_person_no_eq);
        inputLayoutSTDCode = (TextInputLayout) rootView.findViewById(R.id.input_layout_STD_code_eq);
        _STDCode = (EditText) rootView.findViewById(R.id.et_std_code_eq);
        inputLayoutTelephonNo = (TextInputLayout) rootView.findViewById(R.id.input_layout_telephone_eq);
        _TelephonNo = (EditText) rootView.findViewById(R.id.et_person_met_tele_phone_eq);
        inputLayoutWebsite = (TextInputLayout) rootView.findViewById(R.id.input_layout_website_eq);
        _Website = (EditText) rootView.findViewById(R.id.et_website_eq);
        inputLayoutEmail = (TextInputLayout) rootView.findViewById(R.id.input_layout_email_eq);
        _Email = (EditText) rootView.findViewById(R.id.et_email_eq);
        inputLayoutProductRequirement = (TextInputLayout) rootView.findViewById(R.id.input_layout_Product_Requirement_eq);
        _ProductRequirement = (EditText) rootView.findViewById(R.id.et_Product_Requirement_eq);
        inputLayoutReqOnOrBefore = (TextInputLayout) rootView.findViewById(R.id.input_layout_req_on_before_eq);
        _ReqOnOrBefore = (EditText) rootView.findViewById(R.id.et_req_on_before_eq);
        spnEnquiryExecutor = (Spinner) rootView.findViewById(R.id.spn_Enquiry_Executor_eq);
        inputLayoutRemarks = (TextInputLayout) rootView.findViewById(R.id.input_layout_remarks_eq);
        _Remarks = (EditText) rootView.findViewById(R.id.et_remarks_eq);
        btnSave = (Button) rootView.findViewById(R.id.btn_save_eq);
        btnClear = (Button) rootView.findViewById(R.id.btn_clear_eq);
        db = new SQLiteHelper(getActivity());
        enquiryBankDBHelper = new EnquiryBankDBHelper(getActivity());
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        vecDistrict = new Vector<String>();
        vecEnquirySource = new Vector<String>();
        vecEmployee = new Vector<String>();

        EmployeeName();
        CallEnquireDate();
        LoadEnquirySource();
        LoadDistrict();
        LoadEnquiryExecutor();
    }

    private void EmployeeName() {
        try {
            Cursor c = db.SelectUser();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                EnquiryReceivedBy = c.getString(1);
                                Log.i("sdv", EnquiryReceivedBy);

                            }
                        }
                    }
                }
            }

            txtEnquiryReceivedBy.setText(EnquiryReceivedBy);
        } catch (Exception ex) {

        }
    }

    private void CallEnquireDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTimeForDB = sdfForDB.format(new Date());
        txtEnquiryDate.setText(currentDateandTime);
        EnquireDate = currentDateandTimeForDB;
    }

    public void LoadEnquirySource() {

        try {
            Cursor c = enquiryBankDBHelper.getEnquirySource();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecEnquirySource.add(c.getString(2));
                    } while (c.moveToNext());
                }
            }

            for (int i = 0; i < vecEnquirySource.size(); i++) {
                lsEnquirySource.add(vecEnquirySource.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsEnquirySource);
            lsEnquirySource.clear();
            lsEnquirySource.addAll(ts);
            db.close();

            adptEnquirySource = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lsEnquirySource);
            adptEnquirySource
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnEnquirySource.setAdapter(adptEnquirySource);
            spnEnquirySource.setWillNotDraw(false);

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void EnquirySourceIDValue(String EnquirySource) {
        String EnquirySourceID = null;
        try {
            Cursor c = enquiryBankDBHelper.getEnquirySourceIDValue(EnquirySource);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        EnquirySourceID = c.getString(0);
                    } while (c.moveToNext());
                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        LoadMediaMediator(EnquirySourceID);
    }

    private void LoadMediaMediator(String EnquirySourceID) {

        Vector<String> vecMediaMediator = new Vector<String>();
        List<String> lsMediaMediator = new ArrayList<String>();
        ArrayAdapter<String> adptMediaMediator;
        try {
            Cursor c = enquiryBankDBHelper.getMediaMediator(EnquirySourceID);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecMediaMediator.add(c.getString(2));
                    } while (c.moveToNext());
                }
            }

            for (int i = 0; i < vecMediaMediator.size(); i++) {
                lsMediaMediator.add(vecMediaMediator.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsMediaMediator);
            lsMediaMediator.clear();
            lsMediaMediator.addAll(ts);
            db.close();

        } catch (
                Exception e
                )

        {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // MediaMediator
        adptMediaMediator = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lsMediaMediator);
        adptMediaMediator
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMediaMediator.setAdapter(adptMediaMediator);
        spnMediaMediator.setWillNotDraw(false);
    }

    public void LoadDistrict() {

        try {
            Cursor c = db.selectDistrict();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecDistrict.add(c.getString(2));
                    } while (c.moveToNext());
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

            // District
            adptDistrict = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lsDistrict);
            adptDistrict
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnDistrict.setAdapter(adptDistrict);
            spnDistrict.setWillNotDraw(false);

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void LoadEnquiryExecutor() {
        try {
            Cursor c = db.selectEmployee();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecEmployee.add(c.getString(0));
                    } while (c.moveToNext());
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

            adptEmployee = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lsEmployee);
            adptEmployee
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnEnquiryExecutor.setAdapter(adptEmployee);
            spnEnquiryExecutor.setWillNotDraw(false);


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Deprecated
    protected Dialog onCreateDialog(int id) {

        if (id == 0) {
            return new DatePickerDialog(getActivity(), datePickerListener, year, month,
                    day);
        }
        if (id == 1) {
            return new DatePickerDialog(getActivity(), datePickerListener1, year, month,
                    day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            dueDate = selectedDay;
            dueMonth = selectedMonth;
            dueYear = selectedYear;

            _RefDate.setText(new StringBuilder().append(pad(selectedDay))
                    .append("/").append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            dueDate = selectedDay;
            dueMonth = selectedMonth;
            dueYear = selectedYear;

            _ReqOnOrBefore.setText(new StringBuilder().append(pad(selectedDay))
                    .append("/").append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));
        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void clearAll() {
        _ReferenceNo.setText("");
        _RefDate.setText("");
        _EnquiryProviderName.setText("");
        _Customer.setText("");
        _Address.setText("");
        _Pincode.setText("");
        _ContactPerson.setText("");
        _ContactPersonNo.setText("");
        _STDCode.setText("");
        _TelephonNo.setText("");
        _Website.setText("");
        _Email.setText("");
        _ProductRequirement.setText("");
        _ReqOnOrBefore.setText("");
        _Remarks.setText("");
    }

    private void saveEnquiryBank() {
        String EnquiryNo = txtEnquiryNo.getText().toString();
        String EnquiryDate = txtEnquiryDate.getText().toString().trim();
        String ReferenceNo = _ReferenceNo.getText().toString().trim();
        String ReferenceDate = _RefDate.getText().toString().trim();
        String EnquiryReceivedBy = txtEnquiryReceivedBy.getText().toString();
        EnquiryReceivedByID(EnquiryReceivedBy);
        String EnquiryReceivedByID = GetLoginUserID;
        String EnquirySource = spnEnquirySource.getSelectedItem().toString();
        EnquirySourceID(EnquirySource);
        String EnquirySourceID = GetEnquirySourceID;
        String MediaOrMediator = spnMediaMediator.getSelectedItem().toString();
        MediaOrMediatorID(MediaOrMediator);
        String MediaOrMediatorID = GetMediaOrMediatorID;
        String EnquiryProviderName = _EnquiryProviderName.getText().toString().trim();
        String Customer = _Customer.getText().toString().trim();
        String Address = _Address.getText().toString().trim();
        String Pincode = _Pincode.getText().toString().trim();
        String District = spnDistrict.getSelectedItem().toString();
        DistrictID(District);
        String DistrictID = GetDistrictID;
        String ContactPerson = _ContactPerson.getText().toString().trim();
        String ContactPersonNo = _ContactPersonNo.getText().toString().trim();
        String STDNo = _STDCode.getText().toString().trim();
        String TelePhoneNo = _TelephonNo.getText().toString().trim();
        String Website = _Website.getText().toString().trim();
        String Email = _Email.getText().toString().trim();
        String ProductRequirement = _ProductRequirement.getText().toString().trim();
        String ReqOnOrBefore = _ReqOnOrBefore.getText().toString().trim();
        String EnquiryExecutor = spnEnquiryExecutor.getSelectedItem().toString();
        EnquiryExecutorID(EnquiryExecutor);
        String EnquiryExecutorID = GetEnquiryExecutorID;
        String Remarks = _Remarks.getText().toString().trim();
        String ProjectType = "EnquiryBank";
        String ProjectName = Customer;
        String UpStatus = "N";
        getCurrentLocation();
        String lat = String.valueOf(latitude);
        String lon = String.valueOf(longitude);

//        if (!validateReferenceNo()) {
//            return;
//        }
//        if (!validateReferenceDate()) {
//            return;
//        }
        if (!validateEnquiryProviderName()) {
            return;
        }
        if (!validateCustomer()) {
            return;
        }
        if (!validateAddress()) {
            return;
        }
        if (!validatePincode()) {
            return;
        }
        if (!validateContactPerson()) {
            return;
        }
        if (!validateContactPersonNo()) {
            return;
        }
        if (!validateSTDNo()) {
            return;
        }
        if (!validateTelePhoneNo()) {
            return;
        }
        if (!validateWebsite()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        if (!validateProductRequirement()) {
            return;
        }
        if (!validateReqOnOrBefore()) {
            return;
        }
        if (!validateRemarks()) {
            return;
        }

        enquiryBankDBHelper.insertEnquiryBank(EnquiryNo, EnquiryDate, ReferenceNo, ReferenceDate, EnquiryReceivedByID, EnquirySourceID,
                MediaOrMediatorID, EnquiryProviderName, Customer, Address, Pincode, DistrictID, ContactPerson, ContactPersonNo, STDNo,
                TelePhoneNo, Website, Email, ProductRequirement, ReqOnOrBefore, EnquiryExecutorID, Remarks, ProjectType, ProjectName,
                UpStatus, lat, lon);
        Toast.makeText(getActivity(), "Enquiry Bank Added !!!", Toast.LENGTH_LONG)
                .show();
        clearAll();
        //finish();
    }

    private boolean validateReferenceNo() {
        if (_ReferenceNo.getText().toString().trim().isEmpty()) {
            inputLayoutReferenceNo.setError(getString(R.string.err_ReferenceNo));
            requestFocus(_ReferenceNo);
            return false;
        } else {
            inputLayoutReferenceNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateReferenceDate() {
        if (_RefDate.getText().toString().trim().isEmpty()) {
            inputLayoutRefDate.setError(getString(R.string.err_ReferenceDate));
            requestFocus(_RefDate);
            return false;
        } else {
            inputLayoutRefDate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEnquiryProviderName() {
        if (_EnquiryProviderName.getText().toString().trim().isEmpty()) {
            inputLayoutEnquiryProviderName.setError(getString(R.string.err_EnquiryProviderName));
            requestFocus(_EnquiryProviderName);
            return false;
        } else {
            inputLayoutEnquiryProviderName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCustomer() {
        if (_Customer.getText().toString().trim().isEmpty()) {
            inputLayoutCustomer.setError(getString(R.string.err_Customer));
            requestFocus(_Customer);
            return false;
        } else {
            inputLayoutCustomer.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddress() {
        if (_Address.getText().toString().trim().isEmpty()) {
            inputLayoutAddress.setError(getString(R.string.err_Address));
            requestFocus(_Address);
            return false;
        } else {
            inputLayoutAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePincode() {
        if (_Pincode.getText().toString().trim().isEmpty()) {
            inputLayoutPincode.setError(getString(R.string.err_Pincode));
            requestFocus(_Pincode);
            return false;
        } else {
            inputLayoutPincode.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateContactPerson() {
        if (_ContactPerson.getText().toString().trim().isEmpty()) {
            inputLayoutContactPerson.setError(getString(R.string.err_ContactPerson));
            requestFocus(_ContactPerson);
            return false;
        } else {
            inputLayoutContactPerson.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateContactPersonNo() {
        if (_ContactPersonNo.getText().toString().trim().isEmpty()) {
            inputLayoutContactPersonNo.setError(getString(R.string.err_ContactPersonNo));
            requestFocus(_ContactPersonNo);
            return false;
        } else {
            inputLayoutContactPersonNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSTDNo() {
        if (_STDCode.getText().toString().trim().isEmpty()) {
            inputLayoutSTDCode.setError(getString(R.string.err_StdCode));
            requestFocus(_STDCode);
            return false;
        } else {
            inputLayoutSTDCode.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateTelePhoneNo() {
        if (_TelephonNo.getText().toString().trim().isEmpty()) {
            inputLayoutTelephonNo.setError(getString(R.string.err_Telephone));
            requestFocus(_TelephonNo);
            return false;
        } else {
            inputLayoutTelephonNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateWebsite() {
        if (_Website.getText().toString().trim().isEmpty()) {
            inputLayoutWebsite.setError(getString(R.string.err_Website));
            requestFocus(_Website);
            return false;
        } else {
            inputLayoutWebsite.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email = _Email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(_Email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validateProductRequirement() {
        if (_ProductRequirement.getText().toString().trim().isEmpty()) {
            inputLayoutProductRequirement.setError(getString(R.string.err_ProductRequirement));
            requestFocus(_ProductRequirement);
            return false;
        } else {
            inputLayoutProductRequirement.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateReqOnOrBefore() {
        if (_ReqOnOrBefore.getText().toString().trim().isEmpty()) {
            inputLayoutReqOnOrBefore.setError(getString(R.string.err_ReqOnOrBefore));
            requestFocus(_ReqOnOrBefore);
            return false;
        } else {
            inputLayoutReqOnOrBefore.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRemarks() {
        if (_Remarks.getText().toString().trim().isEmpty()) {
            inputLayoutRemarks.setError(getString(R.string.err_Remarks));
            requestFocus(_Remarks);
            return false;
        } else {
            inputLayoutRemarks.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void EnquirySourceID(String EnquirySource) {
        try {
            Cursor c = enquiryBankDBHelper.getEnquirySourceIDValue(EnquirySource);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        String GetID = c.getString(0);
                        GetEnquirySourceID = GetID;
                    } while (c.moveToNext());
                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void MediaOrMediatorID(String MediaOrMediator) {
        try {
            Cursor c = enquiryBankDBHelper.getMediaMediatorIDValue(MediaOrMediator);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        String GetID = c.getString(0);
                        GetMediaOrMediatorID = GetID;
                    } while (c.moveToNext());
                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void DistrictID(String District) {

        try {
            Cursor c = db.getDistrictID(District);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        String GetID = c.getString(0);
                        GetDistrictID = GetID;
                    } while (c.moveToNext());
                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private String EnquiryExecutorID(String EnquiryExecutor) {
        String type = null;
        try {
            Cursor c = db.getEmployeeID(EnquiryExecutor);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        String GetID = c.getString(0);
                        GetEnquiryExecutorID = GetID;
                    } while (c.moveToNext());
                }
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return type;
    }

    private void EnquiryReceivedByID(String EnquiryReceivedBy) {
        try {
            Cursor c = db.SelectUser();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        String GetID = c.getString(0);
                        GetLoginUserID = GetID;
                        Log.i("sdv", GetLoginUserID);
                    } while (c.moveToNext());
                }
            }
        } catch (Exception ex) {

        }
    }

    public void getCurrentLocation() {
        latitude = 0.0;
        longitude = 0.0;
        GPSTracker gps = new GPSTracker(getActivity());
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        } else {
            gps.showSettingsAlert();
        }

    }
}
