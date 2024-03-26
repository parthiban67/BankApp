package bank

import bank.account.Account
import bank.account.AccountFactory
import bank.dto.CreateAccount
import bank.dto.WithdrawAccount
import exception.AccountNotFoundException
import exception.InSufficientBalanceException
import kotlin.random.Random

class Bank {

    private val accounts = mutableListOf<Account>()

    private val accountMap = mutableMapOf<String,Int>()
    private val accountStore = mutableMapOf<String,Account>()

    private val charMap = arrayOfNulls<Char>(10)
    init{
        var idx = 0
        for(n in 48..57){
             charMap[idx++] = n.toChar()
        }
    }

    fun createAccount(account: Account){
        account.accountNumber = generateAccountNumber()
        if(accounts.add(account)){
            val idx = accounts.size - 1
            accountMap[account.accountNumber] = idx
        }
    }

    fun updateAccount(account: Account){
        if(!accountMap.containsKey(account.accountNumber)){
            throw AccountNotFoundException("${account.accountNumber} is not found in Bank")
        }
        accountMap[account.accountNumber]?.let {
            val bAccount = accounts[it]
            bAccount.name = account.name
            //bAccount.branchName= bank.account.branchName
        }
    }

    fun getAccountByNumber(accountNumber: String): Account?{
        if(!accountMap.containsKey(accountNumber)){
            throw AccountNotFoundException("$accountNumber is not found in Bank")
        }
        return accountMap[accountNumber]?.let {
            accounts[it]
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
}