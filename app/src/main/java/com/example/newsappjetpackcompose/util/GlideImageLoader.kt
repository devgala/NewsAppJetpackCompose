package com.example.newsappjetpackcompose.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


@Composable
fun LoadImageByURL(
    url : String,
    @DrawableRes defaultImg: Int
) : MutableState<Bitmap?>{
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImg)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object: CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
               bitmap.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })

    return bitmap
}