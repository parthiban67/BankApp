package db

import bank.account.Account
import bank.account.AccountFactory
import bank.account.AccountType

class DefaultLineMapper {

    fun convertToFields(account: Account): List<String>{
        val fields = mutableListOf<String>()
        fields.add(account.accountNumber)
        fields.add(account.name)
        fields.add(account.balance.toString())
        fields.add(account.accountType.name)
        return fields
    }

    fun convertToEntity(fields: List<String>): Account{
        val accountType = AccountType.getType(fields[3])
        val account = AccountFactory.getAccount(accountType)
        account.accountNumber = fields[0]
        account.name = fields[1]
        account.balance = fields[2].toDouble()
        return account;
    }
}