package bank.handler

import bank.Bank

interface Handler {
    fun handle(bank: Bank): Unit
}