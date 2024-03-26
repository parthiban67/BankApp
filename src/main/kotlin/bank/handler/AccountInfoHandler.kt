package bank.handler

import bank.Bank

class AccountInfoHandler: Handler {

    override fun handle(bank: Bank) {
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }

            var accountNumber = ""
            var accountNumberStep = false
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
            try {
                bank.printAccount(accountNumber)
            } catch (ex: Exception) {
                println("!!! ${ex.message} !!!")
            }
        }
    }
}