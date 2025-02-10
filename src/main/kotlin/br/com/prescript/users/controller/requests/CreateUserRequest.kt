package br.com.prescript.users.controller.requests

import br.com.prescript.users.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class CreateUserRequest(
    @field:Email(message = "Não é um e-mail válido!")
    @field:NotNull
    val email: String?,

    @field:NotBlank
    val password: String?,

    @field:NotBlank
    val name: String?
) {
    fun toUser() = User(
        email = email!!,
        password = password!!,
        name = name!!
    )
}
