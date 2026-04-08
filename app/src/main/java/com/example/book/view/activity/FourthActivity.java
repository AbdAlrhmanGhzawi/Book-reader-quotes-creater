package com.example.book.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.book.R;
import com.example.book.model.entity.SummaryEntity;
import com.example.book.view.adapter.SummaryRecyclerAdapter;
import com.example.book.view.dialog.SummaryInfoSheetFragment;
import com.example.book.viewmodel.SummaryViewModel;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {

    private SummaryViewModel summaryViewModel;
    private FloatingActionMenu menuMain2;
    private FloatingActionButton fabMainSummaryAdd;
    private RecyclerView recyclerMainSummary;
    private SummaryRecyclerAdapter summaryRecyclerAdapter;
    private List<SummaryEntity> summary = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.read:
                        startActivity(new Intent(getApplicationContext(),ThirdActivity.class));
                        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

                    case R.id.summary:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);


                }
                return true;
            }
        });

        


        summaryViewModel = new ViewModelProvider(FourthActivity.this).get(SummaryViewModel.class);
        menuMain2 = findViewById(R.id.menuMain2);
        fabMainSummaryAdd = findViewById(R.id.fabMainSummaryAdd);
        fabMainSummaryAdd.setOnClickListener(v ->
        {
            SummaryInfoSheetFragment summaryInfoSheetFragment = new SummaryInfoSheetFragment();
            summaryInfoSheetFragment.show(getSupportFragmentManager(), "SummaryInfoSheetFragment");

            menuMain2.close(true);
        });

        recyclerMainSummary = findViewById(R.id.recyclerMainSummary);
        recyclerMainSummary.setHasFixedSize(true);
        summaryRecyclerAdapter = new SummaryRecyclerAdapter(FourthActivity.this, summary);
        recyclerMainSummary.setAdapter(summaryRecyclerAdapter);




        summaryGetAll();
    }

    //region Summary

    public void summaryInsert(SummaryEntity summary) {
        summaryViewModel.insert(summary);
    }

    public void summaryDelete(SummaryEntity summary) {

        summaryViewModel.delete(summary);
    }

    public void summaryUpdate(SummaryEntity summary) {
        summaryViewModel.update(summary);
    }

    private void summaryGetAll() {
        summaryViewModel.getAll().observe(this, summary ->
        {
            FourthActivity.this.summary = summary;
            summaryRecyclerAdapter.setData(summary);

        });
    }

    //endregion




}

