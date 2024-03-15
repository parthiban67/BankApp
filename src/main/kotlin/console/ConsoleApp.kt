package console

import account.Account
import account.AccountFactory
import account.AccountType
import bank.Bank

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
        println("-1 Exit")
    }

    private fun createNewAccount(): Unit{
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }
            println("Enter the account type: ")
            for ((i, type) in AccountType.values().withIndex()) {
                println("${i + 1} ${type.name}")
            }
            val accountTypeOption = readln().toInt();
            val accountType: AccountType? = AccountType.values()
                .filterIndexed { i, _ -> i == accountTypeOption }.firstOrNull()
            if (accountType == null) {
                println("!!! Invalid option !!!")
            }
            val account: Account? = accountType?.let { AccountFactory.getAccount(it) };
            val name = readln()
            account?.name = name

            account?.let{
                bank.createAccount(it)
                println("Account created successfully")
            }
        }
    }

    fun begin(): Unit{
        var shouldRun = true
        while(shouldRun){
            displayOptions()
            print("Please enter your option: ")
            when(readln().toInt()){
                1 -> createNewAccount()
                2 -> ""
                3 -> ""
                4 -> ""
                -1 -> shouldRun = false
                else -> println("!!! Invalid option !!!")
            }
        }
    }
}