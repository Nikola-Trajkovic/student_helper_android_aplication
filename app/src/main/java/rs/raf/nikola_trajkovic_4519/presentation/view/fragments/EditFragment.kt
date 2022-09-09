package rs.raf.nikola_trajkovic_4519.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.nikola_trajkovic_4519.R
import rs.raf.nikola_trajkovic_4519.databinding.FragmentEditBinding
import rs.raf.nikola_trajkovic_4519.databinding.FragmentNewBeleskeBinding
import rs.raf.nikola_trajkovic_4519.presentation.contract.MainContract
import rs.raf.nikola_trajkovic_4519.presentation.view.states.AddBeleskeState
import rs.raf.nikola_trajkovic_4519.presentation.viewmodel.BeleskeViewModel

class EditFragment(id: Long, naslov: String, content: String): Fragment(R.layout.fragment_edit) {
    private val beleskeViewModel: MainContract.ViewModelBeleske by sharedViewModel<BeleskeViewModel>()

    private var _binding: FragmentEditBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var naslov1: String
    private var content1: String
    private var id1: Long

    private val binding get() = _binding!!

    init {
        naslov1 = naslov
        content1 = content
        id1 = id
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()
        binding.inputEt.setText(naslov1)
        binding.textArea.setText(content1)
    }

    private fun init() {

        binding.dodajBtn.setOnClickListener({
            val naslovZaSlanje = binding.inputEt.text
            val contentZaSlanje = binding.textArea.text


            if(naslovZaSlanje.isBlank()){
                Toast.makeText(context, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(contentZaSlanje.isBlank()){
                Toast.makeText(context, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            println(naslovZaSlanje.toString() + "prviiii")
            println(contentZaSlanje.toString() + "drugiiiii")

            beleskeViewModel.update(id1,naslovZaSlanje.toString(),contentZaSlanje.toString())

//            val addFragment = MainFragment()
//            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
//            transaction.replace(R.id.fragmentContainerView,addFragment)
//            transaction.commit()


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