package com.example.book.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.book.OnPdfSelectListener;
import com.example.book.R;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {
    private Context context;
    private List<File> pdfFiles;
    private OnPdfSelectListener listener;

    public PdfAdapter(Context context, List<File> pdfFiles, OnPdfSelectListener listener) {
        this.context = context;
        this.pdfFiles = pdfFiles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PdfViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_pdf, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder,  int position) {
        holder.txtPdfName.setText(pdfFiles.get(position).getName());
        holder.txtPdfName.setSelected(true);

        holder.pdfCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onPdfSelected(pdfFiles.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pdfFiles.size();
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder {
        public TextView txtPdfName;
        public CardView pdfCardView;

        public PdfViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPdfName = itemView.findViewById(R.id.txtPdfName);
            pdfCardView = itemView.findViewById(R.id.pdfCardView);

        }

    }
}
