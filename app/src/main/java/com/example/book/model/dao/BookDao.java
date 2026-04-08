package com.example.book.model.dao;

import com.example.book.model.entity.BookEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BookDao {

    @Insert
    void insert(BookEntity bookEntity);

    @Delete
    void delete(BookEntity bookEntity);

    @Update
    void update(BookEntity bookEntity);

    @Query("Select Id, Name, Image From Books")
    LiveData<List<BookEntity>> getAll();

    @Query("Select Count(*) From Books")
    LiveData<Integer> getCount();
}


