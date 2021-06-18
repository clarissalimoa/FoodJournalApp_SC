package id.ac.ubaya.informatika.foodjournalapp_sc.model


import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

    @Query("SELECT * FROM user ORDER BY priority DESC")
    suspend fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE is_done=0 ORDER BY priority DESC")
    suspend fun selectAllUndoneUser(): List<User>

    @Query("SELECT * FROM user WHERE uuid= :id")
    suspend fun selectUser(id:Int): User

    @Query("UPDATE user SET title=:title, notes=:notes, priority=:priority" +
            " WHERE uuid = :id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE user SET is_done=1" +
            " WHERE uuid = :id")
    suspend fun done(id:Int)


    @Delete
    suspend fun deleteUser(user:User)
}
