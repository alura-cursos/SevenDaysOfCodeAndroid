package br.com.alura.devhub.webclient

import android.util.Log
import br.com.alura.devhub.webclient.model.GitHubProfileWeb
import br.com.alura.devhub.webclient.service.GitHubService
import kotlinx.coroutines.flow.flow

class GitHubWebClient(
    private val service: GitHubService = RetrofitInit().gitHubService
) {

    fun findProfileBy(user: String) = flow {
        try {
            emit(service.findProfileBy(user))
        } catch (e: Exception) {
            Log.e("GitHubWebClient", "findProfileBy: falha ao buscar usuário", e)
        }
    }

}