package org.assessment.myapplication.model

import com.squareup.moshi.Json

data class Jam(
    val id: String,
    @Json(name = "nama")val nama: String,
    @Json(name = "pemilik")val pemilik: String,
    @Json(name = "image_id") val imageId: String
)