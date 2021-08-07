package com.dicoding.dummyfilmapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dicoding.dummyfilmapp.R
import com.dicoding.dummyfilmapp.core.utils.EspressoIdlingResource
import com.dicoding.dummyfilmapp.favourite.ui.FavouriteActivity
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import com.dicoding.dummyfilmapp.favourite.R as RFav


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun test1_loadFilmList() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())

        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.view_pager2)).perform(swipeRight())
    }

    @Test
    fun test2_loadMovieDetail() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_sinopsis)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    }

    @Test
    fun test3_loadTvShowDetail() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_detail_sinopsis)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    }

    @Test
    fun test4_favButtonTest() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.fab_fav)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.fab_fav)).perform(click())

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    }

    @Test
    fun test5_loadFilmFavList() {
        ActivityScenario.launch(FavouriteActivity::class.java).use {
            onView(withId(RFav.id.rv_movie_fav)).check(matches(isDisplayed()))
            onView(withId(RFav.id.view_pager2)).perform(swipeLeft())
            onView(withId(RFav.id.rv_tv_fav)).check(matches(isDisplayed()))
            onView(withId(RFav.id.view_pager2)).perform(swipeRight())
            onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        }
    }

    @Test
    fun test6_loadMovieFavDetail() {
        ActivityScenario.launch(FavouriteActivity::class.java).use {
            onView(withId(RFav.id.rv_movie_fav)).check(matches(isDisplayed()))
            onView(withId(RFav.id.rv_movie_fav)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

            onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))
            onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_detail_sinopsis)).check(matches(isDisplayed()))

            onView(withId(R.id.fab_fav)).perform(click())
            onView(isRoot()).perform(pressBack())
        }
    }

    @Test
    fun test7_loadTvShowFavDetail() {
        ActivityScenario.launch(FavouriteActivity::class.java).use {
            onView(withText("TV SHOW")).perform(click())
            onView(withId(RFav.id.rv_tv_fav)).check(matches(isDisplayed()))
            onView(withId(RFav.id.rv_tv_fav)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

            onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_detail_release_date)).check(matches(isDisplayed()))
            onView(withId(R.id.iv_detail_poster)).check(matches(isDisplayed()))
            onView(withId(R.id.tv_detail_sinopsis)).check(matches(isDisplayed()))

            onView(withId(R.id.fab_fav)).perform(click())
            onView(isRoot()).perform(pressBack())
        }
    }

}