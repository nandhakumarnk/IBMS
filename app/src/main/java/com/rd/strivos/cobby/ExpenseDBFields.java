package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 08-01-2016.
 */
public class ExpenseDBFields {

    public ExpenseDBFields() {
    }

    public static abstract class ExpenseAdd implements BaseColumns {
        public static final String TABLE_NAME = "tbladdexpense";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_EXPENSE_TYPE = "expensetype";
        public static final String COLUMN_NAME_EXPENSE_TYPE_ID = "expensetypeid";
        public static final String COLUMN_NAME_EXPENSE_AMOUNT = "expenseamount";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projecttype";
        public static final String COLUMN_NAME_STATUS = "status";
    }

    public static abstract class ExpenseType implements BaseColumns {
        public static final String TABLE_NAME = "tbladdexpensetype";
        public static final String COLUMN_NAME_EXPENSES_TYPE_ID = "id";
        public static final String COLUMN_NAME_EXPENSE_TYPE_DESCRIPTION = "description";
        public static final String COLUMN_NAME_EXPENSE_DB_MASTER_TYPE_ID = "mastertypeid";
    }
}
