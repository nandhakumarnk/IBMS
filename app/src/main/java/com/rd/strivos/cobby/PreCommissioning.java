package com.rd.strivos.cobby;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ImageButton;
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
 * Created by COBURG DESIGN on 09-02-2016.
 */
public class PreCommissioning extends Fragment {

    View rootView;
    SQLiteHelper db;
    ContactPersonDBHelper contactPersonDBHelper;
    ServiceVisitDBHelper serviceVisitDBHelper;
    PreCommissioningDBHelper preCommissioningDBHelper;

    public PreCommissioning() {
    }

    SharedPreferences prefs;
    TextView txtServiceEngg, txtAddress, txtPincode, txtDistrict, txtContactPersonNo, txtEmail, txtProductName;
    Spinner spnCompany, spnContactPerson;
    private EditText etMachineNo, etPartNo, etExpectDOC, etYearOfManuf, etRemarks;
    private TextInputLayout inputMachineNo, inputPartNo, inputExpectDOC, inputYearOfManuf, inputRemarks;
    private ImageButton ibDate;
    private Button btnProductSelect, btnInstructionInfo, btnParameterInfo, btnExpensesInfo, btnSave, btnClear;
    Vector<String> vecCompany;
    List<String> lsCompany = new ArrayList<String>();
    ArrayAdapter<String> adptCompany;
    String CompanyName, Pincode, District, Address;
    private int day, month, year;
    int dueDate, dueMonth, dueYear, mDay, mMonth, mYear;
    private Calendar cal;
    String resultValue, EnggName, AccountMContactId = "";
    Location location; // location
    double latitude; // latitude
    double longitude; // longitude

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.pre_commissioning, container, false);
        prefs = getActivity().getSharedPreferences(LoginForm.MY_PREFS_NAME, getActivity().MODE_PRIVATE);
        FindViewById();
        EnggName = prefs.getString("UserNAME", "No name defined");
        txtServiceEngg.setText(EnggName);

        etExpectDOC.setOnClickListener(new View.OnClickListener() {
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

                                etExpectDOC.setText(new StringBuilder().append(pad(dayOfMonth))
                                        .append("/").append(pad(monthOfYear + 1)).append("/")
                                        .append(pad(year)));
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

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

        btnProductSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addSapres = new Intent(getActivity(),
                        PreCommissioningProductDtls.class);
                startActivityForResult(addSapres, 1);
            }
        });

        btnParameterInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = spnCompany.getSelectedItem().toString();
                String Project = "PreCommissioning";
                Intent parameter = new Intent(getActivity(), PreCommissioningParameterInfo.class);
                parameter.putExtra("CompanyName", CompanyName);
                parameter.putExtra("ProjectType", Project);
                startActivity(parameter);
            }
        });

        btnExpensesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = spnCompany.getSelectedItem().toString();
                String Project = "PreCommissioning";
                Intent addExpense = new Intent(getActivity(),
                        ExpenseListPagePreCommissioning.class);
                addExpense.putExtra("CompanyName", CompanyName);
                addExpense.putExtra("ProjectType", Project);
                startActivity(addExpense);
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
                insertPreCommissioningInDB();
            }
        });

        return rootView;
    }

    private void insertPreCommissioningInDB() {

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
        String MachineSlNo = etMachineNo.getText().toString().trim();
        String PartNo = etPartNo.getText().toString().trim();
        String Edc = etExpectDOC.getText().toString().trim();
        String Yom = etYearOfManuf.getText().toString().trim();
        String Remarks = etRemarks.getText().toString().trim();
        String Lat = String.valueOf(latitude);
        String Lon = String.valueOf(longitude);
        String Status = "N";

        preCommissioningDBHelper.insertPreCommissioning(ServiceEngineerId, ServiceEngineerName, Address,
                Pincode, District, CustomerName, CustomerId, AMCID, AccountMContactName, PhoneNo,
                Email, ProductId, ProductName, MachineSlNo, PartNo, Edc, Yom, Remarks, Lat, Lon, Status);
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
        btnProductSelect = (Button) rootView.findViewById(R.id.btn_get_product);
        txtProductName = (TextView) rootView.findViewById(R.id.txt_product_name);
        inputMachineNo = (TextInputLayout) rootView.findViewById(R.id.input_machine_no);
        etMachineNo = (EditText) rootView.findViewById(R.id.et_machine_no);
        inputPartNo = (TextInputLayout) rootView.findViewById(R.id.input_part_no);
        etPartNo = (EditText) rootView.findViewById(R.id.et_part_no);
        inputExpectDOC = (TextInputLayout) rootView.findViewById(R.id.input_layout_datepicker);
        etExpectDOC = (EditText) rootView.findViewById(R.id.et_datepicker);
        ibDate = (ImageButton) rootView.findViewById(R.id.img_datepicker);
        inputYearOfManuf = (TextInputLayout) rootView.findViewById(R.id.input_year_of_manu);
        etYearOfManuf = (EditText) rootView.findViewById(R.id.et_year_of_manu);
        btnInstructionInfo = (Button) rootView.findViewById(R.id.btn_ins_info);
        btnParameterInfo = (Button) rootView.findViewById(R.id.btn_parameter_info);
        btnExpensesInfo = (Button) rootView.findViewById(R.id.btn_expense);
        inputRemarks = (TextInputLayout) rootView.findViewById(R.id.input_remarks);
        etRemarks = (EditText) rootView.findViewById(R.id.et_remarks);
        btnSave = (Button) rootView.findViewById(R.id.btn_save);
        btnClear = (Button) rootView.findViewById(R.id.btn_clear);
        db = new SQLiteHelper(getActivity());
        contactPersonDBHelper = new ContactPersonDBHelper(getActivity());
        serviceVisitDBHelper = new ServiceVisitDBHelper(getActivity());
        preCommissioningDBHelper = new PreCommissioningDBHelper(getActivity());
        vecCompany = new Vector<String>();
        getCompaniesList();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTimeForDB = sdfForDB.format(new Date());
        etExpectDOC.setText(currentDateandTime);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                resultValue = data.getStringExtra("result");
                if (!resultValue.contentEquals("")) {
                    getProductInstallDtls(resultValue);
                    //lay_ParameterInfo.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void getProductInstallDtls(String Id) {
        String GetVisitTypeID = null, Product = "", ProductType = "", MCSlNo = "", PartNo = "", ContractType = "", ContractSummarId = "", ProductTypeId = "";
        try {
            Cursor c = serviceVisitDBHelper.selectProductInstallationBasedID(Id);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        GetVisitTypeID = c.getString(0);
                        ContractSummarId = c.getString(3);
                        Product = c.getString(5);
                        ProductType = c.getString(5);
                        MCSlNo = c.getString(6);
                        PartNo = c.getString(7);
                        ContractType = c.getString(8);
                        ProductTypeId = c.getString(11);

                    } while (c.moveToNext());
                }
            }
            txtProductName.setText(Product);
//            txtProduct.setText(Product);
//            txtProductType.setText(ProductType);
//            txtMachineSlno.setText(MCSlNo);
//            txtPartno.setText(PartNo);
//            txtContractno.setText(ContractType);
//            txtContractSummaryId.setText(ContractSummarId);
//            txtProductTypeId.setText(ProductTypeId);

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error Product Name", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    @Deprecated
    protected Dialog onCreateDialog(int id) {

        if (id == 0) {
            return new DatePickerDialog(getActivity(), datePickerListener, year, month,
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

            etExpectDOC.setText(new StringBuilder().append(pad(selectedDay))
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
