package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.app1.databinding.ActivityFormBinding
import com.example.app1.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        binding = ActivityResultBinding.inflate(layoutInflater)

        // Get intent
        val intent = intent

        // Assign text fields based on input
        val firstNameField = findViewById<TextView>(R.id.firstNameText)
        val lastNameField = findViewById<TextView>(R.id.lastNameText)
        if (savedInstanceState != null) {
            firstNameField.text = "First Name: " + savedInstanceState.getString("firstName")
            lastNameField.text = "Last Name: " + savedInstanceState.getString("lastName")
        } else {
            firstNameField.text = "First Name: " + intent.getStringExtra("firstName")
            lastNameField.text = "Last Name: " + intent.getStringExtra("lastName")
        }
    }
}