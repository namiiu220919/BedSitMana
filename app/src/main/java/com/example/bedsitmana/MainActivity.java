package com.example.bedsitmana;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bedsitmana.Activity.hoaDon_Activity;
import com.example.bedsitmana.Activity.loaiPhong_Activity;
import com.example.bedsitmana.Activity.nguoiThue_Activity;
import com.example.bedsitmana.Activity.phong_Activity;
import com.example.bedsitmana.Activity.suCo_Activity;
import com.example.bedsitmana.Activity.thongKe_Activity;
import com.example.bedsitmana.Fragment.frg_thongtintaikhoan;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    View mHeader;
    AdView mAdView;
    Button btnLoaiPhong, btnPhong, btnNguoiThue, btnThongKe, btnHoaDon, btnSuCo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.nav);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        btnLoaiPhong = findViewById(R.id.btnLoaiPhong);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnPhong = findViewById(R.id.btnPhong);
        btnNguoiThue = findViewById(R.id.btnNguoiThue);
        btnThongKe = findViewById(R.id.btnThongKe);
        btnSuCo = findViewById(R.id.btnSuCo);
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

        SharedPreferences preferences = getSharedPreferences("user11", MODE_PRIVATE);
        String username = preferences.getString("username11", "...");

        Bundle bundle = new Bundle();
        bundle.putString("key", username);
        frg_thongtintaikhoan frgThongtintaikhoan=new frg_thongtintaikhoan();
        frgThongtintaikhoan.setArguments(bundle);


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.nav_profileUser){

                    setTitle("Thông tin tài khoản");
                    replaceFrg(frgThongtintaikhoan);
                }else if (item.getItemId()==R.id.nav_Home){
                    IntentClass(MainActivity.class);
                }
                return false;
            }

        });


    }

    public void replaceFrg(Fragment frg) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav, frg).commit();
    }

    public void IntentClass(Class target) {
        Intent intent = new Intent(MainActivity.this, target);
        startActivity(intent);
    }
}