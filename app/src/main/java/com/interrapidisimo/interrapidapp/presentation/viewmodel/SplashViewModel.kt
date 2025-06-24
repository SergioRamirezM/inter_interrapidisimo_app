package com.interrapidisimo.interrapidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.interrapidapp.domain.usecase.CheckAppVersionUseCase
import com.interrapidisimo.interrapidapp.domain.usecase.VersionStatus
import com.interrapidisimo.interrapidapp.util.ErrorHandler
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkVersionUseCase: CheckAppVersionUseCase
) : ViewModel() {
    private val _status = MutableStateFlow<Resource<VersionStatus>>(Resource.Idle)
    val status: StateFlow<Resource<VersionStatus>> = _status

    fun checkVersion(localVersion: Int) {
        viewModelScope.launch {
            _status.value = Resource.Loading
            try {
                val result = checkVersionUseCase.invoke(localVersion)
                _status.value = Resource.Success(result)
            } catch (t: Throwable) {
                val apiError = ErrorHandler.parseError(t)
                _status.value = Resource.Error(apiError.getErrorMessage())
            }
        }
    }
}