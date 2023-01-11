package com.example.fhsnews.network

import com.example.fhsnews.model.Article
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.reflect.Type
import java.util.*


private const val BASE_URL = "http://76.139.70.221:3000"

var deser =
    JsonDeserializer { jSon: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext? ->
        if (jSon == null) null else Date(
            jSon.asLong
        )
    }

var gson = GsonBuilder()
    .registerTypeAdapter(Date::class.java, deser).create()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface FHSNewsApiService {
    @GET("ArticlesList.json")
    suspend fun getArticles(): List<Article>
}

object FHSNewsApi {
    val retrofitService: FHSNewsApiService by lazy { retrofit.create(FHSNewsApiService::class.java) }
}
