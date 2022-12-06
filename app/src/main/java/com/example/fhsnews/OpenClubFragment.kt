package com.example.fhsnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fhsnews.databinding.FragmentOpenClubBinding
import com.example.fhsnews.model.Club
import kotlin.properties.Delegates

private var _binding: FragmentOpenClubBinding? = null
private val binding get() = _binding!!
private var clubId by Delegates.notNull<Int>()

class OpenClubFragment : Fragment() {

    private val clubsList: List<Club> = com.example.fhsnews.data.DataSource.clubsList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        clubId = OpenClubFragmentArgs.fromBundle(requireArguments()).clubId
        _binding = FragmentOpenClubBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var thisClub = clubsList[clubId]
        binding.openClubThumbnail.setImageResource(thisClub.thumbnail)
        binding.openClubHeadline.text = thisClub.name
        binding.openClubContent.text = thisClub.text
        binding.openClubSubtitle.text = thisClub.subtitle
        return
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}