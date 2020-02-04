package com.example.paisamanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Records extends AppCompatActivity {
    private  ArrayList<Users> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Bundle bundle = getIntent().getExtras();
        data = (ArrayList<Users>) bundle.get("data");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        itemsList = FileHelper.readData(this);
        recyclerView.setAdapter(new MyAdapter(this, data));

    }
}
