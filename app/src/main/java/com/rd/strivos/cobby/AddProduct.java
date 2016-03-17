package com.rd.strivos.cobby;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

public class AddProduct extends Activity {

    Spinner spnPrincipal, spnPartno, spnShortDesc, spnProductType, spnUOM;
    EditText etQty;
    Button btnSave, btnClear;
    SQLiteHelper db;
    Vector<String> vecPrincipal, vecPartno;
    List<String> lsPrincipal = new ArrayList<String>();
    //List<String> lsPartno = new ArrayList<String>();
    ArrayAdapter<String> adptPrincipal, adptPartno, adptShortDesc, adptProductType, adptUOM;
    String DefaultValue = "Please select";
    TextView txtUOMAdd, txtShortAdd, txtpTypeAdd;
    String uom, shortDesc, pType, eqType, id, company_name, sales_type;
    private ListView lstView;
    private ListAdapter listAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        db = new SQLiteHelper(getApplicationContext());
        spnPrincipal = (Spinner) findViewById(R.id.spn_selectPrincipal_product);
        spnPartno = (Spinner) findViewById(R.id.spn_selectPartno_product);
        spnShortDesc = (Spinner) findViewById(R.id.spn_selectShortDesc_product);
        spnProductType = (Spinner) findViewById(R.id.spn_selectProductType_product);
        spnUOM = (Spinner) findViewById(R.id.spn_selectUOM_product);
        etQty = (EditText) findViewById(R.id.edtQty_product);
        btnSave = (Button) findViewById(R.id.add_product);
        btnClear = (Button) findViewById(R.id.remove_product);
        txtUOMAdd = (TextView) findViewById(R.id.txtUOMResult_product);
        txtShortAdd = (TextView) findViewById(R.id.txtShortDescResult_product);
        txtpTypeAdd = (TextView) findViewById(R.id.txtProductTypeResult_product);
        vecPrincipal = new Vector<String>();
        lstView = (ListView) findViewById(R.id.lst_product);
        listAdaptor = lstView.getAdapter();
        Bundle b = getIntent().getExtras();
        company_name = b.getString("Key_name");
        sales_type = b.getString("Key_project");

        getfromdatabase();
        loadSpinner();

        spnPrincipal.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String principal = arg0.getItemAtPosition(arg2).toString();
                Partlist(principal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spnPartno.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                String Partno = arg0.getItemAtPosition(arg2).toString();
                Shortlist(Partno);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String PARTNO = spnPartno.getSelectedItem().toString();
                Cursor c = db.selectShortDesc(PARTNO);
                if (c.getCount() > 0) {
                    for (int i = 0; i < c.getCount(); i++) {
                        for (int j = 0; j < c.getColumnCount(); j++) {
                            if (c.getCount() > 0) {
                                shortDesc = c.getString(2);
                                pType = c.getString(3);
                                uom = c.getString(4);
                                eqType = c.getString(5);
                                id = c.getString(6);
                            }
                        }
                    }
                }

                String PRINCIPAL = spnPrincipal.getSelectedItem().toString();
                String ID = id;
                String SHORTDESCRIPTION = txtShortAdd.getText().toString();
                String PRODUCTTYPE = txtpTypeAdd.getText().toString();
                String EQUIPMENTTYPE = eqType;
                String UOM = txtUOMAdd.getText().toString();
                String CompanyName = company_name;
                String SalesType = sales_type;
                String Quantity = etQty.getText().toString();
                String Status = "N";

                if (Quantity.length() != 0) {
                    db.inserProduct(PRINCIPAL, ID, PARTNO, SHORTDESCRIPTION, PRODUCTTYPE, EQUIPMENTTYPE, UOM, SalesType, Quantity, CompanyName, Status);
                    clearAll();

                    Toast toast = Toast.makeText(AddProduct.this,
                            "New product added",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(AddProduct.this,
                            "Please fill quantity of product",
                            Toast.LENGTH_SHORT);
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
        etQty.setText("");
    }

    private void refreshList() {

        ArrayAdapter listAdaptor = new ArrayAdapter(getApplicationContext(),
                R.layout.text_view);

        String PRINCIPAL, ID, PARTNO, SHORTDESCRIPTION, PRODUCTTYPE, EQUIPMENTTYPE, UOM, Projectid, Quantity, Status;

        try {
            Cursor c = db.selectProduct();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                PARTNO = c.getString(3);
                                UOM = c.getString(7);
                                Quantity = c.getString(9);
                                listAdaptor.add(PARTNO + " - " + UOM
                                        + "-" + Quantity);
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


    public void Shortlist(String partno) {
        try {

            List<String> lst_short = new ArrayList<String>();
            Vector<String> vec_short = new Vector<String>();
            Cursor c = db.selectShortDesc(partno);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            shortDesc = c.getString(2);
                            pType = c.getString(3);
                            uom = c.getString(4);
                        }
                    }
                }
            }

            for (int i = 0; i < vec_short.size(); i++) {
                lst_short.add(vec_short.get(i));
            }
            txtUOMAdd.setText(uom);
            txtShortAdd.setText(shortDesc);
            txtpTypeAdd.setText(pType);

        } catch (Exception e) {

        }
    }

    public void ProductTypelist(String partno) {
        try {
            List<String> lst_pType = new ArrayList<String>();
            Vector<String> vec_pType = new Vector<String>();
            Cursor c = db.selectShortDesc(partno);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vec_pType.add(c.getString(2));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vec_pType.size(); i++) {
                lst_pType.add(vec_pType.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lst_pType);
            lst_pType.clear();
            lst_pType.addAll(ts);

            adptProductType = new ArrayAdapter<String>(AddProduct.this, android.R.layout.simple_spinner_item, lst_pType);
            adptProductType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnProductType.setAdapter(adptProductType);
            spnProductType.setWillNotDraw(false);
        } catch (Exception e) {

        }
    }

    public void UOMlist(String partno) {
        try {
            List<String> lst_uom = new ArrayList<String>();
            Vector<String> vec_uom = new Vector<String>();
            Cursor c = db.selectShortDesc(partno);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vec_uom.add(c.getString(3));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vec_uom.size(); i++) {
                lst_uom.add(vec_uom.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lst_uom);
            lst_uom.clear();
            lst_uom.addAll(ts);

            adptUOM = new ArrayAdapter<String>(AddProduct.this, android.R.layout.simple_spinner_item, lst_uom);
            adptUOM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnUOM.setAdapter(adptUOM);
            spnUOM.setWillNotDraw(false);
        } catch (Exception e) {

        }
    }

    public void Partlist(String principal) {
        try {

            List<String> lst_partno = new ArrayList<String>();
            Vector<String> vec_part = new Vector<String>();
            Cursor c = db.selectPartno(principal);
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vec_part.add(c.getString(1));

                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vec_part.size(); i++) {
                lst_partno.add(vec_part.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lst_partno);
            lst_partno.clear();
            lst_partno.addAll(ts);

            adptPartno = new ArrayAdapter<String>(AddProduct.this, android.R.layout.simple_spinner_item, lst_partno);
            adptPartno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnPartno.setAdapter(adptPartno);
            spnPartno.setWillNotDraw(false);
        } catch (Exception e) {

        }
    }

    public void getfromdatabase() {

        try {
            Cursor c = db.selectProductLookup();
            if (c.getCount() > 0) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        if (c.getCount() > 0) {
                            while (c.moveToNext()) {
                                vecPrincipal.add(c.getString(1));
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vecPrincipal.size(); i++) {
                lsPrincipal.add(vecPrincipal.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsPrincipal);
            lsPrincipal.clear();
            lsPrincipal.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void loadSpinner() {

        adptPrincipal = new ArrayAdapter<String>(AddProduct.this,
                android.R.layout.simple_spinner_item, lsPrincipal);
        adptPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPrincipal.setAdapter(adptPrincipal);
        spnPrincipal.setWillNotDraw(false);
    }
}
