package db

import bank.account.Account

class CSVFileManager(private val lineMapper: DefaultLineMapper, val path: String) {

    fun write(account: Account): Unit{
        val fields = lineMapper.convertToFields(account)
    }

    fun read(): Account{
        val fields = mutableListOf<String>()
        return lineMapper.convertToEntity(fields)
    }
}