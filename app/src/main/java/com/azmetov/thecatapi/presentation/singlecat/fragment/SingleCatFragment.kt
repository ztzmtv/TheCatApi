package com.azmetov.thecatapi.presentation.singlecat.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.azmetov.thecatapi.databinding.FragmentSingleCatBinding
import com.azmetov.thecatapi.presentation.imageloader.Downloader
import com.azmetov.thecatapi.presentation.imageloader.ImageLoader
import org.koin.android.ext.android.inject

class SingleCatFragment : Fragment() {
    private var _binding: FragmentSingleCatBinding? = null
    private val binding get() = _binding!!
    private val imageLoader: ImageLoader by inject()
    private val downloader: Downloader by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingleCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(EXTRA_ID) ?: throw NullPointerException("argument is null")
        binding.catIv.apply {
            imageLoader.loadInto(url, this, requireContext())
            setOnClickListener {
                downloader.downloadFile(url)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_ID = "url"
        fun newInstance(url: String): Fragment {
            return SingleCatFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, url)
                }
            }
        }
    }
}