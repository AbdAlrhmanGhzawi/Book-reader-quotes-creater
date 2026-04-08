package com.example.book.model.dao;

import com.example.book.model.entity.SummaryEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SummaryDao {
    @Insert
    void insert(SummaryEntity summaryEntity);

    @Delete
    void delete(SummaryEntity summaryEntity);

    @Update
    void update(SummaryEntity summaryEntity);

    @Query("Select Id,Name,Summary From Summary")
    LiveData<List<SummaryEntity>> getAll();

    @Query("Select Count(*) From Summary")
    LiveData<Integer> getCount();
}

