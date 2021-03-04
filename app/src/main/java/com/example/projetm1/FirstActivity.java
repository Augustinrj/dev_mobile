package com.example.projetm1;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public  class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private  NavigationView navigationView;
    private Fragment fragmentProf;
    private Fragment fragmentMatiere;
    private Fragment fragmentVolumeHoraire;
    private Fragment fragmentBulletinPaie;
    private Fragment fragmentMontant;
    private Fragment fragmentDiagramme;
    private static final int FRAGMENT_PROF=0;
    private static final int FRAGMENT_MATIERE=1;
    private static final int FRAGMENT_VOLUME_HORAIRE=2;
    private static final int FRAGMENT_BULLETIN_PAIE=3;
    private static final int FRAGMENT_MONTANT=4;
    private static final int FRAGMENT_DIAGRAMME=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.showFirstFragment();
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
            case  R.id.first_activity_nav_view_professeur: this.showFragment(FRAGMENT_PROF);
                break;
            case R.id.first_activity_nav_view_matiere : this.showFragment(FRAGMENT_MATIERE);
                break;
            case R.id.first_activity_nav_view_volume_horaire : this.showFragment(FRAGMENT_VOLUME_HORAIRE);
                break;
            case R.id.first_activity_nav_view_bulletin_paie : this.showFragment(FRAGMENT_BULLETIN_PAIE);
                break;
            case R.id.first_activity_nav_view_montant : this.showFragment(FRAGMENT_MONTANT);
                break;
            case R.id.first_activity_nav_view_graphique : this.showFragment(FRAGMENT_DIAGRAMME);
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
    //Set to framelayout
    private void startTransactionFragment(Fragment fragment){
        if(!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.first_activity_frame_layout,fragment).commit();
        }
    }
    //create each fragment page
    private  void showProfesseurFragment(){
        if(this.fragmentProf==null)this.fragmentProf = Professeurview.newInstance();
        this.startTransactionFragment(this.fragmentProf);
    }
    private  void  showMatiereFragment(){
        if(this.fragmentMatiere==null)this.fragmentMatiere = Matiereview.newInstance();
        this.startTransactionFragment(this.fragmentMatiere);
    }
    private  void  showVolumeHoraireFragment(){
        if(this.fragmentVolumeHoraire==null)this.fragmentVolumeHoraire = VolumeHoraireview.newInstance();
        this.startTransactionFragment(this.fragmentVolumeHoraire);
    }
    private  void  showBulletinPaieFragment(){
        if(this.fragmentBulletinPaie==null)this.fragmentBulletinPaie = BulletinPaieview.newInstance();
        this.startTransactionFragment(this.fragmentBulletinPaie);
    }
    private  void  showMontantFragment(){
        if(this.fragmentMontant==null)this.fragmentMontant = Montantview.newInstance();
        this.startTransactionFragment(this.fragmentMontant);
    }
    private  void  showDiagrammeFragment(){
        if(this.fragmentDiagramme==null)this.fragmentDiagramme = Diagrammeview.newInstance();
        this.startTransactionFragment(this.fragmentDiagramme);
    }
    //show fragment according identifier
    private  void showFragment(int fragmentIdentifier){
        switch (fragmentIdentifier){
            case FRAGMENT_PROF :
                this.showProfesseurFragment();
                break;
            case FRAGMENT_MATIERE :
                this.showMatiereFragment();
                    break;
            case FRAGMENT_VOLUME_HORAIRE:
                this.showVolumeHoraireFragment();
                     break;
            case FRAGMENT_BULLETIN_PAIE :
                this.showBulletinPaieFragment();
                    break;
            case FRAGMENT_MONTANT :
                this.showMontantFragment();
                    break;
            case FRAGMENT_DIAGRAMME :
                this.showDiagrammeFragment();
                    break;
             default:
                 break;
        }
    }

    private  void showFirstFragment(){
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.first_activity_frame_layout);
        if(visibleFragment==null){
            this.showFragment(FRAGMENT_PROF);
            this.navigationView.getMenu().getItem(0).setChecked(true);
        }
    }
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}