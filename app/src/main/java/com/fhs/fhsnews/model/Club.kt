package com.fhs.fhsnews.model

// Data for a club on the clubs scroller
// (This is the data that goes into the adapter)

data class Club(
    val clubId: Int, // Id of this club. Can be any int as long as no two clubs share the same ID
    val clubThumbnail: String, // Image for the club, will be scaled and cropped TODO: Use placeholder if blank
    val tags: List<String>, // Used for filtering, "Technology", "Sports", "Painting", etc. TODO: Filter by tag
    val clubName: String, // Name of the club
    val clubSubtitle: String, // One-line subtitle displayed below the club name
    val clubText: String // Longer list of details about the club TODO: Change to a different text type that can embed images and links
)

// Arguments may be freely added (or removed if we don't end up using them)