package br.com.prescript.recipe

import br.com.prescript.roles.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecipeRepository: JpaRepository<Recipe, Long> {

    @Query("select distinct r from Recipe r" +
            " join r.prescription p" +
            " where p.id = :prescriptionId" +
            " order by p.id")
    fun findByRecipes(prescriptionId: Long): List<Recipe>

}