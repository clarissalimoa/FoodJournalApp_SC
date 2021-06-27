package id.ac.ubaya.informatika.foodjournalapp_sc.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg history: History)

    @Query("SELECT totalCalory FROM history WHERE date= :date LIMIT 1")
    suspend fun selecttotalCalory(date: String): Int

    @Query("SELECT * FROM history WHERE date LIKE :date")
    suspend fun selectAllHistory(date: String): List<History>

    @Query("SELECT COUNT(foodCount) FROM history WHERE date=:date1")
    suspend fun selectJumlahHistory(date1: String): Int

    @Query("UPDATE history SET totalCalory=:totalCalory1+totalCalory, foodCount=foodCount+1, status=:status1 WHERE date = :date1")
    suspend fun updatehistory(totalCalory1: Int, status1: String, date1: String)

   // WHERE date= :date1

}
