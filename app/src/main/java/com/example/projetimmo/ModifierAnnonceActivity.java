package com.example.projetimmo;

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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifierAnnonceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

      private static final String TAG = "CreerAnnonceActivity";
      RadioGroup radioGroup;
      RadioButton rb;
      Spinner spinner, spin;
      Button bouton;
      ImageView back;
      TextView min, max, tvdatee;
      private DatePickerDialog.OnDateSetListener dateSetListener;
      itemToSell itemToSell;
      Toolbar toolbar;
      EditText name, description, addresse;
      SeekBar price, surface;
      TextView priceShown, surfaceShown;
      RadioButton radio1, radio2, radio3, radio4, radio5, radio6;
      RadioButton r1, r2, r3, r4, r5, r6;
      ImageView picture;
      String atouts, categorie;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.modifierannonce);
            final TinyDB tinydb = new TinyDB(getApplicationContext());
            itemToSell = tinydb.getObject("selectedItem", itemToSell.class);
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            radioGroup = findViewById(R.id.radiogroup);
            bouton = findViewById(R.id.rechercher);
            toolbar = findViewById(R.id.toolbar8);
            spinner = findViewById(R.id.sp);
            spin = findViewById(R.id.spin);
            min = findViewById(R.id.min);
            max = findViewById(R.id.max);
            tvdatee = findViewById(R.id.tvdate);
            price = findViewById(R.id.seek_bar_price);
            surface = findViewById(R.id.seek_bar_surface);
            back = findViewById(R.id.back);
            name = findViewById(R.id.name);
            description = findViewById(R.id.description);
            addresse = findViewById(R.id.addresse);
            priceShown = findViewById(R.id.afficheprix);
            surfaceShown = findViewById(R.id.affichesurface);
            picture = findViewById(R.id.picture);
            radio1 = findViewById(R.id.radio1);
            radio2 = findViewById(R.id.radio2);
            radio3 = findViewById(R.id.radio3);
            radio4 = findViewById(R.id.radio4);
            radio5 = findViewById(R.id.radio5);
            radio6 = findViewById(R.id.radio6);
            r1 = findViewById(R.id.r1);
            r2 = findViewById(R.id.r2);
            r3 = findViewById(R.id.r3);
            r4 = findViewById(R.id.r4);
            r5 = findViewById(R.id.r5);
            r6 = findViewById(R.id.r6);

            radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              categorie = radio1.getText().toString();
                              radio2.setChecked(false);
                              radio3.setChecked(false);
                              radio4.setChecked(false);
                              radio5.setChecked(false);
                              radio6.setChecked(false);
                        }
                  }
            });
            radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              categorie = radio2.getText().toString();
                              radio1.setChecked(false);
                              radio3.setChecked(false);
                              radio4.setChecked(false);
                              radio5.setChecked(false);
                              radio6.setChecked(false);
                        }
                  }
            });
            radio3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              categorie = radio3.getText().toString();
                              radio2.setChecked(false);
                              radio1.setChecked(false);
                              radio4.setChecked(false);
                              radio5.setChecked(false);
                              radio6.setChecked(false);
                        }
                  }
            });
            radio4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              categorie = radio4.getText().toString();
                              radio2.setChecked(false);
                              radio3.setChecked(false);
                              radio1.setChecked(false);
                              radio5.setChecked(false);
                              radio6.setChecked(false);
                        }
                  }
            });
            radio5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              categorie = radio5.getText().toString();
                              radio2.setChecked(false);
                              radio3.setChecked(false);
                              radio4.setChecked(false);
                              radio1.setChecked(false);
                              radio6.setChecked(false);
                        }
                  }
            });
            radio6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              categorie = radio6.getText().toString();
                              radio2.setChecked(false);
                              radio3.setChecked(false);
                              radio4.setChecked(false);
                              radio5.setChecked(false);
                              radio1.setChecked(false);
                        }
                  }
            });
            r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              atouts = r1.getText().toString();
                              r2.setChecked(false);
                              r3.setChecked(false);
                              r4.setChecked(false);
                              r5.setChecked(false);
                              r6.setChecked(false);
                        }
                  }
            });
            r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              atouts = r2.getText().toString();
                              r1.setChecked(false);
                              r3.setChecked(false);
                              r4.setChecked(false);
                              r5.setChecked(false);
                              r6.setChecked(false);
                        }
                  }
            });
            r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              atouts = r3.getText().toString();
                              r2.setChecked(false);
                              r1.setChecked(false);
                              r4.setChecked(false);
                              r5.setChecked(false);
                              r6.setChecked(false);
                        }
                  }
            });
            r4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              atouts = r4.getText().toString();
                              r2.setChecked(false);
                              r3.setChecked(false);
                              r1.setChecked(false);
                              r5.setChecked(false);
                              r6.setChecked(false);
                        }
                  }
            });
            r5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              atouts = r5.getText().toString();
                              r2.setChecked(false);
                              r3.setChecked(false);
                              r4.setChecked(false);
                              r1.setChecked(false);
                              r6.setChecked(false);
                        }
                  }
            });
            r6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                              atouts = r6.getText().toString();
                              r2.setChecked(false);
                              r3.setChecked(false);
                              r4.setChecked(false);
                              r5.setChecked(false);
                              r1.setChecked(false);
                        }
                  }
            });

            name.setText(itemToSell.getTitle());
            description.setText(itemToSell.getDescription());
            addresse.setText(itemToSell.getLocation().getAddress());
            surface.setProgress((int) itemToSell.getSurface());
            price.setProgress(itemToSell.getPrice());
            priceShown.setText(itemToSell.getPrice() + "");
            surfaceShown.setText(itemToSell.getSurface() + "");

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        ModifierAnnonceActivity.super.onBackPressed();
                  }
            });

            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.ville, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            ArrayAdapter<CharSequence> adapterr = ArrayAdapter
                    .createFromResource(this, R.array.tunisie, android.R.layout.simple_spinner_item);
            adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapterr);
            spin.setOnItemSelectedListener(this);
            tvdatee.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(ModifierAnnonceActivity.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                  }
            });
            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        Log.d(TAG, "ondateset : dd/mm/yyy" + dayOfMonth + "/" + month + "/" + year);
                        String date = dayOfMonth + "/" + dayOfMonth + "/" + year;
                        tvdatee.setText(date);

                  }
            };
            bouton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (name.getText().length() > 0)
                              itemToSell.setTitle(name.getText().toString());
                        if (description.getText().length() > 0)
                              itemToSell.setDescription(description.getText().toString());
                        if (addresse.getText().length() > 0)
                              itemToSell.getLocation().setAddress(addresse.getText().toString());
                        itemToSell.setPrice(price.getProgress());
                        itemToSell.setSurface((double) surface.getProgress());
                        itemToSell.getCategorie().setName(categorie);
                        itemToSell.setAtouts(atouts);
                        RetrofitClient.build().updateAnnonce(itemToSell, "Bearer " + userResponse.getToken())
                                .enqueue(new Callback<itemToSell>() {
                                      @Override
                                      public void onResponse(Call<itemToSell> call, Response<itemToSell> response) {
                                            if (response.isSuccessful()) {
                                                  tinydb.putObject("selectedItem", response.body());
                                                  Intent i;
                                                  Toast.makeText(getApplicationContext(), "Vos informations sont modifier", Toast.LENGTH_LONG).show();
                                                  if (userResponse.getClaims().getScopes().contains("PROMO"))
                                                        i = new Intent(ModifierAnnonceActivity.this, DashbordActivity.class);
                                                  else
                                                        i = new Intent(ModifierAnnonceActivity.this, MainActivity.class);
                                                  i.putExtra("fragment", "profile");
                                                  startActivity(i);
                                            } else {
                                                  Log.e(TAG, "onResponse code: " + response.code());
                                                  Log.e(TAG, "onResponse code: " + response.errorBody());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<itemToSell> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                                      }
                                });

                  }
            });
      }

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
}
