package com.example.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.adapter.NewsCardAdapter
import com.example.fhsnews.databinding.FragmentNewsScrollerBinding

private var _binding: FragmentNewsScrollerBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView

class NewsScrollerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: fragment created")
        _binding = FragmentNewsScrollerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: fragment created")
        val recyclerView = binding.newsRecycler
        recyclerView.adapter = NewsCardAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        Log.d(TAG, "onViewCreated: ${recyclerView.adapter}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}