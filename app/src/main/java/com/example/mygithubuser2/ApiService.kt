package com.example.mygithubuser2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUser(
        @Header("Authorization") auth: String,
        @Query("q") username: String
    ): Call<Users>

    @GET("users/{username}")
    fun getDetailUser(
        @Header("Authorization") auth: String,
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    fun getFollowersUser(
        @Header("Authorization") auth: String,
        @Path("username") username: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    fun getFollowingUser(
        @Header("Authorization") auth: String,
        @Path("username") username: String
    ): Call<List<User>>
}