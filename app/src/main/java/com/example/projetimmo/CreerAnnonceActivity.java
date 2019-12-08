package com.example.projetimmo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.Calendar;

public class CreerAnnonceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "CreerAnnonceActivity";
    RadioGroup radioGroup;
    RadioButton rb ;
    Spinner spinner , spin ;
    Button bouton ;
    ImageView back ;
    TextView min , max , tvdatee;
    private DatePickerDialog.OnDateSetListener dateSetListener ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_annonce);
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup = findViewById(R.id.radiogroup);
        bouton = findViewById(R.id.rechercher);
        spinner = findViewById(R.id.sp);
        spin = findViewById(R.id.spin);
        min = findViewById(R.id.min);
        max = findViewById(R.id.max);
        tvdatee = findViewById(R.id.tvdate);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreerAnnonceActivity.this,DashbordActivity.class);
                startActivity(i);
            }
        });


        //spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(this,R.array.ville,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //spinner Gouv
        ArrayAdapter<CharSequence> adapterr = ArrayAdapter
                .createFromResource(this,R.array.tunisie,android.R.layout.simple_spinner_item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterr);
        spin.setOnItemSelectedListener( this);

        //recherche
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreerAnnonceActivity.this,DashbordActivity.class);
                startActivity(intent);
            }
        });



        //datepicker
        tvdatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(CreerAnnonceActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,dateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1 ;
                Log.d(TAG,"ondateset : dd/mm/yyy"+dayOfMonth+"/"+month+"/"+year);
                String date = dayOfMonth + "/" +dayOfMonth+ "/"+year ;
                tvdatee.setText(date);

            }
        };

    }

    public void checked(View v){
        int radiobuttonid = radioGroup.getCheckedRadioButtonId();
        rb = findViewById(radiobuttonid);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
