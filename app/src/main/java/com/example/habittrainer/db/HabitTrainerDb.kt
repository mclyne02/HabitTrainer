package com.example.habittrainer.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// This class will be our database helper class, which will be used to create, destroy and recreate the db as needed
class HabitTrainerDb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // SQL command to create the schema and table
    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${HabitEntry.TABLE_NAME} (" +
            "${HabitEntry._ID} INTEGER PRIMARY KEY," +
            "${HabitEntry.TITLE_COL} TEXT," +
            "${HabitEntry.DESCR_COL} TEXT," +
            "${HabitEntry.IMAGE_COL} BLOB" +
            ")"

    // SQL to Delete all the tables we have
    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${HabitEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    // This will be executed whenever the database version is updated, so for example, when you update the database from version 10 (1.0) to 11 (1.1)
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Run the delete entries SQL to delete the existing tables we have
        db.execSQL(SQL_DELETE_ENTRIES)
        // Run the onCreate to make our tables after deletion 
        onCreate(db)
    }
}