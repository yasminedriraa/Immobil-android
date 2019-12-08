package com.example.projetimmo.utils;


public class BottonNavigationViewHelper {

    private static final String TAG="BottonNavigationViewHelper";

  /**  public static void enableNavigation(final Context context , BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case  R.id.navigation_home :
                        Intent intent1 = new Intent(context , AccueilClient.class);
                        context.startActivity(intent1);
                        break;
                    case R.id.navigation_fav :
                        Intent intent2 = new Intent(context , FavorisActivity.class);
                        context.startActivity(intent2);
                        break;
                    case R.id.navigation_inbox :
                        Intent intent3 = new Intent(context , MessagerieActivity.class);
                        context.startActivity(intent3);
                        break;
                    case R.id.navigation_profil :
                        Intent intent4 = new Intent(context , ProfilActivity.class);
                        context.startActivity(intent4);
                        break;
                }
                return false;
            }
        });
    }*/


}
