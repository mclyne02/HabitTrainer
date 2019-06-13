package com.example.habittrainer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // Note: since we have this line in our build.gradle app project: apply plugin: 'kotlin-android-extensions'
    // We can access member variables directly

    // One way, to init the variable
    // private var tvDescription: TextView? = null
    // You can also use the lateinit keyword, which allows you to remove the ? nullable operator
    // private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tvDescription = findViewById(R.id.tv_description)
        // tvDescription.text = "A refreshing glass of water gets you hydrated"

        // The tv_description member variable loaded from that line in our build.gradle
        /*
        tv_description.text = getString(R.string.drink_water)
        tv_title.text = getString(R.string.drink_water_desc)
        iv_icon.setImageResource(R.drawable.water)
        */

        // Adapter -> defines the data you wish to use in the recycler view
        // To implement the recycler view -> implement 3 methods

        // This method tells the recycler view that the cards that it holds will be the same size and will not change
        // This helps with performance
        rv.setHasFixedSize(true)

        rv.layoutManager = LinearLayoutManager(this)

        // This adapter we define will be the main part of the work
        // We define some class that we create. In this case, it will be a list of habits
        // We will create some class "HabitsAdapter" that will store some list of habits (data we want to show in the recycler view)
        // The data will be displayed in our single card layouts
        rv.adapter = HabitsAdapter(getSampleHabits())
    }

    // Override onCreateOptionsMenu
    // This will attach a menu to the given activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Grab our custom menu from the menu resource directory and inflate it into the menu values passed into this method
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Add the functionality that will allow us to switch to the new activity
    // We have to override another method. this method is a listener that will be executed once an options item is selected
    // Note, an options item is just a menu item on android
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Check whether the item id of the selected item is equal to our R.id for adding a habit
        // What this is doing is checking which menu item is selected
        if (item.itemId == R.id.add_habit) {
            // Switch activities
            // To do this, you create an Intent()
            // First parameter (Source of the intent) is the context, which we can just use 'this' since the context is this activity we are using
            // Second parameter (Target of the intent) in this case, the new activity we want to allow the user to do
            // Note, usually you would do CreateHabitActivity.class in java, but since this is a kotlin class, we have to use the '::' and then class.java
            switchTo(CreateHabitActivity::class.java)
        }
        return true
    }

    // This is saying that the method accepts a parameter c of type Class of type any
    // Note, the <*> is just saying any class
    private fun switchTo(c: Class<*>) {
        val intent = Intent(this, c)
        // This startActivity(intent) will switch to the new activity from the current activity
        startActivity(intent)
    }
}
