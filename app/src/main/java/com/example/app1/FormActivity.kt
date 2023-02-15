package com.example.app1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.app1.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener(this::nextActivity)
    }

    /**
     * This function is called when the next button is clicked.
     */
    private fun nextActivity(view: View) {
        val messageIntent = Intent(this, ResultActivity::class.java)
        messageIntent.putExtra("firstName", binding.firstName.text.toString())
        messageIntent.putExtra("lastName", binding.lastName.text.toString())
        this.startActivity(messageIntent)
    }
}