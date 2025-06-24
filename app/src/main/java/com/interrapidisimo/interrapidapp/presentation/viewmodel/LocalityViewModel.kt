package com.interrapidisimo.interrapidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.interrapidapp.data.database.entities.LocalityEntity
import com.interrapidisimo.interrapidapp.domain.usecase.SyncLocalitiesUseCase
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalityViewModel @Inject constructor(
    private val syncLocalitiesUseCase: SyncLocalitiesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<List<LocalityEntity>>>(Resource.Idle)
    val state: StateFlow<Resource<List<LocalityEntity>>> get() = _state

    fun loadLocalities() {
        viewModelScope.launch {
            _state.value = Resource.Loading
            _state.value = syncLocalitiesUseCase.invoke()
        }
    }
}