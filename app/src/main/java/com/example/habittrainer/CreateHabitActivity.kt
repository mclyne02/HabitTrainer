package com.example.habittrainer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_create_habit.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {

    private val TAG = CreateHabitActivity::class.java.simpleName

    private val CHOOSE_IMAGE_REQUEST = 1

    // Class level variable to hold if an img bitmap has been chosen
    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)

        btn_choose_image.text = getString(R.string.choose_image_btn)
        btn_save.text = getString(R.string.save_habit_btn)
    }

    // This function, along with any other click listener button function must take an argument of type View
    fun storeHabit(@Suppress("UNUSED_PARAMETER")v: View) {
        // This function will validate the inputs and use our text view to display to the user if there is anything missing
        if (et_title.text.toString().isBlank() || et_description.text.toString().isBlank()) {
            Log.d(TAG, "No habit is stored: title or description missing")
            displayErrorMessage("Your habit needs an engaging title and description.")
            return
        } else if (imageBitmap == null) {
            // Check if an image bitmap has been chosen, to do this reference the class level variable
            Log.d(TAG, "No habit is stored: image missing")
            displayErrorMessage("Your habit needs an engaging title and description.")
            return
        }
    }

    fun displayErrorMessage(s: String) {

    }

    // This function, along with any other click listener button function must take an argument of type View
    fun chooseImage(@Suppress("UNUSED_PARAMETER")v: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Choose image for habit")

        // Simple way to start the activity
        // startActivity(chooser)

        // Preferred way to show the image as a preview
        // Needs Intent and requestCode (which is just an identifier for us to know what kind of request we are currently getting back
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)

        // Logging Example
        // Note, the .d is for debug, there is Log.e for error Log.i for info etc
        // Note, the TAG variable here is defined above and is the value description to which the log will be stored
        Log.d(TAG, "Intent to choose image sent...")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null) {
            Log.d(TAG, "An image was chosen by the user")

            // Create a function to read the data
            val bitmap = tryReadBitMap(data.data)

            // This block helps us reduce the nullability in the code by saying only execute this block if the bitmap object is NOT null
            bitmap?.let {
                // When the bitmap is chosen and it is not null, set the class level bitmap variable to the bitmap choosen
                this.imageBitmap = bitmap
                iv_image.setImageBitmap(bitmap)
                Log.d(TAG, "Read the image bitmap and updates the image view")
            }
        }
    }

    // Note, since we are doing the return from the try catch block, the return is null in the catch for the Bitmap return object of the function when the catch block is hit
    // this is why the Bitmap is using the null optional parameter '?' at the end, i.e. Bitmap?
    private fun tryReadBitMap(data: Uri?): Bitmap? {
        return try {
            // Note, this getBitmap is grabbing the contentResolver property directly and thereby calling the method needed
            // Note, this line below is also an expression that returns the actual return type in Kotlin
            MediaStore.Images.Media.getBitmap(contentResolver, data)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }

    }
}
