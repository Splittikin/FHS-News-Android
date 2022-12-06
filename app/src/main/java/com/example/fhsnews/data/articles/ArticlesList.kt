package com.example.fhsnews.data.articles

import com.example.fhsnews.R
import com.example.fhsnews.model.Article
import java.sql.Date

// https://www.unixtimestamp.com/ Multiply the number you get here by 1000
// 0 = Article
// 1 = Weather Card - Only displayed once, at very top
// 2 = Red/Silver Card - Only displayed once, just below weather

// ArticleId is used to tell the OpenArticleFragment which article to open when the article gets clicked
// ArticleId can be any number as long as no two articles share the same number

/*
Article(
    /*         Card type */ 0, // Leave as 0 always
    /*        Article ID */ 1, // (Change to any int that isn't in use by another article)
    /* Article thumbnail */ R.drawable.img_ocean,
    /*       Time posted */ Date(1671469200000),
    /*  Time until event */ Date(0), // (Optional - Set to Date(0) to skip)
    /*       Topper text */"Fundraiser", // (Optional - Set to "" to skip) ("FHS Percussion", "Computer Science Club", etc.)
    /*       Topper icon */ R.drawable.events_ico, // (Optional - Set to 0 to skip)
    /*    Article author */ "Jeff Jimothy Jones", // (Optional - Set to "" to skip)
    /*      Article tags */ listOf("fundraiser", "holiday", "december"), // (Optional - Set to listOf() to skip)
    /*          Headline */ "Holiday Gift Wrapping",
    /*   1-line subtitle */ "For the Leukemia and Lymphoma Society", // (Optional - Set to "" to skip)
    /*   Article content */ "On December 19th at door 19,\nremember to drop off 5-10 gifts to be wrapped for a donation via cash, check, or CashApp. You may bring your own wrapping paper if it is specific to your kid. All proceeds go to the Leukemia and Lymphoma Society"
),
*/

object ArticlesList {
    val newsList: List<Article> = listOf(
        Article(
            0,
            1,
            R.drawable.gift_wrappinh,
            Date(1671469200000),
            Date(0),
            "Fundraiser",
            R.drawable.events_ico,
            "",
            listOf(),
            "Holiday Gift Wrapping",
            "For the Leukemia and Lymphoma Society",
            "On December 19th at door 19, remember to drop off 5-10 gifts to be wrapped for a donation via cash, check, or CashApp. You may bring your own wrapping paper if it is specific to your kid. All proceeds go to the Leukemia and Lymphoma Society"
        ),
        Article(
            0,
            2,
            R.drawable.winter_band_digital_program,
            Date(1670542200000),
            Date(0),
            "Band",
            R.drawable.music_ico,
            "",
            listOf(),
            "Sounds of the Season Concert",
            "Intermediate Jazz Ensemble, Advanced Jazz Ensemble, Concert Band, Symphonic Band Red, Symphonic Band Gold, Wind Symphony, Wind Ensemble",
            "Enjoy the Sounds of the Season as the FHS Bands play selections to get you into the holiday spirit. The Intermediate Jazz Ensemble will play in the Auditorium Lobby beginning at 6:30 p.m. as we begin seating. The concert will begin in the Auditorium at 7 p.m. and the Concert, Symphonic Band Red, Symphonic Band Gold, Wind Symphony, Wind Ensemble and Advanced Jazz Ensemble bands will perform."
        ), /*Article(
            0,3, R.drawable.orchestra_holiday_digital_monitor, Date(1670371200000), 0, "Orchestra", R.drawable.music_ico, "", listOf(), "Orchestra Holiday Concert"
        )*/
    )
}