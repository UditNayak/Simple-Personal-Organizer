/*
    Name: Udit Kumar Nayak
    Rollno: 2023EBCS701
*/

package com.udit.simplepersonalorganizer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Personal Organizer"
        supportActionBar?.subtitle = "Built by Udit • 2023EBCS701"

        dbHelper = DatabaseHelper(this)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnViewAll = findViewById<Button>(R.id.btnViewAll)

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please enter both fields", Toast.LENGTH_SHORT).show()
            } else {
                val id = dbHelper.insertUser(name, email)
                if (id > 0) {
                    Toast.makeText(this, "Saved (ID=$id)", Toast.LENGTH_SHORT).show()
                    etName.text.clear()
                    etEmail.text.clear()
                } else {
                    Toast.makeText(this, "Save failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Navigate to UserListActivity
        btnViewAll.setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }
    }
}