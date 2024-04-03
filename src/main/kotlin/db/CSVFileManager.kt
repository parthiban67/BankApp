package db

import bank.account.Account
import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

class CSVFileManager(private val lineMapper: DefaultLineMapper, val path: String) {

    fun write(account: Account): Unit{
        val fields = lineMapper.convertToFields(account)
        val filePath = Path(path)

    }

    fun append(account: Account): Unit{
        val fields = lineMapper.convertToFields(account)
        val line = fields.joinToString { "\"$it\"" } + System.lineSeparator()
        val file = File(path)
        file.appendText(line)
    }

    fun read(): Account{
        val fields = mutableListOf<String>()
        return lineMapper.convertToEntity(fields)
    }

    fun readAll(): List<Account>{
        val lines = Files.readAllLines(Path(path))
        return lines.map{it ->
            val fields = it.split(", ").map{its -> its.substring(1,its.length-1)}
            lineMapper.convertToEntity(fields)
        }
    }
}