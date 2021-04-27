package com.example.projetm1.volumehoraire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetm1.FirstActivity;
import com.example.projetm1.R;
import com.example.projetm1.volumehoraire.share.Listdematricule;
import com.example.projetm1.volumehoraire.share.Listdenumatiere;
import com.example.projetm1.volumehoraire.share.ModifyVolume;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class EditVolumehoraire  extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn_save_edit_vol;
    AutoCompleteTextView numat_edit,matricule_edit;
    TextInputEditText tauxhoraire_edit;
    String newNumat,newMatricule;
    int newTauxhoraire;
    int tauxhoraires,id;//nbh;
    //    String designation;
//    String numat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_volume);
        super.onCreate(savedInstanceState);
        this.configureToolbar();

        Bundle extras = getIntent().getExtras();
        final  String numat = extras.getString("numMat");
        final  String matricule = extras.getString("matricule");
        final int tauxhoraire = extras.getInt("tauxhoraire");
        id = extras.getInt("id");

        numat_edit = findViewById(R.id.numatiere);
        matricule_edit = findViewById(R.id.matricule);
        tauxhoraire_edit  = findViewById(R.id.tauxhoraire);
        btn_save_edit_vol = findViewById(R.id.btn_save_volumehoraire);

        numat_edit.setText(numat);
        matricule_edit.setText(matricule);
        tauxhoraire_edit.setText(""+tauxhoraire);



        btn_save_edit_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    newNumat = numat_edit.getText().toString();
                    newMatricule = matricule_edit.getText().toString();
                    newTauxhoraire= Integer.valueOf(tauxhoraire_edit.getText().toString());

                    ModifyVolume edited = new ModifyVolume(newNumat,newMatricule,newTauxhoraire,id);
                    Thread thread = new Thread(edited);
                    thread.start();
                    thread.join();
                    if(edited.getResponse()){
                        numat_edit.setText("");
                        matricule_edit.setText("");
                        tauxhoraire_edit.setText("");
                        Toast.makeText(getApplicationContext()," Volume horaire modifié ",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    System.out.println("Thread Edit Volume : " +e.getMessage());
                }

            }
        });
    }

    private  void configureToolbar(){
        this.toolbar = (Toolbar) findViewById(R.id.first_activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                    }
                }
        );
    }
    private void initUI() {
        numat_edit = findViewById(R.id.numatiere);
        ArrayList<String> numatList = Listdenumatiere.getAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(EditVolumehoraire.this,android.R.layout.simple_list_item_1,numatList);
        numat_edit.setAdapter(adapter);

        matricule_edit = findViewById(R.id.matricule);
        ArrayList<String> matriculeList = Listdematricule.getAll();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(EditVolumehoraire.this,android.R.layout.simple_list_item_1,matriculeList);
        matricule_edit.setAdapter(stringArrayAdapter);
    }
}
