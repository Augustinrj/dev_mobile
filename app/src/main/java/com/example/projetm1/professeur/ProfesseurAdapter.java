package com.example.projetm1.professeur;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Professeur;
import com.example.projetm1.Professeurview;
import com.example.projetm1.R;
import com.example.projetm1.professeur.shared.DeleteProf;

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
    public void onBindViewHolder(final ProfesseurViewHolder holder, final int position) {
        // Cet country in countries via position
        final Professeur professeur = this.professeurList.get(position);
            int imageResId = this.getDrawableResIdByName(professeur.getImageProf());
            // Bind data to viewholder
            holder.professeurIcon.setImageResource(R.drawable.user_prof);
            holder.textView1.setText(professeur.getNumMat());
            holder.textView2.setText(professeur.getNomProf());
            holder.textView3.setText("");
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
                                    Intent i=new Intent(context, EditProfesseur.class);
                                    i.putExtra("matricule",professeur.getNumMat());
                                    i.putExtra("name",professeur.getNomProf());
                                    context.startActivity(i);
                                    break;

                                case R.id.delete_option:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Voulez vous vraiment supprimer ceci ("+professeur.getNumMat()+") ?")
                                                  .setCancelable(false)
                                                  .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          try {
                                                                  DeleteProf deleteProf = new DeleteProf(professeur.getNumMat());
                                                                  Thread thread = new Thread(deleteProf);
                                                                  thread.start();
                                                                  thread.join();
                                                                  if(deleteProf.getResponse()){
                                                                      professeurList.remove(position);
                                                                      notifyDataSetChanged();
                                                                      Toast.makeText(context,"Supprimer  " + professeur.getNomProf(), Toast.LENGTH_LONG).show();
                                                                  }
                                                          }catch (Exception e){
                                                              System.out.println("Supprimer prof : "+e);
                                                          }

                                                      }
                                                  })
                                                  .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialog, int which) {
                                                          Toast.makeText(context,"Suppression annulé  " + professeur.getNomProf(), Toast.LENGTH_LONG).show();
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
        return this.professeurList.size();
    }

    // Find Image ID corresponding to the name of the image (in the directory drawable).
    public int getDrawableResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        //Log.i(Professeurview.LOG_TAG, "Res Name: "+ resName+"==> Res ID = "+ resID +" Nombre item :"+this.getItemCount());
        return resID;
    }

    // Click on RecyclerView Item.
    private void handleRecyclerItemClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildLayoutPosition(itemView);
        Professeur professeur  = this.professeurList.get(itemPosition);

        Toast.makeText(this.context, professeur.getNomProf(), Toast.LENGTH_LONG).show();
    }


}
