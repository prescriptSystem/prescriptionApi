package br.com.prescript.medicine

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

@Entity
class Medicine(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @NotBlank
    @Column(nullable = false)
    var medicineName: String
)