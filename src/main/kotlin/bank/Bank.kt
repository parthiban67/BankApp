package bank

import account.Account
import exception.AccountNotFoundException
import kotlin.random.Random

class Bank {

    private val accounts = mutableListOf<Account>()

    private val accountMap = mutableMapOf<String,Int>()

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
            //bAccount.branchName= account.branchName
        }
    }

    private fun generateAccountNumber(): String{
        var accountNumber = ""
        for( i in 1..15){
            accountNumber += charMap[Random.nextInt(0,9)]
        }
        return accountNumber
    }
}