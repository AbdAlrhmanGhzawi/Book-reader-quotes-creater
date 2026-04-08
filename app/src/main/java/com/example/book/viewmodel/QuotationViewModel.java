package com.example.book.viewmodel;

import android.app.Application;

import com.example.book.model.database.BookDatabase;
import com.example.book.model.entity.QuotationEntity;
import com.example.book.repository.QuotationRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class QuotationViewModel extends AndroidViewModel
{
    private QuotationRepository quotationRepository;

    public QuotationViewModel(@NonNull Application application)
    {
        super(application);

        quotationRepository = new QuotationRepository(application);
    }

    public void insert(QuotationEntity quotationEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            quotationRepository.insert(quotationEntity);
        });
    }

    public void delete(QuotationEntity quotationEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            quotationRepository.delete(quotationEntity);
        });
    }

    public void update(QuotationEntity quotationEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            quotationRepository.update(quotationEntity);
        });
    }

    public LiveData<List<QuotationEntity>> getAll()
    {
        return quotationRepository.getAll();
    }

    public LiveData<Integer> getCount()
    {
        return quotationRepository.getCount();
    }

    public LiveData<List<QuotationEntity>> getByBook(int bookId) { return quotationRepository.getByBook(bookId); }
}
