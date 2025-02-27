package br.com.prescript.medicine

import br.com.prescript.medicine.requests.CreateMedicineRequest
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicines")
class MedicineController(private val medicineService: MedicineService) {

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun insert(@RequestBody @Valid medicine: CreateMedicineRequest) =
        medicineService.save(medicine.toMedicine()).let {
            ResponseEntity
                .status(HttpStatus.CREATED)
                .body(it)
        }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun findAll() = medicineService.findAll()

    @DeleteMapping("/{idMedicine}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun delete(@PathVariable idMedicine: Long): ResponseEntity<Void> =
        medicineService.delete(idMedicine)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{idMedicine}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun grant(@PathVariable idMedicine: Long, @RequestBody @Valid medicine: CreateMedicineRequest): ResponseEntity<Void> =
        if (medicineService.updateMedicine(idMedicine, medicine)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}