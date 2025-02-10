package br.com.prescript.recipe

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Embeddable
class RecipePK(prescriptionId: Long, medicineId: Long) : Serializable{

    @NotNull
    @Column(name = "prescription_id", nullable = false)
    val prescriptionId: Long = prescriptionId;

    @NotNull
    @Column(name = "medicine_id", nullable = false)
    val medicineId: Long = medicineId;
}