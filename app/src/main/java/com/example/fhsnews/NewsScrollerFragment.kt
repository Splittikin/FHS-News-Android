package com.example.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.adapter.NewsCardAdapter
import com.example.fhsnews.databinding.ActivityMainBinding
import com.example.fhsnews.databinding.FragmentNewsScrollerBinding

private var _binding: FragmentNewsScrollerBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView

class NewsScrollerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsScrollerBinding.inflate(inflater, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.NewsRecycler
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = NewsCardAdapter()

        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}