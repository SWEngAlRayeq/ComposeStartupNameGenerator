package app.startup_name_generator.di

import app.startup_name_generator.data.remote.StartupApi
import app.startup_name_generator.data.repo_impl.StartupRepositoryImpl
import app.startup_name_generator.domain.repo.StartupRepository
import app.startup_name_generator.domain.usecase.GetStartupNameUseCase
import kotlin.jvm.java
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
object AppModule {

    @Provides
    fun provideBaseUrl() = "https://itsthisforthat.com/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideStartupApi(okHttpClient: OkHttpClient, baseUrl: String): StartupApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StartupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: StartupApi): StartupRepository =
        StartupRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideUseCase(repo: StartupRepository): GetStartupNameUseCase =
        GetStartupNameUseCase(repo)


}
