package com.example.fhsnews.data

import com.example.fhsnews.R
import com.example.fhsnews.model.Article
import com.example.fhsnews.model.Club
import java.sql.Date

// https://www.unixtimestamp.com/
// 0 = Article
// 1 = Weather Card - Only displayed once, at very top
// 2 = Red/Silver Card - Only displayed once, just below weather

object DataSource {
    val newsList: List<Article> = listOf(
        Article(
            1, 0, Date(0), 0, "Weather", 0, "", listOf(), "", "", ""
        ), Article(
            2, 0, Date(0), 0, "Red/Silver Indicator", 0, "", listOf(), "", "", ""
        ), Article(
            //first article
            0,
            //picture displayed
            R.drawable.img_ocean,
            Date(1665757481000),
            //for announcements, not implemented yet
            0,
            //topper, topic of club
            "Fishers Boy's Varsity Football",
            //icon
            R.drawable.sports_ico,
            "Goobert Scoobert",
            listOf(),
            "Lorel Ispum",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ), Article(
            0,
            R.drawable.img_ocean,
            Date(1665757481000),
            0,
            "Chess Club",
            R.drawable.club_ico,
            "Goobert Scoobert II",
            listOf(),
            "Lorel Ispum",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "Lorel ispum dolor sit amet\n"
        ), Article(
            0,
            R.drawable.img_ocean,
            Date(1665757481000),
            0,
            "Computer Science Club",
            R.drawable.club_ico,
            "Goobert Scoobert III",
            listOf(),
            "Webdev makes new website",
            "The Webdevs did the thing",
            "they made a website"
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