package com.example.saveomovies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.saveomovies.R

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView).load("$IMAGE_BASE_URL$url")
        .placeholder(imageView.context.getDrawable(R.drawable.gray_background)).into(imageView)
}