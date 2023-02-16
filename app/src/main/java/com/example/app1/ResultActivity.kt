package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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
        val nameField = findViewById<TextView>(R.id.resultText)
        val name: String = if (savedInstanceState != null) {
            savedInstanceState.getString("firstName") + " " + savedInstanceState.getString("lastName")
        } else {
            intent.getStringExtra("firstName") + " " + intent.getStringExtra("lastName")
        }
        nameField.text = "$name is logged in!"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("firstName", intent.getStringExtra("firstName"))
        outState.putString("lastName", intent.getStringExtra("lastName"))
    }
}