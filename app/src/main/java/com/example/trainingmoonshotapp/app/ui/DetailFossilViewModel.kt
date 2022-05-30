package com.example.trainingmoonshotapp.app.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmoonshotapp.domain.Fossil
import com.example.trainingmoonshotapp.usecases.GetFossil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFossilViewModel @Inject constructor(val getFossil: GetFossil): ViewModel() {
    private val _state: MutableStateFlow<FossilState> = MutableStateFlow(FossilState.Loading)
    val state = _state.asStateFlow()

    fun getFossilDetails(name: String) {

        viewModelScope.launch {

            val result = getFossil.invoke(name)

            _state.value = FossilState.ShowFossil(result)

            Log.d("FossilsViewModel", result.name)
        }
    }

}

sealed class FossilState {
    object Loading : FossilState()
    data class ShowFossil(val fossil: Fossil) : FossilState()
}