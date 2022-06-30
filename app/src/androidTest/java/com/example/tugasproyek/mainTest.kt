package com.example.tugasproyek

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainTest {
    @Rule @JvmField
    var activityTestRule = ActivityTestRule(Login::class.java)

    @Test
    fun login2register(){
        onView(withId(R.id.btn_registertologin)).perform(ViewActions.click())
        onView(withId(R.id.register_view)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun pressback2login(){
        onView(withId(R.id.btn_registertologin)).perform(ViewActions.click())
        onView(withId(R.id.register_view)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBack()
        Espresso.pressBack()
        onView(withId(R.id.login_view)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun Login(){
        onView(withId(R.id.txtEmail)).perform(ViewActions.typeText("tommy@gmail.com"))
        onView(withId(R.id.txtPassword)).perform(ViewActions.typeText("tommy"))
        onView(withId(R.id.btnRegister)).perform(ViewActions.click())
        onView(withId(R.id.home_view)).check(ViewAssertions.matches(isDisplayed()))
    }





}

