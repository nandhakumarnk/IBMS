package com.rd.strivos.cobby;

import android.provider.BaseColumns;

/**
 * Created by COBURG DESIGN on 01-02-2016.
 */
public class EnquiryBankDBFields {

    public static abstract class EnquirySource implements BaseColumns {
        public static final String TABLE_NAME = "tblEnquirySource";
        public static final String COLUMN_NAME_ENQUIRY_SOURCE_ID = "id";
        public static final String COLUMN_NAME_ENQUIRY_SOURCE_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ENQUIRY_SOURCE_MASTER_TYPE_ID = "mastertypeid";
    }

    public static abstract class MediaMediator implements BaseColumns {
        public static final String TABLE_NAME = "tblMediaMediator";
        public static final String COLUMN_NAME_MEDIA_MEDIATOR_ID = "id";
        public static final String COLUMN_NAME_MEDIA_MEDIATOR_DESCRIPTION = "description";
        public static final String COLUMN_NAME_MEDIA_MEDIATOR_MASTER_TYPE_ID = "mastertypeid";
        public static final String COLUMN_NAME_MEDIA_MEDIATOR_PARENT_MASTERM_ID = "parentmasterid";
    }

    public static abstract class EnquiryBank implements BaseColumns {
        public static final String TABLE_NAME = "tblEnquiryBank";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_NO= "EnquiryNo";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_DATE= "EnquiryDate";
        public static final String COLUMN_NAME_ENQUIRY_BANK_REFERENCE_NO= "ReferenceNo";
        public static final String COLUMN_NAME_ENQUIRY_BANK_REFERENCE_DATE= "ReferenceDate";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_RECEIVED_BY= "EnquiryReceivedBy";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_SOURCE= "EnquirySource";
        public static final String COLUMN_NAME_ENQUIRY_BANK_MEDIAOR_MEDIATOR= "MediaOrMediator";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_PROVIDER_NAME= "EnquiryProviderName";
        public static final String COLUMN_NAME_ENQUIRY_BANK_CUSTOMER= "Customer";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ADDRESS= "Address";
        public static final String COLUMN_NAME_ENQUIRY_BANK_PINCODE= "Pincode";
        public static final String COLUMN_NAME_ENQUIRY_BANK_DISTRICT= "District";
        public static final String COLUMN_NAME_ENQUIRY_BANK_CONTACT_PERSON= "ContactPerson";
        public static final String COLUMN_NAME_ENQUIRY_BANK_CONTACT_PERSON_NO= "ContactPersonNo";
        public static final String COLUMN_NAME_ENQUIRY_BANK_STD_NO= "STDNo";
        public static final String COLUMN_NAME_ENQUIRY_BANK_TELE_PHONE_NO= "TelePhoneNo";
        public static final String COLUMN_NAME_ENQUIRY_BANK_WEBSITE= "Website";
        public static final String COLUMN_NAME_ENQUIRY_BANK_EMAIL= "Email";
        public static final String COLUMN_NAME_ENQUIRY_BANK_PRODUCT_REQUIREMENT= "ProductRequirement";
        public static final String COLUMN_NAME_ENQUIRY_BANK_REQ_ON_OR_BEFORE= "ReqOnOrBefore";
        public static final String COLUMN_NAME_ENQUIRY_BANK_ENQUIRY_EXECUTOR= "EnquiryExecutor";
        public static final String COLUMN_NAME_ENQUIRY_BANK_REMARKS= "Remarks";
        public static final String COLUMN_NAME_PROJECT_TYPE = "projectype";
        public static final String COLUMN_NAME_PROJECT_NAME = "projectname";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
    }
}
