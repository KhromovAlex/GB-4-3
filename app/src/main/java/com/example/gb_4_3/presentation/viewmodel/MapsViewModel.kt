package com.example.gb_4_3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_4_3.data.entity.MarkerEntity
import com.example.gb_4_3.data.repository.MapsRepository
import com.google.android.gms.maps.model.LatLng
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class MapsViewModel(
    private val mapsRepository: MapsRepository,
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _liveData = MutableLiveData<List<MarkerEntity>>()
    val liveData: LiveData<List<MarkerEntity>> = _liveData

    fun watchMarkers() {
        disposables += mapsRepository.watchMarkers()
            .subscribeOn(Schedulers.io())
            .subscribe {
                _liveData.postValue(it)
            }
    }

    fun addMarker(position: LatLng) {
        val currentTime: Date = Calendar.getInstance().time
        val newList = mutableListOf<MarkerEntity>()
        liveData.value?.let { newList.addAll(it) }
        newList.add(MarkerEntity(id = "${currentTime.time}${Random().nextDouble()}", name = liveData.value?.size.toString(), position = position, description = "description"))
        mapsRepository.updateMarkers(newList)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
