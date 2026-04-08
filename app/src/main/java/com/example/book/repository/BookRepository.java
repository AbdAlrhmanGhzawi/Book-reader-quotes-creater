package com.example.book.repository;

import android.app.Application;

import com.example.book.model.dao.BookDao;
import com.example.book.model.database.BookDatabase;
import com.example.book.model.entity.BookEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

public class BookRepository {
    private BookDao bookDao;

    public BookRepository(Application application)
    {
        bookDao = BookDatabase.getDatabase(application).getBookDao();
    }

    public void insert(BookEntity bookEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            bookDao.insert(bookEntity);
        });
    }

    public void delete(BookEntity bookEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            bookDao.delete(bookEntity);
        });
    }

    public void update(BookEntity bookEntity)
    {
        BookDatabase.databaseWriteExecutor.execute(() ->
        {
            bookDao.update(bookEntity);
        });
    }

    public LiveData<List<BookEntity>> getAll()
    {
        return bookDao.getAll();
    }

    public LiveData<Integer> getCount()
    {
        return bookDao.getCount();
    }
}
