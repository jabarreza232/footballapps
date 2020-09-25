package com.evolutions.jabar.footballclub.fragment.team

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.R.array.league
import com.evolutions.jabar.footballclub.activity.detail.team.TeamDetailActivity
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.model.Teams
import com.evolutions.jabar.footballclub.view.invisible
import com.evolutions.jabar.footballclub.view.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh


class TeamFragment : Fragment(), TeamView {

    private var teams: MutableList<Teams> = mutableListOf()

    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter

    private lateinit var leagueName: String
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        adapter = TeamAdapter(teams) {
            requireContext().startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        list_team.adapter = adapter
        val request = ApiRespository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)

            }


            override fun onNothingSelected(parent: AdapterView<*>) {}

        }
        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        // Inflate the options menu from XML
        inflater?.inflate(R.menu.search, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.action_search)?.actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(true) // Do not iconify the widget; expand it by default

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    presenter.searchTeam(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    /*
    @SuppressLint("ResourceAsColor")
    override fun createView(ui:AnkoContext<Context>):View= with(ui){
        linearLayout{
            lparams(width= matchParent,height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding=dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)


            spinner = spinner()

            swipeRefresh = swipeRefreshLayout{
                setColorSchemeColors(android.support.design.R.attr.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                )
                relativeLayout {
                    lparams(width= matchParent, height = wrapContent)
                    listEvent = recyclerView{
                        lparams(width= matchParent,height = wrapContent)
                        layoutManager= LinearLayoutManager(ctx)
                    }
                    progressBar=progressBar{

                    }.lparams{
                        centerHorizontally()
                    }
                }

            }

        }
    }

*/
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Teams>?) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        if (data != null) {
            teams.addAll(data)

        }
        adapter.notifyDataSetChanged()
    }


}
