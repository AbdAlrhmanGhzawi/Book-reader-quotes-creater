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
import com.example.book.R;
import com.example.book.model.entity.QuotationEntity;
import com.example.book.utils.Common;
import com.example.book.view.activity.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

public class QuotationInfoSheetFragment extends BottomSheetDialogFragment
{
    private boolean isAdding = true;
    private QuotationEntity currentQuotation =  null;
    private ImageView imgQuotationInfoImage;
    private EditText edtQuotationInfoName;
    private EditText edtQuotationInfoPage;
    private EditText edtQuotationInfoContent;
    private Button btnQuotationInfoSave;
    private Button btnQuotationInfoDelete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sheet_fragment_quotation_info, null);

        imgQuotationInfoImage = view.findViewById(R.id.imgQuotationInfoImage);
        edtQuotationInfoName = view.findViewById(R.id.edtQuotationInfoName);
        edtQuotationInfoPage = view.findViewById(R.id.edtQuotationInfoPage);
        edtQuotationInfoContent = view.findViewById(R.id.edtQuotationInfoContent);
        btnQuotationInfoSave = view.findViewById(R.id.btnQuotationInfoSave);
        btnQuotationInfoDelete = view.findViewById(R.id.btnQuotationInfoDelete);

        if (isAdding)
        {
            btnQuotationInfoDelete.setVisibility(View.GONE);
        }
        else
        {
            Glide.with(this)
                    .load(currentQuotation.getImage())
                    .placeholder(R.drawable.ic_book)
                    .into(imgQuotationInfoImage);
            edtQuotationInfoName.setText(currentQuotation.getName());
            edtQuotationInfoPage.setText(currentQuotation.getPage() + "");
            edtQuotationInfoContent.setText(currentQuotation.getContent());
        }

        imgQuotationInfoImage.setOnClickListener(v ->
        {
            if (Common.isPermissionsGranted(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}))
            {
                Common.chooseImage(null, QuotationInfoSheetFragment.this);
            }
            else
            {
                Common.requestRuntimePermissions(null, QuotationInfoSheetFragment.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        Common.EXTERNAL_REQUEST_PERMISSION);
            }
        });

        btnQuotationInfoSave.setOnClickListener(v  ->
        {
            String name  = edtQuotationInfoName.getText().toString().trim();
            if (name.isEmpty())
            {
                Toast.makeText(getActivity(), "Please enter Author name.", Toast.LENGTH_SHORT).show();
                return;
            }

            String page  = edtQuotationInfoPage.getText().toString().trim();
            if (page.isEmpty())
            {
                Toast.makeText(getActivity(), "Please enter quote page number.", Toast.LENGTH_SHORT).show();
                return;
            }

            byte[] image = Common.getBytesFromImageView(imgQuotationInfoImage);

            String content  = edtQuotationInfoContent.getText().toString().trim();

            if (isAdding)
            {
                QuotationEntity quotation =  new QuotationEntity( name, image, Integer.parseInt(page), content, Common.CurrentBookId);
                ((MainActivity) getActivity()).quotationInsert(quotation);
            }
            else
            {
                currentQuotation.setName(name);
                currentQuotation.setImage(image);
                currentQuotation.setPage(Integer.parseInt(page));
                currentQuotation.setContent(content);
                ((MainActivity) getActivity()).quotationUpdate(currentQuotation);
            }

            dismiss();
        });

        btnQuotationInfoDelete.setOnClickListener(v ->
        {
            ((MainActivity) getActivity()). quotationDelete(currentQuotation);
            dismiss();
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case Common.EXTERNAL_REQUEST_PERMISSION:
                if (Common.isPermissionsGranted(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}))
                {
                    Common.chooseImage(null, QuotationInfoSheetFragment.this);
                }
                else
                {
                    Toast.makeText(getActivity(), "Permissions denied.", Toast.LENGTH_SHORT).show();
                }
                break;

            case Common.CAMERA_REQUEST_PERMISSION:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent)
    {
        if (resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case Common.GALLERY_REQUEST_CODE:
                    Uri imageUri = intent.getData();
                    Common.cropImage(getActivity(), QuotationInfoSheetFragment.this, imageUri);
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                    Uri croppedImageUri = result.getUri();
                    imgQuotationInfoImage.setImageURI(croppedImageUri);
                    break;
            }
        }
    }

    public void setAdding(boolean adding)
    {
        isAdding = adding;
    }

    public void setCurrentQuotation(QuotationEntity currentQuotation)
    {
        this.currentQuotation = currentQuotation;
    }
}
