package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.bedsitmana.Adapter.Coso_Adapter;
import com.example.bedsitmana.Adapter.NguoiThue_Adapter;
import com.example.bedsitmana.Dao.coSoDao;
import com.example.bedsitmana.Dao.nguoiThueDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.Coso;
import com.example.bedsitmana.model.NguoiThue;

import java.util.ArrayList;

public class coSo_Activity extends AppCompatActivity {
    ListView lstCoso;
    ArrayList<Coso> list;
    Coso_Adapter cosoAdapter;
    Coso coso;
    coSoDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co_so);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cơ sở");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lstCoso = findViewById(R.id.lstCoSo);
        dao = new coSoDao(coSo_Activity.this);
        list = (ArrayList<Coso>) dao.getAll();
        cosoAdapter = new Coso_Adapter(coSo_Activity.this,list,this);
        lstCoso.setAdapter(cosoAdapter);
    }
}