package com.evolutions.jabar.footballclub.fragment.event.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.model.Event
import com.evolutions.jabar.footballclub.view.DateTimeConverter
import kotlinx.android.synthetic.main.item_list.view.*

class NextMatchAdapter(private val context: Context, private val events: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<NextMatchAdapter.NextMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindTeam(events[position], listener)

    }

    override fun getItemCount(): Int = events.size

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
}
*/

    class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindTeam(events: Event, listener: (Event) -> Unit) {
            itemView.score_home.text = events.scoreHome

            itemView.score_away.text = events.scoreAway
            itemView.name_home.text = events.teamHome
            itemView.name_away.text = events.teamAway
            itemView.date_.text = DateTimeConverter.longDate(events.dateEvent + " " + events.timeEvent!!)
            itemView.time_.text = DateTimeConverter.timeDate(events.dateEvent + " " + events.timeEvent!!)

            itemView.setOnClickListener {
                listener(events)
            }
        }
    }

    interface LayoutContainer
}