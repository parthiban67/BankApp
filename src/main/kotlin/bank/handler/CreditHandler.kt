package bank.handler

import bank.Bank
import bank.dto.CreditAccount

class CreditHandler: Handler {

    override fun handle(bank: Bank) {
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }
            var accountNumberStep = false
            var accountNumber = ""
            do {
                println("Enter bank account number: ")
                readln().let {
                    if (it.isNotBlank()) {
                        accountNumber = it
                        accountNumberStep = true
                    } else {
                        println("!!! Account number can't be blank !!!")
                    }
                }
            } while (!accountNumberStep)

            var amountStep = false
            var amount = 0.0
            do {
                println("Enter amount to withdraw: ")
                readln().let {
                    if (it.isNotBlank()) {
                        try {
                            amount = it.toDouble()
                            amountStep = true
                        } catch (ex: Exception) {
                            println("!!! Invalid amount !!!")
                        }
                    } else {
                        println("!!! Amount can't be blank !!!")
                    }
                }
            } while (!amountStep)

            try{
                val creditAccount = CreditAccount(accountNumber,amount)
                bank.creditAccount(creditAccount)
                println("Amount credited successfully")
                break
            }catch (ex: Exception){
                println("!!! ${ex.message} !!!")
            }
        }
    }
}