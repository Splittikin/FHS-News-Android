package com.fhs.fhsnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fhs.fhsnews.ClubScrollerFragmentDirections
import com.fhs.fhsnews.R
import com.fhs.fhsnews.data.clubs.ClubsList
import com.fhs.fhsnews.model.Club

// Adapter to find and inflate cards for clubs

class ClubCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // TODO: Filter by tag

    private val clubsList: List<Club> = com.fhs.fhsnews.data.clubs.ClubsList.clubsList

    inner class ClubCardViewHolder(val view: View?) : RecyclerView.ViewHolder(view!!) {
        var clubThumbnail: ImageView = view!!.findViewById(R.id.clubThumbnail)
        var clubName: TextView = view!!.findViewById(R.id.clubName)
        var clubSubtitle: TextView = view!!.findViewById(R.id.clubSubtitle)
        var clubInfo: TextView = view!!.findViewById(R.id.clubInfo)
        var clubCardConstraintLayout: ConstraintLayout =
            view!!.findViewById(R.id.clubCardConstraintLayout)
    }

    override fun getItemCount(): Int {
        return clubsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.club_card, parent, false)
        return ClubCardViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thisClub = ClubsList.clubsList[position]
        (holder as ClubCardAdapter.ClubCardViewHolder).clubThumbnail.setImageResource(thisClub.thumbnail)
        holder.clubName.text = thisClub.name
        holder.clubSubtitle.text = thisClub.subtitle
        holder.clubInfo.text = thisClub.text
        holder.clubCardConstraintLayout.setOnClickListener {
            val action =
                ClubScrollerFragmentDirections.actionClubScrollerFragmentToOpenClubFragment(
                    clubId = position
                )
            holder.view!!.findNavController().navigate(action)
        }
    }
}

