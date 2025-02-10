package br.com.prescript.prescription.responses

import br.com.prescript.prescription.Prescription
import br.com.prescript.recipe.Recipe
import br.com.prescript.recipe.RecipeRepository
import br.com.prescript.recipe.RecipeService
import br.com.prescript.users.User

data class PrescriptionResponse(
    val id: Long,
    val doctorName: String,
    val patientName: String,
    val recipes: List<RecipeResponse>
) {

    constructor(prescription: Prescription): this(id=prescription.id!!, prescription.doctor.name, prescription.patient.name, prescription.recipes.map { r -> RecipeResponse(r)})
}