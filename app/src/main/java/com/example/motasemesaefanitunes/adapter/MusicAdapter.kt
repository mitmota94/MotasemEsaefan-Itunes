package com.example.motasemesaefanitunes.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.motasemesaefanitunes.R
import androidx.recyclerview.widget.RecyclerView
import com.example.motasemesaefanitunes.ITunesSong
import com.squareup.picasso.Picasso


// create our ViewHolder
// bind the data to the ViewHolder
class MusicAdapter(private val list: List<ITunesSong>) : RecyclerView.Adapter<MusicAdapter.SongViewHolder>() {

    // binding our data, to our view
    inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun onBind(song: ITunesSong) {
            val tvSongName : TextView = itemView.findViewById(R.id.tv_song_name)
            val tvArtistName : TextView = itemView.findViewById((R.id.tv_user_name))
            val tvPriceSong : TextView = itemView.findViewById(R.id.tv_price)
            val tvArtistImage : ImageView = itemView.findViewById(R.id.iv_user_thumbnail)
            tvSongName.text = song.trackName
            tvArtistName.text = song.artistName
            if (song.trackPrice <= 0) {
                tvPriceSong.text = "Hey it's Free"
            }else{
               tvPriceSong.text = "$" + song.trackPrice.toString()
            }
            Picasso.get()
                .load(song.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit()
                .into(tvArtistImage)
            itemView.setOnClickListener {
              val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.setDataAndType(Uri.parse(song.previewUrl), "audio/*")
                itemView.context.startActivity(intent)
            }
        }
    }
    // we inflate our list item and pass it to our ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val listItem = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SongViewHolder(listItem)
    }
    // this is where we bind the data to the view
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(list[position])
    }
    // return the size of the list
    override fun getItemCount(): Int {
        return list.size
    }
}