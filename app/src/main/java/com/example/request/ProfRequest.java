package com.example.request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.model.Professeur;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProfRequest {
    private Context context;
    private RequestQueue queue;
    public int saved;
    public  String reponse;

    public ProfRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
        this.saved = 0;
        reponse ="";
    }



//    public boolean save (final ProfesseurShare professeur){
//        String url = "http://192.168.43.24/back-android/Controller/ProfesseurCtrl.php";
//        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try{
//                    JSONObject  reader = new JSONObject(response);
//                    String nom = reader.getString("message");
//                    Log.i("REPONSE", "Response add prof: "+nom);
//                    Toast.makeText(context,"Message : "+nom,Toast.LENGTH_LONG).show();
//                }catch (JSONException e){
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//               // Log.d("APP","ERROR : "+error);                        AVD
//                Toast.makeText(context,"Erreur de connexion au serveur",Toast.LENGTH_LONG).show();
//            }
//        }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map = new HashMap<>();
//                map.put("matricule",professeur.getNumMat());
//                map.put("name",professeur.getNomProf());
//                map.put("instruction","1");
//                return map;
//            }
//        };
//        queue.add(request);
//
//        return false;
//    }



}
