package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.model.Professeur;
import com.example.projetm1.montant.Getmontant;
import com.example.projetm1.professeur.shared.Getnom;
import com.example.projetm1.volumehoraire.share.Listdematricule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Montantview extends Fragment {
    TableLayout tableLayout;
    private TableRow tableRow;
    int montant=0,somme=0;
    TextView afficheMontant;
    public static Montantview newInstance(){
        return (new Montantview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.montant_view,container,false);
        tableLayout = view.findViewById(R.id.tableheurecomp);
        afficheMontant = view.findViewById(R.id.montant_all);

        ArrayList<String> matriculeList = Listdematricule.getAll();
        String nom_prof="",jsonString="";
        int iter=0;
        for(String matricule : matriculeList){
            /*En tete
            Du Tableau
            */
            tableRow = new TableRow(getContext());
            TableRow tableRow1 = new TableRow(getContext());
            if(iter==0){
                for (int x=0;x<3;x++){
                    TextView cell0 = new TextView(getContext());
                    cell0.setBackgroundResource(R.drawable.cell_shape);
                    cell0.setPadding(15,20,10,20);
                    cell0.setTextSize(15);

                    if(x==0){
                        cell0.setText("MATRICULE");
                    }
                    if(x==1) {
                        cell0.setText("NOM");
                    }
                    if(x==2){
                        cell0.setText("MONTANT");
                    }
                    tableRow1.addView(cell0);
                }
                tableLayout.addView(tableRow1);
            }

            nom_prof = getnomProf(matricule);
            Getmontant getmontant = new Getmontant(matricule);
            try {
                Thread thread = new Thread(getmontant);
                thread.start();
                thread.join();
                jsonString = getmontant.getResponse();
                jsonString = jsonString.trim();
                System.out.println("Afficher 12 : "+jsonString);
                if (jsonString!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);
                          montant = (jsonObject.getInt("montant"));
                    }catch (JSONException e){
                        System.out.println("JSONException : "+e.getMessage());
                    }
                }

            }catch (Exception e){
                System.out.println("EXCEPTION THREAD : "+e.getMessage());
            }
            for(int j=0;j<3;j++){
                TextView cell = new TextView(getContext());
                cell.setBackgroundResource(R.drawable.cell_shape);
                cell.setPadding(15,20,10,20);
                cell.setTextSize(15);

                if(j==0){
                    cell.setText(matricule);
                }
                if(j==1) {
                    cell.setText(nom_prof);
                }
                if(j==2){
                    cell.setText(""+montant);
                    somme+=montant;
                }
                tableRow.addView(cell);
            }

            tableLayout.addView(tableRow);
            iter++;
        }
        afficheMontant.setText(" MONTANT : "+somme+" Ar ");
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
