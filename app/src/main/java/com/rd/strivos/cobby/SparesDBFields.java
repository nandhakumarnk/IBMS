package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 21-01-2016.
 */
public class SparesDBFields {

    public static abstract class SparesAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblSpares";
        public static final String COLUMN_NAME_PRINCIPAL = "principal";
        public static final String COLUMN_NAME_SPARES_ID = "sparesid";
        public static final String COLUMN_NAME_PART_NO = "partno";
        public static final String COLUMN_NAME_SHORT_DESCRIPTION = "shortdescription";
        public static final String COLUMN_NAME_UOM = "uom";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    public static abstract class SparesLookupType implements BaseColumns {
        public static final String TABLE_NAME = "tblSparesLookup";
        public static final String COLUMN_NAME_PRINCIPAL = "principal";
        public static final String COLUMN_NAME_SPARES_ID = "sparesid";
        public static final String COLUMN_NAME_PART_NO = "partno";
        public static final String COLUMN_NAME_SHORT_DESCRIPTION = "shortdescription";
        public static final String COLUMN_NAME_UOM = "uom";
    }
}
