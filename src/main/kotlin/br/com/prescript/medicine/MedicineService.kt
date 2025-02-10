package br.com.prescript.medicine

import br.com.prescript.exception.BadRequestException
import br.com.prescript.exception.NotFoundException
import br.com.prescript.medicine.requests.CreateMedicineRequest
import br.com.prescript.roles.Role
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MedicineService(private val medicineRepository: MedicineRepository) {

    fun save(medicine: Medicine) =
        medicineRepository.save(medicine)
    fun findAll() =
        medicineRepository.findAll(Sort.by("medicineName"))
    fun findByNameOrNull(id: Long) =
        medicineRepository.findByIdOrNull(id)

    fun updateMedicine(idMedicine: Long, medicine: CreateMedicineRequest): Boolean {
        var newMedicine = medicineRepository.findByIdOrNull(idMedicine) ?: throw NotFoundException("Medicine ${idMedicine} not found")
        newMedicine.medicineName = medicine.medicineName
        medicineRepository.save(newMedicine)
        return true
    }

    fun delete(idMedicine: Long){
        medicineRepository.findByIdOrNull(idMedicine) ?: throw NotFoundException("Medicine ${idMedicine} not found")
        medicineRepository.deleteById(idMedicine)
    }
}