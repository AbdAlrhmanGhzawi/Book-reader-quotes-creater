package com.example.book.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class Common {

    public static int CurrentBookId = -1;


    //region Requests Codes

    public static final int GALLERY_REQUEST_CODE = 200;
    public static final int CROP_REQUEST_CODE = CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;
    public static final int MAP_REQUEST_CODE = 300;

    //endregion

    //region Permissions

    //region Permissions Requests

    public static final int EXTERNAL_REQUEST_PERMISSION = 500;
    public static final int LOCATION_REQUEST_PERMISSION = 501;
    public static final int CAMERA_REQUEST_PERMISSION = 502;

    //endregion

    public static boolean isPermissionsGranted(Context context,
                                               String[] permissions)
    {
        for (int i = 0; i < permissions.length; i++)
        {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED)
            {
                return false;
            }
        }

        return true;
    }

    public static void requestRuntimePermissions(Activity activity,
                                                 Fragment fragment,
                                                 String[] permissions,
                                                 int requestPermission)
    {
        if (activity != null)
        {
            ActivityCompat.requestPermissions(
                    activity,
                    permissions,
                    requestPermission);
        }
        else if (fragment != null)
        {
            fragment.requestPermissions(
                    permissions,
                    requestPermission);
        }
    }

    //endregion

    //region Images

    public static void chooseImage(Activity activity, Fragment fragment)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (activity != null)
        {
            activity.startActivityForResult(intent, GALLERY_REQUEST_CODE);
        }
        else if (fragment != null)
        {
            fragment.startActivityForResult(intent, GALLERY_REQUEST_CODE);
        }
    }

    public static void cropImage(Activity activity, Fragment fragment, Uri imageUri)
    {
        if (activity != null && fragment == null)
        {
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .setMinCropWindowSize(500, 500)
                    .start(activity);
        }
        else
        {
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .setMinCropWindowSize(500, 500)
                    .start(activity, fragment);
        }
    }

    public static byte[] getBytesFromImageView(ImageView imageView)
    {
        Bitmap bitmap;
        if (imageView instanceof RoundedImageView)
        {
            bitmap = ((RoundedDrawable) imageView.getDrawable()).toBitmap();
        }
        else
        {
            bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    //endregion
}

