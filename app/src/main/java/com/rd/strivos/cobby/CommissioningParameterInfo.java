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
 * Created by COBURG DESIGN on 19-02-2016.
 */
public class CommissioningParameterInfo extends AppCompatActivity {

    private EditText _Environment, _AmbientTemperatureInDegreeCelsius, _DuctingInstalled, _RoomVentilation, _FlexibleConnection,
            _InsolationValue, _IncomingVoltage, _SupplyCopperCableRecommendedInMm, _FuseInstalledInAmps, _FullLoadCurrent, _UnloadCurrent,
            _SettingOverloadProtectionOfMainMotorInAmps, _SettingOverloadProtectionOfFanMotorInAmps, _AirendTemperatureInDegreeCelcius, _PressurSetting,
            _SumpPressureAtUnloadCondition, _DryerPipelineBypass, _FilterPipelineBypass, _VerticalAirReceiverGrouting;
    private TextInputLayout inputLayoutEnvironment, inputLayoutAmbientTemperatureInDegreeCelsius, inputLayoutDuctingInstalled, inputLayoutRoomVentilation,
            inputLayoutFlexibleConnection, inputLayoutInsolationValue, inputLayoutIncomingVoltage, inputLayoutSupplyCopperCableRecommendedInMm,
            inputLayoutFuseInstalledInAmps, inputLayoutFullLoadCurrent, inputLayoutUnloadCurrent, inputLayoutSettingOverloadProtectionOfMainMotorInAmps,
            inputLayoutSettingOverloadProtectionOfFanMotorInAmps, inputLayoutAirendTemperatureInDegreeCelcius, inputLayoutPressurSetting,
            inputLayoutSumpPressureAtUnloadCondition, inputLayoutDryerPipelineBypass, inputLayoutFilterPipelineBypass, inputLayoutVerticalAirReceiverGrouting;
    private Button _Submit, _Clear;
    private String companyName, projectType;
    long id;
    private CommissioningDBHelper commissioningDBHelper = new CommissioningDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commissioning_parameter_info);
        FindViewById();
        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");


        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCommissioningParameterInDB();
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

    private void insertCommissioningParameterInDB() {

        String Environment = _Environment.getText().toString().trim();
        String AmbientTemperatureInDegreeCelsius = _AmbientTemperatureInDegreeCelsius.getText().toString().trim();
        String DuctingInstalled = _DuctingInstalled.getText().toString().trim();
        String RoomVentilation = _RoomVentilation.getText().toString().trim();
        String FlexibleConnection = _FlexibleConnection.getText().toString().trim();
        String InsolationValue = _InsolationValue.getText().toString().trim();
        String IncomingVoltage = _IncomingVoltage.getText().toString().trim();
        String SupplyCopperCableRecommendedInMm = _SupplyCopperCableRecommendedInMm.getText().toString().trim();
        String FuseInstalledInAmps = _FuseInstalledInAmps.getText().toString().trim();
        String FullLoadCurrent = _FullLoadCurrent.getText().toString().trim();
        String UnloadCurrent = _UnloadCurrent.getText().toString().trim();
        String SettingOverloadProtectionOfMainMotorInAmps = _SettingOverloadProtectionOfMainMotorInAmps.getText().toString().trim();
        String SettingOverloadProtectionOfFanMotorInAmps = _SettingOverloadProtectionOfFanMotorInAmps.getText().toString().trim();
        String AirendTemperatureInDegreeCelcius = _AirendTemperatureInDegreeCelcius.getText().toString().trim();
        String PressurSetting = _PressurSetting.getText().toString().trim();
        String SumpPressureAtUnloadCondition = _SumpPressureAtUnloadCondition.getText().toString().trim();
        String DryerPipelineBypass = _DryerPipelineBypass.getText().toString().trim();
        String FilterPipelineBypass = _FilterPipelineBypass.getText().toString().trim();
        String VerticalAirReceiverGrouting = _VerticalAirReceiverGrouting.getText().toString().trim();
        String Status = "N";

        if (!validateEnvironment()) {
            return;
        }
        if (!validateAmbientTemperatureInDegreeCelsius()) {
            return;
        }
        if (!validateDuctingInstalled()) {
            return;
        }
        if (!validateRoomVentilation()) {
            return;
        }
        if (!validateFlexibleConnection()) {
            return;
        }
        if (!validateInsolationValue()) {
            return;
        }
        if (!validateIncomingVoltage()) {
            return;
        }
        if (!validateSupplyCopperCableRecommendedInMm()) {
            return;
        }
        if (!validateFuseInstalledInAmps()) {
            return;
        }
        if (!validateFullLoadCurrent()) {
            return;
        }
        if (!validateUnloadCurrent()) {
            return;
        }
        if (!validateSettingOverloadProtectionOfMainMotorInAmps()) {
            return;
        }
        if (!validateSettingOverloadProtectionOfFanMotorInAmps()) {
            return;
        }
        if (!validateAirendTemperatureInDegreeCelcius()) {
            return;
        }
        if (!validatePressurSetting()) {
            return;
        }
        if (!validateSumpPressureAtUnloadCondition()) {
            return;
        }
        if (!validateDryerPipelineBypass()) {
            return;
        }
        if (!validateFilterPipelineBypass()) {
            return;
        }
        if (!validateVerticalAirReceiverGrouting()) {
            return;
        }

        commissioningDBHelper.insertCommissioningParameterInfo(Environment,
                AmbientTemperatureInDegreeCelsius,
                DuctingInstalled,
                RoomVentilation,
                FlexibleConnection,
                InsolationValue,
                IncomingVoltage,
                SupplyCopperCableRecommendedInMm,
                FuseInstalledInAmps,
                FullLoadCurrent,
                UnloadCurrent,
                SettingOverloadProtectionOfMainMotorInAmps,
                SettingOverloadProtectionOfFanMotorInAmps,
                AirendTemperatureInDegreeCelcius,
                PressurSetting,
                SumpPressureAtUnloadCondition,
                DryerPipelineBypass,
                FilterPipelineBypass,
                VerticalAirReceiverGrouting,
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

    private boolean validateAmbientTemperatureInDegreeCelsius() {
        if (_AmbientTemperatureInDegreeCelsius.getText().toString().trim().isEmpty()) {
            inputLayoutAmbientTemperatureInDegreeCelsius.setError(getString(R.string.err_fill_field));
            requestFocus(_AmbientTemperatureInDegreeCelsius);
            return false;
        } else {
            inputLayoutAmbientTemperatureInDegreeCelsius.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDuctingInstalled() {
        if (_DuctingInstalled.getText().toString().trim().isEmpty()) {
            inputLayoutDuctingInstalled.setError(getString(R.string.err_fill_field));
            requestFocus(_DuctingInstalled);
            return false;
        } else {
            inputLayoutDuctingInstalled.setErrorEnabled(false);
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

    private boolean validateInsolationValue() {
        if (_InsolationValue.getText().toString().trim().isEmpty()) {
            inputLayoutInsolationValue.setError(getString(R.string.err_fill_field));
            requestFocus(_InsolationValue);
            return false;
        } else {
            inputLayoutInsolationValue.setErrorEnabled(false);
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

    private boolean validateFuseInstalledInAmps() {
        if (_FuseInstalledInAmps.getText().toString().trim().isEmpty()) {
            inputLayoutFuseInstalledInAmps.setError(getString(R.string.err_fill_field));
            requestFocus(_FuseInstalledInAmps);
            return false;
        } else {
            inputLayoutFuseInstalledInAmps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFullLoadCurrent() {
        if (_FullLoadCurrent.getText().toString().trim().isEmpty()) {
            inputLayoutFullLoadCurrent.setError(getString(R.string.err_fill_field));
            requestFocus(_FullLoadCurrent);
            return false;
        } else {
            inputLayoutFullLoadCurrent.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateUnloadCurrent() {
        if (_UnloadCurrent.getText().toString().trim().isEmpty()) {
            inputLayoutUnloadCurrent.setError(getString(R.string.err_fill_field));
            requestFocus(_UnloadCurrent);
            return false;
        } else {
            inputLayoutUnloadCurrent.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSettingOverloadProtectionOfMainMotorInAmps() {
        if (_SettingOverloadProtectionOfMainMotorInAmps.getText().toString().trim().isEmpty()) {
            inputLayoutSettingOverloadProtectionOfMainMotorInAmps.setError(getString(R.string.err_fill_field));
            requestFocus(_SettingOverloadProtectionOfMainMotorInAmps);
            return false;
        } else {
            inputLayoutSettingOverloadProtectionOfMainMotorInAmps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSettingOverloadProtectionOfFanMotorInAmps() {
        if (_SettingOverloadProtectionOfFanMotorInAmps.getText().toString().trim().isEmpty()) {
            inputLayoutSettingOverloadProtectionOfFanMotorInAmps.setError(getString(R.string.err_fill_field));
            requestFocus(_SettingOverloadProtectionOfFanMotorInAmps);
            return false;
        } else {
            inputLayoutSettingOverloadProtectionOfFanMotorInAmps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAirendTemperatureInDegreeCelcius() {
        if (_AirendTemperatureInDegreeCelcius.getText().toString().trim().isEmpty()) {
            inputLayoutAirendTemperatureInDegreeCelcius.setError(getString(R.string.err_fill_field));
            requestFocus(_AirendTemperatureInDegreeCelcius);
            return false;
        } else {
            inputLayoutAirendTemperatureInDegreeCelcius.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePressurSetting() {
        if (_PressurSetting.getText().toString().trim().isEmpty()) {
            inputLayoutPressurSetting.setError(getString(R.string.err_fill_field));
            requestFocus(_PressurSetting);
            return false;
        } else {
            inputLayoutPressurSetting.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSumpPressureAtUnloadCondition() {
        if (_SumpPressureAtUnloadCondition.getText().toString().trim().isEmpty()) {
            inputLayoutSumpPressureAtUnloadCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_SumpPressureAtUnloadCondition);
            return false;
        } else {
            inputLayoutSumpPressureAtUnloadCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDryerPipelineBypass() {
        if (_DryerPipelineBypass.getText().toString().trim().isEmpty()) {
            inputLayoutDryerPipelineBypass.setError(getString(R.string.err_fill_field));
            requestFocus(_DryerPipelineBypass);
            return false;
        } else {
            inputLayoutDryerPipelineBypass.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFilterPipelineBypass() {
        if (_FilterPipelineBypass.getText().toString().trim().isEmpty()) {
            inputLayoutFilterPipelineBypass.setError(getString(R.string.err_fill_field));
            requestFocus(_FilterPipelineBypass);
            return false;
        } else {
            inputLayoutFilterPipelineBypass.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateVerticalAirReceiverGrouting() {
        if (_VerticalAirReceiverGrouting.getText().toString().trim().isEmpty()) {
            inputLayoutVerticalAirReceiverGrouting.setError(getString(R.string.err_fill_field));
            requestFocus(_VerticalAirReceiverGrouting);
            return false;
        } else {
            inputLayoutVerticalAirReceiverGrouting.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void FindViewById() {
        inputLayoutEnvironment = (TextInputLayout) findViewById(R.id.input_layout_environment);
        inputLayoutAmbientTemperatureInDegreeCelsius = (TextInputLayout) findViewById(R.id.input_layout_ambient_temperature_in_degree_celsius);
        inputLayoutDuctingInstalled = (TextInputLayout) findViewById(R.id.input_layout_ducting_installed);
        inputLayoutRoomVentilation = (TextInputLayout) findViewById(R.id.input_layout_room_ventilation);
        inputLayoutFlexibleConnection = (TextInputLayout) findViewById(R.id.input_layout_flexible_connection);
        inputLayoutInsolationValue = (TextInputLayout) findViewById(R.id.input_layout_insolation_value);
        inputLayoutIncomingVoltage = (TextInputLayout) findViewById(R.id.input_layout_incoming_voltage);
        inputLayoutSupplyCopperCableRecommendedInMm = (TextInputLayout) findViewById(R.id.input_layout_supply_copper_cable_recommended_in_mm);
        inputLayoutFuseInstalledInAmps = (TextInputLayout) findViewById(R.id.input_layout_fuse_installed_in_amps);
        inputLayoutFullLoadCurrent = (TextInputLayout) findViewById(R.id.input_layout_full_load_current);
        inputLayoutUnloadCurrent = (TextInputLayout) findViewById(R.id.input_layout_unload_current);
        inputLayoutSettingOverloadProtectionOfMainMotorInAmps = (TextInputLayout) findViewById(R.id.input_layout_setting_overload_protection_of_main_motor_in_amps);
        inputLayoutSettingOverloadProtectionOfFanMotorInAmps = (TextInputLayout) findViewById(R.id.input_layout_setting_overload_protection_of_fan_motor_in_amps);
        inputLayoutAirendTemperatureInDegreeCelcius = (TextInputLayout) findViewById(R.id.input_layout_airend_temperature_in_degree_celcius);
        inputLayoutPressurSetting = (TextInputLayout) findViewById(R.id.input_layout_pressur_setting);
        inputLayoutSumpPressureAtUnloadCondition = (TextInputLayout) findViewById(R.id.input_layout_sump_pressure_at_unload_condition);
        inputLayoutDryerPipelineBypass = (TextInputLayout) findViewById(R.id.input_layout_dryer_pipeline_bypass);
        inputLayoutFilterPipelineBypass = (TextInputLayout) findViewById(R.id.input_layout_filter_pipeline_bypass);
        inputLayoutVerticalAirReceiverGrouting = (TextInputLayout) findViewById(R.id.input_layout_vertical_air_receiver_grouting);

        _Environment = (EditText) findViewById(R.id.et_environment);
        _AmbientTemperatureInDegreeCelsius = (EditText) findViewById(R.id.et_ambient_temperature_in_degree_celsius);
        _DuctingInstalled = (EditText) findViewById(R.id.et_ducting_installed);
        _RoomVentilation = (EditText) findViewById(R.id.et_room_ventilation);
        _FlexibleConnection = (EditText) findViewById(R.id.et_flexible_connection);
        _InsolationValue = (EditText) findViewById(R.id.et_insolation_value);
        _IncomingVoltage = (EditText) findViewById(R.id.et_incoming_voltage);
        _SupplyCopperCableRecommendedInMm = (EditText) findViewById(R.id.et_supply_copper_cable_recommended_in_mm);
        _FuseInstalledInAmps = (EditText) findViewById(R.id.et_fuse_installed_in_amps);
        _FullLoadCurrent = (EditText) findViewById(R.id.et_full_load_current);
        _UnloadCurrent = (EditText) findViewById(R.id.et_unload_current);
        _SettingOverloadProtectionOfMainMotorInAmps = (EditText) findViewById(R.id.et_setting_overload_protection_of_main_motor_in_amps);
        _SettingOverloadProtectionOfFanMotorInAmps = (EditText) findViewById(R.id.et_setting_overload_protection_of_fan_motor_in_amps);
        _AirendTemperatureInDegreeCelcius = (EditText) findViewById(R.id.et_airend_temperature_in_degree_celcius);
        _PressurSetting = (EditText) findViewById(R.id.et_pressur_setting);
        _SumpPressureAtUnloadCondition = (EditText) findViewById(R.id.et_sump_pressure_at_unload_condition);
        _DryerPipelineBypass = (EditText) findViewById(R.id.et_dryer_pipeline_bypass);
        _FilterPipelineBypass = (EditText) findViewById(R.id.et_filter_pipeline_bypass);
        _VerticalAirReceiverGrouting = (EditText) findViewById(R.id.et_vertical_air_receiver_grouting);

        _Submit = (Button) findViewById(R.id.add);
        _Clear = (Button) findViewById(R.id.remove);
    }

    private void clear() {
        _Environment.setText("");
        _AmbientTemperatureInDegreeCelsius.setText("");
        _DuctingInstalled.setText("");
        _RoomVentilation.setText("");
        _FlexibleConnection.setText("");
        _InsolationValue.setText("");
        _IncomingVoltage.setText("");
        _SupplyCopperCableRecommendedInMm.setText("");
        _FuseInstalledInAmps.setText("");
        _FullLoadCurrent.setText("");
        _UnloadCurrent.setText("");
        _SettingOverloadProtectionOfMainMotorInAmps.setText("");
        _SettingOverloadProtectionOfFanMotorInAmps.setText("");
        _AirendTemperatureInDegreeCelcius.setText("");
        _PressurSetting.setText("");
        _SumpPressureAtUnloadCondition.setText("");
        _DryerPipelineBypass.setText("");
        _FilterPipelineBypass.setText("");
        _VerticalAirReceiverGrouting.setText("");
    }
}
