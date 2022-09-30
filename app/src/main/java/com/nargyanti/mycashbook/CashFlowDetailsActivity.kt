package com.nargyanti.mycashbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nargyanti.mycashbook.adapter.TransactionAdapter
import com.nargyanti.mycashbook.database.DatabaseHelper
import com.nargyanti.mycashbook.model.TransactionModel

class CashFlowDetailsActivity : AppCompatActivity() {
    private lateinit var rvTransaction : RecyclerView
    private var dbHandler : DatabaseHelper?= null
    private var transactionList: List<TransactionModel> = ArrayList<TransactionModel>()
    private var userId = 0

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }

    override fun onRestart() {
        super.onRestart()
        showRecyclerList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_flow_details)
        supportActionBar?.title = "Detail Cash Flow"

        rvTransaction = findViewById(R.id.rv_transaction)
        rvTransaction.setHasFixedSize(true)

        userId = intent.getIntExtra(MainActivity.EXTRA_USER_ID, 0)
        dbHandler = DatabaseHelper(this)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        transactionList = dbHandler!!.getAllTransactionsByUserId(userId)
        rvTransaction.layoutManager = LinearLayoutManager(this)
        val transactionAdapter = TransactionAdapter(transactionList)
        rvTransaction.adapter = transactionAdapter
    }
}