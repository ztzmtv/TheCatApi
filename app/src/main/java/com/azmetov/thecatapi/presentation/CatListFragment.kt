package com.azmetov.thecatapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.azmetov.thecatapi.databinding.FragmentCatListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatListFragment : Fragment() {
    private var _binding: FragmentCatListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageViewModel by viewModel()
    private val adapter: ImagesPagingAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCatList.adapter = adapter
        adapter.setItemClickListener { viewModel.addToFavorites(it) }
        adapter.setItemLongClickListener { viewModel.deleteFavorite(it) }

        lifecycleScope.launch {
            viewModel.flow.collectLatest { adapter.submitData(it) }
        }

        lifecycleScope.launch {
            viewModel.getFavorites().collectLatest { adapter.setFavorites(it) }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.clear()
    }
}