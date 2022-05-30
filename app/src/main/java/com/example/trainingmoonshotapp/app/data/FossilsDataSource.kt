package com.example.trainingmoonshotapp.app.data

import com.example.trainingmoonshotapp.data.RemoteDataSource
import com.example.trainingmoonshotapp.domain.Fossil

class FossilsDataSource(private val api: FossilApi) : RemoteDataSource {

    private val apiKey = "05655aef-42c0-4a88-a180-8f58d2fd03c7"

    override suspend fun getFossils(): List<Fossil> {
        return api.getFossils(apiKey).map { it.toFossilDomain() }
    }

    override suspend fun getFossil(name: String): Fossil {
        return api.getFossil(name, apiKey).toFossilDomain()
    }
}

fun FossilBE.toFossilDomain(): Fossil {
    return Fossil(
        this.name,
        this.url,
        this.image_url,
        this.fossil_group,
        this.interactable,
        this.sell,
        this.hha_base,
        this.width,
        this.length,
        this.colors
    )
}