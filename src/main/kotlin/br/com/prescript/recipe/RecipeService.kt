package br.com.prescript.recipe

import br.com.prescript.exception.NotFoundException
import br.com.prescript.medicine.MedicineRepository
import br.com.prescript.medicine.requests.CreateMedicineRequest
import br.com.prescript.prescription.Prescription
import br.com.prescript.prescription.PrescriptionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class RecipeService(
    private val prescriptionRepository: PrescriptionRepository,
    private val medicineRepository: MedicineRepository,
    private val recipeRepository: RecipeRepository,){

    fun insert(idPrescription: Long, idMedicine: Long, description: String) : Boolean
    {
        val prescription = prescriptionRepository.findByIdOrNull(idPrescription) ?: throw NotFoundException("Prescription ${idPrescription} not found")
        val medicine = medicineRepository.findByIdOrNull(idMedicine) ?: throw NotFoundException("Medicine ${idMedicine} not found")


        //if (doctor.id !=  idDoctor || patient.id !=idPatient || doctor.id == patient.id) return false

        /* val role = roleService.findByNameOrNull(roleUpper) ?: throw BadRequestException("Role ${roleUpper} not found")*/

        recipeRepository.save(Recipe(id = RecipePK(prescriptionId = idPrescription, medicineId = idMedicine), prescription = prescription, medicine = medicine, description = description))
        return true
    }

    fun updateRecipe(idRecipe: Long, description: String): Boolean {
        var newRecipe = recipeRepository.findByIdOrNull(idRecipe) ?: throw NotFoundException("Recipe ${idRecipe} not found")
        newRecipe.description = description
        recipeRepository.save(newRecipe)
        return true
    }

    fun delete(idRecipe: Long){
        recipeRepository.findByIdOrNull(idRecipe) ?: throw NotFoundException("Recipe ${idRecipe} not found")
        recipeRepository.deleteById(idRecipe)
    }

}