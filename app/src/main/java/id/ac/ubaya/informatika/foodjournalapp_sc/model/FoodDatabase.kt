package id.ac.ubaya.informatika.foodjournalapp_sc.model


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Food::class), version = 2)
abstract class FoodDatabase:RoomDatabase() {
    //abstract fun userDao(): UserDao
    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile private var instance:FoodDatabase ?= null

        private val LOCK = Any()
        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodDatabase::class.java,
                "foodjournaldb2")
                .addMigrations()
                .build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}





