package com.triare.cocktalesproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.cocktalesproject.adapters.AlcoAdapter
import com.triare.cocktalesproject.databinding.FragmentAlcoholBinding
import com.triare.cocktalesproject.viewmodel.AlcoViewModel

class AlcoFragment: BaseCocktaleFragment() {
    private lateinit var viewModel: AlcoViewModel
    private var _binding: FragmentAlcoholBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlcoholBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AlcoViewModel::class.java)

        val recyclerView: RecyclerView = binding.recycleViewAlco

        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val adapter = AlcoAdapter()
        recyclerView.adapter = adapter
        viewModel.alcoDvoLiveData.observe(viewLifecycleOwner) {
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