package br.com.prescript.users.controller.responses

import br.com.prescript.security.UserToken

data class LoginResponse (
    val token: String,
    val user: UserResponse
)
