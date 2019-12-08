package com.example.projetimmo;

import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.utils.TinyDB;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetimmo.utils.ViewPagerInscriAdapter;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class IncriptionActivity extends AppCompatActivity {

      private static final String TAG = "IncriptionActivity";
      private int position;
      SelectedBundle selectedBundle;
      private TabLayout tabLayout;
      private ConstraintLayout appBarLayout;
      private ViewPager viewPager;
      CircleImageView profilePicture;
      ImageView changeProfilePicture;
      String urlProfile;
      int RESULT_LOAD_IMG = 100;
      ProgressDialog progressdialog;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_incription);
            tabLayout = findViewById(R.id.tablayout_id);
            appBarLayout = findViewById(R.id.appbarlayout_id);
            viewPager = findViewById(R.id.viewpager_id);
            profilePicture = findViewById(R.id.LogoidApp);
            changeProfilePicture = findViewById(R.id.LogoidApp);
            final ViewPagerInscriAdapter adapter = new ViewPagerInscriAdapter(getSupportFragmentManager());
            adapter.addFragment(new FragmentInscriptionClient(), "client");
            adapter.addFragment(new FragmentInscriptionCompagnie(), "company");
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            changeProfilePicture.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        progressdialog = new ProgressDialog(IncriptionActivity.this);
                        progressdialog.setMessage("Please Wait....");
                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
                  }
            });
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

      public void uploadImage(Uri toUpload) {
            final TinyDB tinydb = new TinyDB(getApplicationContext());
            final UserResponse userResponse = tinydb.getObject("user", UserResponse.class);
            final User userModified = userResponse.getUser();
            Long tsLong = System.currentTimeMillis() / 1000;
            String timestamp = tsLong.toString();
            Log.e(TAG, "uploadImage: " + timestamp);
            final StorageReference ref = FirebaseStorage.getInstance().getReference().child("profile/" + timestamp + ".jpg");
            progressdialog.show();
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
                                                        progressdialog.dismiss();
                                                        Log.e(TAG, "onSuccess: imageURL:" + imageUrl);
                                                        userModified.setUrlProfilePicture(imageUrl);
                                                        userResponse.setUser(userModified);
                                                        tinydb.putObject("user", userResponse);
                                                        urlProfile = imageUrl;
                                                        selectedBundle.onBundleSelect(urlProfile);
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

      public interface SelectedBundle {
            void onBundleSelect(String bundle);
      }

      public void setOnBundleSelected(SelectedBundle selectedBundle) {
            this.selectedBundle = selectedBundle;
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
                        profilePicture.setImageBitmap(selectedImage);
                        uploadImage(imageUri);
                  } catch (FileNotFoundException e) {
                        Log.e(TAG, "onActivityResult: " + e.getMessage());
                  }

            } else {
                  Log.e(TAG, "onActivityResult: The else");
            }
      }
}
