package com.fhs.fhsnews.overview

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fhs.fhsnews.model.Article
import com.fhs.fhsnews.network.FHSNewsApi
import kotlinx.coroutines.launch


enum class FHSNewsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<FHSNewsApiStatus>()
    val status: LiveData<FHSNewsApiStatus> = _status

    private val _problem = MutableLiveData("")
    val problem: LiveData<String> = _problem

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _searchResults = MutableLiveData<List<Article>>()
    val searchResults: LiveData<List<Article>> = _searchResults

    init {
        getArticles()
    }

    private fun getArticles() {

        viewModelScope.launch {
            _status.value = FHSNewsApiStatus.LOADING
            try {
                _articles.value = FHSNewsApi.retrofitService.getArticles()
                Log.d(TAG, "getArticles: got articles ${_articles.value}")
                _status.value = FHSNewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FHSNewsApiStatus.ERROR
                _problem.value = e.toString()
                Log.e(TAG, "getArticles: couldn't get articles: $e")
                _articles.value = listOf()
            }
        }
    }

    fun getSearchDateResults(rangeStart: Long, rangeEnd: Long) {
        viewModelScope.launch {
            _status.value = FHSNewsApiStatus.LOADING
            _problem.value = ""
            try {
                _searchResults.value = FHSNewsApi.retrofitService.searchArticlesDate(rangeStart, rangeEnd)
                _status.value = FHSNewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FHSNewsApiStatus.ERROR
                _problem.value = e.toString()
                Log.e(TAG, "getArticles: couldn't get articles: $e")
                _searchResults.value = listOf()
            }
        }
    }

}