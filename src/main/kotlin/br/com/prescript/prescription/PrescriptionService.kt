package br.com.prescript.prescription

import br.com.prescript.exception.BadRequestException
import br.com.prescript.exception.NotFoundException
import br.com.prescript.medicine.Medicine
import br.com.prescript.medicine.MedicineRepository
import br.com.prescript.medicine.MedicineService
import br.com.prescript.recipe.Recipe
import br.com.prescript.recipe.RecipePK
import br.com.prescript.recipe.RecipeRepository
import br.com.prescript.recipe.RecipeService
import br.com.prescript.users.User
import br.com.prescript.users.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class PrescriptionService(
    private val prescriptionRepository: PrescriptionRepository,
    private val userRepository: UserRepository,
    private val medicineRepository: MedicineRepository,
    private val recipeRepository: RecipeRepository
) {
    fun insert(idDoctor: Long, idPatient: Long) : Boolean
    {
        val doctor = findByUserAndRole(idDoctor, "DOCTOR") ?: throw NotFoundException("Doctor ${idDoctor} not found")
        val patient = findByUserAndRole(idPatient, "PATIENT") ?: throw NotFoundException("Patient ${idPatient} not found")



        if (doctor.id !=  idDoctor || patient.id !=idPatient || doctor.id == patient.id) return false

       /* val role = roleService.findByNameOrNull(roleUpper) ?: throw BadRequestException("Role ${roleUpper} not found")*/

        prescriptionRepository.save(Prescription(doctor = doctor, patient = patient))
        return true
    }

    fun findAll(dir: SortDir): List<Prescription> {
        //if (!medicine.isNullOrBlank())
            //return prescriptionRepository.findByMedicine(medicine)
        //val prescriptions = prescriptionRepository.findAll()

        return when(dir) {
            SortDir.ASC -> prescriptionRepository.findAll(Sort.by("id"))
            SortDir.DESC -> prescriptionRepository.findAll(Sort.by("id").descending())
        }
    }

    fun findAllRecipes(idPrescription: Long?): List<Recipe>
    {
        return recipeRepository.findByRecipes(idPrescription!!);
    }

    fun delete(id: Long) = prescriptionRepository.deleteById(id)

    fun findByUserAndRole(id: Long, role: String) =
        userRepository.findByUserAndRole(id, role)

    fun findByIdOrNull(id: Long) =
        prescriptionRepository.findByIdOrNull(id)

    /*fun addMedicine(idPrescription: Long, idMedicine: Long): Boolean {
        val medicine = medicineRepository.findByIdOrNull(idMedicine) ?: throw NotFoundException("Medicine ${idMedicine} not found")
        val prescription = prescriptionRepository.findByIdOrNull(idPrescription) ?: throw NotFoundException("Prescription ${idPrescription} not found")
        if (prescription.medicines.any { it.medicineName ==  medicine.medicineName }) return false



        prescription.medicines.add(medicine)
        prescriptionRepository.save(prescription)
        return true
    }*/

}