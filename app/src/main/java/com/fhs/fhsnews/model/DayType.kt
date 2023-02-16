package com.fhs.fhsnews.model

data class DayType(
    val itemType: String = "RedSilverDay",
    val dayType: String
)
/*
'Red' for red days,
'Silver' for silver days,
'Employability' for silver days with employability,
'None' for no school,
'Finals' for finals week,
etc...
*/
