package br.com.alura.devhub.webclient.model

import br.com.alura.devhub.model.GitHubRepository

data class GitHubRepositoryWeb(
    val name: String = "",
    val description: String? = null
)

fun GitHubRepositoryWeb.toGitHubRepository() = GitHubRepository(
    name = name,
    description = description ?: ""
)
