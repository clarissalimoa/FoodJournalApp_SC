package id.ac.ubaya.informatika.foodjournalapp_sc.model


import androidx.room.*

@Dao
interface FoodHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg foodHistory: FoodHistory)

    @Query("SELECT * FROM foodHistory WHERE date= :date")
    suspend fun selectToday(date: String): List<FoodHistory>

    @Query("SELECT SUM(calories) FROM foodHistory WHERE date= :date")
    suspend fun selectTodayTotalCalories(date: String): Int

    @Query("SELECT * FROM foodHistory")
    suspend fun selectAll(): List<FoodHistory>
}
