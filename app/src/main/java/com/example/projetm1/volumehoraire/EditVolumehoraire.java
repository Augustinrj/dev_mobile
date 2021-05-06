package com.example.projetm1.volumehoraire;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetm1.FirstActivity;
import com.example.projetm1.R;
import com.example.projetm1.VolumeHoraireview;
import com.example.projetm1.volumehoraire.imported.Publicget;
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
        this.initUI();
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
                    if(numat_edit.getText().toString().equals("")||matricule_edit.getText().toString().equals("")||tauxhoraire_edit.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext()," Tous les chsmps sont obligatoires ",Toast.LENGTH_LONG).show();
                    }else{
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
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext()," Valeur invalide ",Toast.LENGTH_LONG).show();
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
                        startActivity(new Intent(getApplicationContext(), VolumeHoraireview.class));
                    }
                }
        );
    }
    private void initUI() {
        numat_edit = findViewById(R.id.numatiere);
        ArrayList<String> numatList = Listdenumatiere.getAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(EditVolumehoraire.this,android.R.layout.simple_list_item_1,numatList);
        numat_edit.setAdapter(adapter);
        final TextView num_matiere = findViewById(R.id.vlm_matiere);

        matricule_edit = findViewById(R.id.matricule);
        ArrayList<String> matriculeList = Listdematricule.getAll();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(EditVolumehoraire.this,android.R.layout.simple_list_item_1,matriculeList);
        matricule_edit.setAdapter(stringArrayAdapter);
        final TextView nom = findViewById(R.id.vlm_prof);

        numat_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num_matiere.setText("Designation : "+ Publicget.getdesignMat(numat_edit.getText().toString().trim()));
                //System.out.println("NOM : "+Publicget.getdesignMat(matricule.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        matricule_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nom.setText("NOM : "+ Publicget.getnomProf(matricule_edit.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
