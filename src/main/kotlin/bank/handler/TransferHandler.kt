package bank.handler

import bank.account.Account
import bank.Bank
import exception.AccountNotFoundException

class TransferHandler: Handler {

    override fun handle(bank: Bank) {
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }

            var fromAccount: Account? = null
            var fromAccountNumberStep = false
            do {
                println("Enter from bank.account number: ")
                readln().let {
                    if (it.isNotBlank()) {
                        try {
                            fromAccount = bank.getAccountByNumber(it)
                            fromAccountNumberStep = true
                        } catch (ex: AccountNotFoundException) {
                            println("!!! ${ex.message} !!!")
                        }
                    } else {
                        println("!!! Account number can't be blank !!!")
                    }
                }
            } while (!fromAccountNumberStep)

            var toAccount: Account? = null
            var toAccountNumberStep = false
            do {
                println("Enter from bank.account number: ")
                readln().let {
                    if (it.isNotBlank()) {
                        try {
                            toAccount = bank.getAccountByNumber(it)
                            toAccountNumberStep = true
                        } catch (ex: AccountNotFoundException) {
                            println("!!! ${ex.message} !!!")
                        }
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

            fromAccount?.debit(amount);
            toAccount?.credit(amount);
            println("Amount transferred successfully")
            break
        }
    }
}