package com.example.projetm1.volumehoraire.share;
import com.example.model.Volumehoraire;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VolumehoraireShare {
    public static List<Volumehoraire> volumehoraireListByString(String chaine) {
        List<Volumehoraire> list = new ArrayList<Volumehoraire>();
        try {
            ListBystringVolume listBystringVolume = new ListBystringVolume(chaine);
            Thread thread = new Thread(listBystringVolume);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString = listBystringVolume.getJsonString();
            jsonString = "{\"liste\":" + jsonString.substring(4).trim() + "}";
            // jsonString = "{ \"liste\" :[{\"id\":\"1\",\"matricule\":\"455452\",\"name\":\"gfferrr\"},{\"id\":\"2\",\"matricule\":\"57555\",\"name\":\"Defk\"}]}";
            if (jsonString != null) {
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject1.getJSONArray("liste");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //System.out.println(jsonObject.getString("designation"));
                        Volumehoraire volumehoraire = new Volumehoraire();
                        volumehoraire.setId(jsonObject.getInt("id"));
                        volumehoraire.setNumat(jsonObject.getString("numat"));
                        volumehoraire.setMatricule(jsonObject.getString("matricule"));
                        volumehoraire.setTauxhoraire(jsonObject.getInt("tauxhoraire"));
                        list.add(volumehoraire);
                    }
                } catch (JSONException e) {
                    System.out.println("JSONException : " + e.getMessage());
                }
            }


            return list;
        } catch (Exception e) {
            System.out.println(" Exception List Volume Horaire String : " + e.getMessage());
        }

        return list;
    }

    public static Boolean deleteVolume(Volumehoraire volumehoraire) {
        boolean saved = false;
        String nextLine, jsonString = "";
        try {
            String link = "http://192.168.43.24/back-android/Controller/VolumehoraireCtrl.php";
            URL url = new URL(link);
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry = "{\"instruction\": 6,\"numat\":\"" + volumehoraire.getNumat() + "\",\"matricule\":\"" + volumehoraire.getMatricule() + "\"}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0, input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                while (true) {
                    nextLine = buff.readLine();
                    if (nextLine != null) {
                        jsonString += nextLine;
                    } else {
                        break;
                    }

                }

                con.disconnect();
                return true;
            } catch (IOException e) {
                return false;
            }
        } catch (
                MalformedURLException e) {
            return false;
        }
    }

    public static Boolean AddVolume(Volumehoraire volumehoraire) {
        boolean saved = false;
        String nextLine, jsonString = "";
        try {
            String link = "http://192.168.43.24/back-android/Controller/VolumehoraireCtrl.php";
            URL url = new URL(link);//localhost:8080/Produits
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry = "{\"instruction\": 1,\"numat\":\"" + volumehoraire.getNumat() + "\",\"matricule\":\"" + volumehoraire.getMatricule() + "\",\"tauxhoraire\":" + volumehoraire.getTauxhoraire() + "}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0, input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                // Read and print the lines from index.html
                while (true) {
                    nextLine = buff.readLine();
                    if (nextLine != null) {
                        jsonString += nextLine;
                    } else {
                        break;
                    }

                }
                con.disconnect();
                return  true;
            } catch (IOException e) {
               return false;
            }
        } catch (MalformedURLException e) {
           return false;
        }
    }

    public static Boolean EditVolume(Volumehoraire volumehoraire) {
        boolean saved = false;
        String nextLine, jsonString = "";
        try {
            String link = "http://192.168.43.24/back-android/Controller/VolumehoraireCtrl.php";
            URL url = new URL(link);
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry = "{\"instruction\": 5,\"matricule\":\"" + volumehoraire.getMatricule() + "\",\"numat\":\"" + volumehoraire.getNumat() + "\",\"tauxhoraire\":\"" + volumehoraire.getTauxhoraire() + "\",\"id\":\""+volumehoraire.getId()+"\"}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0, input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                while (true) {
                    nextLine = buff.readLine();
                    if (nextLine != null) {
                        jsonString += nextLine;
                    } else {
                        break;
                    }

                }

                con.disconnect();
                return true;
            } catch (IOException e) {
                return false;
            }
        } catch (MalformedURLException e) {
            return false;
        }
    }
}