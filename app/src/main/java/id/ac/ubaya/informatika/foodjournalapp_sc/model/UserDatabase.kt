package id.ac.ubaya.informatika.foodjournalapp_sc.model


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = arrayOf(User::class , Food::class, FoodHistory::class , History::class), version = 2)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance:UserDatabase ?= null

        private val LOCK = Any()

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                "foodjournaldb")
                    .addMigrations()
                    .fallbackToDestructiveMigration()
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





