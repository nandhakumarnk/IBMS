package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 07-01-2016.
 */
public class PersonDBFields {

    public PersonDBFields(){}

    public static abstract class PersonAdd implements BaseColumns {
        public static final String TABLE_NAME = "tbladdmoreperson";
        public static final String COLUMN_NAME_PROJECT_ID = "projectid";
        public static final String COLUMN_NAME_PERSON_NAME = "name";
        public static final String COLUMN_NAME_PERSON_DESIGNATION = "designation";
        public static final String COLUMN_NAME_PERSON_MOBILE_NUMBER = "mobile";
        public static final String COLUMN_NAME_PERSON_EMAIL = "email";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projecttype";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
