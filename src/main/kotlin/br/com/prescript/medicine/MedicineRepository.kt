package br.com.prescript.medicine

import org.springframework.data.jpa.repository.JpaRepository

interface MedicineRepository : JpaRepository<Medicine, Long> {
}