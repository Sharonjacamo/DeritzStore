
package com.deritzstore.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        val btnClients = findViewById<Button>(R.id.btnClients)
        val btnProducts = findViewById<Button>(R.id.btnProducts)
        val btnSales = findViewById<Button>(R.id.btnSales)
        val btnPayments = findViewById<Button>(R.id.btnPayments)
        val btnMap = findViewById<Button>(R.id.btnMap)

        btnClients.setOnClickListener {
            val intent = Intent(this, ClientActivity::class.java)
            startActivity(intent)
        }

        btnProducts.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

        btnSales.setOnClickListener {
            val intent = Intent(this, SaleListActivity::class.java)
            startActivity(intent)
        }

        btnPayments.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        btnMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }
    }
}
