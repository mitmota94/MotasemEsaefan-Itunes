package com.example.motasemesaefanitunes.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motasemesaefanitunes.R
import com.example.motasemesaefanitunes.adapter.MusicAdapter
import  com.example.motasemesaefanitunes.adapter.ViewPageAdapter
import  com.example.motasemesaefanitunes.Fragments.SongFragment
import  com.example.motasemesaefanitunes.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motasemesaefanitunes.ItunesResponses


class SongFragment : Fragment() {

    lateinit var rvUserList: RecyclerView
    lateinit var musicAdapter: MusicAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    companion object {

        const val MUSIC_KEY = "MUSIC_TYPE"

        fun newInstance(musicType: Int) : SongFragment  {
            val fragment = SongFragment()
            val bundle = Bundle()
            bundle.putInt(MUSIC_KEY, musicType)
            fragment.arguments = bundle
            return fragment
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("CREATING SONG FRAGMENT")
        val view = inflater.inflate(R.layout.fragment_recyclev, container, false)
        rvUserList = view.findViewById((R.id.recycle_song_list))
        layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        val musicType = arguments?.getInt(MUSIC_KEY)
       switchMusicType(musicType)
        return view
    }

    private fun switchMusicType(musicType: Int?) {
        println("SWITCHING MUSIC TYPE")
        var callSongRes: Call<ItunesResponses>? = when (musicType) {
            1-> ApiService.createRetrofit().create(ApiService::class.java).getRockSongs()
            2-> ApiService.createRetrofit().create(ApiService::class.java).getClassicSongs()
            3-> ApiService.createRetrofit().create(ApiService::class.java).getPopSongs()
            else-> throw Exception()
        }
        if (callSongRes != null) {
            startRetrofit(callSongRes)
        }
    }
    private fun startRetrofit(something: Call<ItunesResponses>) {
        something.enqueue(object : Callback<ItunesResponses> {
            override fun onResponse(
                call: Call<ItunesResponses>,
                response: Response<ItunesResponses>
            ) {
                if (response.isSuccessful) {
                    musicAdapter = MusicAdapter(response.body()!!.results)
                    rvUserList.adapter = musicAdapter;
                    rvUserList.layoutManager = layoutManager;
                }
            }

            override fun onFailure(call: Call<ItunesResponses>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}