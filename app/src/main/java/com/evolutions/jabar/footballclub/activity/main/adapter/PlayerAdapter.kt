package com.evolutions.jabar.footballclub.activity.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.model.Players
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_player.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerAdapter(private val context: Context, private val players: List<Players>, private val listener:(Players)->Unit): RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.list_player,parent,false))
    }

    override fun onBindViewHolder(holder:PlayerViewHolder, position: Int) {
        holder.bindPlayer(context,players[position],listener)

    }

    override fun getItemCount(): Int = players.size

    class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view),LayoutContainer{
        @SuppressLint("SetTextI18n")
        fun bindPlayer(context: Context, players: Players, listener:(Players)->Unit){

            Glide.with(context).load(players.playerImage).apply(RequestOptions().circleCrop().override(300,300)).into(itemView.img_player)

            itemView.name_player.text = "Nama : ${players.playerName}"
            itemView.position_player.text = "Posisi : ${players.playerPosition}"
            itemView.weight_player.text = "Berat badan : ${players.playerWeight}"
            itemView.height_player.text = "Tinggi badan : ${players.playerHeight}"
            Picasso.get().load(players.playerImageDetails).into(itemView.fanart)

            itemView.deskripsi.text = players.playerDescription
            itemView.onClick{
                listener(players)

            }
        }
    }
    interface LayoutContainer{

    }
}