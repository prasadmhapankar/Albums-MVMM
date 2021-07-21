package com.jpmc.android_challenge.data.source

import retrofit2.Response
import com.jpmc.android_challenge.utils.Result

abstract class BaseDataSource {
    protected suspend fun <T>getResult(call:suspend()->Response<T>): Result<T> {
        try {
            val response= call()
            if(response.isSuccessful) {
                val body= response.body()
                if(body != null) return Result.success(body)
            }
            return Result.error(response.message(),null)
        } catch (exception:Exception) {
            return Result.error(exception.message?:exception.toString(),null)
        }
    }
}