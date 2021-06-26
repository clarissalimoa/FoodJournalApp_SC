package id.ac.ubaya.informatika.foodjournalapp_sc.model

import androidx.room.*

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg history: History)

    @Query("SELECT totalCalory FROM history WHERE date = :date")
    suspend fun selecttotalCalory(date:String): Int

    @Query("SELECT * FROM history WHERE date LIKE :date")
    suspend fun selectAllHistory(date: String): List<History>

    @Query("SELECT count(*) as oke FROM history WHERE date LIKE :date")
    suspend fun selectJumlahHistory(date: String): Int

    @Query("UPDATE history SET totalCalory=:totalCalory1+totalCalory, foodCount=foodCount+1, status=:status1 WHERE date = :date1")
    suspend fun updatehistory(totalCalory1:Int, status1:String, date1:String)

}
