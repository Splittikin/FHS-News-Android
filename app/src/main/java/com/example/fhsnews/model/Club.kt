package com.example.fhsnews.model

import androidx.annotation.DrawableRes
import java.sql.Date

// Data for a club on the clubs scroller
// (This is the data that goes into the adapter)

data class Club(
    @DrawableRes val thumbnail: Int, // Image for the article, will be scaled and cropped TODO: Use placeholder if blank
    val postedTime: Date, // UNIX time of when the article was posted (Showed like "Posted 6 hours ago". Also determines where on the timeline this article appears, so this should be defined even if the time until is intended to be displayed instead) TODO: Formatting
    val tags: List<String>, // Used for filtering
    val name: String, // Headline for the article
    val subtitle: String, // Subtitle displayed below the headline
    val text: String // Contents of the article TODO: Change to a different text type that can embed images
)

// Arguments may be freely added (or removed if we don't end up using them)