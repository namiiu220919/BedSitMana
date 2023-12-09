package com.example.bedsitmana.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bedsitmana.Adapter.viewpage_adapter;
import com.example.bedsitmana.Dao.ThongKeDao;
import com.example.bedsitmana.Dao.hoaDonDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.HoaDon;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class thongKe_Activity extends AppCompatActivity {

//    Button btnTuNgay, btnDenNgay, btnDoanhThu;
//    EditText edtTuNgay, edtDenNgay;
//    TextView txtDoanhThu;
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    int mYear, mMonth, mDay;
    ArrayList<HoaDon> list;
hoaDonDao hdDao;
    TabLayout tabLayout;
    viewpage_adapter adapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabLayout=findViewById(R.id.tablayout);
        viewPager2=findViewById(R.id.viewpage2);
        adapter=new viewpage_adapter(this);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0: tab.setText("Biểu đồ");break;
                    case 1: tab.setText("Khoảng thời gian");break;
                }
            }
        }).attach();







    }

}