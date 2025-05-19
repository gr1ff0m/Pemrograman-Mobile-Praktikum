package com.example.scrollablelist2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class BookViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            // Return instance dari BookViewModel
            return BookViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
