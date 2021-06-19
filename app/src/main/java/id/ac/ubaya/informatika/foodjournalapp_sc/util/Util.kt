package id.ac.ubaya.informatika.foodjournalapp_sc.util


import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.informatika.foodjournalapp_sc.model.UserDatabase

const val DB_NAME = "foodjournaldb";
fun buildDb(context: Context):UserDatabase {
    val db = Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME)
        .build()
    return db
}



