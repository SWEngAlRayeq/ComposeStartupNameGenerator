package app.startup_name_generator.domain.usecase

import app.startup_name_generator.data.model.StartupResponse
import app.startup_name_generator.domain.repo.StartupRepository
import javax.inject.Inject

class GetStartupNameUseCase @Inject constructor(
    private val repository: StartupRepository
) {
    suspend operator fun invoke(): StartupResponse = repository.getStartupName()
}