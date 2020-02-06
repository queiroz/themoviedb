package org.queiroz.themoviedb

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.AndroidSupportInjection
import org.queiroz.themoviedb.di.factory.AppFragmentFactory
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class AppNavHostFragment : NavHostFragment() {

    @Inject
    protected lateinit var appFragmentFactory: AppFragmentFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = appFragmentFactory
        super.onCreate(savedInstanceState)
    }

}
