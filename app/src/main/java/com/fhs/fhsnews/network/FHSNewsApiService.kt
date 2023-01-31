package com.fhs.fhsnews.network

import com.fhs.fhsnews.model.Article
import com.fhs.fhsnews.model.Club
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type
import java.util.*


private const val BASE_URL = "http://76.139.70.221:3000" // Actual server
//private const val BASE_URL = "http://10.0.2.2:3000" // Android emulator

// Thingy that converts the date strings fron the json files to proper java.util.Date objects
var deser =
    JsonDeserializer { jSon: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext? ->
        if (jSon == null) null else Date(
            jSon.asLong
        )
    }
var gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, deser).create()

// Retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface FHSNewsApiService {
    // Called when opening or refreshing the home feed
    @GET("api/home")
    suspend fun getArticlesFromApi(): List<Article>

    // Called when opening or refreshing the clubs feed
    @GET("api/feedClubs")
    suspend fun getClubs(): List<Club>

    // Called when clicking on an article club
    @GET("api/article/{id}")
    suspend fun getArticle(@Path("id") articleId: Int): Article

    // Called when clicking on a club card
    @GET("api/club/{id}")
    suspend fun getClub(@Path("id") clubId: Int): Club

    // Called when selecting a date in the Events tab
    @GET("api/search_date")
    suspend fun searchArticlesDate(
        @Query("range_start") rangeStart: Long,
        @Query("range_end") rangeEnd: Long
    ): List<Article>
}

object FHSNewsApi {
    val retrofitService: FHSNewsApiService by lazy { retrofit.create(FHSNewsApiService::class.java) }
}
