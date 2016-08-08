package com.rd.strivos.cobby;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class Fetch extends Activity {

    String result, result1, result2, result3, result4, result5, result6,
            result7, result8, result9, result10, result11, result12, result13,
            result14, result15, result16, result17, result18, result19,
            result20, result21, result22, result23, result24, result25, result26, result27, result28, result29, result30, result31;

    String[] record, record1, record2, record3, record4, record5, record6,
            record7, record8, record9, record10, record11, record12, record13,
            record14, record15, record16, record17, record18, record19,
            record20, record21, record22, record23, record24, record25, record26, record27, record28, record29, record30, record31;

    int count, count1, count2, count3, count4, count5, count6, count7, count8,
            count9, count10, count11, count12, count13, count14, count15,
            count16, count17, count18, count19, count20, count21, count22, count23, count24, count25, count26, count27, count28, count29,
            count30, count31;

    Thread t;
    TextView tv1, tv2, tv3;
    SQLiteHelper database;
    ExpenseDBHelper expenseDBHelper;
    ProductDBHelper productDBHelper;
    ServiceVisitDBHelper serviceVisitDBHelper;
    SparesDBHelper sparesDBHelper;
    EnquiryBankDBHelper enquiryBankDBHelper;
    EmployeeDBHelper employeeDBHelper;
    ContactPersonDBHelper contactPersonDBHelper;
    CommissioningDBHelper commissioningDBHelper;
    Vector<String> vec_result, vec_result1, vec_result2, vec_result3,
            vec_result4, vec_result5, vec_result6, vec_result7, vec_result8,
            vec_result9, vec_result10, vec_result11, vec_result12,
            vec_result13, vec_result14, vec_result15, vec_result16,
            vec_result17, vec_result18, vec_result19, vec_result20, vec_result21, vec_result22, vec_result23, vec_result24,
            vec_result25, vec_result26, vec_result27, vec_result28, vec_result29, vec_result30, vec_result31;

    Vector<Vector<String>> vec_entries = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries1 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries2 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries3 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries4 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries5 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries6 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries7 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries8 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries9 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries10 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries11 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries12 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries13 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries14 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries15 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries16 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries17 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries18 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries19 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries20 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries21 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries22 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries23 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries24 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries25 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries26 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries27 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries28 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries29 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries30 = new Vector<Vector<String>>();
    Vector<Vector<String>> vec_entries31 = new Vector<Vector<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fetch);
        database = new SQLiteHelper(getApplicationContext());
        expenseDBHelper = new ExpenseDBHelper(getApplicationContext());
        productDBHelper = new ProductDBHelper(getApplicationContext());
        serviceVisitDBHelper = new ServiceVisitDBHelper(getApplicationContext());
        sparesDBHelper = new SparesDBHelper(getApplicationContext());
        enquiryBankDBHelper = new EnquiryBankDBHelper(getApplicationContext());
        employeeDBHelper = new EmployeeDBHelper(getApplicationContext());
        contactPersonDBHelper = new ContactPersonDBHelper(getApplicationContext());
        commissioningDBHelper = new CommissioningDBHelper(getApplicationContext());
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        clearmem();
        importlocally();
    }

    public void clearmem() {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "",
                "Clearing Local Data, Please wait...");
        new Thread() {
            public void run() {
                try {
                    del();
                    tv1.setText("Local memory cleared");
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
                // dismiss the progress dialog
                progressDialog.dismiss();
            }
        }.start();
    }

    public void del() {
        // delete old table values
        try {
            database.deleteLocal();
            expenseDBHelper.deleteExpensesTables();
            productDBHelper.deleteProductLookupTables();
            serviceVisitDBHelper.deleteServiceVisitTables();
            sparesDBHelper.deleteSparesLookupTables();
            enquiryBankDBHelper.deleteEnquiryBank();
            employeeDBHelper.deleteEmployee();
            contactPersonDBHelper.deleteContactPerson();
            commissioningDBHelper.deleteCommissioning();

        } catch (Exception e) {
        }
    }

    public void importlocally() {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "",
                "Importing Data, Please wait...");
        new Thread() {
            public void run() {
                try {
                    tv2.setText("Importing Data...");
                    addCompany();
                    addEmployee();
                    addProduct();
                    addDistrict();
                    addIndustry();
                    addProcess();
                    addVisitRef();
                    addProjectStatus();
                    addKeyPoints();
                    addOutcomeProspects();
                    addOutcomeOrderReceived();
                    addProspectByCoburg();
                    addTypeofCall();
                    addOutcomeDealer();
                    addByCoburgDealer();
                    addOutcomeConsultancy();
                    addByCoburgConsultancy();
                    addOEMType();
                    addOutcomeOEM();
                    addOEMByCob();
                    addOEMConvinced();
                    addVisitType();
                    addOutcomeCustomer();
                    addCustomerByCob();
                    addVisitTypeXConsult();
                    addVisitTypeXOEM();
                    addExpenseType();
                    addServiceVisitType();
                    addServiceVisitWorkStatus();
                    addSparesDtls();
                    getProductInstallationDtls();
                    addMediaMediator();
                    addEnquirySource();
                    getEmployeeDetails();
                    getContactPersonDetails();
                    getContractType();
                    getPreCommissioningList();
                    Fetch.this.finish();
                } catch (Exception e) {
                    Log.e("tag", e.getMessage());
                }
                // dismiss the progress dialog
                progressDialog.dismiss();
            }
        }.start();
    }

    private void addProduct() {
        try {
            String qry = "Select PR.DESCRIPTION AS PRINCIPAL, P.ID, P.PARTNO, P.SHORTDESCRIPTION, M.DESCRIPTION AS PRODUCTTYPE,E.DESCRIPTION AS EQUIPMENTTYPE, U.DESCRIPTION AS UOM From ProductM P INNER JOIN MASTERM M ON M.ID = P.PRODUCTTYPE_ID INNER JOIN MASTERM E ON E.ID = P.EQUIPMENTTYPE_ID INNER JOIN MASTERM U ON U.ID = P.UOM_ID INNER JOIN MASTERM PR ON PR.ID = P.PRINCIPAL_ID WHERE P.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And P.STATUSM_ID='7352002b-3031-49b6-8178-4bd6d89b68aa'";

            result2 = WebServices.select(qry);
            System.out.println("Check:" + result2);

            record2 = result2.split("~~");
            vec_result2 = new Vector<String>(Arrays.asList(record2));

            for (int i = 0; i < vec_result2.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result2.get(i)).split(",");
                count2 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries2.add(vec_record);
            }

            for (int i = 0; i < vec_entries2.size(); i++) {

                String v1 = vec_entries2.get(i).get(0);
                String v2 = vec_entries2.get(i).get(1);
                String v3 = vec_entries2.get(i).get(2);
                String v4 = vec_entries2.get(i).get(3);
                String v5 = vec_entries2.get(i).get(4);
                String v6 = vec_entries2.get(i).get(5);
                String v7 = vec_entries2.get(i).get(6);
                System.out.println("");
                productDBHelper.insertProductLookup(v1, v2, v3, v4, v5, v6, v7);
            }
        } catch (Exception e) {
        }
    }

    private void addEmployee() {
        try {
            String qry = "Select A.NAME, EJ.MOBILE, EJ.EMPLOYEEID, R.DESCRIPTION As DESIGNATION, A.ID AS EMPLOYEE_ID, D.DESCRIPTION AS DEPARTMENT,EJ.DEPARTMENT_ID, EJ.DESIGNATION_ID From EMPLOYEEM A INNER JOIN EMPLOYEEMJOININGDETAILS EJ ON EJ.EMPLOYEEM_ID = A.ID Inner Join MASTERM R On R.ID = EJ.DESIGNATION_ID Inner Join MASTERM D On D.ID = EJ.DEPARTMENT_ID WHERE A.STATUSM_ID<>'9569e2bd-4e32-4ab6-9a04-3fc4699d9f43' AND A.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61'";

            result1 = WebServices.select(qry);
            System.out.println("Check:" + result1);

            record1 = result1.split("~~");
            vec_result1 = new Vector<String>(Arrays.asList(record1));

            for (int i = 0; i < vec_result1.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result1.get(i)).split(",");
                count1 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries1.add(vec_record);
            }

            for (int i = 0; i < vec_entries1.size(); i++) {

                String v1 = vec_entries1.get(i).get(0);
                String v2 = vec_entries1.get(i).get(1);
                String v3 = vec_entries1.get(i).get(2);
                String v4 = vec_entries1.get(i).get(3);
                String v5 = vec_entries1.get(i).get(4);
                String v6 = vec_entries1.get(i).get(5);
                String v7 = vec_entries1.get(i).get(6);
                String v8 = vec_entries1.get(i).get(7);
                System.out.println("");
                database.insertEmployee(v1, v2, v3, v4, v5, v6, v7, v8);
            }

        } catch (Exception e) {
        }
    }

    private void addCompany() {

        try {

            String qry = "Select PR.DESCRIPTION AS PRINCIPAL, P.ID, P.PARTNO, P.SHORTDESCRIPTION,U.DESCRIPTION AS UOM From SPARESM P INNER JOIN MASTERM U ON U.ID = P.UOM_ID INNER JOIN MASTERM PR ON PR.ID = P.PRINCIPAL_ID WHERE P.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And P.STATUSM_ID='7352002b-3031-49b6-8178-4bd6d89b68aa' and ISKIT = '0'";

            String result30 = WebServices.TestJSON("COMPANY");


            JSONArray json = new JSONArray(result30);


            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);


                System.out.println("ID : " + i + " = " + jsonobj.getString("ID"));
                System.out.println("ACCOUNTNAME : " + i + " = " + jsonobj.getString("ACCOUNTNAME"));
                System.out.println("PINCODE : " + i + " = " + jsonobj.getString("PINCODE"));
                System.out.println("STDCODE : " + i + " = " + jsonobj.getString("STDCODE"));
                System.out.println("PHONE : " + i + " = " + jsonobj.getString("PHONE"));
                System.out.println("MOBILE : " + i + " = " + jsonobj.getString("MOBILE"));
                System.out.println("WEBSITE : " + i + " = " + jsonobj.getString("WEBSITE"));
                System.out.println("EMAIL : " + i + " = " + jsonobj.getString("EMAIL"));
                System.out.println("DISTRICT : " + i + " = " + jsonobj.getString("DISTRICT"));
                System.out.println("TYPE : " + i + " = " + jsonobj.getString("TYPE"));
                System.out.println("TYPE_ID : " + i + " = " + jsonobj.getString("TYPE_ID"));
                System.out.println("BILLINGADDRESS : " + i + " = " + jsonobj.getString("BILLINGADDRESS"));
                String v1 = jsonobj.getString("ID"),
                        v2 = jsonobj.getString("ACCOUNTNAME"),
                        v3 = jsonobj.getString("PINCODE"),
                        v4 = jsonobj.getString("STDCODE"),
                        v5 = jsonobj.getString("PHONE"),
                        v6 = jsonobj.getString("MOBILE"),
                        v7 = jsonobj.getString("WEBSITE"),
                        v8 = jsonobj.getString("EMAIL"),
                        v9 = jsonobj.getString("DISTRICT"),
                        v10 = jsonobj.getString("TYPE"),
                        v11 = jsonobj.getString("TYPE_ID"),
                        v12 = jsonobj.getString("BILLINGADDRESS");

                //serviceVisitDBHelper.insertProductInstallationDtls(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10);
                database.insertCompany(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addDistrict() {
        try {

            String qry = "Select ID,DESCRIPTION,PARENT_MASTERM_ID from MASTERM where mastertypem_id='2f71ff39-4dab-4da1-a9de-75d06a04c390'";

            result3 = WebServices.select(qry);
            System.out.println("Check:" + result3);

            record3 = result3.split("~~");
            vec_result3 = new Vector<String>(Arrays.asList(record3));

            for (int i = 0; i < vec_result3.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result3.get(i)).split(",");
                count3 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries3.add(vec_record);
            }

            for (int i = 0; i < vec_entries3.size(); i++) {

                String v1 = vec_entries3.get(i).get(0);
                String v2 = vec_entries3.get(i).get(1);
                String v3 = vec_entries3.get(i).get(2);
                System.out.println("");
                database.insertDistrict(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addIndustry() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='9ceecf0a-a981-44ed-8fb5-4147b0eb8889'";

            result4 = WebServices.select(qry);
            System.out.println("Check:" + result4);

            record4 = result4.split("~~");
            vec_result4 = new Vector<String>(Arrays.asList(record4));

            for (int i = 0; i < vec_result4.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result4.get(i)).split(",");
                count4 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries4.add(vec_record);
            }

            for (int i = 0; i < vec_entries4.size(); i++) {

                String v1 = vec_entries4.get(i).get(0);
                String v2 = vec_entries4.get(i).get(1);
                String v3 = vec_entries4.get(i).get(2);
                System.out.println("");
                database.insertIndustry(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addProcess() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id,PARENT_MASTERM_ID from MASTERM where mastertypem_id='33e727c0-5fe4-4bac-bcdf-0e00c4a75dde'";

            result5 = WebServices.select(qry);
            System.out.println("Check:" + result5);

            record5 = result5.split("~~");
            vec_result5 = new Vector<String>(Arrays.asList(record5));

            for (int i = 0; i < vec_result5.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result5.get(i)).split(",");
                count5 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries5.add(vec_record);
            }

            for (int i = 0; i < vec_entries5.size(); i++) {

                String v1 = vec_entries5.get(i).get(0);
                String v2 = vec_entries5.get(i).get(1);
                String v3 = vec_entries5.get(i).get(2);
                String v4 = vec_entries5.get(i).get(3);
                System.out.println("");
                database.insertProcess(v1, v2, v3, v4);
            }

        } catch (Exception e) {
        }
    }

    private void addVisitRef() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='98e49f2b-26e2-4139-87b0-6a180fbaf3e1'";

            result6 = WebServices.select(qry);
            System.out.println("Check:" + result6);

            record6 = result6.split("~~");
            vec_result6 = new Vector<String>(Arrays.asList(record6));

            for (int i = 0; i < vec_result6.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result6.get(i)).split(",");
                count6 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries6.add(vec_record);
            }

            for (int i = 0; i < vec_entries6.size(); i++) {

                String v1 = vec_entries6.get(i).get(0);
                String v2 = vec_entries6.get(i).get(1);
                String v3 = vec_entries6.get(i).get(2);
                System.out.println("");
                database.insertVisitRef(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addProjectStatus() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='5d136fce-deef-452a-b147-3b7083fe6a53'";

            result7 = WebServices.select(qry);
            System.out.println("Check:" + result7);

            record7 = result7.split("~~");
            vec_result7 = new Vector<String>(Arrays.asList(record7));

            for (int i = 0; i < vec_result7.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result7.get(i)).split(",");
                count7 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries7.add(vec_record);
            }

            for (int i = 0; i < vec_entries7.size(); i++) {

                String v1 = vec_entries7.get(i).get(0);
                String v2 = vec_entries7.get(i).get(1);
                String v3 = vec_entries7.get(i).get(2);
                System.out.println("");
                database.insertProjectStatus(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addKeyPoints() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='bd94c2dc-64e6-41cf-a113-bdd29f5dbb17'";

            result8 = WebServices.select(qry);
            System.out.println("Check:" + result8);

            record8 = result8.split("~~");
            vec_result8 = new Vector<String>(Arrays.asList(record8));

            for (int i = 0; i < vec_result8.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result8.get(i)).split(",");
                count8 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries8.add(vec_record);
            }

            for (int i = 0; i < vec_entries8.size(); i++) {

                String v1 = vec_entries8.get(i).get(0);
                String v2 = vec_entries8.get(i).get(1);
                String v3 = vec_entries8.get(i).get(2);
                System.out.println("");
                database.insertKeyPoints(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOutcomeProspects() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='af7d8238-1067-488f-9a1a-18a2b428e0a3'";

            result9 = WebServices.select(qry);
            System.out.println("Check:" + result9);

            record9 = result9.split("~~");
            vec_result9 = new Vector<String>(Arrays.asList(record9));

            for (int i = 0; i < vec_result9.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result9.get(i)).split(",");
                count9 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries9.add(vec_record);
            }

            for (int i = 0; i < vec_entries9.size(); i++) {

                String v1 = vec_entries9.get(i).get(0);
                String v2 = vec_entries9.get(i).get(1);
                String v3 = vec_entries9.get(i).get(2);
                System.out.println("");
                database.insertOutcomeProspect(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOutcomeOrderReceived() {

        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id,PARENT_MASTERM_ID from MASTERM where PARENT_MASTERM_ID='c26ce8c2-63f2-4df7-88b9-6e179afb773b'";

            result10 = WebServices.select(qry);
            System.out.println("Check:" + result10);

            record10 = result10.split("~~");
            vec_result10 = new Vector<String>(Arrays.asList(record10));

            for (int i = 0; i < vec_result10.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result10.get(i)).split(",");
                count10 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries10.add(vec_record);
            }

            for (int i = 0; i < vec_entries10.size(); i++) {

                String v1 = vec_entries10.get(i).get(0);
                String v2 = vec_entries10.get(i).get(1);
                String v3 = vec_entries10.get(i).get(2);
                String v4 = vec_entries10.get(i).get(3);
                System.out.println("");
                database.insertOutcomeProspectOR(v1, v2, v3, v4);
            }

        } catch (Exception e) {
        }
    }

    private void addProspectByCoburg() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='bf2c9886-e9e6-411a-8e33-dd5fe58b5339'";

            result11 = WebServices.select(qry);
            System.out.println("Check:" + result11);

            record11 = result11.split("~~");
            vec_result11 = new Vector<String>(Arrays.asList(record11));

            for (int i = 0; i < vec_result11.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result11.get(i)).split(",");
                count11 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries11.add(vec_record);
            }

            for (int i = 0; i < vec_entries11.size(); i++) {

                String v1 = vec_entries11.get(i).get(0);
                String v2 = vec_entries11.get(i).get(1);
                String v3 = vec_entries11.get(i).get(2);
                System.out.println("");
                database.insertProspectByCoburg(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addTypeofCall() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='9859ecf6-52ce-4c2a-ae99-82609646cedf'";

            result12 = WebServices.select(qry);
            System.out.println("Check:" + result12);

            record12 = result12.split("~~");
            vec_result12 = new Vector<String>(Arrays.asList(record12));

            for (int i = 0; i < vec_result12.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result12.get(i)).split(",");
                count12 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries12.add(vec_record);
            }

            for (int i = 0; i < vec_entries12.size(); i++) {

                String v1 = vec_entries12.get(i).get(0);
                String v2 = vec_entries12.get(i).get(1);
                String v3 = vec_entries12.get(i).get(2);
                System.out.println("");
                database.insertTypeOfCall(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOutcomeDealer() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='4c863993-1838-4017-a764-f1a2d9653e51'";

            result13 = WebServices.select(qry);
            System.out.println("Check:" + result13);

            record13 = result13.split("~~");
            vec_result13 = new Vector<String>(Arrays.asList(record13));

            for (int i = 0; i < vec_result13.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result13.get(i)).split(",");
                count13 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries13.add(vec_record);
            }

            for (int i = 0; i < vec_entries13.size(); i++) {

                String v1 = vec_entries13.get(i).get(0);
                String v2 = vec_entries13.get(i).get(1);
                String v3 = vec_entries13.get(i).get(2);
                System.out.println("");
                database.insertOutcomeDealer(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addByCoburgDealer() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='1514fc99-2872-4a0f-97ca-54281df3692d'";

            result14 = WebServices.select(qry);
            System.out.println("Check:" + result14);

            record14 = result14.split("~~");
            vec_result14 = new Vector<String>(Arrays.asList(record14));

            for (int i = 0; i < vec_result14.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result14.get(i)).split(",");
                count14 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries14.add(vec_record);
            }

            for (int i = 0; i < vec_entries14.size(); i++) {

                String v1 = vec_entries14.get(i).get(0);
                String v2 = vec_entries14.get(i).get(1);
                String v3 = vec_entries14.get(i).get(2);
                System.out.println("");
                database.insertByCoburgDealer(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOutcomeConsultancy() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='382e9646-ec59-4a6b-913c-cf8e1e4d7dd0'";

            result15 = WebServices.select(qry);
            System.out.println("Check:" + result15);

            record15 = result15.split("~~");
            vec_result15 = new Vector<String>(Arrays.asList(record15));

            for (int i = 0; i < vec_result15.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result15.get(i)).split(",");
                count15 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries15.add(vec_record);
            }

            for (int i = 0; i < vec_entries15.size(); i++) {

                String v1 = vec_entries15.get(i).get(0);
                String v2 = vec_entries15.get(i).get(1);
                String v3 = vec_entries15.get(i).get(2);
                System.out.println("");
                database.insertOutcomeConsultancy(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addByCoburgConsultancy() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='880b99a2-82c3-47cb-84d6-dd941537735b'";

            result16 = WebServices.select(qry);
            System.out.println("Check:" + result16);

            record16 = result16.split("~~");
            vec_result16 = new Vector<String>(Arrays.asList(record16));

            for (int i = 0; i < vec_result16.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result16.get(i)).split(",");
                count16 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries16.add(vec_record);
            }

            for (int i = 0; i < vec_entries16.size(); i++) {

                String v1 = vec_entries16.get(i).get(0);
                String v2 = vec_entries16.get(i).get(1);
                String v3 = vec_entries16.get(i).get(2);
                System.out.println("");
                database.insertByCoburgConsultancy(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOEMType() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='9f1a44aa-817f-432e-9d5f-e4ed52a64206'";

            result17 = WebServices.select(qry);
            System.out.println("Check:" + result17);

            record17 = result17.split("~~");
            vec_result17 = new Vector<String>(Arrays.asList(record17));

            for (int i = 0; i < vec_result17.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result17.get(i)).split(",");
                count17 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries17.add(vec_record);
            }

            for (int i = 0; i < vec_entries17.size(); i++) {

                String v1 = vec_entries17.get(i).get(0);
                String v2 = vec_entries17.get(i).get(1);
                String v3 = vec_entries17.get(i).get(2);
                System.out.println("");
                database.insertOEMType(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOutcomeOEM() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='44d6f68e-037c-4559-b090-49b6af850e8a'";

            result18 = WebServices.select(qry);
            System.out.println("Check:" + result18);

            record18 = result18.split("~~");
            vec_result18 = new Vector<String>(Arrays.asList(record18));

            for (int i = 0; i < vec_result18.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result18.get(i)).split(",");
                count18 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries18.add(vec_record);
            }

            for (int i = 0; i < vec_entries18.size(); i++) {

                String v1 = vec_entries18.get(i).get(0);
                String v2 = vec_entries18.get(i).get(1);
                String v3 = vec_entries18.get(i).get(2);
                System.out.println("");
                database.insertOutcomeOEM(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOEMByCob() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='aea34794-ee54-4bb0-b42e-534036c23d1e'";

            result19 = WebServices.select(qry);
            System.out.println("Check:" + result19);

            record19 = result19.split("~~");
            vec_result19 = new Vector<String>(Arrays.asList(record19));

            for (int i = 0; i < vec_result19.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result19.get(i)).split(",");
                count19 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries19.add(vec_record);
            }

            for (int i = 0; i < vec_entries19.size(); i++) {

                String v1 = vec_entries19.get(i).get(0);
                String v2 = vec_entries19.get(i).get(1);
                String v3 = vec_entries19.get(i).get(2);
                System.out.println("");
                database.insertOEMByCob(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOEMConvinced() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='e4d3cf3d-7de5-4c05-b1fa-0e53e124940f'";

            result20 = WebServices.select(qry);
            System.out.println("Check:" + result20);

            record20 = result20.split("~~");
            vec_result20 = new Vector<String>(Arrays.asList(record20));

            for (int i = 0; i < vec_result20.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result20.get(i)).split(",");
                count20 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries20.add(vec_record);
            }

            for (int i = 0; i < vec_entries20.size(); i++) {

                String v1 = vec_entries20.get(i).get(0);
                String v2 = vec_entries20.get(i).get(1);
                String v3 = vec_entries20.get(i).get(2);
                System.out.println("");
                database.insertOEMConvinced(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addVisitType() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='2c9183e5-8851-4d91-a2d0-f900dd061393'";

            result21 = WebServices.select(qry);
            System.out.println("Check:" + result21);

            record21 = result21.split("~~");
            vec_result21 = new Vector<String>(Arrays.asList(record21));

            for (int i = 0; i < vec_result21.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result21.get(i)).split(",");
                count21 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries21.add(vec_record);
            }

            for (int i = 0; i < vec_entries21.size(); i++) {

                String v1 = vec_entries21.get(i).get(0);
                String v2 = vec_entries21.get(i).get(1);
                String v3 = vec_entries21.get(i).get(2);
                System.out.println("");
                database.insertVisitType(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addOutcomeCustomer() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id,PARENT_MASTERM_ID from MASTERM where mastertypem_id='ff967316-3cd1-4d13-a73e-c614399a78f1'";

            result22 = WebServices.select(qry);
            System.out.println("Check:" + result22);

            record22 = result22.split("~~");
            vec_result22 = new Vector<String>(Arrays.asList(record22));

            for (int i = 0; i < vec_result22.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result22.get(i)).split(",");
                count22 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries22.add(vec_record);
            }

            for (int i = 0; i < vec_entries22.size(); i++) {

                String v1 = vec_entries22.get(i).get(0);
                String v2 = vec_entries22.get(i).get(1);
                String v3 = vec_entries22.get(i).get(2);
                String v4 = vec_entries22.get(i).get(3);
                System.out.println("");
                database.insertOutcomeCustomer(v1, v2, v3, v4);
            }

        } catch (Exception e) {
        }
    }

    private void addCustomerByCob() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='95ccfc61-9373-49c8-a628-52fd1ed4221b'";

            result23 = WebServices.select(qry);
            System.out.println("Check:" + result23);

            record23 = result23.split("~~");
            vec_result23 = new Vector<String>(Arrays.asList(record23));

            for (int i = 0; i < vec_result23.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result23.get(i)).split(",");
                count23 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries23.add(vec_record);
            }

            for (int i = 0; i < vec_entries23.size(); i++) {

                String v1 = vec_entries23.get(i).get(0);
                String v2 = vec_entries23.get(i).get(1);
                String v3 = vec_entries23.get(i).get(2);
                System.out.println("");
                database.insertCustomerByCob(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addVisitTypeXConsult() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='8eefde67-24ae-4bf2-9aa9-034abe21f7ed'";

            result24 = WebServices.select(qry);
            System.out.println("Check:" + result24);

            record24 = result24.split("~~");
            vec_result24 = new Vector<String>(Arrays.asList(record24));

            for (int i = 0; i < vec_result24.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result24.get(i)).split(",");
                count24 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries24.add(vec_record);
            }

            for (int i = 0; i < vec_entries24.size(); i++) {

                String v1 = vec_entries24.get(i).get(0);
                String v2 = vec_entries24.get(i).get(1);
                String v3 = vec_entries24.get(i).get(2);
                System.out.println("");
                database.insertVisitTypeXConsult(v1, v2, v3);
            }

        } catch (Exception e) {
        }
    }

    private void addVisitTypeXOEM() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='ad1464c9-f24a-40d4-8e18-00d25cf4cdb9'";

            result25 = WebServices.select(qry);
            System.out.println("Check:" + result25);

            record25 = result25.split("~~");
            vec_result25 = new Vector<String>(Arrays.asList(record25));

            for (int i = 0; i < vec_result25.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result25.get(i)).split(",");
                count25 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries25.add(vec_record);
            }

            for (int i = 0; i < vec_entries25.size(); i++) {

                String v1 = vec_entries25.get(i).get(0);
                String v2 = vec_entries25.get(i).get(1);
                String v3 = vec_entries25.get(i).get(2);
                System.out.println("");
                database.insertVisitTypeXOEM(v1, v2, v3);
            }
        } catch (Exception e) {
        }
    }

    private void addExpenseType() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='f308ddad-0897-48ff-b950-47930f88b584'";

            result26 = WebServices.select(qry);
            System.out.println("Check:" + result26);

            record26 = result26.split("~~");
            vec_result26 = new Vector<String>(Arrays.asList(record26));

            for (int i = 0; i < vec_result26.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result26.get(i)).split(",");
                count26 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries26.add(vec_record);
            }

            System.out.println("Check:" + vec_entries26);

            for (int i = 0; i < vec_entries26.size(); i++) {

                String v1 = vec_entries26.get(i).get(0);
                String v2 = vec_entries26.get(i).get(1);
                String v3 = vec_entries26.get(i).get(2);
                System.out.println("");
                expenseDBHelper.insertExpenseType(v1, v2, v3);
            }
        } catch (Exception e) {
        }
    }

    private void addServiceVisitType() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='423826db-e2c6-4f9a-bec1-d1a8eb71cf6d'";

            result27 = WebServices.select(qry);
            System.out.println("Check:" + result27);

            record27 = result27.split("~~");
            vec_result27 = new Vector<String>(Arrays.asList(record27));

            for (int i = 0; i < vec_result27.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result27.get(i)).split(",");
                count27 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries27.add(vec_record);
            }

            System.out.println("Check:" + vec_entries27);

            for (int i = 0; i < vec_entries27.size(); i++) {

                String v1 = vec_entries27.get(i).get(0);
                String v2 = vec_entries27.get(i).get(1);
                String v3 = vec_entries27.get(i).get(2);
                System.out.println("");
                serviceVisitDBHelper.insertServiceVisitType(v1, v2, v3);
            }
        } catch (Exception e) {
        }
    }

    private void addServiceVisitWorkStatus() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='051d5fca-1311-43d0-92c3-43a1ca24618a'";

            result28 = WebServices.select(qry);
            System.out.println("Check:" + result28);

            record28 = result28.split("~~");
            vec_result28 = new Vector<String>(Arrays.asList(record28));

            for (int i = 0; i < vec_result28.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result28.get(i)).split(",");
                count28 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries28.add(vec_record);
            }

            System.out.println("Check:" + vec_entries28);

            for (int i = 0; i < vec_entries28.size(); i++) {

                String v1 = vec_entries28.get(i).get(0);
                String v2 = vec_entries28.get(i).get(1);
                String v3 = vec_entries28.get(i).get(2);
                System.out.println("");
                serviceVisitDBHelper.insertServiceVisitWorkStatus(v1, v2, v3);
            }
        } catch (Exception e) {
        }
    }

    private void addSparesDtls() {
        try {

            String qry = "Select PR.DESCRIPTION AS PRINCIPAL, P.ID, P.PARTNO, P.SHORTDESCRIPTION,U.DESCRIPTION AS UOM From SPARESM P INNER JOIN MASTERM U ON U.ID = P.UOM_ID INNER JOIN MASTERM PR ON PR.ID = P.PRINCIPAL_ID WHERE P.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And P.STATUSM_ID='7352002b-3031-49b6-8178-4bd6d89b68aa' and ISKIT = '0'";

            result29 = WebServices.select(qry);
            System.out.println("Check:" + result29);

            record29 = result29.split("~~");
            vec_result29 = new Vector<String>(Arrays.asList(record29));

            for (int i = 0; i < vec_result29.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result29.get(i)).split(",");
                count29 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries29.add(vec_record);
            }

            System.out.println("Check:" + vec_entries29);

            for (int i = 0; i < vec_entries29.size(); i++) {

                String v1 = vec_entries29.get(i).get(0);
                String v2 = vec_entries29.get(i).get(1);
                String v3 = vec_entries29.get(i).get(2);
                String v4 = vec_entries29.get(i).get(3);
                String v5 = vec_entries29.get(i).get(4);
                System.out.println("");
                sparesDBHelper.insertSparesLookup(v1, v2, v3, v4, v5);
            }
            //Fetch.this.finish();
        } catch (Exception e) {
        }
    }

    private void getProductInstallationDtls() {
        try {

            String qry = "Select PR.DESCRIPTION AS PRINCIPAL, P.ID, P.PARTNO, P.SHORTDESCRIPTION,U.DESCRIPTION AS UOM From SPARESM P INNER JOIN MASTERM U ON U.ID = P.UOM_ID INNER JOIN MASTERM PR ON PR.ID = P.PRINCIPAL_ID WHERE P.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And P.STATUSM_ID='7352002b-3031-49b6-8178-4bd6d89b68aa' and ISKIT = '0'";

            String result30 = WebServices.TestJSON("INSTALL");

            JSONArray json = new JSONArray(result30);

            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);

                System.out.println("ACCOUNTM_ID : " + i + " = " + jsonobj.getString("ACCOUNTM_ID"));
                System.out.println("COMMISSIONINGTRANS_ID : " + i + " = " + jsonobj.getString("COMMISSIONINGTRANS_ID"));
                System.out.println("CONTRACTSUMMARY_ID : " + i + " = " + jsonobj.getString("CONTRACTSUMMARY_ID"));
                System.out.println("PRINCIPAL : " + i + " = " + jsonobj.getString("PRINCIPAL"));
                System.out.println("PRODUCTPARTNO : " + i + " = " + jsonobj.getString("PRODUCTPARTNO"));
                System.out.println("PARTNO : " + i + " = " + jsonobj.getString("PARTNO"));
                System.out.println("SLNO : " + i + " = " + jsonobj.getString("SLNO"));
                System.out.println("CONTRACTTYPE : " + i + " = " + jsonobj.getString("CONTRACTTYPE"));
                System.out.println("FROMDATE : " + i + " = " + jsonobj.getString("FROMDATE"));
                System.out.println("TODATE : " + i + " = " + jsonobj.getString("TODATE"));
                System.out.println("PRODUCTTYPE_ID : " + i + " = " + jsonobj.getString("PRODUCTTYPE_ID"));
                String v1 = jsonobj.getString("ACCOUNTM_ID"),
                        v2 = jsonobj.getString("COMMISSIONINGTRANS_ID"),
                        v3 = jsonobj.getString("CONTRACTSUMMARY_ID"),
                        v4 = jsonobj.getString("PRINCIPAL"),
                        v5 = jsonobj.getString("PRODUCTPARTNO"),
                        v6 = jsonobj.getString("PARTNO"),
                        v7 = jsonobj.getString("SLNO"),
                        v8 = jsonobj.getString("CONTRACTTYPE"),
                        v9 = jsonobj.getString("FROMDATE"),
                        v10 = jsonobj.getString("TODATE"),
                        v11 = jsonobj.getString("PRODUCTTYPE_ID");

                serviceVisitDBHelper.insertProductInstallationDtls(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11);
            }
        } catch (Exception e) {
        }
    }

    private void addEnquirySource() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id from MASTERM where mastertypem_id='b6961687-8a38-4458-891d-a9b211fc7bc9' order by CODE";

            result30 = WebServices.select(qry);
            System.out.println("Check:" + result30);

            record30 = result30.split("~~");
            vec_result30 = new Vector<String>(Arrays.asList(record30));

            for (int i = 0; i < vec_result30.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result30.get(i)).split(",");
                count30 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries30.add(vec_record);
            }

            System.out.println("Check:" + vec_entries30);

            for (int i = 0; i < vec_entries30.size(); i++) {

                String v1 = vec_entries30.get(i).get(0);
                String v2 = vec_entries30.get(i).get(1);
                String v3 = vec_entries30.get(i).get(2);
                System.out.println("");
                enquiryBankDBHelper.insertEnquirySource(v1, v2, v3);
            }
        } catch (Exception e) {
        }
    }

    private void addMediaMediator() {
        try {

            String qry = "Select ID,DESCRIPTION,mastertypem_id,PARENT_MASTERM_ID from MASTERM where MASTERTYPEM_ID = '997337f2-95d5-43ae-8c5b-689f817e094d'";

            result31 = WebServices.select(qry);
            System.out.println("Check:" + result31);

            record31 = result31.split("~~");
            vec_result31 = new Vector<String>(Arrays.asList(record31));

            for (int i = 0; i < vec_result31.size(); i++) {
                String[] entries_buffer;
                entries_buffer = (vec_result31.get(i)).split(",");
                count31 = entries_buffer.length;
                Vector<String> vec_record = new Vector<String>();
                vec_record = new Vector<String>(Arrays.asList(entries_buffer));
                vec_entries31.add(vec_record);
            }

            for (int i = 0; i < vec_entries31.size(); i++) {

                String v1 = vec_entries31.get(i).get(0);
                String v2 = vec_entries31.get(i).get(1);
                String v3 = vec_entries31.get(i).get(2);
                String v4 = vec_entries31.get(i).get(3);
                System.out.println("");
                enquiryBankDBHelper.insertMediaMediator(v1, v2, v3, v4);
            }
            //Fetch.this.finish();
        } catch (Exception e) {
        }
    }

    private void getEmployeeDetails() {
        try {

            String qry = "Select PR.DESCRIPTION AS PRINCIPAL, P.ID, P.PARTNO, P.SHORTDESCRIPTION,U.DESCRIPTION AS UOM From SPARESM P INNER JOIN MASTERM U ON U.ID = P.UOM_ID INNER JOIN MASTERM PR ON PR.ID = P.PRINCIPAL_ID WHERE P.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And P.STATUSM_ID='7352002b-3031-49b6-8178-4bd6d89b68aa' and ISKIT = '0'";

            String result30 = WebServices.JSONEmployee("9e77ea32-d210-4b8f-945c-097e94932f61");

            JSONArray json = new JSONArray(result30);

            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);

                System.out.println("NAME : " + i + " = " + jsonobj.getString("NAME"));
                System.out.println("MOBILE : " + i + " = " + jsonobj.getString("MOBILE"));
                System.out.println("EMPLOYEEID : " + i + " = " + jsonobj.getString("EMPLOYEEID"));
                System.out.println("DESIGNATION : " + i + " = " + jsonobj.getString("DESIGNATION"));
                System.out.println("EMPLOYEE_ID : " + i + " = " + jsonobj.getString("EMPLOYEE_ID"));
                System.out.println("DEPARTMENT : " + i + " = " + jsonobj.getString("DEPARTMENT"));
                System.out.println("DEPARTMENT_ID : " + i + " = " + jsonobj.getString("DEPARTMENT_ID"));
                System.out.println("DESIGNATION_ID : " + i + " = " + jsonobj.getString("DESIGNATION_ID"));
                System.out.println("DESIGNATIONCATEGORY_ID : " + i + " = " + jsonobj.getString("DESIGNATIONCATEGORY_ID"));
                String v1 = jsonobj.getString("NAME"),
                        v2 = jsonobj.getString("MOBILE"),
                        v3 = jsonobj.getString("EMPLOYEEID"),
                        v4 = jsonobj.getString("DESIGNATION"),
                        v5 = jsonobj.getString("EMPLOYEE_ID"),
                        v6 = jsonobj.getString("DEPARTMENT"),
                        v7 = jsonobj.getString("DEPARTMENT_ID"),
                        v8 = jsonobj.getString("DESIGNATION_ID"),
                        v9 = jsonobj.getString("DESIGNATIONCATEGORY_ID");

                employeeDBHelper.insertEmployeeDetails(v1, v2, v3, v4, v5, v6, v7, v8, v9);
            }
            //Fetch.this.finish();
        } catch (Exception e) {
        }
    }

    private void getContactPersonDetails() {
        try {

            String qry = "Select PR.DESCRIPTION AS PRINCIPAL, P.ID, P.PARTNO, P.SHORTDESCRIPTION,U.DESCRIPTION AS UOM From SPARESM P INNER JOIN MASTERM U ON U.ID = P.UOM_ID INNER JOIN MASTERM PR ON PR.ID = P.PRINCIPAL_ID WHERE P.COMPANYM_ID = '9e77ea32-d210-4b8f-945c-097e94932f61' And P.STATUSM_ID='7352002b-3031-49b6-8178-4bd6d89b68aa' and ISKIT = '0'";

            String result30 = WebServices.TestJSON("CONTACT");

            JSONArray json = new JSONArray(result30);

            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);

                System.out.println("ID : " + i + " = " + jsonobj.getString("ID"));
                System.out.println("ACCOUNTM_ID : " + i + " = " + jsonobj.getString("ACCOUNTM_ID"));
                System.out.println("CONTACTNAME : " + i + " = " + jsonobj.getString("CONTACTNAME"));
                System.out.println("MOBILENO : " + i + " = " + jsonobj.getString("MOBILENO"));
                System.out.println("EMAIL : " + i + " = " + jsonobj.getString("EMAIL"));
                System.out.println("DEPARTMENT : " + i + " = " + jsonobj.getString("DEPARTMENT"));
                System.out.println("DESIGNATION : " + i + " = " + jsonobj.getString("DESIGNATION"));
                System.out.println("STATUSM_ID : " + i + " = " + jsonobj.getString("STATUSM_ID"));
                String v1 = jsonobj.getString("ID"),
                        v2 = jsonobj.getString("ACCOUNTM_ID"),
                        v3 = jsonobj.getString("CONTACTNAME"),
                        v4 = jsonobj.getString("MOBILENO"),
                        v5 = jsonobj.getString("EMAIL"),
                        v6 = jsonobj.getString("DEPARTMENT"),
                        v7 = jsonobj.getString("DESIGNATION"),
                        v8 = jsonobj.getString("STATUSM_ID");

                contactPersonDBHelper.insertContactPerson(v1, v2, v3, v4, v5, v6, v7, v8);
            }
            //Fetch.this.finish();
        } catch (Exception e) {
        }
    }

    private void getContractType() {
        try {

            String qry = "";

            String result30 = WebServices.TestJSON("CONTRACTTYPE");

            JSONArray json = new JSONArray(result30);

            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);

                System.out.println("ID : " + i + " = " + jsonobj.getString("ID"));
                System.out.println("DESCRIPTION : " + i + " = " + jsonobj.getString("DESCRIPTION"));
                System.out.println("mastertypem_id : " + i + " = " + jsonobj.getString("mastertypem_id"));
                String v1 = jsonobj.getString("ID"),
                        v2 = jsonobj.getString("DESCRIPTION"),
                        v3 = jsonobj.getString("mastertypem_id");

                commissioningDBHelper.insertContractType(v1, v2, v3);
            }
            //Fetch.this.finish();
        } catch (Exception e) {
        }
    }

    private void getPreCommissioningList() {
        try {

            String qry = "";

            String result30 = WebServices.TestJSON("PRECOMMISSIONING_LIST");

            JSONArray json = new JSONArray(result30);

            for (int i = 0; i < json.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = json.getJSONObject(i);

                System.out.println("ID : " + i + " = " + jsonobj.getString("ID"));
                System.out.println("LONGDOCUMENTNO : " + i + " = " + jsonobj.getString("LONGDOCUMENTNO"));
                System.out.println("CONVDOCDATE : " + i + " = " + jsonobj.getString("CONVDOCDATE"));
                System.out.println("SERVICEENGINEER : " + i + " = " + jsonobj.getString("SERVICEENGINEER"));
                System.out.println("SLNO : " + i + " = " + jsonobj.getString("SLNO"));
                System.out.println("PARTNO : " + i + " = " + jsonobj.getString("PARTNO"));
                System.out.println("PRODUCTPARTNO : " + i + " = " + jsonobj.getString("PRODUCTPARTNO"));
                System.out.println("ACCOUNTM_ID : " + i + " = " + jsonobj.getString("ACCOUNTM_ID"));
                String v1 = jsonobj.getString("ID"),
                        v2 = jsonobj.getString("LONGDOCUMENTNO"),
                        v3 = jsonobj.getString("CONVDOCDATE"),
                        v4 = jsonobj.getString("SERVICEENGINEER"),
                        v5 = jsonobj.getString("SLNO"),
                        v6 = jsonobj.getString("PARTNO"),
                        v7 = jsonobj.getString("PRODUCTPARTNO"),
                        v8 = jsonobj.getString("ACCOUNTM_ID");

                commissioningDBHelper.insertPreCommissioningList(v1, v2, v3, v4, v5, v6, v7, v8);
            }
            Fetch.this.finish();
        } catch (Exception e) {
        }
    }
}