package br.com.fiap.rpp.githubapp.service

import br.com.fiap.rpp.githubapp.model.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{userName}")
    fun searchUser(@Path("userName") userName: String): Call<GithubUser>

}