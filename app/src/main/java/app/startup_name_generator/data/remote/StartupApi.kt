package app.startup_name_generator.data.remote

import app.startup_name_generator.data.model.StartupResponse
import retrofit2.http.GET

interface StartupApi {

    @GET("api.php?json")
    suspend fun getStartupName(): StartupResponse

}