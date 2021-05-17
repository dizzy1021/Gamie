package dev.dizzy1021.gamie.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.dizzy1021.core.utils.EspressoIdlingResource
import dev.dizzy1021.core.utils.SPLASH_SCREEN_DELAY
import dev.dizzy1021.gamie.R

@AndroidEntryPoint
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val actionBar = requireActivity().findViewById<Toolbar>(R.id.main_toolbar)
        actionBar.isGone = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        EspressoIdlingResource.increment()

        Handler(Looper.getMainLooper()).postDelayed({
            context?.let {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }

            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }

        }, SPLASH_SCREEN_DELAY)
    }

}