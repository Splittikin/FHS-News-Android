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
    val extrasList: List<Article> = listOf(
        // Contains the weather and red/silver card
        // This list is merged at the front of the news list in the NewsCard adapter, but they are
        //  kept separate for the EventsViewAdapter
        Article(
            1, 0,0, Date(0), 0, "Weather", 0, "", listOf(), "", "", ""
        ), Article(
            2, 0, 0, Date(0), 0, "Red/Silver Indicator", 0, "", listOf(), "", "", ""
        )
    )
    val newsList: List<Article> = listOf(
        Article(
            //first article
            0,
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
            "Fishers wins against HSE!",
            "The fishers tigers beat the HSE royals 13-7 in the latest game.",
            "The High school’s rule book outlaws celebrations that are “excessive,” which is a subjective measure. To truly gauge whether a celebration is excessive, you have to determine an appropriate amount of celebration for a certain accomplishment. For a first down when trailing by seven in the first quarter? Anything more than a smile is excessive. But let’s say a player hypothetically made a spectacular Hail Mary touchdown catch at the very back of the end zone—the deepest catch ever recorded—to win a game, putting his team in first place in their division, just weeks after they’d fired their coach. It’s hard to imagine any celebration that would be excessive. Any amount of screaming, gesturing, alcohol consumption, pyrotechnic displays, musical performances, and a certain amount of tasteful stripping would be tolerated. Unfortunately, this was not a hypothetical scenario. In Sunday’s Panthers-Falcons game, with possession of first place in the NFC South on the line and the Panthers trailing by six points, Carolina QB P.J. Walker threw a stunning 62-yard touchdown pass to D.J. Moore."
        ), Article(
            0,
            1,
            R.drawable.img_ocean,
            Date(1665757481000),
            0,
            "Chess Club",
            R.drawable.club_ico,
            "Goobert Scoobert II",
            listOf(),
            "Chess club meetings are on wednesdays!",
            "The chess club meetings are on wednesdays, we hope to see you there!",
            "The chess club allows any person of any skill level and grade. we are a welcoming community, willing to teach anyone!"
        ), Article(
            0,
            2,
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
        ), Article(
            0,
            3,
            R.drawable.img_ocean,
            Date(1665757481000),
            0,
            "Girl's Volleyball",
            R.drawable.sports_ico,
            "Goobert Scoobert IV",
            listOf(),
            "They win",
            "Yaaaaaayyyyy",
            "they are awesome"
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