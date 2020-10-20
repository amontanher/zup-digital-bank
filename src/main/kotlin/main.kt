package digitalBank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BankApplication

fun main(){
    runApplication<BankApplication>()
}