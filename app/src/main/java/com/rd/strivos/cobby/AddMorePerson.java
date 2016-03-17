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

public class AddMorePerson extends Activity {

    private EditText etName, etDesignation, etPhone, etEmail;
    private Button btnSave, btnClear;
    private ListView lstView;
    private ListAdapter listAdaptor;
    SQLiteHelper db;
    String name, project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmoreperson);
        etName = (EditText) findViewById(R.id.edtName_more);
        etDesignation = (EditText) findViewById(R.id.edtDesignation_more);
        etPhone = (EditText) findViewById(R.id.edtPhone_more);
        etEmail = (EditText) findViewById(R.id.edtemail_more);
        btnSave = (Button) findViewById(R.id.add_more);
        btnClear = (Button) findViewById(R.id.remove_more);
        lstView = (ListView) findViewById(R.id.lst_more);
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
                String Name = etName.getText().toString();
                String Designation = etDesignation.getText().toString();
                String Phone = etPhone.getText().toString();
                String Email = etEmail.getText().toString();
                String SalesType = project;
                String Status = "N";

                if (Name.length() != 0 & Designation.length() != 0
                        & Phone.length() != 0 & Email.length() != 0) {

                    if (isValidEmail(Email) != false) {
                        if (isValidMobile(Phone) != false) {

                            db.insertMorePerson(ProjectID, Name, Designation,
                                    Phone, Email, SalesType, Status);
                            clearAll();
                            Toast toast = Toast.makeText(AddMorePerson.this,
                                    "Saved",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(AddMorePerson.this,
                                    "Please fill valid mobile number",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        refreshList(ProjectID);
                    } else {
                        Toast toast = Toast.makeText(AddMorePerson.this,
                                "Please fill valid Email address",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                } else {
                    Toast toast = Toast.makeText(AddMorePerson.this,
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

    }

    private void clearAll() {
        etName.setText("");
        etDesignation.setText("");
        etPhone.setText("");
        etEmail.setText("");
    }

    private void refreshList(String Type) {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String name, designation, phone, email;
        try {
            Cursor c = db.selectMorePerson(Type);
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
