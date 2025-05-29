package com.example.scrollablelist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SharedPreferenceViewModel(private val preferenceManager: PreferenceManager) : ViewModel() {

    val isDarkMode: StateFlow<Boolean> = preferenceManager.isDarkMode
        .stateIn(
            CoroutineScope(Dispatchers.Main),
            SharingStarted.Eagerly,
            false
        )

    fun toggleDarkMode() {
        val current = isDarkMode.value
        preferenceManager.setDarkMode(!current)
    }
}
