package com.example.projetimmo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {
      private static final String TAG = "EditProfilActivity";
      ImageView back, check, changeProfilePicture;
      CircleImageView profileImage;
      TextInputEditText nomEditText, prenomEditText, mailEditText, phoneEditText, passwordEditText, repasswordEditText;
      User userModified;
      String urlProfile = "";
      UserResponse userResponse;
      TinyDB tinydb;
      int RESULT_LOAD_IMG = 100;

      @Override
      protected void onStart() {
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
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_profil);
            tinydb = new TinyDB(getApplicationContext());
            userResponse = tinydb.getObject("user", UserResponse.class);
            userModified = userResponse.getUser();
            back = findViewById(R.id.back);
            check = findViewById(R.id.check);
            nomEditText = findViewById(R.id.nom_edit_text);
            prenomEditText = findViewById(R.id.prenom_edit_text);
            mailEditText = findViewById(R.id.email_edit_text);
            phoneEditText = findViewById(R.id.phone_edit_text);
            passwordEditText = findViewById(R.id.password_edit_text);
            repasswordEditText = findViewById(R.id.repassword_edit_text);
            changeProfilePicture = findViewById(R.id.change_profile_picture);
            profileImage = findViewById(R.id.profile_image);

            Glide.with(getApplicationContext())
                    .load(userResponse.getUser()
                            .getUrlProfilePicture())
                    .apply(new RequestOptions()
                            .error(R.drawable.ic_close))
                    .into(profileImage);
            updateTheUI();
            changeProfilePicture.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                  }
            });
            check.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (verifie()) {
                              userModified.setFirstName(nomEditText.getText().toString());
                              userModified.setLastName(prenomEditText.getText().toString());
                              userModified.setEmail(mailEditText.getText().toString());
                              userModified.setPhoneNumber(phoneEditText.getText().toString());
                              userModified.setPassword(passwordEditText.getText().toString());
                              if (urlProfile.length() != 0)
                                    userModified.setUrlProfilePicture(urlProfile);
                              RetrofitClient.build().updateProfile(userModified, "Bearer " + userResponse.getToken())
                                      .enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                  if (response.isSuccessful()) {
                                                        UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
                                                        userResponse.setUser(response.body());
                                                        tinydb.putObject("user", userResponse);
                                                        Intent i;
                                                        TinyDB tinyDB = new TinyDB(getApplicationContext());
                                                        //UserResponse user = tinyDB.getObject("user", UserResponse.class);
                                                        Toast.makeText(getApplicationContext(), "Vos informations sont modifier", Toast.LENGTH_LONG).show();
                                                        if (userResponse.getClaims().getScopes().contains("PROMO"))
                                                              i = new Intent(EditProfilActivity.this, DashbordActivity.class);
                                                        else
                                                              i = new Intent(EditProfilActivity.this, MainActivity.class);
                                                        i.putExtra("fragment", "profile");
                                                        startActivity(i);

                                                  } else {
                                                        Log.e(TAG, "onResponse code: " + response.code());
                                                  }
                                            }

                                            @Override
                                            public void onFailure(Call<User> call, Throwable t) {
                                                  Log.e(TAG, "onFailure: " + t.getMessage());
                                            }
                                      });


                        }

                  }
            });
            back.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent i;
                        TinyDB tinyDB = new TinyDB(getApplicationContext());
                        UserResponse user = tinyDB.getObject("user", UserResponse.class);
                        if (user.getClaims().getScopes().equals("ROLE_PROMOTEUR"))
                              i = new Intent(EditProfilActivity.this, DashbordActivity.class);
                        else
                              i = new Intent(EditProfilActivity.this, MainActivity.class);
                        i.putExtra("fragment", "profile");
                        startActivity(i);
                  }
            });

      }

      public Boolean verifie() {
            Boolean toReturn = true;
            if (nomEditText.getText().length() == 0) {
                  nomEditText.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  nomEditText.setError(null);
            if (prenomEditText.getText().length() == 0) {
                  prenomEditText.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  nomEditText.setError(null);
            if (mailEditText.getText().length() == 0) {
                  mailEditText.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  nomEditText.setError(null);
            if (phoneEditText.getText().length() == 0) {
                  phoneEditText.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else
                  nomEditText.setError(null);
            if ((passwordEditText.getText().length() == 0) || (repasswordEditText.getText().length() == 0)) {
                  passwordEditText.setError(getResources().getString(R.string.emptyfield));
                  toReturn = false;
            } else if (!(passwordEditText.getText().toString().equals(repasswordEditText.getText().toString()))) {
                  passwordEditText.setError("Mot de passe non identique");
                  toReturn = false;
            } else
                  passwordEditText.setError(null);
            return toReturn;
      }

      @Override
      protected void onActivityResult(int reqCode, int resultCode, Intent data) {
            super.onActivityResult(reqCode, resultCode, data);

            Log.e(TAG, "onActivityResult: first step");
            if (resultCode == RESULT_OK) {
                  try {
                        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        Long tsLong = System.currentTimeMillis() / 1000;
                        String ts = tsLong.toString();
                        profileImage.setImageBitmap(selectedImage);
                        uploadImage(getApplicationContext(), imageUri, mStorageRef, ts);
                  } catch (FileNotFoundException e) {
                        Log.e(TAG, "onActivityResult: " + e.getMessage());
                  }

            } else {
                  Log.e(TAG, "onActivityResult: The else");
            }
      }

      public void updateTheUI() {
            nomEditText.setText(userResponse.getUser().getLastName());
            prenomEditText.setText(userResponse.getUser().getFirstName());
            mailEditText.setText(userResponse.getUser().getEmail());
            phoneEditText.setText(userResponse.getUser().getPhoneNumber());
      }

      public void signInAnonymously(FirebaseAuth mAuth) {
            mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                  @Override
                  public void onSuccess(AuthResult authResult) {
                        // do your stuff
                  }
            })
                    .addOnFailureListener(this, new OnFailureListener() {
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
                                                        urlProfile = imageUrl;

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
