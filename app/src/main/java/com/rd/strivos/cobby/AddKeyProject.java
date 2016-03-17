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

public class AddKeyProject extends Activity {

    private EditText edtPROJECTNAME, edtYEARCOMPLETED, edtVALUE;
    private Button btnSave, btnClear;
    private ListView lstView;
    private ListAdapter listAdaptor;
    SQLiteHelper db;
    String name, project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addkeyproject);
        edtPROJECTNAME = (EditText) findViewById(R.id.edtPROJECTNAME_keyproj);
        edtYEARCOMPLETED = (EditText) findViewById(R.id.edtYEARCOMPLETED_keyproj);
        edtVALUE = (EditText) findViewById(R.id.edtVALUE_keyproj);
        btnSave = (Button) findViewById(R.id.add_keyproj);
        btnClear = (Button) findViewById(R.id.remove_keyproj);
        lstView = (ListView) findViewById(R.id.lst_keyproj);
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
                String PROJECTNAME = edtPROJECTNAME.getText().toString();
                String YEARCOMPLETED = edtYEARCOMPLETED.getText().toString();
                String VALUE = edtVALUE.getText().toString();

                String ProjectType = project;
                String Status = "N";

                if (PROJECTNAME.length() != 0 & YEARCOMPLETED.length() != 0
                        & VALUE.length() != 0) {


                    db.insertKeyProject(ProjectID, PROJECTNAME, YEARCOMPLETED,
                            VALUE, ProjectType, Status);
                    clearAll();
                    Toast toast = Toast.makeText(AddKeyProject.this,
                            "Saved",
                            Toast.LENGTH_SHORT);
                    toast.show();


                } else {
                    Toast toast = Toast.makeText(AddKeyProject.this,
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
        edtPROJECTNAME.setText("");
        edtYEARCOMPLETED.setText("");
        edtVALUE.setText("");

    }

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String PROJECTNAME, YEARCOMPLETED, VALUE;
        try {
            Cursor c = db.selectKeyProject();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        PROJECTNAME = c.getString(1);
                        YEARCOMPLETED = c.getString(2);
                        VALUE = c.getString(3);

                        listAdaptor.add(PROJECTNAME + " - " + YEARCOMPLETED
                                + "-" + VALUE);
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
