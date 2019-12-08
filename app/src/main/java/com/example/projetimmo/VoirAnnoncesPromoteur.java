package com.example.projetimmo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetimmo.Models.Categorie;
import com.example.projetimmo.Models.Location;
import com.example.projetimmo.Models.Notes;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.utils.RecyclerViewAdapter;
import com.example.projetimmo.utils.TinyDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VoirAnnoncesPromoteur extends AppCompatActivity {

      private Context context = VoirAnnoncesPromoteur.this;
      List<itemToSell> annonces;
      TextView text_view;
      ImageView back;


      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_voir_annonces_promoteur);
            TinyDB tinydb = new TinyDB(getApplicationContext());
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);

            back = findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent i = new Intent(VoirAnnoncesPromoteur.this, DashbordActivity.class);
                        startActivity(i);
                  }
            });


            text_view = findViewById(R.id.text_view);
            text_view.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
//                        Intent i = new Intent(VoirAnnoncesPromoteur.this, FilterActivity.class);
//                        startActivity(i);
                  }
            });
      }
}
