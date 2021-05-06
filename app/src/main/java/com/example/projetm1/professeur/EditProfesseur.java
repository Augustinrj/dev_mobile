package com.example.projetm1.professeur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetm1.FirstActivity;
import com.example.projetm1.Professeurview;
import com.example.projetm1.R;
import com.example.projetm1.professeur.shared.ModifyProf;
import com.google.android.material.textfield.TextInputEditText;

public class EditProfesseur  extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn_save_edit_prof;
    TextInputEditText identifiant_edit,nom_edit;
    String newName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_professeur);
        super.onCreate(savedInstanceState);
        this.configureToolbar();

        Bundle extras = getIntent().getExtras();
        final String matricule = extras.getString("matricule");
        final String name = extras.getString("name");
        identifiant_edit = findViewById(R.id.identifiantedit);
        nom_edit = findViewById(R.id.nom_edit);
        btn_save_edit_prof = findViewById(R.id.btn_save_edit_prof);

        identifiant_edit.setText(matricule);
        identifiant_edit.setEnabled(false);
        nom_edit.setText(name);

        newName = nom_edit.getText().toString();

        btn_save_edit_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(identifiant_edit.getText().toString().equals("")||nom_edit.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext()," Tous les champs sont obligatoires ",Toast.LENGTH_LONG).show();
                    }else{
                        ModifyProf edited = new ModifyProf(matricule,nom_edit.getText().toString().trim());
                        Thread thread = new Thread(edited);
                        thread.start();
                        thread.join();
                        if(edited.getResponse()){
                            identifiant_edit.setText("");
                            nom_edit.setText("");
                            Toast.makeText(getApplicationContext()," Professeur modifié ",Toast.LENGTH_LONG).show();
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext()," Valeur invalid ",Toast.LENGTH_LONG).show();
                    System.out.println("Thread Edit Prof : " +e.getMessage());
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
                        startActivity(new Intent(getApplicationContext(), Professeurview.class));
                    }
                }
        );
    }

}
