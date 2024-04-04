package db

import bank.account.Account
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

class CSVFileManager(private val lineMapper: DefaultLineMapper, private val path: String) {

    fun writeAll(account: Account): Unit{
        val accountsLines = readAll().map{
            if(it.accountNumber == account.accountNumber) account else it
        }.map{
            transformToWritable(it)
        }
        val file = File(path)
        accountsLines.forEach{
            file.writeText(it)
        }
    }

    private fun transformToWritable(account: Account): String{
        val fields = lineMapper.convertToFields(account)
        val line = fields.joinToString { "\"$it\"" } + System.lineSeparator()
        return line
    }

    fun append(account: Account): Unit{
        val line = transformToWritable(account)
        val file = File(path)
        file.appendText(line)
    }

    fun readAll(): List<Account>{
        val lines = Files.readAllLines(Path(path))
        return lines.map{it ->
            val fields = it.split(", ").map{its -> its.substring(1,its.length-1)}
            lineMapper.convertToEntity(fields)
        }
    }
}