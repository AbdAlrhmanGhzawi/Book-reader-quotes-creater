package com.example.book.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "summary")

public class SummaryEntity
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id", typeAffinity = ColumnInfo.INTEGER)
    private int id;

    @ColumnInfo(name = "Name", typeAffinity = ColumnInfo.TEXT)
    private String name;

    @ColumnInfo(name = "Summary", typeAffinity = ColumnInfo.TEXT)
    private String summary;

    public SummaryEntity() {
    }

    public SummaryEntity(String name,  String summary) {
        this.name = name;

        this.summary = summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
