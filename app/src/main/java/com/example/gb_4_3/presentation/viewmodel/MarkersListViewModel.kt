package com.example.gb_4_3.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_4_3.data.entity.MarkerEntity
import com.example.gb_4_3.data.repository.MapsRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers

class MarkersListViewModel(
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

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
