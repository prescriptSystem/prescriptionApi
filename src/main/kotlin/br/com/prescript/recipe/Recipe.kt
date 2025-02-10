package br.com.prescript.recipe

import br.com.prescript.medicine.Medicine
import br.com.prescript.prescription.Prescription
import jakarta.persistence.*

@Entity
class Recipe(@EmbeddedId val id: RecipePK,
             @ManyToOne
             @MapsId("prescriptionId")
             @PrimaryKeyJoinColumn(name = "prescription_id")
             val prescription: Prescription,
             @ManyToOne
             @MapsId("medicineId")
             @PrimaryKeyJoinColumn(name = "medicine_id")
             val medicine: Medicine,
             var description: String)