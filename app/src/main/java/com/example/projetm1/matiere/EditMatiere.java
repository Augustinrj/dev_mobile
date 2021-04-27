package com.example.projetm1.matiere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetm1.FirstActivity;
import com.example.projetm1.R;
import com.example.projetm1.matiere.shared.ModifyMat;
import com.google.android.material.textfield.TextInputEditText;

public class EditMatiere extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn_save_edit_mat;
    TextInputEditText identifiant_edit;
    TextInputEditText designation_edit,nbheures;
    String newDesign;
    int newNbh;//nbh;
//    String designation;
//    String numat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_matiere);
        super.onCreate(savedInstanceState);
        this.configureToolbar();

        Bundle extras = getIntent().getExtras();
        final  String numat = extras.getString("numMat");
        final  String designation = extras.getString("designation");
        final int nbh = extras.getInt("nbheure");

        identifiant_edit = findViewById(R.id.identifiantedit_mat);
       designation_edit = findViewById(R.id.designation_edit);
       nbheures = findViewById(R.id.nbheures_edit);
        btn_save_edit_mat = findViewById(R.id.btn_save_edit_mat);

        identifiant_edit.setText(numat);
        designation_edit.setText(designation);
        nbheures.setText(""+nbh);



        btn_save_edit_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    newDesign = designation_edit.getText().toString();
                    newNbh = Integer.valueOf(nbheures.getText().toString());

                    ModifyMat edited = new ModifyMat(numat,newDesign,newNbh);
                    Thread thread = new Thread(edited);
                    thread.start();
                    thread.join();
                    if(edited.getResponse()){
                        identifiant_edit.setText("");
                        designation_edit.setText("");
                        nbheures.setText("");
                        Toast.makeText(getApplicationContext()," Matiere modifié ",Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
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
                        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                    }
                }
        );
    }
}
