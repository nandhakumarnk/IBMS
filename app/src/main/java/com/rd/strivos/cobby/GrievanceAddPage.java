package com.rd.strivos.cobby;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by COBURG DESIGN on 23-01-2016.
 */
public class GrievanceAddPage extends AppCompatActivity {

    EditText _Grievance, _Solution;
    private TextInputLayout inputLayoutGrievance, inputLayoutSolution;
    Button btnSave, btnClear;
    private GrievanceDBHelper db = new GrievanceDBHelper(this);
    String companyName, projectType;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance_add_page);

        _Grievance = (EditText) findViewById(R.id.et_grievance);
        _Solution = (EditText) findViewById(R.id.et_Solution);
        inputLayoutGrievance = (TextInputLayout) findViewById(R.id.input_layout_grievance);
        inputLayoutSolution = (TextInputLayout) findViewById(R.id.input_layout_solution);
        btnSave = (Button) findViewById(R.id.add_grievance);
        btnClear = (Button) findViewById(R.id.remove_grievance);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");
        id = getIntent().getExtras().getLong("id");

        if (id == -1) {
            //personDetails = new MainCommonPersonModel();
//            getfromdatabase();
//            loadSpinner();
        } else {

            try {
                int value = (int) id;


                Cursor c = db.getGrievanceListFull(value);
                if (c.getCount() > 0) {
                    _Grievance.setText(c.getString(1));
                    _Solution.setText(c.getString(2));
                }

                db.close();

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                        .show();
                e.printStackTrace();
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddGrievanceInDB();
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
        _Grievance.setText("");
        _Solution.setText("");
    }

    private void AddGrievanceInDB() {

        final String grievance = _Grievance.getText().toString().trim();
        final String solution = _Solution.getText().toString().trim();
        String CompanyName = companyName;
        String ProjectType = projectType;
        String Status = "N";

        if (!validateGrievance()) {
            return;
        }

        if (!validateSolution()) {
            return;
        }

        if ((int) id < 0) {
            db.insertGrievance(grievance, solution, ProjectType, CompanyName, Status);
        } else {
            db.updateGrievance(id, grievance, solution, ProjectType, CompanyName);
        }

        setResult(RESULT_OK);

        finish();
    }

    private boolean validateGrievance() {
        if (_Grievance.getText().toString().trim().isEmpty()) {
            inputLayoutGrievance.setError(getString(R.string.err_msg_grievance));
            requestFocus(_Grievance);
            return false;
        } else {
            inputLayoutGrievance.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSolution() {
        if (_Solution.getText().toString().trim().isEmpty()) {
            inputLayoutSolution.setError(getString(R.string.err_msg_solution));
            requestFocus(_Solution);
            return false;
        } else {
            inputLayoutSolution.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}

