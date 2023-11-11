package com.example.bedsitmana.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.bedsitmana.Adapter.NguoiThue_Adapter;
import com.example.bedsitmana.Dao.nguoiThueDao;
import com.example.bedsitmana.R;
import com.example.bedsitmana.model.NguoiThue;

import java.util.ArrayList;

public class nguoiThue_Activity extends AppCompatActivity {

    ListView lstNguoiThue;
    ArrayList<NguoiThue> list;
    NguoiThue_Adapter nguoiThueAdapter;
    NguoiThue nguoiThue;
    nguoiThueDao dao;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_thue);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Người thuê");

        Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lstNguoiThue=findViewById(R.id.lstNguoiThue);
        dao =new nguoiThueDao(nguoiThue_Activity.this);
        list = (ArrayList<NguoiThue>) dao.getAll();
        nguoiThueAdapter=new NguoiThue_Adapter(nguoiThue_Activity.this,this,list);
        lstNguoiThue.setAdapter(nguoiThueAdapter);
    }
}