package org.queiroz.themoviedb.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment

fun Fragment.navigate(
    resId: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null,
    extras: FragmentNavigator.Extras? = null
) {
    NavHostFragment.findNavController(this).navigate(resId, bundle, navOptions, extras)
}
