package account

class CurrentAccount: Account(AccountType.CURRENT) {

    private val withdrawRatePerTxn = 2.0

    final override fun withdraw(amount: Double) {
        val debitAmount = withdrawRatePerTxn + amount;
        super.debit(debitAmount);
    }
}