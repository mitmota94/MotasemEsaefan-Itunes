package com.example.motasemesaefanitunes.api

import  com.example.motasemesaefanitunes.ItunesResponses
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search?term=rock&amp;media=music&entity=song&limit=50")
    fun getRockSongs(
    ): Call<ItunesResponses>
    @GET("search")
    fun getClassicSongs(
        @Query("term") results: String = "rock&amp;",
        @Query("media") music: String = "music",
        @Query("entity") song: String = "song",
        @Query("limit") limit: Int = 50
    ): Call<ItunesResponses>
    @GET("search?term=classic&amp;media=music&entity=song&limit=50")
    fun getClassicSongs(
    ): Call<ItunesResponses>
    @GET("search?term=pop&amp;media=music&entity=song&limit=50")
    fun getPopSongs(
    ): Call<ItunesResponses>
    //create our instance of Retrofit
    companion object {
        var instance: Retrofit? = null
        fun createRetrofit(): Retrofit {
            if (instance == null) {
                instance =
                    Retrofit.Builder()
                        .baseUrl("https://itunes.apple.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return instance!!
        }
    }
}