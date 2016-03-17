package com.rd.strivos.cobby;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by COBURG DESIGN on 07-01-2016.
 */
public class PersonAddPage extends AppCompatActivity {

    EditText _PersonName, _PersonDesignation, _Email, _Mobile;
    private TextInputLayout inputLayoutPersonName, inputLayoutDesignation, inputLayoutEmail, inputLayoutMobile;
    Button _Submit, _Clear;
    private PersonDBHelper db = new PersonDBHelper(this);
    String companyName, projectType;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_more_person);

        _PersonName = (EditText) findViewById(R.id.edtName_more);
        _PersonDesignation = (EditText) findViewById(R.id.edtDesignation_more);
        _Email = (EditText) findViewById(R.id.edtemail_more);
        _Mobile = (EditText) findViewById(R.id.edtPhone_more);
        _Submit = (Button) findViewById(R.id.add_more);
        _Clear = (Button) findViewById(R.id.remove_more);

        inputLayoutPersonName = (TextInputLayout) findViewById(R.id.input_layout_employee_name);
        inputLayoutDesignation = (TextInputLayout) findViewById(R.id.input_layout_designation);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutMobile = (TextInputLayout) findViewById(R.id.input_layout_mobile);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");
        id = getIntent().getExtras().getLong("id");

        if (id == -1) {
            //personDetails = new MainCommonPersonModel();
        } else {
            //personDetails = db.getReminderId(id);
            try {
                int value = (int)id;
                Cursor c = db.getPersonListFull(value);
                if (c.getCount() > 0) {
//                    for (int i = 0; i < c.getCount(); i++) {
//                        for (int j = 0; j < c.getColumnCount(); j++) {
                            //if (c.getCount() > 0) {
                                //while (c.moveToNext()) {
                                    _PersonName.setText(c.getString(2));
                                    _PersonDesignation.setText(c.getString(3));
                                    _Email.setText(c.getString(5));
                                    _Mobile.setText(c.getString(4));
                                //}
                            //}
                        //}
                    //}
                }
                db.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                        .show();
                e.printStackTrace();
            }
//            _PersonName.setText(personDetails.personName);
//            _PersonDesignation.setText(personDetails.personDesignation);
//            _Email.setText(personDetails.personEmail);
//            _Mobile.setText(personDetails.personMobile);
        }

        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddPersonInDB();
            }
        });

        _Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void AddPersonInDB() {
        final String PersonName = _PersonName.getText().toString().trim();
        final String PersonDesignation = _PersonDesignation.getText().toString().trim();
        final String Email = _Email.getText().toString().trim();
        final String Mobile = _Mobile.getText().toString().trim();

        if (!validatePersonName()) {
            return;
        }

        if (!validatePersonDesignation()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!isValidMobile()) {
            return;
        }

        if ((int)id < 0) {
            db.insertAddMorePerson(companyName, PersonName, PersonDesignation, Mobile, Email, projectType, "N");
        } else {
            db.updateAddMorePerson(id, companyName, PersonName, PersonDesignation, Mobile, Email, projectType);
        }

        setResult(RESULT_OK);

        finish();
    }

    private boolean validatePersonName() {
        if (_PersonName.getText().toString().trim().isEmpty()) {
            inputLayoutPersonName.setError(getString(R.string.err_msg_perName));
            requestFocus(_PersonName);
            return false;
        } else {
            inputLayoutPersonName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePersonDesignation() {
        if (_PersonDesignation.getText().toString().trim().isEmpty()) {
            inputLayoutDesignation.setError(getString(R.string.err_msg_perDesign));
            requestFocus(_PersonDesignation);
            return false;
        } else {
            inputLayoutDesignation.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        String email = _Email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(_Email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidMobile() {
        String mobile = _Mobile.getText().toString().trim();
        if (!Pattern.matches("[a-zA-Z]+", mobile)) {
            if (mobile.length() < 10 || mobile.length() > 13) {
                inputLayoutMobile.setError(getString(R.string.err_msg_mobile));
                requestFocus(_Mobile);
                return false;
            } else {
                inputLayoutMobile.setErrorEnabled(false);
            }
        } else {
            inputLayoutMobile.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void clear()
    {
        _PersonDesignation.setText("");
        _PersonName.setText("");
        _Email.setText("");
        _Mobile.setText("");
    }
}
