package com.example.projetm1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Volumehoraire;
import com.example.projetm1.volumehoraire.AddVolumeHoraire;
import com.example.projetm1.volumehoraire.VolumehoraireAdapter;
import com.example.projetm1.volumehoraire.share.ListVolumeHoraire;
import com.example.projetm1.volumehoraire.share.VolumehoraireShare;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolumeHoraireview extends Fragment {
    private TextInputEditText searchtext;
    private FloatingActionButton addvolumehorairebtn;
    List<Volumehoraire> volumehoraires ;
    private RecyclerView recyclerView;
    private VolumehoraireAdapter volumehoraireAdapter;
    public static VolumeHoraireview newInstance(){
        return (new VolumeHoraireview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        final View view =  inflater.inflate(R.layout.volume_horaire_view,container,false);
        addvolumehorairebtn = view.findViewById(R.id.floatin);
        volumehoraires = getListData();
        this.searchtext = view.findViewById(R.id.textinput);

        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(getContext(),"Message : text changed",Toast.LENGTH_LONG).show();
                volumehoraires = VolumehoraireShare.volumehoraireListByString(searchtext.getText().toString());
                volumehoraireAdapter = new VolumehoraireAdapter(getContext(),volumehoraires);
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
                recyclerView.setAdapter(new  VolumehoraireAdapter(getContext(),volumehoraires));
                volumehoraireAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
        addvolumehorairebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getContext(), AddVolumeHoraire.class);
                    startActivity(i);
                }catch (RuntimeException e){

                }
            }
        });
        this.recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new VolumehoraireAdapter(getContext(), volumehoraires));

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    private List<Volumehoraire> getListData() {
        List<Volumehoraire> list = new ArrayList<Volumehoraire>();
        try {
            ListVolumeHoraire listvolumehoraire = new ListVolumeHoraire();
            Thread thread = new Thread(listvolumehoraire);
            thread.start();
            thread.join();
            String jsonString = "";
            jsonString=listvolumehoraire.getJsonString();
            jsonString = "{\"liste\":"+jsonString.substring(4).trim()+"}";
            // jsonString = "{ \"liste\" :[{\"id\":\"1\",\"matricule\":\"455452\",\"name\":\"gfferrr\"},{\"id\":\"2\",\"matricule\":\"57555\",\"name\":\"Defk\"}]}";
            if (jsonString!=null){
                try {
                    JSONObject jsonObject1 = new JSONObject(jsonString);
                    JSONArray jsonArray =  jsonObject1.getJSONArray("liste");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        System.out.println(jsonObject.getInt("id"));
                        Volumehoraire volumehoraire = new Volumehoraire();
                        volumehoraire.setId(jsonObject.getInt("id"));
                        volumehoraire.setNumat(jsonObject.getString("numat"));
                        volumehoraire.setMatricule(jsonObject.getString("matricule"));
                        volumehoraire.setTauxhoraire(jsonObject.getInt("tauxhoraire"));
                        list.add(volumehoraire);
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
