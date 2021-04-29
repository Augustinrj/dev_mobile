package com.example.projetm1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.model.Professeur;
import com.example.projetm1.montant.Getmontant;
import com.example.projetm1.professeur.shared.Getnom;
import com.example.projetm1.volumehoraire.share.Listdematricule;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Diagrammeview extends Fragment {
    int montant=0,somme=0;
    TextView afficheMontant;
    PieChart pieChart;int couleur;
    public static Diagrammeview newInstance(){
        return (new Diagrammeview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.diagramme_view,container,false);
        LinearLayout linearLayout = view.findViewById(R.id.legend_chart);
        pieChart = view.findViewById(R.id.piechart);
//        pieChart.addPieSlice(new PieModel("legende1",5, Color.parseColor("#ffaec8")));
//        pieChart.addPieSlice(new PieModel("legende2",15, Color.parseColor("#beaeff")));
//        pieChart.addPieSlice(new PieModel("legende3",25, Color.parseColor("#aeffbd")));
//        pieChart.addPieSlice(new PieModel("legende4",35, Color.parseColor("#ffdcae")));
       // afficheMontant = view.findViewById(R.id.montant_all);
        ArrayList<String> matriculeList = Listdematricule.getAll();
        String nom_prof="",jsonString="";
        int iter=0;
        for(String matricule : matriculeList){

            nom_prof = getnomProf(matricule);
            Getmontant getmontant = new Getmontant(matricule);
            try {
                Thread thread = new Thread(getmontant);
                thread.start();
                thread.join();
                jsonString = getmontant.getResponse();
                jsonString = jsonString.trim();
                System.out.println("Afficher  : "+jsonString);
                if (jsonString!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);
                        montant = (jsonObject.getInt("montant"));

                        TextView cell = new TextView(getContext());
                        cell.setText("|::| "+matricule);
                        cell.setTextSize(15);



                        if(iter==1){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#D81B60")));
                            cell.setBackgroundColor(Color.parseColor("#D81B60"));
                        }
                        else if(iter==2){
                            pieChart.addPieSlice(new PieModel(matricule,montant,Color.parseColor("#958686")));
                            cell.setBackgroundColor(Color.parseColor("#958686"));
                        }
                        else if(iter==3){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#c4d712")));
                            cell.setBackgroundColor(Color.parseColor("#c4d712"));
                        }
                        else if(iter==4){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#00574B")));
                            cell.setBackgroundColor(Color.parseColor("#00574B"));
                        }
                        else if(iter==5){
                            pieChart.addPieSlice(new PieModel(matricule,montant,Color.parseColor("#008577")));
                            cell.setBackgroundColor(Color.parseColor("#008577"));
                        }
                        else if(iter==6){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#1a0fea")));
                            cell.setBackgroundColor(Color.parseColor("#1a0fea"));
                        }
                        else if(iter==7){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#914012")));
                            cell.setBackgroundColor(Color.parseColor("#914012"));
                        }
                        else if(iter==8){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#09b7ec")));
                            cell.setBackgroundColor( Color.parseColor("#09b7ec"));
                        }
                        else if(iter==9){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#ffaec8")));
                            cell.setBackgroundColor(Color.parseColor("#ffaec8"));
                        }
                        else  if(iter==10){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#248509")));
                            cell.setBackgroundColor(Color.parseColor("#248509"));
                        }
                        else if(iter==11){
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#ef620a")));
                            cell.setBackgroundColor(Color.parseColor("#ef620a"));
                        }
                        else if(iter==12){
                            pieChart.addPieSlice(new PieModel(matricule,montant,Color.parseColor("#f10b0b")));
                            cell.setBackgroundColor(Color.parseColor("#f10b0b"));
                        }

                        else {
                            pieChart.addPieSlice(new PieModel(matricule,montant, Color.parseColor("#00574B")));
                            cell.setBackgroundColor(Color.parseColor("#00574B"));
                        }
                        linearLayout.addView(cell);
                    }catch (JSONException e){
                        System.out.println("JSONException : "+e.getMessage());
                    }
                }

            }catch (Exception e){
                System.out.println("EXCEPTION THREAD : "+e.getMessage());
            }
           iter++;
        }
        pieChart.startAnimation();
        //afficheMontant.setText(" MONTANT : "+somme+" Ar ");
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
