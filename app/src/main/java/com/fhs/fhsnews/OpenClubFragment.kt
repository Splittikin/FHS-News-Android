package com.fhs.fhsnews

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fhs.fhsnews.databinding.FragmentOpenClubBinding
import com.fhs.fhsnews.network.FHSNewsApi
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

private var clubId by Delegates.notNull<Int>()

class OpenClubFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        clubId = OpenClubFragmentArgs.fromBundle(requireArguments()).clubId

        val binding = FragmentOpenClubBinding.inflate(inflater)
        Log.d(ContentValues.TAG, "onCreateView: getting club $clubId")

        viewLifecycleOwner.lifecycleScope.launch {
            binding.club = FHSNewsApi.retrofitService.getClub(clubId)
        }
        return binding.root

    }
}