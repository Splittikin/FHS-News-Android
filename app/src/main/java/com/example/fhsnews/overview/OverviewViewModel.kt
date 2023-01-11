package com.example.fhsnews.overview

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fhsnews.model.Article
import com.example.fhsnews.network.FHSNewsApi
import kotlinx.coroutines.launch

enum class FHSNewsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<FHSNewsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<FHSNewsApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _articles = MutableLiveData<List<Article>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val articles: LiveData<List<Article>> = _articles

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getArticles()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getArticles() {

        viewModelScope.launch {
            _status.value = FHSNewsApiStatus.LOADING
            try {
                _articles.value = FHSNewsApi.retrofitService.getArticles()
                Log.d(TAG, "getArticles: got articles ${_articles.value}")
                _status.value = FHSNewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FHSNewsApiStatus.ERROR
                Log.e(TAG, "getArticles: couldn't get articles: $e")
                _articles.value = listOf()
            }
        }
    }
}