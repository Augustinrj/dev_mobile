package com.example.projetm1.volumehoraire.share;

import com.example.model.Matiere;
import com.example.projetm1.matiere.shared.ListMatiere;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Listdenumatiere {
    public static ArrayList<String> getAll() {
        ArrayList<String> list = new ArrayList<>();
        try {
            ListMatiere listMatiere = new ListMatiere();
            Thread thread = new Thread(listMatiere);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString = listMatiere.getJsonString();
            jsonString = "{\"liste\":" + jsonString.substring(4).trim() + "}";
            if (jsonString != null) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject1.getJSONArray("liste");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Matiere matiere = new Matiere();
                        matiere.setNumat(jsonObject.getString("numat"));
                        matiere.setDesignation(jsonObject.getString("designation"));
                        matiere.setNbheures(jsonObject.getInt("nbheure"));
                        list.add(matiere.getNumat());
                    }
                } catch (JSONException e) {
                    System.out.println("JSONException : " + e.getMessage());
                }
            }


            return list;
        } catch (Exception e) {
            System.out.println(" Exception List Prof : " + e.getMessage());
            return list;
        }
    }
}
