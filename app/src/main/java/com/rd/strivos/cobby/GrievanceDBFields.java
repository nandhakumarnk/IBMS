package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 23-01-2016.
 */
public class GrievanceDBFields {

    public static abstract class GrievanceAdd implements BaseColumns {
        public static final String TABLE_NAME = "tblGrievance";
        public static final String COLUMN_NAME_GRIEVANCE = "grievance";
        public static final String COLUMN_NAME_SOLUTION = "solution";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }

}
