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
 * Created by COBURG DESIGN on 11-02-2016.
 */
public class PreCommissioningParameterInfo extends AppCompatActivity {

    private EditText _Environment, _RoomVentilation, _DuctingRecommended, _FlexibleConnection, _InsolationValve, _IncomingVoltage,
            _SupplyCopperCableRecommendedInMm, _FuseRecommendedInAmps, _DryerPipelineBypassRecommended, _FilterPipelineBypassRecommended,
            _VerticalAirReceiverGroutingRecommended;
    private TextInputLayout inputLayoutEnvironment, inputLayoutRoomVentilation, inputLayoutDuctingRecommended, inputLayoutFlexibleConnection,
            inputLayoutInsolationValve, inputLayoutIncomingVoltage, inputLayoutSupplyCopperCableRecommendedInMm, inputLayoutFuseRecommendedInAmps,
            inputLayoutDryerPipelineBypassRecommended, inputLayoutFilterPipelineBypassRecommended,
            inputLayoutVerticalAirReceiverGroutingRecommended;
    Button _Submit, _Clear;

    String companyName, projectType;
    long id;
    private PreCommissioningParameterInfoDBHelper preCommissioningParameterInfoDBHelper = new PreCommissioningParameterInfoDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_commissioning_parameter_info);
        FindViewById();

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        id = getIntent().getExtras().getLong("id");

        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPreCommissioningParameterInDB();
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

    private void insertPreCommissioningParameterInDB() {
        String Environment = _Environment.getText().toString().trim();
        String RoomVentilation = _RoomVentilation.getText().toString().trim();
        String DuctingRecommended = _DuctingRecommended.getText().toString().trim();
        String FlexibleConnection = _FlexibleConnection.getText().toString().trim();
        String InsolationValve = _InsolationValve.getText().toString().trim();
        String IncomingVoltage = _IncomingVoltage.getText().toString().trim();
        String SupplyCopperCableRecommendedInMm = _SupplyCopperCableRecommendedInMm.getText().toString().trim();
        String FuseRecommendedInAmps = _FuseRecommendedInAmps.getText().toString().trim();
        String DryerPipelineBypassRecommended = _DryerPipelineBypassRecommended.getText().toString().trim();
        String FilterPipelineBypassRecommended = _FilterPipelineBypassRecommended.getText().toString().trim();
        String VerticalAirReceiverGroutingRecommended = _VerticalAirReceiverGroutingRecommended.getText().toString().trim();
        String Status = "N";

        if (!validateEnvironment()) {
            return;
        }
        if (!validateRoomVentilation()) {
            return;
        }
        if (!validateDuctingRecommended()) {
            return;
        }
        if (!validateFlexibleConnection()) {
            return;
        }
        if (!validateInsolationValve()) {
            return;
        }
        if (!validateIncomingVoltage()) {
            return;
        }
        if (!validateSupplyCopperCableRecommendedInMm()) {
            return;
        }
        if (!validateFuseRecommendedInAmps()) {
            return;
        }
        if (!validateDryerPipelineBypassRecommended()) {
            return;
        }
        if (!validateFilterPipelineBypassRecommended()) {
            return;
        }
        if (!validateVerticalAirReceiverGroutingRecommended()) {
            return;
        }

        preCommissioningParameterInfoDBHelper.insertPreCommissioningParameterInfo(Environment,
                RoomVentilation,
                DuctingRecommended,
                FlexibleConnection,
                InsolationValve,
                IncomingVoltage,
                SupplyCopperCableRecommendedInMm,
                FuseRecommendedInAmps,
                DryerPipelineBypassRecommended,
                FilterPipelineBypassRecommended,
                VerticalAirReceiverGroutingRecommended,
                projectType, companyName, Status);

        Toast.makeText(getApplicationContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();

        finish();
    }

    private boolean validateEnvironment() {
        if (_Environment.getText().toString().trim().isEmpty()) {
            inputLayoutEnvironment.setError(getString(R.string.err_fill_field));
            requestFocus(_Environment);
            return false;
        } else {
            inputLayoutEnvironment.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRoomVentilation() {
        if (_RoomVentilation.getText().toString().trim().isEmpty()) {
            inputLayoutRoomVentilation.setError(getString(R.string.err_fill_field));
            requestFocus(_RoomVentilation);
            return false;
        } else {
            inputLayoutRoomVentilation.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDuctingRecommended() {
        if (_DuctingRecommended.getText().toString().trim().isEmpty()) {
            inputLayoutDuctingRecommended.setError(getString(R.string.err_fill_field));
            requestFocus(_DuctingRecommended);
            return false;
        } else {
            inputLayoutDuctingRecommended.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFlexibleConnection() {
        if (_FlexibleConnection.getText().toString().trim().isEmpty()) {
            inputLayoutFlexibleConnection.setError(getString(R.string.err_fill_field));
            requestFocus(_FlexibleConnection);
            return false;
        } else {
            inputLayoutFlexibleConnection.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateInsolationValve() {
        if (_InsolationValve.getText().toString().trim().isEmpty()) {
            inputLayoutInsolationValve.setError(getString(R.string.err_fill_field));
            requestFocus(_InsolationValve);
            return false;
        } else {
            inputLayoutInsolationValve.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateIncomingVoltage() {
        if (_IncomingVoltage.getText().toString().trim().isEmpty()) {
            inputLayoutIncomingVoltage.setError(getString(R.string.err_fill_field));
            requestFocus(_IncomingVoltage);
            return false;
        } else {
            inputLayoutIncomingVoltage.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSupplyCopperCableRecommendedInMm() {
        if (_SupplyCopperCableRecommendedInMm.getText().toString().trim().isEmpty()) {
            inputLayoutSupplyCopperCableRecommendedInMm.setError(getString(R.string.err_fill_field));
            requestFocus(_SupplyCopperCableRecommendedInMm);
            return false;
        } else {
            inputLayoutSupplyCopperCableRecommendedInMm.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFuseRecommendedInAmps() {
        if (_FuseRecommendedInAmps.getText().toString().trim().isEmpty()) {
            inputLayoutFuseRecommendedInAmps.setError(getString(R.string.err_fill_field));
            requestFocus(_FuseRecommendedInAmps);
            return false;
        } else {
            inputLayoutFuseRecommendedInAmps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerPipelineBypassRecommended() {
        if (_DryerPipelineBypassRecommended.getText().toString().trim().isEmpty()) {
            inputLayoutDryerPipelineBypassRecommended.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerPipelineBypassRecommended);
            return false;
        } else {
            inputLayoutDryerPipelineBypassRecommended.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFilterPipelineBypassRecommended() {
        if (_FilterPipelineBypassRecommended.getText().toString().trim().isEmpty()) {
            inputLayoutFilterPipelineBypassRecommended.setError(getString(R.string.err_fill_field));
            requestFocus(_FilterPipelineBypassRecommended);
            return false;
        } else {
            inputLayoutFilterPipelineBypassRecommended.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateVerticalAirReceiverGroutingRecommended() {
        if (_VerticalAirReceiverGroutingRecommended.getText().toString().trim().isEmpty()) {
            inputLayoutVerticalAirReceiverGroutingRecommended.setError(getString(R.string.err_fill_field));
            requestFocus(_VerticalAirReceiverGroutingRecommended);
            return false;
        } else {
            inputLayoutVerticalAirReceiverGroutingRecommended.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void clear() {
        _Environment.setText("");
        _RoomVentilation.setText("");
        _DuctingRecommended.setText("");
        _FlexibleConnection.setText("");
        _InsolationValve.setText("");
        _IncomingVoltage.setText("");
        _SupplyCopperCableRecommendedInMm.setText("");
        _FuseRecommendedInAmps.setText("");
        _DryerPipelineBypassRecommended.setText("");
        _FilterPipelineBypassRecommended.setText("");
        _VerticalAirReceiverGroutingRecommended.setText("");
    }

    private void FindViewById() {
        inputLayoutEnvironment = (TextInputLayout) findViewById(R.id.input_layout_environment);
        inputLayoutRoomVentilation = (TextInputLayout) findViewById(R.id.input_layout_room_ventilation);
        inputLayoutDuctingRecommended = (TextInputLayout) findViewById(R.id.input_layout_ducting_recommended);
        inputLayoutFlexibleConnection = (TextInputLayout) findViewById(R.id.input_layout_flexible_connection);
        inputLayoutInsolationValve = (TextInputLayout) findViewById(R.id.input_layout_insolation_valve);
        inputLayoutIncomingVoltage = (TextInputLayout) findViewById(R.id.input_layout_incoming_voltage);
        inputLayoutSupplyCopperCableRecommendedInMm = (TextInputLayout) findViewById(R.id.input_layout_supply_copper_cable_recommended_in_mm);
        inputLayoutFuseRecommendedInAmps = (TextInputLayout) findViewById(R.id.input_layout_fuse_recommended_in_amps);
        inputLayoutDryerPipelineBypassRecommended = (TextInputLayout) findViewById(R.id.input_layout_dryer_pipeline_bypass_recommended);
        inputLayoutFilterPipelineBypassRecommended = (TextInputLayout) findViewById(R.id.input_layout_filter_pipeline_bypass_recommended);
        inputLayoutVerticalAirReceiverGroutingRecommended = (TextInputLayout) findViewById(R.id.input_layout_vertical_air_receiver_grouting_recommended);

        _Environment = (EditText) findViewById(R.id.et_environment);
        _RoomVentilation = (EditText) findViewById(R.id.et_room_ventilation);
        _DuctingRecommended = (EditText) findViewById(R.id.et_ducting_recommended);
        _FlexibleConnection = (EditText) findViewById(R.id.et_flexible_connection);
        _InsolationValve = (EditText) findViewById(R.id.et_insolation_valve);
        _IncomingVoltage = (EditText) findViewById(R.id.et_incoming_voltage);
        _SupplyCopperCableRecommendedInMm = (EditText) findViewById(R.id.et_supply_copper_cable_recommended_in_mm);
        _FuseRecommendedInAmps = (EditText) findViewById(R.id.et_fuse_recommended_in_amps);
        _DryerPipelineBypassRecommended = (EditText) findViewById(R.id.et_dryer_pipeline_bypass_recommended);
        _FilterPipelineBypassRecommended = (EditText) findViewById(R.id.et_filter_pipeline_bypass_recommended);
        _VerticalAirReceiverGroutingRecommended = (EditText) findViewById(R.id.et_vertical_air_receiver_grouting_recommended);

        _Submit = (Button) findViewById(R.id.add);
        _Clear = (Button) findViewById(R.id.remove);
    }
}
