package com.example.submission2.data.model

import com.google.gson.annotations.SerializedName

data class UsersResponse(
        @field:SerializedName("total_count")
        var totalCount : Int? = null,

        @field:SerializedName("items")
        var items : List<Users>? = null
)