package com.example.fhsnews.data

import com.example.fhsnews.R
import com.example.fhsnews.model.Article

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
            1665757481,
            0,
            "Goobert Scoobert",
            R.drawable.sports_ico,
            "",
            "I three stars your Mother",
            "Idote",
            "He He He Haw",
            "He He He Haw"
        ),
        Article(
            false,
            R.drawable.img_6887_106,
            1665757481,
            0,
            "Goobert Scoobert II",
            R.drawable.club_ico,
            "",
            "I three stars your Mother (Again)",
            "Again!",
            "He He He Haw (x2)",
            "He He He Haw (x2)"
        )
    )
}