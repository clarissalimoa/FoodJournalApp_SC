package id.ac.ubaya.informatika.foodjournalapp_sc.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="age")
    var age:Int,
    @ColumnInfo(name="gender")
    var gender:String,
    @ColumnInfo(name="height")
    var height:Int,
    @ColumnInfo(name="weight")
    var weight:Int,
    @ColumnInfo(name="goal")
    var goal:String,
    @ColumnInfo(name="bmr")
    var bmr:Double,
    @ColumnInfo(name="caloriesTarget")
    var caloriesTarget:Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}

@Entity
data class Food(
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="calories")
    var calories:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}

@Entity
data class FoodHistory(
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="calories")
    var calories:String,
    @ColumnInfo(name="date")
    var date:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}