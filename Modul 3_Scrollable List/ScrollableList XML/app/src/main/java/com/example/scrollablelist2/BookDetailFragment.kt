package com.example.scrollablelist2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.scrollablelist2.databinding.FragmentBookDetailBinding

class BookDetailFragment : Fragment() {

    private lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        val args = BookDetailFragmentArgs.fromBundle(requireArguments())
        binding.detailTitle.text = args.title
        binding.detailDescription.text = args.description

        Glide.with(this)
            .load(args.imageUrl)
            .into(binding.detailImageView)

        return binding.root
    }
}
