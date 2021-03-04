package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Matiereview extends Fragment {
    public static Matiereview newInstance(){
        return (new Matiereview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return  inflater.inflate(R.layout.matiere_view,container,false);
    }
}
