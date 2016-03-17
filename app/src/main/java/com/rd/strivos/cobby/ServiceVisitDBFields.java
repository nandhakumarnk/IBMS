package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 18-01-2016.
 */
public class ServiceVisitDBFields {

    public static abstract class ServiceVisitType implements BaseColumns {
        public static final String TABLE_NAME = "tbladdservicevisittype";
        public static final String COLUMN_NAME_SERVICE_VISIT_TYPE_ID = "id";
        public static final String COLUMN_NAME_SERVICE_VISIT_TYPE_DESCRIPTION = "description";
        public static final String COLUMN_NAME_SERVICE_VISIT_DB_MASTER_TYPE_ID = "mastertypeid";
    }

    public static abstract class ServiceVisitWorkStatus implements BaseColumns {
        public static final String TABLE_NAME = "tbladdservicevisitworkstatus";
        public static final String COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_ID = "id";
        public static final String COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DESCRIPTION = "description";
        public static final String COLUMN_NAME_SERVICE_VISIT_WORK_STATUS_DB_MASTER_ID = "mastertypeid";
    }

    public static abstract class ProductInstallationDtls implements BaseColumns {
        public static final String TABLE_NAME = "tblProductInstallationDtls";
        public static final String COLUMN_NAME_INSTALLATION_ACCOUNTM_ID = "accountm_id";
        public static final String COLUMN_NAME_INSTALLATION_COMMISSIONING_TRANS_ID = "commissioningtrans_id";
        public static final String COLUMN_NAME_INSTALLATION_CONTRACT_SUMMARY_ID = "contractsummary_id";
        public static final String COLUMN_NAME_INSTALLATION_PRINCIPAL = "principal";
        public static final String COLUMN_NAME_INSTALLATION_PRODUCT_PART_NO = "productpartno";
        public static final String COLUMN_NAME_INSTALLATION_PART_NO = "partno";
        public static final String COLUMN_NAME_INSTALLATION_SL_NO = "slno";
        public static final String COLUMN_NAME_INSTALLATION_CONTRACT_TYPE = "contracttype";
        public static final String COLUMN_NAME_INSTALLATION_FROM_DATE = "fromdate";
        public static final String COLUMN_NAME_INSTALLATION_TO_DATE = "todate";
        public static final String COLUMN_NAME_INSTALLATION_PRODUCT_TYPE_ID = "producttypeid";
    }

    public static abstract class ServiceVisit implements BaseColumns {
        public static final String TABLE_NAME = "tblServiceVisit";
        public static final String COLUMN_NAME_EMPLOYEE_ID = "employeeId";
        public static final String COLUMN_NAME_CO_EMPLOYEE_ID = "coEmployeeId";
        public static final String COLUMN_NAME_IN_DATE = "InDate";
        public static final String COLUMN_NAME_OUT_DATE = "OutDate";
        public static final String COLUMN_NAME_ACCOUNTM_ID = "companyId";
        public static final String COLUMN_NAME_COMPANY_NAME = "companyName";
        public static final String COLUMN_NAME_ADDRESS = "Address";
        public static final String COLUMN_NAME_PINCODE = "Pincode";
        public static final String COLUMN_NAME_DISTRICT_ID = "districtId";
        public static final String COLUMN_NAME_VISIT_TYPE = "VisitType";
        public static final String COLUMN_NAME_PRODUCT = "Product";
        public static final String COLUMN_NAME_PRODUCT_TYPE = "ProductType";
        public static final String COLUMN_NAME_MACHINE_SERIAL_NO = "MachineSlNo";
        public static final String COLUMN_NAME_PART_NO = "PartNo";
        public static final String COLUMN_NAME_CONTRACT_TYPE = "ContractType";
        public static final String COLUMN_NAME_SERVICE_PERFORMED = "ServicePerformed";
        public static final String COLUMN_NAME_ACTION_REQUIRED = "ActionRequired";
        public static final String COLUMN_NAME_VISIT_SUMMARY = "VisitSummary";
        public static final String COLUMN_NAME_WORK_STATUS = "WorkStatus";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_STATUS = "Status";
    }

    public static abstract class ServiceVisitList implements BaseColumns {
        public static final String TABLE_NAME = "tbladdservicevisitlist";
        public static final String COLUMN_NAME_SERVICE_VISIT_LIST_CONTRACT_SUMMARY_ID = "contractsummaryid";
        public static final String COLUMN_NAME_SERVICE_VISIT_LIST_REF_NO = "refno";
        public static final String COLUMN_NAME_SERVICE_VISIT_LIST_REF_DATE = "refdate";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
    }
}
