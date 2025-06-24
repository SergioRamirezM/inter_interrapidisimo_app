package com.interrapidisimo.interrapidapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.interrapidisimo.interrapidapp.databinding.FragmentSchemaBinding
import com.interrapidisimo.interrapidapp.presentation.ui.adapters.SchemaAdapter
import com.interrapidisimo.interrapidapp.presentation.viewmodel.SchemaViewModel
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SchemaFragment : Fragment() {
    private var _binding: FragmentSchemaBinding? = null
    private val binding get() = _binding!!
    private val schemaViewModel: SchemaViewModel by viewModels()
    private lateinit var schemaAdapter: SchemaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchemaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        schemaAdapter = SchemaAdapter().also { binding.rvSchemas.adapter = it }
        binding.rvSchemas.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                schemaViewModel.uiState.collect { res ->
                    when (res) {
                        is Resource.Loading -> {
                            binding.progressSchema.visible()
                            binding.rvSchemas.gone()
                            binding.tvSchemaError.gone()
                        }

                        is Resource.Success -> {
                            binding.progressSchema.gone()
                            if (res.data.isEmpty()) {
                                binding.tvSchemaError.text = "No hay tablas disponibles"
                                binding.tvSchemaError.visible()
                            } else {
                                schemaAdapter.submitList(res.data)
                                binding.rvSchemas.visible()
                            }
                        }

                        is Resource.Error -> {
                            binding.progressSchema.gone()
                            binding.rvSchemas.gone()
                            binding.tvSchemaError.text = res.message
                            binding.tvSchemaError.visible()
                        }

                        else -> Unit
                    }
                }
            }
        }
        schemaViewModel.loadSchemas()
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