package org.queiroz.themoviedb.di.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class AppFragmentFactory @Inject constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = creators[fragmentClass]
            ?: return instantiateNoArgsFragment(classLoader, className)

        try {
            return creator.get()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun instantiateNoArgsFragment(classLoader: ClassLoader, className: String): Fragment {
        Timber.w("No creator found for class: $className. Using default constructor")
        return super.instantiate(classLoader, className)
    }

}
