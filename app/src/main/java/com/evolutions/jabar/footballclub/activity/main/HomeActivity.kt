package com.evolutions.jabar.footballclub.activity.main

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.Menu
import         androidx.appcompat.widget.SearchView
import com.evolutions.jabar.footballclub.R
import com.evolutions.jabar.footballclub.R.id.*
import com.evolutions.jabar.footballclub.R.menu.search
import com.evolutions.jabar.footballclub.fragment.favorite.FavoriteMatchFragment
import com.evolutions.jabar.footballclub.fragment.event.LastmatchFragment
import com.evolutions.jabar.footballclub.fragment.event.MatchFragment
import com.evolutions.jabar.footballclub.fragment.event.NextmatchFragment
import com.evolutions.jabar.footballclub.fragment.favorite.FavoriteFragment
import com.evolutions.jabar.footballclub.fragment.team.TeamFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.searchManager

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                match -> {
                    val matchFragment = MatchFragment.newInstance()
                    loadMatchFragment(matchFragment)
                }

                teams-> {
                    loadTeamFragment(savedInstanceState)
                }
                favorites -> {
                    val favoriteFragment= FavoriteFragment.newInstance()
                    loadFavoriteFragment(favoriteFragment)

                }
            }
            true
        }
        bottom_navigation.selectedItemId = match

    }

    private fun loadMatchFragment(fragment: Fragment) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container,fragment)
                    .addToBackStack(null)
                    .commit()


    }

/*
    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextmatchFragment(), NextmatchFragment::class.java.simpleName)
                    .commit()

        }
    }
*/
    private fun loadFavoriteFragment(fragment: Fragment) {

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container,fragment)
                    .addToBackStack(null)
                    .commit()

    }
    private fun loadTeamFragment(savedInstanceState: Bundle?){
        if(savedInstanceState ==null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container,TeamFragment(),TeamFragment::class.java.simpleName)
                    .commit()
        }
    }



//    private var menuItem:Menu?=null
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    menuInflater.inflate(search,menu)
//    menuItem  = menu
//        val searchView= menu?.findItem(R.id.action_search) as SearchView
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//         override fun onQueryTextSubmit(p0: String?): Boolean {
//             TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//         }
//         override fun onQueryTextChange(p0: String?): Boolean {
//             TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//         }
//
//     })
//        return super.onCreateOptionsMenu(menu)
//
//    }


}

