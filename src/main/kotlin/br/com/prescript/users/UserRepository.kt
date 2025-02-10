package br.com.prescript.users

import br.com.prescript.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    @Query("select distinct u from User u" +
            " join u.roles r" +
            " where r.name = :role" +
            " order by u.name")
    fun findByRole(role: String): List<User>

    @Query("select distinct u from User u" +
            " join u.roles r" +
            " where u.id= :userId AND r.name = :role" +
            " order by u.name")
    fun findByUserAndRole(userId: Long, role: String): User?
}
