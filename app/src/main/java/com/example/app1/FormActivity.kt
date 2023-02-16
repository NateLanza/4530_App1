package com.example.app1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.app1.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private var thumbnail : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener(this::nextActivity)
        binding.picButton.setOnClickListener(this::takePicture)
        binding.picView.visibility = View.INVISIBLE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("thumbnail", thumbnail)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        thumbnail = savedInstanceState.getParcelable("thumbnail", Bitmap::class.java)
        if (thumbnail != null) {
            binding.picView.setImageBitmap(thumbnail)
            binding.picView.visibility = View.VISIBLE
            binding.picButton.visibility = View.INVISIBLE
        }
    }

    /**
     * This function is called when the camera button is clicked.
     */
    private fun takePicture(view: View) {
        // Create an intent to open the camera
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Start the camera activity and catch exception
        try {
            cameraActivity.launch(cameraIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Camera not found", Toast.LENGTH_SHORT).show()
        }
    }

    private val cameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // Set the image bitmap
            var bitmap = result.data!!.extras!!.getParcelable("data", Bitmap::class.java)
            (findViewById<View>(R.id.picView) as ImageView).setImageBitmap(bitmap)
            // Make the image visible
            binding.picView.visibility = View.VISIBLE
            binding.picButton.visibility = View.INVISIBLE
            // Save data
            thumbnail = bitmap
        }
    }

    /**
     * This function is called when the next button is clicked.
     */
    private fun nextActivity(view: View) {
        // Make sure the editTexts are not empty
        if (binding.firstName.text.toString().isBlank() || binding.lastName.text.toString().isBlank()) {
            // Show a toast message
            Toast.makeText(this, "Please enter your first and last name", Toast.LENGTH_SHORT).show()
            // Exit function
            return
        }

        // Send data to the next activity
        val messageIntent = Intent(this, ResultActivity::class.java)
        messageIntent.putExtra("firstName", binding.firstName.text.toString())
        messageIntent.putExtra("lastName", binding.lastName.text.toString())
        this.startActivity(messageIntent)
    }
}