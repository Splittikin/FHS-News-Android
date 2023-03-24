package com.fhs.fhsnews.network

import android.content.ContentValues.TAG
import android.util.Log
import com.fhs.fhsnews.model.*
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type
import java.util.*


private const val BASE_URL = "http://76.139.70.221:3000" // Actual server
//private const val BASE_URL = "http://192.168.8.208:3000" // Actual server on own computer
//private const val BASE_URL = "http://10.0.2.2:3000" // Android emulator

// Thingy that converts the date strings from the json files to proper java.util.Date objects
var dateDeser =
    JsonDeserializer { jSon: JsonElement?, _: Type?, _: JsonDeserializationContext? ->
        if (jSon == null) null else Date(
            jSon.asLong
        )
    }

class HomeFeedDataJsonAdapter : TypeAdapter<FeedData>() {
    override fun write(out: JsonWriter?, value: FeedData?) {
        // burh
    }

    override fun read(reader: JsonReader): FeedData {
        Log.d(TAG, "read: got feed data ${reader.beginObject()}, ${reader.nextName()}")
        when (reader.nextString()) {
            "Article" -> {
                var returnData = FeedData(
                    Article(
                        -1,
                        "",
                        Date(-1),
                        Date(-1),
                        "",
                        "",
                        "",
                        mutableListOf(),
                        "Bruh",
                        "",
                        ""
                    )
                )
                while (reader.peek() != JsonToken.END_OBJECT) {
                    var fieldName = reader.nextName()

                    when (fieldName) {
                        "articleId" -> {
                            returnData.article.articleId = reader.nextInt()
                        }
                        "articleThumbnail" -> {
                            returnData.article.articleThumbnail = reader.nextString()
                        }
                        "postedTime" -> {
                            returnData.article.postedTime =
                                Date(reader.nextString().toLong() * 1000)
                        }
                        "timeUntil" -> {
                            returnData.article.timeUntil = Date(reader.nextString().toLong() * 1000)
                        }
                        "topperText" -> {
                            returnData.article.topperText = reader.nextString()
                        }
                        "topperIcon" -> {
                            returnData.article.topperIcon = reader.nextString()
                        }
                        "author" -> {
                            returnData.article.author = reader.nextString()
                        }
                        "tags" -> {
                            reader.beginArray()
                            while (reader.peek() != JsonToken.END_ARRAY) {
                                returnData.article.tags.add(reader.nextString())
                            }
                            reader.endArray()
                        }
                        "headline" -> {
                            returnData.article.headline = reader.nextString()
                        }
                        "subtitle" -> {
                            returnData.article.subtitle = reader.nextString()
                        }
                        "text" -> {
                            returnData.article.text = reader.nextString()
                        }
                    }
                }
                reader.endObject()
                Log.d(TAG, "read: final data is $returnData")
                return returnData
            }
            "WeatherData" -> {
                var returnData = FeedData(
                    WeatherData(Date(-1), "-1", "", "Bruh")
                )
                while (reader.peek() != JsonToken.END_OBJECT) {
                    var fieldName = reader.nextName()

                    when (fieldName) {
                        "time" -> {
                            returnData.weatherData.time = Date(reader.nextString().toLong() * 1000)
                        }
                        "current_temp" -> {
                            returnData.weatherData.current_temp = reader.nextInt().toString() + "°F"
                        }
                        "weather_icon_id" -> {
                            returnData.weatherData.weather_icon_id = reader.nextString()
                        }
                        "weather_description" -> {
                            returnData.weatherData.weather_description = reader.nextString()
                        }
                    }
                }
                reader.endObject()
                Log.d(TAG, "read: final data is $returnData")
                return returnData
            }
            "Alert" -> {
                var returnData = FeedData(
                    Alert("", "", "")
                )
                while (reader.peek() != JsonToken.END_OBJECT) {
                    var fieldName = reader.nextName()

                    when (fieldName) {
                        "text" -> {
                            returnData.alert.text = reader.nextString()
                        }
                        "background_color" -> {
                            returnData.alert.background_color = reader.nextString()
                        }
                        "foreground_color" -> {
                            returnData.alert.foreground_color = reader.nextString()
                        }
                        "night_background_color" -> {
                            returnData.alert.night_background_color = reader.nextString()
                        }
                        "night_foreground_color" -> {
                            returnData.alert.night_foreground_color = reader.nextString()
                        }
                        "links" -> {
                            reader.beginObject()
                            while (reader.peek() != JsonToken.END_OBJECT) {
                                returnData.alert.links[reader.nextName()] = reader.nextString()
                            }
                            reader.endObject()
                        }
                    }
                }
                reader.endObject()
                Log.d(TAG, "read: final data is $returnData")
                return returnData
            }
            "LunchData" -> {
                var returnData = FeedData(
                    LunchData(Date(0))
                )
                while (reader.peek() != JsonToken.END_OBJECT) {
                    var fieldName = reader.nextName()

                    when (fieldName) {
                        "time" -> {
                            returnData.lunchData.time = Date(reader.nextInt().toLong() * 1000)
                        }
                        "entree1" -> {
                            returnData.lunchData.entree1 = reader.nextString()
                        }
                        "entree2" -> {
                            returnData.lunchData.entree2 = reader.nextString()
                        }
                        "vegetable1" -> {
                            returnData.lunchData.vegetable1 = reader.nextString()
                        }
                        "vegetable2" -> {
                            returnData.lunchData.vegetable2 = reader.nextString()
                        }
                        "grain1" -> {
                            returnData.lunchData.grain1 = reader.nextString()
                        }
                        "grain2" -> {
                            returnData.lunchData.grain2 = reader.nextString()
                        }
                        "fruit1" -> {
                            returnData.lunchData.fruit1 = reader.nextString()
                        }
                        "fruit2" -> {
                            returnData.lunchData.fruit2 = reader.nextString()
                        }
                    }
                }
                reader.endObject()
                Log.d(TAG, "read: final data is $returnData")
                return returnData
            }
            else -> {
                return FeedData(
                    Article(
                        -1,
                        "",
                        Date(-1),
                        Date(-1),
                        "",
                        "",
                        "",
                        mutableListOf(),
                        "Bruh Moment",
                        "",
                        "A Bruh Moment has occurred."
                    )
                )
            }
        }
    }
}

var gson: Gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, dateDeser)
    .registerTypeAdapter(FeedData::class.java, HomeFeedDataJsonAdapter())
    .create()

// Retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface FHSNewsApiService {
    // Called when opening or refreshing the home feed
    @GET("api/home")
    suspend fun getArticlesFromApi(
        /* @Query("position") position: Int,
        @Query("amount") amount: Int */
        // SOON
    ): List<FeedData>

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
    ): List<FeedData>
}

object FHSNewsApi {
    val retrofitService: FHSNewsApiService by lazy { retrofit.create(FHSNewsApiService::class.java) }
}
