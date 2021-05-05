package com.example.projetm1.professeur.shared;

import android.widget.Toast;

import com.example.model.Professeur;
import com.example.projetm1.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

public class AjouterProf implements Runnable {
    String identifiant,nom;
    public volatile Boolean response;
    public AjouterProf(String identifiant, String nom) {
        this.identifiant = identifiant;
        this.nom = nom;
    }

    @Override
    public void run() {
        addprof();
    }

    public void addprof(){
        String id = identifiant;//getText().toString().trim();
        String nom_prof = nom;//.getText().toString().trim();

        Professeur professeur = new Professeur();
        professeur.setNumMat(id);
        professeur.setNomProf(nom_prof);
        response = ProfesseurShare.AddProfesseur(professeur);

//        try {
//            JSONObject reader = new JSONObject(response);
//            String result = reader.getString("message");
//            //Toast.makeText(null,"Message : "+result,Toast.LENGTH_LONG).show();
//            identifiant= (TextInputEditText) findViewById(R.id.identifiant);
//            identifiant.setText("");
//            nom.setText("");
//            Toast.makeText(null,"",Toast.LENGTH_LONG);
//        } catch (Exception e) {
//        }
    }
    public Boolean getResponse(){
        return response;
    }
}
