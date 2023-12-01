package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bedsitmana.Dao.ThongKeDao;
import com.example.bedsitmana.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

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

//        edtTuNgay = findViewById(R.id.edtTuNgay);
//        edtDenNgay = findViewById(R.id.edtDenNgay);
//        txtDoanhThu = findViewById(R.id.txtDoanhThu);
//        btnTuNgay = findViewById(R.id.btnTuNgay);
//        btnDenNgay = findViewById(R.id.btnDenNgay);
//        btnDoanhThu = findViewById(R.id.btnDoanhThu);
//        btnTuNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog d = new DatePickerDialog(thongKe_Activity.this,0,mDateTuNgay,mYear,mMonth,mDay);
//                d.show();
//            }
//        });
//        btnDenNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog d = new DatePickerDialog(thongKe_Activity.this,0,mDateDenNgay,mYear,mMonth,mDay);
//                d.show();
//            }
//        });
//        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String tuNgay = edtTuNgay.getText().toString();
//                String denNgay = edtDenNgay.getText().toString();
//                ThongKeDao thongKeDao=new ThongKeDao(thongKe_Activity.this);
//                txtDoanhThu.setText("Doanh Thu: "+thongKeDao.getDoanhThu(tuNgay,denNgay)+" VND");
//            }
//        });
//    }
//
//    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//            mYear = i;
//            mMonth = i1;
//            mDay = i2;
//            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
//            edtTuNgay.setText(sdf.format(c.getTime()));
//        }
//    };
//    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//            mYear = i;
//            mMonth = i1;
//            mDay = i2;
//            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
//            edtDenNgay.setText(sdf.format(c.getTime()));
//        }



        BarChart barChart = findViewById(R.id.barChart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 100f));  // Tháng 1
        entries.add(new BarEntry(1f, 200f));  // Tháng 2
        entries.add(new BarEntry(2f, 150f));  // Tháng 3
        entries.add(new BarEntry(3f, 100f));  // Tháng 4
        entries.add(new BarEntry(4f, 200f));  // Tháng 5
        entries.add(new BarEntry(5f, 150f));  // Tháng 6
        entries.add(new BarEntry(6f, 100f));  // Tháng 7
        entries.add(new BarEntry(7f, 200f));  // Tháng 8
        entries.add(new BarEntry(8f, 150f));  // Tháng 9
        entries.add(new BarEntry(9f, 100f));  // Tháng 10
        entries.add(new BarEntry(10f, 200f));  // Tháng 11
        entries.add(new BarEntry(11f, 150f));  // Tháng 12

        BarDataSet dataSet = new BarDataSet(entries, "Doanh thu theo tháng");

        dataSet.setColors(Color.RED);
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData(dataSet);

        // Cấu hình trục x
        String[] months = new String[]{"T1", "T2", "T3","T4", "T5", "T6","T7", "T8", "T9","T10", "T11", "T12" };
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(months));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setLabelCount(months.length);

        // Cấu hình trục y
        barChart.getAxisRight().setEnabled(false);
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(2000);

// Cập nhật biểu đồ
        barChart.invalidate();
    }
}