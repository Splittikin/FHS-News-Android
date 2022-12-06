package com.example.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
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
        _binding = FragmentOpenArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var filteredNewsList: List<Article> = newsList.filter { it.articleId == articleId }
        if (filteredNewsList.size > 1) {
            error("More than one article with this articleId")
        }
        var thisArticle = filteredNewsList[0]
        binding.openArticleTopperIcon.setImageResource(thisArticle.topperIcon)
        binding.openArticleTopperText.text = thisArticle.topperText
        binding.openArticleThumbnail.setImageResource(thisArticle.articleThumbnail)
        binding.openArticleHeadline.text = thisArticle.headline
        binding.openArticleContent.text = thisArticle.text
        binding.openArticlePostedTime.text = thisArticle.postedTime.toString()
        binding.openArticleSubtitle.text = thisArticle.subtitle
        binding.openArticleAuthorName.text = thisArticle.author

        // hide any empty article elements
        if (thisArticle.topperIcon == 0 && thisArticle.topperText != "") {
            val topperTextConstraintParam = ConstraintSet()
            topperTextConstraintParam.clone(binding.openArticleConstraintLayout)
            topperTextConstraintParam.connect(R.id.openArticleTopperText, ConstraintSet.START, R.id.openArticleConstraintLayout, ConstraintSet.START)
            topperTextConstraintParam.connect(R.id.openArticleThumbnail, ConstraintSet.TOP, R.id.openArticleTopperText, ConstraintSet.BOTTOM)
            topperTextConstraintParam.applyTo(binding.openArticleConstraintLayout)
        }
        if (thisArticle.topperText == "" && thisArticle.topperIcon == 0) {
            val imgMarginParam =
                binding.openArticleThumbnail.layoutParams as ViewGroup.MarginLayoutParams
            imgMarginParam.setMargins(0, 0, 0, 0)
            binding.openArticleThumbnail.layoutParams = imgMarginParam

            val icoMarginParam =
                binding.openArticleTopperIcon.layoutParams as ViewGroup.MarginLayoutParams
            icoMarginParam.setMargins(0, 0, 0, 0)
            binding.openArticleTopperIcon.layoutParams = icoMarginParam
        }
        if (thisArticle.subtitle == "") {
            val subMarginParam =
                binding.openArticleSubtitle.layoutParams as ViewGroup.MarginLayoutParams
            subMarginParam.setMargins(0, 0, 0, 0)
            binding.openArticleSubtitle.layoutParams = subMarginParam

            val previewMarginParam =
                binding.openArticleContent.layoutParams as ViewGroup.MarginLayoutParams
            previewMarginParam.setMargins(8, 0, 8, 8)
            binding.openArticleContent.layoutParams = previewMarginParam
        }
        return
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}