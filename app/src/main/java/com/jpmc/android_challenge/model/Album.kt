package com.jpmc.android_challenge.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null
)
