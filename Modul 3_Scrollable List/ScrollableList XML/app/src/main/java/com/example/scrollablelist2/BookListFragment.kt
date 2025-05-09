package com.example.scrollablelist2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.scrollablelist2.data.Book
import com.example.scrollablelist2.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {
    private lateinit var binding: FragmentBookListBinding

    private val books = listOf(
        Book(
            id = 1,
            title = "Atomic Habits",
            imageUrl = "https://m.media-amazon.com/images/I/91bYsX41DVL.jpg",
            webUrl = "https://www.goodreads.com/book/show/40121378-atomic-habits",
            shortDescription = "Build good habits and break bad ones.",
            fullDescription = "Atomic Habits by James Clear offers a proven framework for improving everyday life. " +
                    "Learn how tiny changes add up to remarkable results."
        ),
        Book(
            id = 2,
            title = "Deep Work",
            imageUrl = "https://m.media-amazon.com/images/I/91nujEwIpYL._SL1500_.jpg",
            webUrl = "https://www.goodreads.com/book/show/25744928-deep-work",
            shortDescription = "Focused success in a distracted world.",
            fullDescription = "Deep Work is a book about the benefits of intense focus and how to systematically train " +
                    "your mind and habits to achieve it."
        ),
        Book(
            id = 3,
            title = "The Subtle Art of Not Giving a F*ck",
            imageUrl = "https://m.media-amazon.com/images/I/71QKQ9mwV7L.jpg",
            webUrl = "https://www.goodreads.com/book/show/28257707-the-subtle-art-of-not-giving-a-f-ck",
            shortDescription = "Counterintuitive approach to living a good life.",
            fullDescription = "Mark Manson's book shows that life's struggles give it meaning, and teaches how to stop " +
                    "trying to be positive all the time."
        ),
        Book(
            id = 4,
            title = "Thinking, Fast and Slow",
            imageUrl = "https://m.media-amazon.com/images/I/61fdrEuPJwL._SL1500_.jpg",
            webUrl = "https://www.goodreads.com/book/show/11468377-thinking-fast-and-slow",
            shortDescription = "Explores how we think, make decisions, and act.",
            fullDescription = "Nobel laureate Daniel Kahneman explores two modes of thought: 'fast', intuitive thinking, " +
                    "and 'slow', deliberate thinking."
        ),
        Book(
            id = 5,
            title = "Start With Why",
            imageUrl = "https://m.media-amazon.com/images/I/71NBZIExBCL._SY466_.jpg",
            webUrl = "https://www.goodreads.com/book/show/7108725-start-with-why",
            shortDescription = "Find your why and inspire others.",
            fullDescription = "Simon Sinek explains how leaders can inspire cooperation, trust and change by focusing on " +
                    "the WHY behind their mission."
        ),
        Book(
            id = 6,
            title = "The Power of Now",
            imageUrl = "https://m.media-amazon.com/images/I/61Ij8nLooNL._SL1500_.jpg",
            webUrl = "https://www.amazon.com/Power-Now-Guide-Spiritual-Enlightenment/dp/1577314808",
            shortDescription = "A guide to spiritual enlightenment.",
            fullDescription = "Eckhart Tolle’s guide helps readers discover the importance of living in the present " +
                    "moment and letting go of the past and future."
        ),
        Book(
            id = 7,
            title = "Can't Hurt Me",
            imageUrl = "https://m.media-amazon.com/images/I/81gTRv2HXrL._SL1500_.jpg",
            webUrl = "https://www.goodreads.com/book/show/41721428-can-t-hurt-me",
            shortDescription = "Master your mind and defy the odds.",
            fullDescription = "David Goggins shares his incredible life story and teaches readers how to overcome pain, " +
                    "fear, and self-doubt to reach their full potential."
        ),
        Book(
            id = 8,
            title = "Educated",
            imageUrl = "https://m.media-amazon.com/images/I/81WojUxbbFL.jpg",
            webUrl = "https://www.goodreads.com/book/show/35133922-educated",
            shortDescription = "A memoir of transformation through education.",
            fullDescription = "Tara Westover tells her story of growing up in a strict and abusive household in rural Idaho " +
                    "and how she escaped through learning and education."
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBookListBinding.inflate(inflater, container, false)
        binding.bookRecyclerView.adapter = BookAdapter(books)
        return binding.root
    }
}
