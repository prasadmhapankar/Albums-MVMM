package com.jpmc.android_challenge.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int? = null,
    @SerializedName("title") val title: String? = null
)
