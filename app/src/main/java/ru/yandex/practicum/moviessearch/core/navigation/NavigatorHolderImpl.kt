package ru.yandex.practicum.moviessearch.core.navigation

import androidx.fragment.app.Fragment

class NavigatorHolderImpl : NavigatorHolder {

    private var navigator: Navigator? = null

    override fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun detachNavigator() {
        this.navigator = null
    }

    override fun openFragment(fragment: Fragment) {
        navigator?.openFragment(fragment)
    }

}
