package com.example.projetimmo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetimmo.Models.Role;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.register.RegisterRequestData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInscriptionClient extends Fragment {
      View view;

      TextInputEditText nom, prenom, repassword, password, email, numTel, confirmation;
      Button btn_Inscri;
      String url;
      private static final String TAG = "FragmentInscriptionClie";
      FirebaseAuth auth;
      DatabaseReference reference;

      public FragmentInscriptionClient() {
      }


      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.clientinscription, container, false);
            url = "";
            ((IncriptionActivity) getActivity()).setOnBundleSelected(new IncriptionActivity.SelectedBundle() {
                  @Override
                  public void onBundleSelect(String bundle) {
                        Log.e(TAG, "onBundleSelect: changed in the Fragment after interface");
                        url = bundle;
                  }
            });
            auth = FirebaseAuth.getInstance();
            nom = view.findViewById(R.id.first_name_et);
            prenom = view.findViewById(R.id.last_name_et);
            email = view.findViewById(R.id.email_et);
            password = view.findViewById(R.id.password_et);
            repassword = view.findViewById(R.id.repassword_et);
            numTel = view.findViewById(R.id.phone_et);
            btn_Inscri = view.findViewById(R.id.sign_up);

            btn_Inscri.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        String username = prenom.getText().toString();
                        String lastname = nom.getText().toString();
                        String mail = email.getText().toString();
                        String passwrd = password.getText().toString();
                        String confir_pass = repassword.getText().toString();
                        String numPerso = numTel.getText().toString();
                        String type = "0";

                        if ((TextUtils.isEmpty(username) || (TextUtils.isEmpty(mail)) || (TextUtils.isEmpty(passwrd)) || (TextUtils.isEmpty
                                (confir_pass)))) {
                              Log.e(TAG, "onClick: "+username );
                              Log.e(TAG, "onClick: "+mail );
                              Log.e(TAG, "onClick: "+passwrd );
                              Log.e(TAG, "onClick: "+confir_pass );
                              Log.e(TAG, "onClick: "+url );
                              Toast.makeText(getActivity(), "Tous leschamps sont obligatoires ", Toast.LENGTH_LONG).show();

                        } else {
                              register(lastname, username, mail, numPerso, passwrd, url);
                        }

                  }
            });


            return view;
      }

      private void register(String nom, String prenom, String email, String phone, String password, String urlProfilPicture) {

            RegisterRequestData registerRequestData = new RegisterRequestData();
            registerRequestData.setFirstName(nom);
            registerRequestData.setLastName(prenom);
            registerRequestData.setEmail(email);
            registerRequestData.setPhoneNumber(phone);
            registerRequestData.setPassword(password);
            registerRequestData.setRole(new Role(0, "CLIENT"));
            registerRequestData.setUrlprofilepicture(urlProfilPicture);
            Log.e(TAG, "register: " + registerRequestData.toString());
            RetrofitClient.build().register(registerRequestData)
                    .enqueue(new Callback<ResponseBody>() {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Log.e(TAG, "onResponse: " + response.code());
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                          }

                          @Override
                          public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.getMessage());
                                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                                Log.e(TAG, "onFailure: " + t.getCause());
                          }
                    });

      }
}
