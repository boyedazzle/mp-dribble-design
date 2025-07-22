package com.example.moniepointdesignapplication.modal

data class SearchHistory(
    var title: String,
    var desc: String,
    var location: String,
    val imageResId: Int // Resource ID for the image
)
