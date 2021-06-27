package id.ac.ubaya.informatika.foodjournalapp_sc.model


import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg foodHistory: FoodHistory)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg history: History)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg food:Food)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uuid= :id")
    suspend fun selectUser(id:Int): User

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun selectCurrentUser(): User

    @Query("SELECT COUNT(*) FROM user ")
    suspend fun selectJumlah():Int

    @Delete
    suspend fun deleteUser(user:User)
//
//    @Query("SELECT * FROM food")
//    suspend fun selectAllFood(): List<Food>
//
//    @Query("SELECT * FROM food WHERE id= :id")
//    suspend fun selectFood(id:Int): Food
//
//
//
//    @Query("SELECT totalCalory FROM history WHERE date = :date")
//   suspend fun selecttotalCalory(date:String): Int
//
//   @Query("SELECT * FROM history WHERE date LIKE :date")
//   suspend fun selectAllHistory(date: String): List<History>
//
//    @Query("SELECT count(*) as oke FROM history WHERE date LIKE :date")
//    suspend fun selectJumlahHistory(date: String): Int
//
    @Query("UPDATE user SET name=:name1 +age=:age1, gender=:gender1, height=:height1, weight=:weight1, goal=:goal1, bmr=:bmr1, caloriesTarget=:target1 WHERE uuid = :uuid1")
    suspend fun update(name1:String, age1 :Int, gender1: String, height1:Int, weight1:Int, goal1:String, bmr1:Double, target1:Int, uuid1:Int )


}
