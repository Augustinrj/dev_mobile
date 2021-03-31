package com.example.projetm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity{
    private ProgressBar progressBar;
    @Override
        public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        setContentView(R.layout.start_app);

        Thread  welcomeThread = new Thread(){
            @Override
                public  void run(){

                    try {
                        super.run();

                      sleep(1700);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        Intent i = new Intent(MainActivity.this,FirstActivity.class);
                        startActivity(i);
                        finish();
                    }
            }
        };
        welcomeThread.start();
    }
}