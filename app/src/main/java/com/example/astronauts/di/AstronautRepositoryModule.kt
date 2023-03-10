package com.example.astronauts.di

import com.example.astronauts.datalayer.AstronautsRepository
import com.example.astronauts.datalayer.AstronautsRepositoryImpl
import com.example.astronauts.datalayer.network.RemoteDataService
import com.example.astronauts.datalayer.network.RemoteDataServiceImpl
import com.example.astronauts.datalayer.network.SpaceLaunchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AstronautRepositoryModule {
    @Singleton
    @Provides
    fun providesAstronautRepository(remoteDataService: RemoteDataService): AstronautsRepository {
        return AstronautsRepositoryImpl(remoteDataService)
    }
}