package com.triare.cocktalesproject.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.cocktalesproject.adapters.AlcoAdapter
import com.triare.cocktalesproject.databinding.FragmentAlcoholBinding
import com.triare.cocktalesproject.viewmodel.AlcoViewModel

class AlcoFragment: BaseFragment<FragmentAlcoholBinding>(FragmentAlcoholBinding::inflate) {
    private lateinit var viewModel: AlcoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AlcoViewModel::class.java)
        val recyclerView: RecyclerView = binding.recycleViewAlco
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = AlcoAdapter()
        recyclerView.adapter = adapter
        viewModel.alcoDvoLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it.drinks) }
        }

    }

}