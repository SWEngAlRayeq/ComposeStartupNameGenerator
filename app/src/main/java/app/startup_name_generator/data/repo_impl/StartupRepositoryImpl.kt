package app.startup_name_generator.data.repo_impl

import app.startup_name_generator.data.model.StartupResponse
import app.startup_name_generator.data.remote.StartupApi
import app.startup_name_generator.domain.repo.StartupRepository
import javax.inject.Inject

class StartupRepositoryImpl @Inject constructor(
    private val api: StartupApi
) : StartupRepository {
    override suspend fun getStartupName(): StartupResponse {
        return api.getStartupName()
    }
}