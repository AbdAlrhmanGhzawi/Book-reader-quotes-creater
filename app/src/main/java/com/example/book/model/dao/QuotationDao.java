package com.example.book.model.dao;

import com.example.book.model.entity.QuotationEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface QuotationDao {

    @Insert
    void insert(QuotationEntity quotationEntity);

    @Delete
    void delete(QuotationEntity quotationEntity);

    @Update
    void update(QuotationEntity quotationEntity);

    @Query("Select * From Quotations")
    LiveData<List<QuotationEntity>> getAll();

    @Query("Select Count(*) From Quotations")
    LiveData<Integer> getCount();

    @Query("Select * From Quotations Where BookId = :bookId")
    LiveData<List<QuotationEntity>> getByBook(int bookId);

    @Query("Select * From Quotations Where Name Like ''%:search%''")
    LiveData<List<QuotationEntity>> getByBook(String search);
}
