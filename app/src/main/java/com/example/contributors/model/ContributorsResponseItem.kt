package com.example.contributors.model

data class ContributorsResponseItem(
    val author: Author,
    val total: Int,
    val weeks: List<Week>
)