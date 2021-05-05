package com.example.projetm1.matiere;


        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentTransaction;

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
        import com.example.projetm1.Matiereview;
        import com.example.projetm1.R;

        import com.example.projetm1.matiere.shared.Ajoutmatiere;
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


                        String id = identifiant.getText().toString().trim();
                        String design = designation.getText().toString().trim();

//                        Matiere matiere = new Matiere();
//                        matiere.setNumat(id);
//                        matiere.setDesignation(design);
//                        matiere.setNbheures(nbh);
                        if(id.equals("") || design.equals("") || (nbheures.getText().toString().equals(""))){
                            Toast.makeText(AddMatiere.this,"Veuillez remplir toutes les champs",Toast.LENGTH_LONG).show();
                        }
                        else{
                            int nbh = Integer.valueOf(nbheures.getText().toString());
                            Ajoutmatiere ajoutmatiere = new Ajoutmatiere(id,design,nbh);
                            try{Thread thread1 = new Thread(ajoutmatiere);

//                            Thread thread = new Thread(
//                                    new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            addmat();
//                                        }
//                                    }
//                            );
                            thread1.start();
                            thread1.join();
                            Boolean response = ajoutmatiere.getResponse();
                                if(response){
                                    identifiant= (TextInputEditText) findViewById(R.id.identifiant);
                                    designation = findViewById(R.id.designation);
                                    nbheures = findViewById(R.id.nbheures);
                                    identifiant.setText("");
                                    designation.setText("");
                                    nbheures.setText("");
                                    Toast.makeText(getApplicationContext(),"Matiere ajouté",Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(getApplicationContext(), Matiereview.class));
//                                    Intent intent = new Intent(getApplicationContext(), Matiereview.class);
//                                    startActivity(intent);
//                                    finish();
                                }
                        }catch(Exception e){
                            System.out.println("Message : " +e.getMessage());
                        }
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
                        startActivity(new Intent(getApplicationContext(), Matiereview.class));
                    }
                }
        );
    }
//    public void addmat(){
//        String id = identifiant.getText().toString().trim();
//        String design = designation.getText().toString().trim();
//        int nbh = Integer.valueOf(nbheures.getText().toString());
//        Matiere matiere = new Matiere();
//        matiere.setNumat(id);
//        matiere.setDesignation(design);
//        matiere.setNbheures(nbh);
//        Boolean response = MatiereShare.AddMatiere(matiere);
//
//        try {
//            if(response){
//                identifiant= (TextInputEditText) findViewById(R.id.identifiant);
//                designation = findViewById(R.id.designation);
//                nbheures = findViewById(R.id.nbheures);
//                identifiant.setText("");
//                designation.setText("");
//                nbheures.setText("");
//                Toast.makeText(null,"Matiere ajouté",Toast.LENGTH_LONG);
//            }
//
//        } catch (Exception e) {
//        }
//    }
}
