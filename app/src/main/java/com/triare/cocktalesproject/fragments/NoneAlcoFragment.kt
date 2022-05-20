package com.triare.cocktalesproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.cocktalesproject.adapters.NonAlcoAdapter
import com.triare.cocktalesproject.databinding.FragmentNonAlcoholBinding
import com.triare.cocktalesproject.viewmodel.NoneAlcoViewModel

class NoneAlcoFragment: Fragment() {
    private lateinit var viewModel: NoneAlcoViewModel
    private var _binding: FragmentNonAlcoholBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNonAlcoholBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NoneAlcoViewModel::class.java)

        val recyclerView: RecyclerView = binding.recyclerViewNonAlco

        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val adapter = NonAlcoAdapter()
        recyclerView.adapter = adapter
        viewModel.NonAlcoDvoLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it.drinks) }
        }
        val view = binding.root
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
