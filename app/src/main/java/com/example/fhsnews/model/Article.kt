package com.example.fhsnews.model

import androidx.annotation.DrawableRes
import java.sql.Date

// Data for a card on the news scroller, such as weather, a piece of news, an announcement or a reminder of an upcoming activity
// (This is the data that goes into the adapter)

data class Article(
    val cardType: Int, // 0 = Article, 1 = Weather, 2 = Red/Silver card
    val articleId: Int,
    @DrawableRes val articleThumbnail: Int, // Image for the article, will be scaled and cropped TODO: Use placeholder if blank
    val postedTime: Date, // UNIX time of when the article was posted (Showed like "Posted 6 hours ago". Also determines where on the timeline this article appears, so this should be defined even if the time until is intended to be displayed instead) TODO: Formatting
    val timeUntil: Int = 0, // If applicable, UNIX time of when the associated activity will happen (Skipped if blank. If specified, the timestamp will show how long until that activity happens i.e. "In 4 days" instead of "6 hours ago") TODO: Implementation
    val topperText: String, // Activity the card is associated with, such as "Fishers Football" or "FHS Percussion" (Skipped if blank)
    @DrawableRes val topperIcon: Int, // Emblem for the topper activity (Skipped if topper text is blank) TODO: Skip if blank
    val author: String, // Who wrote the article (Skipped if blank)
    val tags: List<String>, // Used for filtering
    val headline: String, // Headline for the article
    val subtitle: String, // Subtitle displayed below the headline
    val text: String // Contents of the article TODO: Change to a different text type that can embed images
    // TODO: Attribute for color of topper
)

// Arguments may be freely added (or removed if we don't end up using them)