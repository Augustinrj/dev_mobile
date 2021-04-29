package com.example.projetm1.montant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BulletinpaieShare {
    public static String getAll(String matricule){
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/BulletinpaieCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 7,\"matricule\":\""+matricule+"\"}";
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
                return  jsonString;
            }catch(IOException e){
                System.out.println("Erreur "+e.getMessage());
                return jsonString;
            }
        }catch(
                MalformedURLException e){
            System.out.println("Erreur "+e.getMessage());
            return jsonString;
        }
    }

    public static String getMontant(String matricule){
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/BulletinpaieCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 8,\"matricule\":\""+matricule+"\"}";
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
                return  jsonString;
            }catch(IOException e){
                System.out.println("Erreur "+e.getMessage());
                return jsonString;
            }
        }catch(
                MalformedURLException e){
            System.out.println("Erreur "+e.getMessage());
            return jsonString;
        }

    }
}
