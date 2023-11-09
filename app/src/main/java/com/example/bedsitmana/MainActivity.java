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

import com.example.bedsitmana.Activity.coSo_Activity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView nav;
    View mHeader;
    Button btnCoso,btnPhong,btnNguoiThue,btnThongKe,btnHoaDon,btnSuCo;


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
        btnCoso=findViewById(R.id.btnCoso);
        btnHoaDon=findViewById(R.id.btnHoaDon);
        btnPhong=findViewById(R.id.btnPhong);
        btnNguoiThue=findViewById(R.id.btnNguoiThue);
        btnThongKe=findViewById(R.id.btnThongKe);
        btnHoaDon=findViewById(R.id.btnHoaDon);
//        frg_trangchu frgTrangchu = new frg_trangchu();
//        replaceFrg(frgTrangchu);
        btnCoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, coSo_Activity.class);
                startActivity(intent);

            }
        });
        nav.setItemIconTintList(null);


    }
    public void replaceFrg(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmnav,frg).commit();
    }
}