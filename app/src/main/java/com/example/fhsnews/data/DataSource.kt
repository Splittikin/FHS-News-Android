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
            0, 1, R.drawable.gift_wrappinh, Date(1671469200000), 0, "Fundraiser", R.drawable.events_ico, "", listOf(), "Holiday Gift Wrapping", "For the Leukemia and Lymphoma Society", "On December 19th at door 19, remember to drop off 5-10 gifts to be wrapped for a donation via cash, check, or CashApp. You may bring your own wrapping paper if it is specific to your kid. All proceeds go to the Leukemia and Lymphoma Society"
        ), Article(
            0, 2, R.drawable.winter_band_digital_program, Date(1670542200000), 0, "Band", R.drawable.music_ico, "", listOf(), "Sounds of the Season Concert", "Intermediate Jazz Ensemble, Advanced Jazz Ensemble, Concert Band, Symphonic Band Red, Symphonic Band Gold, Wind Symphony, Wind Ensemble", "Enjoy the Sounds of the Season as the FHS Bands play selections to get you into the holiday spirit. The Intermediate Jazz Ensemble will play in the Auditorium Lobby beginning at 6:30 p.m. as we begin seating. The concert will begin in the Auditorium at 7 p.m. and the Concert, Symphonic Band Red, Symphonic Band Gold, Wind Symphony, Wind Ensemble and Advanced Jazz Ensemble bands will perform."
        ), /*Article(
            0,3, R.drawable.orchestra_holiday_digital_monitor, Date(1670371200000), 0, "Orchestra", R.drawable.music_ico, "", listOf(), "Orchestra Holiday Concert"
        )*/
    )
    val clubsList: List<Club> = listOf(
        Club(
            R.drawable.img_ocean,
            Date(0),
            listOf(),
            "Computer Science Club",
            "Sponsored by Mr Guler, Room C209",
            "Computer Science Club is a forum for students interested in computer programming, hardware, software, or other CS topics to connect with students who have similar interests and learn from each other. "
        )
    )
}