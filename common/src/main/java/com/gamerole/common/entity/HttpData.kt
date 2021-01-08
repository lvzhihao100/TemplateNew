package com.gamerole.common.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class HttpData<T> : Serializable {
    @SerializedName(value = "status", alternate =["code"])
    var status = 0

    @SerializedName(value = "msg", alternate = ["message"])
    var msg: String? = null
    var data: T? = null
}