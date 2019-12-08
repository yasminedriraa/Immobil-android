package com.example.projetimmo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.projetimmo.Models.Categorie;
import com.example.projetimmo.Models.Location;
import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.utils.RecyclerViewAdapterAnnProm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnnoncesPromoteur extends AppCompatActivity {

    List<itemToSell> annonces;
    ImageView back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_promoteur);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnoncesPromoteur.this,DashbordActivity.class);
                startActivity(i);
            }
        });
        //recyclerview
        annonces = new ArrayList<>();
//        annonces.add(new itemToSell(1, "Berbes des champs", new Location(1, "sd", "sd", "sd", "monastir"),
//                new Categorie(1, "hello"), new ArrayList<Notes>(), "qqs", 1, "sdsd", Calendar.getInstance().getTime(), "Berbes des champs", 1, 5, "https://logos-download.com/wp-content/uploads/2016/05/FC_Barcelona_logo_logotipo_crest-690x700.png"));
//        annonces.add(new itemToSell(1, "Les 3 M", new Location(1, "sd", "sd", "sd", "Tunis"),
//                new Categorie(1, "hello"), new ArrayList<Notes>(), "qqs", 1, "sdsd", Calendar.getInstance().getTime(), "Berbes des champs", 1, 5, "https://logos-download.com/wp-content/uploads/2016/05/FC_Barcelona_logo_logotipo_crest-690x700.png"));
//        annonces.add(new itemToSell(1, "Les champs elizee", new Location(1, "sd", "sd", "sd", "sfax"),
//                new Categorie(1, "hello"), new ArrayList<Notes>(), "qqs", 1, "sdsd", Calendar.getInstance().getTime(), "Berbes des champs", 1, 5, "https://logos-download.com/wp-content/uploads/2016/05/FC_Barcelona_logo_logotipo_crest-690x700.png"));
//
//        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerviewfav);
//        RecyclerViewAdapterAnnProm myAdapter = new RecyclerViewAdapterAnnProm(this, annonces);
//        myrv.setLayoutManager(new GridLayoutManager(this, 2));
//        myrv.setAdapter(myAdapter);
    }
}
