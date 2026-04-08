package com.example.book.view.dialog;


import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.book.view.activity.FourthActivity;
import com.example.book.R;
import com.example.book.model.entity.SummaryEntity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

public class SummaryInfoSheetFragment extends BottomSheetDialogFragment
{
    private boolean isAdding = true;
    private SummaryEntity currentSummary= null;

    private EditText edtSummaryInfoName;
    private EditText  edtSummaryInfoSummary ;
    private Button btnSummaryInfoSave;
    private Button btnSummaryInfoDelete;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sheet_fragment_summary_info, null);

        edtSummaryInfoName = view.findViewById(R.id.edtSummaryInfoName);
        edtSummaryInfoSummary = view.findViewById(R.id.edtSummaryInfoSummary);
        btnSummaryInfoSave = view.findViewById(R.id.btnSummaryInfoSave);
        btnSummaryInfoDelete = view.findViewById(R.id.btnSummaryInfoDelete);

        if (isAdding)
        {
            btnSummaryInfoDelete.setVisibility(View.GONE);
        }
        else
        {
            edtSummaryInfoName.setText(currentSummary.getName());
            edtSummaryInfoSummary.setText(currentSummary.getSummary());
        }



        btnSummaryInfoSave.setOnClickListener(v  ->
        {
            String name  = edtSummaryInfoName.getText().toString().trim();
            if (name.isEmpty())
            {
                Toast.makeText(getActivity(), "Please enter book name.", Toast.LENGTH_SHORT).show();
                return;
            }

            String summary  = edtSummaryInfoSummary.getText().toString().trim();
            if (summary.isEmpty())
            {
                Toast.makeText(getActivity(), "Please enter summary book.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isAdding)
            {
                SummaryEntity summaryEntity =  new SummaryEntity(name,summary);
                ((FourthActivity) getActivity()).summaryInsert(summaryEntity);
            }
            else
            {
                currentSummary.setName(name);
                currentSummary.setSummary(summary);
                ((FourthActivity) getActivity()).summaryUpdate(currentSummary);
            }

            dismiss();
        });

        btnSummaryInfoDelete.setOnClickListener(v ->
        {
            ((FourthActivity) getActivity()).summaryDelete(currentSummary);
            dismiss();
        });

        return view;
    }


    public void setIsAdding(boolean isAdding)
    {
        this.isAdding = isAdding;
    }

    public void setCurrentSummary(SummaryEntity currentSummary)
    {
        this.currentSummary = currentSummary;
    }
}
