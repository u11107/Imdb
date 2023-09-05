package ru.yandex.practicum.moviessearch.core.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Navigator - сущность для работы с [FragmentManager].
 */
interface Navigator {

    @get:IdRes
    val fragmentContainerViewId: Int
    val fragmentManager: FragmentManager

    fun openFragment(fragment: Fragment)

}
