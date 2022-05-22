package com.triare.cocktalesproject.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.cocktalesproject.adapters.AlcoAdapter
import com.triare.cocktalesproject.databinding.FragmentNonAlcoholBinding
import com.triare.cocktalesproject.viewmodel.NoneAlcoViewModel

class NoneAlcoFragment: BaseFragment<FragmentNonAlcoholBinding>(FragmentNonAlcoholBinding::inflate) {
    private lateinit var viewModel: NoneAlcoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoneAlcoViewModel::class.java)
        val recyclerView: RecyclerView = binding.recyclerViewNonAlco
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val adapter = AlcoAdapter()
        recyclerView.adapter = adapter
        viewModel.alcoDvoLiveData.observe(viewLifecycleOwner) {
            it?.let { adapter.submitList(it.drinks) }
        }

    }
}
