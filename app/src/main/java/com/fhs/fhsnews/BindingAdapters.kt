package com.fhs.fhsnews

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.fhs.fhsnews.adapter.ClubDataAdapter
import com.fhs.fhsnews.adapter.NewsDataAdapter
import com.fhs.fhsnews.model.Club
import com.fhs.fhsnews.model.FeedData
import com.fhs.fhsnews.overview.FHSNewsApiStatus

// All of these List Data adapters do the same thing but with different types of objects and different card views
@BindingAdapter("articleListData")
fun bindArticlesRecyclerView(recyclerView: RecyclerView, data: List<FeedData>?) {
    Log.d(TAG, "bindRecyclerView: updating list data with $data")
    val adapter = recyclerView.adapter as NewsDataAdapter?
    adapter?.submitList(data)
}

@BindingAdapter("eventsListData")
fun bindEventsRecyclerView(recyclerView: RecyclerView, data: List<FeedData>?) {
    Log.d(TAG, "bindRecyclerView: updating list data with $data")
    val adapter = recyclerView.adapter as NewsDataAdapter?
    adapter?.submitList(data)
}

@BindingAdapter("clubsListData")
fun bindClubsRecyclerView(recyclerView: RecyclerView, data: List<Club>?) {
    Log.d(TAG, "bindRecyclerView: updating list data with $data")
    val adapter = recyclerView.adapter as ClubDataAdapter?
    adapter?.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.d(TAG, "bindImage: loading image at $imgUrl")
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_baseline_download_24)
            error(R.drawable.ic_baseline_broken_image_24)
            scale(Scale.FILL)
        }
    }
}

@BindingAdapter("weatherImage")
fun bindWeatherImage(imgView: ImageView, imgCode: String?) {
    imgCode?.let {
        val imgUri = "https://www.weatherbit.io/static/img/icons/$imgCode.png".toUri().buildUpon()
            .scheme("https").build()

        Log.d(TAG, "bindImage: loading weather image with code $imgCode from $imgUri")
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_baseline_download_24)
            error(R.drawable.ic_baseline_broken_image_24)
            scale(Scale.FILL)
        }
    }
}

@BindingAdapter("FHSNewsApiStatus")
fun bindStatus(statusImageView: ImageView, status: FHSNewsApiStatus?) {
    when (status) {
        FHSNewsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_download_24)
        }
        FHSNewsApiStatus.ERROR -> {
            statusImageView.visibility = View.GONE
        }
        FHSNewsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("FHSNewsApiStatus")
fun bindProblem(problemTextView: TextView, problem: String) {
    if (problem != "") {
        problemTextView.visibility = View.VISIBLE
        problemTextView.text = problem
    }
}