package com.example.book.view.activity;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.book.R;
import com.example.book.model.entity.QuotationEntity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
private TextView txtQuotationContent;
private TextView txtQuotationName;
  private ImageView  imgQuotationImage;
public static final String EXTRA_QUOTATION ="extra_quotation";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtQuotationContent = findViewById(R.id.txtQuotationContent);
        txtQuotationName = findViewById(R.id.txtQuotationName);
         imgQuotationImage =findViewById(R.id.imgQuotationImage);

        QuotationEntity quotation= (QuotationEntity) getIntent().getSerializableExtra(EXTRA_QUOTATION);
        Glide.with(this).load(quotation.getImage()).into(imgQuotationImage);
        txtQuotationName.setText(quotation.getName());
        txtQuotationContent.setText(quotation.getContent());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.static_anmiation,R.anim.zoom_out);
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


