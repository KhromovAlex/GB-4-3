package com.example.gb_4_3.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.gb_4_3.data.entity.MarkerEntity

class MarkersListDiffUtil(
    private val oldList: List<MarkerEntity>,
    private val newList: List<MarkerEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
