package id.ac.ubaya.informatika.foodjournalapp_sc.model

import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:Food)

    @Query("SELECT * FROM food")
    suspend fun selectAllFood(): List<Food>

    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun selectFood(id:Int): Food

}
