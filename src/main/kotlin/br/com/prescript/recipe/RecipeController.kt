package br.com.prescript.recipe

import br.com.prescript.medicine.requests.CreateMedicineRequest
import br.com.prescript.prescription.PrescriptionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
class RecipeController(private val recipeService: RecipeService) {

    @PostMapping("/{idPrescription}/{idMedicine}")
    fun insert(@PathVariable idPrescription: Long, @PathVariable idMedicine: Long, @RequestBody description: String): ResponseEntity<Void> =
        if (recipeService.insert(idPrescription, idMedicine, description)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

    @DeleteMapping("/{idRecipe}")
    fun delete(@PathVariable idRecipe: Long): ResponseEntity<Void> =
        recipeService.delete(idRecipe)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{idRecipe}")
    fun grant(@PathVariable idRecipe: Long, @RequestBody @Valid description: String): ResponseEntity<Void> =
        if (recipeService.updateRecipe(idRecipe, description)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}