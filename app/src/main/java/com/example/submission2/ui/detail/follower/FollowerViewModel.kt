package com.example.submission2.ui.detail.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.data.model.Users
import com.example.submission2.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {

    companion object{
        private const val TAG = "FollowerViewModel"
    }
    private val listFollower = MutableLiveData<ArrayList<Users>>()
    fun getFollowers(username: String): LiveData<ArrayList<Users>> {
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    listFollower.postValue(response.body() as ArrayList<Users>?)
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }

        })
        return listFollower
    }
}