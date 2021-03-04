package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class BulletinPaieview extends Fragment {
    public static BulletinPaieview newInstance(){
        return (new BulletinPaieview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return  inflater.inflate(R.layout.bulletin_paie_view,container,false);
    }
}
