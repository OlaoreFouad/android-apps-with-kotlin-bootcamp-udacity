package dev.olaore.realestateonmars.models

import com.squareup.moshi.Json

data class MarsProperty (

    val price: Double,

    val id: String,

    val type: String,

    @Json(name = "img_src")
    val imgSrcUrl: String

)