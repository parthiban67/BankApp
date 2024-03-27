package bank.handler

import bank.Bank

class ListAccountHandler: Handler {

    override fun handle(bank: Bank) {
        print("Proceed with the selected option (y/n): ");
        val proceed = readln()
        if (proceed == "n") {
            return
        }
        bank.listAccounts()
    }
}