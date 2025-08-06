package app.startup_name_generator.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.startup_name_generator.domain.usecase.GetStartupNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val getStartupNameUseCase: GetStartupNameUseCase
): ViewModel() {

    var idea by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    init {
        getIdea()
    }

    fun getIdea() {
        viewModelScope.launch {
            isLoading = true
            try {
                val res = getStartupNameUseCase()
                idea = "It's like ${res.thisThing} for ${res.that}."
            }catch (e: Exception) {
                idea = "Oops! Something went wrong."
            } finally {
                isLoading = false
            }
        }
    }

}