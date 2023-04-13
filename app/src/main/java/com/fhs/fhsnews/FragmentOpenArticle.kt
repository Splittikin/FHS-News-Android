package com.fhs.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fhs.fhsnews.databinding.FragmentOpenArticleBinding
import com.fhs.fhsnews.network.FHSNewsApi
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

private var articleId by Delegates.notNull<Int>()

class FragmentOpenArticle : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		articleId = FragmentOpenArticleArgs.fromBundle(requireArguments()).articleId

		val binding = FragmentOpenArticleBinding.inflate(inflater)
		Log.d(TAG, "onCreateView: getting article $articleId")

		viewLifecycleOwner.lifecycleScope.launch {
			binding.article = FHSNewsApi.retrofitService.getArticle(articleId)
		}
		return binding.root

	}
}
