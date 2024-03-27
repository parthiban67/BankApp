package bank.handler

import bank.Bank
import bank.dto.TransferAccount

class TransferHandler: Handler {

    override fun handle(bank: Bank) {
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }

            var fromAccountNumber = ""
            var fromAccountNumberStep = false
            do {
                println("Enter from bank.account number: ")
                readln().let {
                    if (it.isNotBlank()) {
                        fromAccountNumber = it
                        fromAccountNumberStep = true
                    } else {
                        println("!!! Account number can't be blank !!!")
                    }
                }
            } while (!fromAccountNumberStep)

            var toAccountNumber = ""
            var toAccountNumberStep = false
            do {
                println("Enter from bank.account number: ")
                readln().let {
                    if (it.isNotBlank()) {
                        toAccountNumber = it
                        toAccountNumberStep = true
                    } else {
                        println("!!! Account number can't be blank !!!")
                    }
                }
            } while (!toAccountNumberStep)

            var amountStep = false
            var amount = 0.0
            do {
                println("Enter amount to transfer: ")
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
                val transferAccount = TransferAccount(fromAccountNumber,toAccountNumber,amount)
                bank.transferAccount(transferAccount);
                println("Amount transferred successfully")
                break
            }catch (ex: Exception){
                println("!!! ${ex.message} !!!")
            }
        }
    }
}