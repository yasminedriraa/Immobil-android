package com.example.projetimmo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetimmo.Models.ChartItem;
import com.example.projetimmo.Models.PieChartItem;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.statisticsByRoomNumber;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class statistics_first extends Fragment {
      private String title;
      private int page;
      private static final String TAG = "statistics_first";

      public static statistics_first newInstance(int page, String title) {
            statistics_first fragmentFirst = new statistics_first();
            Bundle args = new Bundle();
            args.putInt("someInt", page);
            args.putString("someTitle", title);
            fragmentFirst.setArguments(args);
            return fragmentFirst;
      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            page = getArguments().getInt("someInt", 0);
            title = getArguments().getString("someTitle");
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_statistics_first, container, false);
            TextView tvLabel = view.findViewById(R.id.tvLabel);
            tvLabel.setText(title);
            return view;
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final ListView lv = view.findViewById(R.id.listView1);
            TinyDB tinydb = new TinyDB(getContext());
            UserResponse userMe = tinydb.getObject("user", UserResponse.class);
            final ArrayList<ChartItem> list = new ArrayList<>();
            RetrofitClient.build().getTheStatisticsByRoomNumber(userMe.getUser().getId(), "Bearer " + userMe.getToken())
                    .enqueue(new Callback<statisticsByRoomNumber>() {
                          @Override
                          public void onResponse(Call<statisticsByRoomNumber> call, Response<statisticsByRoomNumber> response) {
                                if (response.isSuccessful()) {
                                      list.add(new PieChartItem(generateDataPie(response.body()), getContext()));
                                      ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
                                      lv.setAdapter(cda);

                                } else {
                                      Log.e(TAG, "onResponse: " + response.code());
                                }
                          }

                          @Override
                          public void onFailure(Call<statisticsByRoomNumber> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.getMessage());
                          }
                    });

      }

      public PieData generateDataPie(statisticsByRoomNumber statisticsByRoomNumber) {
            Log.e(TAG, "generateDataPie: " + statisticsByRoomNumber.toString());
            ArrayList<PieEntry> entries = new ArrayList<>();
            for (int i = 0; i < statisticsByRoomNumber.getObjectPercentages().size(); i++) {
                  entries.add(new PieEntry(statisticsByRoomNumber.getObjectPercentages().get(i).getPercentage(), "S+" + (i)));
            }

            PieDataSet d = new PieDataSet(entries, "Sold houses by classification");
            d.setSliceSpace(2f);
            d.setColors(ColorTemplate.COLORFUL_COLORS);
            return new PieData(d);
      }

      private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

            ChartDataAdapter(Context context, List<ChartItem> objects) {
                  super(context, 0, objects);
            }

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                  return getItem(position).getView(position, convertView, getContext());
            }

            @Override
            public int getItemViewType(int position) {
                  // return the views type
                  ChartItem ci = getItem(position);
                  return ci != null ? ci.getItemType() : 0;
            }

            @Override
            public int getViewTypeCount() {
                  return 3;
            }
      }

}
