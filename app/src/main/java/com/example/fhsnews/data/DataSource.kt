package com.example.fhsnews.data

import com.example.fhsnews.R
import com.example.fhsnews.model.Article
import java.sql.Date
import java.sql.Time

// https://www.unixtimestamp.com/

object DataSource {
    val newsList: List<Article> = listOf(
        /* Article(
            true,
            0,
            0,
            0,
            "Weather",
            0,
            "",
            "",
            "",
            "",
            ""
        ), */
        Article(
            false,
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
            false,
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