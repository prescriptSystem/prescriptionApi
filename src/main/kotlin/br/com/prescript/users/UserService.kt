package br.com.prescript.users

import br.com.prescript.exception.BadRequestException
import br.com.prescript.exception.NotFoundException
import br.com.prescript.roles.RoleRepository
import br.com.prescript.roles.RoleService
import br.com.prescript.security.JWT
import br.com.prescript.users.controller.responses.LoginResponse
import br.com.prescript.users.controller.responses.UserResponse
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    private val roleService: RoleService,
    val jwt: JWT
) {
    fun insert(user: User)= userRepository.save(user)
    fun findAll(dir: SortDir, role: String?): List<User> {
        if (!role.isNullOrBlank())
            return userRepository.findByRole(role)
        return when(dir) {
            SortDir.ASC -> userRepository.findAll(Sort.by("name"))
            SortDir.DESC -> userRepository.findAll(Sort.by("name").descending())
        }
    }


    fun findByIdOrNull(id: Long) =
        userRepository.findByIdOrNull(id)
    fun delete(id: Long) = userRepository.deleteById(id)

    fun addRole(id: Long, roleName: String): Boolean {
        val roleUpper = roleName.uppercase()
        val user = findByIdOrNull(id) ?: throw NotFoundException("User ${id} not found")
        if (user.roles.any { it.name ==  roleUpper }) return false

        val role = roleService.findByNameOrNull(roleUpper) ?: throw BadRequestException("Role ${roleUpper} not found")

        user.roles.add(role)
        userRepository.save(user)
        return true
    }

    fun login(email: String, password: String): LoginResponse? {
        val user = userRepository.findByEmail(email)
        if (user == null)
        {
            log.warn("User ${email} not found")
            return null
        }

        if (user.password != password)
        {
            log.warn("User ${user.email} passwords do not match")
            return null
        }

        log.info("User ${user.email} authenticated")

        return LoginResponse(

            jwt.createToken(user),
            UserResponse(user)

        )

    }

    companion object{
        val log = LoggerFactory.getLogger(UserService::class.java)
    }
}
