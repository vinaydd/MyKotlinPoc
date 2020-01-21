package com.wipro.pocapp
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.wipro.pocapp.ui.home.MainActivity
import org.junit.After

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @Before
    fun initValidString() {

    }
    @After
    fun clereTest(){
    }

    @Test
    fun changeText_sameActivity() {
        onView(withId(R.id.fragment))
        onView(withId(R.id.progress_bar_global))
    }
}