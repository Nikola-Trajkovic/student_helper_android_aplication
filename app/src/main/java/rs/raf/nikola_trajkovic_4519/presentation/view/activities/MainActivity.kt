package rs.raf.nikola_trajkovic_4519.presentation.view.activities

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.databinding.ActivityMainBinding
import rs.raf.nikola_trajkovic_4519.presentation.view.adapters.MainPagerAdapter
import rs.raf.nikola_trajkovic_4519.presentation.view.fragments.MainFragment
import rs.raf.nikola_trajkovic_4519.presentation.view.fragments.NewBeleskeFragment
import android.R.attr.fragment
import androidx.fragment.app.FragmentManager



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        val fragment = MainFragment()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
//        binding.viewPager.adapter =
//            MainPagerAdapter(
//                supportFragmentManager,
//                this
//            )
//        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}
