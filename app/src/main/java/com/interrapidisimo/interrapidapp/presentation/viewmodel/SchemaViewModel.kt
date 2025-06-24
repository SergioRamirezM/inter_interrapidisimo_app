package com.interrapidisimo.interrapidapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interrapidisimo.interrapidapp.data.database.entities.SchemaEntity
import com.interrapidisimo.interrapidapp.domain.usecase.SyncSchemasUseCase
import com.interrapidisimo.interrapidapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchemaViewModel @Inject constructor(
    private val syncSchemasUseCase: SyncSchemasUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<Resource<List<SchemaEntity>>>(Resource.Idle)
    val uiState: StateFlow<Resource<List<SchemaEntity>>> get() = _uiState

    fun loadSchemas() {
        viewModelScope.launch {
            _uiState.value = Resource.Loading
            _uiState.value = syncSchemasUseCase.invoke()
        }
    }
}