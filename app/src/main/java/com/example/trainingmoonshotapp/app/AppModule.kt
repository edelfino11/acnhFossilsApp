package com.example.trainingmoonshotapp.app

import com.example.trainingmoonshotapp.app.data.FossilApi
import com.example.trainingmoonshotapp.app.data.FossilsDataSource
import com.example.trainingmoonshotapp.data.FossilsRepo
import com.example.trainingmoonshotapp.data.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    @Provides
    @Singleton
    fun provideFossilsApi(okHttpClient: OkHttpClient): FossilApi {
        return Retrofit.Builder()
            .baseUrl("https://api.nookipedia.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FossilApi::class.java)
    }

    @Provides
    fun provideFossilsNetworkDataSource(api: FossilApi): RemoteDataSource {
        return FossilsDataSource(api)
    }

    @Provides
    fun provideFossilsRepository(dataSource: RemoteDataSource): FossilsRepo {
        return FossilsRepo(dataSource)
    }

}