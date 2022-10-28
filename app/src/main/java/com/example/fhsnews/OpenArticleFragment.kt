package com.example.fhsnews

import android.content.ContentValues
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

private var _binding: FragmentOpenArticleBinding? = null
private val binding get() = _binding!!

class OpenArticleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(ContentValues.TAG, "onCreateView: fragment created")
        _binding = FragmentOpenArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        return
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}