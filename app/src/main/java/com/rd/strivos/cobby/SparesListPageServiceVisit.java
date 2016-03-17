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
public class SparesListPageServiceVisit extends AppCompatActivity {

    ListView lvDetail;
    Context context = SparesListPageServiceVisit.this;
    ArrayList<ListDataSpares> myList = new ArrayList<ListDataSpares>();
    String companyName, projectType;
    private SparesDBHelper dbHelper = new SparesDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spares_list_page);

        lvDetail = (ListView) findViewById(R.id.list);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        getDataInList();
        lvDetail.setAdapter(new SparesListPageServiceVisitBaseAdapter(context, myList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_spare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.add_new_spares: {
                startAddSparesActivity(-1);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void startAddSparesActivity(long id) {
        Intent intent = new Intent(this, SparesAddPage.class);
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
            lvDetail.setAdapter(new SparesListPageServiceVisitBaseAdapter(context, myList));
        }
    }

    private void getDataInList() {
        myList.clear();
        try {
            Cursor c = dbHelper.getSparesList(companyName, projectType);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        ListDataSpares lde = new ListDataSpares();
                        lde.setId(Integer.parseInt(c.getString(0)));
                        lde.setPartNo(c.getString(3));
                        lde.setShortDescription(c.getString(4));
                        lde.setUOM(c.getString(5));
                        lde.setQuantity(c.getString(6));

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

    public void deleteSpares(long id) {
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

                        dbHelper.deleteSpares(expenseId);

                        getDataInList();
                        lvDetail.setAdapter(new SparesListPageServiceVisitBaseAdapter(context, myList));
                    }
                }).show();
    }
}
