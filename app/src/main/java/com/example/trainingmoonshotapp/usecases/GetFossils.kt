package com.example.trainingmoonshotapp.usecases

import com.example.trainingmoonshotapp.data.FossilsRepo
import javax.inject.Inject

class GetFossils @Inject constructor(private val repository: FossilsRepo){
    suspend fun invoke() = repository.getFossils()
}