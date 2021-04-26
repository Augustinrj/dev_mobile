package com.example.projetm1.professeur.profDAO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.model.Professeur;
import com.example.projetm1.FirstActivity;
import com.example.projetm1.R;
import com.example.projetm1.professeur.shared.ProfesseurShare;
import com.example.request.ProfRequest;
import com.example.request.VolleySingleton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class AddProfesseur extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn_save_prof;
    TextInputEditText identifiant,nom;
    RequestQueue queue;
    ProfRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            setContentView(R.layout.activity_add_professeur);
            super.onCreate(savedInstanceState);
            this.configureToolbar();
            btn_save_prof = (Button) findViewById(R.id.btn_save_prof);
            identifiant = (TextInputEditText) findViewById(R.id.identifiant);
            nom = (TextInputEditText) findViewById(R.id.nom);

    //        queue = VolleySingleton.getInstance(this).getRequestQueue();
    //        request = new ProfRequest(this,queue);
            btn_save_prof.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        try{


                        Thread thread = new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        addprof();
                                    }
                                }
                        );
                        thread.start();
                        }catch(Exception e){

                        }
                    }



            });

         }catch (AndroidRuntimeException e){

        }
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
    public void addprof(){
        String id = identifiant.getText().toString().trim();
        String nom_prof = nom.getText().toString().trim();

        Professeur professeur = new Professeur();
        professeur.setNumMat(id);
        professeur.setNomProf(nom_prof);
        String response = ProfesseurShare.AddProfesseur(professeur);

        try {
            JSONObject reader = new JSONObject(response);
            String result = reader.getString("message");
            //Toast.makeText(null,"Message : "+result,Toast.LENGTH_LONG).show();
            identifiant= (TextInputEditText) findViewById(R.id.identifiant);
            identifiant.setText("");
            nom.setText("");
            Toast.makeText(null,"",Toast.LENGTH_LONG);
        } catch (Exception e) {
        }
    }
}
