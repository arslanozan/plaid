/*
 * Copyright 2018 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.plaidapp.ui

import android.content.Context
import android.view.Gravity
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.nhaarman.mockitokotlin2.check
import io.plaidapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun drawerClosedOnStartup() {
        // Given that the app is not launched

        // When the app is first launched

        // Then the drawer should be closed
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))
    }

    @Test
    fun pressFilterButtonOpensDrawer() {
        // Given that the drawer is closed
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))

        // When the filter button is pressed
        onView(withId(R.id.menu_filter)).perform(click())

        // Then the drawer should be opened
        onView(withId(R.id.drawer)).check(matches(isOpen(Gravity.END)))
    }

    @Test
    fun drawerStaysOpenAfterRotation() {
        // Given that the drawer is open
        onView(withId(R.id.menu_filter)).perform(click())
        onView(withId(R.id.drawer)).check(matches(isOpen(Gravity.END)))

        // When rotating the device
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).setOrientationLeft()

        // Then the drawer should be stayed open after rotation
        onView(withId(R.id.drawer)).check(matches(isOpen(Gravity.END)))
    }

    //Test ID TC_3
    @Test
    fun searchIconDisplayedOnStartup() {
        // Given that the app is not launched

        // When the app is first launched

        // Then the search button should be displayed
        onView(withId(R.id.menu_search)).check(matches(isDisplayed()))
    }

    //Test ID TC_4
    @Test
    fun themeIconDisplayedOnStartup() {
        // Given that the app is not launched

        // When the app is first launched

        // Then the theme button should be displayed
        onView(withId(R.id.menu_theme)).check(matches(isDisplayed()))
    }

    //Test ID TC_5
    @Test
    fun filterIconDisplayedOnStartup() {
        // Given that the app is not launched

        // When the app is first launched

        // Then the filter button should be displayed
        onView(withId(R.id.menu_filter)).check(matches(isDisplayed()))

    }

    //Test ID TC_7
    @Test
    fun loginTextDisplayedOnOverflowMenuPressed() {
        // Given the app is first launched

        // When the overflow button is pressed
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())

        // Then the Log in button should be displayed
        onView(withText(R.string.designer_news_login)).check(matches(isDisplayed()))

    }

    //Test ID TC_9
    @Test
    fun aboutTextDisplayedOnOverflowMenuPressed() {
        // Given the app is first launched

        // When the overflow button is pressed
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())

        // Then the about button should be displayed
        onView(withText(R.string.about)).check(matches(isDisplayed()))
    }


    //Test ID TC_15
    @Test
    fun searchTextAreaValidOnSearchPressed() {
        // Given that search button is pressed
        onView(withId(R.id.menu_search)).perform(click())

        // When typing the text to search area
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Vngrs"),
            pressImeActionButton())

        // Then query text should be displayed
        onView(isAssignableFrom(EditText::class.java)).check(matches(withText("Vngrs")))
    }

    //Test ID TC_18
    @Test
    fun searchTextAreaClearedOnClearButtonPressed() {
        // Given that search button is pressed
        onView(withId(R.id.menu_search)).perform(click())

        // When typing the text to search area and pressing clear button
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Vngrs"))
        onView(withText("Vngrs")).perform(clearText())

        // Then query text should be cleared
        onView(isAssignableFrom(EditText::class.java)).check(matches(withText("")))
    }

    //Test ID TC_20
    @Test
    fun pressThemeButtonValid() {
        // Given the drawer is close when app launched
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))

        // When tapping to theme toggle
        onView(withId(R.id.menu_theme)).perform(click())
        Thread.sleep(2000) //waiting for theme changing is completed

        // Then theme toggle should be checked
        onView(withId(R.id.menu_theme)).check(matches(isChecked()))
    }

    //Test ID TC_26
    @Test
    fun filterItemsDisplayedOnFilterButtonPressed() {
        // Given the drawer is close when app launched
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))

        // When tapping to filter button
        onView(withId(R.id.menu_filter)).perform(click())

        // Then all items should be displayed
        onView(withText(R.string.source_product_hunt)).check(matches(isDisplayed()))
        onView(withText(R.string.source_designer_news_popular)).check(matches(isDisplayed()))
        onView(withText(R.string.source_dribbble_search_material_design)).check(matches(isDisplayed()))
    }

    //Test ID TC_27
    @Test
    fun filterDrawerClosedOnSwipeRight() {
        // Given the filter drawer is opened
        onView(withId(R.id.menu_filter)).perform(click())

        // When swiping drawer to right
        onView(withId(R.id.drawer)).perform(swipeRight())

        // Then drawer should be closed
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))
    }

    //Test ID TC_29
    @Test
    fun popupDisplayedOnLoginPressed() {
        // Given that the drawer is closed
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))

        // When tapping log in button
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())
        onView(withText(R.string.designer_news_login)).perform(click())

        // Then login popup should be opened
        onView(withText("SIGN UP")).check(matches(isDisplayed()))
    }

    //Test ID TC_31
    @Test
    fun ownerNameDisplayedOnAboutPressed() {
        // Given that the drawer is closed
        onView(withId(R.id.drawer)).check(matches(isClosed(Gravity.END)))

        // When tapping about button
        openActionBarOverflowOrOptionsMenu(
            ApplicationProvider.getApplicationContext<Context>())
        onView(withText("About")).perform(click())

        // Then about should be opened
        onView(withText("Nick Butcher")).check(matches(isDisplayed()))
    }
}
