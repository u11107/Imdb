package ru.yandex.practicum.moviessearch.core.navigation

import androidx.fragment.app.Fragment

/**
 * Router - это входная точка для фрагментов,
 * которые хотят открыть следующий экран.
 */
interface Router {

    val navigatorHolder: NavigatorHolder

    /**
     * Функция для перехода на нужный фрагмент.
     */
    fun openFragment(fragment: Fragment)

}
