package br.com.fiap.rpp.githubapp.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    @SerializedName("name") val userName: String,
    @SerializedName("avatar_url") val avatar: String,
    val bio: String
)