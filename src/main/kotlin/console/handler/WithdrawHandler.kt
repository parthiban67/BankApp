package console.handler

import account.Account
import bank.Bank
import exception.AccountNotFoundException
import exception.InSufficientBalanceException

class WithdrawHandler: Handler {

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
                println("Enter account number: ")
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
                account?.withdraw(amount);
                println("Amount withdrawn successfully")
                break
            }catch (ex: InSufficientBalanceException){
                println("!!! ${ex.message} !!!")
            }
        }
    }
}