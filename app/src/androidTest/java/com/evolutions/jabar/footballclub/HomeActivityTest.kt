package com.evolutions.jabar.footballclub

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.recyclerview.widget.RecyclerView
import com.evolutions.jabar.footballclub.R.id.*
import com.evolutions.jabar.footballclub.R.layout.item_list
import com.evolutions.jabar.footballclub.activity.main.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    /*
  @Test
    fun testRecyclerViewBehaviour() {
      Thread.sleep(3000)
      onView(withId(list_event))
              .check(matches(isDisplayed()))
      onView(withId(list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
      onView(withId(list_event)).perform(
              RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

    }
*/
    @Test
    fun testAppBehavior(){

         Thread.sleep(3000)
        onView(withId(list_last_match))
                .check(matches(isDisplayed()))
        onView(withId(list_last_match)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(list_last_match)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))


        Thread.sleep(2000)
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added To Favorite"))
                .check(matches(isDisplayed()))
        pressBack()

        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
           Thread.sleep(3000)
        onView(withId(list_team))
                .check(matches(isDisplayed()))
        onView(withId(list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(list_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        Thread.sleep(2000)
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added To Favorite"))
                .check(matches(isDisplayed()))
        pressBack()



        Thread.sleep(2000)

        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())

        Thread.sleep(2000)
        onView(withId(tab_favorite)).check(matches(isDisplayed()))
        onView(withText("Team")).perform(click())



   }



}