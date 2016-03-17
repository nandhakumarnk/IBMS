package com.rd.strivos.cobby;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;
    SQLiteHelper dbHelper;
    String setName, setMail, Department;
    AlertDialog.Builder builder, builder_verify;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new SQLiteHelper(getApplicationContext());
        builder_verify = new AlertDialog.Builder(this);

        Bundle b = getIntent().getExtras();
        Department = b.getString("User");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (Department.contentEquals("31964e21-6996-4910-a936-12496e9f0559")) {
            MenuItem item = navigationView.getMenu().getItem(2);
            item.setVisible(false);
        } else if (Department.contentEquals("13b65ba2-9c63-40f9-91b9-5c52d7209977")) {
            MenuItem item = navigationView.getMenu().getItem(1);
            item.setVisible(false);
        }

        View hView = navigationView.getHeaderView(0);
        ImageView iv = (ImageView) hView.findViewById(R.id.imageView);
        TextView headerName = (TextView) hView.findViewById(R.id.txtName);
        TextView headerMail = (TextView) hView.findViewById(R.id.textEmail);
        checkName();
        checkMail();
        iv.setImageResource(R.drawable.logo);
        headerName.setText(setName);
        headerMail.setText(setMail);
        loadDashboard();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            String set_string = "Do you want logout?";
            builder_verify
                    .setMessage(set_string)
                    .setCancelable(true)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    count = 1;
                                    finish();
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog verify_alert = builder_verify.create();
            builder_verify.show();
            return true;
        }
        if (id == R.id.action_import) {

            String set_string = "Do you want to import data from server?";
            builder_verify
                    .setMessage(set_string)
                    .setCancelable(true)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    count = 1;
                                    Intent myIntent = new Intent(MainActivity.this, getDataFromServerFragment.class);
                                    startActivity(myIntent);
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog verify_alert = builder_verify.create();
            builder_verify.show();
            return true;
        }
        if (id == R.id.action_sync) {
            String set_string = "Do you want sync data to server?";
            builder_verify
                    .setMessage(set_string)
                    .setCancelable(true)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    count = 1;
                                    Intent i = new Intent(MainActivity.this, Sync.class);
                                    startActivity(i);
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog verify_alert = builder_verify.create();
            builder_verify.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            // Handle the camera action
            fragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        } else if (id == R.id.nav_sales_visit) {
            fragment = new DailySalesReport();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        } else if (id == R.id.nav_service_visit) {
            fragment = new DailyServiceVisit();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        } else if (id == R.id.nav_enquiry_bank) {
            fragment = new EnquiryBank();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        } else if (id == R.id.nav_precommissioning) {
            fragment = new PreCommissioning();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        }else if (id == R.id.nav_commissioning) {
            fragment = new Commissioning();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadDashboard() {
        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flContent, fragment);
        ft.commit();
    }

    private void checkName() {
        String _Name = "";
        try {
            _Name = dbHelper.getName();
            setName = _Name;

        } catch (Exception ex) {

        }
    }

    private void checkMail() {
        String _Mail = "";
        try {
            _Mail = dbHelper.getMail();
            setMail = _Mail;

        } catch (Exception ex) {

        }
    }
}
