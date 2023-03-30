package com.fhs.fhsnews.model

enum class ErrorType { UNKNOWN, WARNING, PROBLEM }

data class Error(
    var type: ErrorType, // UNKNOWN, WARNING, or PROBLEM
    var text: String = "", // {"itemType": "Soup", "soupContents": ["Carrot... etc.
    // ↑ Only used for UNKNOWN errors! Ignored with WARNING or PROBLEM errors!
    var description: String // "Unknown object. Is your app up to date?"
)
