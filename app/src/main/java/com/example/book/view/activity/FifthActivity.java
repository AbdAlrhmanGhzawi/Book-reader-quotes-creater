package com.example.book.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.book.R;

public class FifthActivity extends AppCompatActivity {

    private TextView txtBN;
    private TextView txtBS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        txtBN = findViewById(R.id.txtBN);
        txtBS = findViewById(R.id.txtBS);

        txtBN.setText(getIntent().getStringExtra("BookName"));
        txtBS.setText(getIntent().getStringExtra("SummaryBook"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.static_anmiation,R.anim.zoom_out);
    }
}