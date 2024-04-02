package db

import bank.account.Account

class AccountRepository {

    private val dbManager = DBManager()

    fun create(account: Account): Unit{
        dbManager.insert(account)
    }

    fun update(account: Account): Unit{
        dbManager.update(account)
    }

    fun findByAccountNumber(accountNumber: String): Account{
        return dbManager.select(accountNumber)
    }

    fun findAll(): List<Account>{
        return dbManager.selectAll()
    }
}