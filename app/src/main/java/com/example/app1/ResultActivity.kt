package com.example.app1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        println("firstName: $firstName")
        println("lastName: $lastName")

        if (savedInstanceState != null) {
            binding.firstNameText.text = savedInstanceState.getString("firstName")
            binding.lastNameText.text = savedInstanceState.getString("lastName")
        } else {

        }
    }
}