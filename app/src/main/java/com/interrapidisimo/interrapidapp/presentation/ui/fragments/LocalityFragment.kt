package com.interrapidisimo.interrapidapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.interrapidisimo.interrapidapp.databinding.FragmentLocalityBinding
import com.interrapidisimo.interrapidapp.presentation.ui.adapters.LocalityAdapter
import com.interrapidisimo.interrapidapp.presentation.viewmodel.LocalityViewModel
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocalityFragment : Fragment() {

    private var _binding: FragmentLocalityBinding? = null
    private val binding get() = _binding!!
    private val localityViewModel: LocalityViewModel by viewModels()
    private lateinit var localityAdapter: LocalityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocalityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        localityAdapter = LocalityAdapter().also { binding.rvLocalities.adapter = it }
        binding.rvLocalities.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                localityViewModel.state.collect { res ->
                    when (res) {
                        is Resource.Loading -> {
                            binding.progressLocality.visible()
                            binding.rvLocalities.gone()
                            binding.tvLocalityError.gone()
                        }

                        is Resource.Success -> {
                            binding.progressLocality.gone()
                            if (res.data.isEmpty()) {
                                binding.tvLocalityError.text = "No hay localidades"
                                binding.tvLocalityError.visible()
                                binding.rvLocalities.gone()
                            } else {
                                localityAdapter.submitList(res.data)
                                binding.tvLocalityError.gone()
                                binding.rvLocalities.visible()
                            }
                        }

                        is Resource.Error -> {
                            binding.progressLocality.gone()
                            binding.rvLocalities.gone()
                            binding.tvLocalityError.text = res.message
                            binding.tvLocalityError.visible()
                        }

                        else -> Unit
                    }
                }
            }
        }
        localityViewModel.loadLocalities()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.gone() {
        visibility = View.GONE
    }
}
