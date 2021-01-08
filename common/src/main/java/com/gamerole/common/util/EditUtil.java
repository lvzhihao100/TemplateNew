package com.gamerole.common.util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.gamerole.common.R;


public class EditUtil {

    public static void togglePs(EditText editText, ImageView imageView) {
        togglePs(editText, imageView, R.mipmap.common_icon_eye_open,R.mipmap.common_icon_eye_hide);
    }

    public static void togglePs(EditText editText, ImageView imageView, int resShow, int resHide) {
        imageView.setOnClickListener(v -> {
            if (editText.getTransformationMethod() instanceof PasswordTransformationMethod) {
                editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                editText.setSelection(editText.getText().length());
                imageView.setImageResource(resShow);
            } else {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editText.setSelection(editText.getText().length());
                imageView.setImageResource(resHide);
            }
        });


    }
}
