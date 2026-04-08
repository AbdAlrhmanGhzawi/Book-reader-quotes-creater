package com.example.book.viewmodel;


import android.app.Application;

import com.example.book.model.database.BookDatabase;
import com.example.book.model.database.SummaryDatabase;
import com.example.book.model.entity.SummaryEntity;
import com.example.book.repository.SummaryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SummaryViewModel extends AndroidViewModel
{
    private SummaryRepository summaryRepository;

    public SummaryViewModel(@NonNull Application application)
    {
        super(application);

        summaryRepository =  new SummaryRepository(application);
    }

    public void insert(SummaryEntity summaryEntity)
    {
        SummaryDatabase.databaseWriteExecutor.execute(() ->
        {
            summaryRepository.insert(summaryEntity);
        });
    }

    public void delete(SummaryEntity summaryEntity)
    {
        SummaryDatabase.databaseWriteExecutor.execute(() ->
        {
            summaryRepository.delete(summaryEntity);
        });
    }

    public void update(SummaryEntity summaryEntity)
    {
        SummaryDatabase.databaseWriteExecutor.execute(() ->
        {
            summaryRepository.update(summaryEntity);
        });
    }

    public LiveData<List<SummaryEntity>> getAll()
    {
        return summaryRepository.getAll();
    }

    public LiveData<Integer> getCount()
    {
        return summaryRepository.getCount();
    }
}

