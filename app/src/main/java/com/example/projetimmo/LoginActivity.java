package com.example.projetimmo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetimmo.login.LoginRequest;
import com.example.projetimmo.login.LoginWebService;
import com.example.projetimmo.login.LoginWebServiceListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
      EditText email, password;
      private Button loginn;
      FirebaseAuth auth;
      TextView forgetPassword, inscriptionScreen;
      private static final String TAG = "LoginActivity";

      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            if (android.os.Build.VERSION.SDK_INT > 9) {
                  StrictMode.ThreadPolicy policy = new
                          StrictMode.ThreadPolicy.Builder().permitAll().build();
                  StrictMode.setThreadPolicy(policy);
            }
            email = findViewById(R.id.emailLoginid);
            inscriptionScreen = findViewById(R.id.insc);
            password = findViewById(R.id.passwordlogin);
            loginn = findViewById(R.id.login);
            forgetPassword = findViewById(R.id.forget_password);
            forgetPassword.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, ForgetPasswwordActivity.class));
                  }
            });
            inscriptionScreen.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, IncriptionActivity.class));
                  }
            });
            auth = FirebaseAuth.getInstance();
            loginn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        String emailText = email.getText().toString();
                        String passwordText = password.getText().toString();
                        if (TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passwordText)) {
                              Toast.makeText(LoginActivity.this, "All fields are requied", Toast.LENGTH_LONG);
                        } else {

                              final ProgressDialog progressdialog = new ProgressDialog(LoginActivity.this);
                              progressdialog.setMessage("Please Wait....");
                              progressdialog.show();
                              new LoginWebService().login(new LoginWebServiceListener() {
                                    @Override
                                    public void onSuccess(int type) {
                                          if (type == 0) {
                                                progressdialog.dismiss();
                                                startActivity(new Intent(LoginActivity.this, DashbordActivity.class));
                                                finish();
                                          } else {
                                                progressdialog.dismiss();
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                finish();
                                          }
                                    }

                                    @Override
                                    public void onError(String message) {
                                          progressdialog.dismiss();
                                          Log.d("ERROR", message);
                                          email.setError("Verify Your Email");
                                          password.setError("Check Your Password");
                                    }

                                    @Override
                                    public void onFailure(String message) {
                                          progressdialog.dismiss();
                                          snackieBar().show();
                                    }
                              }, new LoginRequest(emailText, passwordText), getApplicationContext());
                        }
                  }
            });

      }

      public Snackbar snackieBar() {
            final Snackbar snackie = Snackbar.make(getCurrentFocus(), "Problem de connection internet", Snackbar.LENGTH_LONG);
            View snackView = snackie.getView();
            TextView snackViewText = (TextView) snackView.findViewById(com.google.android.material.R.id.snackbar_text);
            Button snackViewButton = (Button) snackView.findViewById(com.google.android.material.R.id.snackbar_action);
            snackView.setBackgroundColor(getResources().getColor(R.color.red));
            snackViewText.setTextColor(getResources().getColor(R.color.white));
            snackViewButton.setTextColor(getResources().getColor(R.color.white));
            snackie.setAction("Hide", new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        snackie.dismiss();
                  }
            });
            return snackie;
      }
}
