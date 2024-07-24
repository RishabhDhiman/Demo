package com.example.startwarblaster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.startwarblaster.R
import com.example.startwarblaster.dataclass.PlayerAndPointsData
import com.example.startwarblaster.utils.Callback

class PlayersAdapter(private val playerAndPointsDataList: List<PlayerAndPointsData>, val clickEventListener: Callback<PlayerAndPointsData>) :
    RecyclerView.Adapter<PlayersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_points_table, parent, false)
        return PlayersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerAndPointsDataList.size
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        Glide.with(holder.itemView).load(playerAndPointsDataList[position].iconURL).skipMemoryCache(false)
            .into(holder.imageView)
        holder.name.text = playerAndPointsDataList[position].name
        holder.score.text = playerAndPointsDataList[position].score.toString()
        holder.view.setOnClickListener { clickEventListener.onCall(playerAndPointsDataList[position]) }
    }

}

class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.ivIcon)
    val name: TextView = itemView.findViewById(R.id.tvName)
    val score: TextView = itemView.findViewById(R.id.tvScore)
    val view = itemView
}