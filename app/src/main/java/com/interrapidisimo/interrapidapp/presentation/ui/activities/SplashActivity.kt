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
import com.interrapidisimo.interrapidapp.databinding.ActivitySplashBinding
import com.interrapidisimo.interrapidapp.domain.usecase.VersionStatus
import com.interrapidisimo.interrapidapp.presentation.viewmodel.SplashViewModel
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeVersionCheck()
        splashViewModel.checkVersion(BuildConfig.VERSION_CODE)

    }

    private fun observeVersionCheck() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                splashViewModel.status.collect { res ->
                    when (res) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            handleVersionStatus(res.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@SplashActivity, res.message, Toast.LENGTH_LONG)
                                .show()
                            finish()
                        }

                        is Resource.Idle -> Unit

                    }
                }
            }
        }
    }

    private fun handleVersionStatus(status: VersionStatus) {
        when (status) {
            VersionStatus.UPDATE_REQUIRED ->
                Toast.makeText(this, "Por favor actualiza la app", Toast.LENGTH_LONG).show()

            VersionStatus.UP_TO_DATE ->
                startActivity(Intent(this, LoginActivity::class.java))

            VersionStatus.AHEAD ->
                Toast.makeText(
                    this,
                    "Tu versión es más reciente que la del servidor",
                    Toast.LENGTH_LONG
                ).show()
        }
        finish()
    }
}
