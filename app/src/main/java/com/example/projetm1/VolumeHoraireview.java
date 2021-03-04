package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class VolumeHoraireview extends Fragment {
    public static VolumeHoraireview newInstance(){
        return (new VolumeHoraireview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        return  inflater.inflate(R.layout.volume_horaire_view,container,false);
    }
}
