package br.com.alura.devhub.webclient.model

import com.squareup.moshi.Json

data class GitHubProfileWeb(
    val login: String,
    @field:Json(name = "avatar_url")
    val avatar: String,
    val name: String,
    val bio: String
)
