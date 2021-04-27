package com.example.projetm1.volumehoraire.share;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListBystringVolume implements Runnable {
    private String chaine;
    public volatile String  jsonString;

    public ListBystringVolume(String chaine) {
        this.chaine = chaine;
    }

    @Override
    public void run() {

        String nextLine;

        try{
            String link = "http://192.168.43.24/back-android/Controller/VolumehoraireCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 4,\"chaine\":\""+chaine+"\"}";
                OutputStream os = con.getOutputStream();
                byte[] input = jsonEntry.getBytes("UTF-8");
                os.write(input, 0,input.length);
                os.close();
                InputStreamReader inStream = new InputStreamReader(con.getInputStream(), "UTF8");
                BufferedReader buff = new BufferedReader(inStream);
                while (true){
                    nextLine =buff.readLine();
                    if (nextLine !=null){
                        //System.out.println(nextLine);
                        jsonString += nextLine;
                    }else{
                        break;
                    }

                }
//                System.out.println("Resultat : --- "+jsonString);


                con.disconnect();
            }catch(IOException e){

                System.out.println("IOException : " +e.getMessage());
            }
        }catch(MalformedURLException e){

        }
    }
    public String getJsonString(){
        return jsonString;
    }
}
