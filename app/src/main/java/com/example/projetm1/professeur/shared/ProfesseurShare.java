package com.example.projetm1.professeur.shared;

import com.example.model.Professeur;

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

public class ProfesseurShare {

    public static Boolean AddProfesseur(Professeur professeur){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/ProfesseurCtrl.php";
            URL url = new URL (link);//localhost:8080/Produits
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 1,\"name\":\""+professeur.getNomProf()+"\",\"matricule\":\""+professeur.getNumMat()+"\"}";//"{\"instruction\": 1,\"name\":"+professeur.getNomProf()+",\"matricule\":"+professeur.getNumMat()+"}";
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

                con.disconnect();
                return true;
            }catch(IOException e){
                return false;
            }
        }catch(MalformedURLException e){
            return false;
        }
    }

    public static Boolean EditProfesseur(Professeur professeur){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/ProfesseurCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 5,\"matricule\":\""+professeur.getNumMat()+"\",\"name\":\""+professeur.getNomProf()+"\"}";
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

    public  static Boolean deleteProf(Professeur professeur){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/ProfesseurCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 6,\"matricule\":\""+professeur.getNumMat()+"\",\"name\":\""+professeur.getNomProf()+"\"}";
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

    public static List<Professeur> professeurListByString(String chaine){
            List<Professeur> list = new ArrayList<Professeur>();
            try {
                ListBystringProf listProfesseur = new ListBystringProf(chaine);
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
                            list.add(professeur);
                        }
                    }catch (JSONException e){
                        System.out.println("JSONException : "+e.getMessage());
                    }
                }


                return list;
            }catch (Exception e){
                System.out.println(" Exception List Prof String : "+e.getMessage());
            }

            return list ;
        }

    public  static String getnomProf(Professeur professeur){
        boolean saved = false;
        String nextLine,jsonString="";
        try{
            String link = "http://192.168.43.24/back-android/Controller/ProfesseurCtrl.php";
            URL url = new URL (link);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.setConnectTimeout(5000);
                con.setDoOutput(true);
                con.setDoInput(true);
                String jsonEntry  = "{\"instruction\": 3,\"matricule\":\""+professeur.getNumMat()+"\"}";
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
                return jsonString;
            }
        }catch(MalformedURLException e){
            return jsonString;
        }
    }
}
