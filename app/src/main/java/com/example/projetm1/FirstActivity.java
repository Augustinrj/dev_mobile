package com.example.projetm1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public  class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private  NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    @Override
    public  void  onBackPressed(){
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {
        int id= menuItem.getItemId();
        switch (id){
            case  R.id.first_activity_nav_view_professeur: menuItem.setIcon(getDrawable(R.drawable.professeur));
                break;
            case R.id.first_activity_nav_view_matiere:menuItem.setIcon(getDrawable(R.drawable.matiere));
                break;
            case R.id.first_activity_nav_view_volume_horaire:menuItem.setIcon(getDrawable(R.drawable.volume_horaire_24));
                break;
            case R.id.first_activity_nav_view_bulletin_paie:
                break;
            case R.id.first_activity_nav_view_montant:
                break;
            case R.id.first_activity_nav_view_graphique:
                break;
             default:
                 break;
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private  void configureToolbar(){
           this.toolbar = (Toolbar) findViewById(R.id.first_activity_main_toolbar);
           setSupportActionBar(toolbar);
    }
    private  void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activiity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.first_activity_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}