package com.example.projetimmo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.projetimmo.Models.Categorie;
import com.example.projetimmo.Models.FavoritRetrievedData;
import com.example.projetimmo.Models.Location;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.RecyclerViewAdapter;
import com.example.projetimmo.utils.TinyDB;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.PRINT_SERVICE;

public class addAnnonceFragment extends Fragment {
      private static final String TAG = "addAnnonceFragment";
      EditText nameET, descriptionET, address, urlVideo;
      RadioGroup categorieRG, atoutsRG;
      SeekBar priceSB, surfaceSB;
      TextView priceTV, surfaceTV, dateTV, totalHome;
      Spinner country, ville;
      Button addAnnonce;
      RadioButton radio1, radio2, radio3, radio4, radio5, radio6;
      RadioButton r1, r2, r3, r4, r5, r6;
      String categories, atouts;
      String urlPicture = "";
      ImageView picture;
      itemToSell itemToSell = new itemToSell();
      ImageView addIcon, removeIcon;
      RadioGroup piece;
      int genid = 0;
      private DatePickerDialog.OnDateSetListener dateSetListener;

      public addAnnonceFragment() {
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            TinyDB tinydb = new TinyDB(getContext());
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            nameET = view.findViewById(R.id.name_et);
            picture = view.findViewById(R.id.picture);
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
            urlVideo = view.findViewById(R.id.url_video);
            descriptionET = view.findViewById(R.id.description);
            categorieRG = view.findViewById(R.id.radiogroup);
            address = view.findViewById(R.id.address);
            atoutsRG = view.findViewById(R.id.rg);
            priceSB = view.findViewById(R.id.rangeseekbar);
            priceTV = view.findViewById(R.id.afficheprix);
            surfaceTV = view.findViewById(R.id.affichesurface);
            surfaceSB = view.findViewById(R.id.surface_sb);
            country = view.findViewById(R.id.spin);
            dateTV = view.findViewById(R.id.tvdate);
            ville = view.findViewById(R.id.sp);
            addAnnonce = view.findViewById(R.id.rechercher);

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
            totalHome = view.findViewById(R.id.total_home);
            totalHome.setText("1");
            addIcon = view.findViewById(R.id.add_icon);
            removeIcon = view.findViewById(R.id.remove_icon);
            piece = view.findViewById(R.id.piece);

            addIcon.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        int foo;
                        try {
                              foo = Integer.parseInt(totalHome.getText().toString());
                              foo++;
                              totalHome.setText(foo + "");
                        } catch (NumberFormatException e) {
                              foo = 0;
                        }
                        Log.e(TAG, "onClick: " + foo);
                        //totalHome.setText(foo + 1);
                  }
            });
            removeIcon.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (!totalHome.getText().toString().equals("0")) {
                              int foo;
                              try {
                                    foo = Integer.parseInt(totalHome.getText().toString());
                                    foo--;
                                    totalHome.setText(foo + "");
                              } catch (NumberFormatException e) {
                                    foo = 0;
                              }
                              Log.e(TAG, "onClick: " + foo);

                              //totalHome.setText(foo - 1);
                        }
                  }
            });
            priceSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                  @Override
                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Log.e(TAG, "onProgressChanged: " + progress);
                        priceTV.setText(String.valueOf(progress));
                        itemToSell.setPrice(progress);
                  }

                  @Override
                  public void onStartTrackingTouch(SeekBar seekBar) {

                  }

                  @Override
                  public void onStopTrackingTouch(SeekBar seekBar) {

                  }
            });
            surfaceSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                  @Override
                  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Log.e(TAG, "onProgressChanged: " + progress);
                        surfaceTV.setText(String.valueOf(progress));
                        itemToSell.setSurface(progress);

                  }

                  @Override
                  public void onStartTrackingTouch(SeekBar seekBar) {

                  }

                  @Override
                  public void onStopTrackingTouch(SeekBar seekBar) {

                  }
            });
            //spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(getContext(), R.array.ville, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ville.setAdapter(adapter);
            dateSetListener = new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        Log.d(TAG, "ondateset : dd/mm/yyy" + dayOfMonth + "/" + month + "/" + year);
                        String date = dayOfMonth + "/" + dayOfMonth + "/" + year;
                        dateTV.setText(date);

                  }
            };
            dateTV.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                  }
            });
            ArrayAdapter<CharSequence> adapterr = ArrayAdapter
                    .createFromResource(getContext(), R.array.tunisie, android.R.layout.simple_spinner_item);
            adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            country.setAdapter(adapterr);
            piece.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                  @Override
                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                        genid = checkedId;
                        Log.e(TAG, "onCheckedChanged: " + checkedId);
                  }
            });
            addAnnonce.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        selectedAtouts();
                        selectedCategorie();
                        RadioButton radioButton = (RadioButton) getView().findViewById(genid);
                        String gender = radioButton.getText().toString();


                        Log.e(TAG, "onClick: Selelcted: " + Integer.parseInt(gender.charAt(gender.length() - 1) + ""));
                        itemToSell.setNumberBedrooms(Integer.parseInt(gender.charAt(gender.length() - 1) + ""));

                        itemToSell.setNumberHouses(Integer.parseInt(totalHome.getText().toString()));
                        Location location = new Location();
                        location.setAddress(address.getText().toString());
                        location.setCountry(country.getSelectedItem().toString());
                        location.setVille(ville.getSelectedItem().toString());
                        itemToSell.setTitle(nameET.getText().toString());
                        itemToSell.setDescription(descriptionET.getText().toString());
                        itemToSell.setAtouts(atouts.toUpperCase());
                        itemToSell.setDateDeLivraison(dateTV.getText().toString());
                        itemToSell.setLocation(location);
                        itemToSell.setCategorie(new Categorie(22, categories));
                        itemToSell.setUrlVideo(urlVideo.getText().toString());
                        itemToSell.setUrlPicture(urlPicture);
                        itemToSell.setUserId(userResponse.getUser().getId());

                        RetrofitClient.build().postAnnonces(itemToSell, "Bearer " + userResponse.getToken())
                                .enqueue(new Callback<ResponseBody>() {
                                      @Override
                                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onResponse: All good");
                                                  startActivity(new Intent(getContext(), DashbordActivity.class));
                                            } else {
                                                  Log.e(TAG, "onResponse: I found an error code: " + response.code());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getMessage());
                                      }
                                });

                  }
            });
            picture.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, 100);
                  }
            });

      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_add_annonce, container, false);
      }

      @Override
      public void onStart() {
            super.onStart();
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();

            if (user != null) {
                  // do your stuff
            } else {
                  signInAnonymously(mAuth);
            }
      }

      @Override
      public void onAttach(Context context) {
            super.onAttach(context);

      }

      @Override
      public void onDetach() {
            super.onDetach();
      }

      public void selectedCategorie() {

            if (radio1.isChecked()) {
                  categories = radio1.getText().toString();
            } else if (radio2.isChecked()) {
                  categories = radio2.getText().toString();
            } else if (radio3.isChecked()) {
                  categories = radio3.getText().toString();
            } else if (radio4.isChecked()) {
                  categories = radio4.getText().toString();

            } else if (radio5.isChecked()) {
                  categories = radio5.getText().toString();

            } else if (radio6.isChecked()) {
                  categories = radio6.getText().toString();

            } else
                  Log.e(TAG, "onClick: Select atouts");
      }

      public void selectedAtouts() {

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
            } else {
                  Log.e(TAG, "onClick: Select atouts");
            }
      }

      @Override
      public void onActivityResult(int reqCode, int resultCode, Intent data) {
            super.onActivityResult(reqCode, resultCode, data);

            Log.e(TAG, "onActivityResult: first step");
            if (resultCode == RESULT_OK) {
                  try {
                        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        picture.setImageBitmap(selectedImage);
                        uploadImage(getContext(), imageUri, mStorageRef, ts);
                  } catch (FileNotFoundException e) {
                        Log.e(TAG, "onActivityResult: " + e.getMessage());
                  }

            } else {
                  Log.e(TAG, "onActivityResult: The else");
            }
      }

      public void signInAnonymously(FirebaseAuth mAuth) {
            mAuth.signInAnonymously().addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
                  @Override
                  public void onSuccess(AuthResult authResult) {
                        // do your stuff
                  }
            })
                    .addOnFailureListener(getActivity(), new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception exception) {
                                Log.e(TAG, "signInAnonymously:FAILURE", exception);
                          }
                    });
      }

      public void uploadImage(Context context, Uri toUpload, StorageReference storageReference, String timestamp) {
            final TinyDB tinydb = new TinyDB(context);
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            final User userModified = userResponse.getUser();
            Log.e(TAG, "uploadImage: " + timestamp);
            final StorageReference ref = storageReference.child("profile/" + timestamp + ".jpg");

            ref.putFile(toUpload)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                          @Override
                          public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                if (taskSnapshot.getMetadata() != null) {
                                      if (taskSnapshot.getMetadata().getReference() != null) {
                                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                  @Override
                                                  public void onSuccess(Uri uri) {
                                                        String imageUrl = uri.toString();
                                                        Log.e(TAG, "onSuccess: imageURL:" + imageUrl);
                                                        userModified.setUrlProfilePicture(imageUrl);
                                                        userResponse.setUser(userModified);
                                                        tinydb.putObject("user", userResponse);
                                                        urlPicture = imageUrl;

                                                  }
                                            });
                                      }
                                }

                          }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception exception) {
                                Log.e(TAG, "onFailure: " + exception.getMessage());
                          }
                    });
      }
}
