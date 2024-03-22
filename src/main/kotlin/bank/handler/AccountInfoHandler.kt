package bank.handler

import bank.account.Account
import bank.Bank
import exception.AccountNotFoundException

class AccountInfoHandler: Handler {

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
            account?.printAccountInfo()
        }
    }
}