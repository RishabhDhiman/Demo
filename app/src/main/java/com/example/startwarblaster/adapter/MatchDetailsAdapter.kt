package com.example.startwarblaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.startwarblaster.R
import com.example.startwarblaster.dataclass.MatchInfoData

class MatchDetailsAdapter(
    private val matchInfoData: List<MatchInfoData>,
) :
    RecyclerView.Adapter<MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match_details, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matchInfoData.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.player1.text = matchInfoData[position].player1.name
        holder.player2.text = matchInfoData[position].player2.name
        holder.score.text = holder.itemView.context.getString(
            R.string.score_placeholder,
            matchInfoData[position].player1.score.toString(),
            matchInfoData[position].player2.score.toString()
        )
    }

}

class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val player1: TextView = itemView.findViewById(R.id.tvPlayer1)
    val player2: TextView = itemView.findViewById(R.id.tvPlayer2)
    val score: TextView = itemView.findViewById(R.id.tvScore)
}