package bank

import bank.account.Account
import bank.account.AccountFactory
import bank.dto.CreateAccount
import bank.dto.CreditAccount
import bank.dto.TransferAccount
import bank.dto.WithdrawAccount
import exception.AccountNotFoundException
import exception.InSufficientBalanceException
import kotlin.random.Random

class Bank {

    private val accountStore = mutableMapOf<String,Account>()

    private val charMap = arrayOfNulls<Char>(10)
    init{
        var idx = 0
        for(n in 48..57){
             charMap[idx++] = n.toChar()
        }
    }

    private fun generateAccountNumber(): String{
        var accountNumber = ""
        for( i in 1..15){
            accountNumber += charMap[Random.nextInt(0,9)]
        }
        return accountNumber
    }

    private fun setBankAndBranch(account: Account): Unit{
        account.bankName = "Kotlin Bank"
        account.branchName = "Chennai"
    }

    private fun findAccount(accountNumber: String): Account{
        return accountStore[accountNumber].let{
            it ?: throw AccountNotFoundException("Account not found")
        }
    }

    fun createAccount(createAccount: CreateAccount): Unit{
        val account: Account = AccountFactory.getAccount(createAccount.accountType)
        account.accountNumber = generateAccountNumber()
        setBankAndBranch(account)
        accountStore[account.accountNumber] = account
    }

    fun withdrawAccount(withdrawAccount: WithdrawAccount): Unit{
        val account: Account = findAccount(withdrawAccount.accountNumber)
        if(account.balance < withdrawAccount.amount){
            throw InSufficientBalanceException("Account balance is low")
        }
        account.balance -= withdrawAccount.amount
    }

    fun creditAccount(creditAccount: CreditAccount): Unit{
        val account: Account = findAccount(creditAccount.accountNumber)
        account.balance += creditAccount.amount
    }

    fun printAccount(accountNumber: String): Unit{
        val account: Account = findAccount(accountNumber)
        account.printAccountInfo()
    }

    fun transferAccount(transferAccount: TransferAccount): Unit{
        val fromAccount = findAccount(transferAccount.fromAccountNumber)
        val toAccount = findAccount(transferAccount.toAccountNumber)
        if(fromAccount.balance < transferAccount.amount){
            throw InSufficientBalanceException("Account balance is low")
        }
        toAccount.balance += transferAccount.amount
    }

    fun listAccounts(): Unit{
        accountStore.values.forEach {
            it.printAccountInfo()
        }
    }
}