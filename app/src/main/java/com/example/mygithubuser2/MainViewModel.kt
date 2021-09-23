package com.example.mygithubuser2

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val listUser = MutableLiveData<List<User>>()
    val detailUser = MutableLiveData<DetailUser>()
    val listFollowers = MutableLiveData<List<User>>()
    val listFollowing = MutableLiveData<List<User>>()

    companion object{
        private const val TAG = "MainViewModel"
        private const val apiKey = "token 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"
    }

    fun setUser(user: String){
        val client = ApiConfig.getApiService().searchUser(apiKey, user)
        client.enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>){
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        listUser.value = response.body()?.items
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                listUser.value = listOf()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailUser(user: String){
        val client = ApiConfig.getApiService().getDetailUser(apiKey, user)
        client.enqueue(object : Callback<DetailUser>{
            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>){
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        detailUser.value = response.body()
                        detailUser.postValue(detailUser.value)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setFollowers(user: String){
        val client = ApiConfig.getApiService().getFollowersUser(apiKey, user)
        client.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if(responseBody.isNotEmpty()){
                            listFollowers.value = response.body()
                        } else{
                            listFollowers.value = null
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                listFollowers.value = listOf()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun setFollowing(user: String){
        val client = ApiConfig.getApiService().getFollowingUser(apiKey, user)
        client.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if(responseBody.isNotEmpty()){
                            listFollowing.value = response.body()
                        } else{
                            listFollowing.value = null
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                listFollowing.value = listOf()
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}