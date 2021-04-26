package com.example.projetm1.matiere;
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

        import com.example.model.Matiere;
        import com.example.projetm1.Matiereview;
        import com.example.projetm1.R;
        import com.example.projetm1.matiere.shared.DeleteMat;
        // import com.example.projetm1.Matiere.shared.DeleteProf;

        import java.util.List;

public class MatiereAdapter extends RecyclerView.Adapter<MatiereViewHolder> {
    private List<Matiere> matiereList;
    private Context context;
    private LayoutInflater mLayoutInflater;


    public MatiereAdapter(Context context, List<Matiere> datas ) {
        this.context = context;
        this.matiereList = datas;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MatiereViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Inflate view from recyclerview_item_layout.xml
        View recyclerViewItem = mLayoutInflater.from(parent.getContext()).inflate(R.layout.matierecardview, parent, false);

        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRecyclerItemClick( (RecyclerView)parent, v);
            }
        });
        return new MatiereViewHolder(recyclerViewItem);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindViewHolder(final MatiereViewHolder holder, final int position) {
        // Cet country in countries via position
        final Matiere matiere = this.matiereList.get(position);
        holder.textView1.setText(matiere.getNumat());
        holder.textView2.setText(matiere.getDesignation());
        holder.textView3.setText(""+matiere.getNbheures());
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
                                Intent i=new Intent(context, EditMatiere.class);
                                i.putExtra("numMat",matiere.getNumat());
                                i.putExtra("designation",matiere.getDesignation());
                                i.putExtra("nbheure",matiere.getNbheures());
                                context.startActivity(i);
                                break;

                            case R.id.delete_option:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Voulez vous vraiment supprimer ceci ("+matiere.getNumat()+") ?")
                                        .setCancelable(false)
                                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                try {
                                                    DeleteMat deleteMat = new DeleteMat(matiere.getNumat());
                                                    Thread thread = new Thread(deleteMat);
                                                    thread.start();
                                                    thread.join();
                                                    if(deleteMat.getResponse()){
                                                        matiereList.remove(position);
                                                        notifyDataSetChanged();
                                                        Toast.makeText(context,"Supprimer  " + matiere.getDesignation(), Toast.LENGTH_LONG).show();
                                                    }
                                                }catch (Exception e){
                                                    System.out.println("Supprimer prof : "+e);
                                                }

                                            }
                                        })
                                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(context,"Suppression annulé  " + matiere.getDesignation(), Toast.LENGTH_LONG).show();
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
        return this.matiereList.size();
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
        Matiere matiere  = this.matiereList.get(itemPosition);

        Toast.makeText(this.context, matiere.getDesignation(), Toast.LENGTH_LONG).show();
    }


}
