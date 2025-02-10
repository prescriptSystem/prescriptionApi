package br.com.prescript.prescription

import br.com.prescript.medicine.Medicine
import br.com.prescript.recipe.Recipe
import br.com.prescript.users.User
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
class Prescription(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @NotBlank
    @ManyToOne
    val doctor: User,

    @NotBlank
    @ManyToOne
    var patient: User,

    @OneToMany(mappedBy = "prescription")
    val recipes: MutableList<Recipe> = mutableListOf(),

)