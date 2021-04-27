package com.example.projetm1.volumehoraire.share;

import com.example.model.Professeur;
import com.example.projetm1.professeur.shared.ListProfesseur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Listdematricule {
    public static ArrayList<String> getAll(){
        ArrayList<String> list = new ArrayList<>();
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
                        list.add(professeur.getNumMat());
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
