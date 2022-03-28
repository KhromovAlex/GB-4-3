package com.example.gb_4_3.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_4_3.R
import com.example.gb_4_3.data.entity.MarkerEntity

class MarkersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var lat: TextView = itemView.findViewById(R.id.latitude)
    private var long: TextView = itemView.findViewById(R.id.longitude)
    private var name: TextView = itemView.findViewById(R.id.name)
    private var description: TextView = itemView.findViewById(R.id.description)

    fun bind(marker: MarkerEntity) {
        lat.text = marker.position.latitude.toString()
        long.text = marker.position.longitude.toString()
        name.text = marker.name
        description.text = marker.description
    }
}
