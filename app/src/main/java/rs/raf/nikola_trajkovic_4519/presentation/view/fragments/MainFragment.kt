package rs.raf.nikola_trajkovic_4519.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.databinding.ActivityMainBinding
import rs.raf.nikola_trajkovic_4519.databinding.FragmentMainBinding
import rs.raf.nikola_trajkovic_4519.presentation.contract.MainContract
import rs.raf.nikola_trajkovic_4519.presentation.view.adapters.MainPagerAdapter
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.RasporedViewModel

class MainFragment: Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainContract.ViewModel by sharedViewModel<RasporedViewModel>()

    private var _binding: FragmentMainBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.viewPager.adapter =
            MainPagerAdapter(
                parentFragmentManager
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        val fragment = binding.viewPager.get(MainPagerAdapter.FRAGMENT_1).findFragment<Fragment>()
        val manager = parentFragmentManager.beginTransaction()
        manager.remove(fragment).commit()


        _binding = null
    }

}