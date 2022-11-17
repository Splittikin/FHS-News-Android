package com.example.fhsnews

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.adapter.EventsViewAdapter
import com.example.fhsnews.databinding.FragmentEventsViewBinding

private var _binding: FragmentEventsViewBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView

class EventsViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventsViewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.eventsRecycler
        recyclerView.adapter = EventsViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        (activity as AppCompatActivity).supportActionBar?.title = "FHS Events"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}