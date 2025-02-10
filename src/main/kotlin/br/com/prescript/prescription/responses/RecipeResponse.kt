package br.com.prescript.prescription.responses

import br.com.prescript.prescription.Prescription
import br.com.prescript.recipe.Recipe

class RecipeResponse(
    val medicineName: String,
    val description: String
) {

    constructor(recipe: Recipe): this(recipe.medicine.medicineName, recipe.description)
}