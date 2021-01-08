package com.gamerole.app.di

import com.gamerole.app.service.HttpService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpService(retrofit: Retrofit): HttpService {
        return retrofit.create(HttpService::class.java)
    }
}