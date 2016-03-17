package com.rd.strivos.cobby;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class AddBranchInfoOEM extends Activity {

    private EditText etCity, etAddress, etName, etDesignation, etPhone, etMobile, etEmail;
    private Button btnSave, btnClear;
    private ListView lstView;
    private ListAdapter listAdaptor;
    SQLiteHelper db;
    String name, project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbranchinfooem);
        etCity = (EditText) findViewById(R.id.edtCity_binfooem);
        etAddress = (EditText) findViewById(R.id.edtAddress_binfooem);
        etName = (EditText) findViewById(R.id.edtContanctPerson_binfooem);
        etDesignation = (EditText) findViewById(R.id.edtDesignation_binfooem);
        etPhone = (EditText) findViewById(R.id.edtPhone_binfooem);
        etMobile = (EditText) findViewById(R.id.edtMobile_binfooem);
        etEmail = (EditText) findViewById(R.id.edtemail_binfooem);
        btnSave = (Button) findViewById(R.id.add_binfooem);
        btnClear = (Button) findViewById(R.id.remove_binfooem);
        lstView = (ListView) findViewById(R.id.lst_binfooem);
        listAdaptor = lstView.getAdapter();
        db = new SQLiteHelper(getApplicationContext());
        Bundle b = getIntent().getExtras();
        name = b.getString("Key_name");
        project = b.getString("Key_project");

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String ProjectID = name;
                String City = etCity.getText().toString();
                String address = etAddress.getText().toString();
                String Name = etName.getText().toString();
                String Designation = etDesignation.getText().toString();
                String Phone = etPhone.getText().toString();
                String mobile = etMobile.getText().toString();
                String Email = etEmail.getText().toString();
                String ProjectType = project;
                String Status = "N";

                if (Name.length() != 0 & Designation.length() != 0
                        & Phone.length() != 0 & Email.length() != 0 & City.length() != 0 & address.length() != 0
                        & mobile.length() != 0) {

                    if (isValidEmail(Email) != false) {
                        if (isValidMobile(Phone) != false) {
                            db.insertBranchInfoOEM(ProjectID, City, address, Name, Designation,
                                    Phone, mobile, Email, ProjectType, Status);
                            clearAll();
                            Toast toast = Toast.makeText(AddBranchInfoOEM.this,
                                    "Saved",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(AddBranchInfoOEM.this,
                                    "Please fill valid mobile number",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        Toast toast = Toast.makeText(AddBranchInfoOEM.this,
                                "Please fill valid Email address",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(AddBranchInfoOEM.this,
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

    }

    private void clearAll() {
        etName.setText("");
        etDesignation.setText("");
        etPhone.setText("");
        etEmail.setText("");
        etCity.setText("");
        etAddress.setText("");
        etMobile.setText("");
    }

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String name, designation, phone, email;
        try {
            Cursor c = db.selectBranchInfoOEM();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        name = c.getString(1);
                        designation = c.getString(2);
                        phone = c.getString(3);
                        email = c.getString(4);
                        listAdaptor.add(name + " - " + designation
                                + "-" + phone + "-" + email);
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
