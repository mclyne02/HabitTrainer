package com.example.habittrainer.db

import android.provider.BaseColumns

val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 10

// Note, BAseColumns is a class you can inherit from that allows you to define a schema type file or table of what a certain type of object should look like in the table/db/schema
// This will be the definition of a 'Habit' to store in the db
object HabitEntry : BaseColumns {
    val TABLE_NAME = "habit"
    val _ID = "id"
    val TITLE_COL = "title"
    val DESCR_COL = "description"
    val IMAGE_COL = "image"
}