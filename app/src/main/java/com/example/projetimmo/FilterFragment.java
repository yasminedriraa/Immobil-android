package com.example.projetimmo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetimmo.Models.Categorie;
import com.example.projetimmo.Models.Location;
import com.example.projetimmo.Models.SearchCriteriaRequest;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.ui.home.HomeFragment;
import com.example.projetimmo.utils.TinyDB;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilterFragment extends Fragment implements AdapterView.OnItemSelectedListener {
      private static final String TAG = "FilterFragment";
      RadioGroup radioGroup;
      RadioButton rb;
      Spinner spinner, spin;
      Button bouton;
      RangeSeekBar rangeSeekBar, rangeSeekBarSurface;
      TextView min, max, minimum, maximum;
      String categories, atouts;
      RadioButton radio1, radio2, radio3, radio4, radio5, radio6;
      RadioButton r1, r2, r3, r4, r5, r6;

      public FilterFragment() {
      }


      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final TinyDB tinydb = new TinyDB(getContext());
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            radioGroup = view.findViewById(R.id.radiogroup);
            bouton = view.findViewById(R.id.rechercher);
            spinner = view.findViewById(R.id.sp);
            spin = view.findViewById(R.id.spin);
            rangeSeekBar = view.findViewById(R.id.rangeseekbar);
            min = view.findViewById(R.id.min);
            max = view.findViewById(R.id.max);
            radio1 = view.findViewById(R.id.radio1);
            radio2 = view.findViewById(R.id.radio2);
            radio3 = view.findViewById(R.id.radio3);
            radio4 = view.findViewById(R.id.radio4);
            radio5 = view.findViewById(R.id.radio5);
            radio6 = view.findViewById(R.id.radio6);
            r1 = view.findViewById(R.id.r1);
            r2 = view.findViewById(R.id.r2);
            r3 = view.findViewById(R.id.r3);
            r4 = view.findViewById(R.id.r4);
            r5 = view.findViewById(R.id.r5);
            r6 = view.findViewById(R.id.r6);
            radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
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
                              r2.setChecked(false);
                              r3.setChecked(false);
                              r4.setChecked(false);
                              r5.setChecked(false);
                              r1.setChecked(false);
                        }
                  }
            });
            rangeSeekBarSurface = view.findViewById(R.id.rangeseekbarsurface);
            minimum = view.findViewById(R.id.minsurface);
            maximum = view.findViewById(R.id.maxsurface);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(getContext(), R.array.ville, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            ArrayAdapter<CharSequence> adapterr = ArrayAdapter
                    .createFromResource(getContext(), R.array.tunisie, android.R.layout.simple_spinner_item);
            adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapterr);
            spin.setOnItemSelectedListener(this);
            bouton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        SearchCriteriaRequest searchCriteriaRequest = new SearchCriteriaRequest();
                        searchCriteriaRequest.setAtout(selectedAtouts());
                        searchCriteriaRequest.setCategory(selectedCategorie());
                        searchCriteriaRequest.setMinPrice(rangeSeekBar.getSelectedMinValue().intValue());
                        searchCriteriaRequest.setMaxPrice(rangeSeekBar.getSelectedMaxValue().intValue());
                        searchCriteriaRequest.setMinSurface(rangeSeekBarSurface.getSelectedMinValue().intValue());
                        searchCriteriaRequest.setMaxSurface(rangeSeekBarSurface.getSelectedMaxValue().intValue());
                        searchCriteriaRequest.setLocation(new Location(0, spin.getSelectedItem().toString(), "", spinner.getSelectedItem().toString(), ""));
                        RetrofitClient.build().getAnnoncesByFilter(searchCriteriaRequest, "Bearer " + userResponse.getToken())
                                .enqueue(new Callback<List<itemToSell>>() {
                                      @Override
                                      public void onResponse(Call<List<itemToSell>> call, Response<List<itemToSell>> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onResponse: All good");
                                                  Log.e(TAG, "onResponse Size: " + response.body().size());
                                                  tinydb.putListItemToSell("filtredObjects", response.body());
                                                  if (userResponse.getClaims().getScopes().equals("ROLE_PROMOTEUR")) {
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.dashboardFrame, new HomeFragment(), "fromFilter")
                                                                .addToBackStack(null)
                                                                .commit();
                                                  } else {
                                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                                .replace(R.id.frame_main_activity, new HomeFragment(), "fromFilter")
                                                                .addToBackStack(null)
                                                                .commit();
                                                  }
                                            } else {
                                                  Log.e(TAG, "onResponse: I found an error code: " + response.code());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<List<itemToSell>> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getMessage());
                                      }
                                });
//                        Intent intent = new Intent(FilterActivity.this, AccueilClient.class);
//                        startActivity(intent);
                  }
            });

            //rangeseekbar

            rangeSeekBar.setRangeValues(0, 200000);
            rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
                  @Override
                  public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                        Log.e("value", minValue + "" + maxValue);
                        min.setText(minValue + "DT");
                        max.setText(maxValue + "DT");
                  }
            });

            rangeSeekBarSurface.setRangeValues(0, 300);
            rangeSeekBarSurface.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
                  @Override
                  public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                        Log.e("value", minValue + "" + maxValue);
                        minimum.setText(minValue + "M2");
                        maximum.setText(maxValue + "M2");
                  }
            });
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_filter, container, false);
      }

      @Override
      public void onAttach(Context context) {
            super.onAttach(context);

      }

      @Override
      public void onDetach() {
            super.onDetach();
      }

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String text = parent.getItemAtPosition(position).toString();
      }


      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }

      public String selectedAtouts() {

            if (r1.isChecked()) {
                  atouts = "CAM_SURVEILLANCE";
                  r2.setChecked(false);
                  r3.setChecked(false);
                  r4.setChecked(false);
                  r5.setChecked(false);
                  r6.setChecked(false);
            } else if (r2.isChecked()) {
                  atouts = "ESPACE_VERT";
                  r1.setChecked(false);
                  r3.setChecked(false);
                  r4.setChecked(false);
                  r5.setChecked(false);
                  r6.setChecked(false);
            } else if (r3.isChecked()) {
                  atouts = "CLIMATISATION";
                  r2.setChecked(false);
                  r1.setChecked(false);
                  r4.setChecked(false);
                  r5.setChecked(false);
                  r6.setChecked(false);
            } else if (r4.isChecked()) {
                  atouts = "CHAUFFAGE";
                  r2.setChecked(false);
                  r3.setChecked(false);
                  r1.setChecked(false);
                  r5.setChecked(false);
                  r6.setChecked(false);
            } else if (r5.isChecked()) {
                  atouts = "ASCENCEUR";
                  r2.setChecked(false);
                  r3.setChecked(false);
                  r4.setChecked(false);
                  r1.setChecked(false);
                  r6.setChecked(false);
            } else if (r6.isChecked()) {
                  atouts = "PARKING";
                  r2.setChecked(false);
                  r3.setChecked(false);
                  r4.setChecked(false);
                  r5.setChecked(false);
                  r1.setChecked(false);
            }
            return atouts;
      }

      public Categorie selectedCategorie() {

            if (radio1.isChecked())
                  categories = radio1.getText().toString();
            else if (radio2.isChecked())
                  categories = radio2.getText().toString();
            else if (radio3.isChecked())
                  categories = radio3.getText().toString();
            else if (radio4.isChecked())
                  categories = radio4.getText().toString();
            else if (radio5.isChecked())
                  categories = radio5.getText().toString();
            else if (radio6.isChecked())
                  categories = radio6.getText().toString();
            return new Categorie(0, categories);
      }

}
