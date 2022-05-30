package com.example.trainingmoonshotapp.app.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainingmoonshotapp.domain.Fossil
import com.example.trainingmoonshotapp.usecases.GetFossils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FossilsViewModel @Inject constructor(getFossils: GetFossils): ViewModel() {
    private val _state: MutableStateFlow<FossilScreensState> = MutableStateFlow(FossilScreensState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {

            val result = getFossils.invoke()

            _state.value = FossilScreensState.ShowFossilList(result)

            Log.d("FossilsViewModel", result.get(1).name)
        }
    }
}


sealed class FossilScreensState {
    object Loading : FossilScreensState()
    data class ShowFossilList(val fossils: List<Fossil>) : FossilScreensState()
}