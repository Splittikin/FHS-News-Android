package com.example.fhsnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fhsnews.ClubScrollerFragmentDirections
import com.example.fhsnews.R
import com.example.fhsnews.data.DataSource
import com.example.fhsnews.model.Club

// Adapter to find and inflate cards for clubs

class ClubCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // TODO: Filter by tag

    private val clubsList: List<Club> = com.example.fhsnews.data.DataSource.clubsList

    inner class ClubCardViewHolder(val view: View?) : RecyclerView.ViewHolder(view!!) {
        var clubThumbnail: ImageView = view!!.findViewById(R.id.clubThumbnail)
        var clubName: TextView = view!!.findViewById(R.id.clubName)
        var postedTime: TextView = view!!.findViewById(R.id.clubPostedTime)
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
        val thisClub = DataSource.clubsList[position]
        (holder as ClubCardAdapter.ClubCardViewHolder).clubThumbnail.setImageResource(thisClub.thumbnail)
        holder.clubName.text = thisClub.name
        holder.clubSubtitle.text = thisClub.subtitle
        holder.postedTime.text = thisClub.postedTime.toString()
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

