package com.example.book.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.book.R;
import com.example.book.model.entity.BookEntity;
import com.example.book.utils.Common;
import com.example.book.view.activity.MainActivity;
import com.example.book.view.dialog.BookInfoSheetFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.BookViewHolder>
{
    private Context context;
    private List<BookEntity> books;


    public BookRecyclerAdapter(Context context, List<BookEntity> books)
    {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position)
    {
        BookEntity book = books.get(position);

        holder.getTxtBookName().setText(book.getName());
        Glide.with(context)
                .load(book.getImage())
                .placeholder(R.drawable.ic_book)
                .into(holder.getImgBookImage());

        holder.itemView.setOnClickListener(v ->
        {
            Common.CurrentBookId = book.getId();
            ((MainActivity) context).quotationGetByBook();
        });

        holder.itemView.setOnLongClickListener(v ->
        {
            BookInfoSheetFragment bookInfoSheetFragment = new BookInfoSheetFragment();
            bookInfoSheetFragment.setIsAdding(false);
            bookInfoSheetFragment.setCurrentBook(book);
            bookInfoSheetFragment.show(((MainActivity) context).getSupportFragmentManager(), "BookInfoSheetFragment");

            return true;
        });
    }

    @Override
    public int getItemCount()
    {
        return books.size();
    }

    public void setData(List<BookEntity> books)
    {
        this.books = books;
        notifyDataSetChanged();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imgBookImage;
        private TextView txtBookName;

        public BookViewHolder(View itemView)
        {
            super(itemView);

            imgBookImage = itemView.findViewById(R.id.imgBookImage);
            txtBookName = itemView.findViewById(R.id.txtBookName);
        }

        public ImageView getImgBookImage()
        {
            return imgBookImage;
        }

        public TextView getTxtBookName()
        {
            return txtBookName;
        }
    }
}
