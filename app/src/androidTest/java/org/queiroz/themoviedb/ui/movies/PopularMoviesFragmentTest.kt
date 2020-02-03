package org.queiroz.themoviedb.ui.movies

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.util.EspressoIdlingResource

@RunWith(AndroidJUnit4::class)
@LargeTest
class PopularMoviesFragmentTest {

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testMovieNavigation() {
        val popularMoviesFragmentScenario = launchFragmentInContainer<PopularMoviesFragment>()
        val navController = mock(NavController::class.java)
        popularMoviesFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.popular_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        val action =
            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailFragment(
                movie = getMovieStub(),
                transitionName = "poster-0"
            )
        verify(navController).navigate(action)
    }

    private fun getMovieStub() =
        TmdbMovies.Movie(
            id = 419704,
            popularity = 455.208f,
            voteCount = 2166,
            video = false,
            posterPath = "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg",
            adult = false,
            backdropPath = "/5BwqwxMEjeFtdknRV792Svo0K1v.jpg",
            originalLanguage = "en",
            originalTitle = "Ad Astra",
            genreIds = listOf(12, 18, 9648, 878, 53),
            title = "Ad Astra",
            voteAverage = 6f,
            overview = "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
            releaseDate = "2019-09-17"
        )

}
