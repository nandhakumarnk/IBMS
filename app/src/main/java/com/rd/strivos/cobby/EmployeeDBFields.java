package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 19-01-2016.
 */
public class EmployeeDBFields {

    public static abstract class EmployeeDetails implements BaseColumns {
        public static final String TABLE_NAME = "tblemployee";
        public static final String COLUMN_NAME_EMPLOYEE_NAME = "name";
        public static final String COLUMN_NAME_EMPLOYEE_MOBILE = "mobile";
        public static final String COLUMN_NAME_EMPLOYEE_EMPLOYEEID = "employeeid";
        public static final String COLUMN_NAME_EMPLOYEE_DESIGNATION = "designation";
        public static final String COLUMN_NAME_EMPLOYEE_ID = "employee_id";
        public static final String COLUMN_NAME_EMPLOYEE_DEPARTMENT = "department";
        public static final String COLUMN_NAME_EMPLOYEE_DEPARTMENT_ID = "department_id";
        public static final String COLUMN_NAME_EMPLOYEE_DESIGNATION_ID = "designation_id";
        public static final String COLUMN_NAME_EMPLOYEE_DESIGNATION_CATEGORY_ID = "designation_category_id";
    }
}
