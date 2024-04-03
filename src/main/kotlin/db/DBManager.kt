package db

import bank.account.Account
import exception.AccountNotFoundException
import java.nio.file.Files
import kotlin.io.path.Path

class DBManager {

    private val accountStore = mutableMapOf<String, Account>()
    private val currentPath = "db/file0000"
    private val csvFileManager: CSVFileManager = CSVFileManager(DefaultLineMapper(),currentPath)

    init{
        val path = Path(currentPath)
        if(Files.notExists(path)){
            Files.createFile(path)
        }
    }
    init{
        val accounts: List<Account> = csvFileManager.readAll()
        accounts.forEach { accountStore[it.accountNumber] = it }
    }

    fun insert(account: Account): Unit{
        accountStore[account.accountNumber] = account
        csvFileManager.append(account)
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