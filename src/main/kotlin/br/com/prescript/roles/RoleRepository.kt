package br.com.prescript.roles

import br.com.prescript.roles.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, String>
