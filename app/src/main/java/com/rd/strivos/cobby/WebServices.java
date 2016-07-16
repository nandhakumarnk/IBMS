package com.rd.strivos.cobby;

import org.apache.http.conn.ConnectTimeoutException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import java.net.ConnectException;
import java.nio.channels.ConnectionPendingException;

public class WebServices {

//    Live Connection

    private static final String SOAP_ACTION = "http://223.30.140.163:81/";
    private static final String NAMESPACE = "http://223.30.140.163:81/";
    private static final String URL = "http://223.30.140.163:81/InboxDetails.asmx";
    private static final String CONNANAME = "Data Source=223.30.140.163\\SQLEXPRESS;Initial Catalog=IBMS_DB;User ID=sa;Password=coburg@1234";

//    UAT Connection

//    private static final String SOAP_ACTION = "http://223.30.140.163:82/";
//    private static final String NAMESPACE = "http://223.30.140.163:82/";
//    private static final String URL = "http://223.30.140.163:82/InboxDetails.asmx";
//    private static final String CONNANAME = "Data Source=223.30.140.163\\SQLEXPRESS;Initial Catalog=IBMS_DB_UAT;User ID=sa;Password=pod1234";


    public static String EnquiryBank(String COMPANYM_ID, String DOCUMENTM_ID, String DOCUMENTDATE, String EMPLOYEEM_ID,
                                     String ENQSOURCE_ID, String MEDIA_ID, String ENQPROVIDERNAME, String ACCOUNTNAME,
                                     String ADDRESS, String PINCODE, String DISTRICT_ID, String CONTACTPERSON, String CONTACTNO,
                                     String STDCODE, String PHONE, String WEBSITE, String EMAIL, String REQUIREMENT, String REQON,
                                     String ENQEXECUTOR_ID, String STATUSM_ID, String REMARKS) throws Exception {
        try {
            SoapObject request = new SoapObject(NAMESPACE, "EnquiryBank");
            request.addProperty("COMPANYM_ID", COMPANYM_ID);
            request.addProperty("DOCUMENTM_ID", DOCUMENTM_ID);
            request.addProperty("DOCUMENTDATE", DOCUMENTDATE);
            request.addProperty("EMPLOYEEM_ID", EMPLOYEEM_ID);
            request.addProperty("ENQSOURCE_ID", ENQSOURCE_ID);
            request.addProperty("MEDIA_ID", MEDIA_ID);
            request.addProperty("ENQPROVIDERNAME", ENQPROVIDERNAME);
            request.addProperty("ACCOUNTNAME", ACCOUNTNAME);
            request.addProperty("ADDRESS", ADDRESS);
            request.addProperty("PINCODE", PINCODE);
            request.addProperty("DISTRICT_ID", DISTRICT_ID);
            request.addProperty("CONTACTPERSON", CONTACTPERSON);
            request.addProperty("CONTACTNO", CONTACTNO);
            request.addProperty("STDCODE", STDCODE);
            request.addProperty("PHONE", PHONE);
            request.addProperty("WEBSITE", WEBSITE);
            request.addProperty("EMAIL", EMAIL);
            request.addProperty("REQUIREMENT", REQUIREMENT);
            request.addProperty("REQON", REQON);
            request.addProperty("ENQEXECUTOR_ID", ENQEXECUTOR_ID);
            request.addProperty("STATUSM_ID", STATUSM_ID);
            request.addProperty("REMARKS", REMARKS);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String ServiceVisitSync(String CompanyId, String DocumentId, String DocumentNo, String LongDocumentNo,
                                          String DocumentDate, String AccountM, String EmplyoeeId, String CoEmployeeId,
                                          String TimeIn, String TimeOut, String VisitTypeId, String ServicePerformed,
                                          String ActionRequired, String VisitSummary, String StatusMId, String Remarks,
                                          String WorkStatusId, String LAT, String LON) throws Exception {
        try {
            SoapObject request = new SoapObject(NAMESPACE, "ServiceVisitSyncDB");
            request.addProperty("CompanyId", CompanyId);
            request.addProperty("DocumentId", DocumentId);
            request.addProperty("DocumentNo", DocumentNo);
            request.addProperty("LongDocumentNo", LongDocumentNo);
            request.addProperty("DocumentDate", DocumentDate);
            request.addProperty("AccountM", AccountM);
            request.addProperty("EmplyoeeId", EmplyoeeId);
            request.addProperty("CoEmployeeId", CoEmployeeId);
            request.addProperty("TimeIn", TimeIn);
            request.addProperty("TimeOut", TimeOut);
            request.addProperty("VisitTypeId", VisitTypeId);
            request.addProperty("ServicePerformed", ServicePerformed);
            request.addProperty("ActionRequired", ActionRequired);
            request.addProperty("VisitSummary", VisitSummary);
            request.addProperty("StatusMId", StatusMId);
            request.addProperty("Remarks", Remarks);
            request.addProperty("WorkStatusId", WorkStatusId);
            request.addProperty("LAT", LAT);
            request.addProperty("LON", LON);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String TestJSON(String set) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "DataFromJSON");
            request.addProperty("value", set);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String JSONEmployee(String companyId) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "FnGetJSONStringEmployee");
            request.addProperty("companyId", companyId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String JSONWithQry(String set, String Qry) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "DataFromJSONWithQry");
            request.addProperty("value", set);
            request.addProperty("qry", Qry);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String select(String qry) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Select");
            request.addProperty("Connname", CONNANAME);
            request.addProperty("SqlQuery", qry);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String MorePerson(String TRANS_ID, String NAME, String DESIGNATION, String MOBILENO, String EMAIL, String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "MorePerson");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("NAME", NAME);
            request.addProperty("DESIGNATION", DESIGNATION);
            request.addProperty("MOBILENO", MOBILENO);
            request.addProperty("EMAIL", EMAIL);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String StatutoryDocuments(String TRANS_ID, String VendorName, String RefNo, String RefDate,
                                            String Amount, String RefDocNo, String RefDocDate, String Remarks, String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "StatutoryDocuments");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("VendorName", VendorName);
            request.addProperty("RefNo", RefNo);
            request.addProperty("RefDate", RefDate);
            request.addProperty("Amount", Amount);
            request.addProperty("RefDocNo", RefDocNo);
            request.addProperty("RefDocDate", RefDocDate);
            request.addProperty("Remarks", Remarks);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String SVPayments(String TRANS_ID, String VendorName, String RefNo, String RefDate,
                                    String Amount, String RecAmount, String RefDocNo, String RefDocDate, String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "SVPayments");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("VendorName", VendorName);
            request.addProperty("RefNo", RefNo);
            request.addProperty("RefDate", RefDate);
            request.addProperty("Amount", Amount);
            request.addProperty("RecAmount", RecAmount);
            request.addProperty("RefDocNo", RefDocNo);
            request.addProperty("RefDocDate", RefDocDate);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String SVList(String TRANS_ID, String ContractSummary_ID, String RefNo, String RefDate,
                                String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "SVList");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("ContractSummary_ID", ContractSummary_ID);
            request.addProperty("RefNo", RefNo);
            request.addProperty("RefDate", RefDate);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String ProsProduct(String PROSPECTSALESVISITTRANS_ID, String PRODUCTM_ID, String UOM_ID, String QTY) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "ProsProduct");
            request.addProperty("PROSPECTSALESVISITTRANS_ID", PROSPECTSALESVISITTRANS_ID);
            request.addProperty("PRODUCTM_ID", PRODUCTM_ID);
            request.addProperty("UOM_ID", UOM_ID);
            request.addProperty("QTY", QTY);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String ExpensesSyncOnline(String TRANS_ID, String EXPENSESTYPE_ID, String AMOUNT, String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Expenses");
            request.addProperty("PROSPECTSALESVISITTRANS_ID", TRANS_ID);
            request.addProperty("EXPENSESTYPE_ID", EXPENSESTYPE_ID);
            request.addProperty("AMOUNT", AMOUNT);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String GrievanceSyncOnline(String TRANS_ID, String Grievance_ID, String SOLUTION, String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Grievance");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("Grievance_ID", Grievance_ID);
            request.addProperty("SOLUTION", SOLUTION);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String SparesSyncOnline(String TRANS_ID, String Spares_ID, String Qty, String ProjectType) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Spares");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("Spares_ID", Spares_ID);
            request.addProperty("Qty", Qty);
            request.addProperty("ProjectType", ProjectType);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String SVOtherParameter(String TRANS_ID, String dryercurrentinvoltager, String dryercurrentinvoltagey, String dryercurrentinvoltageb,
                                          String dryercurrentinloadconditionampsr, String dryercurrentinloadconditionampsy,
                                          String dryercurrentinloadconditionampsb, String dryerdewpointindication, String filtercatridge,
                                          String filtercatridgeindicationlevel, String filtercatridgechangedate) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "ServiceVisitTransOtherParameter");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("dryercurrentinvoltager", dryercurrentinvoltager);
            request.addProperty("dryercurrentinvoltagey", dryercurrentinvoltagey);
            request.addProperty("dryercurrentinvoltageb", dryercurrentinvoltageb);
            request.addProperty("dryercurrentinloadconditionampsr", dryercurrentinloadconditionampsr);
            request.addProperty("dryercurrentinloadconditionampsy", dryercurrentinloadconditionampsy);
            request.addProperty("dryercurrentinloadconditionampsb", dryercurrentinloadconditionampsb);
            request.addProperty("dryerdewpointindication", dryerdewpointindication);
            request.addProperty("filtercatridge", filtercatridge);
            request.addProperty("filtercatridgeindicationlevel", filtercatridgeindicationlevel);
            request.addProperty("filtercatridgechangedate", filtercatridgechangedate);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String SVParameter(String TRANS_ID, String controltype, String pressuresetting, String ambienttemperature,
                                     String incomingvoltage, String ductingfacility, String runninghoursperday, String runningdaysperweek,
                                     String iscompressorcleanedeveryday, String totalrunhours, String totalloadhours,
                                     String airenddischargetemperature, String currentinloadconditionamps, String currentinidleconditionamps,
                                     String noofmotorstarts, String noofloadvalveon, String oillevel, String coolercondition,
                                     String motorbearingnoise, String airendbearingnoise, String inletvalveworkingcondition,
                                     String combinationvalveworkingcondition, String cavvalveworkingcondition, String mpcvalveworkingcondition,
                                     String couplingcondition, String beltcondition, String airfilter, String oilfilter, String oilseparator,
                                     String oil, String filtermat, String bearingreplacement, String motorgreasing, String motorgreasinginhours,
                                     String sumppressureatunloadcondition) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "ServiceVisitTransParameter");
            request.addProperty("TRANS_ID", TRANS_ID);
            request.addProperty("controltype", controltype);
            request.addProperty("pressuresetting", pressuresetting);
            request.addProperty("ambienttemperature", ambienttemperature);
            request.addProperty("incomingvoltage", incomingvoltage);
            request.addProperty("ductingfacility", ductingfacility);
            request.addProperty("runninghoursperday", runninghoursperday);
            request.addProperty("runningdaysperweek", runningdaysperweek);
            request.addProperty("iscompressorcleanedeveryday", iscompressorcleanedeveryday);
            request.addProperty("totalrunhours", totalrunhours);
            request.addProperty("totalloadhours", totalloadhours);
            request.addProperty("airenddischargetemperature", airenddischargetemperature);
            request.addProperty("currentinloadconditionamps", currentinloadconditionamps);
            request.addProperty("currentinidleconditionamps", currentinidleconditionamps);
            request.addProperty("noofmotorstarts", noofmotorstarts);
            request.addProperty("noofloadvalveon", noofloadvalveon);
            request.addProperty("oillevel", oillevel);
            request.addProperty("coolercondition", coolercondition);
            request.addProperty("motorbearingnoise", motorbearingnoise);
            request.addProperty("airendbearingnoise", airendbearingnoise);
            request.addProperty("inletvalveworkingcondition", inletvalveworkingcondition);
            request.addProperty("combinationvalveworkingcondition", combinationvalveworkingcondition);
            request.addProperty("cavvalveworkingcondition", cavvalveworkingcondition);
            request.addProperty("mpcvalveworkingcondition", mpcvalveworkingcondition);
            request.addProperty("couplingcondition", couplingcondition);
            request.addProperty("beltcondition", beltcondition);
            request.addProperty("airfilter", airfilter);
            request.addProperty("oilfilter", oilfilter);
            request.addProperty("oilseparator", oilseparator);
            request.addProperty("oil", oil);
            request.addProperty("filtermat", filtermat);
            request.addProperty("bearingreplacement", bearingreplacement);
            request.addProperty("motorgreasing", motorgreasing);
            request.addProperty("motorgreasinginhours", motorgreasinginhours);
            request.addProperty("sumppressureatunloadcondition", sumppressureatunloadcondition);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }


    public static String KeyPoints(String Pros_ID, String ScopeofSupply, String TechFeatures, String Quality, String ServiceSupport,
                                   String DeliverySchedule, String ModeSale, String Price, String Payment, String Warranty, String Other) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "KeyPoints");
            request.addProperty("Pros_ID", Pros_ID);
            request.addProperty("ScopeofSupply", ScopeofSupply);
            request.addProperty("TechFeatures", TechFeatures);
            request.addProperty("Quality", Quality);
            request.addProperty("ServiceSupport", ServiceSupport);
            request.addProperty("DeliverySchedule", DeliverySchedule);
            request.addProperty("ModeSale", ModeSale);
            request.addProperty("Price", Price);
            request.addProperty("Payment", Payment);
            request.addProperty("Warranty", Warranty);
            request.addProperty("Other", Other);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String ProspectSync(String Name, String Fulladdress,
                                      String Pincode, String DistrictID, String AUTHORITYNAME,
                                      String AUTHORITYDESIGNATION, String PERSONMEET, String DESIGNATION,
                                      String Mobile, String TelePhone, String Email, String WebSite,
                                      String KEYPERSONSMEET, String EMPLOYEEM_ID, String COEMPLOYEEM_ID,
                                      String TYPEOFCALL_ID, String VISITREFERENCE_ID, String VISITDATE,
                                      String ESTYEAR, String ANNUALTURNOVER, String NOOFEMPLOYEES,
                                      String ASSOCIATEDCOMPANIES, String INDUSTRYTYPE_ID,
                                      String PROCESSTYPE_ID, String ENDPRODUCT,
                                      String PRODUCTCLASSIFICATION, String PRODUCTDETAILS,
                                      String PROJECTSTATUS_ID, String PROJECTCOMPLETEDBY,
                                      String PRODUCTREQBEFORE, String QUESTION1, String QUESTION2,
                                      String QUESTION2VALUE, String VISITOUTCOME, String VISITOUTCOME1,
                                      String ACTIONREQUIRED, String CONTACTPERSONPHOTO, String CARDFRONT,
                                      String CARDBACK, String KeyScope, String KeyTechnical,
                                      String KeyQuality, String KeyService, String KeyDelivery,
                                      String KeyMode, String KeyPrice, String KeyPayment,
                                      String KeyWarranty, String KeyOtherPart) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "ProspectSync");
            request.addProperty("Name", Name);
            request.addProperty("Fulladdress", Fulladdress);
            request.addProperty("Pincode", Pincode);
            request.addProperty("DistrictID", DistrictID);
            request.addProperty("AUTHORITYNAME", AUTHORITYNAME);
            request.addProperty("AUTHORITYDESIGNATION", AUTHORITYDESIGNATION);
            request.addProperty("PERSONMEET", PERSONMEET);
            request.addProperty("DESIGNATION", DESIGNATION);
            request.addProperty("Mobile", Mobile);
            request.addProperty("TelePhone", TelePhone);
            request.addProperty("Email", Email);
            request.addProperty("WebSite", WebSite);
            request.addProperty("KEYPERSONSMEET", KEYPERSONSMEET);
            request.addProperty("EMPLOYEEM_ID", EMPLOYEEM_ID);
            request.addProperty("COEMPLOYEEM_ID", COEMPLOYEEM_ID);
            request.addProperty("TYPEOFCALL_ID", TYPEOFCALL_ID);
            request.addProperty("VISITREFERENCE_ID", VISITREFERENCE_ID);
            request.addProperty("VISITDATE", VISITDATE);
            request.addProperty("ESTYEAR", ESTYEAR);
            request.addProperty("ANNUALTURNOVER", ANNUALTURNOVER);
            request.addProperty("NOOFEMPLOYEES", NOOFEMPLOYEES);
            request.addProperty("ASSOCIATEDCOMPANIES", ASSOCIATEDCOMPANIES);
            request.addProperty("INDUSTRYTYPE_ID", INDUSTRYTYPE_ID);
            request.addProperty("PROCESSTYPE_ID", PROCESSTYPE_ID);
            request.addProperty("ENDPRODUCT", ENDPRODUCT);
            request.addProperty("PRODUCTCLASSIFICATION", PRODUCTCLASSIFICATION);
            request.addProperty("PRODUCTDETAILS", PRODUCTDETAILS);
            request.addProperty("PROJECTSTATUS_ID", PROJECTSTATUS_ID);
            request.addProperty("PROJECTCOMPLETEDBY", PROJECTCOMPLETEDBY);
            request.addProperty("PRODUCTREQBEFORE", PRODUCTREQBEFORE);
            request.addProperty("QUESTION1", QUESTION1);
            request.addProperty("QUESTION2", QUESTION2);
            request.addProperty("QUESTION2VALUE", QUESTION2VALUE);
            request.addProperty("VISITOUTCOME", VISITOUTCOME);
            request.addProperty("VISITOUTCOME1", VISITOUTCOME1);
            request.addProperty("ACTIONREQUIRED", ACTIONREQUIRED);
            request.addProperty("CONTACTPERSONPHOTO", CONTACTPERSONPHOTO);
            request.addProperty("CARDFRONT", CARDFRONT);
            request.addProperty("CARDBACK", CARDBACK);
            request.addProperty("KeyScope", KeyScope);
            request.addProperty("KeyTechnical", KeyTechnical);
            request.addProperty("KeyQuality", KeyQuality);
            request.addProperty("KeyService", KeyService);
            request.addProperty("KeyDelivery", KeyDelivery);
            request.addProperty("KeyMode", KeyMode);
            request.addProperty("KeyPrice", KeyPrice);
            request.addProperty("KeyPayment", KeyPayment);
            request.addProperty("KeyWarranty", KeyWarranty);
            request.addProperty("KeyOtherPart", KeyOtherPart);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String ProspectSyncNew(String COMPANYM_ID, String DOCUMENTNO, String DOCUMENTDATE, String NAME, String ADDRESS, String PINCODE, String DISTRICT_ID, String AUTHORITYNAME,
                                         String AUTHORITYDESIGNATION, String PERSONMEET, String DESIGNATION, String STDCODE, String PHONE, String MOBILE, String WEBSITE, String EMAIL, String KEYPERSONSMEET,
                                         String EMPLOYEEM_ID, String COEMPLOYEEM_ID, String TYPEOFCALL_ID, String VISITREFERENCE_ID, String VISITDATE, String INDUSTRYTYPE_ID,
                                         String PROCESSTYPE_ID, String ENDPRODUCT, String PRODUCTCLASSIFICATION, String PRODUCTDETAILS, String PROJECTSTATUS_ID, String PROJECTCOMPLETEDBY,
                                         String PRODUCTREQBEFORE, String QUESTION1, String QUESTION2, String QUESTION2VALUE, String QUESTION3, String QUESTION4, String VISITOUTCOME,
                                         String VISITOUTCOME1, String ACTIONREQUIRED, String STATUSM_ID, String REMARKS, String PHOTO1, String PHOTO2, String PHOTO3, String LAT, String LON) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "ProspectSyncNew");
            request.addProperty("COMPANYM_ID", COMPANYM_ID);
            request.addProperty("DOCUMENTNO", DOCUMENTNO);
            request.addProperty("DOCUMENTDATE", DOCUMENTDATE);
            request.addProperty("NAME", NAME);
            request.addProperty("ADDRESS", ADDRESS);
            request.addProperty("PINCODE", PINCODE);
            request.addProperty("DISTRICT_ID", DISTRICT_ID);
            request.addProperty("AUTHORITYNAME", AUTHORITYNAME);
            request.addProperty("AUTHORITYDESIGNATION", AUTHORITYDESIGNATION);
            request.addProperty("PERSONMEET", PERSONMEET);
            request.addProperty("DESIGNATION", DESIGNATION);
            request.addProperty("STDCODE", STDCODE);
            request.addProperty("PHONE", PHONE);
            request.addProperty("MOBILE", MOBILE);
            request.addProperty("WEBSITE", WEBSITE);
            request.addProperty("EMAIL", EMAIL);
            request.addProperty("KEYPERSONSMEET", KEYPERSONSMEET);
            request.addProperty("EMPLOYEEM_ID", EMPLOYEEM_ID);
            request.addProperty("COEMPLOYEEM_ID", COEMPLOYEEM_ID);
            request.addProperty("TYPEOFCALL_ID", TYPEOFCALL_ID);
            request.addProperty("VISITREFERENCE_ID", VISITREFERENCE_ID);
            request.addProperty("VISITDATE", VISITDATE);
            request.addProperty("INDUSTRYTYPE_ID", INDUSTRYTYPE_ID);
            request.addProperty("PROCESSTYPE_ID", PROCESSTYPE_ID);
            request.addProperty("ENDPRODUCT", ENDPRODUCT);
            request.addProperty("PRODUCTCLASSIFICATION", PRODUCTCLASSIFICATION);
            request.addProperty("PRODUCTDETAILS", PRODUCTDETAILS);
            request.addProperty("PROJECTSTATUS_ID", PROJECTSTATUS_ID);
            request.addProperty("PROJECTCOMPLETEDBY", PROJECTCOMPLETEDBY);
            request.addProperty("PRODUCTREQBEFORE", PRODUCTREQBEFORE);
            request.addProperty("QUESTION1", QUESTION1);
            request.addProperty("QUESTION2", QUESTION2);
            request.addProperty("QUESTION2VALUE", QUESTION2VALUE);
            request.addProperty("VISITOUTCOME", VISITOUTCOME);
            request.addProperty("VISITOUTCOME1", VISITOUTCOME1);
            request.addProperty("ACTIONREQUIRED", ACTIONREQUIRED);
            request.addProperty("STATUSM_ID", STATUSM_ID);
            request.addProperty("REMARKS", REMARKS);
            request.addProperty("QUESTION3", QUESTION3);
            request.addProperty("QUESTION4", QUESTION4);
            request.addProperty("PHOTO1", PHOTO1);
            request.addProperty("PHOTO2", PHOTO2);
            request.addProperty("PHOTO3", PHOTO3);
            request.addProperty("LAT", LAT);
            request.addProperty("LON", LON);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String ValueFromJSON(String value) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "DataFromJSON");
            request.addProperty("Value", value);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setAddAdornments(false);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            Object result = (Object) envelope.getResponse();
            return response.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String Register(String EmployeeID, String Name, String Email,
                                  String Mobile, String DOB, String IMEINo, String Model, String OTP, String SIM)
            throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Register");
            request.addProperty("EmployeeID", EmployeeID);
            request.addProperty("Name", Name);
            request.addProperty("Email", Email);
            request.addProperty("Mobile", Mobile);
            request.addProperty("DOB", DOB);
            request.addProperty("IMEINo", IMEINo);
            request.addProperty("Model", Model);
            request.addProperty("OTP", OTP);
            request.addProperty("SIM", SIM);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectionPendingException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String sendOTP(String qry) throws Exception {

        try {
            // SoapObject request = new SoapObject(NAMESPACE, "Select");
            // request.addProperty("Connname", CONNANAME);
            // request.addProperty("SqlQuery", qry);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            // envelope.bodyOut = request;
            // envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            // envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    qry);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static String insert(String qry) {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Insert");
            request.addProperty("Connname", CONNANAME);
            request.addProperty("SqlQuery", qry);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            return "Failed1: Unable to connect.Check your internet connection.";

        } catch (ConnectException ex) {
            return "Failed2: Unable to connect.Check your internet connection.";
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        return "Failed3: Unable to connect.Check your internet connection.";
    }

    public static String Update(String qry) throws Exception {

        try {
            SoapObject request = new SoapObject(NAMESPACE, "Update");
            request.addProperty("Connname", CONNANAME);
            request.addProperty("SqlQuery", qry);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.XSD;
            envelope.setOutputSoapObject(request);
            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(
                    URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            Object result = (Object) envelope.getResponse();
            return result.toString();
        } catch (ConnectTimeoutException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (ConnectException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
