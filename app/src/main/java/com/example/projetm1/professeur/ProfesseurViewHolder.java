package com.example.projetm1.professeur;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;

public class ProfesseurViewHolder extends RecyclerView.ViewHolder {
    ImageView professeurIcon;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    public ProfesseurViewHolder (@NonNull View itemView){
        super(itemView);
        this.professeurIcon = (ImageView) itemView.findViewById(R.id.imageview_prof);
        this.textView1 = (TextView) itemView.findViewById(R.id.textView_1);
        this.textView2 = (TextView) itemView.findViewById(R.id.textView_2);
        this.textView3  = (TextView) itemView.findViewById(R.id.textView_3);
    }
}
