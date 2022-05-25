package br.com.alura.devhub.webclient

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.alura.devhub.ui.screen.ProfileUiState
import br.com.alura.devhub.webclient.model.GitHubProfileWeb
import br.com.alura.devhub.webclient.model.toGitHubRepository
import br.com.alura.devhub.webclient.model.toProfileUiState
import br.com.alura.devhub.webclient.service.GitHubService
import kotlinx.coroutines.flow.flow

class GitHubWebClient(
    private val service: GitHubService = RetrofitInit().gitHubService
) {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    suspend fun findProfileBy(user: String) {
        try {
            val profile = service.findProfileBy(user).toProfileUiState()
            val repositories = service.findRepositoriesBy(user).map { it.toGitHubRepository() }
            uiState = profile.copy(repositories = repositories)
        } catch (e: Exception) {
            Log.e("GitHubWebClient", "findProfileBy: falha ao buscar usuário", e)
        }
    }

}