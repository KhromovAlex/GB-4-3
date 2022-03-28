package com.example.gb_4_3.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_4_3.R
import com.example.gb_4_3.data.entity.MarkerEntity

class MarkersListAdapter(
    private val delegate: (id: String) -> Unit
) :
    RecyclerView.Adapter<MarkersListViewHolder>() {
    private val list: MutableList<MarkerEntity> = mutableListOf()

    fun submitList(newList: List<MarkerEntity>) {
        val callback =
            MarkersListDiffUtil(
                list,
                newList
            )
        val result = DiffUtil.calculateDiff(callback)
        list.clear()
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkersListViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent,
            false
        )
        return MarkersListViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MarkersListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            delegate(list[position].id)
        }
        holder.bind(list[position])
        holder.itemView.tag = list[position]
    }

    override fun getItemCount(): Int = list.size

}
