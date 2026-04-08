package com.example.book.repository;

import android.app.Application;

import com.example.book.model.dao.SummaryDao;
import com.example.book.model.database.BookDatabase;
import com.example.book.model.database.SummaryDatabase;
import com.example.book.model.entity.SummaryEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SummaryRepository {
    private SummaryDao summaryDao;

    public SummaryRepository(Application application)
    {
        summaryDao = SummaryDatabase.getDatabase(application).getSummaryDao();
    }

    public void insert(SummaryEntity summaryEntity)
    {
        SummaryDatabase.databaseWriteExecutor.execute(() ->
        {
            summaryDao.insert(summaryEntity);
        });
    }

    public void delete(SummaryEntity summaryEntity)
    {
        SummaryDatabase.databaseWriteExecutor.execute(() ->
        {
            summaryDao.delete(summaryEntity);
        });
    }

    public void update(SummaryEntity summaryEntity)
    {
        SummaryDatabase.databaseWriteExecutor.execute(() ->
        {
            summaryDao.update(summaryEntity);
        });
    }

    public LiveData<List<SummaryEntity>> getAll()
    {
        return summaryDao.getAll();
    }

    public LiveData<Integer> getCount()
    {
        return summaryDao.getCount();
    }
}


