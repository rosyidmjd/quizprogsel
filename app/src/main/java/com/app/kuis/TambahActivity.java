package com.app.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.kuis.database.AppDatabase;
import com.app.kuis.database.entitas.Mahasiswa;

public class TambahActivity extends AppCompatActivity {

    EditText etNama, etNPM;
    Button btnSimpan;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.etNama);
        etNPM = findViewById(R.id.etNPM);
        btnSimpan = findViewById(R.id.btnSimpan);
        database = AppDatabase.getInstance(getApplicationContext());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Mahasiswa mahasiswa = new Mahasiswa();
                    mahasiswa.fullName = etNama.getText().toString();
                    mahasiswa.npm = etNPM.getText().toString();

                    String nama = etNama.getText().toString().trim();
                    String npm = etNPM.getText().toString().trim();

                    if (nama.isEmpty() || npm.isEmpty()) {
                        Toast.makeText(TambahActivity.this, "Data wajib diisi.!", Toast.LENGTH_SHORT).show();
                    } else {
                        database.mahasiswaDao().insertAll(mahasiswa);
                        Toast.makeText(TambahActivity.this, "Data Berhasil diinput", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
        });
    }
}