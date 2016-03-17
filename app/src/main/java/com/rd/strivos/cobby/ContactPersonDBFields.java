package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 10-02-2016.
 */
public class ContactPersonDBFields {

    public static abstract class ContactPerson implements BaseColumns {
        public static final String TABLE_NAME = "tblcontactperson";
        public static final String COLUMN_NAME_CONTACT_PERSON_ID = "id";
        public static final String COLUMN_NAME_CONTACT_PERSON_ACCOUNTM_ID = "accountm_id";
        public static final String COLUMN_NAME_CONTACT_PERSON_CONTACT_NAME = "contact_name";
        public static final String COLUMN_NAME_CONTACT_PERSON_MOBILENO = "mobileno";
        public static final String COLUMN_NAME_CONTACT_PERSON_EMAIL = "email";
        public static final String COLUMN_NAME_CONTACT_PERSON_DEPARTMENT = "department";
        public static final String COLUMN_NAME_CONTACT_PERSON_DESIGNATION = "designation";
        public static final String COLUMN_NAME_CONTACT_PERSON_STATUSM_ID = "statusm_id";

    }
}
