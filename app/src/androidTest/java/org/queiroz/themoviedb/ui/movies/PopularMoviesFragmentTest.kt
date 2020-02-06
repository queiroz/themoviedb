package org.queiroz.themoviedb.ui.movies

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.queiroz.themoviedb.R
import org.queiroz.themoviedb.mock
import org.queiroz.themoviedb.model.TmdbMovies
import org.queiroz.themoviedb.movies.MoviesViewModel
import org.queiroz.themoviedb.movies.repository.MoviesRepository
import org.queiroz.themoviedb.util.EspressoIdlingResource
import org.queiroz.themoviedb.util.Resource

@RunWith(AndroidJUnit4::class)
@LargeTest
class PopularMoviesFragmentTest {

    lateinit var repository: MoviesRepository
    private val mockFragmentFactory = object : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            val viewModelFactory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    runBlocking {
                        Mockito.`when`(repository.getPopularMovies(1))
                            .thenReturn(Resource.success(TmdbMovies(1, 1, 1, listOf(movie))))
                    }
                    return MoviesViewModel(repository) as T
                }
            }
            return PopularMoviesFragment(viewModelFactory)
        }
    }
    private val movie = TmdbMovies.Movie(
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

    @Before
    fun setUp() {
        repository = mock()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testMovieNavigation() {
        val navController = mock(NavController::class.java)
        val popularMoviesFragmentScenario = launchFragmentInContainer<PopularMoviesFragment>(
            factory = mockFragmentFactory,
            themeResId = R.style.TheMovieTheme
        )
        popularMoviesFragmentScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        var poster: View? = null
        val viewTransitionName = "poster-0"
        Espresso.onView(ViewMatchers.withId(R.id.popular_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                object : ViewAction {
                    override fun getDescription(): String {
                        return "item clicked"
                    }

                    override fun getConstraints(): Matcher<View> {
                        return isEnabled()
                    }

                    override fun perform(uiController: UiController?, view: View?) {
                        poster = view?.findViewById(R.id.poster)
                        poster?.transitionName = viewTransitionName
                        view?.performClick()
                    }
                })
        )
        val action =
            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMovieDetailFragment(
                movie = movie,
                transitionName = viewTransitionName
            )
        val extras = FragmentNavigatorExtras(poster!! to viewTransitionName)
        Mockito.verify(navController).navigate(action, extras)
    }

}
