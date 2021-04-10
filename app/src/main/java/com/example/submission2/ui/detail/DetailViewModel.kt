package com.example.submission2.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.data.model.Users
import com.example.submission2.data.model.UsersResponse
import com.example.submission2.data.retrofit.ApiConfig
import com.example.submission2.ui.home.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    companion object{
        private const val TAG = "DetailViewModel"
    }
    private val user = MutableLiveData<Users>()


    fun getUser(username: String): LiveData<Users> {
        val client = ApiConfig.getApiService().getUser(username)
        client.enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    user.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d(TAG, "onFailure: ")
            }

        })

        return user
    }

}