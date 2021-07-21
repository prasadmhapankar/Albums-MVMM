package com.jpmc.android_challenge.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jpmc.android_challenge.db.entity.AlbumEntity

class Converter {

    @TypeConverter
    fun fromListToString(list: List<AlbumEntity>?) : String{
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToList(value: String?) : List<AlbumEntity> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

}