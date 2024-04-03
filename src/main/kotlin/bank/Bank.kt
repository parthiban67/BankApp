package bank

import bank.account.Account
import bank.account.AccountFactory
import bank.dto.CreateAccount
import bank.dto.CreditAccount
import bank.dto.TransferAccount
import bank.dto.WithdrawAccount
import db.AccountRepository
import exception.AccountNotFoundException
import exception.InSufficientBalanceException
import kotlin.random.Random

class Bank {

    private val accountRepository = AccountRepository()

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

    fun createAccount(createAccount: CreateAccount): Unit{
        val account: Account = AccountFactory.getAccount(createAccount.accountType)
        account.name = createAccount.customerName
        account.accountNumber = generateAccountNumber()
        setBankAndBranch(account)
        accountRepository.create(account)
    }

    fun withdrawAccount(withdrawAccount: WithdrawAccount): Unit{
        val account: Account = accountRepository.findByAccountNumber(withdrawAccount.accountNumber)
        if(account.balance < withdrawAccount.amount){
            throw InSufficientBalanceException("Account balance is low")
        }
        account.balance -= withdrawAccount.amount
        accountRepository.update(account)
    }

    fun creditAccount(creditAccount: CreditAccount): Unit{
        val account: Account = accountRepository.findByAccountNumber(creditAccount.accountNumber)
        account.balance += creditAccount.amount
        accountRepository.update(account)
    }

    fun printAccount(accountNumber: String): Unit{
        val account: Account = accountRepository.findByAccountNumber(accountNumber)
        account.printAccountInfo()
    }

    fun transferAccount(transferAccount: TransferAccount): Unit{
        val fromAccount = accountRepository.findByAccountNumber(transferAccount.fromAccountNumber)
        val toAccount = accountRepository.findByAccountNumber(transferAccount.toAccountNumber)
        if(fromAccount.balance < transferAccount.amount){
            throw InSufficientBalanceException("Account balance is low")
        }
        toAccount.balance += transferAccount.amount
        fromAccount.balance -= transferAccount.amount
        accountRepository.update(fromAccount)
        accountRepository.update(toAccount)
    }

    fun listAccounts(): Unit{
        accountRepository.findAll().forEach {
            it.printAccountInfo()
        }
    }
}