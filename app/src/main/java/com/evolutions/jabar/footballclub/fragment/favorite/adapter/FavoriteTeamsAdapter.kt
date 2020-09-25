package com.evolutions.jabar.footballclub.fragment.favorite.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.evolutions.jabar.footballclub.R.id.team_badge
import com.evolutions.jabar.footballclub.R.id.team_name
import com.evolutions.jabar.footballclub.sqlite.db.Favorite

import com.evolutions.jabar.footballclub.sqlite.db.FavoriteTeams
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class FavoriteTeamsAdapter(private val favorite:List<FavoriteTeams>, private val listener:(FavoriteTeams)->Unit): RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI1().createView(AnkoContext.create(parent.context, parent)))

    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position],listener)
    }

    override fun getItemCount(): Int = favorite.size

}

class TeamUI1 : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent , height = wrapContent)
                leftPadding = dip(16)
                rightPadding = dip(16)
                bottomPadding = dip(16)
                topPadding = dip(8)
                orientation = LinearLayout.HORIZONTAL

                imageView{
                    id=  team_badge
                }.lparams{
                    width = dip(50)
                    height = dip (50)
                }
                textView{
                    id= team_name
                    textSize = 16f
                }.lparams{
                    margin= dip(15)
                }}
        }
    }
}
class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(favorite: FavoriteTeams,listener: (FavoriteTeams) -> Unit){
        Picasso.get().load(favorite.teamBadge).into(teamBadge)
        teamName.text = favorite.teamName

        itemView.onClick {
            listener(favorite)
        }

    }


}