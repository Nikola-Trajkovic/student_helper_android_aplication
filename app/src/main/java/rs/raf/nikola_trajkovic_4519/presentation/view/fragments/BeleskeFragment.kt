package rs.raf.nikola_trajkovic_4519.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.databinding.FragmentBeleskeBinding
import rs.raf.nikola_trajkovic_4519.modules.beleskeModule

import rs.raf.nikola_trajkovic_4519.presentation.contract.MainContract
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.adapter.BeleskeAdapter
import rs.raf.nikola_trajkovic_4519.presentation.view.recycler.adapter.RasporedAdapter
import rs.raf.nikola_trajkovic_4519.presentation.view.states.AddBeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.view.states.BeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.view.states.RasporedState
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.BeleskeViewModel
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.RasporedViewModel
import timber.log.Timber
import java.util.*

class BeleskeFragment : Fragment(R.layout.fragment_beleske) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModelBeleske by sharedViewModel<BeleskeViewModel>()

    private var _binding: FragmentBeleskeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: BeleskeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeleskeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        initUi()
        initObservers()

    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BeleskeAdapter(onArchivedClicked = {
            println("USAO U ARCHIVE")
            it.id?.let { it1 -> mainViewModel.archive(it1) }
        }, onDeleteClicked = {
            println("USAO U DELETE")
            it.id?.let { it1 -> mainViewModel.delete(it1) }
        }, onEditClicked = {
            println("USAO U EDIT")
            val addFragment = it.id?.let { it1 -> EditFragment(it1,it.naslov,it.content) }
            val transaction: FragmentTransaction= fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentContainerView,addFragment!!)
            transaction.remove(this)
            transaction.commit()
        })
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.inputEt.doAfterTextChanged {
            val filter = it.toString()
            mainViewModel.getBeleskeFilter(filter)
        }

        binding.switch1.setOnClickListener {
            if (binding.switch1.isChecked) {

                mainViewModel.getArchived()

            } else {

                mainViewModel.getAllBeleske()

            }
        }

        binding.addBtn.setOnClickListener {
            val addFragment = NewBeleskeFragment()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, addFragment)
            transaction.remove(this)
            transaction.commit()
        }
    }

    private fun initObservers() {
        mainViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        mainViewModel.getAllBeleske()
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query

        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()

    }

    private fun renderState(state: BeleskeState) {
        when (state) {
            is BeleskeState.Success -> {

                adapter.submitList(state.beleske)
            }
            is BeleskeState.Error -> {

                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is BeleskeState.DataFetched -> {

                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG).show()
            }
            is BeleskeState.Loading -> {

            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}