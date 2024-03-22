package bank.handler

import bank.account.Account
import bank.Bank
import exception.AccountNotFoundException

class CreditHandler: Handler {

    override fun handle(bank: Bank) {
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }

            var account: Account? = null
            var accountNumberStep = false
            do {
                println("Enter bank.account number: ")
                readln().let {
                    if (it.isNotBlank()) {
                        try {
                            account = bank.getAccountByNumber(it)
                            accountNumberStep = true
                        } catch (ex: AccountNotFoundException) {
                            println("!!! ${ex.message} !!!")
                        }
                    } else {
                        println("!!! Account number can't be blank !!!")
                    }
                }
            } while (!accountNumberStep)

            var amountStep = false
            var amount = 0.0
            do {
                println("Enter amount to credit: ")
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
            account?.credit(amount);
            println("Amount credited successfully")
            break
        }
    }
}