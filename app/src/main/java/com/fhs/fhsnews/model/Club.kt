package com.fhs.fhsnews.model

// Data for a club on the clubs scroller
// (This is the data that goes into the adapter)

data class Club(
	var clubId: Int, // Id of this club. Can be any int as long as no two clubs share the same ID
	var clubThumbnail: String, // Image for the club, will be scaled and cropped TODO: Use placeholder if blank
	var tags: List<String>, // Used for filtering, "Technology", "Sports", "Painting", etc. TODO: Filter by tag
	var clubName: String, // Name of the club
	var clubSubtitle: String, // One-line subtitle displayed below the club name
	var clubText: String // Longer list of details about the club TODO: Change to a different text errorType that can embed images and links
)

// Arguments may be freely added (or removed if we don't end up using them)