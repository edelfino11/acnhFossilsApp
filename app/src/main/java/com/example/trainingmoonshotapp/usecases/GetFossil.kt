package com.example.trainingmoonshotapp.usecases

import com.example.trainingmoonshotapp.data.FossilsRepo
import javax.inject.Inject

class GetFossil @Inject constructor(private val repository: FossilsRepo){
    suspend fun invoke(name: String) = repository.getFossil(name)
}