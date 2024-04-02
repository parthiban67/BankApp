package db

import bank.account.Account
import exception.AccountNotFoundException

class DBManager {

    private val accountStore = mutableMapOf<String, Account>()

    fun insert(account: Account): Unit{
        accountStore[account.accountNumber] = account
    }

    fun update(account: Account): Unit{
        accountStore[account.accountNumber] = account
    }

    fun select(accountNumber: String): Account{
        return accountStore[accountNumber] ?: throw AccountNotFoundException("Account not found")
    }

    fun delete(account: Account): Unit{
        accountStore.remove(account.accountNumber)
    }

    fun selectAll(): List<Account> {
        return accountStore.values.toList()
    }
}