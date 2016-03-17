package com.rd.strivos.cobby;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Sync extends Activity {
    TextView tv_count;
    Button bt_sync, bt_return;
    SQLiteHelper database;
    ServiceVisitDBHelper serviceVisitDBHelper;
    PersonDBHelper personDBHelper;
    ExpenseDBHelper expenseDBHelper;
    ProductDBHelper productDBHelper;
    EnquiryBankDBHelper enquiryBankDBHelper;
    StatutoryDocumentsDBHelper statutoryDocumentsDBHelper;
    GrievanceDBHelper grievanceDBHelper;
    ParameterDBHelper parameterDBHelper;
    PaymentsDBHelper paymentsDBHelper;
    SparesDBHelper sparesDBHelper;
    OtherParameterInfoDBHelper otherParameterInfoDBHelper;
    public FTPClient mFTPClient = null;
    private static final String TAG = "ISD";
    String host;
    public static String PIC = " ";
    public static String PIC2 = " ";
    public static String PIC3 = " ";
    String addr;
    ImageView img_on_off;
    TextView txt_on_off;
    public static int NETWORK_STATE = 0;
    public static final int CONNECTED = 1;
    public static final int NOT_CONNECTED = 2;
    String sql_uid = null;
    String RSN;
    String output, CompanyName, outputMorePerson, outputKeyPoints, outputProsProduct, outputExpenses, EQoutput, SVoutput,
            outputStatutoryDocuments, outputGrievance, outputParameter, outputPayments, outputSpares, outputServiceVisitList, outputOtherParameter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sync);
        database = new SQLiteHelper(Sync.this);
        personDBHelper = new PersonDBHelper(Sync.this);
        expenseDBHelper = new ExpenseDBHelper(Sync.this);
        productDBHelper = new ProductDBHelper(Sync.this);
        serviceVisitDBHelper = new ServiceVisitDBHelper(Sync.this);
        enquiryBankDBHelper = new EnquiryBankDBHelper(Sync.this);
        statutoryDocumentsDBHelper = new StatutoryDocumentsDBHelper(Sync.this);
        grievanceDBHelper = new GrievanceDBHelper(Sync.this);
        parameterDBHelper = new ParameterDBHelper(Sync.this);
        paymentsDBHelper = new PaymentsDBHelper(Sync.this);
        sparesDBHelper = new SparesDBHelper(Sync.this);
        otherParameterInfoDBHelper = new OtherParameterInfoDBHelper(Sync.this);
        tv_count = (TextView) findViewById(R.id.s_txt_train);
        bt_sync = (Button) findViewById(R.id.s_bt_sync);
        bt_return = (Button) findViewById(R.id.s_bt_return);
        img_on_off = ((ImageView) findViewById(R.id.image_on_off));
        txt_on_off = (TextView) findViewById(R.id.text_on_off);
        host = "223.30.140.163";
        getcount();
        check_data();
        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String getSimSerialNumber = telemamanger.getSimSerialNumber();
        String getSimNumber = telemamanger.getLine1Number();
        this.registerReceiver(this.mConnReceiver, new IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION));

        bt_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Sync();
                getcount();
            }
        });

        bt_return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Sync.this.finish();
            }
        });
    }

    public void enabledisable() {
        int countValuePros = 0, countValueService = 0, countValueEnquiryBank = 0;
        Cursor c = database.selectupstatusnoProspect();
        countValuePros = c.getCount();
        Cursor c2 = serviceVisitDBHelper.selectUpStatusServiceVisit();
        countValueService = c2.getCount();
        Cursor c3 = enquiryBankDBHelper.selectUpEnquiryBank();
        countValueEnquiryBank = c3.getCount();
        int total = countValuePros + countValueService + countValueEnquiryBank;
        if (total == 0) {
            bt_sync.setEnabled(false);
        } else {
            bt_sync.setEnabled(true);
        }
        database.close();
        serviceVisitDBHelper.close();
        enquiryBankDBHelper.close();
    }

    public void getcount() {
        //String count = "0";
        int countValuePros = 0, countValueService = 0, countValueEnquiry = 0;
        try {
            Cursor c = database.selectupstatusnoProspect();
            countValuePros = c.getCount();
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Cursor c = serviceVisitDBHelper.selectUpStatusServiceVisit();
            countValueService = c.getCount();
            serviceVisitDBHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Cursor c = enquiryBankDBHelper.selectUpEnquiryBank();
            countValueEnquiry = c.getCount();
            serviceVisitDBHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int total = countValuePros + countValueService + countValueEnquiry;

        tv_count.setText(Integer.toString(total));
    }

    public void check_data() {
        try {
            int count_flag = 0;
            Cursor c = database.selectupstatusno_tosyncProspect();

            if (c != null) {
                for (int i = 0; i < c.getCount(); i++) {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        while (c.moveToNext()) {
                            String s = c.getString(0).toString();
                            System.out.println(s);
                            Log.i("Check", s);
                            count_flag = 1;
                        }
                    }
                }
            }
            database.close();
        } catch (Exception e) {
        }
    }

    public void Sync() {
        int count_flag = 0;
        Sync_Prospect();
        Sync_ServiceVisit();
        Sync_EnquiryBank();
    }

    private void Sync_EnquiryBank() {
        try {
            Cursor c = enquiryBankDBHelper.selectEnquiryBank();
            String count = Integer.toString(c.getCount());
            if (c.getCount() > 0) {
                if (c.moveToLast()) {
                    String _id = c.getString(0);
                    String COMPANYM_ID = "9e77ea32-d210-4b8f-945c-097e94932f61";
                    String DOCUMENTM_ID = "7652b262-e4d4-482f-82ee-1c98784d4057";
                    String DOCUMENTDATE = c.getString(2);
                    String EMPLOYEEM_ID = c.getString(5);
                    String ENQSOURCE_ID = c.getString(6);
                    String MEDIA_ID = c.getString(7);
                    String ENQPROVIDERNAME = c.getString(8);
                    String ACCOUNTNAME = c.getString(9);
                    String ADDRESS = c.getString(10);
                    String PINCODE = c.getString(11);
                    String DISTRICT_ID = c.getString(12);
                    String CONTACTPERSON = c.getString(13);
                    String CONTACTNO = c.getString(14);
                    String STDCODE = c.getString(15);
                    String PHONE = c.getString(16);
                    String WEBSITE = c.getString(17);
                    String EMAIL = c.getString(18);
                    String REQUIREMENT = c.getString(19);
                    String REQON = c.getString(20);
                    String ENQEXECUTOR_ID = c.getString(21);
                    String STATUSM_ID = "7352002b-3031-49b6-8178-4bd6d89b68aa";
                    String REMARKS = c.getString(22);

                    onlineInsertEnquiryBank(COMPANYM_ID, DOCUMENTM_ID, DOCUMENTDATE, EMPLOYEEM_ID, ENQSOURCE_ID, MEDIA_ID, ENQPROVIDERNAME,
                            ACCOUNTNAME, ADDRESS, PINCODE,
                            DISTRICT_ID, CONTACTPERSON, CONTACTNO, STDCODE, PHONE, WEBSITE, EMAIL, REQUIREMENT, REQON, ENQEXECUTOR_ID,
                            STATUSM_ID, REMARKS, _id);
                }
            }

        } catch (Exception ex) {
        }
    }

    private void onlineInsertEnquiryBank(String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9,
                                         String v10, String v11, String v12, String v13, String v14, String v15, String v16, String v17,
                                         String v18, String v19, String v20, String v21, String v22, String v23) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            EQoutput = WebServices.EnquiryBank(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19, v20, v21, v22);
            System.out.println(EQoutput);
            Log.i("Output", EQoutput);
            int countLength = EQoutput.length();

            if ((output == "No") || (countLength == 0)) {

                Toast.makeText(getBaseContext(), output + "Action failed.",
                        Toast.LENGTH_LONG).show();
            } else {
                enquiryBankDBHelper.EnquiryBankStatusUpdate(v23);
                Toast.makeText(getBaseContext(),
                        "Synced.",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_ServiceVisit() {
        try {
            Cursor c = serviceVisitDBHelper.selectServiceVisit();
            String count = Integer.toString(c.getCount());
            if (c.getCount() > 0) {
                if (c.moveToLast()) {
                    String _id = c.getString(0);
                    String EmployeeId = c.getString(1);
                    String CoEmployeeId = c.getString(2);
                    String InDate = c.getString(3);
                    String OutDate = c.getString(4);
                    String AccountMId = c.getString(5);
                    String Customer = c.getString(6);
                    String Address = c.getString(7);
                    String Pincode = c.getString(8);
                    String DistrictId = c.getString(9);
                    String VisitTypeId = c.getString(10);
                    String Product = c.getString(11);
                    String ProductType = c.getString(12);
                    String MachineSlNo = c.getString(13);
                    String PartNo = c.getString(14);
                    String ContractType = c.getString(15);
                    String ServicePerformed = c.getString(16);
                    String ActionRequierd = c.getString(17);
                    String VisitSummary = c.getString(18);
                    String WorkStatusID = c.getString(19);
                    String lat = c.getString(20);
                    String lon = c.getString(21);
                    String Status = c.getString(22);
                    String CompanyId = "9e77ea32-d210-4b8f-945c-097e94932f61";
                    String StatusMId = "7352002b-3031-49b6-8178-4bd6d89b68aa";
                    String DocumentId = "73681abd-c376-48d6-b7d5-ad3066a8f1d3";
                    String DocDate = "0";

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                    SimpleDateFormat sdfForDB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentDateandTime = sdf.format(new Date());
                    String currentDateandTimeForDB = sdfForDB.format(new Date());
                    DocDate = currentDateandTime;


                    onlineInsertServiceVisit(CompanyId, DocumentId, "0", "0", DocDate, AccountMId, EmployeeId, CoEmployeeId, InDate, OutDate,
                            VisitTypeId, ServicePerformed, ActionRequierd, VisitSummary, StatusMId, "0", WorkStatusID, lat, lon);

                    int countLength = SVoutput.length();

                    if ((SVoutput == "No") || (countLength == 0)) {

                        Toast.makeText(getBaseContext(), SVoutput + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {

                        Sync_KeyPerson(Customer, SVoutput, "Service");
                        Sync_Parameter(Customer, SVoutput, "Service");
                        Sync_OtherParameter(Customer, SVoutput, "Service");
                        Sync_Spares(Customer, SVoutput, "Service");
                        Sync_StatutoryDocuments(Customer, SVoutput, "Service");
                        Sync_Grievance(Customer, SVoutput, "Service");
                        Sync_Payments(Customer, SVoutput, "Service");
                        Sync_Expenses(Customer, SVoutput, "Service");
                        Sync_ServiceVisit(Customer, SVoutput, "Service");

                        serviceVisitDBHelper.ServiceVisitStatusUpdate(_id);
                        Toast.makeText(getBaseContext(),
                                "Synced.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    private void Sync_OtherParameter(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = otherParameterInfoDBHelper.OtherPaymentsForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String dryercurrentinvoltager = c.getString(1);
                    String dryercurrentinvoltagey = c.getString(2);
                    String dryercurrentinvoltageb = c.getString(3);
                    String dryercurrentinloadconditionampsr = c.getString(4);
                    String dryercurrentinloadconditionampsy = c.getString(5);
                    String dryercurrentinloadconditionampsb = c.getString(6);
                    String dryerdewpointindication = c.getString(7);
                    String filtercatridge = c.getString(8);
                    String filtercatridgeindicationlevel = c.getString(9);
                    String filtercatridgechangedate = c.getString(10);


                    online_insert_other_Parameter(Pros_ID, dryercurrentinvoltager, dryercurrentinvoltagey, dryercurrentinvoltageb, dryercurrentinloadconditionampsr, dryercurrentinloadconditionampsy, dryercurrentinloadconditionampsb, dryerdewpointindication, filtercatridge, filtercatridgeindicationlevel, filtercatridgechangedate);

                    if (outputOtherParameter.contentEquals("No")) {
                        Toast.makeText(getBaseContext(), outputOtherParameter + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        otherParameterInfoDBHelper.OtherParameterStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_other_Parameter(String TRANS_ID, String dryercurrentinvoltager, String dryercurrentinvoltagey, String dryercurrentinvoltageb, String dryercurrentinloadconditionampsr, String dryercurrentinloadconditionampsy, String dryercurrentinloadconditionampsb, String dryerdewpointindication, String filtercatridge, String filtercatridgeindicationlevel, String filtercatridgechangedate) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputOtherParameter = WebServices.SVOtherParameter(TRANS_ID, dryercurrentinvoltager, dryercurrentinvoltagey, dryercurrentinvoltageb, dryercurrentinloadconditionampsr, dryercurrentinloadconditionampsy, dryercurrentinloadconditionampsb, dryerdewpointindication, filtercatridge, filtercatridgeindicationlevel, filtercatridgechangedate);
            System.out.println(outputOtherParameter);
            Log.i("Output", outputOtherParameter);

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_ServiceVisit(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = serviceVisitDBHelper.ServiceVisitListForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String ContractID = c.getString(1);
                    String RefNo = c.getString(2);
                    String RefDate = c.getString(3);

                    online_insert_ServiceVisitList(Pros_ID, ContractID, RefNo, RefDate, ProjectType);

                    if (outputServiceVisitList.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputServiceVisitList + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String _id = String.valueOf(ID);
                        serviceVisitDBHelper.ServiceVisitListStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_ServiceVisitList(String TRANS_ID, String ContractID, String RefNo, String RefDate, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputServiceVisitList = WebServices.SVList(TRANS_ID, ContractID, RefNo, RefDate, ProjectType);
            System.out.println(outputServiceVisitList);
            Log.i("Output", outputServiceVisitList);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_Spares(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = sparesDBHelper.SparesForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String SparedId = c.getString(1);
                    String Qty = c.getString(3);

                    online_insert_Spares(Pros_ID, SparedId, Qty, ProjectType);

                    if (outputSpares.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputSpares + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String _id = String.valueOf(ID);
                        sparesDBHelper.SparesStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_Spares(String TRANS_ID, String SparedId, String Qty, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputSpares = WebServices.SparesSyncOnline(TRANS_ID, SparedId, Qty, ProjectType);
            System.out.println(outputSpares);
            Log.i("Output", outputSpares);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_Payments(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = paymentsDBHelper.PaymentsForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String VendorName = c.getString(1);
                    String RefNo = c.getString(2);
                    String RefDate = c.getString(3);
                    String Amount = c.getString(4);
                    String RecAmount = c.getString(5);
                    String RefDocNo = c.getString(6);
                    String RefDocDate = c.getString(7);


                    online_insert_Payments(Pros_ID, VendorName, RefNo, RefDate, Amount, RecAmount, RefDocNo, RefDocDate, ProjectType);

                    if (outputPayments.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputPayments + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String _id = String.valueOf(ID);
                        paymentsDBHelper.PaymentsStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_Payments(String TRANS_ID, String VendorName, String RefNo, String RefDate,
                                        String Amount, String RecAmount, String RefDocNo, String RefDocDate, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputPayments = WebServices.SVPayments(TRANS_ID, VendorName, RefNo, RefDate,
                    Amount, RecAmount, RefDocNo, RefDocDate, ProjectType);
            System.out.println(outputPayments);
            Log.i("Output", outputPayments);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_Parameter(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = parameterDBHelper.PaymentsForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String controltype = c.getString(1);
                    String pressuresetting = c.getString(2);
                    String ambienttemperature = c.getString(3);
                    String incomingvoltage = c.getString(4);
                    String ductingfacility = c.getString(5);
                    String runninghoursperday = c.getString(6);
                    String runningdaysperweek = c.getString(7);
                    String iscompressorcleanedeveryday = c.getString(8);
                    String totalrunhours = c.getString(9);
                    String totalloadhours = c.getString(10);
                    String airenddischargetemperature = c.getString(11);
                    String currentinloadconditionamps = c.getString(12);
                    String currentinidleconditionamps = c.getString(13);
                    String noofmotorstarts = c.getString(14);
                    String noofloadvalveon = c.getString(15);
                    String oillevel = c.getString(16);
                    String coolercondition = c.getString(17);
                    String motorbearingnoise = c.getString(18);
                    String airendbearingnoise = c.getString(19);
                    String inletvalveworkingcondition = c.getString(20);
                    String combinationvalveworkingcondition = c.getString(21);
                    String cavvalveworkingcondition = c.getString(22);
                    String mpcvalveworkingcondition = c.getString(23);
                    String couplingcondition = c.getString(24);
                    String beltcondition = c.getString(25);
                    String airfilter = c.getString(26);
                    String oilfilter = c.getString(27);
                    String oilseparator = c.getString(28);
                    String oil = c.getString(29);
                    String filtermat = c.getString(30);
                    String bearingreplacement = c.getString(31);
                    String motorgreasing = c.getString(32);
                    String motorgreasinginhours = c.getString(33);
                    String sumppressureatunloadcondition = c.getString(34);


                    online_insert_Parameter(Pros_ID, controltype, pressuresetting, ambienttemperature, incomingvoltage, ductingfacility, runninghoursperday, runningdaysperweek, iscompressorcleanedeveryday, totalrunhours, totalloadhours, airenddischargetemperature, currentinloadconditionamps, currentinidleconditionamps, noofmotorstarts, noofloadvalveon, oillevel, coolercondition, motorbearingnoise, airendbearingnoise, inletvalveworkingcondition, combinationvalveworkingcondition, cavvalveworkingcondition, mpcvalveworkingcondition, couplingcondition, beltcondition, airfilter, oilfilter, oilseparator, oil, filtermat, bearingreplacement, motorgreasing, motorgreasinginhours, sumppressureatunloadcondition);

                    if (outputParameter.contentEquals("No")) {
                        Toast.makeText(getBaseContext(), outputParameter + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        parameterDBHelper.ParameterStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_Parameter(String TRANS_ID, String controltype, String pressuresetting, String ambienttemperature, String incomingvoltage, String ductingfacility, String runninghoursperday, String runningdaysperweek, String iscompressorcleanedeveryday, String totalrunhours, String totalloadhours, String airenddischargetemperature, String currentinloadconditionamps, String currentinidleconditionamps, String noofmotorstarts, String noofloadvalveon, String oillevel, String coolercondition, String motorbearingnoise, String airendbearingnoise, String inletvalveworkingcondition, String combinationvalveworkingcondition, String cavvalveworkingcondition, String mpcvalveworkingcondition, String couplingcondition, String beltcondition, String airfilter, String oilfilter, String oilseparator, String oil, String filtermat, String bearingreplacement, String motorgreasing, String motorgreasinginhours, String sumppressureatunloadcondition) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputParameter = WebServices.SVParameter(TRANS_ID, controltype, pressuresetting, ambienttemperature, incomingvoltage, ductingfacility, runninghoursperday, runningdaysperweek, iscompressorcleanedeveryday, totalrunhours, totalloadhours, airenddischargetemperature, currentinloadconditionamps, currentinidleconditionamps, noofmotorstarts, noofloadvalveon, oillevel, coolercondition, motorbearingnoise, airendbearingnoise, inletvalveworkingcondition, combinationvalveworkingcondition, cavvalveworkingcondition, mpcvalveworkingcondition, couplingcondition, beltcondition, airfilter, oilfilter, oilseparator, oil, filtermat, bearingreplacement, motorgreasing, motorgreasinginhours, sumppressureatunloadcondition);
            System.out.println(outputParameter);
            Log.i("Output", outputParameter);

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_Grievance(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = grievanceDBHelper.GrievanceForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String Grievance = c.getString(1);
                    String Solution = c.getString(2);


                    online_insert_Grievance(Pros_ID, Grievance, Solution, ProjectType);

                    if (outputGrievance.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputGrievance + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        grievanceDBHelper.GrievanceStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_Grievance(String TRANS_ID, String Grievance, String Solution, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputGrievance = WebServices.GrievanceSyncOnline(TRANS_ID, Grievance, Solution, ProjectType);
            System.out.println(outputGrievance);
            Log.i("Output", outputGrievance);

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_StatutoryDocuments(String companyName, String SyncID, String ProjectType) throws SQLException {

        Cursor c = statutoryDocumentsDBHelper.StatutoryDocumentsForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    String ID = c.getString(0);
                    String VendorName = c.getString(1);
                    String RefNo = c.getString(2);
                    String RefDate = c.getString(3);
                    String Amount = c.getString(4);
                    String RefDocNo = c.getString(5);
                    String RefDocDate = c.getString(6);
                    String Remarks = c.getString(7);

                    online_insert_StatutoryDocuments(Pros_ID, VendorName, RefNo, RefDate, Amount, RefDocNo, RefDocDate, Remarks, ProjectType);

                    if (outputStatutoryDocuments.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputStatutoryDocuments + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String _id = String.valueOf(ID);
                        statutoryDocumentsDBHelper.StatutoryDocumentsStatusUpdate(ID);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_StatutoryDocuments(String TRANS_ID, String VendorName, String RefNo, String RefDate,
                                                  String Amount, String RefDocNo, String RefDocDate, String Remarks, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputStatutoryDocuments = WebServices.StatutoryDocuments(TRANS_ID, VendorName, RefNo, RefDate,
                    Amount, RefDocNo, RefDocDate, Remarks, ProjectType);
            System.out.println(outputStatutoryDocuments);
            Log.i("Output", outputStatutoryDocuments);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void onlineInsertServiceVisit(String v1, String v2, String v3, String v4, String v5, String v6, String v7, String v8, String v9, String v10,
                                          String v11, String v12, String v13, String v14, String v15, String v16, String v17, String v18, String v19) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            SVoutput = WebServices.ServiceVisitSync(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17, v18, v19);
            System.out.println(SVoutput);
            Log.i("Output", SVoutput);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void Sync_Prospect() {
        try {

            Cursor c = database.selectProspect();
            String count = Integer.toString(c.getCount());
            if (c.getCount() > 0) {
                if (c.moveToLast()) {
                    String _id = c.getString(0);

                    String FotoVCardFront = c.getString(14);
                    String FotoVCardBack = c.getString(15);
                    String FotoPerson = c.getString(16);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentDateandTime = sdf.format(new Date());

                    String COMPANYM_ID = "9e77ea32-d210-4b8f-945c-097e94932f61",
                            DOCUMENTNO = "0",
                            DOCUMENTDATE = currentDateandTime,
                            NAME = c.getString(1),
                            ADDRESS = c.getString(2),
                            PINCODE = c.getString(4),
                            DISTRICT_ID = c.getString(3),
                            AUTHORITYNAME = c.getString(5),
                            AUTHORITYDESIGNATION = c.getString(6),
                            PERSONMEET = c.getString(7),
                            DESIGNATION = c.getString(8),
                            STDCODE = c.getString(9),
                            PHONE = c.getString(10),
                            MOBILE = c.getString(11),
                            WEBSITE = c.getString(13),
                            EMAIL = c.getString(12),
                            KEYPERSONSMEET = c.getString(17),
                            EMPLOYEEM_ID = c.getString(51),
                            COEMPLOYEEM_ID = c.getString(47),
                            TYPEOFCALL_ID = c.getString(48),
                            VISITREFERENCE_ID = c.getString(18),
                            VISITDATE = c.getString(49),
                            INDUSTRYTYPE_ID = c.getString(19),
                            PROCESSTYPE_ID = c.getString(20),
                            ENDPRODUCT = c.getString(21),
                            PRODUCTCLASSIFICATION = c.getString(22),
                            PRODUCTDETAILS = c.getString(23),
                            PROJECTSTATUS_ID = c.getString(24),
                            PROJECTCOMPLETEDBY = c.getString(25),
                            PRODUCTREQBEFORE = c.getString(26),
                            QUESTION1 = c.getString(29),
                            QUESTION2 = c.getString(30),
                            QUESTION2VALUE = c.getString(31),
                            QUESTION3 = c.getString(27),
                            QUESTION4 = c.getString(28),
                            VISITOUTCOME = c.getString(42),
                            VISITOUTCOME1 = c.getString(43),
                            ACTIONREQUIRED = c.getString(44),
                            STATUSM_ID = "7352002b-3031-49b6-8178-4bd6d89b68aa",
                            REMARKS = c.getString(45),
                            PHOTO1 = FotoVCardFront,
                            PHOTO2 = FotoVCardBack,
                            PHOTO3 = FotoPerson,
                            LAT = c.getString(53),
                            LON = c.getString(54),
                            ScopeofSupply = c.getString(32),
                            TechFeatures = c.getString(33),
                            Quality = c.getString(34),
                            ServiceSupport = c.getString(35),
                            DeliverySchedule = c.getString(36),
                            ModeSale = c.getString(37),
                            Price = c.getString(38),
                            Payment = c.getString(39),
                            Warranty = c.getString(40),
                            Other = c.getString(41);

                    CompanyName = NAME;

                    online_insert_pros(COMPANYM_ID, DOCUMENTNO, DOCUMENTDATE, NAME, ADDRESS, PINCODE, DISTRICT_ID, AUTHORITYNAME,
                            AUTHORITYDESIGNATION, PERSONMEET, DESIGNATION, STDCODE, PHONE, MOBILE, WEBSITE, EMAIL, KEYPERSONSMEET,
                            EMPLOYEEM_ID, COEMPLOYEEM_ID, TYPEOFCALL_ID, VISITREFERENCE_ID, VISITDATE, INDUSTRYTYPE_ID, PROCESSTYPE_ID,
                            ENDPRODUCT, PRODUCTCLASSIFICATION, PRODUCTDETAILS, PROJECTSTATUS_ID, PROJECTCOMPLETEDBY,
                            PRODUCTREQBEFORE, QUESTION1, QUESTION2, QUESTION2VALUE, QUESTION3, QUESTION4, VISITOUTCOME,
                            VISITOUTCOME1, ACTIONREQUIRED, STATUSM_ID, REMARKS, PHOTO1, PHOTO2, PHOTO3, LAT, LON);
                    int countLength = output.length();

                    if ((output == "No") || (countLength == 0)) {

                        Toast.makeText(getBaseContext(), output + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {

                        Sync_KeyPoints(output, ScopeofSupply, TechFeatures, Quality, ServiceSupport, DeliverySchedule, ModeSale, Price, Payment, Warranty, Other);

                        int KeyPerson = Integer.parseInt(KEYPERSONSMEET);

                        if (KeyPerson == 1) {
                            Sync_KeyPerson(CompanyName, output, "Prospect");
                        }

                        Sync_ProsProducts(CompanyName, output);

                        Sync_Expenses(CompanyName, output, "Prospect");

                        ftp_upload(FotoVCardFront);
                        ftp_upload(FotoVCardBack);
                        ftp_upload(FotoPerson);
                        database.setstatustoyesProspect(_id);
                        Toast.makeText(getBaseContext(),
                                "Synced.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
            database.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Sync_Expenses(String companyName, String PROSPECTSALESVISITTRANS_ID, String ProjectType) {

        Cursor c = expenseDBHelper.ExpenseForSync(companyName, ProjectType);
        int count = c.getCount();
        String _id = "";

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_IDs = PROSPECTSALESVISITTRANS_ID;
                    _id = c.getString(0);
                    String AMOUNT = c.getString(3);
                    String EXPENSESTYPE_ID = c.getString(6);


                    online_insert_expenses(Pros_IDs, EXPENSESTYPE_ID, AMOUNT, ProjectType);

                    if (outputExpenses.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputExpenses + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        expenseDBHelper.ExpenseStatusUpdate(_id);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }

    }

    private void online_insert_expenses(String Pros_ID, String EXPENSESTYPE_ID, String Amount, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputExpenses = WebServices.ExpensesSyncOnline(Pros_ID, EXPENSESTYPE_ID, Amount, ProjectType);
            System.out.println(outputExpenses);
            Log.i("Output", outputExpenses);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void Sync_ProsProducts(String companyName, String PROSPECTSALESVISITTRANS_ID) {

        Cursor c = productDBHelper.ProductForSync(companyName, "Prospect");
        int count = c.getCount();
        String _id = "";

        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = PROSPECTSALESVISITTRANS_ID;
                    _id = c.getString(0);
                    String PRODUCTM_ID = c.getString(1);
                    String UOM_ID = c.getString(2);
                    String QTY = c.getString(3);
                    online_insert_prosproduct(Pros_ID, PRODUCTM_ID, UOM_ID, QTY);

                    if (outputProsProduct.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputProsProduct + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        productDBHelper.ProductStatusUpdate(_id);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                } while (c.moveToNext());
            }
        }
    }

    private void Sync_KeyPoints(String Pros_ID, String ScopeofSupply, String TechFeatures, String Quality, String ServiceSupport, String DeliverySchedule, String ModeSale, String Price, String Payment, String Warranty, String Other) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputKeyPoints = WebServices.KeyPoints(Pros_ID, ScopeofSupply, TechFeatures, Quality, ServiceSupport, DeliverySchedule, ModeSale, Price, Payment, Warranty, Other);
            System.out.println(outputKeyPoints);
            Log.i("Output", outputKeyPoints);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void Sync_KeyPerson(String companyName, String SyncID, String ProjectType) {

        Cursor c = personDBHelper.selectMorePersonForSync(companyName, ProjectType);
        int count = c.getCount();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                do {
                    String Pros_ID = SyncID;
                    int ID = c.getInt(0);
                    String Name = c.getString(2);
                    String Designation = c.getString(3);
                    String Mobile = c.getString(4);
                    String Email = c.getString(5);

                    online_insert_moreperson(Pros_ID, Name, Designation, Mobile, Email, ProjectType);

                    if (outputMorePerson.contentEquals("No")) {

                        Toast.makeText(getBaseContext(), outputMorePerson + "Action failed.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        String _id = String.valueOf(ID);
                        personDBHelper.AddMorePersonStatusUpdate(_id);
                        Toast.makeText(getBaseContext(),
                                "Success",
                                Toast.LENGTH_LONG).show();
                    }
                }
                while (c.moveToNext());
            }
        }
    }

    private void online_insert_prosproduct(String Pros_ID, String PRODUCTM_ID, String UOM_ID, String QTY) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputProsProduct = WebServices.ProsProduct(Pros_ID, PRODUCTM_ID, UOM_ID, QTY);
            System.out.println(outputProsProduct);
            Log.i("Output", outputProsProduct);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }

    }

    private void online_insert_moreperson(String Pros_ID, String Name, String Designation, String Mobile, String Email, String ProjectType) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            outputMorePerson = WebServices.MorePerson(Pros_ID, Name, Designation, Mobile,
                    Email, ProjectType);
            System.out.println(outputMorePerson);
            Log.i("Output", outputMorePerson);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void online_insert_pros(String v1, String v2, String v3, String v4,
                                    String v5, String v6, String v7, String v8, String v9, String v10,
                                    String v11, String v12, String v13, String v14, String v15,
                                    String v16, String v17, String v18, String v19, String v20,
                                    String v21, String v22, String v23, String v24, String v25,
                                    String v26, String v27, String v28, String v29, String v30,
                                    String v31, String v32, String v33, String v34, String v35,
                                    String v36, String v37, String v38, String v39, String v40,
                                    String v41, String v42, String v43, String v44, String v45) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            output = WebServices.ProspectSyncNew(v1, v2, v3, v4,
                    v5, v6, v7, v8, v9, v10,
                    v11, v12, v13, v14, v15,
                    v16, v17, v18, v19, v20,
                    v21, v22, v23, v24, v25,
                    v26, v27, v28, v29, v30,
                    v31, v32, v33, v34, v35,
                    v36, v37, v38, v39, v40,
                    v41, v42, v43, v44, v45);
            System.out.println(output);
            Log.i("Output", output);

        } catch (Exception e) {

            Toast.makeText(getBaseContext(), "Error-No connectivity.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void ftp_upload(String photo) {
        try {
            String path = "/storage/sdcard0/ISD_BE/" + photo;
            ftpConnect(host, "ftpuser", "India123", 21);

            ftpGetCurrentWorkingDirectory();
            ftpChangeDirectory("/cobby");
            ftpGetCurrentWorkingDirectory();
            ftpUpload(path, photo, ftpGetCurrentWorkingDirectory());
            ftpDisconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Method to connect to FTP server: */
    public boolean ftpConnect(String host, String username, String password,
                              int port) {
        try {
            mFTPClient = new FTPClient();
            // connecting to the host
            mFTPClient.connect(host, port);
            // now check the reply code, if positive mean connection success
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                // login using username & password
                boolean status = mFTPClient.login(username, password);
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();
                return status;
            }
        } catch (Exception e) {
            Log.d(TAG, "Error: could not connect to host " + host);
        }
        return false;
    }

    /* Method to disconnect from FTP server: */
    public boolean ftpDisconnect() {
        try {
            mFTPClient.logout();
            mFTPClient.disconnect();
            Log.d(TAG, "FTP Disconnected");
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Error occurred while disconnecting from ftp server.");
        }
        return false;
    }

    /* Method to get current working directory: */
    public String ftpGetCurrentWorkingDirectory() {
        try {
            String workingDir = mFTPClient.printWorkingDirectory();
            Log.i("Workdir", workingDir);
            return workingDir;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Error: could not get current working directory.");
        }
        return null;
    }

    /* Method to change working directory: */
    public boolean ftpChangeDirectory(String directory_path) {
        try {
            boolean status = mFTPClient.changeWorkingDirectory(directory_path);
            Log.d(TAG, "Change DIR Status:" + String.valueOf(status));
            return true;
        } catch (Exception e) {
            Log.d(TAG, "Error: could not change directory to " + directory_path);
        }
        return false;
    }

	/* Method to upload a file to FTP server: */

    /**
     * mFTPClient: FTP client connection object (see FTP connection example)
     * srcFilePath: source file path in sdcard desFileName: file name to be
     * stored in FTP server desDirectory: directory path where the file should
     * be upload to
     */
    public boolean ftpUpload(String srcFilePath, String desFileName,
                             String desDirectory) {
        boolean status = false;
        try {
            FileInputStream srcFileStream = new FileInputStream(srcFilePath);

            // change working directory to the destination directory
            if (ftpChangeDirectory(desDirectory)) {
                try {
                    status = mFTPClient.storeFile(desFileName, srcFileStream);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }

            srcFileStream.close();
            Log.d(TAG, String.valueOf(status));
            return status;
        } catch (Exception e) {
            // e.printStackTrace();
            Log.d(TAG, "upload failed");
        }
        return status;
    }

    public Dialog showProgressDialog(String title, String message) {
        final ProgressDialog dialog = ProgressDialog.show(this, title, message);
        return dialog;
    }

    private String getAddress(double lat, double lon) {
        Log.e("Address Called", "Address Called");
        try {
            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<android.location.Address> addresses = gcd.getFromLocation(lat,
                    lon, 1);
            if (addresses.size() > 0) {
                android.location.Address address1 = addresses.get(0);

                StringBuilder result = new StringBuilder();
                for (int i = 0; i < addresses.size(); i++) {
                    android.location.Address address = addresses.get(i);

                    int maxIndex = address.getMaxAddressLineIndex();
                    for (int x = 0; x <= maxIndex; x++) {
                        result.append(address.getAddressLine(x));
                        if (x <= maxIndex - 1)
                            result.append(",");
                        else
                            result.append(".");
                    }
                    return result.append("\n\n").toString();
                }

            } else {
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            NetworkInfo currentNetworkInfo = (NetworkInfo) intent
                    .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);

            if (currentNetworkInfo.isConnected()) {
                //img_on_off.setImageResource(R.drawable.d_on);
                //txt_on_off.setText("Online");
                //txt_on_off.setTextColor(Color.rgb(0, 153, 0));
                NETWORK_STATE = CONNECTED;
                bt_sync.setEnabled(true);
                enabledisable();

            } else {

                NETWORK_STATE = NOT_CONNECTED;
                //img_on_off.setImageResource(R.drawable.d_off);
                //txt_on_off.setText("Offline");
                //txt_on_off.setTextColor(Color.RED);
                bt_sync.setEnabled(false);
            }
        }
    };

    public void getuserid() {
        try {

            DBdata db = new DBdata(Sync.this);
            db.open();
            Cursor c = db.login_get(1);
            startManagingCursor(c);
            sql_uid = c.getString(0);
            db.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void set_upstatus() {
        database.setstatustoyesProspect(RSN);
    }

}
