package bank.dto

import bank.account.AccountType

data class CreateAccount(val customerName: String, val accountType: AccountType)
