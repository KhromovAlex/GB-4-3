package com.example.gb_4_3.data.entity

import com.google.android.gms.maps.model.LatLng

data class MarkerEntity (
    val id: String,
    val name: String,
    val description: String,
    val position: LatLng
)
