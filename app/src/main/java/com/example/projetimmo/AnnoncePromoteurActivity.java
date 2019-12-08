package com.example.projetimmo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class AnnoncePromoteurActivity extends AppCompatActivity {

    TextView nommm , localisationnn , dateee , prixxx ;
    ViewFlipper viewFlipper ;
    ImageView thumbnail , back ;
    GridView gditems ;
    Button modif ;
    String[] atout = {"Camera", "Climatisation", "Espace vert", "Parking", "chauffage", "Ascenseur"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_promoteur);

        modif = findViewById(R.id.modif);
        viewFlipper = findViewById(R.id.v_flipper);
        nommm = findViewById(R.id.text1);
        localisationnn = findViewById(R.id.adressee);
        dateee = findViewById(R.id.datee);
        prixxx = findViewById(R.id.price);
        thumbnail = findViewById(R.id.bienthumbnail);
        gditems = findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gditems.setAdapter(customAdapter);
        back = findViewById(R.id.back);

        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnnoncePromoteurActivity.this,ModifierAnnonceActivity.class);
                startActivity(i);
            }
        });






        int image[] ={R.drawable.image1 , R.drawable.image2 , R.drawable.image3};

        for(int images:image){
            flipperImages(images);
        }





        Intent intent = getIntent();
        String nomm = intent.getExtras().getString("nom");
        String localisationn = intent.getExtras().getString("localisation");
        String datee = intent.getExtras().getString("date");
        String prixx = intent.getExtras().getString("prix");
        int thumbnailll = intent.getExtras().getInt("thumbnail");



        nommm.setText(nomm);
        localisationnn.setText(localisationn);
        dateee.setText(datee);
        prixxx.setText(prixx);
        thumbnail.setImageResource(thumbnailll);

    }
    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return atout.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.row_atouts,null);
            TextView textt = view1.findViewById(R.id.atouts);
            textt.setText(atout[position]);
            return view1;
        }
    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

}
