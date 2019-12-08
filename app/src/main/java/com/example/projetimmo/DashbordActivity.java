package com.example.projetimmo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.ui.home.HomeFragment;
import com.example.projetimmo.utils.TinyDB;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashbordActivity extends AppCompatActivity {

      CardView myAnnonces, infoCompany, createAnnonce, messagesCompany, getAllAnnonces, myFavorits, statistics, disconnect;
      CircleImageView profilePicture;
      FrameLayout dashbordFrame;
      ImageView menu;
      ScrollView theButtons;
      TextView titleToolbar;
      boolean hideTheFragment = false;
      private static final String TAG = "DashbordActivity";
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dashbord);
            TinyDB tinyDB = new TinyDB(this);
            final UserResponse userMe = tinyDB.getObject("user", UserResponse.class);
            menu = findViewById(R.id.menuIcon);
            theButtons = findViewById(R.id.theButtons);
            dashbordFrame = findViewById(R.id.dashboardFrame);
            profilePicture = findViewById(R.id.profile_image);
            myAnnonces = findViewById(R.id.mesannonce);
            myFavorits = findViewById(R.id.favories);
            titleToolbar = findViewById(R.id.title_toolbar);
            getAllAnnonces = findViewById(R.id.voir_annonces);
            infoCompany = findViewById(R.id.infoagence);
            createAnnonce = findViewById(R.id.creerannonce);
            messagesCompany = findViewById(R.id.messages);
            disconnect = findViewById(R.id.disconnect);
            statistics = findViewById(R.id.statistics);
            if (userMe.getUser().getUrlProfilePicture() != null)
                  if (userMe.getUser().getUrlProfilePicture().length() != 0)
                        Glide.with(getApplicationContext())
                                .load(userMe.getUser()
                                        .getUrlProfilePicture())
                                .apply(new RequestOptions()
                                        .error(R.drawable.photo))
                                .into(profilePicture);

            messagesCompany.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        loadFragment(new MessagingFragment(), null);
                  }
            });
            menu.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        if (hideTheFragment) {
                              dashbordFrame.setVisibility(View.GONE);
                              theButtons.setVisibility(View.VISIBLE);
                              menu.setBackground(null);
                              hideTheFragment = false;
                              titleToolbar.setText("Mon tableau de bord");

                        }

                  }
            });
            statistics.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("Statistique");
                        loadFragment(new statisticsFragment(), null);
                  }
            });
            disconnect.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        startActivity(new Intent(DashbordActivity.this, HomeActivity.class));
                  }
            });
            myAnnonces.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("Mes annonces");
                        loadFragment(new HomeFragment(), ""+userMe.getUser().getId());
                  }
            });
            myFavorits.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("Liste favorits");
                        loadFragment(new FavoriteFragment() ,null);
                  }
            });
            profilePicture.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("Profile");
                        loadFragment(new ProfileFragment(), null);
                  }
            });
            infoCompany.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("information entreprise");
                        loadFragment(new profileCompanyFragment(), null);
                  }
            });
            createAnnonce.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("Creer annonce");
                        loadFragment(new addAnnonceFragment(), null);

                  }
            });
            getAllAnnonces.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        titleToolbar.setText("Tous annonces");
                        loadFragment(new HomeFragment(), null);
                  }
            });

      }

      private boolean loadFragment(Fragment fragment, @Nullable String userID) {
            dashbordFrame.setVisibility(View.VISIBLE);
            theButtons.setVisibility(View.GONE);
            theButtons.setVisibility(View.GONE);
            menu.setBackground(getResources().getDrawable(R.drawable.back));
            hideTheFragment = true;
            if (fragment != null) {
                  Log.e(TAG, "loadFragment: "+userID );
                  getSupportFragmentManager()
                          .beginTransaction()
                          .replace(R.id.dashboardFrame, fragment, userID)
                          .commit();
                  return true;
            }
            return false;
      }
}
