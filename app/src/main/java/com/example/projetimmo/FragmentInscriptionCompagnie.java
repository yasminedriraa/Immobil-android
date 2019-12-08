package com.example.projetimmo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetimmo.Models.Role;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.register.RegisterRequestData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentInscriptionCompagnie extends Fragment {
      View view;
      private static final String TAG = "FragmentInscriptionComp";
      TextInputEditText nom, prenom, passwordTextInput, email, numTel, confirmation;
      EditText nomCompagnie, emailCompagnie, numCompagnie, adresseCompagnie;
      Button btn_Inscri;
      String url;

      FirebaseAuth auth;
      DatabaseReference reference;

      public FragmentInscriptionCompagnie() {
      }

      @Nullable
      @Override
      public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.compagnieinscription, container, false);
            auth = FirebaseAuth.getInstance();
            url = "";
            ((IncriptionActivity) getActivity()).setOnBundleSelected(new IncriptionActivity.SelectedBundle() {
                  @Override
                  public void onBundleSelect(String bundle) {
                        url = bundle;
                  }
            });
            nom = view.findViewById(R.id.name_et);
            prenom = view.findViewById(R.id.prenom_et);
            email = view.findViewById(R.id.email_et);
            numTel = view.findViewById(R.id.phone_et);
            nomCompagnie = view.findViewById(R.id.nom_company_et);
            emailCompagnie = view.findViewById(R.id.email_company_et);
            numCompagnie = view.findViewById(R.id.num_company_et);
            adresseCompagnie = view.findViewById(R.id.address_company_et);
            passwordTextInput = view.findViewById(R.id.password_et);
            confirmation = view.findViewById(R.id.repassword_et);

            btn_Inscri = view.findViewById(R.id.user_inscrip);

            btn_Inscri.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        String username = prenom.getText().toString();
                        String lastname = nom.getText().toString();
                        String mail = email.getText().toString();
                        String passwrd = passwordTextInput.getText().toString();
                        String confir_pass = confirmation.getText().toString();
                        String numCompa = numCompagnie.getText().toString();
                        String adress = adresseCompagnie.getText().toString();
                        String emailComp = emailCompagnie.getText().toString();
                        String nomComp = nomCompagnie.getText().toString();
                        String numPerso = numTel.getText().toString();
                        String type = "0";

                        if (TextUtils.isEmpty(username) || (TextUtils.isEmpty(mail)) || (TextUtils.isEmpty(passwrd)) || (TextUtils.isEmpty
                                (confir_pass)) || (url.length() == 0)) {

                              Toast.makeText(getActivity(), "Tous leschamps sont obligatoires ", Toast.LENGTH_LONG).show();

                        } else {
                              register(lastname, username, mail, numPerso, nomComp, emailComp, "3asba", numCompa, adress, passwrd, url);
                        }
                  }
            });


            return view;
      }


      private void register(String username, String lastname, String email, String phone,
                            String nomCompagn, String emailCompagn, String type, String numCompagn, String adresCompang, String password,
                            String urlProfilPicture) {

            RegisterRequestData registerRequestData = new RegisterRequestData();
            registerRequestData.setFirstName(username);
            registerRequestData.setLastName(lastname);
            registerRequestData.setEmail(email);
            registerRequestData.setPhoneNumber(phone);
            registerRequestData.setEntreprise(nomCompagn);
            registerRequestData.setEmailCompany(emailCompagn);
            registerRequestData.setPhoneNumberCompany(numCompagn);
            registerRequestData.setAdresseCompany(adresCompang);
            registerRequestData.setPassword(password);
            registerRequestData.setType(type);
            registerRequestData.setRole(new Role(0, "PROMOTEUR"));
            registerRequestData.setUrlprofilepicture(urlProfilPicture);
            Log.e(TAG, "register: " + registerRequestData.toString());
            RetrofitClient.build().register(registerRequestData)
                    .enqueue(new Callback<ResponseBody>() {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                      Log.e(TAG, "onResponse: " + response.code());
                                      Intent intent = new Intent(getActivity(), LoginActivity.class);
                                      startActivity(intent);
                                      getActivity().finish();
                                } else {
                                      Log.e(TAG, "onResponse: " + response.code());
                                }
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
