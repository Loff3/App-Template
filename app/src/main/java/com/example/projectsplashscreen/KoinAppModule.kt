package com.example.projectsplashscreen

import com.example.projectsplashscreen.data.JoobleApiService.JobsRepository
import com.example.projectsplashscreen.data.JoobleApiService.JobsRepositoryImpl
import com.example.projectsplashscreen.data.JoobleApiService.JoobleApiService
import com.example.projectsplashscreen.presentation.searchcomponents.SearchViewModel
import com.example.projectsplashscreen.presentation.jobs.JobSharedViewModel
import com.example.projectsplashscreen.presentation.jobs.JobsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Koin Module
// Module = container to define dependencies and instructions how to create instances
// Define what should be able available for injection.
// Life cycle management = single, factory, or scoped
// Single for stateless components like repositories and API-clients
// viewModel for viewmodels which holds state
// Factory for creating a new instance every time
// AppModule.kt

val NavGraphScope = named("NavGraphScope")

val appModule = module {

    scope(NavGraphScope) {
        // Declare JobSharedViewModel within this scope
        viewModel { JobSharedViewModel() }
    }

    // Provide Moshi instance
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    // Provide Logging Interceptor
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY // For development/debugging
            } else {
                HttpLoggingInterceptor.Level.NONE // Disable logging in production
            }
        }
    }

    // Provide OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    // Provide Retrofit instance
    single {
        Retrofit.Builder()
            .baseUrl("https://jooble.org/api/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    // Provide JoobleApiService
    single<JoobleApiService> {
        get<Retrofit>().create(JoobleApiService::class.java)
    }

    // Provide JobsRepository
    single<JobsRepository> {
        JobsRepositoryImpl(get())
    }

    // Provide JobsViewModel
    viewModel {
        JobsViewModel(get())
    }


    // Provide SearchViewModel
    viewModel {
        SearchViewModel()
    }
}
