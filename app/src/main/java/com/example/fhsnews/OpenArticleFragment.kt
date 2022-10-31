package com.example.fhsnews

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fhsnews.adapter.NewsCardAdapter
import com.example.fhsnews.databinding.FragmentOpenArticleBinding
import com.example.fhsnews.model.Article
import kotlin.properties.Delegates

private var _binding: FragmentOpenArticleBinding? = null
private val binding get() = _binding!!
private var articleId by Delegates.notNull<Int>()

class OpenArticleFragment : Fragment() {

    private val newsList: List<Article> = com.example.fhsnews.data.DataSource.newsList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        articleId = OpenArticleFragmentArgs.fromBundle(requireArguments()).articleId
        Log.d(ContentValues.TAG, "onCreateView: fragment created $articleId")
        _binding = FragmentOpenArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: fragment created $articleId")
        var thisArticle = newsList[articleId]
        binding.openArticleTopperIcon.setImageResource(thisArticle.topperIcon)
        binding.openArticleTopperText.text = thisArticle.topperText
        binding.openArticleThumbnail.setImageResource(thisArticle.articleThumbnail)
        binding.openArticleHeadline.text = thisArticle.headline
        binding.openArticleContent.text = thisArticle.text
        binding.openArticlePostedTime.text = thisArticle.postedTime.toString()
        binding.openArticleSubtitle.text = thisArticle.subtitle
        binding.openArticleAuthorName.text = thisArticle.author
        return
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}