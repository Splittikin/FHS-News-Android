package com.fhs.fhsnews.adapter

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.ClubScrollerFragmentDirections
import com.fhs.fhsnews.databinding.CardClubBinding
import com.fhs.fhsnews.model.Club

// Adapter to find and inflate cards for clubs

class ClubDataAdapter : ListAdapter<Club, ClubDataAdapter.CardClubViewHolder>(
    ClubDataAdapter
) {

    // TODO: Filter by tag

    class CardClubViewHolder(private var binding: CardClubBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(club: Club) {
            binding.club = club
            binding.clubCardConstraintLayout.setOnClickListener {
                Log.d(ContentValues.TAG, "onBindViewbinding: club click")
                val action =
                    ClubScrollerFragmentDirections.actionClubScrollerFragmentToOpenClubFragment(
                        clubId = club.clubId
                    )
                binding.root.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Club>() {
        override fun areItemsTheSame(oldItem: Club, newItem: Club): Boolean {
            return oldItem.clubId == newItem.clubId
        }

        override fun areContentsTheSame(oldItem: Club, newItem: Club): Boolean {
            return oldItem.clubText == newItem.clubText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardClubViewHolder {
        return CardClubViewHolder(
            CardClubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardClubViewHolder, position: Int) {
        val thisClub = getItem(position)
        holder.bind(thisClub)
    }
}

