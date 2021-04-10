package com.example.submission2.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.data.model.Users
import com.example.submission2.data.model.UsersResponse
import com.example.submission2.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){

    companion object {
        const val TAG = "MainViewModel"
    }
    private val listUser = MutableLiveData<ArrayList<Users>>()

     fun getUsers() : LiveData<ArrayList<Users>>{
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {
                    listUser.postValue(response.body() as ArrayList<Users>?)
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.d(TAG, "onFailure GetUsers: ")

            }

        })
        return listUser
    }

    fun searchUsers(username: String): LiveData<ArrayList<Users>> {
        val client = ApiConfig.getApiService().getUsers(username)
        client.enqueue(object : Callback<UsersResponse>{
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    listUser.value = response.body()?.items as ArrayList<Users>?
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.d(TAG, "onFailure SearchUsers: ")
            }

        })
        return listUser
    }


}