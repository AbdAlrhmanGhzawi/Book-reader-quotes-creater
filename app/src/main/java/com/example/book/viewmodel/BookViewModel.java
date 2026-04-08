package com.example.book.viewmodel;

import android.app.Application;

import com.example.book.model.database.BookDatabase;
import com.example.book.model.entity.BookEntity;
import com.example.book.repository.BookRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BookViewModel extends AndroidViewModel
{
    private BookRepository bookRepository;

    public BookViewModel(@NonNull Application application)
    {
        super(application);

        bookRepository =  new BookRepository(application);
    }

    public void insert(BookEntity bookEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            bookRepository.insert(bookEntity);
        });
    }

    public void delete(BookEntity bookEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            bookRepository.delete(bookEntity);
        });
    }

    public void update(BookEntity bookEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
           bookRepository.update(bookEntity);
        });
    }

    public LiveData<List<BookEntity>> getAll()
    {
        return bookRepository.getAll();
    }

    public LiveData<Integer> getCount()
    {
        return bookRepository.getCount();
    }
}

