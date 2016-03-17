package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by COBURG DESIGN on 05-02-2016.
 */
public class PaymentsListPageServiceVisit extends AppCompatActivity {
    ListView lvDetail;
    Context context = PaymentsListPageServiceVisit.this;
    ArrayList<ListDataPayments> myList = new ArrayList<ListDataPayments>();
    String companyName, projectType;
    private PaymentsDBHelper dbHelper = new PaymentsDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_list_page);

        lvDetail = (ListView) findViewById(R.id.list);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        getDataInList();
        lvDetail.setAdapter(new PaymentsListPageServiceVisitBaseAdapter(context, myList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_payments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.add_new_payments: {
                startAddPaymentActivity(-1);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void startAddPaymentActivity(long id) {
        Intent intent = new Intent(this, PaymentAddPage.class);
        intent.putExtra("id", id);
        intent.putExtra("ProjectType", projectType);
        intent.putExtra("CompanyName", companyName);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            getDataInList();
            lvDetail.setAdapter(new PaymentsListPageServiceVisitBaseAdapter(context, myList));
        }
    }

    private void getDataInList() {
        myList.clear();
        try {
            Cursor c = dbHelper.getPaymentsList(companyName, projectType);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        ListDataPayments lde = new ListDataPayments();
                        lde.setId(Integer.parseInt(c.getString(0)));
                        lde.setVendor(c.getString(1));
                        lde.setRefNo(c.getString(2));
                        lde.setRefDate(c.getString(3));
                        lde.setAmount(c.getString(4));
                        lde.setRecedAmount(c.getString(5));
                        lde.setRefDocNo(c.getString(6));
                        lde.setRefDocDate(c.getString(7));

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

    public void deletePayments(long id) {
        // TODO Auto-generated method stub
        final long expenseId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please confirm")
                .setTitle("Delete set?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dbHelper.deletePayments(expenseId);

                        getDataInList();
                        lvDetail.setAdapter(new PaymentsListPageServiceVisitBaseAdapter(context, myList));
                    }
                }).show();
    }
}
