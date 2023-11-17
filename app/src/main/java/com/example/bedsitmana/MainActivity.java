package com.example.bedsitmana;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bedsitmana.Activity.loaiPhong_Activity;
import com.example.bedsitmana.Activity.hoaDon_Activity;
import com.example.bedsitmana.Activity.nguoiThue_Activity;
import com.example.bedsitmana.Activity.phong_Activity;
import com.example.bedsitmana.Activity.suCo_Activity;
import com.example.bedsitmana.Activity.thongKe_Activity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    View mHeader;
    Button btnLoaiPhong,btnPhong,btnNguoiThue,btnThongKe,btnHoaDon,btnSuCo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        btnLoaiPhong=findViewById(R.id.btnLoaiPhong);
        btnHoaDon=findViewById(R.id.btnHoaDon);
        btnPhong=findViewById(R.id.btnPhong);
        btnNguoiThue=findViewById(R.id.btnNguoiThue);
        btnThongKe=findViewById(R.id.btnThongKe);
        btnSuCo=findViewById(R.id.btnSuCo);
        btnLoaiPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentClass(loaiPhong_Activity.class);

            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentClass(hoaDon_Activity.class);
            }
        });
        btnPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentClass(phong_Activity.class);
            }
        });
        btnNguoiThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentClass(nguoiThue_Activity.class);
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentClass(thongKe_Activity.class);
            }
        });
        btnSuCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentClass(suCo_Activity.class);
            }
        });

        nav.setItemIconTintList(null);
        drawerLayout.closeDrawers();


    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav,frg).commit();
    }
    public void IntentClass(Class target){
        Intent intent=new Intent(MainActivity.this, target);
        startActivity(intent);
    }
}