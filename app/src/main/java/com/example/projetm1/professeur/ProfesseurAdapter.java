package com.example.projetm1.professeur;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Professeur;
import com.example.projetm1.Professeurview;
import com.example.projetm1.R;

import java.util.List;

public class ProfesseurAdapter extends RecyclerView.Adapter<ProfesseurViewHolder> {
    private List<Professeur> professeurList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public ProfesseurAdapter(Context context, List<Professeur> datas ) {
        this.context = context;
        this.professeurList = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ProfesseurViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        View recyclerViewItem = mLayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewlayout, parent, false);

        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecyclerItemClick( (RecyclerView)parent, v);
            }
        });
        return new ProfesseurViewHolder(recyclerViewItem);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(ProfesseurViewHolder holder, final int position) {
        // Cet country in countries via position
        Professeur professeur = this.professeurList.get(position);
            int imageResId = this.getDrawableResIdByName(professeur.getImageProf());
            // Bind data to viewholder
            holder.professeurIcon.setImageResource(R.drawable.user_prof);
            holder.textView1.setText(professeur.getNumMat());
            holder.textView2.setText(professeur.getNomProf());
            holder.textView3.setText("");

    }

    @Override
    public int getItemCount() {
        return this.professeurList.size();
    }

    // Find Image ID corresponding to the name of the image (in the directory drawable).
    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i(Professeurview.LOG_TAG, "Res Name: "+ resName+"==> Res ID = "+ resID +" Nombre item :"+this.getItemCount());
        return resID;
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Professeur professeur  = this.professeurList.get(itemPosition);

        Toast.makeText(this.context, professeur.getNomProf(), Toast.LENGTH_LONG).show();
    }
}
