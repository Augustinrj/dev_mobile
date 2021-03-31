package com.example.projetm1.professeur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.projetm1.FirstActivity;
import com.example.projetm1.R;

public class AddProfesseur extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_professeur);
        super.onCreate(savedInstanceState);
        this.configureToolbar();

    }
    private  void configureToolbar(){
        this.toolbar = (Toolbar) findViewById(R.id.first_activity_main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), FirstActivity.class));
                    }
                }
        );
    }
}
