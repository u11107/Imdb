package ru.yandex.practicum.moviessearch.ui.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import org.koin.android.ext.android.inject
import ru.yandex.practicum.moviessearch.R
import ru.yandex.practicum.moviessearch.core.navigation.NavigatorHolder
import ru.yandex.practicum.moviessearch.core.navigation.NavigatorImpl
import ru.yandex.practicum.moviessearch.databinding.ActivityRootBinding
import ru.yandex.practicum.moviessearch.ui.movies.MoviesFragment


class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailsFragment, R.id.moviesCastFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}
