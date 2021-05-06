package com.example.projetm1.volumehoraire;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AndroidRuntimeException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.model.Matiere;
import com.example.model.Volumehoraire;
import com.example.projetm1.FirstActivity;
import com.example.projetm1.R;
import com.example.projetm1.VolumeHoraireview;
import com.example.projetm1.matiere.shared.MatiereShare;
import com.example.projetm1.volumehoraire.imported.Publicget;
import com.example.projetm1.volumehoraire.share.AjouterVolume;
import com.example.projetm1.volumehoraire.share.Listdematricule;
import com.example.projetm1.volumehoraire.share.Listdenumatiere;
import com.example.projetm1.volumehoraire.share.VolumehoraireShare;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.ArrayList;

public class AddVolumeHoraire extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn_save_volume;
    TextInputEditText tauxhoraires;
    AutoCompleteTextView numat,matricule;
    public volatile Boolean reponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            setContentView(R.layout.activity_add_volumehoraire);
            super.onCreate(savedInstanceState);
            this.configureToolbar();
        try {
            this.initUI();

            btn_save_volume = (Button) findViewById(R.id.btn_save_volumehoraire);
            numat =  findViewById(R.id.numatiere);
            matricule =  findViewById(R.id.matricule);
            tauxhoraires = findViewById(R.id.tauxhoraire);
            //        queue = VolleySingleton.getInstance(this).getRequestQueue();
            //        request = new ProfRequest(this,queue);
            btn_save_volume.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{
                        String numatiere = numat.getText().toString().trim();
                        String matriculeprof = matricule.getText().toString().trim();
                        if(numatiere.equals("")||matriculeprof.equals("")||tauxhoraires.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Tout les champs sont obligatoires",Toast.LENGTH_LONG).show();
                        }else {
                            int taux = Integer.valueOf(tauxhoraires.getText().toString());
                            Volumehoraire volumehoraire = new Volumehoraire();
                            volumehoraire.setNumat(numatiere);
                            volumehoraire.setMatricule(matriculeprof);
                            volumehoraire.setTauxhoraire(taux);

                            AjouterVolume ajouterVolume = new AjouterVolume(volumehoraire);
                            Thread thread = new Thread(ajouterVolume);
//                                new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        addVolume();
//                                    }
//                                }
//                        );
                            thread.start();
                            thread.join();
                            if(ajouterVolume.getResponse()){
                                numat.setText("");
                                matricule.setText("");
                                tauxhoraires.setText("");
                                Toast.makeText(getApplicationContext()," Volume horaire a été ajouté",Toast.LENGTH_LONG).show();
                            }
                        }

                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(),"Valeur invalide",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }catch (AndroidRuntimeException e){

        }
    }

    private void initUI() {
        numat = findViewById(R.id.numatiere);
        ArrayList<String> numatList = Listdenumatiere.getAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddVolumeHoraire.this,android.R.layout.simple_list_item_1,numatList);
        numat.setAdapter(adapter);
        final TextView num_matiere = findViewById(R.id.vlm_matiere);


        matricule = findViewById(R.id.matricule);
        ArrayList<String> matriculeList = Listdematricule.getAll();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(AddVolumeHoraire.this,android.R.layout.simple_list_item_1,matriculeList);
        matricule.setAdapter(stringArrayAdapter);
        final TextView nom = findViewById(R.id.vlm_prof);


        numat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                num_matiere.setText("Designation : "+Publicget.getdesignMat(numat.getText().toString()));
                System.out.println("NOM : "+Publicget.getdesignMat(matricule.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        matricule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nom.setText("NOM : "+ Publicget.getnomProf(matricule.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

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
//    public void addVolume(){
////        String numatiere = numat.getText().toString().trim();
////        String matriculeprof = matricule.getText().toString().trim();
////        int taux = Integer.valueOf(tauxhoraires.getText().toString());
////        Volumehoraire volumehoraire = new Volumehoraire();
////        volumehoraire.setNumat(numatiere);
////        volumehoraire.setMatricule(matriculeprof);
////        volumehoraire.setTauxhoraire(taux);
////        String response = VolumehoraireShare.AddVolume(volumehoraire);
//
////        try {
////            JSONObject reader = new JSONObject(response);
////            String result = reader.getString("message");
////            //Toast.makeText(null,"Message : "+result,Toast.LENGTH_LONG).show();
////            numat=  findViewById(R.id.numatiere);
////            reponse = true;
////
////            numat.setText("");
////            matricule.setText("");
////            tauxhoraires.setText("");
////            Toast.makeText(AddVolumeHoraire.this," Volume horaire a été ajouté",Toast.LENGTH_LONG);
////        } catch (Exception e) {
////            System.out.println("Exception -------------------------------" +e.getMessage());
////            reponse = false;
////        }
//    }
}
