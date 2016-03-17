package com.rd.strivos.cobby;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

/**
 * Created by COBURG DESIGN on 13-01-2016.
 */
public class ServiceVisitAdd extends AppCompatActivity {

    private String CoEmployee, inDateTime, outDateTime, getACCOUNTNAME, getPINCODE, getPHONE, getSTD, getMOBILE, getWEBSITE,
            getEMAIL, getDISTRICT, callDateTime, GetServiceVisitTypeID, GetServiceVisitWorkStatusID, getCompanyID, employeeID,
            getDistrictID, GetAddress;
    public static String getID;
    String resultValue;
    private TextView txtCustomer, txtAddress, txtPincode, txtDistrict, txtProduct, txtProductType, txtMachineSlno,
            txtPartno, txtContractno, txtContractSummaryId, txtProductTypeId;
    private Button _AddMorePerson, _AddProduct, _AddParameter, _AddSpares, _AddPayment,
            _AddGrievance, _AddStatutory, _Save, _Expense, _Clear;
    private Spinner spnVisitType, spnWorkCompletionStatus;
    private TextInputLayout inputServicePerformed, inputActionRequired, inputVisitSummary;
    private EditText etServicePerformed, etActionRequired, etVisitSummary;
    LinearLayout lay_ParameterInfo, lay_SparesRequiredInfo, lay_PaymentInfo,
            lay_GrievanceInfo, lay_StatutoryDocumentsInfo;
    AlertDialog.Builder builder_verify;
    public static int count = 0;
    Vector<String> vecVisitType, vecWorkStatus;
    List<String> lsVisitType = new ArrayList<String>();
    List<String> lsWorkStatus = new ArrayList<String>();
    ServiceVisitDBHelper serviceVisitDBHelper;
    SQLiteHelper db;
    ArrayAdapter<String> adptVisitType, adptWorkStatus;

    Location location; // location
    double latitude; // latitude
    double longitude; // longitude


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_visit_add);

        InitialFormLoad();

        _AddMorePerson.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addPerson = new Intent(getApplicationContext(),
                        PersonAddListPageServiceVisit.class);
                addPerson.putExtra("CompanyName", CompanyName);
                addPerson.putExtra("ProjectType", Project);
                startActivity(addPerson);
            }
        });

        spnVisitType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String VisitType = arg0.getItemAtPosition(arg2).toString();
                chooseVisitType(VisitType);
                getVisitTypeID(VisitType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnWorkCompletionStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String WorkStatus = arg0.getItemAtPosition(arg2).toString();
                getVisitWorkStatusID(WorkStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        _AddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addSapres = new Intent(getApplicationContext(),
                        ServiceVisitGetProductDtls.class);
                addSapres.putExtra("CompanyID", getCompanyID);
                addSapres.putExtra("CompanyName", CompanyName);
                addSapres.putExtra("ProjectType", Project);
                startActivityForResult(addSapres, 1);
            }
        });

        _AddStatutory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addSapres = new Intent(getApplicationContext(),
                        StatutoryDocumentsListPageServiceVisit.class);
                addSapres.putExtra("CompanyName", CompanyName);
                addSapres.putExtra("ProjectType", Project);
                startActivity(addSapres);
            }
        });

        _AddSpares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addSapres = new Intent(getApplicationContext(),
                        SparesListPageServiceVisit.class);
                addSapres.putExtra("CompanyName", CompanyName);
                addSapres.putExtra("ProjectType", Project);
                startActivity(addSapres);
            }
        });

        _AddGrievance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addGrievance = new Intent(getApplicationContext(),
                        GrievanceListPageServiceVisit.class);
                addGrievance.putExtra("CompanyName", CompanyName);
                addGrievance.putExtra("ProjectType", Project);
                startActivity(addGrievance);
            }
        });

        _AddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addPayments = new Intent(getApplicationContext(), PaymentsListPageServiceVisit.class);
                addPayments.putExtra("CompanyName", CompanyName);
                addPayments.putExtra("ProjectType", Project);
                startActivity(addPayments);
            }
        });

        _AddParameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                String ProductTypeId = txtProductTypeId.getText().toString();
                if (ProductTypeId.contentEquals("7350cf08-28a5-4e6a-aff5-852ccee1ceb6") || ProductTypeId.contentEquals("869cde39-6d4c-4c6d-ad05-b3272e31ce5d")) {
                    Intent addPayments = new Intent(getApplicationContext(), OtherParameterInfo.class);
                    addPayments.putExtra("CompanyName", CompanyName);
                    addPayments.putExtra("ProjectType", Project);
                    startActivity(addPayments);
                } else {
                    Intent addPayments = new Intent(getApplicationContext(), ParameterInfo.class);
                    addPayments.putExtra("CompanyName", CompanyName);
                    addPayments.putExtra("ProjectType", Project);
                    startActivity(addPayments);
                }
            }
        });

        _Expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CompanyName = txtCustomer.getText().toString();
                String Project = "Service";
                Intent addExpense = new Intent(getApplicationContext(),
                        ExpenseListPageServiceVisit.class);
                addExpense.putExtra("CompanyName", CompanyName);
                addExpense.putExtra("ProjectType", Project);
                startActivity(addExpense);
            }
        });

        _Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
            }
        });

        _Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddServiceVisitIntoDB();
            }
        });
    }

    private void AddServiceVisitIntoDB() {
        getCurrentLocation();
        String EmployeeId = employeeID;
        String CoEmployeeId = CoEmployee;
        String InDate = inDateTime;
        String OutDate = outDateTime;
        String AccountMId = getCompanyID;
        String Customer = txtCustomer.getText().toString();
        String Address = txtAddress.getText().toString();
        String Pincode = txtPincode.getText().toString();
        String District = txtDistrict.getText().toString();
        String DistrictId = getDistrictID;
        String VisitType = spnVisitType.getSelectedItem().toString();
        getVisitTypeID(VisitType);
        String VisitTypeId = GetServiceVisitTypeID;
        String Product = txtProduct.getText().toString();
        String ProductType = txtProductType.getText().toString();
        String MachineSlNo = txtMachineSlno.getText().toString();
        String PartNo = txtPartno.getText().toString();
        String ContractType = txtContractno.getText().toString();
        String ContractSummaryId = txtContractSummaryId.getText().toString();
        String ServicePerformed = etServicePerformed.getText().toString().trim();
        String ActionRequierd = etActionRequired.getText().toString().trim();
        String VisitSummar = etVisitSummary.getText().toString().trim();
        String WorkStatus = spnWorkCompletionStatus.getSelectedItem().toString();
        getVisitWorkStatusID(WorkStatus);
        String WorkStatusID = GetServiceVisitWorkStatusID;
        String lat = String.valueOf(latitude);
        String lon = String.valueOf(longitude);
        String Status = "N";
        String RefNo = "1";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTimeForDB = sdfForDB.format(new Date());
        String RefDate = currentDateandTime;
        String ProjectType = "Service";

        if (!validateProduct()) {
            return;
        }

        if (!validateServicePerformed()) {
            return;
        }

        if (!validateActionRequired()) {
            return;
        }

        if (!validateVisitSummary()) {
            return;
        }

        serviceVisitDBHelper.insertServiceVisit(EmployeeId, CoEmployeeId, InDate, OutDate, AccountMId, Customer, Address, Pincode, DistrictId, VisitTypeId, Product, ProductType, MachineSlNo, PartNo, ContractType, ServicePerformed, ActionRequierd, VisitSummar, WorkStatusID, lat, lon, Status);
        serviceVisitDBHelper.insertServiceVisitList(ContractSummaryId, RefNo, RefDate, ProjectType, Customer, Status);

        Toast.makeText(getApplicationContext(), "Data has been saved successfully !", Toast.LENGTH_SHORT).show();
        finish();
    }

    private boolean validateProduct() {
        if (txtProduct.getText().toString().trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Select Product", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateServicePerformed() {
        if (etServicePerformed.getText().toString().trim().isEmpty()) {
            inputServicePerformed.setError(getString(R.string.err_msg_service_performed));
            requestFocus(etServicePerformed);
            return false;
        } else {
            inputServicePerformed.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateActionRequired() {
        if (etActionRequired.getText().toString().trim().isEmpty()) {
            inputActionRequired.setError(getString(R.string.err_msg_action_required));
            requestFocus(etActionRequired);
            return false;
        } else {
            inputActionRequired.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateVisitSummary() {
        if (etVisitSummary.getText().toString().trim().isEmpty()) {
            inputVisitSummary.setError(getString(R.string.err_msg_visit_summary));
            requestFocus(etVisitSummary);
            return false;
        } else {
            inputVisitSummary.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void clearAll() {
        etServicePerformed.setText("");
        etVisitSummary.setText("");
        etActionRequired.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                resultValue = data.getStringExtra("result");
                if (!resultValue.contentEquals("")) {
                    getProductInstallDtls(resultValue);
                    lay_ParameterInfo.setVisibility(View.VISIBLE);
                }
            }
        }
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

    private void InitialFormLoad() {

        txtCustomer = (TextView) findViewById(R.id.txt_customername_service);
        txtAddress = (TextView) findViewById(R.id.txt_address_service);
        txtPincode = (TextView) findViewById(R.id.txt_pincode_service);
        txtDistrict = (TextView) findViewById(R.id.txt_district_service);
        _AddMorePerson = (Button) findViewById(R.id.btn_moreperson_service);
        spnVisitType = (Spinner) findViewById(R.id.spn_visittype_service);
        _AddProduct = (Button) findViewById(R.id.btn_addprod_service);
        txtProduct = (TextView) findViewById(R.id.txt_product_service);
        txtProductType = (TextView) findViewById(R.id.txt_product_type_service);
        txtMachineSlno = (TextView) findViewById(R.id.txt_machine_slno_service);
        txtPartno = (TextView) findViewById(R.id.txt_part_no_service);
        txtContractno = (TextView) findViewById(R.id.txt_contract_type_service);
        txtContractSummaryId = (TextView) findViewById(R.id.txt_contract_summary_id_service);
        txtProductTypeId = (TextView) findViewById(R.id.txt_product_type_id_service);
        lay_ParameterInfo = (LinearLayout) findViewById(R.id.parameter_service);
        _AddParameter = (Button) findViewById(R.id.btn_add_parameter_info_service);
        lay_SparesRequiredInfo = (LinearLayout) findViewById(R.id.spares_service);
        _AddSpares = (Button) findViewById(R.id.btn_add_spares_required_info_service);
        lay_PaymentInfo = (LinearLayout) findViewById(R.id.payment_service);
        _AddPayment = (Button) findViewById(R.id.btn_add_payment_info_service);
        lay_GrievanceInfo = (LinearLayout) findViewById(R.id.grievance_service);
        _AddGrievance = (Button) findViewById(R.id.btn_add_grievance_info_service);
        lay_StatutoryDocumentsInfo = (LinearLayout) findViewById(R.id.statutory_service);
        _AddStatutory = (Button) findViewById(R.id.btn_add_statutory_documents_info_service);
        inputServicePerformed = (TextInputLayout) findViewById(R.id.input_layout_service_performed_service);
        etServicePerformed = (EditText) findViewById(R.id.et_service_performed_service);
        inputActionRequired = (TextInputLayout) findViewById(R.id.input_layout_action_required_service);
        etActionRequired = (EditText) findViewById(R.id.et_action_required_service);
        inputVisitSummary = (TextInputLayout) findViewById(R.id.input_layout_visit_summary_service);
        etVisitSummary = (EditText) findViewById(R.id.et_visit_summary_service);
        spnWorkCompletionStatus = (Spinner) findViewById(R.id.spn_work_completion_status_service);
        _Expense = (Button) findViewById(R.id.btn_expense_service);
        _Save = (Button) findViewById(R.id.btn_save_service);
        _Clear = (Button) findViewById(R.id.btn_clear_service);
        builder_verify = new AlertDialog.Builder(this);

        vecVisitType = new Vector<String>();
        vecWorkStatus = new Vector<String>();
        serviceVisitDBHelper = new ServiceVisitDBHelper(getApplicationContext());
        db = new SQLiteHelper(getApplicationContext());

        Bundle b = getIntent().getExtras();
        getCompanyID = b.getString("CompanyID");
        CoEmployee = b.getString("CoEmployee");
        inDateTime = b.getString("inDateTime");
        outDateTime = b.getString("outDateTime");
        getACCOUNTNAME = b.getString("ACCOUNTNAME");
        getPINCODE = b.getString("PINCODE");
        getSTD = b.getString("STD");
        getPHONE = b.getString("PHONE");
        getMOBILE = b.getString("MOBILE");
        getWEBSITE = b.getString("WEBSITE");
        getEMAIL = b.getString("EMAIL");
        getDISTRICT = b.getString("DISTRICT");
        GetAddress = b.getString("Address");

        txtCustomer.setText(getACCOUNTNAME);
        txtAddress.setText("");
        txtPincode.setText(getPINCODE);
        txtDistrict.setText(getDISTRICT);
        txtAddress.setText(GetAddress);
        LoadVisitTypeSpin();
        LoadVisitWorkStatusSpin();
        EmployeeID();
        DistrictID();

    }

    private void LoadVisitTypeSpin() {
        // to test
        try {
            Cursor c = serviceVisitDBHelper.selectServiceVisitType();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecVisitType.add(c.getString(2));
                    } while (c.moveToNext());
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
            serviceVisitDBHelper.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error VT Spin", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // District
        adptVisitType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsVisitType);
        adptVisitType
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnVisitType.setAdapter(adptVisitType);
        spnVisitType.setWillNotDraw(false);
    }

    private void chooseVisitType(String Type) {
        if (Type.equalsIgnoreCase("SERVICE")) {
            lay_ParameterInfo.setVisibility(View.GONE);
            lay_SparesRequiredInfo.setVisibility(View.VISIBLE);
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_PaymentInfo.setVisibility(View.GONE);
            lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
        } else if (Type.equalsIgnoreCase("COURTESY")) {
            lay_ParameterInfo.setVisibility(View.GONE);
            lay_SparesRequiredInfo.setVisibility(View.VISIBLE);
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_PaymentInfo.setVisibility(View.GONE);
            lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
        } else if (Type.equalsIgnoreCase("PAYMENT FOLLOWUP")) {
            lay_ParameterInfo.setVisibility(View.GONE);
            lay_SparesRequiredInfo.setVisibility(View.GONE);
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_PaymentInfo.setVisibility(View.VISIBLE);
            lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
        } else if (Type.equalsIgnoreCase("GRIEVANCE")) {
            lay_ParameterInfo.setVisibility(View.GONE);
            lay_SparesRequiredInfo.setVisibility(View.GONE);
            lay_GrievanceInfo.setVisibility(View.VISIBLE);
            lay_PaymentInfo.setVisibility(View.GONE);
            lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
        } else if (Type.equalsIgnoreCase("DOCUMENTS FOLLOWUP")) {
            lay_ParameterInfo.setVisibility(View.GONE);
            lay_SparesRequiredInfo.setVisibility(View.GONE);
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_PaymentInfo.setVisibility(View.GONE);
            lay_StatutoryDocumentsInfo.setVisibility(View.VISIBLE);
        } else {
            lay_ParameterInfo.setVisibility(View.GONE);
            lay_SparesRequiredInfo.setVisibility(View.GONE);
            lay_GrievanceInfo.setVisibility(View.GONE);
            lay_PaymentInfo.setVisibility(View.GONE);
            lay_StatutoryDocumentsInfo.setVisibility(View.GONE);
        }
    }

    private void getVisitTypeID(String Type) {
        String GetVisitTypeID = null;
        try {
            Cursor c = serviceVisitDBHelper.ServiceVisitTypeID(Type);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        GetVisitTypeID = c.getString(0);
                        GetServiceVisitTypeID = GetVisitTypeID;
                    } while (c.moveToNext());
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error VisitType 1", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
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

            txtProduct.setText(Product);
            txtProductType.setText(ProductType);
            txtMachineSlno.setText(MCSlNo);
            txtPartno.setText(PartNo);
            txtContractno.setText(ContractType);
            txtContractSummaryId.setText(ContractSummarId);
            txtProductTypeId.setText(ProductTypeId);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error VisitType 1", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void LoadVisitWorkStatusSpin() {
        // to test
        try {
            Cursor c = serviceVisitDBHelper.selectServiceVisitWorkStatus();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecWorkStatus.add(c.getString(2));
                    } while (c.moveToNext());
                }
            }

            for (int i = 0; i < vecWorkStatus.size(); i++) {
                lsWorkStatus.add(vecWorkStatus.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsWorkStatus);
            lsWorkStatus.clear();
            lsWorkStatus.addAll(ts);
            serviceVisitDBHelper.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error VT Spin", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // District
        adptWorkStatus = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsWorkStatus);
        adptWorkStatus
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnWorkCompletionStatus.setAdapter(adptWorkStatus);
        spnWorkCompletionStatus.setWillNotDraw(false);
    }

    private void getVisitWorkStatusID(String Type) {
        String GetVisitWorkStatusID = null;
        try {
            Cursor c = serviceVisitDBHelper.ServiceVisitVisitWorkStatusID(Type);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        GetVisitWorkStatusID = c.getString(0);
                        GetServiceVisitWorkStatusID = GetVisitWorkStatusID;
                    } while (c.moveToNext());
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error VisitType 1", Toast.LENGTH_LONG)
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

    private void DistrictID() {
        String type = txtDistrict.getText().toString().trim();

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

    public void getCurrentLocation() {
        latitude = 0.0;
        longitude = 0.0;
        GPSTracker gps = new GPSTracker(getApplicationContext());
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        } else {
            gps.showSettingsAlert();
        }

    }
}
