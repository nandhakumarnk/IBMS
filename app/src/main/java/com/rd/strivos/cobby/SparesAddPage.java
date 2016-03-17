package com.rd.strivos.cobby;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Created by COBURG DESIGN on 21-01-2016.
 */
public class SparesAddPage extends AppCompatActivity {
    Spinner _spnSparesPrincipal, _spnSparesPartNo;
    TextView txtShortDescResult, txtUOMResult;
    private TextInputLayout inputLayoutSparesQuantity;
    EditText _Quantity;
    Button btnSave, btnClear;
    private SparesDBHelper db = new SparesDBHelper(this);
    Vector<String> vecPrincipal, vecPartno;
    List<String> lsPrincipal = new ArrayList<String>();
    ArrayAdapter<String> adptPrincipal, adptPartno;
    String companyName, projectType, sparesPrincipal = "";
    long id;
    private SharedPreferences prefs;
    private SharedPreferences prefs_part;
    private String prefName = "spinner_principal";
    private String prefNamePart = "spinner_part";
    int idValue = 0, idValuePart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spares_add_page);

        _spnSparesPrincipal = (Spinner) findViewById(R.id.spn_selectPrincipal_spares);
        _spnSparesPartNo = (Spinner) findViewById(R.id.spn_selectPartno_spares);
        txtShortDescResult = (TextView) findViewById(R.id.txtShortDescResult_spares);
        txtUOMResult = (TextView) findViewById(R.id.txtUOMResult_spares);
        inputLayoutSparesQuantity = (TextInputLayout) findViewById(R.id.input_layout_spares_qty);
        _Quantity = (EditText) findViewById(R.id.edtQty_spares);
        btnSave = (Button) findViewById(R.id.add_spares);
        btnClear = (Button) findViewById(R.id.remove_spares);
        vecPrincipal = new Vector<String>();

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");
        id = getIntent().getExtras().getLong("id");

        getFromDB();
        loadSpinner();

        if (id == -1) {
            //personDetails = new MainCommonPersonModel();
//            getfromdatabase();
//            loadSpinner();
        } else {

            try {
                int value = (int) id;

                ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, lsPrincipal);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                _spnSparesPrincipal.setAdapter(adp);


                Cursor c = db.getSparesListFull(value);
                if (c.getCount() > 0) {
                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    idValue = prefs.getInt("last_val", 1);
                    prefs_part = getSharedPreferences(prefNamePart, MODE_PRIVATE);
                    idValuePart = prefs.getInt("last_val_part", 3);
                    _spnSparesPrincipal.setSelection(idValue);
                    _spnSparesPartNo.setSelection(idValuePart);
                    txtShortDescResult.setText(c.getString(4));
                    txtUOMResult.setText(c.getString(5));
                    _Quantity.setText(c.getString(6));
                    sparesPrincipal = c.getString(2);
                }

                db.close();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                        .show();
                e.printStackTrace();
            }
        }

        _spnSparesPrincipal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                String principal = _spnSparesPrincipal.getSelectedItem().toString();
                Partlist(principal);

                prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putInt("last_val", pos);

                editor.commit();

//                Toast.makeText(getBaseContext(), _spnSparesPrincipal.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        _spnSparesPartNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                String Partno = _spnSparesPartNo.getSelectedItem().toString();
                Shortlist(Partno);

                prefs_part = getSharedPreferences(prefNamePart, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs_part.edit();

                editor.putInt("last_val_part", pos);

                editor.commit();

//                Toast.makeText(getBaseContext(), _spnSparesPartNo.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddProductInDB();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

    }

    private void clear() {
        _Quantity.setText("");
    }

    private void AddProductInDB() {

        String sparesID = "";
        final String SparesPrincipal = _spnSparesPrincipal.getSelectedItem().toString();
        String SparesPartNo = _spnSparesPartNo.getSelectedItem().toString();


        try {
            Cursor c = db.selectLookUpsSpares(SparesPartNo);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        sparesID = c.getString(4);
                    } while (c.moveToNext());
                }
            }

        } catch (Exception ex) {
        }

        String SparesID = sparesID;
        String ShortDescription = txtShortDescResult.getText().toString();
        String UOM = txtUOMResult.getText().toString();
        final String SparesQuantity = _Quantity.getText().toString().trim();
        String CompanyName = companyName;
        String ProjectType = projectType;
        String Status = "N";

        if (!validateQuantity()) {
            return;
        }

        if ((int) id < 0) {
            db.insertSpares(SparesPrincipal, SparesID, SparesPartNo, ShortDescription, UOM, SparesQuantity, ProjectType, CompanyName, Status);
        } else {
            db.updateSpares(id, SparesPrincipal, SparesID, SparesPartNo, ShortDescription, UOM, SparesQuantity, ProjectType, CompanyName);
        }

        setResult(RESULT_OK);

        finish();
    }

    private boolean validateQuantity() {
        if (_Quantity.getText().toString().trim().isEmpty()) {
            inputLayoutSparesQuantity.setError(getString(R.string.err_msg_sparesQty));
            requestFocus(_Quantity);
            return false;
        } else {
            inputLayoutSparesQuantity.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void Partlist(String principal) {
        try {

            List<String> lst_partno = new ArrayList<String>();
            Vector<String> vec_part = new Vector<String>();
            Cursor c = db.selectPartno(principal);

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vec_part.add(c.getString(1));
                    } while (c.moveToNext());
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

            adptPartno = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lst_partno);
            adptPartno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            _spnSparesPartNo.setAdapter(adptPartno);
            _spnSparesPartNo.setWillNotDraw(false);
        } catch (Exception e) {

        }
    }

    private void Shortlist(String Partno) {
        String shortDesc = "", uom = "";
        try {

            Cursor c = db.selectLookUpsSpares(Partno);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        shortDesc = c.getString(2);
                        uom = c.getString(3);
                    } while (c.moveToNext());
                }
            }

            txtShortDescResult.setText(shortDesc);
            txtUOMResult.setText(uom);

        } catch (Exception e) {

        }
    }

    private void getFromDB() {

        try {
            Cursor c = db.getSparesLookupType();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecPrincipal.add(c.getString(1));
                    } while (c.moveToNext());
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
        adptPrincipal = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsPrincipal);
        adptPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spnSparesPrincipal.setAdapter(adptPrincipal);
        _spnSparesPrincipal.setWillNotDraw(false);
    }
}
