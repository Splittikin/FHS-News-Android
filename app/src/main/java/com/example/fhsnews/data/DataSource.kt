package com.example.fhsnews.data

import com.example.fhsnews.R
import com.example.fhsnews.model.Article
import com.example.fhsnews.model.Club
import java.sql.Date

// https://www.unixtimestamp.com/ Multiply the number you get here by 1000
// 0 = Article
// 1 = Weather Card - Only displayed once, at very top
// 2 = Red/Silver Card - Only displayed once, just below weather

// ArticleId is used to tell the OpenArticleFragment which article to open when the article gets clicked
// ArticleId can be any number as long as no two articles share the same number

object DataSource {
    val extrasList: List<Article> = listOf(
        // Contains the weather and red/silver card
        // This list is merged at the front of the news list in the NewsCard adapter, but they are
        //  kept separate for the EventsViewAdapter
        // Only the cardType field matters, no other fields here are used
        Article(
            1, 0, 0, Date(0), 0, "Weather", 0, "", listOf(), "", "", ""
        ), Article(
            2, 0, 0, Date(0), 0, "Red/Silver Indicator", 0, "", listOf(), "", "", ""
        )
    )
    val newsList: List<Article> = listOf(
        Article(
            0, 1, R.drawable.gift_wrappinh, Date(42069), 0, "Weather", 0, "", listOf(), "", "", ""
        ), Article(
            0, 2, R.drawable., Date(42069), 0, "Red/Silver Indicator", 0, "", listOf(), "", "", ""
        )
    )
    val clubsList: List<Club> = listOf(
        Club(
            R.drawable.img_ocean,
            Date(1665757481000),
            listOf(),
            "The piano club",
            "We play piano",
            "Come to our call-out meeting! Aux gym on the 31st!"
        )
    )
}