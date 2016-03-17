package com.rd.strivos.cobby;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 10-02-2016.
 */
public class PreCommissioningProductDtls extends AppCompatActivity {

    ListView lvDetail;
    Context context = PreCommissioningProductDtls.this;
    ArrayList<ListDataInstallProductDtls> myList = new ArrayList<ListDataInstallProductDtls>();
    String companyName, projectType, companyID;
    private ServiceVisitDBHelper dbHelper = new ServiceVisitDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_visit_list_product_detls);
        lvDetail = (ListView) findViewById(R.id.list);

//        Bundle b = getIntent().getExtras();
//        companyName = b.getString("CompanyName");
//        companyID = b.getString("CompanyID");
        //projectType = b.getString("ProjectType");

        getDataInList();
        lvDetail.setAdapter(new PreCommissioningProductDtlsBaseAdapter(context, myList));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void startProductInstallActivity(long id) {
        String ID = Long.toString(id);
        Intent returnIntent = getIntent();
        returnIntent.putExtra("result", ID);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void getDataInList() {
        myList.clear();
        try {
            Cursor c = dbHelper.selectProduct();
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        ListDataInstallProductDtls lde = new ListDataInstallProductDtls();
                        lde.setId(Integer.parseInt(c.getString(0)));
                        lde.setPrincipal(c.getString(4));
                        lde.setProductPartNo(c.getString(5));
                        lde.setPartNo(c.getString(6));
                        lde.setSlNo(c.getString(7));
                        lde.setContractType(c.getString(8));

                        // Add this object into the ArrayList myList
                        myList.add(lde);

                    } while (c.moveToNext());
                }
            }

            dbHelper.close();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG)
                    .show();
            e.printStackTrace();
        }
    }
}
