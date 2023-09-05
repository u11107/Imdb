package ru.yandex.practicum.moviessearch.core.navigation

import androidx.fragment.app.Fragment


class RouterImpl : Router {

    override val navigatorHolder: NavigatorHolder = NavigatorHolderImpl()

    override fun openFragment(fragment: Fragment) {
        navigatorHolder.openFragment(fragment)
    }

}
