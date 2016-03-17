package com.rd.strivos.cobby;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created by COBURG DESIGN on 11-02-2016.
 */
public class Commissioning extends Fragment {

    View rootView;
    SQLiteHelper db;
    ContactPersonDBHelper contactPersonDBHelper;
    ServiceVisitDBHelper serviceVisitDBHelper;
    CommissioningDBHelper commissioningDBHelper;

    public Commissioning() {
    }

    SharedPreferences prefs;
    private TextView txtServiceEngg, txtAddress, txtPincode, txtDistrict, txtContactPersonNo, txtEmail, txtProductName, txtMachineSlNo, txtPartNo;
    private Spinner spnCompany, spnContactPerson, spnContractType;
    private EditText etFromDate, etToDate, etContractAmount, etVisitCharges, etFreeVisits, etRemarks;
    private TextInputLayout inputFromDate, inputToDate, inputContractAmount, inputVisitCharges, inputFreeVisits, inputRemarks;
    private Button btnPreCommRefNo, btnParameterInfo, btnExpensesInfo, btnSave, btnClear;
    Vector<String> vecCompany, vecContractType;
    List<String> lsCompany = new ArrayList<String>();
    List<String> lsContractType = new ArrayList<String>();
    ArrayAdapter<String> adptCompany, adptContractType;
    String CompanyName, Pincode, District, Address;
    String resultValue, EnggName, AccountMContactId = "", contractTypeID = "";
    int dueDate, dueMonth, dueYear, mDay, mMonth, mYear;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.commissioning, container, false);
        prefs = getActivity().getSharedPreferences(LoginForm.MY_PREFS_NAME, getActivity().MODE_PRIVATE);
        FindViewById();
        EnggName = prefs.getString("UserNAME", "No name defined");
        txtServiceEngg.setText(EnggName);

        spnCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String companyname = parent.getItemAtPosition(position).toString();
                CompanyName = companyname;
                GetCompanyDetails(CompanyName);
                String pincode = txtPincode.getText().toString();
                if (pincode.length() != 0) {
                    GetConatctPersonDetails(CompanyName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spnContactPerson.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String contactPerson = parent.getItemAtPosition(position).toString();
                String pincode = txtPincode.getText().toString();
                if (pincode.length() != 0) {
                    FillContactPersonItem(contactPerson);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        etFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog(0);
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                etFromDate.setText(new StringBuilder().append(pad(dayOfMonth))
                                        .append("/").append(pad(monthOfYear + 1)).append("/")
                                        .append(pad(year)));
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        etToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showDialog(0);
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                etToDate.setText(new StringBuilder().append(pad(dayOfMonth))
                                        .append("/").append(pad(monthOfYear + 1)).append("/")
                                        .append(pad(year)));
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        btnPreCommRefNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CustomerName = spnCompany.getSelectedItem().toString();
                String AccountId = "";
                try {
                    AccountId = db.GetAccountMId(CustomerName);
                } catch (Exception ex) {
                }
                String AccountM = AccountId;
                Intent addExpense = new Intent(getActivity(),
                        CommissioningPrecommList.class);
                addExpense.putExtra("accountMId", AccountM);
                startActivityForResult(addExpense, 1);
            }
        });

        btnExpensesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = spnCompany.getSelectedItem().toString();
                String Project = "Commissioning";
                Intent addExpense = new Intent(getActivity(),
                        ExpenseListPagePreCommissioning.class);
                addExpense.putExtra("CompanyName", CompanyName);
                addExpense.putExtra("ProjectType", Project);
                startActivity(addExpense);
            }
        });

        btnParameterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = spnCompany.getSelectedItem().toString();
                String Project = "Commissioning";
                Intent parameter = new Intent(getActivity(), CommissioningParameterInfo.class);
                parameter.putExtra("CompanyName", CompanyName);
                parameter.putExtra("ProjectType", Project);
                startActivity(parameter);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
                insertCommissioningInDB();
            }
        });

        return rootView;
    }

    private void insertCommissioningInDB() {
        String EmpID = prefs.getString("UserEMPLOYEE_ID", "No name defined");

        String ServiceEngineerId = EmpID;
        String ServiceEngineerName = EnggName;
        String Address = txtAddress.getText().toString();
        String Pincode = txtPincode.getText().toString();
        String District = txtDistrict.getText().toString();
        String CustomerName = spnCompany.getSelectedItem().toString();
        String CustomerId = db.GetAccountMId(CustomerName);
        String AccountMContactName = spnContactPerson.getSelectedItem().toString();
        getAccountMContactId(AccountMContactName);
        String AMCID = AccountMContactId;
        String PhoneNo = txtContactPersonNo.getText().toString();
        String Email = txtEmail.getText().toString();
        String ProductName = txtProductName.getText().toString();
        String ProductId = "";
        String MachineSlNo = txtMachineSlNo.getText().toString();
        String PartNo = txtPartNo.getText().toString();
        String ContractType = spnContractType.getSelectedItem().toString();
        getContractTypeID(ContractType);
        String ContractTypeID = contractTypeID;
        String FromDate = etFromDate.getText().toString().trim();
        String ToDate = etToDate.getText().toString().trim();
        String ContractAmount = etContractAmount.getText().toString().trim();
        String VisitCharges = etVisitCharges.getText().toString().trim();
        String FreeVisits = etFreeVisits.getText().toString().trim();
        String Remarks = etRemarks.getText().toString().trim();
        String Lat = String.valueOf(latitude);
        String Lon = String.valueOf(longitude);
        String Status = "N";

        commissioningDBHelper.insertCommissioning(ServiceEngineerId, ServiceEngineerName, Address, Pincode, District, CustomerName, CustomerId,
                AMCID, AccountMContactName, PhoneNo, Email, ProductId, ProductName, MachineSlNo, PartNo, ContractTypeID, FromDate, ToDate,
                ContractAmount, VisitCharges, FreeVisits, Remarks, Lat, Lon, Status);
        clear();
        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_LONG).show();
    }

    private void getAccountMContactId(String AccountMContact) {
        Cursor c = contactPersonDBHelper.selectCPersonBasedPerson(AccountMContact);
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    AccountMContactId = c.getString(1);
                } while (c.moveToNext());
            }
        }

        contactPersonDBHelper.close();
    }

    private void getContractTypeID(String ContractType) {
        Cursor c = commissioningDBHelper.getContractTypeID(ContractType);
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    contractTypeID = c.getString(0);
                } while (c.moveToNext());
            }
        }

        commissioningDBHelper.close();
    }

    private void clear() {
        etRemarks.setText("");
    }

    private void FindViewById() {
        txtServiceEngg = (TextView) rootView.findViewById(R.id.txt_service_engg_name);
        spnCompany = (Spinner) rootView.findViewById(R.id.spn_selectcustomer);
        txtAddress = (TextView) rootView.findViewById(R.id.txt_address);
        txtPincode = (TextView) rootView.findViewById(R.id.txt_pincode);
        txtDistrict = (TextView) rootView.findViewById(R.id.txt_district);
        spnContactPerson = (Spinner) rootView.findViewById(R.id.spn_selectcontactperson);
        txtContactPersonNo = (TextView) rootView.findViewById(R.id.txt_contact_person_no);
        txtEmail = (TextView) rootView.findViewById(R.id.txt_email);
        btnPreCommRefNo = (Button) rootView.findViewById(R.id.btn_get_precomm);
        txtProductName = (TextView) rootView.findViewById(R.id.txt_product_name);
        txtMachineSlNo = (TextView) rootView.findViewById(R.id.txt_machine_no);
        txtPartNo = (TextView) rootView.findViewById(R.id.txt_part_no);
        spnContractType = (Spinner) rootView.findViewById(R.id.spn_contracttype);
        inputFromDate = (TextInputLayout) rootView.findViewById(R.id.input_layout_fromdatepicker);
        etFromDate = (EditText) rootView.findViewById(R.id.et_fromdatepicker);
        inputToDate = (TextInputLayout) rootView.findViewById(R.id.input_layout_todatepicker);
        etToDate = (EditText) rootView.findViewById(R.id.et_todatepicker);
        inputContractAmount = (TextInputLayout) rootView.findViewById(R.id.input_camount);
        etContractAmount = (EditText) rootView.findViewById(R.id.et_camount);
        inputVisitCharges = (TextInputLayout) rootView.findViewById(R.id.input_visit);
        etVisitCharges = (EditText) rootView.findViewById(R.id.et_visit);
        inputFreeVisits = (TextInputLayout) rootView.findViewById(R.id.input_free);
        etFreeVisits = (EditText) rootView.findViewById(R.id.et_free);
        btnParameterInfo = (Button) rootView.findViewById(R.id.btn_parameter_info);
        btnExpensesInfo = (Button) rootView.findViewById(R.id.btn_expense);
        inputRemarks = (TextInputLayout) rootView.findViewById(R.id.input_remarks);
        etRemarks = (EditText) rootView.findViewById(R.id.et_remarks);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnClear = (Button) rootView.findViewById(R.id.btn_clear);
        db = new SQLiteHelper(getActivity());
        contactPersonDBHelper = new ContactPersonDBHelper(getActivity());
        serviceVisitDBHelper = new ServiceVisitDBHelper(getActivity());
        commissioningDBHelper = new CommissioningDBHelper(getActivity());
        vecCompany = new Vector<String>();
        vecContractType = new Vector<String>();
        getCompaniesList();
        getContractType();
    }

    private void getContractType() {
        try {
            Cursor c = commissioningDBHelper.selectContractType();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecContractType.add(c.getString(2));
                    } while (c.moveToNext());
                }
            }
            for (int i = 0; i < vecContractType.size(); i++) {
                lsContractType.add(vecContractType.get(i));
            }
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsContractType);
            lsContractType.clear();
            lsContractType.add("Select");
            lsContractType.addAll(ts);
            db.close();
            adptContractType = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lsContractType);
            adptContractType
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnContractType.setAdapter(adptContractType);
            spnContractType.setWillNotDraw(false);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error contract type", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void getCompaniesList() {
        try {
            Cursor c = db.selectCompany();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecCompany.add(c.getString(1));
                    } while (c.moveToNext());
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
            lsCompany.addAll(ts);
            db.close();
            adptCompany = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lsCompany);
            adptCompany
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnCompany.setAdapter(adptCompany);
            spnCompany.setWillNotDraw(false);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error company lookup", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void GetCompanyDetails(String companyname) {
        try {
            Cursor c = db.selectCompanyDtls(companyname);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        Pincode = c.getString(2);
                        District = c.getString(8);
                        Address = c.getString(11);
                    } while (c.moveToNext());
                }
            }
            db.close();
            txtAddress.setText(Address);
            txtPincode.setText(Pincode);
            txtDistrict.setText(District);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error company", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void GetConatctPersonDetails(String companyname) {
        Vector<String> vecContactPerson = new Vector<String>();
        List<String> lsContactPerson = new ArrayList<String>();
        ArrayAdapter<String> adptContactPerson;
        String AccountId = "";
        try {
            AccountId = db.GetAccountMId(companyname);
        } catch (Exception ex) {
        }
        try {
            Cursor c = contactPersonDBHelper.selectCPersonBasedAccountM(AccountId);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecContactPerson.add(c.getString(3));
                    } while (c.moveToNext());
                }
            }
            for (int i = 0; i < vecContactPerson.size(); i++) {
                lsContactPerson.add(vecContactPerson.get(i));
            }
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsContactPerson);
            lsContactPerson.clear();
            lsContactPerson.add("Select");
            lsContactPerson.addAll(ts);
            db.close();
            adptContactPerson = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, lsContactPerson);
            adptContactPerson
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnContactPerson.setAdapter(adptContactPerson);
            spnContactPerson.setWillNotDraw(false);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error cp lookup", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void FillContactPersonItem(String contactperson) {
        String Mobile = "", Email = "";
        try {
            Cursor c = contactPersonDBHelper.selectCPersonBasedPerson(contactperson);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        Mobile = c.getString(4);
                        Email = c.getString(5);
                    } while (c.moveToNext());
                }
            }
            contactPersonDBHelper.close();
            txtContactPersonNo.setText(Mobile);
            txtEmail.setText(Email);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error fill cp", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                resultValue = data.getStringExtra("result");
                if (!resultValue.contentEquals("")) {
                    getProductPreCommDtls(resultValue);
                    //lay_ParameterInfo.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void getProductPreCommDtls(String Id) {
        String GetPreCommID = null, SlNo = "", PartNo = "", ProductPartNo = "";
        try {
            Cursor c = commissioningDBHelper.selectProductPreCommBasedID(Id);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        GetPreCommID = c.getString(0);
                        SlNo = c.getString(5);
                        PartNo = c.getString(6);
                        ProductPartNo = c.getString(7);


                    } while (c.moveToNext());
                }
            }
            txtProductName.setText(ProductPartNo);
            txtMachineSlNo.setText(SlNo);
            txtPartNo.setText(PartNo);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error Product Name", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }
}
