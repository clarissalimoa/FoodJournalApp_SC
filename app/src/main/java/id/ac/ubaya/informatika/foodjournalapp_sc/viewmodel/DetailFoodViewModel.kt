package id.ac.ubaya.informatika.foodjournalapp_sc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Data
import id.ac.ubaya.informatika.foodjournalapp_sc.model.Food
import id.ac.ubaya.informatika.foodjournalapp_sc.model.FoodHistory
import id.ac.ubaya.informatika.foodjournalapp_sc.model.History
import id.ac.ubaya.informatika.foodjournalapp_sc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class DetailFoodViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val foodLD = MutableLiveData<Food>()
    var totalCalory = MutableLiveData<Data>()
    var angka1 = MutableLiveData<Int>()
    val totalFoodsCalories = MutableLiveData<Int>()
    private var jumlahHistory:Int = 0

    fun addFood(list:List<Food>) {
        launch {
            val db = buildDb(getApplication())
            db.foodDao().insertAll(*list.toTypedArray())
        }
    }

    fun addhistory(history: History) {
        launch {
            val db = buildDb(getApplication())
            db.historyDao().insertAll(history)
        }
    }

    fun addFoodHistory(list:List<FoodHistory>) {
        launch {
            val db = buildDb(getApplication())
            db.foodHistoryDao().insertAll(*list.toTypedArray())
        }
    }

    fun selectTotalCalory() {
        launch {
            val db = buildDb(getApplication())
            val sdf = SimpleDateFormat("dd MMMM yyyy")
            val currentDate = sdf.format(Date())
            //val masuk = "%$date%"
            totalCalory.value?.data1 = db.historyDao().selecttotalCalory(currentDate)
        }
    }

    fun cekHistory(){

        launch {
            val db = buildDb(getApplication())
            val sdf = SimpleDateFormat("dd MMMM yyyy")
            val currentDate = sdf.format(Date())
            val masuk = "%$currentDate%"
            angka1.value = db.historyDao().selectJumlahHistory(currentDate)
            jumlahHistory = db.historyDao().selectJumlahHistory(currentDate)
        }
    }

    fun fetch(uuid:Int)
    {
        launch{
            val db = buildDb(getApplication())
           foodLD.value = db.foodDao().selectFood(uuid)
        }
    }

    fun delete(food:Food)
    {
        launch{
            val db = buildDb(getApplication())
            db.foodDao().delete(food)
        }
    }

    fun updateHistory(fooodCalory2:Int, status2 :String, date2:String)
    {
        launch{
            val db = buildDb(getApplication())
            db.historyDao().updatehistory(fooodCalory2,status2,date2)
        }
    }

    fun addHistoryFromChosenFood(foodCalory:Int, status :String, date:String)
    {
        launch{
            val db = buildDb(getApplication())
            cekHistory()
            if(jumlahHistory>0){
                db.historyDao().updatehistory(foodCalory,status,date)
            }
            else{

            }
        }
    }

    fun totalCaloriesToday(date:String)
    {
        launch {
            val db = buildDb(getApplication())
            if(db.foodHistoryDao().selectTodayTotalCalories(date)==null){
                totalFoodsCalories.value = 0
            }
            else{
                totalFoodsCalories.value = db.foodHistoryDao().selectTodayTotalCalories(date)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}