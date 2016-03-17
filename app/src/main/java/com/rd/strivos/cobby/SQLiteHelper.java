package com.rd.strivos.cobby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String TAG = "SQLiteHelper.java";

    private static final String DATABASE_NAME = "IBMS.db";
    private static final int DATABASE_VERSION = 7;


    String table_create_staff = "Create table stafflogin(_id integer primary key autoincrement,"
            + "staffid text,"
            + "staffname text,"
            + "username text,"
            + "userpassword text);";

    String table_create_register = "create table register(_id integer primary key autoincrement,"
            + "NAME text ,"
            + "MOBILE text ,"
            + "EMPLOYEEID text ,"
            + "DESIGNATION text ,"
            + "EMPLOYEE_ID text ,"
            + "DEPARTMENT text ,"
            + "DEPARTMENT_ID text ,"
            + "DESIGNATION_ID text ,"
            + "EMAIL text ,"
            + "PINNO text ,"
            + "COMPANYM_ID text ,"
            + "DOB text ,"
            + "SIM text ,"
            + "OTP text ,"
            + "STATUS text );";
    //if not exists
    String table_create_pin = "create table tblpin(_id integer primary key autoincrement,"
            + "pin text );";

    String table_create_flag = "create table tblflag(_id integer primary key autoincrement,"
            + "flag text );";

    String tableEmployee = "create table tblEmployee(_id integer primary key autoincrement,"
            + "NAME text ,"
            + "MOBILE text ,"
            + "EMPLOYEEID text ,"
            + "DESIGNATION text ,"
            + "EMPLOYEE_ID text ,"
            + "DEPARTMENT text ,"
            + "DEPARTMENT_ID text ,"
            + "DESIGNATION_ID text );";

    String tableCompany = "create table tblCompany(_id integer primary key autoincrement,"
            + "id text ,"
            + "ACCOUNTNAME text ,"
            + "PINCODE text ,"
            + "STD text ,"
            + "PHONE text ,"
            + "MOBILE text ,"
            + "WEBSITE text ,"
            + "EMAIL text ,"
            + "DISTRICT text ,"
            + "TYPE text ,"
            + "TYPE_ID text ,"
            + "Address text );";

    String tableDistrict = "create table tblDistrict(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "PARENT_MASTERM_ID text );";

    String tableAddMore = "create table tblAddMorePerson(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "NAME text ,"
            + "Designation text ,"
            + "PHONE text ,"
            + "Email text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableProductLookup = "create table tblProductLookup(_id integer primary key autoincrement,"
            + "PRINCIPAL text ,"
            + "ID text ,"
            + "PARTNO text ,"
            + "SHORTDESCRIPTION text ,"
            + "PRODUCTTYPE text ,"
            + "EQUIPMENTTYPE text ," + "UOM text );";

    String tableProduct = "create table tblProduct(_id integer primary key autoincrement,"
            + "PRINCIPAL text ,"
            + "ID text ,"
            + "PARTNO text ,"
            + "SHORTDESCRIPTION text ,"
            + "PRODUCTTYPE text ,"
            + "EQUIPMENTTYPE text ,"
            + "UOM text ,"
            + "Projectid text ,"
            + "Quantity text ,"
            + "CompanyName text ,"
            + "Status text );";

    String tableProspect = "create table tblProspect(_id integer primary key autoincrement,"
            + "CompanyName text ," // 1
            + "FullAddress text ," // 2
            + "District text ," // 3
            + "Pincode text ," // 4
            + "FDAPersonName text ," // 5
            + "FDAPersonDesignation text ," // 6
            + "PersonMeet text ," // 7
            + "Designation text ," // 8
            + "TeleNoSTD text ," // 9
            + "TeleNo text ," // 10
            + "PhoneNo text ," // 11
            + "Email text ," // 12
            + "WebSite text ," // 13
            + "FotoVCardFront text ," // 14
            + "FotoVCardBack text ," // 15
            + "FotoPerson text ," // 16
            + "AnyPersonMet text ," // 17
            + "VisitReference text ," // 18
            + "Industry text ," // 19
            + "Process text ," // 20
            + "EndProductMfrd text ," // 21
            + "ProductClassify text ," // 22
            + "WriteUp text ," // 23
            + "ProjectStatus text ,"// 24
            + "ProjectCompleteBy text ," // 25
            + "ProjectDeliveyBefore text ," // 26
            + "DtlsTech text ," // 27
            + "CustomerTech text ," // 28
            + "CLP text ," // 29
            + "Offered text ," // 30
            + "OfferedHowMuch text ," // 31
            + "ScopeofSupply text ," // 32
            + "TechFeatures text ," // 33
            + "Quality text ," // 34
            + "ServiceSupport text ," // 35
            + "DeliverySchedule text ," // 36
            + "ModeSale text ," // 37
            + "Price text ," // 38
            + "Payment text ," // 39
            + "Warranty text ," // 40
            + "Other text ," // 41
            + "OutCome text ," // 42
            + "OrderReceived text ," // 43
            + "ActionBy text ," // 44
            + "Remarks text ," // 45
            + "UpStatus text ," // 46
            + "CoEmployeeID text ," // 47
            + "TypeofCall text ," // 48
            + "CallDateTime text ," // 49
            + "CheckPoint text ," // 50
            + "CreatedBy text ," // 51
            + "CreatedOn text ," // 52
            + "latitude text ," // 53
            + "longitude text );"; // 54

    String tableXProspect = "create table tblXProspect(_id integer primary key autoincrement,"
            + "CompanyName text ," // 1
            + "FullAddress text ," // 2
            + "District text ," // 3
            + "Pincode text ," // 4
            + "FDAPersonName text ," // 5
            + "FDAPersonDesignation text ," // 6
            + "PersonMeet text ," // 7
            + "Designation text ," // 8
            + "TeleNostd text ," // 9
            + "TeleNo text ," // 10
            + "PhoneNo text ," // 11
            + "Email text ," // 12
            + "WebSite text ," // 13
            + "FotoVCardFront text ," // 14
            + "FotoVCardBack text ," // 15
            + "FotoPerson text ," // 16
            + "AnyPersonMet text ," // 17
            + "VisitReference text ," // 18
            + "ProjectStatus text ,"// 19
            + "ProjectCompleteBy text ," // 20
            + "ProjectDeliveyBefore text ," // 21
            + "CLP text ," // 22
            + "Offered text ," // 23
            + "OfferedHowMuch text ," // 24
            + "ScopeofSupply text ," // 25
            + "TechFeatures text ," // 26
            + "Quality text ," // 27
            + "ServiceSupport text ," // 28
            + "DeliverySchedule text ," // 29
            + "ModeSale text ," // 30
            + "Price text ," // 31
            + "Payment text ," // 32
            + "Warranty text ," // 33
            + "Other text ," // 34
            + "OutCome text ," // 35
            + "OrderReceived text ," // 36
            + "ActionBy text ," // 37
            + "Remarks text ," // 38
            + "UpStatus text ," // 39
            + "CoEmployeeID text ," // 40
            + "TypeofCall text ," // 41
            + "CallDateTime text ," // 42
            + "CheckPoint text ," // 43
            + "CreatedBy text ," // 44
            + "CreatedOn text );"; // 45

    String tableDealer = "create table tblDealer(_id integer primary key autoincrement,"
            + "companyname text ," // 1
            + "fullAddress text ," // 2
            + "District text ," // 3
            + "Pincode text ," // 4
            + "FDAPersonMeet text ," // 5
            + "FDAPersonMDesignation text ," // 6
            + "FDATelephoneSTD text ," // 7
            + "FDATelephone text ," // 8
            + "FDAPhone text ," // 9
            + "FDAEmail text ," // 10
            + "Website text ," // 11
            + "FotoVCardFront text ," // 12
            + "FotoVCardBack text ," // 13
            + "FotoPerson text ," // 14
            + "FDAName text ," // 15
            + "FDADesignation text ," // 16
            + "AnyOtherMeet text ," // 17
            + "DealerContact text ," // 18
            + "DealerReffered text ," // 19
            + "SeekingInterest text ," // 20
            + "InterestProduct text ," // 21
            + "InterestedIn text ," // 22
            + "AfterSale text ," // 23
            + "DealerShip text ," // 24
            + "AreaRegion text ," // 25
            + "WriteUp text ," // 26
            + "Outcome text ," // 27
            + "ActionCoburg text ," // 28
            + "remarks text ," // 29
            + "UpStatus text ," // 30
            + "CoEmployeeID text ," // 31
            + "TypeofCall text ," // 32
            + "CallDateTime text ," // 33
            + "CheckPoint text ," // 34
            + "CreatedBy text ," // 35
            + "CreatedOn text );"; // 36

    String tableConsultancy = "create table tblConsultancy(_id integer primary key autoincrement,"
            + "companyname text ,"// 1
            + "fullAddress text ,"// 2
            + "District text ,"// 3
            + "Pincode text ,"// 4
            + "FDAPersonMeet text ,"// 5
            + "FDAPersonMDesignation text ,"// 6
            + "FDATelephoneSTD text ,"// 7
            + "FDATelephone text ,"// 8
            + "FDAPhone text ,"// 9
            + "FDAEmail text ,"// 10
            + "Website text ,"// 11
            + "FotoVCardFront text ,"// 12
            + "FotoVCardBack text ,"// 13
            + "FotoPerson text ,"// 14
            + "AnyOtherMeet text ,"// 15
            + "Industry text ,"// 16
            + "Process text ,"// 17
            + "DealerContact text ,"// 18
            + "DealerReffered text ,"// 19
            + "TechnicalPresentation text ,"// 20
            + "CONSULTANCYconvinced text ,"// 21
            + "ReadyToRecommend text ,"// 22
            + "ProductsTheyWish text ,"// 23
            + "ReasonsForNot text ,"// 24
            + "TheyNotReadyToRecommend text ,"// 25
            + "Outcome text ,"// 26
            + "ActionCoburg text ,"// 27
            + "KeyPoints text ,"// 28
            + "UpStatus text ,"// 29
            + "CoEmployee text ,"// 30
            + "TypeOfCall text ,"// 31
            + "CallDateTime text ,"// 32
            + "CheckPoint text ," // 33
            + "CreatedBy text ,"// 34
            + "CreatedOn text );";// 35

    String tableOldConsultancy = "create table tblOldConsultancy(_id integer primary key autoincrement,"
            + "companyname text ,"// 1
            + "fullAddress text ,"// 2
            + "District text ,"// 3
            + "Pincode text ,"// 4
            + "FDATelephoneSTD text ,"// 5
            + "FDAPhone text ,"// 6
            + "FDATelephone text ,"// 7
            + "FDAEmail text ,"// 8
            + "Website text ,"// 9
            + "FotoVCardFront text ,"// 10
            + "FotoVCardBack text ,"// 11
            + "FotoPerson text ,"// 12
            + "AnyOtherMeet text ,"// 13
            + "VisitType text ,"// 14
            + "KeyPoints text ,"// 15
            + "ActionCoburg text ,"// 16
            + "UpStatus text ,"// 17
            + "CoEmployee text ,"// 18
            + "TypeOfCall text ,"// 19
            + "CallDateTime text ,"// 20
            + "CheckPoint text ," // 21
            + "CreatedBy text ,"// 22
            + "CreatedOn text );";// 23

    String tableOEM = "create table tblOEM(_id integer primary key autoincrement,"
            + "companyname text ,"// 1
            + "fullAddress text ,"// 2
            + "District text ,"// 3
            + "Pincode text ,"// 4
            + "FDAPersonMeet text ,"// 5
            + "FDAPersonMDesignation text ,"// 6
            + "FDATelephoneSTD text ,"// 7
            + "FDATelephone text ,"// 8
            + "FDAPhone text ,"// 9
            + "FDAEmail text ,"// 10
            + "Website text ,"// 11
            + "OEMType text ,"// 12
            + "FotoVCardFront text ,"// 13
            + "FotoVCardBack text ,"// 14
            + "FotoPerson text ,"// 15
            + "AnyOtherMeet text ,"// 16
            + "TechnicalPresentation text ,"// 17
            + "CustomerConvinced text ,"// 18
            + "NOT_CONVINCED text ,"// 19
            + "CONVINCED text ,"// 20
            + "PresentSupplier text ,"// 21
            + "SupplierName text ,"// 22
            + "ReplacementVendor text ,"// 23
            + "Why text ,"// 24
            + "Outcome text ,"// 25
            + "ActionCoburg text ,"// 26
            + "remarks text ,"// 27
            + "UpStatus text ,"// 28
            + "CoEmployee text ,"// 29
            + "TypeOfCall text ,"// 30
            + "CallDateTime text ,"// 31
            + "CheckPoint text ,"// 32
            + "CreatedBy text ,"// 33
            + "CreatedOn text  );";// 34

    String tableCustomerOld = "create table tblCustomerOld(_id integer primary key autoincrement,"
            + "companyname text ,"// 1
            + "fullAddress text ,"// 2
            + "districts text ,"// 3
            + "Pincode text ,"// 4
            + "FDAPhone text ,"// 5
            + "FDATelephone text ,"// 6
            + "FDAEmail text ,"// 7
            + "Website text ,"// 8
            + "FotoVCardFront text ,"// 9
            + "FotoVCardBack text ,"// 10
            + "FotoPerson text ,"// 11
            + "AnyOtherMeet text ,"// 12
            + "VisitType text ,"// 13
            + "Outcome text ,"// 14
            + "ActionCoburg text ,"// 15
            + "remarks text ,"// 16
            + "UpStatus text ,"// 17
            + "CoEmployee text ,"// 18
            + "TypeOfCall text ,"// 19
            + "CallDateTime text ,"// 20
            + "CheckPoint text ,"// 21
            + "CreatedBy text ,"// 22
            + "CreatedOn text );";// 23

    String tableXOEM = "create table tblXOEM(_id integer primary key autoincrement,"
            + "companyname text ,"// 1
            + "fullAddress text ,"// 2
            + "districts text ,"// 3
            + "Pincode text ,"// 4
            + "FDAPhone text ,"// 5
            + "FDATelephoneSTD text ,"// 6
            + "FDATelephone text ,"// 7
            + "FDAEmail text ,"// 8
            + "Website text ,"// 9
            + "FotoVCardFront text ,"// 10
            + "FotoVCardBack text ,"// 11
            + "FotoPerson text ,"// 12
            + "AnyOtherMeet text ,"// 13
            + "VisitType text ,"// 14
            + "Outcome text ,"// 15
            + "ActionCoburg text ,"// 16
            + "remarks text ,"// 17
            + "UpStatus text ,"// 18
            + "CoEmployee text ,"// 19
            + "TypeOfCall text ,"// 20
            + "CallDateTime text ,"// 21
            + "CheckPoint text ,"// 22
            + "CreatedBy text ,"// 23
            + "CreatedOn text );";// 24

    String tableAddBranchInfo = "create table tblAddBranchInfo(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "City text ,"
            + "Address text ,"
            + "NAME text ,"
            + "Designation text ,"
            + "PHONE text ,"
            + "Mobile text ,"
            + "Email text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableAddBranchInfoOEM = "create table tblAddBranchInfoOEM(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "City text ,"
            + "Address text ,"
            + "NAME text ,"
            + "Designation text ,"
            + "PHONE text ,"
            + "Mobile text ,"
            + "Email text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableAddIndustryServed = "create table tblAddIndustryServed(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "INDUSTRYTYEPE text ,"
            + "PROCESSTYPE text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableAddOEMIndustryInfo = "create table tblAddOEMIndustryInfo(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "INDUSTRYTYEPE text ,"
            + "PROCESSTYPE text ,"
            + "MACHINENAME text ,"
            + "BRANDNAME text ,"
            + "MODEL text ,"
            + "COST text ,"
            + "ESTSALESYEAR text ,"
            + "REGIONSOLD text ," + "ProjectType text ," + "Status text );";

    String tableAddExistingBusinessProducts = "create table tblAddExistingBusinessProducts(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "PRODUCT text ,"
            + "BRAND text ,"
            + "PRINCIPAL text ,"
            + "DEALERSINCE text ,"
            + "OPERATIONAREA text ,"
            + "UNITSYEAR text ,"
            + "SALESYEAR text ,"
            + "MARGINS text ," + "ProjectType text ," + "Status text );";

    String tableAddKeyProject = "create table tblAddKeyProject(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "PROJECTNAME text ,"
            + "YEARCOMPLETED text ,"
            + "VALUE text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableAddProductsRequired = "create table tblAddProductsRequired(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "INDUSTRYNAME text ,"
            + "PROCESS text ,"
            + "APPLICATION text ,"
            + "PRODUCTREQ text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableAddPresentProject = "create table tblAddPresentProject(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "PROJECTNAME text ,"
            + "INDUSTRY text ,"
            + "APPLICATION text ,"
            + "PRODUCTREQ text ,"
            + "ProjectType text ,"
            + "Status text );";

    String tableAddBuyingFromUs = "create table tblAddBuyingFromUs(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "PRODUCT text ,"
            + "QTY text ,"
            + "FINALPRICE text ,"
            + "DELIVERY text ,"
            + "FREIGHT text ,"
            + "PAYMENT text ," + "ProjectType text ," + "Status text );";

    String tableAddGivingReferences = "create table tblAddGivingReferences(_id integer primary key autoincrement,"
            + "Projectid text ,"
            + "NAME text ,"
            + "ADDRESS text ,"
            + "CITY text ,"
            + "CONTACTPERSON text ,"
            + "DESIGNATION text ,"
            + "PHONE text ,"
            + "MOBILENO text ,"
            + "EMAIL text ,"
            + "REQUIRED_CAPACITY text ,"
            + "REQUIREMENT_TIME text ,"
            + "ProjectType text ," + "Status text );";

    String tableIndustry = "create table tblAddIndustry(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableProcess = "create table tblProcess(_id integer primary key autoincrement,"
            + "ID text ,"
            + "DESCRIPTION text ,"
            + "mastertypem_id text ,"
            + "PARENT_MASTERM_ID text );";

    String tableVisitRef = "create table tblVisitRef(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableProjectStatus = "create table tblProjectStatus(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableKeyPoints = "create table tblKeyPoints(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOutcomeProspect = "create table tblOutcomeProspect(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOutcomeProspectOR = "create table tblOutcomeProspectOR(_id integer primary key autoincrement,"
            + "ID text ,"
            + "DESCRIPTION text ,"
            + "mastertypem_id text ,"
            + "PARENT_MASTERM_ID text );";

    String tableProspectByCoburg = "create table tblProspectByCoburg(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableTypeofCall = "create table tblTypeofCall(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOutcomeDealer = "create table tblOutcomeDealer(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableByCoburgDealer = "create table tblByCoburgDealer(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOutcomeConsultancy = "create table tblOutcomeConsultancy(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableByCoburgConsultancy = "create table tblByCoburgConsultancy(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOEMType = "create table tblOEMType(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOutcomeOEM = "create table tblOutcomeOEM(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOEMByCob = "create table tblOEMByCob(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOEMConvinced = "create table tblOEMConvinced(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableVisitType = "create table tblVisitType(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableOutcomeCustomer = "create table tblOutcomeCustomer(_id integer primary key autoincrement,"
            + "ID text ,"
            + "DESCRIPTION text ,"
            + "mastertypem_id text ,"
            + "PARENT_MASTERM_ID text );";

    String tableCustomerByCob = "create table tblCustomerByCob(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableVisitTypeXConsult = "create table tblVisitTypeXConsult(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    String tableVisitTypeXOEM = "create table tblVisitTypeXOEM(_id integer primary key autoincrement,"
            + "ID text ," + "DESCRIPTION text ," + "mastertypem_id text );";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(table_create_staff);
        db.execSQL(table_create_register);
        db.execSQL(table_create_pin);
        db.execSQL(table_create_flag);
        db.execSQL(tableEmployee);
        db.execSQL(tableCompany);
        db.execSQL(tableAddMore);
        db.execSQL(tableProduct);
        db.execSQL(tableProductLookup);
        db.execSQL(tableProspect);
        db.execSQL(tableXProspect);
        db.execSQL(tableAddBranchInfo);
        db.execSQL(tableAddBranchInfoOEM);
        db.execSQL(tableAddIndustryServed);
        db.execSQL(tableAddExistingBusinessProducts);
        db.execSQL(tableAddKeyProject);
        db.execSQL(tableAddProductsRequired);
        db.execSQL(tableAddPresentProject);
        db.execSQL(tableAddOEMIndustryInfo);
        db.execSQL(tableAddBuyingFromUs);
        db.execSQL(tableAddGivingReferences);
        db.execSQL(tableDistrict);
        db.execSQL(tableIndustry);
        db.execSQL(tableProcess);
        db.execSQL(tableVisitRef);
        db.execSQL(tableProjectStatus);
        db.execSQL(tableKeyPoints);
        db.execSQL(tableOutcomeProspect);
        db.execSQL(tableOutcomeProspectOR);
        db.execSQL(tableProspectByCoburg);
        db.execSQL(tableTypeofCall);
        db.execSQL(tableOutcomeDealer);
        db.execSQL(tableByCoburgDealer);
        db.execSQL(tableDealer);
        db.execSQL(tableOutcomeConsultancy);
        db.execSQL(tableByCoburgConsultancy);
        db.execSQL(tableConsultancy);
        db.execSQL(tableOldConsultancy);
        db.execSQL(tableOEMType);
        db.execSQL(tableOutcomeOEM);
        db.execSQL(tableOEMByCob);
        db.execSQL(tableOEM);
        db.execSQL(tableOEMConvinced);
        db.execSQL(tableVisitType);
        db.execSQL(tableOutcomeCustomer);
        db.execSQL(tableCustomerByCob);
        db.execSQL(tableCustomerOld);
        db.execSQL(tableVisitTypeXConsult);
        db.execSQL(tableVisitTypeXOEM);
        db.execSQL(tableXOEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        Log.w(SQLiteHelper.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS stafflogin");
        db.execSQL("DROP TABLE IF EXISTS register");
        db.execSQL("DROP TABLE IF EXISTS tblpin");
        db.execSQL("DROP TABLE IF EXISTS tblflag");
        db.execSQL("DROP TABLE IF EXISTS tblEmployee");
        db.execSQL("DROP TABLE IF EXISTS tblCompany");
        db.execSQL("DROP TABLE IF EXISTS tblAddMorePerson");
        db.execSQL("DROP TABLE IF EXISTS tblProductLookup");
        db.execSQL("DROP TABLE IF EXISTS tblProduct");
        db.execSQL("DROP TABLE IF EXISTS tblProspect");
        db.execSQL("DROP TABLE IF EXISTS tblXProspect");
        db.execSQL("DROP TABLE IF EXISTS tblAddBranchInfo");
        db.execSQL("DROP TABLE IF EXISTS tblAddBranchInfoOEM");
        db.execSQL("DROP TABLE IF EXISTS tblAddIndustryServed");
        db.execSQL("DROP TABLE IF EXISTS tblAddExistingBusinessProducts");
        db.execSQL("DROP TABLE IF EXISTS tblAddKeyProject");
        db.execSQL("DROP TABLE IF EXISTS tblAddProductsRequired");
        db.execSQL("DROP TABLE IF EXISTS tblAddPresentProject");
        db.execSQL("DROP TABLE IF EXISTS tblAddOEMIndustryInfo");
        db.execSQL("DROP TABLE IF EXISTS tblAddBuyingFromUs");
        db.execSQL("DROP TABLE IF EXISTS tblAddGivingReferences");
        db.execSQL("DROP TABLE IF EXISTS tblDistrict");
        db.execSQL("DROP TABLE IF EXISTS tblAddIndustry");
        db.execSQL("DROP TABLE IF EXISTS tblProcess");
        db.execSQL("DROP TABLE IF EXISTS tblVisitRef");
        db.execSQL("DROP TABLE IF EXISTS tblProjectStatus");
        db.execSQL("DROP TABLE IF EXISTS tblKeyPoints");
        db.execSQL("DROP TABLE IF EXISTS tblOutcomeProspect");
        db.execSQL("DROP TABLE IF EXISTS tblOutcomeProspectOR");
        db.execSQL("DROP TABLE IF EXISTS tblProspectByCoburg");
        db.execSQL("DROP TABLE IF EXISTS tblTypeofCall");
        db.execSQL("DROP TABLE IF EXISTS tblOutcomeDealer");
        db.execSQL("DROP TABLE IF EXISTS tblByCoburgDealer");
        db.execSQL("DROP TABLE IF EXISTS tblDealer");
        db.execSQL("DROP TABLE IF EXISTS tblOutcomeConsultancy");
        db.execSQL("DROP TABLE IF EXISTS tblByCoburgConsultancy");
        db.execSQL("DROP TABLE IF EXISTS tblConsultancy");
        db.execSQL("DROP TABLE IF EXISTS tblOldConsultancy");
        db.execSQL("DROP TABLE IF EXISTS tblOEMType");
        db.execSQL("DROP TABLE IF EXISTS tblOutcomeOEM");
        db.execSQL("DROP TABLE IF EXISTS tblOEMByCob");
        db.execSQL("DROP TABLE IF EXISTS tblOEM");
        db.execSQL("DROP TABLE IF EXISTS tblOEMConvinced");
        db.execSQL("DROP TABLE IF EXISTS tblVisitType");
        db.execSQL("DROP TABLE IF EXISTS tblOutcomeCustomer");
        db.execSQL("DROP TABLE IF EXISTS tblCustomerByCob");
        db.execSQL("DROP TABLE IF EXISTS tblCustomerOld");
        db.execSQL("DROP TABLE IF EXISTS tblVisitTypeXConsult");
        db.execSQL("DROP TABLE IF EXISTS tblVisitTypeXOEM");
        db.execSQL("DROP TABLE IF EXISTS tblXOEM");
        onCreate(db);

    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public long insertProspect(String val1, String val2, String val3,
                               String val4, String val5, String val6, String val7, String val8,
                               String val9, String val10, String val11, String val12,
                               String val13, String val14, String val15, String val16,
                               String val17, String val18, String val19, String val20,
                               String val21, String val22, String val23, String val24,
                               String val25, String val26, String val27, String val28,
                               String val29, String val30, String val31, String val32,
                               String val33, String val34, String val35, String val36,
                               String val37, String val38, String val39, String val40,
                               String val41, String val42, String val43, String val44,
                               String val45, String val46, String val47, String val48,
                               String val49, String val50, String val51, String val52, String val53, String val54) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("CompanyName", val1);
        initialValues.put("FullAddress", val2);
        initialValues.put("District", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPersonName", val5);
        initialValues.put("FDAPersonDesignation", val6);
        initialValues.put("PersonMeet", val7);
        initialValues.put("Designation", val8);
        initialValues.put("TeleNoSTD", val9);
        initialValues.put("TeleNo", val10);
        initialValues.put("PhoneNo", val11);
        initialValues.put("Email", val12);
        initialValues.put("WebSite", val13);
        initialValues.put("FotoVCardFront", val14);
        initialValues.put("FotoVCardBack", val15);
        initialValues.put("FotoPerson", val16);
        initialValues.put("AnyPersonMet", val17);
        initialValues.put("VisitReference", val18);
        initialValues.put("Industry", val19);
        initialValues.put("Process", val20);
        initialValues.put("EndProductMfrd", val21);
        initialValues.put("ProductClassify", val22);
        initialValues.put("WriteUp", val23);
        initialValues.put("ProjectStatus", val24);
        initialValues.put("ProjectCompleteBy", val25);
        initialValues.put("ProjectDeliveyBefore", val26);
        initialValues.put("DtlsTech", val27);
        initialValues.put("CustomerTech", val28);
        initialValues.put("CLP", val29);
        initialValues.put("Offered", val30);
        initialValues.put("OfferedHowMuch", val31);
        initialValues.put("ScopeofSupply", val32);
        initialValues.put("TechFeatures", val33);
        initialValues.put("Quality", val34);
        initialValues.put("ServiceSupport", val35);
        initialValues.put("DeliverySchedule", val36);
        initialValues.put("ModeSale", val37);
        initialValues.put("Price", val38);
        initialValues.put("Payment", val39);
        initialValues.put("Warranty", val40);
        initialValues.put("Other", val41);
        initialValues.put("OutCome", val42);
        initialValues.put("OrderReceived", val43);
        initialValues.put("ActionBy", val44);
        initialValues.put("Remarks", val45);
        initialValues.put("UpStatus", val46);
        initialValues.put("CoEmployeeID", val47);
        initialValues.put("TypeofCall", val48);
        initialValues.put("CallDateTime", val49);
        initialValues.put("CheckPoint", val50);
        initialValues.put("CreatedBy", val51);
        initialValues.put("CreatedOn", val52);
        initialValues.put("latitude", val53);
        initialValues.put("longitude", val54);
        long l = db.insert("tblProspect", null, initialValues);
        db.close();
        return l;
    }

    public long insertXProspect(String val1, String val2, String val3,
                                String val4, String val5, String val6, String val7, String val8,
                                String val9, String val10, String val11, String val12,
                                String val13, String val14, String val15, String val16,
                                String val17, String val18, String val19, String val20,
                                String val21, String val22, String val23, String val24,
                                String val25, String val26, String val27, String val28,
                                String val29, String val30, String val31, String val32,
                                String val33, String val34, String val35, String val36,
                                String val37, String val38, String val39, String val40,
                                String val41, String val42, String val43, String val44, String val45) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("CompanyName", val1);
        initialValues.put("FullAddress", val2);
        initialValues.put("District", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPersonName", val5);
        initialValues.put("FDAPersonDesignation", val6);
        initialValues.put("PersonMeet", val7);
        initialValues.put("Designation", val8);
        initialValues.put("TeleNoSTD", val9);
        initialValues.put("TeleNo", val10);
        initialValues.put("PhoneNo", val11);
        initialValues.put("Email", val12);
        initialValues.put("WebSite", val13);
        initialValues.put("FotoVCardFront", val14);
        initialValues.put("FotoVCardBack", val15);
        initialValues.put("FotoPerson", val16);
        initialValues.put("AnyPersonMet", val17);
        initialValues.put("VisitReference", val18);
        initialValues.put("ProjectStatus", val19);
        initialValues.put("ProjectCompleteBy", val20);
        initialValues.put("ProjectDeliveyBefore", val21);
        initialValues.put("CLP", val22);
        initialValues.put("Offered", val23);
        initialValues.put("OfferedHowMuch", val24);
        initialValues.put("ScopeofSupply", val25);
        initialValues.put("TechFeatures", val26);
        initialValues.put("Quality", val27);
        initialValues.put("ServiceSupport", val28);
        initialValues.put("DeliverySchedule", val29);
        initialValues.put("ModeSale", val30);
        initialValues.put("Price", val31);
        initialValues.put("Payment", val32);
        initialValues.put("Warranty", val33);
        initialValues.put("Other", val34);
        initialValues.put("OutCome", val35);
        initialValues.put("OrderReceived", val36);
        initialValues.put("ActionBy", val37);
        initialValues.put("Remarks", val38);
        initialValues.put("UpStatus", val39);
        initialValues.put("CoEmployeeID", val40);
        initialValues.put("TypeofCall", val41);
        initialValues.put("CallDateTime", val42);
        initialValues.put("CheckPoint", val43);
        initialValues.put("CreatedBy", val44);
        initialValues.put("CreatedOn", val45);
        long l = db.insert("tblXProspect", null, initialValues);
        db.close();
        return l;
    }

    public long insertDealer(String val1, String val2, String val3,
                             String val4, String val5, String val6, String val7, String val8,
                             String val9, String val10, String val11, String val12,
                             String val13, String val14, String val15, String val16,
                             String val17, String val18, String val19, String val20,
                             String val21, String val22, String val23, String val24,
                             String val25, String val26, String val27, String val28,
                             String val29, String val30, String val31, String val32,
                             String val33, String val34, String val35, String val36) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("companyname", val1);
        initialValues.put("fullAddress", val2);
        initialValues.put("District", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPersonMeet", val5);
        initialValues.put("FDAPersonMDesignation", val6);
        initialValues.put("FDATelephoneSTD", val7);
        initialValues.put("FDATelephone", val8);
        initialValues.put("FDAPhone", val9);
        initialValues.put("FDAEmail", val10);
        initialValues.put("Website", val11);
        initialValues.put("FotoVCardFront", val12);
        initialValues.put("FotoVCardBack", val13);
        initialValues.put("FotoPerson", val14);
        initialValues.put("FDAName", val15);
        initialValues.put("FDADesignation", val16);
        initialValues.put("AnyOtherMeet", val17);
        initialValues.put("DealerContact", val18);
        initialValues.put("DealerReffered", val19);
        initialValues.put("SeekingInterest", val20);
        initialValues.put("InterestProduct", val21);
        initialValues.put("InterestedIn", val22);
        initialValues.put("AfterSale", val23);
        initialValues.put("DealerShip", val24);
        initialValues.put("AreaRegion", val25);
        initialValues.put("WriteUp", val26);
        initialValues.put("Outcome", val27);
        initialValues.put("ActionCoburg", val28);
        initialValues.put("remarks", val29);
        initialValues.put("UpStatus", val30);
        initialValues.put("CoEmployeeID", val31);
        initialValues.put("TypeofCall", val32);
        initialValues.put("CallDateTime", val33);
        initialValues.put("CheckPoint", val34);
        initialValues.put("CreatedBy", val35);
        initialValues.put("CreatedOn", val36);
        long l = db.insert("tblDealer", null, initialValues);
        db.close();
        return l;
    }

    public long insertConsultancy(String val1, String val2, String val3,
                                  String val4, String val5, String val6, String val7, String val8,
                                  String val9, String val10, String val11, String val12,
                                  String val13, String val14, String val15, String val16,
                                  String val17, String val18, String val19, String val20,
                                  String val21, String val22, String val23, String val24,
                                  String val25, String val26, String val27, String val28,
                                  String val29, String val30, String val31, String val32,
                                  String val33, String val34, String val35) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("companyname", val1);
        initialValues.put("fullAddress", val2);
        initialValues.put("District", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPersonMeet", val5);
        initialValues.put("FDAPersonMDesignation", val6);
        initialValues.put("FDATelephoneSTD", val7);
        initialValues.put("FDATelephone", val8);
        initialValues.put("FDAPhone", val9);
        initialValues.put("FDAEmail", val10);
        initialValues.put("Website", val11);
        initialValues.put("FotoVCardFront", val12);
        initialValues.put("FotoVCardBack", val13);
        initialValues.put("FotoPerson", val14);
        initialValues.put("AnyOtherMeet", val15);
        initialValues.put("Industry", val16);
        initialValues.put("Process", val17);
        initialValues.put("DealerContact", val18);
        initialValues.put("DealerReffered", val19);
        initialValues.put("TechnicalPresentation", val20);
        initialValues.put("CONSULTANCYconvinced", val21);
        initialValues.put("ReadyToRecommend", val22);
        initialValues.put("ProductsTheyWish", val23);
        initialValues.put("ReasonsForNot", val24);
        initialValues.put("TheyNotReadyToRecommend", val25);
        initialValues.put("Outcome", val26);
        initialValues.put("ActionCoburg", val27);
        initialValues.put("KeyPoints", val28);
        initialValues.put("UpStatus", val29);
        initialValues.put("CoEmployee", val30);
        initialValues.put("TypeOfCall", val31);
        initialValues.put("CallDateTime", val32);
        initialValues.put("CheckPoint", val33);
        initialValues.put("CreatedBy", val34);
        initialValues.put("CreatedOn", val35);
        long l = db.insert("tblConsultancy", null, initialValues);
        db.close();
        return l;
    }

    public long insertOldConsultancy(String val1, String val2, String val3,
                                     String val4, String val5, String val6, String val7, String val8,
                                     String val9, String val10, String val11, String val12,
                                     String val13, String val14, String val15, String val16,
                                     String val17, String val18, String val19, String val20,
                                     String val21, String val22, String val23) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("companyname", val1);
        initialValues.put("fullAddress", val2);
        initialValues.put("District", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDATelephoneSTD", val5);
        initialValues.put("FDATelephone", val6);
        initialValues.put("FDAPhone", val7);
        initialValues.put("FDAEmail", val8);
        initialValues.put("Website", val9);
        initialValues.put("FotoVCardFront", val10);
        initialValues.put("FotoVCardBack", val11);
        initialValues.put("FotoPerson", val12);
        initialValues.put("AnyOtherMeet", val13);
        initialValues.put("VisitType", val14);
        initialValues.put("KeyPoints", val15);
        initialValues.put("ActionCoburg", val16);
        initialValues.put("UpStatus", val17);
        initialValues.put("CoEmployee", val18);
        initialValues.put("TypeOfCall", val19);
        initialValues.put("CallDateTime", val20);
        initialValues.put("CheckPoint", val21);
        initialValues.put("CreatedBy", val22);
        initialValues.put("CreatedOn", val23);
        long l = db.insert("tblOldConsultancy", null, initialValues);
        db.close();
        return l;
    }

    public long insertOEM(String val1, String val2, String val3, String val4,
                          String val5, String val6, String val7, String val8, String val9,
                          String val10, String val11, String val12, String val13,
                          String val14, String val15, String val16, String val17,
                          String val18, String val19, String val20, String val21,
                          String val22, String val23, String val24, String val25,
                          String val26, String val27, String val28, String val29,
                          String val30, String val31, String val32, String val33,
                          String val34) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("companyname", val1);
        initialValues.put("fullAddress", val2);
        initialValues.put("District", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPersonMeet", val5);
        initialValues.put("FDAPersonMDesignation", val6);
        initialValues.put("FDATelephoneSTD", val7);
        initialValues.put("FDATelephone", val8);
        initialValues.put("FDAPhone", val9);
        initialValues.put("FDAEmail", val10);
        initialValues.put("Website", val11);
        initialValues.put("OEMType", val12);
        initialValues.put("FotoVCardFront", val13);
        initialValues.put("FotoVCardBack", val14);
        initialValues.put("FotoPerson", val15);
        initialValues.put("AnyOtherMeet", val16);
        initialValues.put("TechnicalPresentation", val17);
        initialValues.put("CustomerConvinced", val18);
        initialValues.put("NOT_CONVINCED", val19);
        initialValues.put("CONVINCED", val20);
        initialValues.put("PresentSupplier", val21);
        initialValues.put("SupplierName", val22);
        initialValues.put("ReplacementVendor", val23);
        initialValues.put("Why", val24);
        initialValues.put("Outcome", val25);
        initialValues.put("ActionCoburg", val26);
        initialValues.put("remarks", val27);
        initialValues.put("UpStatus", val28);
        initialValues.put("CoEmployee", val29);
        initialValues.put("TypeOfCall", val30);
        initialValues.put("CallDateTime", val31);
        initialValues.put("CheckPoint", val32);
        initialValues.put("CreatedBy", val33);
        initialValues.put("CreatedOn", val34);
        long l = db.insert("tblOEM", null, initialValues);
        db.close();
        return l;
    }

    public long insertCustomerOld(String val1, String val2, String val3,
                                  String val4, String val5, String val6, String val7, String val8,
                                  String val9, String val10, String val11, String val12,
                                  String val13, String val14, String val15, String val16,
                                  String val17, String val18, String val19, String val20,
                                  String val21, String val22, String val23) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("companyname", val1);
        initialValues.put("fullAddress", val2);
        initialValues.put("districts", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPhone", val5);
        initialValues.put("FDATelephone", val6);
        initialValues.put("FDAEmail", val7);
        initialValues.put("Website", val8);
        initialValues.put("FotoVCardFront", val9);
        initialValues.put("FotoVCardBack", val10);
        initialValues.put("FotoPerson", val11);
        initialValues.put("AnyOtherMeet", val12);
        initialValues.put("VisitType", val13);
        initialValues.put("Outcome", val14);
        initialValues.put("ActionCoburg", val15);
        initialValues.put("remarks", val16);
        initialValues.put("UpStatus", val17);
        initialValues.put("CoEmployee", val18);
        initialValues.put("TypeOfCall", val19);
        initialValues.put("CallDateTime", val20);
        initialValues.put("CheckPoint", val21);
        initialValues.put("CreatedBy", val22);
        initialValues.put("CreatedOn", val23);
        long l = db.insert("tblCustomerOld", null, initialValues);
        db.close();
        return l;
    }

    public long insertXOEM(String val1, String val2, String val3,
                           String val4, String val5, String val6, String val7, String val8,
                           String val9, String val10, String val11, String val12,
                           String val13, String val14, String val15, String val16,
                           String val17, String val18, String val19, String val20,
                           String val21, String val22, String val23, String val24) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("companyname", val1);
        initialValues.put("fullAddress", val2);
        initialValues.put("districts", val3);
        initialValues.put("Pincode", val4);
        initialValues.put("FDAPhone", val5);
        initialValues.put("FDATelephoneSTD", val6);
        initialValues.put("FDATelephone", val7);
        initialValues.put("FDAEmail", val8);
        initialValues.put("Website", val9);
        initialValues.put("FotoVCardFront", val10);
        initialValues.put("FotoVCardBack", val11);
        initialValues.put("FotoPerson", val12);
        initialValues.put("AnyOtherMeet", val13);
        initialValues.put("VisitType", val14);
        initialValues.put("Outcome", val15);
        initialValues.put("ActionCoburg", val16);
        initialValues.put("remarks", val17);
        initialValues.put("UpStatus", val18);
        initialValues.put("CoEmployee", val19);
        initialValues.put("TypeOfCall", val20);
        initialValues.put("CallDateTime", val21);
        initialValues.put("CheckPoint", val22);
        initialValues.put("CreatedBy", val23);
        initialValues.put("CreatedOn", val24);
        long l = db.insert("tblXOEM", null, initialValues);
        db.close();
        return l;
    }

    public Cursor getOutcomeDealerID(String Outcome) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeDealer where DESCRIPTION='"
                + Outcome + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOutcomeCustomerID(String Outcome) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeCustomer where DESCRIPTION='"
                + Outcome + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOutcomeConsultancyID(String Outcome) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeConsultancy where DESCRIPTION='"
                + Outcome + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOutcomeOEMID(String Outcome) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeOEM where DESCRIPTION='"
                + Outcome + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getDealerByCoburgID(String ProspectByCoburg)
            throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblByCoburgDealer where DESCRIPTION='"
                + ProspectByCoburg + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getCustomerByCob(String ByCoburg)
            throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblCustomerByCob where DESCRIPTION='"
                + ByCoburg + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getByCoburgConsultancyID(String ProspectByCoburg)
            throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblByCoburgConsultancy where DESCRIPTION='"
                + ProspectByCoburg + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getByCoburgOEMID(String ProspectByCoburg) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOEMByCob where DESCRIPTION='"
                + ProspectByCoburg + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long login_insert(String val1, String val2, String val3, String val4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("staffid", val1);
        initialValues.put("staffname", val2);
        initialValues.put("username", val3);
        initialValues.put("userpassword", val4);
        long l = db.insert("stafflogin", null, initialValues);
        db.close();
        return l;

    }

    public long insertIndustry(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblAddIndustry", null, initialValues);
        db.close();
        return l;
    }

    public long insertOutcomeOEM(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOutcomeOEM", null, initialValues);
        db.close();
        return l;
    }

    public long insertOEMType(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOEMType", null, initialValues);
        db.close();
        return l;
    }

    public long insertTypeOfCall(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblTypeofCall", null, initialValues);
        db.close();
        return l;
    }

    public long insertCustomerByCob(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblCustomerByCob", null, initialValues);
        db.close();
        return l;
    }

    public long insertProcess(String val1, String val2, String val3, String val4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        initialValues.put("PARENT_MASTERM_ID", val4);
        long l = db.insert("tblProcess", null, initialValues);
        db.close();
        return l;
    }

    public long insertOutcomeCustomer(String val1, String val2, String val3,
                                      String val4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        initialValues.put("PARENT_MASTERM_ID", val4);
        long l = db.insert("tblOutcomeCustomer", null, initialValues);
        db.close();
        return l;
    }

    public long insertVisitRef(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblVisitRef", null, initialValues);
        db.close();
        return l;
    }

    public long insertOutcomeDealer(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOutcomeDealer", null, initialValues);
        db.close();
        return l;
    }

    public long insertOutcomeConsultancy(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOutcomeConsultancy", null, initialValues);
        db.close();
        return l;
    }

    public long insertByCoburgConsultancy(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblByCoburgConsultancy", null, initialValues);
        db.close();
        return l;
    }

    public long insertOEMByCob(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOEMByCob", null, initialValues);
        db.close();
        return l;
    }

    public long insertByCoburgDealer(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblByCoburgDealer", null, initialValues);
        db.close();
        return l;
    }

    public long insertVisitType(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblVisitType", null, initialValues);
        db.close();
        return l;
    }

    public long insertVisitTypeXConsult(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblVisitTypeXConsult", null, initialValues);
        db.close();
        return l;
    }

    public long insertVisitTypeXOEM(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblVisitTypeXOEM", null, initialValues);
        db.close();
        return l;
    }

    public long insertOEMConvinced(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOEMConvinced", null, initialValues);
        db.close();
        return l;
    }

    public long insertProjectStatus(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblProjectStatus", null, initialValues);
        db.close();
        return l;

    }

    public long insertKeyPoints(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblKeyPoints", null, initialValues);
        db.close();
        return l;
    }

    public long insertOutcomeProspect(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblOutcomeProspect", null, initialValues);
        db.close();
        return l;
    }

    public long insertOutcomeProspectOR(String val1, String val2, String val3,
                                        String val4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        initialValues.put("PARENT_MASTERM_ID", val4);
        long l = db.insert("tblOutcomeProspectOR", null, initialValues);
        db.close();
        return l;
    }

    public long insertProspectByCoburg(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("mastertypem_id", val3);
        long l = db.insert("tblProspectByCoburg", null, initialValues);
        db.close();
        return l;
    }

    public long insertDistrict(String val1, String val2, String val3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("ID", val1);
        initialValues.put("DESCRIPTION", val2);
        initialValues.put("PARENT_MASTERM_ID", val3);
        long l = db.insert("tblDistrict", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectDistrict() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,PARENT_MASTERM_ID from tblDistrict;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeOEM() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOutcomeOEM;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOEMByCob() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOEMByCob;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeDealer() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOutcomeDealer;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOEMConvinced() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOEMConvinced;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOEMType() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOEMType;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeConsultancy() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOutcomeConsultancy;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectByCoburgConsultancy() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblByCoburgConsultancy;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectByCoburgDealer() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblByCoburgDealer;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCustomerByCob() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblCustomerByCob;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectTypeOfCall() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblTypeofCall;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getTypeOfCallID(String TypeOfCall) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblTypeofCall where DESCRIPTION='"
                + TypeOfCall + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getDistrictID(String District) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblDistrict where DESCRIPTION='"
                + District + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOEMTypeID(String District) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOEMType where DESCRIPTION='"
                + District + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProspect() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from tblProspect;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectXProspect() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from tblXProspect;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectDealer() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from tblDealer;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectConsultancy() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from tblConsultancy;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOEM() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select * from tblOEM;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectIndustry() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblAddIndustry;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getIndustryID(String Industry) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblAddIndustry where DESCRIPTION='"
                + Industry + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectVisitRef() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblVisitRef;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectVisitType() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblVisitType;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectVisitTypeXConsult() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblVisitTypeXConsult;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectVisitTypeXOEM() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblVisitTypeXOEM;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getVisitRefID(String VisitRef) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitRef where DESCRIPTION='"
                + VisitRef + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getVisitTypeID(String VisitType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitType where DESCRIPTION='"
                + VisitType + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getVisitTypeIDXConsult(String VisitType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitTypeXConsult where DESCRIPTION='"
                + VisitType + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getVisitTypeIDXOEM(String VisitType) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitTypeXOEM where DESCRIPTION='"
                + VisitType + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOEMConvincedID(String VisitRef) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOEMConvinced where DESCRIPTION='"
                + VisitRef + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProjectStatus() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblProjectStatus;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getProjectStatusID(String ProjectStatus) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblProjectStatus where DESCRIPTION='"
                + ProjectStatus + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectKeyPoints() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblKeyPoints;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeProspect() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblOutcomeProspect;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOutcomeProspectID(String Outcome) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeProspect where DESCRIPTION='"
                + Outcome + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeProspectOR() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id,PARENT_MASTERM_ID from tblOutcomeProspectOR;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeCustomer() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id,PARENT_MASTERM_ID from tblOutcomeCustomer;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProspectByCoburg() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,DESCRIPTION,mastertypem_id from tblProspectByCoburg;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getProspectByCoburgID(String ProspectByCoburg)
            throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblProspectByCoburg where DESCRIPTION='"
                + ProspectByCoburg + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long pin_insert(String val1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("pin", val1);
        long l = db.insert("tblpin", null, initialValues);
        db.close();
        return l;
    }

    public long flag_insert(String val1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("flag", val1);
        long l = db.insert("tblflag", null, initialValues);
        db.close();
        return l;
    }


    public long inserProductLookup(String val1, String val2, String val3,
                                   String val4, String val5, String val6, String val7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("PRINCIPAL", val1);
        initialValues.put("ID", val2);
        initialValues.put("PARTNO", val3);
        initialValues.put("SHORTDESCRIPTION", val4);
        initialValues.put("PRODUCTTYPE", val5);
        initialValues.put("EQUIPMENTTYPE", val6);
        initialValues.put("UOM", val7);
        long l = db.insert("tblProductLookup", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectProductLookup() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PRINCIPAL,ID,PARTNO,SHORTDESCRIPTION,PRODUCTTYPE,EQUIPMENTTYPE,UOM from tblProductLookup;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectPartno(String Principal) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PARTNO from tblProductLookup where PRINCIPAL='"
                + Principal + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectProcess(String Process) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID,DESCRIPTION from tblProcess where PARENT_MASTERM_ID='"
                + Process + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOutcomeCustomerBasedVType(String Process)
            throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID,DESCRIPTION from tblOutcomeCustomer where PARENT_MASTERM_ID='"
                + Process + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getProcessID(String ProcessID) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblProcess where DESCRIPTION='"
                + ProcessID + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectOrderReceived(String OrderReceived) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID,DESCRIPTION from tblOutcomeProspectOR where PARENT_MASTERM_ID='"
                + OrderReceived + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getOrderReceivedID(String OrderReceived) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeProspectOR where DESCRIPTION='"
                + OrderReceived + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor IndustryID(String Industry) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblAddIndustry where DESCRIPTION='"
                + Industry + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor VisitTypeID(String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitType where DESCRIPTION='" + Type
                + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor VisitTypeIDXConsult(String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitTypeXConsult where DESCRIPTION='" + Type
                + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor VisitTypeIDXOEM(String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblVisitTypeXOEM where DESCRIPTION='" + Type
                + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor OutcomeID(String Outcome) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select ID from tblOutcomeProspect where DESCRIPTION='"
                + Outcome + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectShortDesc(String Part) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PARTNO,SHORTDESCRIPTION,PRODUCTTYPE,UOM,EQUIPMENTTYPE,ID from tblProductLookup where PARTNO='"
                + Part + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long inserProduct(String val1, String val2, String val3,
                             String val4, String val5, String val6, String val7, String val8,
                             String val9, String val10, String val11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("PRINCIPAL", val1);
        initialValues.put("ID", val2);
        initialValues.put("PARTNO", val3);
        initialValues.put("SHORTDESCRIPTION", val4);
        initialValues.put("PRODUCTTYPE", val5);
        initialValues.put("EQUIPMENTTYPE", val6);
        initialValues.put("UOM", val7);
        initialValues.put("Projectid", val8);
        initialValues.put("Quantity", val9);
        initialValues.put("CompanyName", val10);
        initialValues.put("Status", val11);
        long l = db.insert("tblProduct", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectProsProductForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,ID,UOM,Quantity from tblProduct where CompanyName ='" + companyName + "' and Projectid = '" + Type + "' and Status='N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    public Cursor selectProduct() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PRINCIPAL,ID,PARTNO,SHORTDESCRIPTION,PRODUCTTYPE,EQUIPMENTTYPE,UOM,Projectid,Quantity from tblProduct;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertMorePerson(String val1, String val2, String val3,
                                 String val4, String val5, String val6, String val7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("NAME", val2);
        initialValues.put("Designation", val3);
        initialValues.put("PHONE", val4);
        initialValues.put("Email", val5);
        initialValues.put("ProjectType", val6);
        initialValues.put("Status", val7);
        long l = db.insert("tblAddMorePerson", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectMorePersonForSync(String companyName, String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,Projectid,NAME,Designation,PHONE,Email,ProjectType,Status from tblAddMorePerson where Projectid ='" + companyName + "' and ProjectType = '" + Type + "' and Status='N';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectMorePerson(String Type) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,NAME,Designation,PHONE,Email from tblAddMorePerson where Projectid ='" + Type + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertBranchInfo(String val1, String val2, String val3,
                                 String val4, String val5, String val6, String val7, String val8,
                                 String val9, String val10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("City", val2);
        initialValues.put("Address", val3);
        initialValues.put("NAME", val4);
        initialValues.put("Designation", val5);
        initialValues.put("PHONE", val6);
        initialValues.put("Mobile", val7);
        initialValues.put("Email", val8);
        initialValues.put("ProjectType", val9);
        initialValues.put("Status", val10);
        long l = db.insert("tblAddBranchInfo", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectBranchInfo() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,NAME,Designation,PHONE,Email from tblAddBranchInfo;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertBranchInfoOEM(String val1, String val2, String val3,
                                    String val4, String val5, String val6, String val7, String val8,
                                    String val9, String val10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("City", val2);
        initialValues.put("Address", val3);
        initialValues.put("NAME", val4);
        initialValues.put("Designation", val5);
        initialValues.put("PHONE", val6);
        initialValues.put("Mobile", val7);
        initialValues.put("Email", val8);
        initialValues.put("ProjectType", val9);
        initialValues.put("Status", val10);
        long l = db.insert("tblAddBranchInfo", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectBranchInfoOEM() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,NAME,Designation,PHONE,Email from tblAddBranchInfo;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertIndustryServed(String val1, String val2, String val3,
                                     String val4, String val5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("INDUSTRYTYEPE", val2);
        initialValues.put("PROCESSTYPE", val3);
        initialValues.put("ProjectType", val4);
        initialValues.put("Status", val5);
        long l = db.insert("tblAddIndustryServed", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectIndustryServed() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,INDUSTRYTYEPE,PROCESSTYPE from tblAddIndustryServed;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertOEMIndustryInfo(String val1, String val2, String val3,
                                      String val4, String val5, String val6, String val7, String val8,
                                      String val9, String val10, String val11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("INDUSTRYTYEPE", val2);
        initialValues.put("PROCESSTYPE", val3);
        initialValues.put("MACHINENAME", val4);
        initialValues.put("BRANDNAME", val5);
        initialValues.put("MODEL", val6);
        initialValues.put("COST", val7);
        initialValues.put("ESTSALESYEAR", val8);
        initialValues.put("REGIONSOLD", val9);
        initialValues.put("ProjectType", val10);
        initialValues.put("Status", val11);
        long l = db.insert("tblAddOEMIndustryInfo", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectOEMIndustryInfo() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,INDUSTRYTYEPE,PROCESSTYPE,MACHINENAME,BRANDNAME from tblAddOEMIndustryInfo;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertExistingBusinessProducts(String val1, String val2,
                                               String val3, String val4, String val5, String val6, String val7,
                                               String val8, String val9, String val10, String val11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("PRODUCT", val2);
        initialValues.put("BRAND", val3);
        initialValues.put("PRINCIPAL", val4);
        initialValues.put("DEALERSINCE", val5);
        initialValues.put("OPERATIONAREA", val6);
        initialValues.put("UNITSYEAR", val7);
        initialValues.put("SALESYEAR", val8);
        initialValues.put("MARGINS", val9);
        initialValues.put("ProjectType", val10);
        initialValues.put("Status", val11);
        long l = db.insert("tblAddExistingBusinessProducts", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectExistingBusinessProducts() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PRODUCT,BRAND,PRINCIPAL,DEALERSINCE from tblAddExistingBusinessProducts;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertKeyProject(String val1, String val2, String val3,
                                 String val4, String val5, String val6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("PROJECTNAME", val2);
        initialValues.put("YEARCOMPLETED", val3);
        initialValues.put("VALUE", val4);
        initialValues.put("ProjectType", val5);
        initialValues.put("Status", val6);
        long l = db.insert("tblAddKeyProject", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectKeyProject() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PROJECTNAME,YEARCOMPLETED,VALUE from tblAddKeyProject;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertProductsRequired(String val1, String val2, String val3,
                                       String val4, String val5, String val6, String val7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("INDUSTRYNAME", val2);
        initialValues.put("PROCESS", val3);
        initialValues.put("APPLICATION", val4);
        initialValues.put("PRODUCTREQ", val5);
        initialValues.put("ProjectType", val6);
        initialValues.put("Status", val7);
        long l = db.insert("tblAddProductsRequired", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectProductsRequired() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,INDUSTRYNAME,PROCESS,APPLICATION,PRODUCTREQ from tblAddProductsRequired;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertPresentProject(String val1, String val2, String val3,
                                     String val4, String val5, String val6, String val7) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("PROJECTNAME", val2);
        initialValues.put("INDUSTRY", val3);
        initialValues.put("APPLICATION", val4);
        initialValues.put("PRODUCTREQ", val5);
        initialValues.put("ProjectType", val6);
        initialValues.put("Status", val7);
        long l = db.insert("tblAddPresentProject", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectPresentProject() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PROJECTNAME,INDUSTRY,APPLICATION,PRODUCTREQ from tblAddPresentProject;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertBuyingFromUs(String val1, String val2, String val3,
                                   String val4, String val5, String val6, String val7, String val8,
                                   String val9) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("PRODUCT", val2);
        initialValues.put("QTY", val3);
        initialValues.put("FINALPRICE", val4);
        initialValues.put("DELIVERY", val5);
        initialValues.put("FREIGHT", val6);
        initialValues.put("PAYMENT", val7);
        initialValues.put("ProjectType", val8);
        initialValues.put("Status", val9);
        long l = db.insert("tblAddBuyingFromUs", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectBuyingFromUs() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,PRODUCT,QTY,FINALPRICE,DELIVERY from tblAddBuyingFromUs;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long insertGivingReferences(String val1, String val2, String val3,
                                       String val4, String val5, String val6, String val7, String val8,
                                       String val9, String val10, String val11, String val12) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("Projectid", val1);
        initialValues.put("NAME", val2);
        initialValues.put("ADDRESS", val3);
        initialValues.put("CONTACTPERSON", val4);
        initialValues.put("DESIGNATION", val5);
        initialValues.put("PHONE", val6);
        initialValues.put("MOBILENO", val7);
        initialValues.put("EMAIL", val8);
        initialValues.put("REQUIRED_CAPACITY", val9);
        initialValues.put("REQUIREMENT_TIME", val10);
        initialValues.put("ProjectType", val1);
        initialValues.put("Status", val12);
        long l = db.insert("tblAddGivingReferences", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectGivingReferences() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select _id,NAME,CITY,CONTACTPERSON,DESIGNATION from tblAddGivingReferences;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    // //////////////////////////////////////////////////////////////////////////////////////

    public void deleteLocal() {
        String ok;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tblCompany", null, null);
        db.delete("tblEmployee", null, null);
        db.delete("tblProductLookup", null, null);
        db.delete("tblDistrict", null, null);
        db.delete("tblAddIndustry", null, null);
        db.delete("tblProcess", null, null);
        db.delete("tblVisitRef", null, null);
        db.delete("tblProjectStatus", null, null);
        db.delete("tblKeyPoints", null, null);
        db.delete("tblOutcomeProspect", null, null);
        db.delete("tblOutcomeProspectOR", null, null);
        db.delete("tblProspectByCoburg", null, null);
        db.delete("tblTypeofCall", null, null);
        db.delete("tblOutcomeDealer", null, null);
        db.delete("tblByCoburgDealer", null, null);
        db.delete("tblOutcomeConsultancy", null, null);
        db.delete("tblByCoburgConsultancy", null, null);
        db.delete("tblOEMType", null, null);
        db.delete("tblOutcomeOEM", null, null);
        db.delete("tblOEMByCob", null, null);
        db.delete("tblOEMConvinced", null, null);
        db.delete("tblVisitType", null, null);
        db.delete("tblOutcomeCustomer", null, null);
        db.delete("tblCustomerByCob", null, null);
        db.delete("tblVisitTypeXConsult", null, null);
        db.delete("tblVisitTypeXOEM", null, null);
        db.close();
    }

    public long register_insert(String val1, String val2, String val3, String val4, String val5, String val6, String val7, String val8,
                                String val9, String val10, String val11, String val12, String val13, String val14, String val15) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("NAME", val1);
        initialValues.put("MOBILE", val2);
        initialValues.put("EMPLOYEEID", val3);
        initialValues.put("DESIGNATION", val4);
        initialValues.put("EMPLOYEE_ID", val5);
        initialValues.put("DEPARTMENT", val6);
        initialValues.put("DEPARTMENT_ID", val7);
        initialValues.put("DESIGNATION_ID", val8);
        initialValues.put("EMAIL", val9);
        initialValues.put("PINNO", val10);
        initialValues.put("COMPANYM_ID", val11);
        initialValues.put("DOB", val12);
        initialValues.put("SIM", val13);
        initialValues.put("OTP", val14);
        initialValues.put("STATUS", val15);
        long l = db.insert("register", null, initialValues);
        db.close();
        return l;
    }

    public long insertEmployee(String val1, String val2, String val3,
                               String val4, String val5, String val6, String val7,
                               String val8) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("NAME", val1);
        initialValues.put("MOBILE", val2);
        initialValues.put("EMPLOYEEID", val3);
        initialValues.put("DESIGNATION", val4);
        initialValues.put("EMPLOYEE_ID", val5);
        initialValues.put("DEPARTMENT", val6);
        initialValues.put("DEPARTMENT_ID", val7);
        initialValues.put("DESIGNATION_ID", val8);
        long l = db.insert("tblEmployee", null, initialValues);
        db.close();
        return l;
    }

    public long insertCompany(String val1, String val2, String val3,
                              String val4, String val5, String val6, String val7, String val8,
                              String val9, String val10, String val11, String val12) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("id", val1);
        initialValues.put("ACCOUNTNAME", val2);
        initialValues.put("PINCODE", val3);
        initialValues.put("STD", val4);
        initialValues.put("PHONE", val5);
        initialValues.put("MOBILE", val6);
        initialValues.put("WEBSITE", val7);
        initialValues.put("EMAIL", val8);
        initialValues.put("DISTRICT", val9);
        initialValues.put("TYPE", val10);
        initialValues.put("TYPE_ID", val11);
        initialValues.put("Address", val12);
        long l = db.insert("tblCompany", null, initialValues);
        db.close();
        return l;
    }

    public Cursor selectCompany() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select id,ACCOUNTNAME,TYPE,TYPE_ID from tblCompany order by ACCOUNTNAME;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCustomer() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select id,ACCOUNTNAME,TYPE,TYPE_ID from tblCompany where TYPE = 'CUSTOMER' order by ACCOUNTNAME;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCustomerOnly() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select id,ACCOUNTNAME,TYPE,TYPE_ID from tblCompany where TYPE_ID = 'ed759edb-d08c-4c7c-8c89-da5f3fbe8251' order by ACCOUNTNAME;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCompanyDtls(String companyname) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select id,ACCOUNTNAME,PINCODE,STD,PHONE,MOBILE,WEBSITE,EMAIL,DISTRICT,TYPE,TYPE_ID,Address from tblCompany where ACCOUNTNAME ='"
                + companyname + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectCompanyType(String name) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select TYPE,TYPE_ID from tblCompany where ACCOUNTNAME='"
                + name + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectEmployee() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select NAME,EMPLOYEEID,DESIGNATION from tblEmployee order by NAME;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getEmployeeID(String Name) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select EMPLOYEEID from tblEmployee where NAME='" + Name
                + "';";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void deleteRegPin() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("register", null, null);
        db.delete("tblpin", null, null);
        db.close();
    }

    public Cursor selectfromlogin() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM stafflogin", null);
        return cursor;
    }

    public Cursor login_get(long rowId) throws SQLException {
        Cursor c = db.query(true, "stafflogin", new String[]{"userid",
                "userpassword"}, null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public long login_update(String val1, String val2) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("userid", val1);
        initialValues.put("userpassword", val2);
        return db.update("stafflogin", initialValues, "id" + "=" + "1", null);
    }

    public Cursor SelectOTP() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("register", new String[]{"OTP"}, null, null,
                null, null, null);
        return c;
    }

    public Cursor SelectUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("register", new String[]{"EMPLOYEEID", "NAME"},
                null, null, null, null, null);
        return c;
    }

    public Cursor GetLoginUserDtls() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("register", new String[]{"NAME", "MOBILE", "EMPLOYEEID", "DESIGNATION", "EMPLOYEE_ID", "DEPARTMENT", "DEPARTMENT_ID", "DESIGNATION_ID", "EMAIL", "PINNO", "COMPANYM_ID", "DOB", "SIM", "OTP", "STATUS"}, null, null, null, null, null);
        return c;
    }

    public Cursor selectEmployeeName() {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select EMPLOYEEID ,NAME from register;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectproduct(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select customername from dailyplanner where ccc_id='"
                + code + "';";
        Cursor c = db.rawQuery(fetch, null);
        return c;
    }

    public void setstatustoyesProspect(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Upstatus", "Y");
        System.out.print(val1);
        sqdb.update("tblProspect", values, "_id=" + val1, null);
    }

    public void setstatustoyesXProspect(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Upstatus", "Y");
        System.out.print(val1);
        sqdb.update("tblXProspect", values, "_id=" + val1, null);
    }

    public void setstatustoyesDealer(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Upstatus", "Y");
        System.out.print(val1);
        sqdb.update("tblDealer", values, "_id=" + val1, null);
    }

    public void setstatustoyesConsultancy(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Upstatus", "Y");
        System.out.print(val1);
        sqdb.update("tblConsultancy", values, "_id=" + val1, null);
    }

    public void setstatustoyesOEM(String val1) {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Upstatus", "Y");
        System.out.print(val1);
        sqdb.update("tblOEM", values, "_id=" + val1, null);
    }

    public void delete(String id) {
        db.execSQL("delete from " + "dailyplanner" + " where ccc_id='" + id
                + "' and upstatus='Y'");
    }

    public Cursor login_get1(long rowId) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        String fetch = "Select Username,Password,StaffID from stafflogin order by _id desc;";
        Cursor c = db.rawQuery(fetch, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor selectupstatusnoProspect() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM tblProspect WHERE Upstatus ='N'", null);
        return cursor;
    }

    public Cursor selectupstatusnoXProspect() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM tblXProspect WHERE Upstatus ='N'", null);
        return cursor;
    }

    public Cursor selectupstatusnoDealer() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM tblDealer WHERE Upstatus ='N'", null);
        return cursor;
    }

    public Cursor selectupstatusnoConsultancy() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM tblConsultancy WHERE Upstatus ='N'", null);
        return cursor;
    }

    public Cursor selectupstatusnoOEM() {

        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM tblOEM WHERE Upstatus ='N'", null);
        return cursor;
    }

    public Cursor selectupstatusno_tosyncProspect() {

        Cursor cursor = getReadableDatabase()
                .rawQuery(
                        "SELECT * FROM tblProspect WHERE Upstatus ='N' ORDER BY _id DESC LIMIT 1;",
                        null);
        return cursor;
    }

    public Cursor selectupstatusno_tosyncXProspect() {

        Cursor cursor = getReadableDatabase()
                .rawQuery(
                        "SELECT * FROM tblXProspect WHERE Upstatus ='N' ORDER BY _id DESC LIMIT 1;",
                        null);
        return cursor;
    }

    public Cursor selectupstatusno_tosyncDealer() {

        Cursor cursor = getReadableDatabase()
                .rawQuery(
                        "SELECT * FROM tblDealer WHERE Upstatus ='N' ORDER BY _id DESC LIMIT 1;",
                        null);
        return cursor;
    }

    public Cursor selectupstatusno_tosyncConsultancy() {

        Cursor cursor = getReadableDatabase()
                .rawQuery(
                        "SELECT * FROM tblConsultancy WHERE Upstatus ='N' ORDER BY _id DESC LIMIT 1;",
                        null);
        return cursor;
    }

    public Cursor selectupstatusno_tosyncOEM() {

        Cursor cursor = getReadableDatabase()
                .rawQuery(
                        "SELECT * FROM tblOEM WHERE Upstatus ='N' ORDER BY _id DESC LIMIT 1;",
                        null);
        return cursor;
    }

    public Cursor isRegister() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("tblpin", new String[]{"pin"}, null, null, null,
                null, null);
        return c;
    }

    public Cursor SelectPIN() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query("tblpin", new String[]{"pin"}, null, null, null,
                null, null);
        return c;
    }

    public String getOTP() {
        String pin = "0";
        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT OTP FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pin = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        return pin;
    }

    public String getMobileNo() {
        String pin = "0";
        SQLiteDatabase database = this.getReadableDatabase();

        String selectQuery = "SELECT MOBILE FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pin = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        String Mobile = "+91" + pin;
        return Mobile;
    }

    public String getStatus() {
        String status = "0";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT STATUS FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                status = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        String Status = status;
        return Status;
    }

    public String getPin() {
        String pin = "0";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT PINNO FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                pin = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        String Pin = pin;
        return Pin;
    }

    public String getEmplyoeeID() {
        String ID = "0";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT EMPLOYEEID FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ID = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return ID;
    }

    public String getName() {
        String Name = "";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT NAME FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Name = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return Name;
    }

    public String getFlag() {
        String Flag = "";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT flag FROM tblflag";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Flag = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return Flag;
    }

    public String getDepartmentId() {
        String Id = "";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT DEPARTMENT_ID FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Id = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return Id;
    }

    public String getMail() {
        String Mail = "0";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT EMAIL FROM register";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Mail = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return Mail;
    }

    public String GetAccountMId(String AccountM) {
        String AMId = "0";
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT id FROM tblCompany where ACCOUNTNAME = '" + AccountM + "';";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                AMId = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return AMId;
    }
}
