package com.example.projetm1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.model.Professeur;
import com.example.projetm1.montant.GetBulletindepaie;
import com.example.projetm1.professeur.shared.Getnom;
import com.example.projetm1.professeur.shared.ListProfesseur;
import com.example.projetm1.volumehoraire.share.Listdematricule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BulletinPaieview extends Fragment {
    TableLayout tableLayout;
    TableRow tableRow;
    AutoCompleteTextView matricule;
    TextView nom_prof,total;
    int somme=0;
    public static BulletinPaieview newInstance(){
        return (new BulletinPaieview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        final View view = inflater.inflate(R.layout.bulletin_paie_view,container,false);
        tableLayout = view.findViewById(R.id.tablepaie);
        nom_prof = view.findViewById(R.id.nom_professeur);
        total = view.findViewById(R.id.total_montant);

        matricule = view.findViewById(R.id.matricule_prof);
        ArrayList<String> matriculeList = Listdematricule.getAll();
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,matriculeList);
        matricule.setAdapter(stringArrayAdapter);

        matricule.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nom_prof.setText("NOM : "+getnomProf(matricule.getText().toString()));
                try {
                    GetBulletindepaie getBulletindepaie = new GetBulletindepaie(matricule.getText().toString());
                    Thread thread = new Thread(getBulletindepaie);
                    thread.start();
                    thread.join();
                    String jsonString = "";
                    jsonString=getBulletindepaie.getResponse();
                    jsonString = "{\"liste\":"+jsonString.trim()+"}";//substring(4).trim()
                    if (jsonString!=null){
                        try {
                            JSONObject jsonObject1 = new JSONObject(jsonString);
                            JSONArray jsonArray =  jsonObject1.getJSONArray("liste");
                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                tableRow = new TableRow(getContext());
                                TableRow tableRow1 = new TableRow(getContext());
                                if(i==0){
                                    for (int x=0;x<4;x++){
                                        TextView cell0 = new TextView(getContext());
                                        cell0.setBackgroundResource(R.drawable.cell_shape);
                                        cell0.setPadding(15,20,15,20);
                                        cell0.setTextSize(10);

                                        if(x==0){
                                            cell0.setText("DESIGNATION");
                                        }
                                        if(x==1) {
                                            cell0.setText("TAUX HORAIRE");
                                        }
                                        if(x==2){
                                            cell0.setText("NBHEURES");
                                        }
                                        if(x==3){
                                            cell0.setText("MONTANT");
                                        }
                                        tableRow1.addView(cell0);
                                    }
                                        tableLayout.addView(tableRow1);
                                }

                                for (int j=0;j<4;j++){


                                    TextView cell = new TextView(getContext());
                                    cell.setBackgroundResource(R.drawable.cell_shape);
                                    cell.setPadding(15,20,15,20);
                                    cell.setTextSize(10);

                                    if(j==0){
                                        cell.setText(jsonObject.getString("designation"));
                                    }
                                    if(j==1) {
                                        cell.setText(jsonObject.getString("tauxhoraire"));
                                    }
                                    if(j==2){
                                        cell.setText(jsonObject.getString("nbheure"));
                                    }
                                    if(j==3){
                                        cell.setText(jsonObject.getString("montant"));
                                        somme+=jsonObject.getInt("montant");
                                    }
                                    tableRow.addView(cell);

                                }

                                tableLayout.addView(tableRow);
                            }

                            total.setText("TOTAL : " +somme+" Ar ");

                        }catch (JSONException e){
                            System.out.println("JSONException : "+e.getMessage());
                        }
                    }

                }catch (Exception e){
                    System.out.println(" Exception List Prof : "+e.getMessage());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });








//        for (int i=0;i<8;i++){//line
//
//            for (int j=0;j<4;j++){//column
//                TextView cell = new TextView(getContext());
//                cell.setBackgroundResource(R.drawable.cell_shape);
//
//                cell.setText(i +" , "+j);
//                cell.setPadding(50,20,50,20);
//                tableRow.addView(cell);
//            }
//            tableLayout.addView(tableRow);
//        }
       // linearLayout.addView(tableLayout);
        return view;
    }

    private String getnomProf(String matricule){
        String resultat="";
        try {
            Getnom getnom = new Getnom(matricule);
            Thread thread = new Thread(getnom);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString=getnom.getResponse();
            jsonString = "{\"liste\":"+jsonString.trim()+"}";//substring(4).trim()
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
                        return professeur.getNomProf();
                    }
                }catch (JSONException e){
                    System.out.println("JSONException : "+e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println(" Exception List Prof : "+e.getMessage());
        }
        return "";
    }





}
