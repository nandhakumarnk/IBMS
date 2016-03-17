package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 11-02-2016.
 */
public class PreCommissioningDBFields {

    public static abstract class PreCommissioningAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblPreCommissioning";
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
        public static final String COLUMN_NAME_EDC = "edc";
        public static final String COLUMN_NAME_YOM = "yom";
        public static final String COLUMN_NAME_REMARKS = "remarks";
        public static final String COLUMN_NAME_LAT = "lat";
        public static final String COLUMN_NAME_LON = "lon";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
