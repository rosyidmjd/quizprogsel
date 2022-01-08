package com.app.kuis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.kuis.database.AppDatabase;
import com.app.kuis.database.entitas.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    Button btnLogout, btnTambah;
    RecyclerView rvMahasiswa;
    AppDatabase database;
    MahasiswaAdapter mahasiswaAdapter;
    List<Mahasiswa> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final SharedPrefManager sharedPrefManager = new SharedPrefManager(this);

        btnLogout = findViewById(R.id.btnLogout);
        rvMahasiswa = findViewById(R.id.rvMahasiswa);
        btnTambah = findViewById(R.id.btnTambah);
        database = AppDatabase.getInstance(getApplicationContext());
        list = new ArrayList<>();
        list.clear();
        list.addAll(database.mahasiswaDao().getAll());
        mahasiswaAdapter = new MahasiswaAdapter(list, getApplicationContext());

        rvMahasiswa.setHasFixedSize(true);
        rvMahasiswa.setLayoutManager(new LinearLayoutManager(this));
        rvMahasiswa.setAdapter(mahasiswaAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, TambahActivity.class);
                startActivity(i);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, MainActivity.class);
                sharedPrefManager.saveIsLogin(false);
                finishAffinity();
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        list.clear();
        list.addAll(database.mahasiswaDao().getAll());
        mahasiswaAdapter.notifyDataSetChanged();
    }

}