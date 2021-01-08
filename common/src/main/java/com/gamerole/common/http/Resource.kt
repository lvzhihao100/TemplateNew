package com.gamerole.common.http

import com.skydoves.sandwich.ApiResponse


/**
 * sealed class Resource<T>(
val data: T? = null,
val errorCode: Int? = null
) {
class Success<T>(data: T) : Resource<T>(data)
class Loading<T>(data: T? = null) : Resource<T>(data)
class DataError<T>(errorCode: Int) : Resource<T>(null, errorCode)

override fun toString(): String {
return when (this) {
is Success<*> -> "Success[data=$data]"
is DataError -> "Error[exception=$errorCode]"
is Loading<T> -> "Loading"
}
}
}

 */
sealed class Resource<T>(
    var data: T? = null,
    var errorMsg: String? = null,
    var apiResponse: ApiResponse<*>? = null,
) {

    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(apiResponse: ApiResponse<*>?) : Resource<T>(null, null,apiResponse)
}