package com.example.projetimmo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projetimmo.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //getSupportActionBar().hide();
            setContentView(R.layout.activity_main);
            BottomNavigationView navigation = findViewById(R.id.nav_view);
            navigation.setOnNavigationItemSelectedListener(this);
            Bundle b = getIntent().getExtras();
            if (b != null) {
                  String data = b.getString("fragment");
                  if (data.equals("profile")) {
                        loadFragment(new ProfileFragment());
                        navigation.setSelectedItemId(R.id.navigation_profil);
                  }

            } else
                  loadFragment(new HomeFragment());

      }

      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                  case R.id.navigation_home:
                        selectedFragment = new HomeFragment();
                        break;
                  case R.id.navigation_fav:
                        selectedFragment = new FavoriteFragment();
                        break;
                  case R.id.navigation_inbox:
                        selectedFragment = new MessagingFragment();
                        break;
                  case R.id.navigation_profil:
                        selectedFragment = new ProfileFragment();
                        break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_main_activity, selectedFragment);
            transaction.commit();
            return true;
      }

      private boolean loadFragment(Fragment fragment) {
            //switching fragment
            if (fragment != null) {
                  getSupportFragmentManager()
                          .beginTransaction()
                          .replace(R.id.frame_main_activity, fragment)
                          .commit();
                  return true;
            }
            return false;
      }


}
