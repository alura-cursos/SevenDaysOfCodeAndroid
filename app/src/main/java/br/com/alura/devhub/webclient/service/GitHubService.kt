package br.com.alura.devhub.webclient.service

import br.com.alura.devhub.webclient.model.GitHubProfileWeb
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/users/{user}")
    suspend fun findProfileBy(@Path("user") user: String): GitHubProfileWeb

}