package com.rd.strivos.cobby;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class AddGivingReferences extends Activity {

    private EditText edtNAME, edtADDRESS, edtCONTACTPERSON,
            edtDESIGNATION, edtPHONE, edtMOBILENO, edtEMAIL, edtREQCAP, et_MACHINEREQTIME;
    private Button btnSave, btnClear;
    private ListView lstView;
    private ListAdapter listAdaptor;
    SQLiteHelper db;
    String name, project, callDateTime;
    ImageButton img_datepicker;
    int dueDate, dueMonth, dueYear;
    private Calendar cal;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgivingreferences);
        edtNAME = (EditText) findViewById(R.id.edtNAME_givref);
        edtADDRESS = (EditText) findViewById(R.id.edtADDRESS_givref);
        edtCONTACTPERSON = (EditText) findViewById(R.id.edtCONTACTPERSON_givref);
        edtDESIGNATION = (EditText) findViewById(R.id.edtDESIGNATION_givref);
        edtPHONE = (EditText) findViewById(R.id.edtPHONE_givref);
        edtMOBILENO = (EditText) findViewById(R.id.edtMOBILENO_givref);
        edtEMAIL = (EditText) findViewById(R.id.edtEMAIL_givref);
        edtREQCAP = (EditText) findViewById(R.id.edtREQCAP_givref);
        et_MACHINEREQTIME = (EditText) findViewById(R.id.et_MACHINEREQTIME_givref);
        img_datepicker = (ImageButton) findViewById(R.id.img_MACHINEREQTIME_givref);
        btnSave = (Button) findViewById(R.id.add_givref);
        btnClear = (Button) findViewById(R.id.remove_givref);
        lstView = (ListView) findViewById(R.id.lst_givref);
        listAdaptor = lstView.getAdapter();
        db = new SQLiteHelper(getApplicationContext());
        Bundle b = getIntent().getExtras();
        name = b.getString("Key_name");
        project = b.getString("Key_project");
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String ProjectID = name;
                String NAME = edtNAME.getText().toString();
                String ADDRESS = edtADDRESS.getText().toString();
                String CONTACTPERSON = edtCONTACTPERSON.getText().toString();
                String DESIGNATION = edtDESIGNATION.getText().toString();
                String PHONE = edtPHONE.getText().toString();
                String MOBILENO = edtMOBILENO.getText().toString();
                String EMAIL = edtEMAIL.getText().toString();
                String REQUIRED_CAPACITY = edtREQCAP.getText().toString();
                String REQUIREMENT_TIME = et_MACHINEREQTIME.getText().toString();
                String ProjectType = project;
                String Status = "N";

                if (NAME.length() != 0 & ADDRESS.length() != 0
                        & CONTACTPERSON.length() != 0
                        & DESIGNATION.length() != 0 & PHONE.length() != 0
                        & MOBILENO.length() != 0 & EMAIL.length() != 0) {

                    if (isValidEmail(EMAIL) != false) {
                        if (isValidMobile(PHONE) != false) {
                            db.insertGivingReferences(ProjectID, NAME, ADDRESS,
                                    CONTACTPERSON, DESIGNATION, PHONE,
                                    MOBILENO, EMAIL, REQUIRED_CAPACITY, callDateTime, ProjectType, Status);
                            clearAll();
                            Toast toast = Toast.makeText(
                                    AddGivingReferences.this, "Saved",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(
                                    AddGivingReferences.this,
                                    "Please fill valid mobile number",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(AddGivingReferences.this,
                                "Please fill valid Email address",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(AddGivingReferences.this,
                            "Please fill all fileds.", Toast.LENGTH_SHORT);
                    toast.show();
                }

                refreshList();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                clearAll();
            }
        });

        img_datepicker.setOnClickListener(new View.OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                showDialog(0);
            }
        });
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

            et_MACHINEREQTIME.setText(new StringBuilder()
                    .append(pad(selectedDay)).append("/")
                    .append(pad(selectedMonth + 1)).append("/")
                    .append(pad(selectedYear)));

            callDateTime = pad(selectedYear) + "-"
                    + pad(selectedMonth + 1) + "-" + pad(selectedDay);

        }
    };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void clearAll() {
        edtNAME.setText("");
        edtADDRESS.setText("");
        edtCONTACTPERSON.setText("");
        edtDESIGNATION.setText("");
        edtPHONE.setText("");
        edtMOBILENO.setText("");
        edtEMAIL.setText("");
    }

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String NAME, CITY, CONTACTPERSON, DESIGNATION;
        try {
            Cursor c = db.selectGivingReferences();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        NAME = c.getString(1);
                        CITY = c.getString(2);
                        CONTACTPERSON = c.getString(3);
                        DESIGNATION = c.getString(4);
                        listAdaptor.add(NAME + " - " + CITY + "-"
                                + CONTACTPERSON + "-" + DESIGNATION);
                    } while (c.moveToNext());
                }
            }

            lstView.setAdapter(listAdaptor);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
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
            } else {
                check = true;
            }
        } else {
            check = false;
        }
        return check;
    }

}
