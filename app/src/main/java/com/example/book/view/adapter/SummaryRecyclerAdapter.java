package com.example.book.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.book.view.activity.FifthActivity;
import com.example.book.view.activity.FourthActivity;
import com.example.book.R;
import com.example.book.model.entity.SummaryEntity;
import com.example.book.view.dialog.SummaryInfoSheetFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SummaryRecyclerAdapter extends RecyclerView.Adapter<SummaryRecyclerAdapter.SummaryViewHolder>
{
    private Context context;
    private List<SummaryEntity> summary;


    public SummaryRecyclerAdapter(Context context, List<SummaryEntity> summary)
    {
        this.context = context;
        this.summary = summary;
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_summary, parent, false);
        return new SummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position)
    {
        SummaryEntity sum = summary.get(position);
        holder.getTxtSummary().setText(sum.getSummary());
        holder.getTxtSummaryName().setText(sum.getName());

        holder.itemView.setOnLongClickListener(v ->
        {
            SummaryInfoSheetFragment summaryInfoSheetFragment = new SummaryInfoSheetFragment();
            summaryInfoSheetFragment.setIsAdding(false);
            summaryInfoSheetFragment.setCurrentSummary(sum);
            summaryInfoSheetFragment.show(((FourthActivity) context).getSupportFragmentManager(), "SummaryInfoSheetFragment");

            return true;
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FifthActivity.class);
                intent.putExtra("BookName",sum.getName());
                intent.putExtra("SummaryBook",sum.getSummary());
                context.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount()
    {
        return summary.size();
    }

    public void setData(List<SummaryEntity> summary)
    {
        this.summary = summary;
        notifyDataSetChanged();
    }

    public class SummaryViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtSummaryName;
        private TextView txtSummary;

        public  SummaryViewHolder(View itemView)
        {
            super(itemView);

            txtSummaryName = itemView.findViewById(R.id.txtSummaryName);
            txtSummary=itemView.findViewById(R.id.txtSummary);
        }

        public TextView getTxtSummaryName()
        {
            return txtSummaryName;
        }
        public TextView getTxtSummary()
        {
            return txtSummary;
        }

    }
}

