package com.interrapidisimo.interrapidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.interrapidapp.data.database.entities.UserEntity
import com.interrapidisimo.interrapidapp.data.remote.dto.request.LoginRequestDto
import com.interrapidisimo.interrapidapp.data.repository.DatabaseRepository
import com.interrapidisimo.interrapidapp.domain.usecase.LoginUseCase
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val databaseRepository: DatabaseRepository,
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<UserEntity>>(Resource.Idle)
    val loginState: StateFlow<Resource<UserEntity>> get() = _loginState

    private val _userState = MutableStateFlow<Resource<UserEntity>>(Resource.Idle)
    val userState: StateFlow<Resource<UserEntity>> get() = _userState

    init {
        viewModelScope.launch {
            kotlin.runCatching { databaseRepository.getUser() }
                .onSuccess { _userState.value = Resource.Success(it) }
        }
    }

    fun doLogin(username: String, password: String) {

        if (username.isBlank() || password.isBlank()) {
            _loginState.value = Resource.Error("Usuario y contraseña requeridos")
            return
        }
        viewModelScope.launch {
            _loginState.value = Resource.Loading
            when (val res = loginUseCase(
                LoginRequestDto(
                    username = username, password = password
                )
            )) {
                is Resource.Success -> {
                    _loginState.value = res
                    _userState.value = res
                }

                is Resource.Error -> {
                    _loginState.value = res
                }

                else -> Unit
            }
        }
    }
}