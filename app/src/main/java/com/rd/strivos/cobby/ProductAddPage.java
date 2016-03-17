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
 * Created by COBURG DESIGN on 11-01-2016.
 */
public class ProductAddPage extends AppCompatActivity {

    Spinner _spnProductPrincipal, _spnProdcutPartNo;
    TextView txtShortDescResult, txtProductTypeResult, txtUOMResult;
    private TextInputLayout inputLayoutProductQuantity;
    EditText _Quantity;
    Button btnSave, btnClear;
    private ProductDBHelper db = new ProductDBHelper(this);
    Vector<String> vecPrincipal, vecPartno;
    List<String> lsPrincipal = new ArrayList<String>();
    ArrayAdapter<String> adptPrincipal, adptPartno;
    String companyName, projectType, productPrincipal = "";
    long id;
    private SharedPreferences prefs;
    private SharedPreferences prefs_part;
    private String prefName = "spinner_principal";
    private String prefNamePart = "spinner_part";
    int idValue = 0,idValuePart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_page);

        _spnProductPrincipal = (Spinner) findViewById(R.id.spn_selectPrincipal_product);
        _spnProdcutPartNo = (Spinner) findViewById(R.id.spn_selectPartno_product);
        txtShortDescResult = (TextView) findViewById(R.id.txtShortDescResult_product);
        txtProductTypeResult = (TextView) findViewById(R.id.txtProductTypeResult_product);
        txtUOMResult = (TextView) findViewById(R.id.txtUOMResult_product);
        inputLayoutProductQuantity = (TextInputLayout) findViewById(R.id.input_layout_product_qty);
        _Quantity = (EditText) findViewById(R.id.edtQty_product);
        btnSave = (Button) findViewById(R.id.add_product);
        btnClear = (Button) findViewById(R.id.remove_product);
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
                _spnProductPrincipal.setAdapter(adp);


                Cursor c = db.getProductListFull(value);
                if (c.getCount() > 0) {
                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    idValue = prefs.getInt("last_val", 1);
                    prefs_part = getSharedPreferences(prefNamePart, MODE_PRIVATE);
                    idValuePart = prefs.getInt("last_val_part", 3);
                    _spnProductPrincipal.setSelection(idValue);
                    _spnProdcutPartNo.setSelection(idValuePart);
                    txtShortDescResult.setText(c.getString(4));
                    txtProductTypeResult.setText(c.getString(5));
                    txtUOMResult.setText(c.getString(7));
                    _Quantity.setText(c.getString(8));
                    productPrincipal = c.getString(2);
                }

                db.close();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                        .show();
                e.printStackTrace();
            }
        }

        _spnProductPrincipal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                String principal = _spnProductPrincipal.getSelectedItem().toString();
                Partlist(principal);

                prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putInt("last_val", pos);

                editor.commit();

//                Toast.makeText(getBaseContext(), _spnProductPrincipal.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        _spnProdcutPartNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                String Partno = _spnProdcutPartNo.getSelectedItem().toString();
                Shortlist(Partno);

                prefs_part = getSharedPreferences(prefNamePart, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs_part.edit();

                editor.putInt("last_val_part", pos);

                editor.commit();

//                Toast.makeText(getBaseContext(), _spnProdcutPartNo.getSelectedItem().toString(),
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

    private void AddProductInDB() {

        String productID = "", equipmentType = "";

        final String ProductPrincipal = _spnProductPrincipal.getSelectedItem().toString();
        final String ProductPartNo = _spnProdcutPartNo.getSelectedItem().toString();

        Cursor c = db.selectLookUps(ProductPartNo);
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    equipmentType = c.getString(5);
                    productID = c.getString(6);
                } while (c.moveToNext());
            }
        }

        String ProductID = productID;
        String ShortDescription = txtShortDescResult.getText().toString();
        String ProductType = txtProductTypeResult.getText().toString();
        String EquipmentType = equipmentType;
        String UOM = txtUOMResult.getText().toString();
        final String ProductQuantity = _Quantity.getText().toString().trim();
        String CompanyName = companyName;
        String ProjectType = projectType;
        String Status = "N";

        if (!validateQuantity()) {
            return;
        }

        if ((int) id < 0) {
            db.insertProduct(ProductPrincipal, ProductID, ProductPartNo, ShortDescription, ProductType, EquipmentType, UOM, ProductQuantity, ProjectType, CompanyName, Status);
        } else {
            db.updateProduct(id, ProductPrincipal, ProductID, ProductPartNo, ShortDescription, ProductType, EquipmentType, UOM, ProductQuantity, ProjectType, CompanyName);
        }

        setResult(RESULT_OK);

        finish();
    }

    private boolean validateQuantity() {
        if (_Quantity.getText().toString().trim().isEmpty()) {
            inputLayoutProductQuantity.setError(getString(R.string.err_msg_proQty));
            requestFocus(_Quantity);
            return false;
        } else {
            inputLayoutProductQuantity.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    // LOAD TEXTVIEW FOR LOOKUP TEXT

    private void Shortlist(String Partno) {
        String shortDesc = "", productType = "", uom = "";
        try {

            Cursor c = db.selectLookUps(Partno);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        shortDesc = c.getString(2);
                        productType = c.getString(3);
                        uom = c.getString(4);
                    } while (c.moveToNext());
                }
            }

            txtShortDescResult.setText(shortDesc);
            txtProductTypeResult.setText(productType);
            txtUOMResult.setText(uom);

        } catch (Exception e) {

        }
    }

    // LOAD PART NUMBER

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

            adptPartno = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lst_partno);
            adptPartno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            _spnProdcutPartNo.setAdapter(adptPartno);
            _spnProdcutPartNo.setWillNotDraw(false);
        } catch (Exception e) {

        }
    }

    // CLEAR TEXT

    private void clear() {
        _Quantity.setText("");
    }

    // LOAD PRINCIPAL DETAILS

    private void getFromDB() {

        try {
            Cursor c = db.getProductLookupType();

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
        adptPrincipal = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, lsPrincipal);
        adptPrincipal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spnProductPrincipal.setAdapter(adptPrincipal);
        _spnProductPrincipal.setWillNotDraw(false);
    }

    //////////////   END LOAD PRINCIPAL  /////////////////
}
