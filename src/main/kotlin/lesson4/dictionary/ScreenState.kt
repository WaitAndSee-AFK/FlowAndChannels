package org.example.lesson4.dictionary

sealed interface ScreenState {
    data object Initial : ScreenState
    data object Loading : ScreenState
    data object NotFound : ScreenState
    data object Error : ScreenState
    data class DefinitionsLoaded(val definitions: List<String>) : ScreenState
}