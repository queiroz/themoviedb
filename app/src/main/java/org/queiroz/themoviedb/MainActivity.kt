package org.queiroz.themoviedb

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_toolbar.*

class MainActivity : DaggerAppCompatActivity() {

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.TheMovieTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(main_toolbar)
        setupActionBarWithNavController(navController)
    }

}
