package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 11-02-2016.
 */
public class PreCommissioningParameterInfoDBFields {

    public static abstract class PreCommissioningParameterInfoAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblPrecommissioningparameterinfo";
        public static final String ENVIRONMENT = "environment";
        public static final String ROOM_VENTILATION = "room_ventilation";
        public static final String DUCTING_RECOMMENDED = "ducting_recommended";
        public static final String FLEXIBLE_CONNECTION = "flexible_connection";
        public static final String INSOLATION_VALVE = "insolation_valve";
        public static final String INCOMING_VOLTAGE = "incoming_voltage";
        public static final String SUPPLY_COPPER_CABLE_RECOMMENDED_IN_MM = "supply_copper_cable_recommended_in_mm";
        public static final String FUSE_RECOMMENDED_IN_AMPS = "fuse_recommended_in_amps";
        public static final String DRYER_PIPELINE_BYPASS_RECOMMENDED = "dryer_pipeline_bypass_recommended";
        public static final String FILTER_PIPELINE_BYPASS_RECOMMENDED = "filter_pipeline_bypass_recommended";
        public static final String VERTICAL_AIR_RECEIVER_GROUTING_RECOMMENDED = "vertical_air_receiver_grouting_recommended";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
