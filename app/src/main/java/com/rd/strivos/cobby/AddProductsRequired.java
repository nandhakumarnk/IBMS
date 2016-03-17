package com.rd.strivos.cobby;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

public class AddProductsRequired extends Activity {

    private EditText edtAPPLICATION, edtPRODUCTREQ;
    private Button btnSave, btnClear;
    private ListView lstView;
    private ListAdapter listAdaptor;
    SQLiteHelper db;
    String name, project, getIndustryID, getProcessID;
    Spinner spnIndustry, spnProcess;
    List<String> lsIndustry = new ArrayList<String>();
    Vector<String> vecIndustry;
    ArrayAdapter<String> adptIndustry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproductsrequired);
        spnIndustry = (Spinner) findViewById(R.id.spn_selectindustry_productreq);
        spnProcess = (Spinner) findViewById(R.id.spn_selectprocess_productreq);
        edtAPPLICATION = (EditText) findViewById(R.id.edtAPPLICATION_productreq);
        edtPRODUCTREQ = (EditText) findViewById(R.id.edtPRODUCTREQ_productreq);
        btnSave = (Button) findViewById(R.id.add_productreq);
        btnClear = (Button) findViewById(R.id.remove_productreq);
        lstView = (ListView) findViewById(R.id.lst_productreq);
        listAdaptor = lstView.getAdapter();
        db = new SQLiteHelper(getApplicationContext());
        Bundle b = getIntent().getExtras();
        name = b.getString("Key_name");
        project = b.getString("Key_project");
        vecIndustry = new Vector<String>();

        loadIndustry();
        loadSpinIndustry();
        IndustryID();
        ProcessID();

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String ProjectID = name;
                String INDUSTRYTYEPE = spnIndustry.getSelectedItem().toString();
                String PROCESSTYPE = spnProcess.getSelectedItem().toString();
                String APPLICATION = edtAPPLICATION.getText().toString();
                String PRODUCTREQ = edtPRODUCTREQ.getText().toString();
                String ProjectType = project;
                String Status = "N";

                if (INDUSTRYTYEPE.length() != 0 & PROCESSTYPE.length() != 0
                        & APPLICATION.length() != 0 & PRODUCTREQ.length() != 0) {


                    db.insertProductsRequired(ProjectID, getIndustryID, getProcessID,
                            APPLICATION, PRODUCTREQ, ProjectType, Status);
                    clearAll();
                    Toast toast = Toast.makeText(AddProductsRequired.this,
                            "Saved",
                            Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Toast toast = Toast.makeText(AddProductsRequired.this,
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

        spnIndustry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String IndustryName = arg0.getItemAtPosition(arg2).toString();
                selectIndustryID(IndustryName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void ProcessID() {
        String type = spnProcess.getSelectedItem().toString();

        try {
            Cursor c = db.getProcessID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {

                            getProcessID = c.getString(0);

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

    private void selectIndustryID(String IndustryID) {
        String GetIndustryID = null;
        try {
            Cursor c = db.IndustryID(IndustryID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            GetIndustryID = c.getString(0);
                        }
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        LoadProcess(GetIndustryID);
    }

    private void LoadProcess(String ID) {

        Vector<String> vecProcess = new Vector<String>();
        List<String> lsProcess = new ArrayList<String>();
        ArrayAdapter<String> adptProcess;
        try {
            Cursor c = db.selectProcess(ID);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecProcess.add(c.getString(1));
                            }
                        }
                    }
                }
            } else {
                lsProcess.add("No sub process");
            }

            for (int i = 0; i < vecProcess.size(); i++) {
                lsProcess.add(vecProcess.get(i));
            }

            @SuppressWarnings("rawtypes")
            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsProcess);
            lsProcess.clear();
            lsProcess.addAll(ts);
            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        // Process
        adptProcess = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsProcess);
        adptProcess
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnProcess.setAdapter(adptProcess);
        spnProcess.setWillNotDraw(false);
    }

    private void loadSpinIndustry() {

        adptIndustry = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsIndustry);
        adptIndustry
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnIndustry.setAdapter(adptIndustry);
        spnIndustry.setWillNotDraw(false);
    }

    private void IndustryID() {
        String type = spnIndustry.getSelectedItem().toString();

        try {
            Cursor c = db.getIndustryID(type);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            getIndustryID = c.getString(0);
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

    private void loadIndustry() {

        try {
            Cursor c = db.selectIndustry();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecIndustry.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecIndustry.size(); i++) {
                lsIndustry.add(vecIndustry.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsIndustry);
            lsIndustry.clear();
            lsIndustry.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }

    private void clearAll() {
        edtAPPLICATION.setText("");
        edtPRODUCTREQ.setText("");
    }

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String INDUSTRYNAME, PROCESS, APPLICATION, PRODUCTREQ;
        try {
            Cursor c = db.selectProductsRequired();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                INDUSTRYNAME = c.getString(1);
                                PROCESS = c.getString(2);
                                APPLICATION = c.getString(3);
                                PRODUCTREQ = c.getString(4);
                                listAdaptor.add(INDUSTRYNAME + " - " + PROCESS
                                        + "-" + APPLICATION + "-" + PRODUCTREQ);
                            }
                        }
                    }
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
