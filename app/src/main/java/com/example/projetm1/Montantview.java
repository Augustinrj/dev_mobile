package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Montantview extends Fragment {
    public static Montantview newInstance(){
        return (new Montantview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return  inflater.inflate(R.layout.montant_view,container,false);
    }
}