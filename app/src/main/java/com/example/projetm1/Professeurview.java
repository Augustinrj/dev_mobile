package com.example.projetm1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Professeur;
import com.example.projetm1.professeur.profDAO.AddProfesseur;
import com.example.projetm1.professeur.ProfesseurAdapter;
import com.example.projetm1.professeur.shared.ListProfesseur;
import com.example.projetm1.professeur.shared.ProfesseurShare;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Professeurview extends Fragment {
    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;
    private Button buttonAddProf;
    private TextInputEditText searchtext;
    List<Professeur> professeurs;
    private ProfesseurAdapter professeurAdapter;
    public static Professeurview newInstance(){
        return (new Professeurview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        professeurs = getListData();//List<Professeur>

       final View view= inflater.inflate(R.layout.professeur_view,container,false);
        this.searchtext = view.findViewById(R.id.textinput);

       searchtext.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               //Toast.makeText(getContext(),"Message : text changed",Toast.LENGTH_LONG).show();
               professeurs = ProfesseurShare.professeurListByString(searchtext.getText().toString());
               //System.out.println("------------------------------String By Chaine :----------------- "+professeurs.get(0).getNomProf().toString());
               professeurAdapter = new ProfesseurAdapter(getContext(),professeurs);
               recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
               recyclerView.setAdapter(new ProfesseurAdapter(getContext(), professeurs));
               professeurAdapter.notifyDataSetChanged();
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });



        this.buttonAddProf = (Button)view.findViewById(R.id.buttonadd);
        this.recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new ProfesseurAdapter(getContext(), professeurs));

        //Button set onclicklistener
        buttonAddProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getContext(), AddProfesseur.class);
                    startActivity(i);
                }catch (RuntimeException e){

                }
            }
        });
        // RecyclerView scroll vertical
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private  List<Professeur> getListData() {
        List<Professeur> list = new ArrayList<Professeur>();
        try {
            ListProfesseur listProfesseur = new ListProfesseur();
            Thread thread = new Thread(listProfesseur);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString=listProfesseur.getJsonString();
            jsonString = "{\"liste\":"+jsonString.substring(4).trim()+"}";
           // jsonString = "{ \"liste\" :[{\"id\":\"1\",\"matricule\":\"455452\",\"name\":\"gfferrr\"},{\"id\":\"2\",\"matricule\":\"57555\",\"name\":\"Defk\"}]}";
            if (jsonString!=null){
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray =  jsonObject1.getJSONArray("liste");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        System.out.println(jsonObject.getInt("id"));
                        Professeur professeur = new Professeur();
                            professeur.setNumMat(jsonObject.getString("matricule"));
                            professeur.setNomProf(jsonObject.getString("name"));
                            professeur.setImageProf("");
                        list.add(professeur);
                    }
                }catch (JSONException e){
                    System.out.println("JSONException : "+e.getMessage());
                }
            }


            return list;
        }catch (Exception e){
            System.out.println(" Exception List Prof : "+e.getMessage());
        }

        return list ;
    }
}
