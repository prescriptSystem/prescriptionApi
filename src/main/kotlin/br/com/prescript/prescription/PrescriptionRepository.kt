package br.com.prescript.prescription


import br.com.prescript.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PrescriptionRepository : JpaRepository<Prescription, Long> {

    @Query("select distinct p from Prescription p" +
            " join p.doctor d" +
            " where d.id = :doctor" +
            " order by p.id")
    fun findByDoctor(doctor: Long): List<Prescription>

    @Query("select distinct p from Prescription p" +
            " join p.patient pa" +
            " where pa.id = :patientId" +
            " order by p.id")
    fun findByPatient(patientId: Long): List<Prescription>

}