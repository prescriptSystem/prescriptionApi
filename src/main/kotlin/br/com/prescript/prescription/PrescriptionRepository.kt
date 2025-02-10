package br.com.prescript.prescription


import br.com.prescript.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PrescriptionRepository : JpaRepository<Prescription, Long> {

    /*@Query("select distinct p from Prescription p" +
            " join p.medicines m" +
            " where m.medicineName = :medicine" +
            " order by p.id")
    fun findByMedicine(medicine: String): List<Prescription>*/

}