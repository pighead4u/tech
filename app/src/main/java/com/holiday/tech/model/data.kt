package com.holiday.tech.model

data class HomeVO(
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String,
    val images: List<String> = emptyList()
)