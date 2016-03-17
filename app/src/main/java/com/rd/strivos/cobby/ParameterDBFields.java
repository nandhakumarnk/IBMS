package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 25-01-2016.
 */
public class ParameterDBFields {

    public static abstract class ParameterAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblParameter";
        public static final String COLUMN_NAME_CONTROL_TYPE = "controltype";
        public static final String COLUMN_NAME_PRESSURE_SETTING = "pressuresetting";
        public static final String COLUMN_NAME_AMBIENT_TEMPERATURE = "ambienttemperature";
        public static final String COLUMN_NAME_INCOMING_VOLTAGE = "incomingvoltage";
        public static final String COLUMN_NAME_DUCTING_FACILITY = "ductingfacility";
        public static final String COLUMN_NAME_RUNNING_HOURS_PER_DAY = "runninghoursperday";
        public static final String COLUMN_NAME_RUNNING_DAYS_PER_WEEK = "runningdaysperweek";
        public static final String COLUMN_NAME_IS_COMPRESSOR_CLEANED_EVERYDAY = "iscompressorcleanedeveryday";
        public static final String COLUMN_NAME_TOTAL_RUN_HOURS = "totalrunhours";
        public static final String COLUMN_NAME_TOTAL_LOAD_HOURS = "totalloadhours";
        public static final String COLUMN_NAME_AIREND_DISCHARGE_TEMPERATURE = "airenddischargetemperature";
        public static final String COLUMN_NAME_CURRENT_IN_LOAD_CONDITION_AMPS = "currentinloadconditionamps";
        public static final String COLUMN_NAME_CURRENT_IN_IDLE_CONDITION_AMPS = "currentinidleconditionamps";
        public static final String COLUMN_NAME_NO_OF_MOTOR_STARTS = "noofmotorstarts";
        public static final String COLUMN_NAME_NO_OF_LOAD_VALVE_ON = "noofloadvalveon";
        public static final String COLUMN_NAME_OIL_LEVEL = "oillevel";
        public static final String COLUMN_NAME_COOLER_CONDITION = "coolercondition";
        public static final String COLUMN_NAME_MOTOR_BEARING_NOISE = "motorbearingnoise";
        public static final String COLUMN_NAME_AIREND_BEARING_NOISE = "airendbearingnoise";
        public static final String COLUMN_NAME_INLET_VALVE_WORKING_CONDITION = "inletvalveworkingcondition";
        public static final String COLUMN_NAME_COMBINATION_VALVE_WORKING_CONDITION = "combinationvalveworkingcondition";
        public static final String COLUMN_NAME_CAV_VALVE_WORKING_CONDITION = "cavvalveworkingcondition";
        public static final String COLUMN_NAME_MPC_VALVE_WORKING_CONDITION = "mpcvalveworkingcondition";
        public static final String COLUMN_NAME_COUPLING_CONDITION = "couplingcondition";
        public static final String COLUMN_NAME_BELT_CONDITION = "beltcondition";
        public static final String COLUMN_NAME_AIR_FILTER = "airfilter";
        public static final String COLUMN_NAME_OIL_FILTER = "oilfilter";
        public static final String COLUMN_NAME_OIL_SEPARATOR = "oilseparator";
        public static final String COLUMN_NAME_OIL = "oil";
        public static final String COLUMN_NAME_FILTER_MAT = "filtermat";
        public static final String COLUMN_NAME_BEARING_REPLACEMENT = "bearingreplacement";
        public static final String COLUMN_NAME_MOTOR_GREASING = "motorgreasing";
        public static final String COLUMN_NAME_MOTOR_GREASING_IN_HOURS = "motorgreasinginhours";
        public static final String COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION = "sumppressureatunloadcondition";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
