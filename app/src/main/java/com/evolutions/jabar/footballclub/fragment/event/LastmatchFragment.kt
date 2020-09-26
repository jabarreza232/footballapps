package com.evolutions.jabar.footballclub.fragment.event

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.activity.detail.event.DetailEvents
import com.evolutions.jabar.footballclub.api.ApiRespository
import com.evolutions.jabar.footballclub.fragment.event.adapter.LastMatchAdapter
import com.evolutions.jabar.footballclub.fragment.event.presenter.LastMatchPresenter
import com.evolutions.jabar.footballclub.fragment.event.view.LastMatchView
import com.evolutions.jabar.footballclub.model.Event
import com.evolutions.jabar.footballclub.view.invisible
import com.evolutions.jabar.footballclub.view.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_lastmatch.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh

class LastmatchFragment : Fragment(), LastMatchView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: LastMatchAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lastmatch, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = LastMatchAdapter(requireContext(), events) {
            requireContext().startActivity<DetailEvents>("id" to it.eventId)
        }
        list_last_match.adapter = adapter

        val league = arrayOf("4328", "4329", "4330", "4331", "4332", "4334", "4335")
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        val spinnerPosition = spinnerAdapter.getPosition("English Premier League")

        spinnerLast.adapter = spinnerAdapter
        spinnerLast.setSelection(spinnerPosition)
        val request = ApiRespository()
        val gson = Gson()
        presenter = LastMatchPresenter(this, request, gson)
        presenter.getEventList(league[0])

        swipeRefreshLast.onRefresh {
            presenter.getEventList(league[0])
        }

        spinnerLast.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                presenter.getEventList(league[pos])
            }
        }
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
                    presenter.searchEvent(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            })

        }
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun showLoading() {
        list_last_match?.invisible()
        progressBarLast?.visible()
    }

    override fun hideLoading() {
        list_last_match?.visible()
        progressBarLast?.invisible()
    }

    override fun showLastMatchList(data: List<Event>?) {
        swipeRefreshLast?.isRefreshing = false
        events.clear()
        if (data != null) {
            events.addAll(data)
        }
        adapter.notifyDataSetChanged()

    }

}