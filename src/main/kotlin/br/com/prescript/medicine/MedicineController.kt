package br.com.prescript.medicine

import br.com.prescript.medicine.requests.CreateMedicineRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicines")
class MedicineController(private val medicineService: MedicineService) {

    @PostMapping
    fun insert(@RequestBody @Valid medicine: CreateMedicineRequest) =
        medicineService.save(medicine.toMedicine()).let {
            ResponseEntity
                .status(HttpStatus.CREATED)
                .body(it)
        }

    @GetMapping
    fun findAll() = medicineService.findAll()

    @DeleteMapping("/{idMedicine}")
    fun delete(@PathVariable idMedicine: Long): ResponseEntity<Void> =
        medicineService.delete(idMedicine)
            .let { ResponseEntity.ok().build() }

    @PutMapping("/{idMedicine}")
    fun grant(@PathVariable idMedicine: Long, @RequestBody @Valid medicine: CreateMedicineRequest): ResponseEntity<Void> =
        if (medicineService.updateMedicine(idMedicine, medicine)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()
}