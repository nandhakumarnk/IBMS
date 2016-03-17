package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 11-01-2016.
 */
public class ProductDBFields {

    public ProductDBFields() {
    }

    public static abstract class ProductAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblProduct";
        public static final String COLUMN_NAME_PRINCIPAL = "principal";
        public static final String COLUMN_NAME_PRODUCT_ID = "productid";
        public static final String COLUMN_NAME_PART_NO = "partno";
        public static final String COLUMN_NAME_SHORT_DESCRIPTION = "shortdescription";
        public static final String COLUMN_NAME_PRODUCT_TYPE = "producttype";
        public static final String COLUMN_NAME_EQUIPMENT_TYPE = "equipmenttype";
        public static final String COLUMN_NAME_UOM = "uom";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    public static abstract class ProductLookupType implements BaseColumns {
        public static final String TABLE_NAME = "tblProductLookup";
        public static final String COLUMN_NAME_PRINCIPAL = "principal";
        public static final String COLUMN_NAME_PRODUCT_ID = "productid";
        public static final String COLUMN_NAME_PART_NO = "partno";
        public static final String COLUMN_NAME_SHORT_DESCRIPTION = "shortdescription";
        public static final String COLUMN_NAME_PRODUCT_TYPE = "producttype";
        public static final String COLUMN_NAME_EQUIPMENT_TYPE = "equipmenttype";
        public static final String COLUMN_NAME_UOM = "uom";
    }
}
