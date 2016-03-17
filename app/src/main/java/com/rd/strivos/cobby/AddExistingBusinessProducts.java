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

public class AddExistingBusinessProducts extends Activity {

    private EditText edtPRODUCT, edtBRAND, edtPRINCIPAL,
            edtDEALERSINCE, edtOPERATIONAREA, edtUNITSYEAR, edtSALESYEAR, edtMARGINS;
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
        setContentView(R.layout.addexistingbusinessproducts);
        edtPRODUCT = (EditText) findViewById(R.id.edtPRODUCT_ebp);
        edtBRAND = (EditText) findViewById(R.id.edtBRAND_ebp);
        edtPRINCIPAL = (EditText) findViewById(R.id.edtPRINCIPAL_ebp);
        edtDEALERSINCE = (EditText) findViewById(R.id.et_DEALERSINCE_ebp);
        img_datepicker = (ImageButton) findViewById(R.id.img_DEALERSINCE_ebp);
        edtOPERATIONAREA = (EditText) findViewById(R.id.edtOPERATIONAREA_ebp);
        edtUNITSYEAR = (EditText) findViewById(R.id.edtUNITSYEAR_ebp);
        edtSALESYEAR = (EditText) findViewById(R.id.edtSALESYEAR_ebp);
        edtMARGINS = (EditText) findViewById(R.id.edtMARGINS_ebp);
        btnSave = (Button) findViewById(R.id.add_ebp);
        btnClear = (Button) findViewById(R.id.remove_ebp);
        lstView = (ListView) findViewById(R.id.lst_ebp);
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
                String PRODUCT = edtPRODUCT.getText().toString();
                String BRAND = edtBRAND.getText().toString();
                String PRINCIPAL = edtPRINCIPAL.getText().toString();
                String DEALERSINCE = edtDEALERSINCE.getText().toString();
                String OPERATIONAREA = edtOPERATIONAREA.getText().toString();
                String UNITSYEAR = edtUNITSYEAR.getText().toString();
                String SALESYEAR = edtSALESYEAR.getText().toString();
                String MARGINS = edtMARGINS.getText().toString();
                String ProjectType = project;
                String Status = "N";

                if (PRODUCT.length() != 0 & BRAND.length() != 0
                        & PRINCIPAL.length() != 0 & DEALERSINCE.length() != 0
                        & OPERATIONAREA.length() != 0 & UNITSYEAR.length() != 0
                        & SALESYEAR.length() != 0 & MARGINS.length() != 0) {

                    db.insertExistingBusinessProducts(ProjectID, PRODUCT,
                            BRAND, PRINCIPAL, callDateTime, OPERATIONAREA, UNITSYEAR,
                            SALESYEAR, MARGINS, ProjectType, Status);
                    refreshList();
                    clearAll();
                    Toast toast = Toast.makeText(AddExistingBusinessProducts.this,
                            "Saved", Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Toast toast = Toast.makeText(AddExistingBusinessProducts.this,
                            "Please fill all fileds.", Toast.LENGTH_SHORT);
                    toast.show();
                }
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

            edtDEALERSINCE.setText(new StringBuilder()
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

        edtPRODUCT.setText("");
        edtBRAND.setText("");
        edtPRINCIPAL.setText("");
        edtDEALERSINCE.setText("");
        edtOPERATIONAREA.setText("");
        edtUNITSYEAR.setText("");
        edtSALESYEAR.setText("");
        edtMARGINS.setText("");
    }

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String PRODUCT, BRAND, PRINCIPAL, DEALERSINCE;
        try {
            Cursor c = db.selectExistingBusinessProducts();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        PRODUCT = c.getString(1);
                        BRAND = c.getString(2);
                        PRINCIPAL = c.getString(3);
                        DEALERSINCE = c.getString(4);
                        listAdaptor.add(PRODUCT + " - " + BRAND
                                + "-" + PRINCIPAL + "-" + DEALERSINCE);
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
}
