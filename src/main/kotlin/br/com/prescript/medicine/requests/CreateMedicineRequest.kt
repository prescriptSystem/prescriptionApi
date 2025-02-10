package br.com.prescript.medicine.requests

import br.com.prescript.medicine.Medicine
import br.com.prescript.users.User
import jakarta.validation.constraints.Email
import org.jetbrains.annotations.NotNull

class CreateMedicineRequest(
                             @field:NotNull
                             val medicineName: String,) {

    fun toMedicine() = Medicine(
        medicineName = medicineName,
    )
}