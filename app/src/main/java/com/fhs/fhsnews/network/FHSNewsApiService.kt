package com.fhs.fhsnews.network

import android.content.ContentValues.TAG
import android.util.Log
import com.fhs.fhsnews.model.*
import com.google.gson.*
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

var dateDeser = JsonDeserializer { jSon: JsonElement?, _: Type?, _: JsonDeserializationContext? ->
	if (jSon == null) null else Date(
		jSon.asLong
	)
}

var feedDataDeser = JsonDeserializer { json, _, _ ->
	val jsonObject = json.asJsonObject
	Log.d(TAG, "feedDataDeser: ${jsonObject.get("itemType")}")
	when (jsonObject.get("itemType").asString) {
		"Article" -> {
			var tagsList: MutableList<String> = mutableListOf()
			for (tag in jsonObject.get("tags").asJsonArray) {
				tagsList.add(tag.asString)
			}
			return@JsonDeserializer FeedData(
				Article(
					jsonObject.get("articleId").asInt,
					jsonObject.get("articleThumbnail").asString,
					Date(jsonObject.get("postedTime").asLong * 1000),
					Date(0),
					jsonObject.get("topperText").asString,
					jsonObject.get("topperIcon").asString,
					jsonObject.get("author").asString,
					tagsList,
					jsonObject.get("headline").asString,
					jsonObject.get("subtitle").asString,
					jsonObject.get("text").asString
				)
			)
		}
		"Club" -> {
			var tagsList: MutableList<String> = mutableListOf()
			for (tag in jsonObject.get("tags").asJsonArray) {
				tagsList.add(tag.asString)
			}
			return@JsonDeserializer FeedData(
				Club(
					jsonObject.get("clubId").asInt,
					jsonObject.get("clubThumbnail").asString,
					tagsList,
					jsonObject.get("clubName").asString,
					jsonObject.get("clubSubtitle").asString,
					jsonObject.get("clubText").asString
				)
			)

		}
		"WeatherData" -> {
			return@JsonDeserializer FeedData(
				WeatherData(
					Date(jsonObject.get("time").asLong * 1000),
					jsonObject.get("current_temp").asString,
					jsonObject.get("weather_icon_id").asString,
					jsonObject.get("weather_description").asString
				)
			)
		}
		"Alert" -> {
			var linksList: MutableMap<String, String> = mutableMapOf()
			if (jsonObject.has("links")) { // TODO: Make this check if the links list has entries in addition to checking if it exists
				var linksJson = jsonObject.get("links").asJsonObject
				Log.d(TAG, "feedDataDeser: ${linksJson.entrySet()}")
				for (item in linksJson.entrySet()) {
					linksList[item.key] = item.value.asString
				}
			}
			return@JsonDeserializer FeedData(
				Alert(
					jsonObject.get("text").asString,
					jsonObject.get("background_color").asString,
					jsonObject.get("foreground_color").asString,
					linksList
				)
			)
		}
		"LunchData" -> {
			return@JsonDeserializer FeedData(
				LunchData(
					Date(jsonObject.get("time").asLong * 1000),
					jsonObject.get("entree1").asString,
					jsonObject.get("entree2").asString,
					jsonObject.get("vegetable1").asString,
					jsonObject.get("vegetable2").asString,
					jsonObject.get("grain1").asString,
					jsonObject.get("grain2").asString,
					jsonObject.get("fruit1").asString,
					jsonObject.get("fruit2").asString
				)
			)
		}
		"ServerError" -> {
			return@JsonDeserializer FeedData(
				Error(
					ErrorType.PROBLEM,
					"",
					jsonObject.get("description").asString
				)
			)
		}
		"ClientError" -> {
			return@JsonDeserializer FeedData(
				Error(
					ErrorType.WARNING,
					"",
					jsonObject.get("description").asString
				)
			)
		}
		else -> {
			return@JsonDeserializer FeedData(
				Error(
					ErrorType.UNKNOWN,
					jsonObject.asJsonObject.toString(),
					"Couldn't identify this item! Is your FHS News version up to date?" // TODO: Unhardcode this
				)
			)
		}
	}
}

var gson: Gson = GsonBuilder().registerTypeAdapter(FeedData::class.java, feedDataDeser).registerTypeAdapter(Date::class.java, dateDeser)
	.create()

// Retrofit
private val retrofit =
	Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).baseUrl(BASE_URL)
		.build()

interface FHSNewsApiService {
	// Called when opening or refreshing the home feed
	@GET("api/home")
	suspend fun getArticlesFromApi(
		@Query("position") position: Int = 0,
		@Query("quantity") quantity: Int = 10
	): List<FeedData>

	// Called when opening or refreshing the clubs feed
	@GET("api/feedClubs")
	suspend fun getClubs(): List<FeedData>

	// Called when clicking on an article club
	@GET("api/article/{id}")
	suspend fun getArticle(@Path("id") articleId: Int): Article

	// Called when clicking on a club card
	@GET("api/club/{id}")
	suspend fun getClub(@Path("id") clubId: Int): Club

	// Called when selecting a date in the Events tab
	@GET("api/search_date")
	suspend fun searchArticlesDate(
		@Query("range_start") rangeStart: Long, @Query("range_end") rangeEnd: Long
	): List<FeedData>
}

object FHSNewsApi {
	val retrofitService: FHSNewsApiService by lazy { retrofit.create(FHSNewsApiService::class.java) }
}
