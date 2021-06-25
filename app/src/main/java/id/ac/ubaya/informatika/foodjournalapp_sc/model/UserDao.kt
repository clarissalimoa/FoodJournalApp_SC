package id.ac.ubaya.informatika.foodjournalapp_sc.model


import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uuid= :id")
    suspend fun selectUser(id:Int): User

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun selectCurrentUser(): User

    @Query("SELECT COUNT(*) FROM user")
    suspend fun selectJumlah():Int

//    @Query("UPDATE user SET name=:name, age=:age, gender=:gender, " +
//            " height=:height, weight=:weight, goal=:goal, bmr:=bmr, caloriesTarget:=caloriesTarget" +
//            " WHERE uuid = :id")
//    suspend fun update(id:Int, name:String, age:Int, gender:String, height:Int, weight:Int, goal:String, bmr:Double, caloriesTarget:Int)

    @Delete
    suspend fun deleteUser(user:User)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAllFood(vararg food:Food)
//
//    @Query("SELECT * FROM food")
//    suspend fun selectAllFood(): List<Food>
//
//    @Query("SELECT * FROM food WHERE id= :id")
//    suspend fun selectFood(id:Int): Food

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg foodHistory: FoodHistory)

}
