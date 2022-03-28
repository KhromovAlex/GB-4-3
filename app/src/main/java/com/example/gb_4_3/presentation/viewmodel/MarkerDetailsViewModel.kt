package com.example.gb_4_3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_4_3.data.entity.MarkerEntity
import com.example.gb_4_3.data.repository.MapsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MarkerDetailsViewModel(
    private val mapsRepository: MapsRepository,
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _liveData = MutableLiveData<MarkerEntity?>()
    val liveData: LiveData<MarkerEntity?> = _liveData

    fun getMarkerById(id: String) {
        val currentMarker = mapsRepository.watchMarkers().value?.find { it.id == id }
        _liveData.postValue(currentMarker)
    }

    fun updateMarker(name: String, description: String) {
        val currentMarker = liveData.value
        if (currentMarker != null) {
            mapsRepository.updateMarker(currentMarker.copy(name = name, description = description))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
