package com.example.projetimmo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilPromoteur extends AppCompatActivity {
    private static final String TAG = "ProfilActivity" ;
    private Context context = ProfilPromoteur.this;
    ImageView editt ;
    TextView gerernotes  ;
    ImageView retourr ;
    TextView deco ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_promoteur);
        editt = (ImageView) findViewById(R.id.edit);
        gerernotes = findViewById(R.id.gerernotes);
        retourr = findViewById(R.id.retour);
        deco = findViewById(R.id.deco);

        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilPromoteur.this,HomeActivity.class);
                startActivity(i);
            }
        });

        retourr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilPromoteur.this,DashbordActivity.class);
                startActivity(i);
            }
        });


        editt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfilPromoteur.this,EditProfilActivity.class);
                startActivity(i);
            }
        });
//        gerernotes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(ProfilPromoteur.this,GererNotesActivity.class);
//                startActivity(i);
//            }
//        });
    }


}
