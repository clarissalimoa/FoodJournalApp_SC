package id.ac.ubaya.informatika.foodjournalapp_sc.util


import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodDatabase
import id.ac.ubaya.informatika.foodjournalapp_sc.model.UserDatabase

const val DB_NAME1 = "foodjournaldb1";
const val DB_NAME2 = "foodjournaldb2";
fun buildDb1(context: Context):UserDatabase {
    val db1 = Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME1).build()
    return db1
}

fun buildDb2(context: Context):FoodDatabase {
    val db2 = Room.databaseBuilder(context, FoodDatabase::class.java, DB_NAME2).build()
    return db2
}




