package com.fhs.fhsnews.model

import java.util.*

// Data class that is all of the other data classes combined
// Used in ListAdapters that need more than one type, like the home feed

data class FeedData(
    val itemType: String,
    var article: Article = Article(
        -1,
        "",
        Date(-1),
        Date(-1),
        "",
        "",
        /* "",
        "", */ // Topper color & text color, soon
        "",
        mutableListOf(),
        "",
        "",
        ""
    ),
    var club: Club = Club(
        -1,
        "",
        listOf(),
        "",
        "",
        ""
    ),
    var weatherData: WeatherData = WeatherData(Date(-1), "-1", "", ""),
    var alert: Alert = Alert("", "", "")
) {
    constructor(inArticle: Article) : this(itemType = "Article", article = inArticle)
    constructor(inClub: Club) : this(itemType = "Club", club = inClub)
    constructor(inWeatherData: WeatherData) : this(
        itemType = "WeatherData",
        weatherData = inWeatherData
    )

    constructor(inAlert: Alert) : this(
        itemType = "Alert",
        alert = inAlert
    )
}


