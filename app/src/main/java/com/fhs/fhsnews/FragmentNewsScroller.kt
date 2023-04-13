package com.fhs.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fhs.fhsnews.adapter.FeedDataAdapter
import com.fhs.fhsnews.databinding.FragmentNewsScrollerBinding
import com.fhs.fhsnews.overview.OverviewViewModel

class FragmentNewsScroller : Fragment() {


	private val viewModel: OverviewViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		val binding = FragmentNewsScrollerBinding.inflate(inflater)

		binding.lifecycleOwner = this
		binding.viewModel = viewModel
		binding.newsRecycler.adapter = FeedDataAdapter()
		viewModel.getArticlesHomeFeed()

		return binding.root
	}
}