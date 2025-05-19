package com.example.scrollablelist2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollablelist2.data.Book
import com.example.scrollablelist2.databinding.ItemBookBinding

class BookAdapter(private var bookList: List<Book>, private val onBookClicked: (Book) -> Unit) :
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

            root.setOnClickListener {
                onBookClicked(book)
            }
        }
    }

    override fun getItemCount(): Int = bookList.size

    fun updateBooks(newBooks: List<Book>) {
        bookList = newBooks
        notifyDataSetChanged()
    }
}
