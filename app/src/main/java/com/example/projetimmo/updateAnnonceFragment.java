package com.example.projetimmo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


public class updateAnnonceFragment extends Fragment {
      RadioGroup radioGroup ;
      RadioButton rb ;
      Spinner spinner , spin ;
      Button bouton ;
      ImageView back ;
      TextView min , max , tvdatee;
      private Context context;
      private DatePickerDialog.OnDateSetListener dateSetListener ;
      private static final String TAG = "updateAnnonceFragment";
      public updateAnnonceFragment(Context context) {
            this.context = context;
      }


      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_update_annonce, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            radioGroup = view.findViewById(R.id.radiogroup);
            bouton = view.findViewById(R.id.rechercher);
            spinner = view.findViewById(R.id.sp);
            spin = view.findViewById(R.id.spin);
            min = view.findViewById(R.id.min);
            max = view.findViewById(R.id.max);
            tvdatee = view.findViewById(R.id.tvdate);
            back = view.findViewById(R.id.back);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(getContext(),R.array.ville,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> parent) {

                  }
            });
            ArrayAdapter<CharSequence> adapterr = ArrayAdapter
                    .createFromResource(getContext(),R.array.tunisie,android.R.layout.simple_spinner_item);
            adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapterr);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  }

                  @Override
                  public void onNothingSelected(AdapterView<?> parent) {

                  }
            });
            tvdatee.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(getContext(),
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

      @Override
      public void onAttach(Context context) {
            super.onAttach(context);

      }

      @Override
      public void onDetach() {
            super.onDetach();
      }

}
