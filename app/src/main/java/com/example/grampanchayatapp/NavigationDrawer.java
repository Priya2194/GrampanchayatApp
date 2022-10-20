package com.example.grampanchayatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grampanchayatapp.databinding.ActivityNavigationDrawerBinding;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

ImageView gharpatti,panipatti,registerp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        gharpatti=findViewById(R.id.gharp);
        panipatti=findViewById(R.id.panip);
        registerp=findViewById(R.id.registerN);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        registerp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationDrawer.this,UserActivity.class));
            }
        });
        gharpatti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationDrawer.this,GharpActivity.class));
            }
        });
        panipatti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NavigationDrawer.this,PanipattiActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.action_settings:



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_info:
                startActivity(new Intent(NavigationDrawer.this,InfoActivity.class));
                break;

            case R.id.nav_abhipray:
                startActivity(new Intent(NavigationDrawer.this,AbhiprayActivity.class));
                break;

            case R.id.about:
                startActivity(new Intent(NavigationDrawer.this,AboutUsActivity.class));
                break;

            case R.id.help:
                startActivity(new Intent(NavigationDrawer.this,HelpActivity.class));
                break;
        }
        return false;
    }


}