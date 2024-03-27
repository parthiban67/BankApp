package bank.account

abstract class Account(private val accountType: AccountType) {
    var accountNumber: String = ""
    var name: String = ""
        set (value) {
            if(value.isBlank()){
                throw IllegalArgumentException("Name can't be blank")
            }
            field = value;
        }

    var balance: Double = 0.0
        set (value) {
            if(value < 0.0){
                throw IllegalArgumentException("Balance can't be below 0");
            }
            field = value;
        }
    lateinit var bankName: String
    lateinit var branchName: String

    fun printAccountInfo(): Unit{
        println("Account Information")
        println("Account number is $accountNumber")
        println("Customer name is $name")
        println("Account Type is ${accountType.name}")
        println("Current balance is $balance")
    }
}