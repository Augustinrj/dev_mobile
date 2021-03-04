package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Diagrammeview extends Fragment {
    public static Diagrammeview newInstance(){
        return (new Diagrammeview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return  inflater.inflate(R.layout.diagramme_view,container,false);
    }
}
