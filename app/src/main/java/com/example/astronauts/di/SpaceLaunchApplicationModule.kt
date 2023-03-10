package com.example.astronauts.di

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
class SpaceLaunchApplicationModule {
    @Singleton
    @Provides
    fun providesSpaceLaunchService(retrofit: Retrofit): SpaceLaunchService {
        return retrofit.create(SpaceLaunchService::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(spaceLaunchService: SpaceLaunchService): RemoteDataService {
        return RemoteDataServiceImpl(spaceLaunchService)
    }
}