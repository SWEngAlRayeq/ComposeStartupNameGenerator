package app.startup_name_generator.domain.repo

import app.startup_name_generator.data.model.StartupResponse

interface StartupRepository {
    suspend fun getStartupName(): StartupResponse
}