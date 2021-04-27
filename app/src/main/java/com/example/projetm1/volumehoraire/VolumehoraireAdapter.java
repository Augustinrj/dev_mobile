package com.example.projetm1.volumehoraire;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Volumehoraire;
import com.example.projetm1.R;
import com.example.projetm1.matiere.MatiereViewHolder;
import com.example.projetm1.volumehoraire.share.DeleteVolume;

import java.util.List;

public class VolumehoraireAdapter extends RecyclerView.Adapter<VolumehoraireViewHolder> {
    private List<Volumehoraire> volumehoraireList;
    private Context context;
    private LayoutInflater mLayoutInflater;


    public VolumehoraireAdapter(Context context, List<Volumehoraire> datas ) {
        this.context = context;
        this.volumehoraireList = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public VolumehoraireViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        View recyclerViewItem = mLayoutInflater.from(parent.getContext()).inflate(R.layout.volume_cardview, parent, false);

        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecyclerItemClick( (RecyclerView)parent, v);
            }
        });
        return new VolumehoraireViewHolder(recyclerViewItem);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(final VolumehoraireViewHolder holder, final int position) {
        // Cet country in countries via position
        final Volumehoraire volumehoraire = this.volumehoraireList.get(position);
        holder.textView1.setText(volumehoraire.getNumat());
        holder.textView2.setText(volumehoraire.getMatricule());
        holder.textView3.setText(""+volumehoraire.getTauxhoraire());
        holder.textView_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.textView_option);
                popupMenu.inflate(R.menu.options_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit_option:
                                Intent i=new Intent(context, EditVolumehoraire.class);
                                i.putExtra("numMat",volumehoraire.getNumat());
                                i.putExtra("matricule",volumehoraire.getMatricule());
                                i.putExtra("tauxhoraire",volumehoraire.getTauxhoraire());
                                i.putExtra("id",volumehoraire.getId());
                                context.startActivity(i);
                                break;

                            case R.id.delete_option:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Voulez vous vraiment supprimer ceci ("+volumehoraire.getNumat()+") ?")
                                        .setCancelable(false)
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                try {
                                                    DeleteVolume deleteVolume = new DeleteVolume(volumehoraire.getNumat(),volumehoraire.getMatricule());
                                                    Thread thread = new Thread(deleteVolume);
                                                    thread.start();
                                                    thread.join();
                                                    if(deleteVolume.getResponse()){
                                                        volumehoraireList.remove(position);
                                                        notifyDataSetChanged();
                                                        Toast.makeText(context,"Supprimer  " + volumehoraire.getNumat(), Toast.LENGTH_LONG).show();
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("Supprimer prof : "+e);
                                                }

                                            }
                                        })
                                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(context,"Suppression annulé  " + volumehoraire.getNumat(), Toast.LENGTH_LONG).show();
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.setTitle("Alert suppression");
                                alertDialog.show();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.volumehoraireList.size();
    }

    // Find Image ID corresponding to the name of the image (in the directory drawable).
    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        //Log.i(Matiereview.LOG_TAG, "Res Name: "+ resName+"==> Res ID = "+ resID +" Nombre item :"+this.getItemCount());
        return resID;
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Volumehoraire volumehoraire  = this.volumehoraireList.get(itemPosition);

        Toast.makeText(this.context, volumehoraire.getNumat(), Toast.LENGTH_LONG).show();
    }
}
