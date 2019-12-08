package com.example.projetimmo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.projetimmo.Models.Discussion;
import com.example.projetimmo.Models.Location;
import com.example.projetimmo.Models.User;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.itemToSell;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.squareup.picasso.Picasso;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.projetimmo.utils.Utils.sendMessage;

public class AnnonceActivity extends AppCompatActivity {

      TextView titleTv, descriptionTv, paysTv, villeTv, addressTv, addressTitleTV, promoteur_nameTv, dateTv, priceTv, categoryTV, surfaceTV;
      ViewFlipper viewFlipper;
      ImageView thumbnail, edit, addItemSold, updateAnnonce;
      Toolbar toolbar;
      GridView gditems;
      String[] atout = {"Surveillance", "Climatisation", "Espace_vert", "Parking", "chauffage", "Ascenseur"};
      String title, date, price, pictureUrl, description, promoteurName;
      Location location;
      int idAnnonce;
      Button message, call;
      UserResponse userMe;
      User userHim;
      itemToSell itemToSell;
      private static final String TAG = "AnnonceActivity";

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_annonce);
            TinyDB tinydb = new TinyDB(getApplicationContext());
            userMe = tinydb.getObject("user", UserResponse.class);
            userHim = tinydb.getObject("user_him", User.class);
            itemToSell = tinydb.getObject("selectedItem", itemToSell.class);
            viewFlipper = findViewById(R.id.v_flipper);
            titleTv = findViewById(R.id.text1);
            toolbar = findViewById(R.id.toolbar3);
            categoryTV = findViewById(R.id.categorie);
            descriptionTv = findViewById(R.id.description);
            addItemSold = findViewById(R.id.imageView3);
            updateAnnonce = findViewById(R.id.imageView4);
            paysTv = findViewById(R.id.pays);
            surfaceTV = findViewById(R.id.surface);
            addressTitleTV = findViewById(R.id.adressee);
            villeTv = findViewById(R.id.ville);
            addressTv = findViewById(R.id.address);
            promoteur_nameTv = findViewById(R.id.promoteur_name);
            dateTv = findViewById(R.id.datee);
            priceTv = findViewById(R.id.price);
            thumbnail = findViewById(R.id.bienthumbnail);
            gditems = findViewById(R.id.gridview);
            //back = findViewById(R.id.back);
            message = findViewById(R.id.message);
            call = findViewById(R.id.call);
            edit = findViewById(R.id.edit);
            Intent intent = getIntent();
            Boolean toSellIcon = intent.getBooleanExtra("myAnnonces", false);
            if (toSellIcon) {
                  addItemSold.setVisibility(View.VISIBLE);
                  updateAnnonce.setVisibility(View.VISIBLE);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        AnnonceActivity.super.onBackPressed();
                  }
            });
            updateAnnonce.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        startActivity(new Intent(AnnonceActivity.this, ModifierAnnonceActivity.class));
                  }
            });
            TinyDB tinyDB = new TinyDB(getApplicationContext());
            updateUI(itemToSell);
            CustomAdapter customAdapter = new CustomAdapter();
            gditems.setAdapter(customAdapter);
            Log.e(TAG, "onCreate: " + userHim.getPhoneNumber());
            call.setOnClickListener(new View.OnClickListener() {

                  public void onClick(View v) {
                        onCall();
                  }
            });

            message.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        //Todo send message activity
                        showPopUp();
                  }
            });
            addItemSold.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        showPopUpSoldHouse();
                  }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        Intent intent = new Intent(AnnonceActivity.this, CreationNoteActivity.class);
                        intent.putExtra("id_annonce", idAnnonce);
                        intent.putExtra("id_user", userMe.getUser().getId());
                        intent.putExtra("token", userMe.getToken());
                        startActivity(intent);
                  }
            });


            int image[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

            for (int images : image) {
                  flipperImages(images);
            }


      }

      private class CustomAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                  return atout.length;
            }

            @Override
            public Object getItem(int position) {
                  return null;
            }

            @Override
            public long getItemId(int position) {
                  return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                  View view1 = getLayoutInflater().inflate(R.layout.row_atouts, null);
                  TextView textt = view1.findViewById(R.id.atouts);
                  ImageView tick = view1.findViewById(R.id.the_tick);
                  textt.setText(atout[position]);
                  Log.e(TAG, "getView: " + itemToSell.toString());
                  if (itemToSell.getAtouts().toUpperCase().contains(atout[position].toUpperCase()))
                        tick.setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent), android.graphics.PorterDuff.Mode.MULTIPLY);

                  return view1;
            }
      }

      public void flipperImages(int image) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(image);
            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(2000);
            viewFlipper.setAutoStart(true);
            viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

      }

      public void updateUI(itemToSell itemToSell) {
            priceTv.setText(String.valueOf(itemToSell.getPrice()));
            titleTv.setText(itemToSell.getTitle());
            dateTv.setText(date);
            addressTv.setText(itemToSell.getLocation().getAddress());
            categoryTV.setText(itemToSell.getCategorie().getName());
            surfaceTV.setText(String.valueOf(itemToSell.getSurface()));
            descriptionTv.setText(itemToSell.getDescription());
            addressTitleTV.setText(itemToSell.getLocation().getAddress());
            paysTv.setText(itemToSell.getLocation().getCountry());
            villeTv.setText(itemToSell.getLocation().getVille());
            addressTv.setText(itemToSell.getLocation().getAddress());
            promoteur_nameTv.setText(userHim.getCompany());
            if (itemToSell.getUrlPicture().length() != 0)
                  Picasso.get().load(itemToSell.getUrlPicture()).into(thumbnail);
      }

      public void showPopUp() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater layoutInflater = getLayoutInflater();
            View customView = layoutInflater.inflate(R.layout.send_message_popup_dialog, null);
            final EditText tv = customView.findViewById(R.id.the_message);
            ImageView sendTheMessage = customView.findViewById(R.id.send_the_message);
            builder.setView(customView);
            builder.create();
            final AlertDialog ad = builder.show();
            sendTheMessage.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        sendMessage(itemToSell, new Discussion(userMe.getUser().getId(), userHim.getId(), tv.getText().toString(), (System.currentTimeMillis() / 100)), userMe.getUser(), userHim);
                        ad.dismiss();
                  }
            });

      }

      public void showPopUpSoldHouse() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            if (title != null) builder.setTitle(title);

            builder.setMessage("Vous allez noter une annoces vendu dans la base de donnees");
            builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                        RetrofitClient.build().makeTheAnnonceSold(itemToSell.getId(), "Bearer " + userMe.getToken())
                                .enqueue(new Callback<ResponseBody>() {
                                      @Override
                                      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            if (response.isSuccessful()) {
                                                  Log.e(TAG, "onResponse code: " + response.code());
                                                  Log.e(TAG, "onResponse: " + response.body());
                                                  Intent intent = new Intent(getApplicationContext(), DashbordActivity.class);
                                                  startActivity(intent);
//                                                  getActivity().finish();
                                            } else {
                                                  Log.e(TAG, "onResponse: " + response.code());
                                            }
                                      }

                                      @Override
                                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Log.e(TAG, "onFailure: " + t.getMessage());
                                      }
                                });
                        dialog.dismiss();
                  }
            });
            builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                  }
            });
            builder.show();

      }

      public void onCall() {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                  ActivityCompat.requestPermissions(
                          this,
                          new String[]{Manifest.permission.CALL_PHONE},
                          Integer.parseInt("123"));
            } else {
                  startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + userHim.getPhoneNumber())));
            }
      }

      @Override
      public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {

                  case 123:
                        if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                              onCall();
                        } else {
                              Log.d("TAG", "Call Permission Not Granted");
                        }
                        break;

                  default:
                        break;
            }
      }
}
