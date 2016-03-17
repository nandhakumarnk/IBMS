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
 * Created by COBURG DESIGN on 25-01-2016.
 */
public class ParameterInfo extends AppCompatActivity {

    EditText _ControlType, _PressureSetting, _AmbientTemperature, _IncomingVoltage, _DuctingFacility,
            _RunningHoursPerDay, _RunningDaysPerWeek, _IsCompressorCleanedEveryday, _TotalRunHours,
            _TotalLoadHours, _AirendDischargeTemperature, _CurrentInLoadConditionAmps,
            _CurrentInIdleConditionAmps, _NoOfMotorStarts, _NoOfLoadValveOn, _OilLevel,
            _CoolerCondition, _MotorBearingNoise, _AirendBearingNoise, _InletValveWorkingCondition,
            _CombinationValveWorkingCondition, _CavValveWorkingCondition, _MpcValveWorkingCondition,
            _CouplingCondition, _BeltCondition, _AirFilter, _OilFilter, _OilSeparator, _Oil, _FilterMat,
            _BearingReplacement, _MotorGreasing, _MotorGreasingInHours, _SumpPressureAtUnloadCondition;
    private TextInputLayout inputLayoutControlType, inputLayoutPressureSetting, inputLayoutAmbientTemperature,
            inputLayoutIncomingVoltage, inputLayoutDuctingFacility, inputLayoutRunningHoursPerDay, inputLayoutRunningDaysPerWeek,
            inputLayoutIsCompressorCleanedEveryday, inputLayoutTotalRunHours, inputLayoutTotalLoadHours,
            inputLayoutAirendDischargeTemperature, inputLayoutCurrentInLoadConditionAmps, inputLayoutCurrentInIdleConditionAmps,
            inputLayoutNoOfMotorStarts, inputLayoutNoOfLoadValveOn, inputLayoutOilLevel, inputLayoutCoolerCondition,
            inputLayoutMotorBearingNoise, inputLayoutAirendBearingNoise, inputLayoutInletValveWorkingCondition,
            inputLayoutCombinationValveWorkingCondition, inputLayoutCavValveWorkingCondition,
            inputLayoutMpcValveWorkingCondition, inputLayoutCouplingCondition, inputLayoutBeltCondition,
            inputLayoutAirFilter, inputLayoutOilFilter, inputLayoutOilSeparator, inputLayoutOil, inputLayoutFilterMat,
            inputLayoutBearingReplacement, inputLayoutMotorGreasing, inputLayoutMotorGreasingInHours, inputLayoutSumpPressureAtUnloadCondition;
    Button _Submit, _Clear;

    String companyName, projectType;
    long id;
    private ParameterDBHelper parameterDBHelper = new ParameterDBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_info_add_page);
        FindViewById();

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        id = getIntent().getExtras().getLong("id");

        _Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertParameterInDB();
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

    private void insertParameterInDB() {
        String ControlType = _ControlType.getText().toString().trim();
        String PressureSetting = _PressureSetting.getText().toString().trim();
        String AmbientTemp = _AmbientTemperature.getText().toString().trim();
        String IncomeVoltage = _IncomingVoltage.getText().toString().trim();
        String DuctingFacility = _DuctingFacility.getText().toString().trim();
        String RunningHours = _RunningHoursPerDay.getText().toString().trim();
        String RunningDays = _RunningDaysPerWeek.getText().toString().trim();
        String IsCompressorCleaned = _IsCompressorCleanedEveryday.getText().toString().trim();
        String TotalRunHours = _TotalRunHours.getText().toString().trim();
        String TotalLoadHours = _TotalLoadHours.getText().toString().trim();
        String Airend = _AirendDischargeTemperature.getText().toString().trim();
        String CurrentInLoad = _CurrentInLoadConditionAmps.getText().toString().trim();
        String CurrentInIdle = _CurrentInIdleConditionAmps.getText().toString().trim();
        String NoOfMotors = _NoOfMotorStarts.getText().toString().trim();
        String NoOfLoad = _NoOfLoadValveOn.getText().toString().trim();
        String OilLevel = _OilLevel.getText().toString().trim();
        String CoolerCondition = _CoolerCondition.getText().toString().trim();
        String MotorBearingNoise = _MotorBearingNoise.getText().toString().trim();
        String AirendBearingNoise = _AirendBearingNoise.getText().toString().trim();
        String InletValue = _InletValveWorkingCondition.getText().toString().trim();
        String CombinationValue = _CombinationValveWorkingCondition.getText().toString().trim();
        String CAVValue = _CavValveWorkingCondition.getText().toString().trim();
        String MPCValue = _MpcValveWorkingCondition.getText().toString().trim();
        String CouplingCondition = _CouplingCondition.getText().toString().trim();
        String BeltCondition = _BeltCondition.getText().toString().trim();
        String AirFilter = _AirFilter.getText().toString().trim();
        String OilFilter = _OilFilter.getText().toString().trim();
        String OilSeparator = _OilSeparator.getText().toString().trim();
        String Oil = _Oil.getText().toString().trim();
        String FilterMat = _FilterMat.getText().toString().trim();
        String BearingReplacement = _BearingReplacement.getText().toString().trim();
        String MotorGreasing = _MotorGreasing.getText().toString().trim();
        String MotorGreasingInHours = _MotorGreasingInHours.getText().toString().trim();
        String SumpPressure = _SumpPressureAtUnloadCondition.getText().toString().trim();
        String Status = "N";

        if (!validateControlType()) {
            return;
        }
        if (!validatePressureSetting()) {
            return;
        }
        if (!validateAmbientTemperature()) {
            return;
        }
        if (!validateIncomingVoltage()) {
            return;
        }
        if (!validateDuctingFacility()) {
            return;
        }
        if (!validateRunningHoursPerDay()) {
            return;
        }
        if (!validateRunningDaysPerWeek()) {
            return;
        }
        if (!validateIsCompressorCleanedEveryday()) {
            return;
        }
        if (!validateTotalRunHours()) {
            return;
        }
        if (!validateTotalLoadHours()) {
            return;
        }
        if (!validateAirendDischargeTemperature()) {
            return;
        }
        if (!validateCurrentInLoadConditionAmps()) {
            return;
        }
        if (!validateCurrentInIdleConditionAmps()) {
            return;
        }
        if (!validateNoOfMotorStarts()) {
            return;
        }
        if (!validateNoOfLoadValveOn()) {
            return;
        }
        if (!validateOilLevel()) {
            return;
        }
        if (!validateCoolerCondition()) {
            return;
        }
        if (!validateMotorBearingNoise()) {
            return;
        }
        if (!validateAirendBearingNoise()) {
            return;
        }
        if (!validateInletValveWorkingCondition()) {
            return;
        }
        if (!validateCombinationValveWorkingCondition()) {
            return;
        }
        if (!validateCavValveWorkingCondition()) {
            return;
        }
        if (!validateMpcValveWorkingCondition()) {
            return;
        }
        if (!validateCouplingCondition()) {
            return;
        }
        if (!validateBeltCondition()) {
            return;
        }
        if (!validateAirFilter()) {
            return;
        }
        if (!validateOilFilter()) {
            return;
        }
        if (!validateOilSeparator()) {
            return;
        }
        if (!validateOil()) {
            return;
        }
        if (!validateFilterMat()) {
            return;
        }
        if (!validateBearingReplacement()) {
            return;
        }
        if (!validateMotorGreasing()) {
            return;
        }
        if (!validateMotorGreasingInHours()) {
            return;
        }
        if (!validateSumpPressureAtUnloadCondition()) {
            return;
        }

        parameterDBHelper.insertParameter(ControlType, PressureSetting, AmbientTemp, IncomeVoltage, DuctingFacility, RunningHours, RunningDays, IsCompressorCleaned, TotalRunHours, TotalLoadHours, Airend, CurrentInLoad, CurrentInIdle, NoOfMotors, NoOfLoad, OilLevel, CoolerCondition, MotorBearingNoise, AirendBearingNoise, InletValue, CombinationValue, CAVValue, MPCValue, CouplingCondition, BeltCondition, AirFilter, OilFilter, OilSeparator, Oil, FilterMat, BearingReplacement, MotorGreasing, MotorGreasingInHours, SumpPressure, projectType, companyName, Status);

        Toast.makeText(getApplicationContext(), "Saved Successfully!", Toast.LENGTH_SHORT).show();

        finish();

    }

    private boolean validateControlType() {
        if (_ControlType.getText().toString().trim().isEmpty()) {
            inputLayoutControlType.setError(getString(R.string.err_fill_field));
            requestFocus(_ControlType);
            return false;
        } else {
            inputLayoutControlType.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePressureSetting() {
        if (_PressureSetting.getText().toString().trim().isEmpty()) {
            inputLayoutPressureSetting.setError(getString(R.string.err_fill_field));
            requestFocus(_PressureSetting);
            return false;
        } else {
            inputLayoutPressureSetting.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAmbientTemperature() {
        if (_AmbientTemperature.getText().toString().trim().isEmpty()) {
            inputLayoutAmbientTemperature.setError(getString(R.string.err_fill_field));
            requestFocus(_AmbientTemperature);
            return false;
        } else {
            inputLayoutAmbientTemperature.setErrorEnabled(false);
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

    private boolean validateDuctingFacility() {
        if (_DuctingFacility.getText().toString().trim().isEmpty()) {
            inputLayoutDuctingFacility.setError(getString(R.string.err_fill_field));
            requestFocus(_DuctingFacility);
            return false;
        } else {
            inputLayoutDuctingFacility.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRunningHoursPerDay() {
        if (_RunningHoursPerDay.getText().toString().trim().isEmpty()) {
            inputLayoutRunningHoursPerDay.setError(getString(R.string.err_fill_field));
            requestFocus(_RunningHoursPerDay);
            return false;
        } else {
            inputLayoutRunningHoursPerDay.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRunningDaysPerWeek() {
        if (_RunningDaysPerWeek.getText().toString().trim().isEmpty()) {
            inputLayoutRunningDaysPerWeek.setError(getString(R.string.err_fill_field));
            requestFocus(_RunningDaysPerWeek);
            return false;
        } else {
            inputLayoutRunningDaysPerWeek.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateIsCompressorCleanedEveryday() {
        if (_IsCompressorCleanedEveryday.getText().toString().trim().isEmpty()) {
            inputLayoutIsCompressorCleanedEveryday.setError(getString(R.string.err_fill_field));
            requestFocus(_IsCompressorCleanedEveryday);
            return false;
        } else {
            inputLayoutIsCompressorCleanedEveryday.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateTotalRunHours() {
        if (_TotalRunHours.getText().toString().trim().isEmpty()) {
            inputLayoutTotalRunHours.setError(getString(R.string.err_fill_field));
            requestFocus(_TotalRunHours);
            return false;
        } else {
            inputLayoutTotalRunHours.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateTotalLoadHours() {
        if (_TotalLoadHours.getText().toString().trim().isEmpty()) {
            inputLayoutTotalLoadHours.setError(getString(R.string.err_fill_field));
            requestFocus(_TotalLoadHours);
            return false;
        } else {
            inputLayoutTotalLoadHours.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAirendDischargeTemperature() {
        if (_AirendDischargeTemperature.getText().toString().trim().isEmpty()) {
            inputLayoutAirendDischargeTemperature.setError(getString(R.string.err_fill_field));
            requestFocus(_AirendDischargeTemperature);
            return false;
        } else {
            inputLayoutAirendDischargeTemperature.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCurrentInLoadConditionAmps() {
        if (_CurrentInLoadConditionAmps.getText().toString().trim().isEmpty()) {
            inputLayoutCurrentInLoadConditionAmps.setError(getString(R.string.err_fill_field));
            requestFocus(_CurrentInLoadConditionAmps);
            return false;
        } else {
            inputLayoutCurrentInLoadConditionAmps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCurrentInIdleConditionAmps() {
        if (_CurrentInIdleConditionAmps.getText().toString().trim().isEmpty()) {
            inputLayoutCurrentInIdleConditionAmps.setError(getString(R.string.err_fill_field));
            requestFocus(_CurrentInIdleConditionAmps);
            return false;
        } else {
            inputLayoutCurrentInIdleConditionAmps.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNoOfMotorStarts() {
        if (_NoOfMotorStarts.getText().toString().trim().isEmpty()) {
            inputLayoutNoOfMotorStarts.setError(getString(R.string.err_fill_field));
            requestFocus(_NoOfMotorStarts);
            return false;
        } else {
            inputLayoutNoOfMotorStarts.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNoOfLoadValveOn() {
        if (_NoOfLoadValveOn.getText().toString().trim().isEmpty()) {
            inputLayoutNoOfLoadValveOn.setError(getString(R.string.err_fill_field));
            requestFocus(_NoOfLoadValveOn);
            return false;
        } else {
            inputLayoutNoOfLoadValveOn.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateOilLevel() {
        if (_OilLevel.getText().toString().trim().isEmpty()) {
            inputLayoutOilLevel.setError(getString(R.string.err_fill_field));
            requestFocus(_OilLevel);
            return false;
        } else {
            inputLayoutOilLevel.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCoolerCondition() {
        if (_CoolerCondition.getText().toString().trim().isEmpty()) {
            inputLayoutCoolerCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_CoolerCondition);
            return false;
        } else {
            inputLayoutCoolerCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMotorBearingNoise() {
        if (_MotorBearingNoise.getText().toString().trim().isEmpty()) {
            inputLayoutMotorBearingNoise.setError(getString(R.string.err_fill_field));
            requestFocus(_MotorBearingNoise);
            return false;
        } else {
            inputLayoutMotorBearingNoise.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAirendBearingNoise() {
        if (_AirendBearingNoise.getText().toString().trim().isEmpty()) {
            inputLayoutAirendBearingNoise.setError(getString(R.string.err_fill_field));
            requestFocus(_AirendBearingNoise);
            return false;
        } else {
            inputLayoutAirendBearingNoise.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateInletValveWorkingCondition() {
        if (_InletValveWorkingCondition.getText().toString().trim().isEmpty()) {
            inputLayoutInletValveWorkingCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_InletValveWorkingCondition);
            return false;
        } else {
            inputLayoutInletValveWorkingCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCombinationValveWorkingCondition() {
        if (_CombinationValveWorkingCondition.getText().toString().trim().isEmpty()) {
            inputLayoutCombinationValveWorkingCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_CombinationValveWorkingCondition);
            return false;
        } else {
            inputLayoutCombinationValveWorkingCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCavValveWorkingCondition() {
        if (_CavValveWorkingCondition.getText().toString().trim().isEmpty()) {
            inputLayoutCavValveWorkingCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_CavValveWorkingCondition);
            return false;
        } else {
            inputLayoutCavValveWorkingCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMpcValveWorkingCondition() {
        if (_MpcValveWorkingCondition.getText().toString().trim().isEmpty()) {
            inputLayoutMpcValveWorkingCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_MpcValveWorkingCondition);
            return false;
        } else {
            inputLayoutMpcValveWorkingCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateCouplingCondition() {
        if (_CouplingCondition.getText().toString().trim().isEmpty()) {
            inputLayoutCouplingCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_CouplingCondition);
            return false;
        } else {
            inputLayoutCouplingCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateBeltCondition() {
        if (_BeltCondition.getText().toString().trim().isEmpty()) {
            inputLayoutBeltCondition.setError(getString(R.string.err_fill_field));
            requestFocus(_BeltCondition);
            return false;
        } else {
            inputLayoutBeltCondition.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAirFilter() {
        if (_AirFilter.getText().toString().trim().isEmpty()) {
            inputLayoutAirFilter.setError(getString(R.string.err_fill_field));
            requestFocus(_AirFilter);
            return false;
        } else {
            inputLayoutAirFilter.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateOilFilter() {
        if (_OilFilter.getText().toString().trim().isEmpty()) {
            inputLayoutOilFilter.setError(getString(R.string.err_fill_field));
            requestFocus(_OilFilter);
            return false;
        } else {
            inputLayoutOilFilter.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateOilSeparator() {
        if (_OilSeparator.getText().toString().trim().isEmpty()) {
            inputLayoutOilSeparator.setError(getString(R.string.err_fill_field));
            requestFocus(_OilSeparator);
            return false;
        } else {
            inputLayoutOilSeparator.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateOil() {
        if (_Oil.getText().toString().trim().isEmpty()) {
            inputLayoutOil.setError(getString(R.string.err_fill_field));
            requestFocus(_Oil);
            return false;
        } else {
            inputLayoutOil.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFilterMat() {
        if (_FilterMat.getText().toString().trim().isEmpty()) {
            inputLayoutFilterMat.setError(getString(R.string.err_fill_field));
            requestFocus(_FilterMat);
            return false;
        } else {
            inputLayoutFilterMat.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateBearingReplacement() {
        if (_BearingReplacement.getText().toString().trim().isEmpty()) {
            inputLayoutBearingReplacement.setError(getString(R.string.err_fill_field));
            requestFocus(_BearingReplacement);
            return false;
        } else {
            inputLayoutBearingReplacement.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMotorGreasing() {
        if (_MotorGreasing.getText().toString().trim().isEmpty()) {
            inputLayoutMotorGreasing.setError(getString(R.string.err_fill_field));
            requestFocus(_MotorGreasing);
            return false;
        } else {
            inputLayoutMotorGreasing.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMotorGreasingInHours() {
        if (_MotorGreasingInHours.getText().toString().trim().isEmpty()) {
            inputLayoutMotorGreasingInHours.setError(getString(R.string.err_fill_field));
            requestFocus(_MotorGreasingInHours);
            return false;
        } else {
            inputLayoutMotorGreasingInHours.setErrorEnabled(false);
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


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private void clear() {
        _ControlType.setText("");
        _PressureSetting.setText("");
        _AmbientTemperature.setText("");
        _IncomingVoltage.setText("");
        _DuctingFacility.setText("");
        _RunningHoursPerDay.setText("");
        _RunningDaysPerWeek.setText("");
        _IsCompressorCleanedEveryday.setText("");
        _TotalRunHours.setText("");
        _TotalLoadHours.setText("");
        _AirendDischargeTemperature.setText("");
        _CurrentInLoadConditionAmps.setText("");
        _CurrentInIdleConditionAmps.setText("");
        _NoOfMotorStarts.setText("");
        _NoOfLoadValveOn.setText("");
        _OilLevel.setText("");
        _CoolerCondition.setText("");
        _MotorBearingNoise.setText("");
        _AirendBearingNoise.setText("");
        _InletValveWorkingCondition.setText("");
        _CombinationValveWorkingCondition.setText("");
        _CavValveWorkingCondition.setText("");
        _MpcValveWorkingCondition.setText("");
        _CouplingCondition.setText("");
        _BeltCondition.setText("");
        _AirFilter.setText("");
        _OilFilter.setText("");
        _OilSeparator.setText("");
        _Oil.setText("");
        _FilterMat.setText("");
        _BearingReplacement.setText("");
        _MotorGreasing.setText("");
        _MotorGreasingInHours.setText("");
        _SumpPressureAtUnloadCondition.setText("");
    }

    private void FindViewById() {
        inputLayoutControlType = (TextInputLayout) findViewById(R.id.input_layout_control_type);
        inputLayoutPressureSetting = (TextInputLayout) findViewById(R.id.input_layout_pressure_setting);
        inputLayoutAmbientTemperature = (TextInputLayout) findViewById(R.id.input_layout_ambient_temperature);
        inputLayoutIncomingVoltage = (TextInputLayout) findViewById(R.id.input_layout_incoming_voltage);
        inputLayoutDuctingFacility = (TextInputLayout) findViewById(R.id.input_layout_ducting_facility);
        inputLayoutRunningHoursPerDay = (TextInputLayout) findViewById(R.id.input_layout_running_hours_per_day);
        inputLayoutRunningDaysPerWeek = (TextInputLayout) findViewById(R.id.input_layout_running_days_per_week);
        inputLayoutIsCompressorCleanedEveryday = (TextInputLayout) findViewById(R.id.input_layout_is_compressor_cleaned_everyday);
        inputLayoutTotalRunHours = (TextInputLayout) findViewById(R.id.input_layout_total_run_hours);
        inputLayoutTotalLoadHours = (TextInputLayout) findViewById(R.id.input_layout_total_load_hours);
        inputLayoutAirendDischargeTemperature = (TextInputLayout) findViewById(R.id.input_layout_airend_discharge_temperature);
        inputLayoutCurrentInLoadConditionAmps = (TextInputLayout) findViewById(R.id.input_layout_current_in_load_condition_amps);
        inputLayoutCurrentInIdleConditionAmps = (TextInputLayout) findViewById(R.id.input_layout_current_in_idle_condition_amps);
        inputLayoutNoOfMotorStarts = (TextInputLayout) findViewById(R.id.input_layout_no_of_motor_starts);
        inputLayoutNoOfLoadValveOn = (TextInputLayout) findViewById(R.id.input_layout_no_of_load_valve_on);
        inputLayoutOilLevel = (TextInputLayout) findViewById(R.id.input_layout_oil_level);
        inputLayoutCoolerCondition = (TextInputLayout) findViewById(R.id.input_layout_cooler_condition);
        inputLayoutMotorBearingNoise = (TextInputLayout) findViewById(R.id.input_layout_motor_bearing_noise);
        inputLayoutAirendBearingNoise = (TextInputLayout) findViewById(R.id.input_layout_airend_bearing_noise);
        inputLayoutInletValveWorkingCondition = (TextInputLayout) findViewById(R.id.input_layout_inlet_valve_working_condition);
        inputLayoutCombinationValveWorkingCondition = (TextInputLayout) findViewById(R.id.input_layout_combination_valve_working_condition);
        inputLayoutCavValveWorkingCondition = (TextInputLayout) findViewById(R.id.input_layout_cav_valve_working_condition);
        inputLayoutMpcValveWorkingCondition = (TextInputLayout) findViewById(R.id.input_layout_mpc_valve_working_condition);
        inputLayoutCouplingCondition = (TextInputLayout) findViewById(R.id.input_layout_coupling_condition);
        inputLayoutBeltCondition = (TextInputLayout) findViewById(R.id.input_layout_belt_condition);
        inputLayoutAirFilter = (TextInputLayout) findViewById(R.id.input_layout_air_filter);
        inputLayoutOilFilter = (TextInputLayout) findViewById(R.id.input_layout_oil_filter);
        inputLayoutOilSeparator = (TextInputLayout) findViewById(R.id.input_layout_oil_separator);
        inputLayoutOil = (TextInputLayout) findViewById(R.id.input_layout_oil);
        inputLayoutFilterMat = (TextInputLayout) findViewById(R.id.input_layout_filter_mat);
        inputLayoutBearingReplacement = (TextInputLayout) findViewById(R.id.input_layout_bearing_replacement);
        inputLayoutMotorGreasing = (TextInputLayout) findViewById(R.id.input_layout_motor_greasing);
        inputLayoutMotorGreasingInHours = (TextInputLayout) findViewById(R.id.input_layout_motor_greasing_in_hours);
        inputLayoutSumpPressureAtUnloadCondition = (TextInputLayout) findViewById(R.id.input_layout_sump_pressure_at_unload_condition);

        _ControlType = (EditText) findViewById(R.id.et_control_type);
        _PressureSetting = (EditText) findViewById(R.id.et_pressure_setting);
        _AmbientTemperature = (EditText) findViewById(R.id.et_ambient_temperature);
        _IncomingVoltage = (EditText) findViewById(R.id.et_incoming_voltage);
        _DuctingFacility = (EditText) findViewById(R.id.et_ducting_facility);
        _RunningHoursPerDay = (EditText) findViewById(R.id.et_running_hours_per_day);
        _RunningDaysPerWeek = (EditText) findViewById(R.id.et_running_days_per_week);
        _IsCompressorCleanedEveryday = (EditText) findViewById(R.id.et_is_compressor_cleaned_everyday);
        _TotalRunHours = (EditText) findViewById(R.id.et_total_run_hours);
        _TotalLoadHours = (EditText) findViewById(R.id.et_total_load_hours);
        _AirendDischargeTemperature = (EditText) findViewById(R.id.et_airend_discharge_temperature);
        _CurrentInLoadConditionAmps = (EditText) findViewById(R.id.et_current_in_load_condition_amps);
        _CurrentInIdleConditionAmps = (EditText) findViewById(R.id.et_current_in_idle_condition_amps);
        _NoOfMotorStarts = (EditText) findViewById(R.id.et_no_of_motor_starts);
        _NoOfLoadValveOn = (EditText) findViewById(R.id.et_no_of_load_valve_on);
        _OilLevel = (EditText) findViewById(R.id.et_oil_level);
        _CoolerCondition = (EditText) findViewById(R.id.et_cooler_condition);
        _MotorBearingNoise = (EditText) findViewById(R.id.et_motor_bearing_noise);
        _AirendBearingNoise = (EditText) findViewById(R.id.et_airend_bearing_noise);
        _InletValveWorkingCondition = (EditText) findViewById(R.id.et_inlet_valve_working_condition);
        _CombinationValveWorkingCondition = (EditText) findViewById(R.id.et_combination_valve_working_condition);
        _CavValveWorkingCondition = (EditText) findViewById(R.id.et_cav_valve_working_condition);
        _MpcValveWorkingCondition = (EditText) findViewById(R.id.et_mpc_valve_working_condition);
        _CouplingCondition = (EditText) findViewById(R.id.et_coupling_condition);
        _BeltCondition = (EditText) findViewById(R.id.et_belt_condition);
        _AirFilter = (EditText) findViewById(R.id.et_air_filter);
        _OilFilter = (EditText) findViewById(R.id.et_oil_filter);
        _OilSeparator = (EditText) findViewById(R.id.et_oil_separator);
        _Oil = (EditText) findViewById(R.id.et_oil);
        _FilterMat = (EditText) findViewById(R.id.et_filter_mat);
        _BearingReplacement = (EditText) findViewById(R.id.et_bearing_replacement);
        _MotorGreasing = (EditText) findViewById(R.id.et_motor_greasing);
        _MotorGreasingInHours = (EditText) findViewById(R.id.et_motor_greasing_in_hours);
        _SumpPressureAtUnloadCondition = (EditText) findViewById(R.id.et_sump_pressure_at_unload_condition);

        _Submit = (Button) findViewById(R.id.add_service);
        _Clear = (Button) findViewById(R.id.remove_service);


    }
}
