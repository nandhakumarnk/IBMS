package com.rd.strivos.cobby;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by COBURG DESIGN on 09-02-2016.
 */
public class OtherParameterInfo extends AppCompatActivity {
    private EditText _DryerCurrentInVoltageR, _DryerCurrentInVoltageY, _DryerCurrentInVoltageB, _DryerCurrentInLoadConditionAmpsR,
            _DryerCurrentInLoadConditionAmpsY, _DryerCurrentInLoadConditionAmpsB, _DryerDewPointIndication, _FilterCatridge,
            _FilterCatridgeIndicationLevel, _FilterCatridgeChangeDate;

    private TextInputLayout inputLayoutDryerCurrentInVoltageR, inputLayoutDryerCurrentInVoltageY, inputLayoutDryerCurrentInVoltageB,
            inputLayoutDryerCurrentInLoadConditionAmpsR, inputLayoutDryerCurrentInLoadConditionAmpsY, inputLayoutDryerCurrentInLoadConditionAmpsB,
            inputLayoutDryerDewPointIndication, inputLayoutFilterCatridge, inputLayoutFilterCatridgeIndicationLevel,
            inputLayoutFilterCatridgeChangeDate;
    Button _Submit, _Clear;

    String companyName, projectType;
    long id;
    private OtherParameterInfoDBHelper otherParameterInfoDBHelper = new OtherParameterInfoDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_parameter_info_add_page);
        FindViewById();

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        id = getIntent().getExtras().getLong("id");

        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertOtherParameterInDB();
            }
        });
        _Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                Toast.makeText(getApplicationContext(), "Data has been cleared!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertOtherParameterInDB() {

        String DryerCurrentInVoltageR = _DryerCurrentInVoltageR.getText().toString().trim();
        String DryerCurrentInVoltageY = _DryerCurrentInVoltageY.getText().toString().trim();
        String DryerCurrentInVoltageB = _DryerCurrentInVoltageB.getText().toString().trim();
        String DryerCurrentInLoadConditionAmpsR = _DryerCurrentInLoadConditionAmpsR.getText().toString().trim();
        String DryerCurrentInLoadConditionAmpsY = _DryerCurrentInLoadConditionAmpsY.getText().toString().trim();
        String DryerCurrentInLoadConditionAmpsB = _DryerCurrentInLoadConditionAmpsB.getText().toString().trim();
        String DryerDewPointIndication = _DryerDewPointIndication.getText().toString().trim();
        String FilterCatridge = _FilterCatridge.getText().toString().trim();
        String FilterCatridgeIndicationLevel = _FilterCatridgeIndicationLevel.getText().toString().trim();
        String FilterCatridgeChangeDate = _FilterCatridgeChangeDate.getText().toString().trim();
        String Status = "N";

        if (!validateDryerCurrentInVoltageR()) {
            return;
        }
        if (!validateDryerCurrentInVoltageY()) {
            return;
        }
        if (!validateDryerCurrentInVoltageB()) {
            return;
        }
        if (!validateDryerCurrentInLoadConditionAmpsR()) {
            return;
        }
        if (!validateDryerCurrentInLoadConditionAmpsY()) {
            return;
        }
        if (!validateDryerCurrentInLoadConditionAmpsB()) {
            return;
        }
        if (!validateDryerDewPointIndication()) {
            return;
        }
        if (!validateFilterCatridge()) {
            return;
        }
        if (!validateFilterCatridgeIndicationLevel()) {
            return;
        }
        if (!validateFilterCatridgeChangeDate()) {
            return;
        }

        otherParameterInfoDBHelper.insertOtherParameter(DryerCurrentInVoltageR,
                DryerCurrentInVoltageY,
                DryerCurrentInVoltageB,
                DryerCurrentInLoadConditionAmpsR,
                DryerCurrentInLoadConditionAmpsY,
                DryerCurrentInLoadConditionAmpsB,
                DryerDewPointIndication,
                FilterCatridge,
                FilterCatridgeIndicationLevel,
                FilterCatridgeChangeDate, projectType, companyName, Status);

        Toast.makeText(getApplicationContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();

        finish();
    }

    private boolean validateDryerCurrentInVoltageR() {
        if (_DryerCurrentInVoltageR.getText().toString().trim().isEmpty()) {
            inputLayoutDryerCurrentInVoltageR.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerCurrentInVoltageR);
            return false;
        } else {
            inputLayoutDryerCurrentInVoltageR.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerCurrentInVoltageY() {
        if (_DryerCurrentInVoltageY.getText().toString().trim().isEmpty()) {
            inputLayoutDryerCurrentInVoltageY.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerCurrentInVoltageY);
            return false;
        } else {
            inputLayoutDryerCurrentInVoltageY.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerCurrentInVoltageB() {
        if (_DryerCurrentInVoltageB
                .getText().toString().trim().isEmpty()) {
            inputLayoutDryerCurrentInVoltageB.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerCurrentInVoltageB);
            return false;
        } else {
            inputLayoutDryerCurrentInVoltageB.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerCurrentInLoadConditionAmpsR() {
        if (_DryerCurrentInLoadConditionAmpsR.getText().toString().trim().isEmpty()) {
            inputLayoutDryerCurrentInLoadConditionAmpsR.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerCurrentInLoadConditionAmpsR);
            return false;
        } else {
            inputLayoutDryerCurrentInLoadConditionAmpsR.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerCurrentInLoadConditionAmpsY() {
        if (_DryerCurrentInLoadConditionAmpsY.getText().toString().trim().isEmpty()) {
            inputLayoutDryerCurrentInLoadConditionAmpsY.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerCurrentInLoadConditionAmpsY);
            return false;
        } else {
            inputLayoutDryerCurrentInLoadConditionAmpsY.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerCurrentInLoadConditionAmpsB() {
        if (_DryerCurrentInLoadConditionAmpsB.getText().toString().trim().isEmpty()) {
            inputLayoutDryerCurrentInLoadConditionAmpsB.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerCurrentInLoadConditionAmpsB);
            return false;
        } else {
            inputLayoutDryerCurrentInLoadConditionAmpsB.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerDewPointIndication() {
        if (_DryerDewPointIndication.getText().toString().trim().isEmpty()) {
            inputLayoutDryerDewPointIndication.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerDewPointIndication);
            return false;
        } else {
            inputLayoutDryerDewPointIndication.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFilterCatridge() {
        if (_FilterCatridge.getText().toString().trim().isEmpty()) {
            inputLayoutFilterCatridge.setError(getString(R.string.err_fill_field));
            requestFocus(_FilterCatridge);
            return false;
        } else {
            inputLayoutFilterCatridge.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFilterCatridgeIndicationLevel() {
        if (_FilterCatridgeIndicationLevel.getText().toString().trim().isEmpty()) {
            inputLayoutFilterCatridgeIndicationLevel.setError(getString(R.string.err_fill_field));
            requestFocus(_FilterCatridgeIndicationLevel);
            return false;
        } else {
            inputLayoutFilterCatridgeIndicationLevel.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFilterCatridgeChangeDate() {
        if (_FilterCatridgeChangeDate.getText().toString().trim().isEmpty()) {
            inputLayoutFilterCatridgeChangeDate.setError(getString(R.string.err_fill_field));
            requestFocus(_FilterCatridgeChangeDate);
            return false;
        } else {
            inputLayoutFilterCatridgeChangeDate.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void clear() {
        _DryerCurrentInVoltageR.setText("");
        _DryerCurrentInVoltageY.setText("");
        _DryerCurrentInVoltageB.setText("");
        _DryerCurrentInLoadConditionAmpsR.setText("");
        _DryerCurrentInLoadConditionAmpsY.setText("");
        _DryerCurrentInLoadConditionAmpsB.setText("");
        _DryerDewPointIndication.setText("");
        _FilterCatridge.setText("");
        _FilterCatridgeIndicationLevel.setText("");
        _FilterCatridgeChangeDate.setText("");
    }

    private void FindViewById() {
        inputLayoutDryerCurrentInVoltageR = (TextInputLayout) findViewById(R.id.input_layout_dryer_current_in_voltage_r);
        inputLayoutDryerCurrentInVoltageY = (TextInputLayout) findViewById(R.id.input_layout_dryer_current_in_voltage_y);
        inputLayoutDryerCurrentInVoltageB = (TextInputLayout) findViewById(R.id.input_layout_dryer_current_in_voltage_b);
        inputLayoutDryerCurrentInLoadConditionAmpsR = (TextInputLayout) findViewById(R.id.input_layout_dryer_current_in_load_condition_amps_r);
        inputLayoutDryerCurrentInLoadConditionAmpsY = (TextInputLayout) findViewById(R.id.input_layout_dryer_current_in_load_condition_amps_y);
        inputLayoutDryerCurrentInLoadConditionAmpsB = (TextInputLayout) findViewById(R.id.input_layout_dryer_current_in_load_condition_amps_b);
        inputLayoutDryerDewPointIndication = (TextInputLayout) findViewById(R.id.input_layout_dryer_dew_point_indication);
        inputLayoutFilterCatridge = (TextInputLayout) findViewById(R.id.input_layout_filter_catridge);
        inputLayoutFilterCatridgeIndicationLevel = (TextInputLayout) findViewById(R.id.input_layout_filter_catridge_indication_level);
        inputLayoutFilterCatridgeChangeDate = (TextInputLayout) findViewById(R.id.input_layout_filter_catridge_change_date);

        _DryerCurrentInVoltageR = (EditText) findViewById(R.id.et_dryer_current_in_voltage_r);
        _DryerCurrentInVoltageY = (EditText) findViewById(R.id.et_dryer_current_in_voltage_y);
        _DryerCurrentInVoltageB = (EditText) findViewById(R.id.et_dryer_current_in_voltage_b);
        _DryerCurrentInLoadConditionAmpsR = (EditText) findViewById(R.id.et_dryer_current_in_load_condition_amps_r);
        _DryerCurrentInLoadConditionAmpsY = (EditText) findViewById(R.id.et_dryer_current_in_load_condition_amps_y);
        _DryerCurrentInLoadConditionAmpsB = (EditText) findViewById(R.id.et_dryer_current_in_load_condition_amps_b);
        _DryerDewPointIndication = (EditText) findViewById(R.id.et_dryer_dew_point_indication);
        _FilterCatridge = (EditText) findViewById(R.id.et_filter_catridge);
        _FilterCatridgeIndicationLevel = (EditText) findViewById(R.id.et_filter_catridge_indication_level);
        _FilterCatridgeChangeDate = (EditText) findViewById(R.id.et_filter_catridge_change_date);

        _Submit = (Button) findViewById(R.id.add_service);
        _Clear = (Button) findViewById(R.id.remove_service);

    }
}
