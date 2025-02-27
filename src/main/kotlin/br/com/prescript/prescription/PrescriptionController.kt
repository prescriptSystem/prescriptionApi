package br.com.prescript.prescription

import br.com.prescript.prescription.responses.PrescriptionResponse
import br.com.prescript.security.UserToken
import br.com.prescript.users.controller.requests.CreateUserRequest
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/prescriptions")
class PrescriptionController(private val prescriptionService: PrescriptionService) {

    @PostMapping("/{idDoctor}/{idPatient}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun insert(@PathVariable idDoctor: Long, @PathVariable idPatient: Long , auth: Authentication): ResponseEntity<Void> {
        val user = auth.principal as UserToken
        return if (idPatient == user.id) ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        else if (prescriptionService.insert(idDoctor, idPatient)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()

    }       //.let { PrescriptionResponse(it) }
            //.let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    @PreAuthorize("hasRole('DOCTOR') || hasRole('PATIENT')")
    @SecurityRequirement(name = "Prescript")
    fun findAll(@RequestParam dir: String = "ASC", auth: Authentication): ResponseEntity<List<PrescriptionResponse>> {
        val user = auth.principal as UserToken
        val sortDir = SortDir.findOrNull(dir)
        if (sortDir == null)
            return ResponseEntity.badRequest().build()
        if(user.roles.contains("DOCTOR"))
        return prescriptionService.findByDoctor(user.id)
            .map { PrescriptionResponse(it) }
            .let { ResponseEntity.ok(it) }
        else if(user.roles.contains("PATIENT"))
            return prescriptionService.findByPatient(user.id)
                .map { PrescriptionResponse(it) }
                .let { ResponseEntity.ok(it) }
        else
            return ResponseEntity.badRequest().build()
    }

    /*@GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        prescriptionService.findByIdOrNull(id)
            ?.let { PrescriptionResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()*/

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('DOCTOR')")
    @SecurityRequirement(name = "Prescript")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> =
        prescriptionService.delete(id)
            .let { ResponseEntity.ok().build() }

   /* @PutMapping("/{idPrescription}/medicine/{idMedicine}")
    fun grant(@PathVariable idPrescription: Long, @PathVariable idMedicine: Long): ResponseEntity<Void> =
        if (prescriptionService.addMedicine(idPrescription, idMedicine)) ResponseEntity.ok().build()
        else ResponseEntity.noContent().build()*/
}