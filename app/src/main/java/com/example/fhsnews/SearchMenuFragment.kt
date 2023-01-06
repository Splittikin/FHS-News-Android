package com.example.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fhsnews.adapter.SearchMenuAdapter
import com.example.fhsnews.databinding.FragmentSearchMenuBinding

private var _binding: FragmentSearchMenuBinding? = null
private val binding get() = _binding!!

class SearchMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchMenuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.searchResultsRecycler
        recyclerView.adapter = SearchMenuAdapter("")
        recyclerView.layoutManager = LinearLayoutManager(context)
        (activity as AppCompatActivity).supportActionBar?.title = "Search"

        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "onQueryTextSubmit: search for \"$query\"")
                if (query != null) {
                    recyclerView.adapter = SearchMenuAdapter(query)
                } else {
                    recyclerView.adapter = SearchMenuAdapter("")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: Find first 3 results and show them as the user types
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}