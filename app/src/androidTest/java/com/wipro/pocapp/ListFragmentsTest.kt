package com.wipro.pocapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListFragmentsTest {
    @Before
    fun initValidString() {

    }
    @After
    fun clereTest(){
    }

    @Test
    fun changeText_sameActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment))
        Espresso.onView(ViewMatchers.withId(R.id.progress_bar_global))
    }
}