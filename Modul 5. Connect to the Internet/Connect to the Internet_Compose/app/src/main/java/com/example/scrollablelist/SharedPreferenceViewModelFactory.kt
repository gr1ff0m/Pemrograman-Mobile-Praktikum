package com.example.scrollablelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SharedPreferenceViewModelFactory(
    private val preferenceManager: PreferenceManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedPreferenceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedPreferenceViewModel(preferenceManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
