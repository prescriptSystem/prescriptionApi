package br.com.prescript.recipe

import br.com.prescript.medicine.requests.CreateMedicineRequest
import br.com.prescript.prescription.PrescriptionService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
class RecipeController(private val recipeService: RecipeService) {

    @PostMapping("/{idPrescription}/{idMedicine}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun insert(@PathVariable idPrescription: Long, @PathVariable idMedicine: Long, @RequestBody description: String): ResponseEntity<Void> =
        if (recipeService.insert(idPrescription, idMedicine, description)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

    @DeleteMapping("/{idRecipe}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun delete(@PathVariable idRecipe: Long): ResponseEntity<Void> =
        recipeService.delete(idRecipe)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{idRecipe}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun grant(@PathVariable idRecipe: Long, @RequestBody @Valid description: String): ResponseEntity<Void> =
        if (recipeService.updateRecipe(idRecipe, description)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}