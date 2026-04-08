package com.example.book.model.database;


import android.content.Context;

import com.example.book.model.dao.BookDao;
import com.example.book.model.dao.QuotationDao;
import com.example.book.model.dao.SummaryDao;
import com.example.book.model.entity.BookEntity;
import com.example.book.model.entity.QuotationEntity;
import com.example.book.model.entity.SummaryEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        SummaryEntity.class

}, version = 1, exportSchema = false)
public abstract class SummaryDatabase extends RoomDatabase
{
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    private static volatile SummaryDatabase INSTANCE;
    public static SummaryDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (SummaryDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SummaryDatabase.class, "SummaryDb")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public  abstract SummaryDao getSummaryDao();



}
