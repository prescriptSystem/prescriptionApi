package br.com.prescript

import br.com.prescript.roles.Role
import br.com.prescript.roles.RoleRepository
import br.com.prescript.users.User
import br.com.prescript.users.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole =
            roleRepository.findByIdOrNull("ADMIN")
                ?: roleRepository.save(Role("ADMIN", "System Administrator"))
                    /*.also { roleRepository.save(Role("USER", "Premium User")) }*/

        if (userRepository.findByRole("ADMIN").isEmpty()) {
            val admin = User(
                email="victor@gmail.com",
                password = "123456",
                name="Prescript Administrator"
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }
    }

}
