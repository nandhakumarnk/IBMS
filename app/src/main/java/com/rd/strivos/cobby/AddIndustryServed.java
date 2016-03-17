package com.rd.strivos.cobby;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

public class AddIndustryServed extends Activity {

    private Button btnSave;
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
        setContentView(R.layout.addindustryserved);
        spnIndustry = (Spinner) findViewById(R.id.spn_selectindustry_inserv);
        spnProcess = (Spinner) findViewById(R.id.spn_selectprocess_inserv);

        btnSave = (Button) findViewById(R.id.add_inserv);
        vecIndustry = new Vector<String>();

        lstView = (ListView) findViewById(R.id.lst_inserv);
        listAdaptor = lstView.getAdapter();
        db = new SQLiteHelper(getApplicationContext());
        Bundle b = getIntent().getExtras();
        name = b.getString("Key_name");
        project = b.getString("Key_project");

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
                String ProjectType = project;
                String Status = "N";

                if (INDUSTRYTYEPE.length() != 0 & PROCESSTYPE.length() != 0) {

                    db.insertIndustryServed(ProjectID, getIndustryID,
                            getProcessID, ProjectType, Status);
                    refreshList();
                    Toast toast = Toast.makeText(AddIndustryServed.this,
                            "Saved", Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Toast toast = Toast.makeText(AddIndustryServed.this,
                            "Please fill all fileds.", Toast.LENGTH_SHORT);
                    toast.show();
                }
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
                if (c.moveToFirst()) {
                    do {
                        getProcessID = c.getString(0);
                    } while (c.moveToNext());
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
                if (c.moveToFirst()) {
                    do {
                        GetIndustryID = c.getString(0);
                    } while (c.moveToNext());
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
                if (c.moveToFirst()) {
                    do {
                        vecProcess.add(c.getString(1));
                    } while (c.moveToNext());
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
        // Industry
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
                if (c.moveToFirst()) {
                    do {
                        getIndustryID = c.getString(0);
                    } while (c.moveToNext());
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
                if (c.moveToFirst()) {
                    do {
                        vecIndustry.add(c.getString(2));
                    } while (c.moveToNext());
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

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);
        String INDUSTRY, PROCESS;
        try {
            Cursor c = db.selectIndustryServed();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        INDUSTRY = c.getString(1);
                        PROCESS = c.getString(2);
                        listAdaptor.add(INDUSTRY + " - " + PROCESS);
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
