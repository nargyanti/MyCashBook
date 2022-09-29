package com.nargyanti.mycashbook.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.nargyanti.mycashbook.model.TransactionModel

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private val DB_NAME = "my_cash_book"
        private val DB_VERSION = 1

        private val TABLE_USERS = "users"
        private val TABLE_TRANSACTIONS = "transactions"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_USERS =
            "CREATE TABLE IF NOT EXISTS $TABLE_USERS ('id' INTEGER, 'username' TEXT NOT NULL UNIQUE, 'password' TEXT NOT NULL, PRIMARY KEY('id' AUTOINCREMENT));"
        val CREATE_TABLE_TRANSACTIONS =
            "CREATE TABLE IF NOT EXISTS $TABLE_TRANSACTIONS ('id' INTEGER, 'date' TEXT NOT NULL, 'description' TEXT NOT NULL, 'category' TEXT NOT NULL, 'user_id' INTEGER, PRIMARY KEY('id' AUTOINCREMENT), FOREIGN KEY('user_id') REFERENCES 'Users'('id'));"

        val INSERT_TABLE_TRANSACTIONS = "INSERT INTO $TABLE_USERS ('username', 'password') VALUES ('user', 'user');"
        db?.execSQL(CREATE_TABLE_USERS)
        db?.execSQL(CREATE_TABLE_TRANSACTIONS)

        db?.execSQL(INSERT_TABLE_USERS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_USERS = "DROP TABLE IF EXISTS 'users'"
        val DROP_TABLE_TRANSACTIONS = "DROP TABLE IF EXISTS 'transactions'"

        db?.execSQL(DROP_TABLE_USERS)
        db?.execSQL(DROP_TABLE_TRANSACTIONS)
    }

    fun getUserByUsername(_username: String) : UserModel {
        val user = UserModel()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_USERSUSERS WHERE 'username' = $_username"
        val cursor = db.rawQuery(selectQuery, null)
        cursor.moveToFirst()
        user.id = cursor.getInt(cursor.getColumnIndex("id"))
        user.username = cursor.getString(cursor.getColumnIndex("username"))
        user.password = cursor.getString(cursor.getColumnIndex("password"))
        cursor.close()
        return user
    }

    fun getAllTransactionsByUserId(_userId: Int): List<TransactionModel> {
        val transactionModel = ArrayList<TransactionModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_TRANSACTIONS WHERE 'user_id' = $_userId;"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val transaction = TransactionModel()
                    transaction.id = cursor.getInt(cursor.getColumnIndex("id"))
                    transaction.date = cursor.getString(cursor.getColumnIndex("date"))
                    transaction.description = cursor.getString(cursor.getColumnIndex("description"))
                    transaction.category = cursor.getString(cursor.getColumnIndex("category"))
                    transaction.user_id = cursor.getInt(cursor.getColumnIndex("user_id"))
                    transactionList.add(transaction)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return transactionList
    }

    fun getAllExpenseByUserId(_userId: Int): List<TransactionModel> {
        val transactionModel = ArrayList<TransactionModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_TRANSACTIONS WHERE 'user_id' = $_userId AND 'category' = 'pengeluaran';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val transaction = TransactionModel()
                    transaction.id = cursor.getInt(cursor.getColumnIndex("id"))
                    transaction.date = cursor.getString(cursor.getColumnIndex("date"))
                    transaction.description = cursor.getString(cursor.getColumnIndex("description"))
                    transaction.category = cursor.getString(cursor.getColumnIndex("category"))
                    transaction.user_id = cursor.getInt(cursor.getColumnIndex("user_id"))
                    transactionList.add(transaction)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return transactionList
    }

    fun getAllIncomeByUserId(_userId: Int): List<TransactionModel> {
        val transactionModel = ArrayList<TransactionModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_TRANSACTIONS WHERE 'user_id' = $_userId AND 'category' = 'pemasukan';"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val transaction = TransactionModel()
                    transaction.id = cursor.getInt(cursor.getColumnIndex("id"))
                    transaction.date = cursor.getString(cursor.getColumnIndex("date"))
                    transaction.description = cursor.getString(cursor.getColumnIndex("description"))
                    transaction.category = cursor.getString(cursor.getColumnIndex("category"))
                    transaction.user_id = cursor.getInt(cursor.getColumnIndex("user_id"))
                    transactionList.add(transaction)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return transactionList
    }
}