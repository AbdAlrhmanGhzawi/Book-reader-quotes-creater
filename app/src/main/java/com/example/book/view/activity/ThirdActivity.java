package com.example.book.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.book.OnPdfSelectListener;
import com.example.book.R;
import com.example.book.view.adapter.PdfAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ThirdActivity extends AppCompatActivity implements OnPdfSelectListener {
    private PdfAdapter adapter;
    private List<File> pdfList;
    private RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        bottomNavigationView=findViewById(R.id.bottom3);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.read:
                        return true;

                    case R.id.summary:
                        startActivity(new Intent(getApplicationContext(), FourthActivity.class));
                        overridePendingTransition(R.anim.zoom_in,R.anim.static_anmiation);

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

                }
                return true;
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        runtimePermission();
    }
    public  void runtimePermission(){
        Dexter.withContext(ThirdActivity.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        displayPdf();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findPdf (File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();
        for (File singleFile : files)
        {
            if( singleFile.isDirectory()&& !singleFile.isHidden()){
                arrayList.addAll(findPdf(singleFile));
            }
            else {
                if (singleFile.getName().endsWith(".pdf")){
                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }
    public  void displayPdf(){
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        pdfList = new ArrayList<>();
        pdfList.addAll(findPdf(Environment.getExternalStorageDirectory()));
        adapter=new PdfAdapter(this,pdfList,this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onPdfSelected(File file) {
        startActivity(new Intent(ThirdActivity.this, PdfActivity.class)
                .putExtra("path",file.getAbsolutePath()));

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
            case 5:
                break;
        }
        return true;
    }


   }

