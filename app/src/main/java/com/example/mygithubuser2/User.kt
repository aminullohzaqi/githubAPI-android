package com.example.mygithubuser2

import com.google.gson.annotations.SerializedName

data class Users(
    var items: List<User>
)

data class User(
    @SerializedName("login")
    var username: String,
    @SerializedName("avatar_url")
    var imageProfile: String?,
    @SerializedName("html_url")
    var url: String,
)

data class DetailUser(
    @SerializedName("name")
    var name: String,
    @SerializedName("login")
    var username: String,
    @SerializedName("avatar_url")
    var imageProfile: String?,
    var company: String,
    var location: String,
    @SerializedName("public_repos")
    var public_repos: Int,
    @SerializedName("followers")
    var followers: Int,
    @SerializedName("following")
    var following: Int,
)