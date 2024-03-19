package console.handler

import bank.Bank

class OperationNotSupported: Handler {

    override fun handle(bank: Bank) {
        println("!!! Operation is not supported !!!")
    }
}