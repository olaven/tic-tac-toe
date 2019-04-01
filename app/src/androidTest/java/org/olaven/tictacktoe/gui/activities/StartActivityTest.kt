package org.olaven.tictacktoe.gui.activities

import android.os.SystemClock
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.olaven.tictacktoe.R
import org.olaven.tictacktoe.gui.BaseActivity


class StartActivityTest {

    // specifying activity to run before each test
    @get:Rule
    var intentsTestRule = IntentsTestRule(BaseActivity::class.java)

    @Test
    fun canChooseElement() {

        val id = R.id.fragment_start_spinner_player2
        val name = R.string.AI_name
        onView(withId(id))
            .perform(click())


        onData(withText(name))
            .perform(click())

        onView(withId(id))
            .check(matches(withSpinnerText(name)))
    }

    @Test
    fun sameUserResultsInToast() {

        onView(withId(R.id.fragment_start_button_start))
            .perform(click())

        onView(withText(R.string.same_user_message))
            .inRoot(withDecorView(not(intentsTestRule.activity.window.decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun differentUserDoesNotToast() {

        val spinner = R.id.fragment_start_spinner_player2
        val button = R.id.fragment_start_button_start
        val message = R.string.same_user_message


        onView(withId(spinner))
            .perform(click())

        onData(withText(R.string.AI_name))
            .perform(click())


        onView(withId(button))
            .perform(click())

        onView(withText(message))
            .inRoot(withDecorView(not(intentsTestRule.activity.window.decorView)))
            .check(doesNotExist())
    }

    @Test
    fun differentUserLaunchesNewActivity() {

        val spinner = R.id.fragment_start_spinner_player2
        val button = R.id.fragment_start_button_start

        onView(withId(spinner))
            .perform(click())

        onData(withText(R.string.AI_name))
            .perform(click())

        onView(withId(button))
            .perform(click())

        onView(withId(R.id.fragment_game_grid_view))
            .check(matches(isDisplayed()))
    }

}