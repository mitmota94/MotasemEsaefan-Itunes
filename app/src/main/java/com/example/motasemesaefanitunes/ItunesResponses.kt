package com.example.motasemesaefanitunes

data class ItunesResponses(
    val results: List<ITunesSong>
)
data class ITunesSong(
    val artistName: String,
    val trackName: String,
    val trackPrice: Double,
    val artworkUrl60: String,
    val previewUrl: String
)

