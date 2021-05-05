package com.example.projetm1.matiere.shared;

import com.example.model.Matiere;

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

public class MatiereShare {
    public  static Boolean deleteMat(Matiere matiere){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/MatiereCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 6,\"numat\":\""+matiere.getNumat()+"\"}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0,input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                while (true){
                    nextLine =buff.readLine();
                    if (nextLine !=null){
                        jsonString += nextLine;
                    }else{
                        break;
                    }

                }

                con.disconnect();
                return  true;
            }catch(IOException e){
                return false;
            }
        }catch(MalformedURLException e){
            return false;
        }
    }

    public static List<Matiere> matiereListByString(String chaine){
        List<Matiere> list = new ArrayList<Matiere>();
        try {
            ListBystringMatiere listMatiere= new ListBystringMatiere(chaine);
            Thread thread = new Thread(listMatiere);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString=listMatiere.getJsonString();
            jsonString = "{\"liste\":"+jsonString.substring(4).trim()+"}";
            // jsonString = "{ \"liste\" :[{\"id\":\"1\",\"matricule\":\"455452\",\"name\":\"gfferrr\"},{\"id\":\"2\",\"matricule\":\"57555\",\"name\":\"Defk\"}]}";
            if (jsonString!=null){
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray =  jsonObject1.getJSONArray("liste");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                       System.out.println(jsonObject.getString("designation"));
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
            System.out.println(" Exception List Matiere String : "+e.getMessage());
        }

        return list ;
    }

    public static Boolean AddMatiere(Matiere matiere){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/MatiereCtrl.php";
            URL url = new URL (link);//localhost:8080/Produits
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 1,\"numat\":\""+matiere.getNumat()+"\",\"designation\":\""+matiere.getDesignation()+"\",\"nbheure\":"+matiere.getNbheures()+"}";//"{\"instruction\": 1,\"name\":"+professeur.getNomProf()+",\"matricule\":"+professeur.getNumMat()+"}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0,input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                // Read and print the lines from index.html
                while (true){
                    nextLine =buff.readLine();
                    if (nextLine !=null){
                        jsonString += nextLine;
                    }else{
                        break;
                    }

                }
                saved = true;
                con.disconnect();
            }catch(IOException e){
                saved = false;
            }
        }catch(MalformedURLException e){
            saved = false;
        }
        return true;
    }

    public static Boolean EditMatiere(Matiere matiere){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/MatiereCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 5,\"numat\":\""+matiere.getNumat()+"\",\"designation\":\""+matiere.getDesignation()+"\",\"nbheure\":\""+matiere.getNbheures()+"\"}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0,input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                while (true){
                    nextLine =buff.readLine();
                    if (nextLine !=null){
                        jsonString += nextLine;
                    }else{
                        break;
                    }

                }

                con.disconnect();
                return  true;
            }catch(IOException e){
                return false;
            }
        }catch(MalformedURLException e){
            return false;
        }
    }

    public  static String getdesignMat(Matiere matiere){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/MatiereCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 3,\"numat\":\""+matiere.getNumat()+"\"}";
                System.out.println(" Json Entry : "+jsonEntry);
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0,input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                while (true){
                    nextLine =buff.readLine();
                    if (nextLine !=null){
                        jsonString += nextLine;
                    }else{
                        break;
                    }

                }

                con.disconnect();
                System.out.println(" Json debug : "+jsonString);
                return  jsonString;
            }catch(IOException e){
                System.out.println(" Json debug : "+e.getMessage());
                return jsonString;
            }
        }catch(MalformedURLException e){
            System.out.println(" Json debug 2: "+e.getMessage());
            return jsonString;
        }
    }
}
