package com.example.module_library.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.module_library.MyApp;

public class GlideUtil {
    public static void BaseGlide(String url,ImageView imageView){
        Glide.with(MyApp.getInstance()).asBitmap().load(url).into(imageView);
    }
}
