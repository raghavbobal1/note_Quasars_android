package com.aby.note_quasars_android.Utils;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

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


    public static void addImageViewAt(int position, Uri photoURI, LinearLayout containerLinearLayout, Context context,boolean isEditTextEnabled ){

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageURI(photoURI);
        imageView.setTag(photoURI);
        containerLinearLayout.addView(imageView,position);




        // add a following editText below new imageView
        if(!isEditTextEnabled){
            return;
        }
        EditText editText = getPreparedEditText(context);

        containerLinearLayout.addView(editText,position+1);

    }

    public static ArrayList<String> getAllViewOrder(LinearLayout containerLinearLayout){
        ArrayList<String> viewOrder = new ArrayList<>();
        for (int i = 0; i < containerLinearLayout.getChildCount(); i++) {
            View v = containerLinearLayout.getChildAt(i);
            if (v instanceof EditText) {
                viewOrder.add("editText");
            } else if (v instanceof ImageView) {
                viewOrder.add("imageView");
            }
            else{
                viewOrder.add("soundView");
            }
        }
        return  viewOrder;
    }

    public static ArrayList<String> getAllImageURIs(LinearLayout containerLinearLayout){
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < containerLinearLayout.getChildCount(); i++) {
            View v = containerLinearLayout.getChildAt(i);
            if (v instanceof ImageView) {
                images.add(v.getTag().toString());
            }
        }
        return  images;
    }

    public static ArrayList<String> getAllSoundURIs(LinearLayout containerLinearLayout){
        ArrayList<String> sounds = new ArrayList<>();
//        for (int i = 0; i < containerLinearLayout.getChildCount(); i++) {
//            View v = containerLinearLayout.getChildAt(i);
//            if (v instanceof ImageView) {
//                images.add(v.getTag().toString());
//            }
//        }
        return  sounds;
    }


    public static ArrayList<String> getAllTexts(LinearLayout containerLinearLayout){
        ArrayList<String> texts = new ArrayList<>();
        for (int i = 0; i < containerLinearLayout.getChildCount(); i++) {
            View v = containerLinearLayout.getChildAt(i);
            if (v instanceof EditText) {
                texts.add(((EditText) v).getText().toString());
            } else {
                // nothing
            }
        }
        return  texts;
    }
}
