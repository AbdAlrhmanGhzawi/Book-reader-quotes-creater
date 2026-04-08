package com.example.book.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.model.entity.BookEntity;
import com.example.book.model.entity.QuotationEntity;
import com.example.book.utils.Common;
import com.example.book.view.adapter.BookRecyclerAdapter;
import com.example.book.view.adapter.QuotationRecyclerAdapter;
import com.example.book.view.dialog.BookInfoSheetFragment;
import com.example.book.view.dialog.QuotationInfoSheetFragment;
import com.example.book.viewmodel.BookViewModel;
import com.example.book.viewmodel.QuotationViewModel;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private BookViewModel bookViewModel;
    private QuotationViewModel quotationViewModel;

    private FloatingActionMenu menuMain;
    private FloatingActionButton fabMainBookAdd;
    private FloatingActionButton fabMainQuotationAdd;

    private RecyclerView recyclerMainBooks;
    private BookRecyclerAdapter bookRecyclerAdapter;
    private List<BookEntity> books = new ArrayList<>();

    private RecyclerView recyclerMainQuotations;
    private QuotationRecyclerAdapter quotationRecyclerAdapter;
    private List<QuotationEntity> quotations = new ArrayList<>();
    BottomNavigationView bottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom);
      bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.read:
                startActivity(new Intent(getApplicationContext(),ThirdActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            case R.id.home:
               return true;
            case R.id.summary:
                startActivity(new Intent(getApplicationContext(),FourthActivity.class));
                overridePendingTransition(0,0);


        }
        return true;
    }
});


        bookViewModel = new ViewModelProvider(MainActivity.this).get(BookViewModel.class);
        quotationViewModel = new ViewModelProvider(MainActivity.this).get(QuotationViewModel.class);

        menuMain = findViewById(R.id.menuMain);
        fabMainBookAdd = findViewById(R.id.fabMainBookAdd);
        fabMainQuotationAdd = findViewById(R.id.fabMainQuotationAdd);



        fabMainBookAdd.setOnClickListener(v ->
        {
            BookInfoSheetFragment bookInfoSheetFragment = new BookInfoSheetFragment();
            bookInfoSheetFragment.show(getSupportFragmentManager(), "BookInfoSheetFragment");

            menuMain.close(true);
        });
        fabMainQuotationAdd.setOnClickListener(v ->
        {
            if (Common.CurrentBookId == -1) {
                Toast.makeText(this, "Please choose a book.", Toast.LENGTH_SHORT).show();
                return;
            }

            QuotationInfoSheetFragment quotationInfoSheetFragment = new QuotationInfoSheetFragment();
            quotationInfoSheetFragment.show(getSupportFragmentManager(), "QuotationInfoSheetFragment");

            menuMain.close(true);
        });

        recyclerMainBooks = findViewById(R.id.recyclerMainBooks);
        recyclerMainBooks.setHasFixedSize(true);
        bookRecyclerAdapter = new BookRecyclerAdapter(MainActivity.this, books);
        recyclerMainBooks.setAdapter(bookRecyclerAdapter);

        recyclerMainQuotations = findViewById(R.id.recyclerMainQuotations);
        recyclerMainQuotations.setHasFixedSize(true);
        quotationRecyclerAdapter = new QuotationRecyclerAdapter(MainActivity.this, quotations );
        recyclerMainQuotations.setAdapter(quotationRecyclerAdapter);


        bookGetAll();
    }

    //region Books

    public void bookInsert(BookEntity book) {
        bookViewModel.insert(book);
    }

    public void bookDelete(BookEntity book) {
        Common.CurrentBookId = -1;
        bookViewModel.delete(book);
    }

    public void bookUpdate(BookEntity book) {
        bookViewModel.update(book);
    }

    private void bookGetAll() {
        bookViewModel.getAll().observe(this, books ->
        {
            MainActivity.this.books = books;
            bookRecyclerAdapter.setData(books);

            if (books.size() > 0) {
                Common.CurrentBookId = books.get(0).getId();
                quotationGetByBook();
            } else {
                Toast.makeText(MainActivity.this, "No books.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //endregion

    //region Quotations

    public void quotationInsert(QuotationEntity quotation) { quotationViewModel.insert(quotation); }

    public void quotationDelete(QuotationEntity quotation) { quotationViewModel.delete(quotation); }

    public void quotationUpdate(QuotationEntity quotation) {
        quotationViewModel.update(quotation);
    }

    public void quotationGetByBook() {
        quotationViewModel.getByBook(Common.CurrentBookId).observe(this, quotations ->
        {
            MainActivity.this.quotations = quotations;
            quotationRecyclerAdapter.setData(quotations);

            if (quotations.size() == 0 && Common.CurrentBookId != -1) {
                Toast.makeText(MainActivity.this, "No quotes for this book.", Toast.LENGTH_SHORT).show();
            }
        });
    }
//endregion


    @Override
    public void onBackPressed() {
            super.onBackPressed();
            finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage(" Do You Want To Exit ?");
            builder.setCancelable(true);

            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finishAffinity();
                }
            });
            builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return true;
    }
}


