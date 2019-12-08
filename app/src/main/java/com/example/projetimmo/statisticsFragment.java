package com.example.projetimmo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projetimmo.Models.BarChartItem;
import com.example.projetimmo.Models.ChartItem;
import com.example.projetimmo.Models.LineChartItem;
import com.example.projetimmo.Models.PieChartItem;
import com.example.projetimmo.Models.UserResponse;
import com.example.projetimmo.Models.linesStatistic;
import com.example.projetimmo.Models.linesStatistics;
import com.example.projetimmo.Models.statisticsByRoomNumber;
import com.example.projetimmo.data.RetrofitClient;
import com.example.projetimmo.utils.TinyDB;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class statisticsFragment extends Fragment {
      statisticsByRoomNumber statisticsByRoomNumber = new statisticsByRoomNumber();
      private static final String TAG = "statisticsFragment";
      FragmentPagerAdapter adapterViewPager;

      public statisticsFragment() {
      }

      public static class MyPagerAdapter extends FragmentPagerAdapter {
            private static int NUM_ITEMS = 3;

            public MyPagerAdapter(FragmentManager fragmentManager) {
                  super(fragmentManager);
            }

            // Returns total number of pages
            @Override
            public int getCount() {
                  return NUM_ITEMS;
            }

            // Returns the fragment to display for that page
            @Override
            public Fragment getItem(int position) {
                  switch (position) {
                        case 0: // Fragment # 0 - This will show FirstFragment
                              return statistics_first.newInstance(0, "Statistique de vente / nombre de pieces");
                        case 1: // Fragment # 0 - This will show FirstFragment different title
                              return statistics_second.newInstance(1, "Statistique de vente / temps");
                        case 2: // Fragment # 0 - This will show FirstFragment different title
                              return statistics_three.newInstance(2, "Statistique de vente / temps");
                        default:
                              return null;
                  }
            }

            // Returns the page title for the top indicator
            @Override
            public CharSequence getPageTitle(int position) {
                  return "Page " + position;
            }

      }

      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
      }

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_statistics, container, false);
      }


      @Override
      public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final ListView lv = view.findViewById(R.id.listView1);
            TinyDB tinydb = new TinyDB(getContext());
            UserResponse userMe = tinydb.getObject("user", UserResponse.class);


            ViewPager vpPager = view.findViewById(R.id.vpPager);
            adapterViewPager = new MyPagerAdapter(getFragmentManager());
            vpPager.setAdapter(adapterViewPager);



//            ArrayList<linesStatistic> linesStatistic = new ArrayList<>();
//            for (int i = 1; i < 13; i++)
//                  linesStatistic.add(new linesStatistic(i, (int) (Math.random() * 100000 + 1)));
//            list.add(new LineChartItem(generateDataLine(linesStatistic), getContext()));
//            list.add(new BarChartItem(generateDataBar(), getContext()));
//            ChartDataAdapter cda = new ChartDataAdapter(getContext(), list);
//            lv.setAdapter(cda);
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
