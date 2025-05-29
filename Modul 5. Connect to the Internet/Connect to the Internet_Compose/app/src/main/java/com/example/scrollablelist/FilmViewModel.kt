package com.example.scrollablelist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scrollablelist.data.FilmRepository
import com.example.scrollablelist.model.Film
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class FilmViewModel(
    private val repository: FilmRepository
) : ViewModel() {

    private val _filmList = MutableStateFlow<List<Film>>(emptyList())
    val filmList: StateFlow<List<Film>> = _filmList.asStateFlow()

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> = _event.asStateFlow()

    init {
        loadFilmsFromApi()
    }

    private fun loadFilmsFromApi() {
        viewModelScope.launch {
            try {
                val films = repository.getPopularFilms()
                _filmList.value = films as List<Film>
            } catch (e: Exception) {
                Timber.e("Gagal memuat film dari API: ${e.message}")
            }
        }
    }

    fun onItemClicked(film: Film) {
        _event.value = Event.NavigateToDetail(film)
    }

    fun onWebButtonClicked(url: String) {
        _event.value = Event.OpenWebUrl(url)
    }

    fun clearEvent() {
        _event.value = null
    }

    sealed class Event {
        data class NavigateToDetail(val film: Film) : Event()
        data class OpenWebUrl(val url: String) : Event()
    }
}
