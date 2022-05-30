package com.example.trainingmoonshotapp.data

import com.example.trainingmoonshotapp.domain.Fossil

class FossilsRepo(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getFossils(): List<Fossil> {
        return remoteDataSource.getFossils()
    }

    suspend fun getFossil(name: String): Fossil {
        return remoteDataSource.getFossil(name)
    }
}