package com.example.scrollablelist2.ui

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollablelist2.data.Book
import com.example.scrollablelist2.databinding.ItemBookBinding

class BookAdapter(private val bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.binding.apply {
            titleTextView.text = book.title
            descTextView.text = book.shortDescription
            Glide.with(imageView.context).load(book.imageUrl).into(imageView)

            buttonOpenLink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.webUrl))
                it.context.startActivity(intent)
            }

            buttonDetails.setOnClickListener {
                val action = BookListFragmentDirections
                    .actionBookListFragmentToBookDetailFragment(
                        book.title,
                        book.fullDescription,
                        book.imageUrl
                    )
                it.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount() = bookList.size
}