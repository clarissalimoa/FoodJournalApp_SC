package id.ac.ubaya.informatika.foodjournalapp_sc.model

import androidx.room.*

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg food:Food)

    @Query("SELECT * FROM food")
    suspend fun selectAll(): List<Food>

    @Query("SELECT * FROM food WHERE id= :id")
    suspend fun selectFood(id:Int): Food

    @Delete
    fun delete(food:Food)

    @Update
    fun updateFood(vararg food:Food)
}
