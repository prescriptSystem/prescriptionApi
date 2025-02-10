package br.com.prescript

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrescriptApplication

fun main(args: Array<String>) {
	runApplication<PrescriptApplication>(*args)
}
