package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 * Created by Nandha on 07-12-2015.
 */
public class Register extends AppCompatActivity {
    private static final String TAG = LoginForm.class.getName();
    public static int NETWORK_STATE = 0, LOCATION_STATE = 0;
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 2;

    EditText _EmployeeID, _Name, _Email, _Mobile, _Date;
    private TextInputLayout inputEmployeeID, inputEmployeeName, inputLayoutEmail, inputLayoutMobile, inputLayoutDateofBirth;
    Button _Submit;
    private int day, month, year;
    int dueDate, dueMonth, dueYear;
    private Calendar cal;
    SQLiteHelper dbHelper;
    String result, result2;
    String[] record2[];
    Vector<String> vec_result2;
    String IMEINo;
    int count2;
    String Model, getSimSerialNumber;
    int Randnumber;
    Vector<Vector<String>> vec_entries2 = new Vector<Vector<String>>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        initialSetup();

        _Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        _Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), "No Internet Connection. Your device is currently not connected to the internet, please try again!", Toast.LENGTH_SHORT).show();
                _Submit.setText("Please wait...");
                _Submit.setEnabled(false);
                dbHelper.flag_insert("Nil");
                submitForm();
                //mProgress.dismiss();
            }
        });
    }

    private void initialSetup() {

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        IMEINo = telephonyManager.getDeviceId();
        Model = android.os.Build.MODEL;
        getSimSerialNumber = telephonyManager.getSimSerialNumber();
        dbHelper = new SQLiteHelper(getApplicationContext());

        _EmployeeID = (EditText) findViewById(R.id.et_employeeid);
        _Name = (EditText) findViewById(R.id.et_Name);
        _Email = (EditText) findViewById(R.id.et_Email);
        _Mobile = (EditText) findViewById(R.id.et_mobile);
        _Date = (EditText) findViewById(R.id.et_datepicker);
        _Submit = (Button) findViewById(R.id.btn_submit);

        inputEmployeeID = (TextInputLayout) findViewById(R.id.input_layout_emplyee_id);
        inputEmployeeName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutMobile = (TextInputLayout) findViewById(R.id.input_layout_mobile);
        inputLayoutDateofBirth = (TextInputLayout) findViewById(R.id.input_layout_datepicker);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        Randnumber = randInt(1000, 5000);
        System.out.println("Random number" + Randnumber);


        //turnGPSOn();

        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(this,
                Splash.class));

        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void turnGPSOn() {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        Register.this.sendBroadcast(intent);

        String provider = Settings.Secure.getString(Register.this.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            Register.this.sendBroadcast(poke);
        }
    }

    private void submitForm() {

        final String employeeId = _EmployeeID.getText().toString().trim();
        final String employeeName = _Name.getText().toString().trim();
        final String email = _Email.getText().toString().trim();
        final String mobile = _Mobile.getText().toString().trim();
        final String dob = _Date.getText().toString().trim();
        final String otp = String.valueOf(Randnumber);

        Log.i(TAG,
                "employeeId : "+employeeId+"employeeName : "+employeeName+"email : "+email+"mobile : "+dob+"IMEINo : "+Model+"otp : "+otp+"getSimSerialNumber : "+getSimSerialNumber);
//employeeId, employeeName, email, mobile, dob, IMEINo, Model, otp, getSimSerialNumber

        if (!validateEmployeeId()) {
            _Submit.setText("Submit");
            _Submit.setEnabled(true);
            return;
        }

        if (!validateEmployeeName()) {
            _Submit.setText("Submit");
            return;
        }

        if (!validateEmail()) {
            _Submit.setText("Submit");
            return;
        }

        if (!isValidMobile()) {
            _Submit.setText("Submit");
            return;
        }

        if (!validateDateOfBirth()) {
            return;
        }

        try {
            result = WebServices.Register(employeeId, employeeName, email, mobile, dob, IMEINo, Model, otp, getSimSerialNumber);

        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

        String getResult = result;

        if (result.toString().equalsIgnoreCase("OK")) {

            try {
                InsertEmployee(employeeId, otp, "OK");
                String mobileno = "+91" + mobile;
                SharedPreferences settings = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("mobileno", mobileno);
                editor.putString("status", "OK");
                editor.commit();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Toast toast = Toast
                    .makeText(
                            Register.this,
                            "You have been successfully registered and OTP will sent to your registered mobile number to set PIN for login.",
                            Toast.LENGTH_SHORT);
            toast.show();

            Intent i = new Intent(Register.this, OTP.class);
            i.putExtra("MobileNumber", mobile);
            i.putExtra("OTP", otp);
            i.putExtra("Status", "New");
            startActivity(i);
            finish();

        } else if (result.toString().equalsIgnoreCase("IN_USER")) {
            try {
                InsertEmployee(employeeId, otp, "IN_USER");
                String mobileno = "+91" + mobile;
                SharedPreferences settings = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("mobileno", mobileno);
                editor.putString("status", "IN_USER");
                editor.commit();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Toast toast = Toast
                    .makeText(
                            Register.this,
                            "You have registered already and OTP will sent to your registered mobile number to login.",
                            Toast.LENGTH_SHORT);
            toast.show();

            Intent i = new Intent(Register.this, OTP.class);
            i.putExtra("MobileNumber", mobile);
            i.putExtra("OTP", otp);
            i.putExtra("Status", "InUser");
            startActivity(i);
            finish();

        } else if (result.toString().equalsIgnoreCase("REGISTERED")) {
            Toast toast = Toast
                    .makeText(
                            Register.this,
                            "Already registered in some other device !!! ",
                            Toast.LENGTH_LONG);
            toast.show();
            clearAll();
            _Submit.setText("Submit");

        } else if (result.toString().equalsIgnoreCase("Failed")) {

            Toast toast = Toast
                    .makeText(
                            Register.this,
                            "Details are not valid. ",
                            Toast.LENGTH_LONG);
            toast.show();
            clearAll();
            _Submit.setText("Submit");

        } else {
            Toast toast = Toast
                    .makeText(
                            Register.this,
                            "Internal Error or No Internet Connection, please try again!",
                            Toast.LENGTH_LONG);
            toast.show();
            _Submit.setText("Submit");
        }
    }

    private void clearAll() {
        _EmployeeID.setText("");
        _Name.setText("");
        _Email.setText("");
        _Mobile.setText("");
        _Date.setText("");
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        if (id == 0) {
            return new DatePickerDialog(this, datePickerListener, year, month,
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

            _Date.setText(new StringBuilder().append(pad(selectedDay))
                    .append("/").append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));
        }
    };

    public static int randInt(int min, int max) {

        Random rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_employeeid:
                    validateEmployeeId();
                    break;
                case R.id.et_Name:
                    validateEmployeeName();
                    break;
                case R.id.et_Email:
                    validateEmail();
                    break;
                case R.id.et_mobile:
                    isValidMobile();
                    break;
                case R.id.et_datepicker:
                    validateDateOfBirth();
                    break;
            }
        }
    }

    private boolean isValidMobile() {
        String mobile = _Mobile.getText().toString().trim();
        if (!Pattern.matches("[a-zA-Z]+", mobile)) {
            if (mobile.length() < 10 || mobile.length() > 13) {
                inputLayoutMobile.setError(getString(R.string.err_msg_mobile));
                requestFocus(_Mobile);
                return false;
            } else {
                inputLayoutMobile.setErrorEnabled(false);
            }
        } else {
            inputLayoutMobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmployeeId() {
        if (_EmployeeID.getText().toString().trim().isEmpty()) {
            inputEmployeeID.setError(getString(R.string.err_msg_empId));
            requestFocus(_EmployeeID);
            return false;
        } else {
            inputEmployeeID.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDateOfBirth() {
        if (_Date.getText().toString().trim().isEmpty()) {
            inputLayoutDateofBirth.setError(getString(R.string.err_msg_dob));
            requestFocus(_Date);
            return false;
        } else {
            inputEmployeeID.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmployeeName() {
        if (_Name.getText().toString().trim().isEmpty()) {
            inputEmployeeName.setError(getString(R.string.err_msg_empname));
            requestFocus(_Name);
            return false;
        } else {
            inputEmployeeName.setErrorEnabled(false);
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            NetworkInfo currentNetworkInfo = (NetworkInfo) intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

            final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            LocationManager mlocManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean enabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            if (currentNetworkInfo.isConnected()) {
                NETWORK_STATE = CONNECTED;
                if (enabled == false) {
                    LOCATION_STATE = NOT_CONNECTED;
                    _Submit.setEnabled(false);
                    showGPSDisabledAlertToUser();
                } else {
                    LOCATION_STATE = CONNECTED;
                    _Submit.setEnabled(true);
                }
            } else {
                NETWORK_STATE = NOT_CONNECTED;

                Toast.makeText(getApplicationContext(), "No Internet Connection. Your device is currently not connected to the internet, please try again!", Toast.LENGTH_SHORT).show();
                _Submit.setText("Submit");
                _Submit.setEnabled(false);
            }
        }
    };

    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    private void InsertEmployee(String employeeId, String Otp, String Status) {
        try {

            String qry = "Select A.NAME,EJ.MOBILE,EJ.EMPLOYEEID, R.DESCRIPTION As DESIGNATION,A.ID AS EMPLOYEE_ID, D.DESCRIPTION AS DEPARTMENT,EJ.DEPARTMENT_ID, EJ.DESIGNATION_ID,ISNULL(EJ.EMAIL,0) AS EMAIL,ISNULL(EJ.PINNO,0) AS PINNO,A.COMPANYM_ID,CONVERT(VARCHAR(10),A.DOB,103) as DOB,EJ.SIMNO From EMPLOYEEM A INNER JOIN EMPLOYEEMJOININGDETAILS EJ ON EJ.EMPLOYEEM_ID = A.ID Inner Join MASTERM R On R.ID = EJ.DESIGNATION_ID Inner Join MASTERM D On D.ID = EJ.DEPARTMENT_ID Where A.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And A.STATUSM_ID<>'9569e2bd-4e32-4ab6-9a04-3fc4699d9f43' AND EJ.EMPLOYEEID='" + employeeId + "'";

            String result30 = WebServices.JSONWithQry("EMPLOYEE", qry);
            JSONArray json = new JSONArray(result30);

            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);

                System.out.println("NAME : " + i + " = " + jsonobj.getString("NAME"));
                System.out.println("MOBILE : " + i + " = " + jsonobj.getString("MOBILE"));
                System.out.println("EMPLOYEEID : " + i + " = " + jsonobj.getString("EMPLOYEEID"));
                System.out.println("DESIGNATION : " + i + " = " + jsonobj.getString("DESIGNATION"));
                System.out.println("EMPLOYEE_ID : " + i + " = " + jsonobj.getString("EMPLOYEE_ID"));
                System.out.println("DEPARTMENT : " + i + " = " + jsonobj.getString("DEPARTMENT"));
                System.out.println("DEPARTMENT_ID : " + i + " = " + jsonobj.getString("DEPARTMENT_ID"));
                System.out.println("DESIGNATION_ID : " + i + " = " + jsonobj.getString("DESIGNATION_ID"));
                System.out.println("EMAIL : " + i + " = " + jsonobj.getString("EMAIL"));
                System.out.println("PINNO : " + i + " = " + jsonobj.getString("PINNO"));
                System.out.println("COMPANYM_ID : " + i + " = " + jsonobj.getString("COMPANYM_ID"));
                System.out.println("DOB : " + i + " = " + jsonobj.getString("DOB"));
                System.out.println("SIMNO : " + i + " = " + jsonobj.getString("SIMNO"));
                String v1 = jsonobj.getString("NAME"),
                        v2 = jsonobj.getString("MOBILE"),
                        v3 = jsonobj.getString("EMPLOYEEID"),
                        v4 = jsonobj.getString("DESIGNATION"),
                        v5 = jsonobj.getString("EMPLOYEE_ID"),
                        v6 = jsonobj.getString("DEPARTMENT"),
                        v7 = jsonobj.getString("DEPARTMENT_ID"),
                        v8 = jsonobj.getString("DESIGNATION_ID"),
                        v9 = jsonobj.getString("EMAIL"),
                        v10 = jsonobj.getString("PINNO"),
                        v11 = jsonobj.getString("COMPANYM_ID"),
                        v12 = jsonobj.getString("DOB"),
                        v13 = jsonobj.getString("SIMNO");

                dbHelper.register_insert(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, Otp, Status);
            }
        } catch (Exception e) {
        }
    }

    private void loadBar() {
        ProgressDialog progressDialog = ProgressDialog.show(this, "",
                "Please wait...");

        //progressDialog.dismiss();
    }
}
