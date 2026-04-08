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
import com.example.book.model.entity.BookEntity;
import com.example.book.utils.Common;
import com.example.book.view.activity.MainActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.theartofdev.edmodo.cropper.CropImage;

import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;

public class BookInfoSheetFragment  extends BottomSheetDialogFragment
{
    private boolean isAdding = true;
    private BookEntity currentBook = null;

    private ImageView imgBookInfoImage;
    private EditText edtBookInfoName;
    private Button btnBookInfoSave;
    private Button btnBookInfoDelete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sheet_fragment_book_info, null);

        imgBookInfoImage = view.findViewById(R.id.imgBookInfoImage);
        edtBookInfoName = view.findViewById(R.id.edtBookInfoName);
        btnBookInfoSave = view.findViewById(R.id.btnBookInfoSave);
        btnBookInfoDelete = view.findViewById(R.id.btnBookInfoDelete);

        if (isAdding)
        {
            btnBookInfoDelete.setVisibility(View.GONE);
        }
        else
        {
            Glide.with(this)
                    .load(currentBook.getImage())
                    .placeholder(R.drawable.ic_book)
                    .into(imgBookInfoImage);
            edtBookInfoName.setText(currentBook.getName());
        }

        imgBookInfoImage.setOnClickListener(v ->
        {
            if (Common.isPermissionsGranted(getActivity(),
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }))
            {
                Common.chooseImage(null, BookInfoSheetFragment.this);
            }
            else
            {
                Common.requestRuntimePermissions(null, BookInfoSheetFragment.this,
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        Common.EXTERNAL_REQUEST_PERMISSION);
            }
        });

        btnBookInfoSave.setOnClickListener(v  ->
        {
            String name  = edtBookInfoName.getText().toString().trim();
            if (name.isEmpty())
            {
                Toast.makeText(getActivity(), "Please enter book name.", Toast.LENGTH_SHORT).show();
                return;
            }

            byte[] image = Common.getBytesFromImageView(imgBookInfoImage);

            if (isAdding)
            {
                BookEntity book =  new BookEntity(name, image);
                ((MainActivity) getActivity()).bookInsert(book);
            }
            else
            {
                currentBook.setName(name);
                currentBook.setImage(image);
                ((MainActivity) getActivity()).bookUpdate(currentBook);
            }

            dismiss();
        });

        btnBookInfoDelete.setOnClickListener(v ->
        {
            ((MainActivity) getActivity()).bookDelete(currentBook);
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
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }))
                {
                    Common.chooseImage(null, BookInfoSheetFragment.this);
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
                    Common.cropImage(getActivity(), BookInfoSheetFragment.this, imageUri);
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(intent);
                    Uri croppedImageUri = result.getUri();
                    imgBookInfoImage.setImageURI(croppedImageUri);
                    break;
            }
        }
    }

    public void setIsAdding(boolean isAdding)
    {
        this.isAdding = isAdding;
    }

    public void setCurrentBook(BookEntity currentBook)
    {
        this.currentBook = currentBook;
    }
}
