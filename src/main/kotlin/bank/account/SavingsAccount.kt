package bank.account

class SavingsAccount: Account(AccountType.SAVINGS) {

    private val withdrawRatePerTxn = 1.0

    final override fun withdraw(amount: Double) {
        val debitAmount = withdrawRatePerTxn + amount;
        super.debit(debitAmount);
    }
}