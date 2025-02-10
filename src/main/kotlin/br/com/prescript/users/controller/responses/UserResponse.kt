package br.com.prescript.users.controller.responses

import br.com.prescript.users.User

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
) {
    constructor(user: User): this(id=user.id!!, user.name, user.email)
}
