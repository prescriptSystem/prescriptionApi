package br.com.prescript.users.controller.requests

data class LoginRequest(
    val email: String?,
    val password: String?
)
