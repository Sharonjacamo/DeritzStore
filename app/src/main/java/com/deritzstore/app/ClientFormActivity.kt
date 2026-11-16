
package com.deritzstore.app

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deritzstore.app.domain.model.Client

class ClientFormActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtAddress: EditText
    private lateinit var txtNotes: EditText
    private lateinit var chkActive: CheckBox

    private var isEditMode = false
    private var currentClientId = 0

    private val viewModel
        get() = StoreData.clientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_client_form)

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

        txtName = findViewById(R.id.etClientName)
        txtPhone = findViewById(R.id.etClientPhone)
        txtEmail = findViewById(R.id.etClientEmail)
        txtAddress = findViewById(R.id.etClientAddress)
        txtNotes = findViewById(R.id.etClientNotes)
        chkActive = findViewById(R.id.cbClientActive)

        val btnSave = findViewById<Button>(R.id.btnSaveClient)
        val btnCancel = findViewById<Button>(R.id.btnCancelClient)

        val clientId = intent.getIntExtra("clientId", 0)

        if (clientId > 0) {
            searchClient(clientId)
        }

        btnSave.setOnClickListener {
            saveClient()
        }

        btnCancel.setOnClickListener {
            cleanScreen()
            finish()
        }
    }

    private fun searchClient(id: Int) {
        val client = viewModel.getClientById(id)

        if (client == null) {
            Toast.makeText(
                this,
                R.string.msg_client_not_found,
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        currentClientId = client.id
        isEditMode = true

        txtName.setText(client.fullName)
        txtPhone.setText(client.phone)
        txtEmail.setText(client.email)
        txtAddress.setText(client.address)
        txtNotes.setText(client.notes)
        chkActive.isChecked = client.isActive
    }

    private fun isValidationData(): Boolean {
        if (txtName.text.toString().trim().isEmpty()) {
            txtName.error = getString(R.string.required_field)
            txtName.requestFocus()
            return false
        }

        if (txtPhone.text.toString().trim().isEmpty()) {
            txtPhone.error = getString(R.string.required_field)
            txtPhone.requestFocus()
            return false
        }

        if (txtAddress.text.toString().trim().isEmpty()) {
            txtAddress.error = getString(R.string.required_field)
            txtAddress.requestFocus()
            return false
        }

        return true
    }

    private fun saveClient() {
        if (!isValidationData()) return

        val client = Client(
            id = if (isEditMode) currentClientId
            else (viewModel.getClients().maxOfOrNull { it.id } ?: 0) + 1,
            fullName = txtName.text.toString().trim(),
            phone = txtPhone.text.toString().trim(),
            email = txtEmail.text.toString().trim(),
            address = txtAddress.text.toString().trim(),
            notes = txtNotes.text.toString().trim(),
            isActive = chkActive.isChecked
        )

        if (isEditMode) {
            AlertDialog.Builder(this)
                .setTitle(R.string.confirm_action)
                .setMessage(R.string.confirm_update_client)
                .setPositiveButton(R.string.confirm) { _, _ ->
                    viewModel.updateClient(client)
                    Toast.makeText(
                        this,
                        R.string.msg_client_updated,
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        } else {
            viewModel.addClient(client)
            Toast.makeText(
                this,
                R.string.msg_client_saved,
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun cleanScreen() {
        txtName.text.clear()
        txtPhone.text.clear()
        txtEmail.text.clear()
        txtAddress.text.clear()
        txtNotes.text.clear()
        chkActive.isChecked = true
        isEditMode = false
        currentClientId = 0
    }
}
