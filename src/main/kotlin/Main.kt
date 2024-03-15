import account.Account
import account.AccountFactory
import account.AccountType
import console.ConsoleApp

fun main(args: Array<String>) {
    val account: Account = AccountFactory.getAccount(AccountType.SAVINGS)
    account.printAccountType()
    ConsoleApp().begin()
}