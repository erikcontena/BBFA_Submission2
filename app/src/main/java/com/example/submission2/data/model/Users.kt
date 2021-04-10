package com.example.submission2.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users (

        @field:SerializedName("login")
        var username: String,

        @field:SerializedName("avatar_url")
        var avatar : String,

        @field:SerializedName("name")
        var name : String? = null,

        @field:SerializedName("company")
        var company : String? = null,

        @field:SerializedName("location")
        var location : String? = null,

        @field:SerializedName("following_url")
        var followingUrl : String? = null,

        @field:SerializedName("follower_url")
        var followerUrl : String? = null,

        @field:SerializedName("public_repos")
        var repository : Int? = null,

        @field:SerializedName("follower")
        var follower : Int? = null,

        @field:SerializedName("following")
        var following : Int? = null

):Parcelable

