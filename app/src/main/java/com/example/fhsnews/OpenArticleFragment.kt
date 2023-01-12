package com.example.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fhsnews.databinding.FragmentOpenArticleBinding
import com.example.fhsnews.network.FHSNewsApi
import com.example.fhsnews.overview.OverviewViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

// TODO: Fetch weather at FHS on date of article

private var articleId by Delegates.notNull<Int>()

class OpenArticleFragment : Fragment() {
    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        articleId = OpenArticleFragmentArgs.fromBundle(requireArguments()).articleId

        val binding = FragmentOpenArticleBinding.inflate(inflater)
        Log.d(TAG, "onCreateView: getting article $articleId")

        viewLifecycleOwner.lifecycleScope.launch {
            binding.article = FHSNewsApi.retrofitService.getArticle(articleId)
        }
        return binding.root

    }
}
