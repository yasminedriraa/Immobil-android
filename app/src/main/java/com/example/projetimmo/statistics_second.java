package com.example.projetimmo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projetimmo.Models.BarChartItem;
import com.example.projetimmo.Models.ChartItem;
import com.example.projetimmo.Models.LineChartItem;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.linesStatistics;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class statistics_second extends Fragment {
      private String title;
      private int page;
      private static final String TAG = "statistics_second";

      public static statistics_second newInstance(int page, String title) {
            statistics_second fragmentFirst = new statistics_second();
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
            View view = inflater.inflate(R.layout.fragment_statistics_second, container, false);
            TextView tvLabel = view.findViewById(R.id.tvLabel);
            tvLabel.setText(title);
            final ListView lv = view.findViewById(R.id.listView2);
            TinyDB tinydb = new TinyDB(getContext());
            final ArrayList<ChartItem> list = new ArrayList<>();
            UserResponse userMe = tinydb.getObject("user", UserResponse.class);
            RetrofitClient.build().getTheStatisticsByDate(userMe.getUser().getId(), "Bearer " + userMe.getToken())
                    .enqueue(new Callback<linesStatistics>() {
                          @Override
                          public void onResponse(Call<linesStatistics> call, Response<linesStatistics> response) {
                                if (response.isSuccessful()) {
                                      Log.e(TAG, "onResponse: 2");
                                      list.add(new LineChartItem(generateDataLine(response.body()), getContext()));
                                      //list.add(new BarChartItem(generateDataBar(), getContext()));
                                      ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
                                      lv.setAdapter(cda);

                                } else {
                                      Log.e(TAG, "onResponse: " + response.code());
                                }
                          }

                          @Override
                          public void onFailure(Call<linesStatistics> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.getMessage());
                          }
                    });
            return view;
      }

      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
      }

      public BarData generateDataBar() {

            ArrayList<BarEntry> entries = new ArrayList<>();
            final ArrayList<String> xAxisLabel = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                  BarEntry barEntry = new BarEntry(i, (int) (Math.random() * 70) + 30);
                  entries.add(barEntry);
                  xAxisLabel.add("S+" + i);
            }
            BarDataSet d = new BarDataSet(entries, null);
            d.setColors(ColorTemplate.MATERIAL_COLORS);
            d.setHighlightEnabled(false);
            BarData cd = new BarData(d);
            cd.setBarWidth(0.8f);
            return cd;
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

      public LineData generateDataLine(linesStatistics linesStatistic) {
            ArrayList<Entry> values1 = new ArrayList<>();

            for (int i = 0; i < linesStatistic.getLinesStatistics().size(); i++) {
                  values1.add(new Entry(i, linesStatistic.getLinesStatistics().get(i).getIncomesMonthIndex()));
                  //values1.add(new Entry(i, (int) (Math.random() * 6500) + 4000));
            }
            LineDataSet d1 = new LineDataSet(values1, "Statistique de vente / temps");
            d1.disableDashedLine();
            d1.disableDashedHighlightLine();
            d1.setColor(ColorTemplate.COLORFUL_COLORS[0]);
            d1.setCircleColor(ColorTemplate.COLORFUL_COLORS[0]);
            d1.setCircleRadius(10.5f);
            d1.setDrawValues(false);
            ArrayList<ILineDataSet> sets = new ArrayList<>();
            sets.add(d1);
            return new LineData(sets);
      }
}
