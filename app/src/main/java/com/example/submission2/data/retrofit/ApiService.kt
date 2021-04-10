package com.example.submission2.data.retrofit

import com.example.submission2.data.model.Users
import com.example.submission2.data.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object{
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users")
    @Headers("Authorization: token 178291b5d936b9311ffb98e7d5b30ec10b3fc5ce")
    fun getUsers(): Call<List<Users>>

    @GET("search/users?")
    @Headers("Authorization: token 178291b5d936b9311ffb98e7d5b30ec10b3fc5ce")
    fun getUsers(@Query("q") username: String): Call<UsersResponse>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<Users>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 178291b5d936b9311ffb98e7d5b30ec10b3fc5ce")
    fun getFollowers(@Path("username") username: String): Call<List<Users>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 178291b5d936b9311ffb98e7d5b30ec10b3fc5ce")
    fun getFollowing(@Path("username") username: String): Call<List<Users>>

}