package com.evolutions.jabar.footballclub.activity.detail.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.activity.main.PlayerActivity
import com.evolutions.jabar.footballclub.model.Players
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val player = intent.getParcelableExtra<Players>(PlayerActivity.ITEM_DATA)
        setContentView(R.layout.activity_detail_player)
        Glide.with(this).load(player.playerImageDetails).apply(RequestOptions().override(500,500)).into(img_fanart)
        supportActionBar?.title = "Detail Player"
        weight_player1.text= player.playerWeight
        height_player1.text = player.playerHeight
        description_player.text = player.playerDescription
        name_player1.text = player.playerName
       detail_posisi.text = player.playerPosition
    }
}
