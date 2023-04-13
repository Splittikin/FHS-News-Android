package com.fhs.fhsnews.model

data class Alert(
	var text: String,
	var background_color: String,
	var foreground_color: String,
	var night_background_color: String = background_color, // If necessary, different colors for night theme
	var night_foreground_color: String = foreground_color,
	var links: MutableMap<String, String> = mutableMapOf()
) {
	constructor(inText: String, inBackgroundColor: String, inForegroundColor: String, inLinks: MutableMap<String, String>) : this(
		text = inText,
		background_color = inBackgroundColor,
		foreground_color = inForegroundColor,
		links = inLinks
	)
}
/* Buttons list is formatted with the button name, then the URL. Like so:

links: {
    "Clever": "https://clever.com/in/hse/student/portal"
    "Enriching Students": "https://student.enrichingstudents.com/dashboard"
    "Potato Website": "https://po.ta.to/"
}

*/