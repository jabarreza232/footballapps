package com.evolutions.jabar.footballclub.fragment.favorite.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.sqlite.db.Favorite
import com.evolutions.jabar.footballclub.view.DateTimeConverter
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list_favorite.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteAdapter(private val context: Context, private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_favorite, parent, false))
    }
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindFavorite(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size

    /* class TeamUI:AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
            linearLayout {
             lparams(width= matchParent ,height = wrapContent)
                orientation = LinearLayout.VERTICAL

               textView{
                    id = R.id.date1
                    textSize = 16f
                    hint="test"
                    }.lparams{
                     width = matchParent
                    height = wrapContent
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    textView {
                        id = R.id.team_Home
                        textSize = 16f
                        hint="test"
                    }.lparams {
                        height = wrapContent
                        width = wrapContent

                    }
                    textView {
                        id = R.id.team_away
                        textSize = 16f
                        hint="test"
                    }.lparams {
                        margin = dip(15)
                    }
                }
            }
            }
        }
    }*/

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindFavorite(favorite:Favorite, listener: (Favorite) -> Unit) {
            val(_,idEvent,teamHome,scoreHome,teamAway,scoreAway,dateEvent,timeEvent) = favorite
            val homescore= if(scoreHome!= null) "${scoreHome}" else ""
            val awayscore = if(scoreAway!=null) "${scoreAway}" else ""

            itemView.date_favorite.text = DateTimeConverter.longDate(dateEvent+" "+timeEvent)
            itemView.time_favorite.text = DateTimeConverter.timeDate(dateEvent+" "+timeEvent)+" WIB"

            itemView.name_home3.text= teamHome
            itemView.name_away3.text= teamAway
            itemView.score_home3.text = homescore
            itemView.score_away3.text = awayscore


            itemView.onClick { listener(favorite)
            }
        }
    }
    interface LayoutContainer
}