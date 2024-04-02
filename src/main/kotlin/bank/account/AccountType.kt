package bank.account

enum class AccountType {
    SAVINGS,
    CURRENT;

    companion object {
        fun getType(type: String) = when (type) {
            "SAVINGS" -> SAVINGS
            "CURRENT" -> CURRENT
            else -> throw IllegalArgumentException("Not an account type")
        }
    }
}