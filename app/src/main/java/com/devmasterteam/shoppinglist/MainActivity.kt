package com.devmasterteam.shoppinglist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.devmasterteam.shoppinglist.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productsAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productsAdapter = ProductAdapter(this)
        productsAdapter.addAll(productsGlobal)
        binding.listProducts.adapter = productsAdapter

        binding.btnInsertProducts.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_insert_products) {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = binding.listProducts.adapter as ProductAdapter
        adapter.clear()
        adapter.addAll(productsGlobal)

        val soma = productsGlobal.sumOf { it.value * it.quantity }
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        binding.txtTotal.text = "TOTAL: ${ f.format(soma) }"
    }
}