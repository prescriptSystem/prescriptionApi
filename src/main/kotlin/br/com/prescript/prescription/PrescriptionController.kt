package br.com.prescript.prescription

import br.com.prescript.prescription.responses.PrescriptionResponse
import br.com.prescript.users.controller.requests.CreateUserRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/prescriptions")
class PrescriptionController(private val prescriptionService: PrescriptionService) {

    @PostMapping("/{idDoctor}/{idPatient}")
    fun insert(@PathVariable idDoctor: Long, @PathVariable idPatient: Long): ResponseEntity<Void>  =
        if (prescriptionService.insert(idDoctor, idPatient)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

            //.let { PrescriptionResponse(it) }
            //.let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun findAll(@RequestParam dir: String = "ASC"): ResponseEntity<List<PrescriptionResponse>> {
        val sortDir = SortDir.findOrNull(dir)
        if (sortDir == null)
            return ResponseEntity.badRequest().build()
        return prescriptionService.findAll(sortDir)
            .map { PrescriptionResponse(it) }
            .let { ResponseEntity.ok(it) }
    }

    /*@GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        prescriptionService.findByIdOrNull(id)
            ?.let { PrescriptionResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()*/

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        prescriptionService.delete(id)
            .let { ResponseEntity.ok().build() }

   /* @PutMapping("/{idPrescription}/medicine/{idMedicine}")
    fun grant(@PathVariable idPrescription: Long, @PathVariable idMedicine: Long): ResponseEntity<Void> =
        if (prescriptionService.addMedicine(idPrescription, idMedicine)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()*/
}