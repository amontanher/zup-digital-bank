package zup.bootcamp.digitalbank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DigitalBankApplication

fun main(args: Array<String>) {
	runApplication<DigitalBankApplication>(*args)
}
