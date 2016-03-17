package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 18-02-2016.
 */
public class CommissioningDBFields {

    public static abstract class CommissioningAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblCommissioning";
        public static final String COLUMN_NAME_SERVICE_ENGINEER_ID = "serviceengineerid";
        public static final String COLUMN_NAME_SERVICE_ENGINEER_NAME = "serviceengineername";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_PINCODE = "pincode";
        public static final String COLUMN_NAME_DISTRICT = "district";
        public static final String COLUMN_NAME_CUSTOMER_NAME = "customername";
        public static final String COLUMN_NAME_CUSTOMER_ID = "customerid";
        public static final String COLUMN_NAME_ACCOUNT_M_CONTACT_ID = "accountmcontactid";
        public static final String COLUMN_NAME_ACCOUNT_M_CONTACT_NAME = "accountmcontactname";
        public static final String COLUMN_NAME_PHONE_NO = "phoneno";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PRODUCT_ID = "productid";
        public static final String COLUMN_NAME_PRODUCT_NAME = "productname";
        public static final String COLUMN_NAME_MACHINE_SL_NO = "machineslno";
        public static final String COLUMN_NAME_PART_NO = "partno";
        public static final String COLUMN_NAME_CONTRACT_TYPE_ID = "contracttypeid";
        public static final String COLUMN_NAME_FROM_DATE = "fromdate";
        public static final String COLUMN_NAME_TO_DATE = "todate";
        public static final String COLUMN_NAME_CONTRACT_AMOUNT = "contractamount";
        public static final String COLUMN_NAME_VISIT_CHARGES = "visitcharges";
        public static final String COLUMN_NAME_FREE_VISITS = "freevisits";
        public static final String COLUMN_NAME_REMARKS = "remarks";
        public static final String COLUMN_NAME_LAT = "lat";
        public static final String COLUMN_NAME_LON = "lon";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    public static abstract class ContractTypeAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblContractType";
        public static final String COLUMN_NAME_CONTRACT_TYPE_ID = "id";
        public static final String COLUMN_NAME_CONTRACT_TYPE_DESCRIPTION = "description";
        public static final String COLUMN_NAME_CONTRACT_TYPE_MASTER_TYPE_ID = "mastertypeid";

    }

    public static abstract class CommissioningParameterInfoAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblCommissioningparameterinfo";
        public static final String COLUMN_NAME_ENVIRONMENT = "environment";
        public static final String COLUMN_NAME_AMBIENT_TEMPERATURE_IN_DEGREE_CELSIUS = "ambient_temperature_in_degree_celsius";
        public static final String COLUMN_NAME_DUCTING_INSTALLED = "ducting_installed";
        public static final String COLUMN_NAME_ROOM_VENTILATION = "room_ventilation";
        public static final String COLUMN_NAME_FLEXIBLE_CONNECTION = "flexible_connection";
        public static final String COLUMN_NAME_INSOLATION_VALUE = "insolation_value";
        public static final String COLUMN_NAME_INCOMING_VOLTAGE = "incoming_voltage";
        public static final String COLUMN_NAME_SUPPLY_COPPER_CABLE_RECOMMENDED_IN_MM = "supply_copper_cable_recommended_in_mm";
        public static final String COLUMN_NAME_FUSE_INSTALLED_IN_AMPS = "fuse_installed_in_amps";
        public static final String COLUMN_NAME_FULL_LOAD_CURRENT = "full_load_current";
        public static final String COLUMN_NAME_UNLOAD_CURRENT = "unload_current";
        public static final String COLUMN_NAME_SETTING_OVERLOAD_PROTECTION_OF_MAIN_MOTOR_IN_AMPS = "setting_overload_protection_of_main_motor_in_amps";
        public static final String COLUMN_NAME_SETTING_OVERLOAD_PROTECTION_OF_FAN_MOTOR_IN_AMPS = "setting_overload_protection_of_fan_motor_in_amps";
        public static final String COLUMN_NAME_AIREND_TEMPERATURE_IN_DEGREE_CELCIUS = "airend_temperature_in_degree_celcius";
        public static final String COLUMN_NAME_PRESSUR_SETTING = "pressur_setting";
        public static final String COLUMN_NAME_SUMP_PRESSURE_AT_UNLOAD_CONDITION = "sump_pressure_at_unload_condition";
        public static final String COLUMN_NAME_DRYER_PIPELINE_BYPASS = "dryer_pipeline_bypass";
        public static final String COLUMN_NAME_FILTER_PIPELINE_BYPASS = "filter_pipeline_bypass";
        public static final String COLUMN_NAME_VERTICAL_AIR_RECEIVER_GROUTING = "vertical_air_receiver_grouting";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    public static abstract class PreCommissioningListAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblPreCommissioningList";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_LONGDOCUMENTNO = "longdocumentno";
        public static final String COLUMN_NAME_CONVDOCDATE = "convdocdate";
        public static final String COLUMN_NAME_SERVICEENGINEER = "serviceengineer";
        public static final String COLUMN_NAME_SLNO = "slno";
        public static final String COLUMN_NAME_PARTNO = "partno";
        public static final String COLUMN_NAME_PRODUCTPARTNO = "productpartno";
        public static final String COLUMN_NAME_ACCOUNTM_ID = "ACCOUNTM_ID";
    }

}
