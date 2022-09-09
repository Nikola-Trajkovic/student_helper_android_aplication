package rs.raf.nikola_trajkovic_4519.presentation.view.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.presentation.view.fragments.BeleskeFragment
import rs.raf.nikola_trajkovic_4519.presentation.view.fragments.RasporedFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager,
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_1 = 0
        const val FRAGMENT_2 = 1
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> RasporedFragment()
            else -> BeleskeFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_1 -> "Raspored"
            else -> "Beleske"
        }
    }

}