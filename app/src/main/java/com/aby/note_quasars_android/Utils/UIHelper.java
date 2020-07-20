package com.aby.note_quasars_android.Utils;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UIHelper {

    public static EditText getPreparedEditText(Context context){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        EditText editText = new EditText(context);
        editText.setLayoutParams(layoutParams);
        editText.setBackground(null);
        return editText;
    }

    public static void addImageViewAt(int position, Uri photoURI, LinearLayout containerLinearLayout, Context context){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageURI(photoURI);
        imageView.setTag(photoURI);
        containerLinearLayout.addView(imageView,position);



        // add a following editText below new imageView
        EditText editText = getPreparedEditText(context);
        containerLinearLayout.addView(editText,position+1);

    }


}
