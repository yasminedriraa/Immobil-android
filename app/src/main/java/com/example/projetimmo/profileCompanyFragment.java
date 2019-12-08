package com.example.projetimmo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.utils.TinyDB;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileCompanyFragment extends Fragment {

      public profileCompanyFragment() {
            // Required empty public constructor
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_profile_company, container, false);
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            TextView nameCompany, addressCompany, emailCompany, phone_company;
            ImageView edit;
            CircleImageView logoCompany;
            logoCompany = view.findViewById(R.id.company_logo);
            edit = view.findViewById(R.id.edit);
            emailCompany = view.findViewById(R.id.email_company);
            nameCompany = view.findViewById(R.id.company_name);
            addressCompany = view.findViewById(R.id.adresse_company);
            phone_company = view.findViewById(R.id.numero_company);
            TinyDB tinyDB = new TinyDB(getContext());
            UserResponse userMe = tinyDB.getObject("user", UserResponse.class);
            nameCompany.setText(userMe.getUser().getCompany());
            addressCompany.setText(userMe.getUser().getCompanyAddress());
            emailCompany.setText(userMe.getUser().getCompagnieEmail());
            phone_company.setText(userMe.getUser().getCompagniePhone());
            Glide.with(getContext())
                    .load(userMe.getUser()
                            .getUrlProfilePicture())
                    .apply(new RequestOptions()
                            .error(R.drawable.photo))
                    .into(logoCompany);
            edit.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.dashboardFrame, new EditInfoCompanyFragment())
                                .commit();
                  }
            });

      }

      @Override
      public void onAttach(Context context) {
            super.onAttach(context);

      }

      @Override
      public void onDetach() {
            super.onDetach();
      }

}
