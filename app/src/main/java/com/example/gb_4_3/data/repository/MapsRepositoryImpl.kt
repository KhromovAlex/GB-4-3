package com.example.gb_4_3.data.repository

import com.example.gb_4_3.data.entity.MarkerEntity
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MapsRepositoryImpl : MapsRepository {
    private val markers: BehaviorSubject<List<MarkerEntity>> = BehaviorSubject.create()

    override fun watchMarkers(): BehaviorSubject<List<MarkerEntity>> = markers

    override fun updateMarkers(list: List<MarkerEntity>) {
        markers.onNext(list)
    }

    override fun updateMarker(marker: MarkerEntity) {
        val newList = mutableListOf<MarkerEntity>()
        markers.value?.let { newList.addAll(it) }
        val pos = newList.indexOfFirst { it.id == marker.id }

        if (pos != -1) {
            newList[pos] = marker
            markers.onNext(newList)
        }
    }
}
