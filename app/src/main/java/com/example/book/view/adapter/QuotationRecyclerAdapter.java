package com.example.book.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.book.view.activity.SecondActivity;
import com.example.book.R;
import com.example.book.model.entity.QuotationEntity;
import com.example.book.view.activity.MainActivity;
import com.example.book.view.dialog.QuotationInfoSheetFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuotationRecyclerAdapter  extends RecyclerView.Adapter<QuotationRecyclerAdapter.QuotationViewHolder>
{
    private Context context;
    private List<QuotationEntity> quotations;


    public QuotationRecyclerAdapter(Context context, List<QuotationEntity> quotations) {
        this.context = context;
        this.quotations = quotations;

    }

    @NonNull
    @Override
    public QuotationViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_quotation, parent, false);
        return new QuotationViewHolder(view);
    }

    @Override
    public void onBindViewHolder( QuotationViewHolder holder,  int position)
    {
        QuotationEntity quotation = quotations.get(position);

        holder.getTxtQuotationName().setText(quotation.getName());
        holder.getTxtQuotationPage().setText(" Page"+ " " + quotation.getPage() );
        holder.getTxtQuotationContent().setText(quotation.getContent());
        Glide.with(context)
                .load(quotation.getImage())
                .placeholder(R.drawable.ic_book)
                .into(holder.getImgQuotationImage());

        holder.itemView.setOnLongClickListener(v ->
        {
            QuotationInfoSheetFragment quotationInfoSheetFragment = new QuotationInfoSheetFragment();
            quotationInfoSheetFragment.setAdding(false);
            quotationInfoSheetFragment.setCurrentQuotation(quotation);
            quotationInfoSheetFragment.show(((MainActivity) context).getSupportFragmentManager(), "QuotationInfoSheetFragment");
            return true;
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra(SecondActivity.EXTRA_QUOTATION,quotations.get(position));
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount()
    {
        return quotations.size();
    }
    public void setData(List<QuotationEntity> quotations)
    {
        this.quotations = quotations;
        notifyDataSetChanged();
    }

    public class QuotationViewHolder extends RecyclerView.ViewHolder  {
        private ImageView imgQuotationImage;
        private TextView txtQuotationName;
        private TextView txtQuotationPage;
        private TextView txtQuotationContent;

        public QuotationViewHolder(View itemView) {
            super(itemView);

            imgQuotationImage = itemView.findViewById(R.id.imgQuotationImage);
            txtQuotationName = itemView.findViewById(R.id.txtQuotationName);
            txtQuotationPage = itemView.findViewById(R.id.txtQuotationPage);
            txtQuotationContent = itemView.findViewById(R.id.txtQuotationContent);

        }



        public ImageView getImgQuotationImage() {
            return imgQuotationImage;
        }

        public TextView getTxtQuotationName() {
            return txtQuotationName;
        }

        public TextView getTxtQuotationPage() {
            return txtQuotationPage;
        }

        public TextView getTxtQuotationContent() {
            return txtQuotationContent;
        }


    }

}

