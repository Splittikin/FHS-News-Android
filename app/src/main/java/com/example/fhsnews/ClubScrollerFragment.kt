package com.example.fhsnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.adapter.ClubCardAdapter
import com.example.fhsnews.adapter.NewsCardAdapter
import com.example.fhsnews.databinding.FragmentClubsScrollerBinding
import com.example.fhsnews.databinding.FragmentNewsScrollerBinding

private var _binding: FragmentClubsScrollerBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView

class ClubScrollerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: fragment created")
        _binding = FragmentClubsScrollerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.clubRecycler
        recyclerView.adapter = ClubCardAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        (activity as AppCompatActivity).supportActionBar?.title = "FHS Clubs"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}