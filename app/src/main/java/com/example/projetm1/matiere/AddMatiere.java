package com.example.projetm1.matiere;


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

        import com.example.model.Matiere;
        import com.example.projetm1.FirstActivity;
        import com.example.projetm1.R;

        import com.example.projetm1.matiere.shared.MatiereShare;
        import com.google.android.material.textfield.TextInputEditText;

        import org.json.JSONException;
        import org.json.JSONObject;

public class AddMatiere extends AppCompatActivity {
    private Toolbar toolbar;
    Button btn_save_mat;
    TextInputEditText identifiant,designation,nbheures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            setContentView(R.layout.activity_add_matiere);
            super.onCreate(savedInstanceState);
            this.configureToolbar();
            btn_save_mat = (Button) findViewById(R.id.btn_save_matiere);
            identifiant = (TextInputEditText) findViewById(R.id.identifiant);
            designation = (TextInputEditText) findViewById(R.id.designation);
            nbheures = findViewById(R.id.nbheures);
            //        queue = VolleySingleton.getInstance(this).getRequestQueue();
            //        request = new ProfRequest(this,queue);
            btn_save_mat.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{


                        Thread thread = new Thread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        addmat();
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
    public void addmat(){
        String id = identifiant.getText().toString().trim();
        String design = designation.getText().toString().trim();
        int nbh = Integer.valueOf(nbheures.getText().toString());
        Matiere matiere = new Matiere();
        matiere.setNumat(id);
        matiere.setDesignation(design);
        matiere.setNbheures(nbh);
        String response = MatiereShare.AddMatiere(matiere);

        try {
            JSONObject reader = new JSONObject(response);
            String result = reader.getString("message");
            //Toast.makeText(null,"Message : "+result,Toast.LENGTH_LONG).show();
            identifiant= (TextInputEditText) findViewById(R.id.identifiant);
            designation = findViewById(R.id.designation);
            nbheures = findViewById(R.id.nbheures);
            identifiant.setText("");
            designation.setText("");
            nbheures.setText("");
            Toast.makeText(null,"Matiere ajouté",Toast.LENGTH_LONG);
        } catch (Exception e) {
        }
    }
}
