
package com.deritzstore.app

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deritzstore.app.domain.model.Client

class ClientActivity : AppCompatActivity() {

    private lateinit var txtSearch: EditText
    private lateinit var clientListContainer: LinearLayout

    private val viewModel
        get() = StoreData.clientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_client)

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

        txtSearch = findViewById(R.id.etSearchClient)

        clientListContainer = findViewById(
            R.id.clientListContainer
        )

        val btnAdd = findViewById<Button>(
            R.id.btnAddClient
        )

        btnAdd.setOnClickListener {
            val intent = Intent(
                this,
                ClientFormActivity::class.java
            )
            startActivity(intent)
        }

        txtSearch.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    searchClients(s.toString())
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()

        searchClients(
            txtSearch.text.toString()
        )
    }

    private fun searchClients(query: String) {

        val clients = if (query.isBlank()) {
            viewModel.getClients()
        } else {
            viewModel.searchClients(query)
        }

        showClients(clients)
    }

    private fun showClients(clients: List<Client>) {

        clientListContainer.removeAllViews()

        if (clients.isEmpty()) {

            val txtEmpty = TextView(this)

            txtEmpty.text = getString(
                R.string.msg_no_clients
            )

            txtEmpty.textSize = 16f

            txtEmpty.setPadding(16, 24, 16, 24)

            clientListContainer.addView(txtEmpty)

            return
        }

        for (client in clients) {

            val card = LinearLayout(this)

            card.orientation = LinearLayout.VERTICAL

            card.setPadding(20, 20, 20, 20)

            card.setBackgroundResource(
                android.R.drawable.dialog_holo_light_frame
            )

            val cardParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            cardParams.bottomMargin = 16

            card.layoutParams = cardParams

            val txtName = TextView(this)
            txtName.text = client.fullName
            txtName.textSize = 20f

            val txtPhone = TextView(this)
            txtPhone.text = getString(
                R.string.client_phone_detail,
                client.phone
            )

            val txtAddress = TextView(this)
            txtAddress.text = getString(
                R.string.client_address_detail,
                client.address
            )

            val txtStatus = TextView(this)
            txtStatus.text = if (client.isActive) {
                getString(R.string.client_status_active)
            } else {
                getString(R.string.client_status_inactive)
            }

            val buttonContainer = LinearLayout(this)

            buttonContainer.orientation =
                LinearLayout.HORIZONTAL

            val btnEdit = Button(this)
            btnEdit.text = getString(
                R.string.client_edit_action
            )

            val btnDelete = Button(this)
            btnDelete.text = getString(
                R.string.client_delete_action
            )

            btnEdit.setOnClickListener {
                editClient(client.id)
            }

            btnDelete.setOnClickListener {
                deleteClient(client.id)
            }

            buttonContainer.addView(
                btnEdit,
                LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
            )

            buttonContainer.addView(
                btnDelete,
                LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f
                )
            )

            card.addView(txtName)
            card.addView(txtPhone)
            card.addView(txtAddress)
            card.addView(txtStatus)
            card.addView(buttonContainer)

            clientListContainer.addView(card)
        }
    }

    private fun editClient(id: Int) {

        val intent = Intent(
            this,
            ClientFormActivity::class.java
        )

        intent.putExtra("clientId", id)

        startActivity(intent)
    }

    private fun deleteClient(id: Int) {

        val client = viewModel.getClientById(id)

        if (client == null) {
            Toast.makeText(
                this,
                R.string.msg_client_not_found,
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        AlertDialog.Builder(this)
            .setTitle(R.string.confirm_action)
            .setMessage(R.string.confirm_delete_client)
            .setPositiveButton(
                R.string.confirm
            ) { _, _ ->

                viewModel.deleteClient(id)

                Toast.makeText(
                    this,
                    R.string.msg_client_deleted,
                    Toast.LENGTH_SHORT
                ).show()

                searchClients(
                    txtSearch.text.toString()
                )
            }
            .setNegativeButton(
                R.string.cancel,
                null
            )
            .show()
    }
}
