package com.example.fhsnews.data

import com.example.fhsnews.R
import com.example.fhsnews.model.Article
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
            0,
            R.drawable.img_6887_106,
            Date(1665757481000),
            0,
            "Doing Your Mom Class",
            R.drawable.sports_ico,
            "Goobert Scoobert",
            listOf(),
            "I three stars your Mother",
            "Idote",
            "He He He Haw"
        ), Article(
            0,
            R.drawable.img_6887_106,
            Date(1665757481000),
            0,
            "Doing Your Mom Class",
            R.drawable.club_ico,
            "Goobert Scoobert II",
            listOf(),
            "I three stars your Mother (Again)",
            "Again!",
            "I’ve come to make an announcement: Shadow the Hedgehog’s a bitch ass mother fucker. He pissed on my fucking wife. That’s right, he took his hedgehog fuckin' quilly dick out and he pissed on my fucking wife, and he said his dick was THIS BIG. And I said “that’s disgusting!” So I’m making a callout post on my twitter dot com: \"Shadow the Hedgehog, you got a small dick, it’s the size of this walnut except WAY smaller.\" And guess what, here’s what my dong looks like: PFFFFFFFFGJT. That’s right baby. All point, no quills, no pillows, look at that it looks like two balls and a bong. He fucked my wife so guess what, I’m gonna FUCK THE EARTH. THATS RIGHT THIS IS WHAT YOU GET, MY SUPER LAZER PISS. Except I’m not gonna piss on the earth, I’m gonna go higher. I’m pissing on the MOOOOOON! How do you like that, OBAMA? I PISSED ON THE MOON, YOU IDIOT! You have twenty-three hours before the piss d r o p l e t s hit the fucking earth, now get out of my fucking sight before I piss on you too!\n"
        )
    )
}