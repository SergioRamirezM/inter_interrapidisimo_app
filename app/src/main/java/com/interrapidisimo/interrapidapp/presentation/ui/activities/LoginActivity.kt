package com.interrapidisimo.interrapidapp.presentation.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.interrapidisimo.interrapidapp.BuildConfig
import com.interrapidisimo.interrapidapp.databinding.ActivityLoginBinding
import com.interrapidisimo.interrapidapp.presentation.viewmodel.LoginViewModel
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etUser.setText(BuildConfig.DEFAULT_USER)
        binding.etPass.setText(BuildConfig.DEFAULT_PASS)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString().trim()
            val pass = binding.etPass.text.toString().trim()
            loginViewModel.doLogin(user, pass)
        }

        collectState()
    }

    private fun collectState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginState.collect { state ->
                    when (state) {
                        is Resource.Idle -> binding.progress.gone()
                        is Resource.Loading -> binding.progress.visible()
                        is Resource.Success -> {
                            binding.progress.gone()
                            goToMain()
                        }

                        is Resource.Error -> {
                            binding.progress.gone()
                            toast(state.message)
                        }
                    }
                }
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

    private fun View.visible() {
        visibility = View.VISIBLE
    }

    private fun View.gone() {
        visibility = View.GONE
    }
}