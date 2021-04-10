package com.example.submission2.ui.detail.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.data.model.Users
import com.example.submission2.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {

    companion object{
        private const val TAG = "FollowingViewModel"
    }
    private val listFollowing = MutableLiveData<ArrayList<Users>>()
    fun getFollowing(username: String): LiveData<ArrayList<Users>> {
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    listFollowing.postValue(response.body() as ArrayList<Users>?)
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }

        })
        return listFollowing
    }
}