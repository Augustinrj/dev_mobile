package com.example.projetm1.matiere;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetm1.R;

public class MatiereViewHolder extends RecyclerView.ViewHolder {
    TextView textView1;
    TextView textView2;
    TextView textView3,textView_option;
    public MatiereViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView1 = (TextView) itemView.findViewById(R.id.textView_1);
        this.textView2 = (TextView) itemView.findViewById(R.id.textView_2);
        this.textView3  = (TextView) itemView.findViewById(R.id.textView_3);
        this.textView_option = (TextView) itemView.findViewById(R.id.textView_options);
    }
}
