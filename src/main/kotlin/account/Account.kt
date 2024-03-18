package account

import java.lang.IllegalArgumentException;

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
    val bankName: String = "ABC Bank"
    var branchName: String = ""
    abstract fun withdraw(amount: Double)

    fun credit(amount: Double){
        balance += amount;
    }

    fun debit(amount: Double){
        if(amount > balance){
            throw IllegalArgumentException("Amount exceeds your balance")
        }
        balance -= amount;
    }

    fun printAccountType(): Unit{
        println("Account Type is ${accountType.name}");
    }
}