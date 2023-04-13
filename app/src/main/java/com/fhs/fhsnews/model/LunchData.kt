package com.fhs.fhsnews.model

import java.util.*

data class LunchData(
	var time: Date,
	var entree1: String = "",
	var entree2: String = "",
	var vegetable1: String = "",
	var vegetable2: String = "",
	var grain1: String = "",
	var grain2: String = "",
	var fruit1: String = "",
	var fruit2: String = ""
)
