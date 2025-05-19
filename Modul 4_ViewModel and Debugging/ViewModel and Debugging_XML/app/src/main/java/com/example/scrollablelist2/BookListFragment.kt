package com.example.scrollablelist2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.scrollablelist2.databinding.FragmentBookListBinding
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class BookListFragment : Fragment() {

    private lateinit var binding: FragmentBookListBinding
    private val viewModel: BookViewModel by activityViewModels { BookViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBookListBinding.inflate(inflater, container, false)

        val adapter = BookAdapter(emptyList()) { book ->
            viewModel.onBookSelected(book)
        }
        binding.bookRecyclerView.adapter = adapter

        // Mengamati perubahan daftar buku
        lifecycleScope.launchWhenStarted {
            viewModel.books.collect { books ->
                Timber.d("Books data collected: ${books.size}")
                adapter.updateBooks(books)
            }
        }

        return binding.root
    }
}
