package com.app.kuis.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mahasiswa {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String npm;

    @ColumnInfo(name = "full_name")
    public String fullName;
}
