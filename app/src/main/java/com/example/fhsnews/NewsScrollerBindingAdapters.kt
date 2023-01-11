package com.example.fhsnews

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fhsnews.adapter.NewsCardAdapter
import com.example.fhsnews.model.Article

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Article>?) {
    Log.d(TAG, "bindRecyclerView: updating list data with $data")
    val adapter = recyclerView.adapter as NewsCardAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.d(TAG, "bindImage: loading image at $imgUrl")
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_baseline_download_24)
            error(R.drawable.ic_baseline_broken_image_24)
        }
    }
}