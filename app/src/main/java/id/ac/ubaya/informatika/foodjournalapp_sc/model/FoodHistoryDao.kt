package id.ac.ubaya.informatika.foodjournalapp_sc.model


import androidx.room.*

@Dao
interface FoodHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg foodHistory: FoodHistory)

    @Query("SELECT * FROM foodhistory WHERE date LIKE :date")
    suspend fun selectToday(date: String): List<FoodHistory>
}
