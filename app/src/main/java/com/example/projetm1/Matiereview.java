package com.example.projetm1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Matiere;
import com.example.projetm1.matiere.AddMatiere;
import com.example.projetm1.matiere.MatiereAdapter;
import com.example.projetm1.matiere.shared.ListMatiere;
import com.example.projetm1.matiere.shared.MatiereShare;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Matiereview extends Fragment {
    private TextInputEditText searchtext;
    private FloatingActionButton addMatierebtn;
    List<Matiere>  matieres ;
    private RecyclerView recyclerView;
    private  MatiereAdapter matiereAdapter;
    public static Matiereview newInstance(){
        return (new Matiereview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        final View view = inflater.inflate(R.layout.matiere_view,container,false);
        addMatierebtn = view.findViewById(R.id.floatin);
        matieres = getListData();
        this.searchtext = view.findViewById(R.id.textinput);

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getContext(),"Message : text changed",Toast.LENGTH_LONG).show();
                matieres = MatiereShare.matiereListByString(searchtext.getText().toString());
                //System.out.println("------------------------------String By Chaine :----------------- "+professeurs.get(0).getNomProf().toString());
                matiereAdapter = new MatiereAdapter(getContext(),matieres);
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
                recyclerView.setAdapter(new  MatiereAdapter(getContext(),matieres));
                matiereAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        addMatierebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getContext(), AddMatiere.class);
                    startActivity(i);
                }catch (RuntimeException e){

                }
            }
        });
        this.recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new MatiereAdapter(getContext(), matieres));

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    private List<Matiere> getListData() {
        List<Matiere> list = new ArrayList<Matiere>();
        try {
            ListMatiere listMatiere = new ListMatiere();
            Thread thread = new Thread(listMatiere);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString=listMatiere.getJsonString();
            jsonString = "{\"liste\":"+jsonString.substring(4).trim()+"}";
            if (jsonString!=null){
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray =  jsonObject1.getJSONArray("liste");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Matiere matiere = new Matiere();
                        matiere.setNumat(jsonObject.getString("numat"));
                        matiere.setDesignation(jsonObject.getString("designation"));
                        matiere.setNbheures(jsonObject.getInt("nbheure"));
                        list.add(matiere);
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
