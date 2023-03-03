package com.fhs.fhsnews.model

import java.util.*

// Data for a card on the news scroller, such as weather, a piece of news, an announcement or a reminder of an upcoming activity
// (This is the data that goes into the adapter)

data class Article(
    var articleId: Int, // Id of this article. Can be any int as long as no two articles share the same ID
    var articleThumbnail: String, // (Optional with placeholder) Image for the article, will be scaled and cropped TODO: Use placeholder if blank
    var postedTime: Date, // Time of when the article was posted (Showed relative like "Posted 6 hours ago". Also determines where on the timeline this article appears, so this should be defined even if the time until is intended to be displayed instead) TODO: Relative time formatting
    var timeUntil: Date = Date(0), // (Optional) UNIX time of when the associated activity will happen (Set to 0 to skip. If specified, the timestamp will show how long until that activity happens i.e. "In 4 days" instead of "6 hours ago", as well as placing the article on the calendar at that date) TODO: Implementation
    var topperText: String, // (Optional) Activity the card is associated with, such as "Fishers Football" or "FHS Percussion"
    var topperIcon: String, // (Optional) Emblem for the topper text
    /* var topperColor: String,
    var topperTextColor: String, */ // SOON
    var author: String, // (Optional) Name of who wrote the article
    var tags: MutableList<String>, // (Optional) Used for filtering TODO: Filter by tag
    var headline: String, // Headline for the article
    var subtitle: String, // (Optional) One-line subtitle displayed below the headline
    var text: String // Contents of the article TODO: Change to a different text type that can embed images and links
    // TODO: Attribute for color of topper
    // TODO: Attribute for additional hidden text used in searches only (such as a transcription of the article image)
)

// Arguments may be freely added (or removed if we don't end up using them)