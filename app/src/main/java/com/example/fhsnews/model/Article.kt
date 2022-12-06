package com.example.fhsnews.model

import androidx.annotation.DrawableRes
import java.sql.Date

// Data for a card on the news scroller, such as weather, a piece of news, an announcement or a reminder of an upcoming activity
// (This is the data that goes into the adapter)

data class Article(
    val cardType: Int, // 0 = Article, 1 = Weather, 2 = Red/Silver card
    val articleId: Int, // Id of this article. Can be any int as long as no articles share the same ID
    @DrawableRes val articleThumbnail: Int, // (Optional with placeholder) Image for the article, will be scaled and cropped TODO: Use placeholder if blank
    val postedTime: Date, // Time of when the article was posted (Showed relative like "Posted 6 hours ago". Also determines where on the timeline this article appears, so this should be defined even if the time until is intended to be displayed instead) TODO: Relative time formatting
    val timeUntil: Date = Date(0), // (Optional) UNIX time of when the associated activity will happen (Set to 0 to skip. If specified, the timestamp will show how long until that activity happens i.e. "In 4 days" instead of "6 hours ago", as well as placing the article on the calendar at that date) TODO: Implementation
    val topperText: String, // (Optional) Activity the card is associated with, such as "Fishers Football" or "FHS Percussion"
    @DrawableRes val topperIcon: Int, // (Optional) Emblem for the topper text
    val author: String, // (Optional) Name of who wrote the article
    val tags: List<String>, // (Optional) Used for filtering TODO: Filter by tag
    val headline: String, // Headline for the article
    val subtitle: String, // (Optional) Subtitle displayed below the headline
    val text: String // Contents of the article TODO: Change to a different text type that can embed images and links
    // TODO: Attribute for color of topper
    // TODO: Attribute for additional hidden text used in searches only (such as a transcription of the article image)
)

// Arguments may be freely added (or removed if we don't end up using them)