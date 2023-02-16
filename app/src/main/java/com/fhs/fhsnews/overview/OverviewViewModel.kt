package com.fhs.fhsnews.overview

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fhs.fhsnews.model.Club
import com.fhs.fhsnews.model.FeedData
import com.fhs.fhsnews.network.FHSNewsApi
import kotlinx.coroutines.launch


enum class FHSNewsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<FHSNewsApiStatus>()
    val status: LiveData<FHSNewsApiStatus> = _status

    private val _problem = MutableLiveData("")
    val problem: LiveData<String> = _problem

    private val _articles = MutableLiveData<List<FeedData>>()
    val articles: LiveData<List<FeedData>> = _articles

    private val _searchResults = MutableLiveData<List<FeedData>>()
    val searchResults: LiveData<List<FeedData>> = _searchResults

    private val _clubs = MutableLiveData<List<Club>>()
    val clubs: LiveData<List<Club>> = _clubs

    fun getArticlesHomeFeed() {
        // Called when opening or refreshing the home feed
        viewModelScope.launch {
            _status.value = FHSNewsApiStatus.LOADING
            try {
                _articles.value = FHSNewsApi.retrofitService.getArticlesFromApi()
                Log.d(TAG, "getArticlesHomeFeed: got articles ${_articles.value}")
                _status.value = FHSNewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FHSNewsApiStatus.ERROR
                _problem.value = e.toString()
                Log.e(TAG, "getArticlesHomeFeed: couldn't get articles: $e")
                _articles.value = listOf()
            }
        }
    }

    fun getSearchDateResults(rangeStart: Long, rangeEnd: Long) {
        // Called when opening the events tab and every time you click on a day on the day picker
        viewModelScope.launch {
            _status.value = FHSNewsApiStatus.LOADING
            _problem.value = ""
            try {
                _searchResults.value =
                    FHSNewsApi.retrofitService.searchArticlesDate(rangeStart, rangeEnd)
                _status.value = FHSNewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FHSNewsApiStatus.ERROR
                _problem.value = e.toString()
                Log.e(TAG, "getSearchDateResults: couldn't get articles: $e")
                _searchResults.value = listOf()
            }
        }
    }

    fun getClubsClubFeed() {
        // Called when opening or refreshing the clubs feed
        viewModelScope.launch {
            _status.value = FHSNewsApiStatus.LOADING
            try {
                _clubs.value = FHSNewsApi.retrofitService.getClubs()
                Log.d(TAG, "getClubsClubFeed: got clubs ${_clubs.value}")
                _status.value = FHSNewsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = FHSNewsApiStatus.ERROR
                _problem.value = e.toString()
                Log.e(TAG, "getClubsClubFeed: couldn't get clubs: $e")
                _clubs.value = listOf()
            }
        }
    }

}