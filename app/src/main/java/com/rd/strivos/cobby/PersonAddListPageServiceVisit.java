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
 * Created by COBURG DESIGN on 04-02-2016.
 */
public class PersonAddListPageServiceVisit extends AppCompatActivity {
    ListView lvDetail;
    Context context = PersonAddListPageServiceVisit.this;
    ArrayList<ListData> myList = new ArrayList<ListData>();
    String companyName, projectType;
    private PersonDBHelper dbHelper = new PersonDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_more_person);
        lvDetail = (ListView) findViewById(R.id.list);

        Bundle b = getIntent().getExtras();
        companyName = b.getString("CompanyName");
        projectType = b.getString("ProjectType");

        getDataInList();
        lvDetail.setAdapter(new PersonAddListPageServiceVisitBaseAdapter(context, myList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_new_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.add_person: {
                startPersonDetailsActivity(-1);
                break;
            }
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startPersonDetailsActivity(long id) {
        Intent intent = new Intent(this, PersonAddPage.class);
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
            lvDetail.setAdapter(new PersonAddListPageServiceVisitBaseAdapter(context, myList));
        }
    }

    private void getDataInList() {
        myList.clear();
        try {
            Cursor c = dbHelper.getPersonList(companyName, projectType);
            if (c.getCount() > 0) {
                if (c.moveToFirst()) {
                    do {
                        ListData ld = new ListData();
                        ld.setId(Integer.parseInt(c.getString(0)));
                        ld.setPersonName(c.getString(2));
                        ld.setPersonDesignation(c.getString(3));
                        ld.setPersonMobile(c.getString(4));
                        ld.setPersonEmail(c.getString(5));
                        // Add this object into the ArrayList myList
                        myList.add(ld);
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

    public void deletePersons(long id) {
        // TODO Auto-generated method stub
        final long personId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please confirm")
                .setTitle("Delete set?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deletePerson(personId);
                        getDataInList();
                        lvDetail.setAdapter(new PersonAddListPageServiceVisitBaseAdapter(context, myList));
                    }
                }).show();
    }
}
