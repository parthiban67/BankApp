package console.handler

import account.Account
import account.AccountFactory
import account.AccountType
import bank.Bank

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

            val account: Account? = accountType?.let { AccountFactory.getAccount(it) }
            var nameStep = false
            do {
                println("Enter customer name: ")
                readln().let{
                    if(it.isNotBlank()){
                        account?.name = it
                        nameStep = true;
                    }else{
                        println("!!! Customer name can't be blank !!!")
                    }
                }
            }while(!nameStep)

            account?.let{
                bank.createAccount(it)
                println("Account ${it.accountNumber} created ")
            }
        }
    }
}