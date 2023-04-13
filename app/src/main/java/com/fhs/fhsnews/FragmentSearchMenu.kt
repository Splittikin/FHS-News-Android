package com.fhs.fhsnews

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
import com.fhs.fhsnews.adapter.SearchDataAdapter
import com.fhs.fhsnews.databinding.FragmentSearchMenuBinding

private var _binding: FragmentSearchMenuBinding? = null
private val binding get() = _binding!!

class FragmentSearchMenu : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentSearchMenuBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val recyclerView = binding.searchResultsRecycler
		recyclerView.adapter = SearchDataAdapter("")
		recyclerView.layoutManager = LinearLayoutManager(context)
		(activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.title_search)

		val searchView = binding.searchView
		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				Log.d(TAG, "onQueryTextSubmit: search for \"$query\"")
				if (query != null) {
					recyclerView.adapter = SearchDataAdapter(query)
				} else {
					recyclerView.adapter = SearchDataAdapter("")
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