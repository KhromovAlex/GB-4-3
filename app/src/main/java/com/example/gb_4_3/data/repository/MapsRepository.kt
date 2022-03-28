package com.example.gb_4_3.data.repository

import com.example.gb_4_3.data.entity.MarkerEntity
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface MapsRepository {
    fun watchMarkers() : BehaviorSubject<List<MarkerEntity>>

    fun updateMarkers(list: List<MarkerEntity>)

    fun updateMarker(marker: MarkerEntity)
}
