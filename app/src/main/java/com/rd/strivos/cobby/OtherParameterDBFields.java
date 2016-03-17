package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 09-02-2016.
 */
public class OtherParameterDBFields {

    public static abstract class OtherParameterInfoAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblParameter";
        public static final String DRYER_CURRENT_IN_VOLTAGE_R = "dryer_current_in_voltage_r";
        public static final String DRYER_CURRENT_IN_VOLTAGE_Y = "dryer_current_in_voltage_y";
        public static final String DRYER_CURRENT_IN_VOLTAGE_B = "dryer_current_in_voltage_b";
        public static final String DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_R = "dryer_current_in_load_condition_amps_r";
        public static final String DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_Y = "dryer_current_in_load_condition_amps_y";
        public static final String DRYER_CURRENT_IN_LOAD_CONDITION_AMPS_B = "dryer_current_in_load_condition_amps_b";
        public static final String DRYER_DEW_POINT_INDICATION = "dryer_dew_point_indication";
        public static final String FILTER_CATRIDGE = "filter_catridge";
        public static final String FILTER_CATRIDGE_INDICATION_LEVEL = "filter_catridge_indication_level";
        public static final String FILTER_CATRIDGE_CHANGE_DATE = "filter_catridge_change_date";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";

    }
}
