package com.example.fhsnews.data

import com.example.fhsnews.R
import com.example.fhsnews.model.Article
import java.sql.Date
import java.sql.Time

// https://www.unixtimestamp.com/
// 0 = Article
// 1 = Weather Card - Only displayed once, at very top
// 2 = Red/Silver Card - Only displayed once, just below weather

object DataSource {
    val newsList: List<Article> = listOf(
        Article(
            1,
            0,
            Date(0),
            0,
            "Weather",
            0,
            "",
            "",
            "",
            "",
            ""
        ),
        Article(
            0,
            R.drawable.img_6887_106,
            Date(1665757481),
            0,
            "Doing Your Mom Class",
            R.drawable.sports_ico,
            "Goobert Scoobert",
            "I three stars your Mother",
            "Idote",
            "He He He Haw",
            "He He He Haw"
        ),
        Article(
            0,
            R.drawable.img_6887_106,
            Date(1665757481),
            0,
            "Doing Your Mom Class",
            R.drawable.club_ico,
            "Goobert Scoobert II",
            "I three stars your Mother (Again)",
            "Again!",
            "He He He Haw (x2)",
            "He He He Haw (x2)"
        )
    )
}