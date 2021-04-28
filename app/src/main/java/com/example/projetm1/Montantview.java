package com.example.projetm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Montantview extends Fragment {
    TableLayout tableLayout;
    public static Montantview newInstance(){
        return (new Montantview());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.montant_view,container,false);
        tableLayout = view.findViewById(R.id.tableheurecomp);
        for (int i=0;i<8;i++){//line
            TableRow tableRow = new TableRow(getContext());
            for (int j=0;j<3;j++){//column
                TextView cell = new TextView(getContext());
                cell.setBackgroundResource(R.drawable.cell_shape);

                cell.setText(i +" , "+j);
                cell.setPadding(80,20,80,20);
                tableRow.addView(cell);
            }
            tableLayout.addView(tableRow);
        }
        // linearLayout.addView(tableLayout);
        return view;
    }
}
