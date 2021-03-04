package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Professeur;
import com.example.projetm1.professeur.ProfesseurAdapter;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.LinearLayoutManager.*;

public class Professeurview extends Fragment {
    public static final String LOG_TAG = "AndroidExample";
    private RecyclerView recyclerView;
    public static Professeurview newInstance(){
        return (new Professeurview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        List<Professeur> professeurs = getListData();

        View view= inflater.inflate(R.layout.professeur_view,container,false);
        this.recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(new ProfesseurAdapter(getContext(), professeurs));

        // RecyclerView scroll vertical
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private  List<Professeur> getListData() {
        List<Professeur> list = new ArrayList<Professeur>();
        Professeur pr01 = new Professeur("nomImage", "PR001", "RAKOTO");
        Professeur pr02 = new Professeur("nomImage", "PR002", "RAKOLO");
        Professeur pr03 = new Professeur("nomImage", "PR003", "RAJAO");
        Professeur pr04 = new Professeur("nomImage", "PR004", "SEDRA");
        Professeur pr05 = new Professeur("nomImage", "PR005", "ANDO");
        Professeur pr06 = new Professeur("nomImage", "PR006", "DINA");
        Professeur pr07 = new Professeur("nomImage", "PR007", "SENTO");
        Professeur pr08 = new Professeur("nomImage", "PR008", "MIARY");

        list.add(pr01);
        list.add(pr02);
        list.add(pr03);
        list.add(pr04);
        list.add(pr05);
        list.add(pr06);
        list.add(pr07);
        list.add(pr08);
        return list;
    }
}
