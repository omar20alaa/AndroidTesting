package app.android_unit_testing

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import app.android_unit_testing.ui.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Before
    fun setUp() {
        // Launch the activity
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun recyclerViewIsDisplayed() {
        // Check if the RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewScroll() {
        // Scroll to item in the RecyclerView
        onView(withId(R.id.recyclerView))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(10))
    }

}


