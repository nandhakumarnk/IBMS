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
 * Created by COBURG DESIGN on 08-01-2016.
 */
public class ExpenseAddPage extends AppCompatActivity {
    EditText _ExpenseAmount;
    Spinner _spnExpenseType;
    private TextInputLayout inputLayoutExpenseAmount;
    Button _Submit, _Clear;
    private ExpenseDBHelper db = new ExpenseDBHelper(this);
    String companyName, projectType, expenseTypeId = "";
    long id;
    Vector<String> vecExpenses;
    List<String> lsExpenses = new ArrayList<String>();
    ArrayAdapter<String> adptExpenses;
    TextView txtUOMAdd;
    private SharedPreferences prefs;
    private String prefName = "spinner_expense";
    int idValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_add_page);

        _spnExpenseType = (Spinner) findViewById(R.id.spn_expenses_type);
        _ExpenseAmount = (EditText) findViewById(R.id.edtExpense_amount);
        _Submit = (Button) findViewById(R.id.add_expense);
        _Clear = (Button) findViewById(R.id.remove_expense);
        vecExpenses = new Vector<String>();

        inputLayoutExpenseAmount = (TextInputLayout) findViewById(R.id.input_layout_expense_amount);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        id = getIntent().getExtras().getLong("id");
        getfromdatabase();
        loadSpinner();

        if (id == -1) {
            //personDetails = new MainCommonPersonModel();
//            getfromdatabase();
//            loadSpinner();
        } else {

            try {
                int value = (int) id;

                ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, lsExpenses);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                _spnExpenseType.setAdapter(adp);


                Cursor c = db.getExpenseListFull(value);
                if (c.getCount() > 0) {
                    prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                    idValue = prefs.getInt("last_val", 0);
                    _spnExpenseType.setSelection(idValue);
                    _ExpenseAmount.setText(c.getString(3));
                    expenseTypeId = c.getString(5);
                }

                db.close();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                        .show();
                e.printStackTrace();
            }
        }

        _spnExpenseType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                // TODO Auto-generated method stub
                String ExpTypeId = _spnExpenseType.getSelectedItem().toString();
                getExpenseTypeID(ExpTypeId);

                prefs = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                //---save the values in the EditText view to preferences---
                editor.putInt("last_val", pos);

                //---saves the values---
                editor.commit();


//                Toast.makeText(getBaseContext(), _spnExpenseType.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddExpenseInDB();
            }
        });

        _Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void getExpenseTypeID(String expTypeId) {
        Cursor c = db.getExpensesTypeIDValue(expTypeId);
        if (c.getCount() > 0) {

            String id = c.getString(0);
            expenseTypeId = id;
        }
    }

    private void AddExpenseInDB() {

        final String ExpenseType = _spnExpenseType.getSelectedItem().toString();
        final String ExpenseAmount = _ExpenseAmount.getText().toString().trim();

        if (!validateExpenseAmount()) {
            return;
        }

        try {
            int value = (int) id;
            Cursor c = db.getExpenseListFull(value);
            if (c.getCount() > 0) {
                _ExpenseAmount.setText(c.getString(3));
            }

            db.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }

        if ((int) id < 0) {
            db.insertExpense(companyName, ExpenseType, ExpenseAmount, projectType, expenseTypeId, "N");
        } else {
            db.updateExpense(id, companyName, ExpenseType, ExpenseAmount, projectType, expenseTypeId);
        }

        setResult(RESULT_OK);

        finish();
    }

    private boolean validateExpenseAmount() {
        if (_ExpenseAmount.getText().toString().trim().isEmpty()) {
            inputLayoutExpenseAmount.setError(getString(R.string.err_msg_expAmount));
            requestFocus(_ExpenseAmount);
            return false;
        } else {
            inputLayoutExpenseAmount.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void clear() {
        _ExpenseAmount.setText("");
    }

    public void getfromdatabase() {
        //to test
        try {
            Cursor c = db.getExpensesType();

            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        vecExpenses.add(c.getString(2));
                        expenseTypeId = c.getString(1);
                    } while (c.moveToNext());
                }
            }

            for (int i = 0; i < vecExpenses.size(); i++) {
                lsExpenses.add(vecExpenses.get(i));
            }

            HashSet hs = new HashSet();
            TreeSet ts = new TreeSet(hs);
            ts.addAll(lsExpenses);
            lsExpenses.clear();
            lsExpenses.addAll(ts);
            db.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void loadSpinner() {
        adptExpenses = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lsExpenses);
        adptExpenses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spnExpenseType.setAdapter(adptExpenses);
        _spnExpenseType.setWillNotDraw(false);
    }
}
