package rs.raf.nikola_trajkovic_4519.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.data.models.Beleske
import rs.raf.nikola_trajkovic_4519.databinding.FragmentBeleskeBinding
import rs.raf.nikola_trajkovic_4519.databinding.FragmentNewBeleskeBinding
import rs.raf.nikola_trajkovic_4519.presentation.contract.MainContract
import rs.raf.nikola_trajkovic_4519.presentation.view.adapters.MainPagerAdapter
import rs.raf.nikola_trajkovic_4519.presentation.view.states.AddBeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.BeleskeViewModel
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.RasporedViewModel
import java.util.*

class NewBeleskeFragment: Fragment(R.layout.fragment_new_beleske){

    private val beleskeViewModel: MainContract.ViewModelBeleske by sharedViewModel<BeleskeViewModel>()

    private var _binding: FragmentNewBeleskeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewBeleskeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()
    }

    private fun init() {

        binding.dodajBtn.setOnClickListener({
            val naslov = binding.inputEt.text
            val content = binding.textArea.text

            println(naslov)
            println(content)

            if(naslov.isBlank()){
                Toast.makeText(context, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(content.isBlank()){
                Toast.makeText(context, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            println(naslov.toString() + "prviiii")
            println(content.toString() + "drugiiiii")

            beleskeViewModel.addBeleske(naslov.toString(),content.toString())

            binding.inputEt.text.clear()
            binding.textArea.text.clear()


        })

        binding.odustaniBtn.setOnClickListener({
            val addFragment = MainFragment()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragmentContainerView,addFragment)
            transaction.commit()
        })

    }

    private fun initObservers() {
        beleskeViewModel.addDone.observe(viewLifecycleOwner,  androidx.lifecycle.Observer {
            renderState(it)
        })
    }

    private fun renderState(state: AddBeleskeState) {
        when(state) {
            is AddBeleskeState.Success -> Toast.makeText(context, "Movie added", Toast.LENGTH_SHORT)
                .show()
            is AddBeleskeState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val manager = parentFragmentManager.beginTransaction()
        manager.remove(this).commit()
        _binding = null
    }

}