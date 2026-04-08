package com.example.book.repository;

import android.app.Application;

import com.example.book.model.dao.QuotationDao;
import com.example.book.model.database.BookDatabase;
import com.example.book.model.entity.QuotationEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

public class
QuotationRepository {

    private QuotationDao quotationDao;

    public QuotationRepository (Application application)
    {
        quotationDao = BookDatabase.getDatabase(application).getQuotationDao();
    }

    public void insert(QuotationEntity quotationEntity)
    {
       BookDatabase.databaseWriteExecutor.execute(() ->
        {
            quotationDao.insert(quotationEntity);
        });
    }

    public void delete(QuotationEntity quotationEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            quotationDao.delete(quotationEntity);
        });
    }

    public void update(QuotationEntity quotationEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            quotationDao.update(quotationEntity);
        });
    }

    public LiveData<List<QuotationEntity>> getAll()
    {
        return quotationDao.getAll();
    }

    public LiveData<Integer> getCount()
    {
        return quotationDao.getCount();
    }

    public LiveData<List<QuotationEntity>> getByBook( int bookId)
    {
        return quotationDao.getByBook(bookId);
    }
}

