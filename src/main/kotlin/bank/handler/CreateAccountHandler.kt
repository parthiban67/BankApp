package bank.handler

import bank.account.Account
import bank.account.AccountFactory
import bank.account.AccountType
import bank.Bank
import bank.dto.CreateAccount

class CreateAccountHandler: Handler {

    final override fun handle(bank: Bank): Unit {
        while (true) {
            print("Proceed with the selected option (y/n): ");
            val proceed = readln()
            if (proceed == "n") {
                return
            }

            var accountTypeStep = false
            var accountType: AccountType?
            do {
                println("Enter the account type: ")
                for ((i, type) in AccountType.entries.withIndex()) {
                    println("${i + 1} ${type.name}")
                }
                val accountTypeOption = readln().toInt();
                accountType = AccountType.entries.toTypedArray()
                    .filterIndexed { i, _ -> (i + 1) == accountTypeOption }.firstOrNull()
                if (accountType == null) {
                    println("!!! Invalid option !!!")
                    continue
                }
                accountTypeStep = true
            }while(!accountTypeStep)

            var nameStep = false
            var customerName: String = ""
            do {
                println("Enter customer name: ")
                readln().let{
                    if(it.isNotBlank()){
                        customerName = it
                        nameStep = true;
                    }else{
                        println("!!! Customer name can't be blank !!!")
                    }
                }
            }while(!nameStep)

            val createAccount = CreateAccount(customerName,accountType as AccountType)
            bank.createAccount(createAccount)
            println("Account created successfully")
        }
    }
}