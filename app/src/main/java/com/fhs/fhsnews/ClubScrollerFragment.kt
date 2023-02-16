package com.fhs.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fhs.fhsnews.adapter.ClubDataAdapter
import com.fhs.fhsnews.databinding.FragmentClubsScrollerBinding
import com.fhs.fhsnews.overview.OverviewViewModel

class ClubScrollerFragment : Fragment() {


    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentClubsScrollerBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.clubRecycler.adapter = ClubDataAdapter()
        viewModel.getClubsClubFeed()

        return binding.root
    }
}