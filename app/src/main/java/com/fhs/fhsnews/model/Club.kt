package com.fhs.fhsnews.model

import androidx.annotation.DrawableRes

// Data for a club on the clubs scroller
// (This is the data that goes into the adapter)

data class Club(
    @DrawableRes val thumbnail: Int, // Image for the article, will be scaled and cropped TODO: Use placeholder if blank
    val tags: List<String>, // Used for filtering, "Technology", "Sports", "Painting", etc. TODO: Filter by tag
    val name: String, // Headline for the article
    val subtitle: String, // Subtitle displayed below the headline
    val text: String // Contents of the article TODO: Change to a different text type that can embed images and links
)

// Arguments may be freely added (or removed if we don't end up using them)