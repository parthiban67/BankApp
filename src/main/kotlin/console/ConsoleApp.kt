package console

import bank.Bank
import console.handler.AccountInfoHandler
import console.handler.CreateAccountHandler
import console.handler.OperationNotSupported

class ConsoleApp {

    private val bank: Bank = Bank();

    private fun displayOptions(): Unit{
        println("${"*".repeat(5)} Welcome to Bank ${"*".repeat(5)}")
        println("Enter the option number to proceed")
        println("1 Create Account")
        println("2 Update Account")
        println("3 Withdraw from Account")
        println("4 Credit Account")
        println("5 Transfer to Account")
        println("6 Account Info")
        println("-1 Exit")
    }

    fun begin(): Unit{
        var shouldRun = true
        while(shouldRun){
            displayOptions()
            print("Please enter your option: ")
            when(readln().toInt()){
                1 -> CreateAccountHandler().handle(bank)
                2 -> OperationNotSupported().handle(bank)
                3 -> ""
                4 -> ""
                5 -> ""
                6 -> AccountInfoHandler().handle(bank)
                -1 -> shouldRun = false
                else -> println("!!! Invalid option !!!")
            }
        }
    }
}