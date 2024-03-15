package account

class AccountFactory {
    companion object {
        fun getAccount(accountType: AccountType): Account = when(accountType){
            AccountType.CURRENT -> CurrentAccount()
            AccountType.SAVINGS -> SavingsAccount()
        }
    }
}