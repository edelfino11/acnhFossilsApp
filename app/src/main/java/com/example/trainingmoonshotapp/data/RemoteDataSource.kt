package com.example.trainingmoonshotapp.data

import com.example.trainingmoonshotapp.domain.Fossil

interface RemoteDataSource {
    suspend fun getFossils(): List<Fossil>
    suspend fun getFossil(name: String): Fossil
}
