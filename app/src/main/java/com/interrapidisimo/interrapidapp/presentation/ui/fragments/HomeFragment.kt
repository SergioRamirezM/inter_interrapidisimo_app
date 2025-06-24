package com.interrapidisimo.interrapidapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.interrapidisimo.interrapidapp.R
import com.interrapidisimo.interrapidapp.databinding.FragmentHomeBinding
import com.interrapidisimo.interrapidapp.presentation.viewmodel.LoginViewModel
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        navController = findNavController()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.userState.collect { res ->
                    if (res is Resource.Success) {
                        val u = res.data
                        binding.tvNombre.text = "Nombre: ${u.name}"
                        binding.tvIdentificacion.text = "Identificación: ${u.idNumber}"
                        binding.tvUsuario.text = "Usuario: ${u.username}"
                    }
                }
            }
        }

        binding.btnTablas.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_schemaFragment)
        }
        binding.btnLocalidades.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_localityFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}