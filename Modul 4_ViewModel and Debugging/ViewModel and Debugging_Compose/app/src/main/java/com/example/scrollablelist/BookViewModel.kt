package com.example.scrollablelist.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.scrollablelist.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class BookViewModel(private val query: String) : ViewModel() {

    private val _bookList = MutableStateFlow<List<Book>>(emptyList())
    val bookList: StateFlow<List<Book>> = _bookList

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?> = _event

    init {
        Timber.d("ViewModel initialized with query: $query")
        loadBooks(query)
    }

    private fun loadBooks(query: String) {
        viewModelScope.launch {
            // Simulasi mengambil data dari sumber data (misalnya database, API, dll)
            val books = listOf(
                Book(
                    1,
                    "Atomic Habits",
                    "https://m.media-amazon.com/images/I/91bYsX41DVL.jpg",
                    "https://www.goodreads.com/book/show/40121378-atomic-habits",
                    "Build good habits and break bad ones.",
                    "Atomic Habits by James Clear offers a proven framework for improving everyday life. Learn how tiny changes add up to remarkable results."
                ),
                Book(
                    2,
                    "Deep Work",
                    "https://m.media-amazon.com/images/I/91nujEwIpYL._SL1500_.jpg",
                    "https://www.goodreads.com/book/show/25744928-deep-work",
                    "Focused success in a distracted world.",
                    "Deep Work is a book about the benefits of intense focus and how to systematically train your mind and habits to achieve it."
                ),
                Book(
                    3,
                    "The Subtle Art of Not Giving a F*ck",
                    "https://m.media-amazon.com/images/I/71QKQ9mwV7L.jpg",
                    "https://www.goodreads.com/book/show/28257707-the-subtle-art-of-not-giving-a-f-ck",
                    "Counterintuitive approach to living a good life.",
                    "Mark Manson's book shows that life's struggles give it meaning, and teaches how to stop trying to be positive all the time."
                ),
                Book(
                    4,
                    "Thinking, Fast and Slow",
                    "https://m.media-amazon.com/images/I/61fdrEuPJwL._SL1500_.jpg",
                    "https://www.goodreads.com/book/show/11468377-thinking-fast-and-slow",
                    "Explores how we think, make decisions, and act.",
                    "Nobel laureate Daniel Kahneman explores two modes of thought: 'fast', intuitive thinking, and 'slow', deliberate thinking."
                ),
                Book(
                    5,
                    "Start With Why",
                    "https://m.media-amazon.com/images/I/71NBZIExBCL._SY466_.jpg",
                    "https://www.goodreads.com/book/show/7108725-start-with-why",
                    "Find your why and inspire others.",
                    "Simon Sinek explains how leaders can inspire cooperation, trust and change by focusing on the WHY behind their mission."
                ),
                Book(
                    6,
                    "The Power of Now",
                    "https://m.media-amazon.com/images/I/61Ij8nLooNL._SL1500_.jpg",
                    "https://www.amazon.com/Power-Now-Guide-Spiritual-Enlightenment/dp/1577314808",
                    "A guide to spiritual enlightenment.",
                    "Eckhart Tolle’s guide helps readers discover the importance of living in the present moment and letting go of the past and future."
                ),
                Book(
                    7,
                    "Can't Hurt Me",
                    "https://m.media-amazon.com/images/I/81gTRv2HXrL._SL1500_.jpg",
                    "https://www.goodreads.com/book/show/41721428-can-t-hurt-me",
                    "Master your mind and defy the odds.",
                    "David Goggins shares his incredible life story and teaches readers how to overcome pain, fear, and self-doubt to reach their full potential."
                ),
                Book(
                    8,
                    "Educated",
                    "https://m.media-amazon.com/images/I/81WojUxbbFL.jpg",
                    "https://www.goodreads.com/book/show/35133922-educated",
                    "A memoir of transformation through education.",
                    "Tara Westover tells her story of growing up in a strict and abusive household in rural Idaho and how she escaped through learning and education."
                )
            )

            // Log saat data masuk ke dalam list
            Timber.d("Books loaded: $books")
            _bookList.value = books
        }
    }

    fun onItemClicked(book: Book) {
        // Log saat detail tombol ditekan
        Timber.d("Detail button clicked for book: ${book.title}")
        _event.value = Event.NavigateToDetail(book)
    }

    fun onWebButtonClicked(book: Book) {
        // Log saat tombol Explicit Intent ditekan
        Timber.d("Web button clicked for book: ${book.title}")
        _event.value = Event.OpenWebUrl(book.webUrl)
    }

    sealed class Event {
        data class NavigateToDetail(val book: Book) : Event()
        data class OpenWebUrl(val url: String) : Event()
    }
}

class BookViewModelFactory(private val query: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(query) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
