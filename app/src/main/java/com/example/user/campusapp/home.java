package com.example.user.campusapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLogin=(Button)findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent=new Intent(home.this,LoginActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainIntent);
            }
        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("email"));
                String[] s={"justineblade39@gmail.com"};
                i.putExtra(Intent.EXTRA_EMAIL,s);
                i.putExtra(Intent.EXTRA_SUBJECT,"Reporting A Bug In Pothole Reporting App");
                i.putExtra(Intent.EXTRA_TEXT,"");
                i.setType("message/rfc822");
                Intent chooser=Intent.createChooser(i,"Launch Mail");
                startActivity(chooser);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent mainIntent=new Intent(home.this,LoginActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
        } else if (id == R.id.nav_gallery) {

            Intent mainIntent=new Intent(home.this,Feedback.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        }  else if (id == R.id.nav_manage) {
            Intent mainIntent=new Intent(home.this,Help.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        } else if (id == R.id.nav_share) {
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareSubText="the great porthole reporting app";
            String shareBodyText="https://play.google.com/store/apps";
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubText);
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBodyText);
            startActivity(Intent.createChooser(shareIntent,"share with..."));

        } else if (id == R.id.nav_send) {
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareSubText="the great porthole reporting app";
            String shareBodyText="https://play.google.com/store/apps";
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubText);
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBodyText);
            startActivity(Intent.createChooser(shareIntent,"share with..."));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
