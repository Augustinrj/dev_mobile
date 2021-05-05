package com.example.projetm1.volumehoraire.imported;

import com.example.model.Matiere;
import com.example.model.Professeur;
import com.example.projetm1.matiere.shared.GetdesignMat;
import com.example.projetm1.professeur.shared.Getnom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Publicget {
    public static String getnomProf(String matricule){
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

    public static String getdesignMat(String numatiere){
        String resultat="";String jsonString = "";
        try {
            GetdesignMat getdesignMatiere = new GetdesignMat(numatiere);
            Thread thread = new Thread(getdesignMatiere);
            thread.start();
            thread.join();

            jsonString=getdesignMatiere.getResponse();
            jsonString = "{\"liste\":"+jsonString.trim()+"}";//substring(4).trim()
            System.out.println(" ----------------"+jsonString);
            if (jsonString!=null){
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray =  jsonObject1.getJSONArray("liste");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       System.out.println(" ----------------"+jsonObject.getString("designation"));
                        Matiere matiere = new Matiere();
                        matiere.setNumat(jsonObject.getString("numat"));
                        matiere.setDesignation(jsonObject.getString("designation"));
                        matiere.setNbheures(jsonObject.getInt("nbheure"));
                        return matiere.getDesignation();
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
